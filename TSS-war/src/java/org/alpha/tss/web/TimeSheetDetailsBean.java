/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dto.TimeSheet;
import org.alpha.tss.logic.dto.TimeSheetEntry;

@RequestScoped
@Named
public class TimeSheetDetailsBean implements Serializable {

    private static final long serialVersionUID = -4894044287710895295L;
    
    @EJB
    private TssLogic tssLogic;
    
    private long id;
    private TimeSheet timeSheet;
    private List<TimeSheetEntry> timeSheetEntries;

    public TimeSheet getTimeSheet() {
        return timeSheet;
    }

    public List<TimeSheetEntry> getTimeSheetEntries() {
        return timeSheetEntries;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        this.timeSheet = tssLogic.getTimeSheetById(id);
        this.timeSheetEntries = tssLogic.getTimeSheetEntriesByTimeSheetId(id);
    }
}