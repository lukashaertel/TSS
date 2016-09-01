/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alpha.tss.logic.remind;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import org.alpha.tss.logic.dto.Contract;
import org.alpha.tss.logic.dto.Person;
import org.alpha.tss.logic.dto.TimeSheet;

/**
 *
 * @author pazuzu
 */
public class ReminderMessages {

    private final ResourceBundle bundle;

    private final String contractURLForId;

    private final String timeSheetURLForId;

    public ReminderMessages(Locale locale, String contractURLForId, String timeSheetURLForId) {
        this.bundle = ResourceBundle.getBundle(ReminderMessages.class.getPackage().getName() + ".reminders", locale);
        this.contractURLForId = contractURLForId;
        this.timeSheetURLForId = timeSheetURLForId;
    }

    public String header(Person person) {
        return MessageFormat.format(bundle.getString("header"), person.getTitle() == null ? "" : person.getTitle(), person.getLastname());
    }

    public String formatContract(Contract contract) {
        return "<a href=\"" + MessageFormat.format(contractURLForId, contract.getId()) + "\">" + contract.getName() + "</a>";
    }

    public String formatTimeSheet(TimeSheet timeSheet) {
        return "<a href=\"" + MessageFormat.format(timeSheetURLForId, timeSheet.getId()) + "\">" + timeSheet.getId() + "</a>";
    }

    public String individual() {
        return bundle.getString("individual");
    }

    public String grouped() {
        return bundle.getString("grouped");
    }

    public String individualSupervisor(Contract contract, TimeSheet timeSheet) {
        return MessageFormat.format(bundle.getString("individual.supervisor"), formatContract(contract), formatTimeSheet(timeSheet));
    }

    public String individualAssistant(Contract contract, TimeSheet timeSheet) {
        return MessageFormat.format(bundle.getString("individual.assistant"), formatContract(contract), formatTimeSheet(timeSheet));
    }

    public String individualEmployee(Contract contract, TimeSheet timeSheet) {
        return MessageFormat.format(bundle.getString("individual.employee"), formatContract(contract), formatTimeSheet(timeSheet));
    }

    public String individualSecretary(Contract contract, TimeSheet timeSheet) {
        return MessageFormat.format(bundle.getString("individual.secretary"), formatContract(contract), formatTimeSheet(timeSheet));
    }

    public String groupedSupervisor(int nTimeSheets) {
        return MessageFormat.format(bundle.getString("grouped.supervisor"), nTimeSheets);
    }

    public String groupedAssistant(int nTimeSheets) {
        return MessageFormat.format(bundle.getString("grouped.assistant"), nTimeSheets);
    }

    public String groupedEmployee(int nTimeSheets) {
        return MessageFormat.format(bundle.getString("grouped.employee"), nTimeSheets);
    }

    public String groupedSecretary(int nTimeSheets) {
        return MessageFormat.format(bundle.getString("grouped.secretary"), nTimeSheets);
    }

    public String groupedItem(Contract contract, TimeSheet timeSheet) {
        return MessageFormat.format(bundle.getString("grouped.item"), formatContract(contract), formatTimeSheet(timeSheet));
    }

    public String footer() {
        return bundle.getString("footer");
    }
}
