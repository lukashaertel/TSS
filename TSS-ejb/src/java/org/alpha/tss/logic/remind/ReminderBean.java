/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Lukas Härtel <lukashaertel@uni-koblenz.de>
 */
package org.alpha.tss.logic.remind;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.alpha.tss.entities.AssistantEntity;
import org.alpha.tss.entities.SecretaryEntity;
import org.alpha.tss.entities.TimeSheetFrequency;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dao.ContractAccess;
import org.alpha.tss.logic.dao.PersonAccess;
import org.alpha.tss.logic.dto.Contract;
import org.alpha.tss.logic.dto.Person;
import org.alpha.tss.logic.dto.TimeSheet;
import org.alpha.tss.util.mail.MessageBuilder;
import org.alpha.tss.util.mail.Multimaps;

/**
 *
 * @author Lukas Härtel
 */
@Stateless
@LocalBean
public class ReminderBean {

    @EJB
    private TssLogic logic;

    @EJB
    private ContractAccess ca;

    @Resource(lookup = "mail/uniko-mail")
    private Session mailSession;

    // TODO: Persistent might need to be true, requires keep-state setting to be true
    @Schedule(hour = "17", persistent = false)
    public void remind() {
        remindFor(LocalDate.now());
    }

    /**
     * Handles collecting and sending reminders for a given point in time
     *
     * @param now The time to handle reminders for
     */
    private void remindFor(LocalDate now) {
        // Collect reminders, so that we can group them later if desired
        Map<Person, Set<Reminder>> reminders = new HashMap<>();

        // Reminders are contract driven, since contracts contain role assignments
        for (Contract c : logic.getContracts())
            // Reminders are created for timesheets
            for (TimeSheet t : logic.getTimeSheetsByContractId(c.getId()))
                // Contract with time sheet on or after the last day
                if (now.isEqual(t.getEnd()) || now.isAfter(t.getEnd())) {
                    // Handle the cases given by RE1-RE3
                    switch (t.getStatus()) {
                        // In Progress and not after expiration, a.p. RE1
                        case IN_PROGRESS:
                            Multimaps.put(reminders, getEmployee(c), Reminder.forEmployee(c, t));
                            break;

                        // Signed by employee but not by supervisor, a.p. RE2
                        case SIGNED_BY_EMPLOYEE:
                            Multimaps.put(reminders, getSupervisor(c), Reminder.forSupervisor(c, t));
                            for (Person a : getAssistants(c))
                                Multimaps.put(reminders, a, Reminder.forAssistant(c, t));
                            break;

                        // Signed by supervisor but not archived, a.p. RE3
                        case SIGNED_BY_SUPERVISOR:
                            for (Person s : getSecretaries(c))
                                Multimaps.put(reminders, s, Reminder.forSecretary(c, t));
                            break;
                    }
                }

        // Process the selected reminders
        processReminders(reminders);
    }

    /**
     * Gets the actual supervisor for a contract
     *
     * @param contract The contract to evaluate
     * @return Returns the person associated to the role
     */
    private Person getSupervisor(Contract contract) {
        return logic.getPersonById(ca.getContract(contract.getId()).getSupervisor().getPerson().getId());
    }

    /**
     * Gets the actual employee for a contract
     *
     * @param contract The contract to evaluate
     * @return Returns the person associated to the role
     */
    private Person getEmployee(Contract contract) {
        return logic.getPersonById(ca.getContract(contract.getId()).getEmployee().getPerson().getId());
    }

    /**
     * Gets the actual assistants for a contract
     *
     * @param contract The contract to evaluate
     * @return Returns the person associated to the role
     */
    private Set<Person> getAssistants(Contract contract) {
        Set<Person> assistants = new HashSet<>();
        for (AssistantEntity a : ca.getContract(contract.getId()).getAssistants())
            assistants.add(logic.getPersonById(a.getPerson().getId()));
        return assistants;
    }

    /**
     * Gets the actual secretaries for a contract
     *
     * @param contract The contract to evaluate
     * @return Returns the person associated to the role
     */
    private Set<Person> getSecretaries(Contract contract) {
        Set<Person> secretaries = new HashSet<>();
        for (SecretaryEntity s : ca.getContract(contract.getId()).getSecretaries())
            secretaries.add(logic.getPersonById(s.getPerson().getId()));
        return secretaries;
    }

    /**
     * Processes a set of reminders by dispatching them grouped or individually
     *
     * @param reminders The reminders to dispatch
     */
    private void processReminders(Map<Person, Set<Reminder>> reminders) {
        // Pair of person and all their reminders
        for (Map.Entry<Person, Set<Reminder>> entry : reminders.entrySet())
            // Do not remind if erroneous input of empty sets
            if (!entry.getValue().isEmpty()) {
                // Select the appropriate sending method
                if (entry.getKey().isGroupReminders())
                    sendGroupedReminders(entry.getKey(), entry.getValue());
                else
                    sendIndividualReminders(entry.getKey(), entry.getValue());
            }
    }

    /**
     * Processes a persons set of reminders individually
     *
     * @param person The person to remind for
     * @param reminders The reminders
     */
    public /* private */ void sendIndividualReminders(Person person, Set<Reminder> reminders) {
        ReminderMessages messages = new ReminderMessages(person.getPreferredLocale(),
                "contract-details.xhtml?contractId={0}",
                "timesheet-details.xhtml?timeSheetId={0}");

        // TODO Use proper template engine
        
        for (Reminder reminder : reminders)
            try {
                StringBuilder builder = new StringBuilder();

                // Add common header
                builder
                        .append("<p>")
                        .append(messages.header(person))
                        .append("</p>");

                // Initialize content based on role
                switch (reminder.getReminderRole()) {
                    case ASSISTANT:
                        builder
                                .append("<p>")
                                .append(messages.individualAssistant(reminder.getContract(), reminder.getTimeSheet()))
                                .append("</p>");
                        break;

                    case EMPLOYEE:
                        builder
                                .append("<p>")
                                .append(messages.individualEmployee(reminder.getContract(), reminder.getTimeSheet()))
                                .append("</p>");
                        break;

                    case SECRETARY:
                        builder
                                .append("<p>")
                                .append(messages.individualSecretary(reminder.getContract(), reminder.getTimeSheet()))
                                .append("</p>");
                        break;

                    case SUPERVISOR:
                        builder
                                .append("<p><sub>")
                                .append(messages.individualSupervisor(reminder.getContract(), reminder.getTimeSheet()))
                                .append("</sub></p>");
                        break;
                }

                // Add common footer
                builder
                        .append("<p>")
                        .append(messages.footer())
                        .append("</p>");

                // Build message with appropriate content and send
                MessageBuilder
                        .on(mailSession)
                        .to(person.getEmail())
                        .subject(messages.individual())
                        .html(builder.toString())
                        .send();
            } catch (MessagingException ex) {
                Logger.getLogger(ReminderBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    /**
     * Processes a persons set of reminders using grouping
     *
     * @param person The person to remind for
     * @param reminders The reminders
     */
    public /* private */ void sendGroupedReminders(Person person, Set<Reminder> reminders) {
        ReminderMessages messages = new ReminderMessages(person.getPreferredLocale(),
                "contract-details.xhtml?contractId={0}",
                "timesheet-details.xhtml?timeSheetId={0}");

        // TODO Use proper template engine
        
        // Filter based on participation type
        List<Reminder> asAssistant = new ArrayList<>();
        List<Reminder> asEmployee = new ArrayList<>();
        List<Reminder> asSecretary = new ArrayList<>();
        List<Reminder> asSupervisor = new ArrayList<>();

        // Add to appropriate list
        for (Reminder reminder : reminders)
            switch (reminder.getReminderRole()) {
                case ASSISTANT:
                    asAssistant.add(reminder);
                    break;

                case EMPLOYEE:
                    asEmployee.add(reminder);
                    break;

                case SECRETARY:
                    asSecretary.add(reminder);
                    break;

                case SUPERVISOR:
                    asSupervisor.add(reminder);
                    break;
            }

        try {
            StringBuilder builder = new StringBuilder();

            // Add common header
            builder
                    .append("<p>")
                    .append(messages.header(person))
                    .append("</p>");

            // Add assistant messages
            switch (asAssistant.size()) {
                case 0:
                    break;
                case 1:
                    Reminder s = asAssistant.get(0);
                    builder
                            .append("<p>")
                            .append(messages.individualAssistant(s.getContract(), s.getTimeSheet()))
                            .append("</p>");
                    break;
                default:
                    builder
                            .append("<p>")
                            .append(messages.groupedAssistant(asAssistant.size()))
                            .append("</p>")
                            .append("<p>")
                            .append("<ul>");

                    for (Reminder r : asAssistant)
                        builder
                                .append("<li>")
                                .append(messages.groupedItem(r.getContract(), r.getTimeSheet()))
                                .append("</li>");

                    builder
                            .append("</ul>")
                            .append("</p>");
            }

            // Add employee messages
            switch (asEmployee.size()) {
                case 0:
                    break;
                case 1:
                    Reminder s = asEmployee.get(0);
                    builder
                            .append("<p>")
                            .append(messages.individualEmployee(s.getContract(), s.getTimeSheet()))
                            .append("</p>");
                    break;
                default:
                    builder
                            .append("<p>")
                            .append(messages.groupedEmployee(asEmployee.size()))
                            .append("</p>")
                            .append("<p>")
                            .append("<ul>");

                    for (Reminder r : asEmployee)
                        builder
                                .append("<li>")
                                .append(messages.groupedItem(r.getContract(), r.getTimeSheet()))
                                .append("</li>");

                    builder
                            .append("</ul>")
                            .append("</p>");
            }

            // Add secretary messages
            switch (asSecretary.size()) {
                case 0:
                    break;
                case 1:
                    Reminder s = asSecretary.get(0);
                    builder
                            .append("<p>")
                            .append(messages.individualEmployee(s.getContract(), s.getTimeSheet()))
                            .append("</p>");
                    break;
                default:
                    builder
                            .append("<p>")
                            .append(messages.groupedEmployee(asSecretary.size()))
                            .append("</p>")
                            .append("<p>")
                            .append("<ul>");

                    for (Reminder r : asSecretary)
                        builder
                                .append("<li>")
                                .append(messages.groupedItem(r.getContract(), r.getTimeSheet()))
                                .append("</li>");

                    builder
                            .append("</ul>")
                            .append("</p>");
            }
            // Add supervisor messages
            switch (asSupervisor.size()) {
                case 0:
                    break;
                case 1:
                    Reminder s = asSecretary.get(0);
                    builder
                            .append("<p>")
                            .append(messages.individualEmployee(s.getContract(), s.getTimeSheet()))
                            .append("</p>");
                    break;
                default:
                    builder
                            .append("<p>")
                            .append(messages.groupedEmployee(asSupervisor.size()))
                            .append("</p>")
                            .append("<p>")
                            .append("<ul>");

                    for (Reminder r : asSupervisor)
                        builder
                                .append("<li>")
                                .append(messages.groupedItem(r.getContract(), r.getTimeSheet()))
                                .append("</li>");

                    builder
                            .append("</ul>")
                            .append("</p>");
            }

            // Add common footer
            builder
                    .append("<p>")
                    .append(messages.footer())
                    .append("</p>");

            // Build message with appropriate content and send
            MessageBuilder
                    .on(mailSession)
                    .to(person.getEmail())
                    .subject(messages.grouped())
                    .html(builder.toString())
                    .send();
        } catch (MessagingException ex) {
            Logger.getLogger(ReminderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
