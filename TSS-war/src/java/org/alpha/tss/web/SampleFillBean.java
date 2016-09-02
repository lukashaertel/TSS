/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Lukas Härtel <lukashaertel@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.MessagingException;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.entities.ContractType;
import org.alpha.tss.entities.TimeSheetFrequency;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dto.Contract;
import org.alpha.tss.logic.dto.Person;
import org.alpha.tss.logic.dto.Project;
import org.alpha.tss.logic.dto.ProjectEntry;
import org.alpha.tss.logic.dto.TimeSheet;
import org.alpha.tss.logic.dto.TimeSheetEntry;
import org.alpha.tss.logic.remind.Reminder;
import org.alpha.tss.logic.remind.ReminderBean;

@RequestScoped
@Named
public class SampleFillBean implements Serializable {

    private static final long serialVersionUID = 318991485487412552L;

    @EJB
    private TssLogic tssLogic;

    @EJB
    private ReminderBean reminder;

    public void sendMails() {
        Contract c = tssLogic.getContracts().get(0);
        TimeSheet t1 = tssLogic.getTimeSheetsByContractId(c.getId()).get(0);
        TimeSheet t2 = tssLogic.getTimeSheetsByContractId(c.getId()).get(1);
        Person p = tssLogic.getPersons().get(0);

        Reminder a1 = Reminder.forAssistant(c, t1);
        Reminder a2 = Reminder.forAssistant(c, t2);
        Reminder a3 = Reminder.forEmployee(c, t1);

        Set<Reminder> s = new HashSet<>();
        s.add(a1);
        s.add(a2);
        s.add(a3);

        reminder.sendGroupedReminders(p, s);
        reminder.sendIndividualReminders(p, s);
    }

    public String sampleContract() {

        Contract contract = tssLogic.createContract(
                ContractType.FIXED_HOURS,
                "Example Contract Fixed Hours",
                "An example contract with fixed hours as mode",
                "Created by sample fill",
                TimeSheetFrequency.MONTHLY,
                40,
                2000, 240,
                Currency.getInstance("EUR"),
                LocalDate.of(2016, 10, 10),
                LocalDate.of(2016, 11, 10),
                LocalDate.of(2016, 10, 10),
                6,
                1);

        return "contract-details.xhtml?faces-redirect=true&contractId=" + contract.getId();
    }

    public String samplePerson() {
        Person person = tssLogic.createPerson(
                "Lukas",
                "Härtel",
                "lukashaertel@uni-koblenz.de",
                null,
                LocalDate.of(1990, 10, 29));

        return "person-details.xhtml?faces-redirect=true&personId=" + person.getId();
    }

    public String sampleProject() {
        Project project = tssLogic.createProject("Testprojekt");

        return "project-details.xhtml?faces-redirect=true&projectId=" + project.getId();
    }
    
    public String sampleTimeSheetEntry(long timeSheetId) {
        TimeSheetEntry tentry = tssLogic.createTimeSheetEntry(timeSheetId, "Test", 
                "TestComment", LocalDate.now(), 0);
        return "timesheet-details.xhtml?faces-redirect=true&timeSheetId=" + timeSheetId;
    }
}
