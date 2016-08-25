/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(
            name = "TimeSheetEntryEntity.getAllTimeSheetEntries",
            query = "SELECT t FROM TimeSheetEntryEntity t"
            + " ORDER BY t.id"),
    @NamedQuery(
            name = "TimeSheetEntity.getTimeSheetEntriesFiltered",
            query = "SELECT t FROM TimeSheetEntryEntity t")

})
@Entity
@Table(name = "TIMESHEETENTRY")
public class TimeSheetEntryEntity extends AbstractEntity {

    private static final long serialVersionUID = 5786260868363176288L;
    
    @ManyToOne(optional = false)
    private TimeSheetEntity timesheet;
    
    private String descriptionOfWork;
    private String comment;
    private Date date;
    private Integer hours;
    
    public TimeSheetEntryEntity() {
    }

    public TimeSheetEntryEntity(TimeSheetEntity timesheet, String descriptionOfWork, String comment, Date date, Integer hours) {
        this.timesheet = timesheet;
        this.descriptionOfWork = descriptionOfWork;
        this.comment = comment;
        this.date = date;
        this.hours = hours;
    }

    public TimeSheetEntity getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(TimeSheetEntity timesheet) {
        this.timesheet = timesheet;
    }

    public String getDescriptionOfWork() {
        return descriptionOfWork;
    }

    public void setDescriptionOfWork(String descriptionOfWork) {
        this.descriptionOfWork = descriptionOfWork;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
