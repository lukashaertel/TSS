/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Lukas Härtel <lukashaertel@uni-koblenz.de>
 */
package org.alpha.tss.logic.remind;

import java.util.Objects;
import org.alpha.tss.logic.dto.Contract;
import org.alpha.tss.logic.dto.TimeSheet;

/**
 * Encapsules a reminder associated to a person externally.
 *
 * @author Lukas Härtel
 */
public final class Reminder {

    private final ReminderRole reminderRole;

    private final Contract contract;

    private final TimeSheet timeSheet;

    public Reminder(ReminderRole reminderRole, Contract contract, TimeSheet timeSheet) {
        this.reminderRole = reminderRole;
        this.contract = contract;
        this.timeSheet = timeSheet;
    }

    public static Reminder forSupervisor(Contract contract, TimeSheet timeSheet) {
        return new Reminder(ReminderRole.SUPERVISOR, contract, timeSheet);
    }

    public static Reminder forAssistant(Contract contract, TimeSheet timeSheet) {
        return new Reminder(ReminderRole.ASSISTANT, contract, timeSheet);
    }

    public static Reminder forEmployee(Contract contract, TimeSheet timeSheet) {
        return new Reminder(ReminderRole.EMPLOYEE, contract, timeSheet);
    }

    public static Reminder forSecretary(Contract contract, TimeSheet timeSheet) {
        return new Reminder(ReminderRole.SECRETARY, contract, timeSheet);
    }

    public ReminderRole getReminderRole() {
        return reminderRole;
    }

    public Contract getContract() {
        return contract;
    }

    public TimeSheet getTimeSheet() {
        return timeSheet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Reminder other = (Reminder) obj;
        if (this.reminderRole != other.reminderRole)
            return false;
        if (!Objects.equals(this.contract, other.contract))
            return false;
        if (!Objects.equals(this.timeSheet, other.timeSheet))
            return false;
        return true;
    }

}
