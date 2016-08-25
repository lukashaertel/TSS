/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dto;

import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TimeSheetEntry extends AbstractTransferObject {    
    private static final long serialVersionUID = -3006745004450084005L;
    
    private String descriptionOfWork;
    private String comment;
    private Date date;
    private Integer hours;
    
    public TimeSheetEntry() {
    }    

    public TimeSheetEntry( long id, String descriptionOfWork, String comment, Date date, Integer hours) {
        super(id);
        this.descriptionOfWork = descriptionOfWork;
        this.comment = comment;
        this.date = date;
        this.hours = hours;
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
