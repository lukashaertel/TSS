/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dto;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlRootElement;
import org.alpha.tss.entities.TimeSheetStatus;

@XmlRootElement
public class TimeSheet extends AbstractTransferObject {    
    private static final long serialVersionUID = -4808026614383646599L;
    
    private TimeSheetStatus status;
    private LocalDate start;
    private LocalDate end;
    private Integer hoursDue;
    
    public TimeSheet() {
    }
    
    public TimeSheet(long id, TimeSheetStatus status, LocalDate start, LocalDate end,
            Integer hoursDue) {
        super(id);
        this.status = status;
        this.start = start;
        this.end = end;
        this.hoursDue = hoursDue;
    }

    public TimeSheetStatus getStatus() {
        return status;
    }

    public void setStatus(TimeSheetStatus status) {
        this.status = status;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Integer getHoursDue() {
        return hoursDue;
    }

    public void setHoursDue(Integer hoursDue) {
        this.hoursDue = hoursDue;
    }
}
