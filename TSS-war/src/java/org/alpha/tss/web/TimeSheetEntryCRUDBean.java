/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Mike Scheja <mscheja@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.alpha.tss.entities.TimeSheetEntity;
import org.alpha.tss.entities.TimeSheetEntryEntity;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dto.TimeSheetEntry;

@Named
@RequestScoped
public class TimeSheetEntryCRUDBean implements Serializable {
    
    @EJB
    private TssLogic tssLogic;
    
    private TimeSheetEntity timesheet;
    
    private String descriptionOfWork;
    private String comment;
    private Date date;
    private Integer hours;
    
    public String createTimeSheetEntry(long timeSheetId) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate entryDate= LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        
        TimeSheetEntry tentry = tssLogic.createTimeSheetEntry(timeSheetId,
                                                              this.descriptionOfWork, 
                                                              this.comment,
                                                              entryDate,
                                                              this.hours);
        return "timesheet-details.xhtml?faces-redirect=true&timeSheetId=" + timeSheetId;
    }

    public TssLogic getTssLogic() {
        return tssLogic;
    }

    public void setTssLogic(TssLogic tssLogic) {
        this.tssLogic = tssLogic;
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
