/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.alpha.tss.logic.dto.Contract;

@NamedQueries({
    @NamedQuery(
            name = "TimeSheetEntity.getAllTimeSheets",
            query = "SELECT t FROM TimeSheetEntity t"
            + " ORDER BY t.id"),
    @NamedQuery(
            name = "TimeSheetEntity.getTimeSheetsFiltered",
            query = "SELECT t FROM TimeSheetEntity t"),
    @NamedQuery(
            name = "TimeSheetEntity.getTimeSheetById",
            query= "SELECT t FROM TimeSheetEntity t"
            + " WHERE t.id = :id"),
    @NamedQuery(
            name = "TimeSheetEntity.getTimeSheetsByContractId",
            query = "SELECT t FROM TimeSheetEntity t"
            + " JOIN FETCH t.contract"
            + " WHERE t.contract.id = :contractId")

})
@Entity
@Table(name = "TIMESHEET")
public class TimeSheetEntity extends AbstractEntity {

    private static final long serialVersionUID = 7107466550354850132L;

    @ManyToOne(optional = false)
    private ContractEntity contract;
    
    @OneToMany(mappedBy = "timesheet")
    private Set<TimeSheetEntryEntity> timeSheetEntries;
    
    @Enumerated(EnumType.STRING)
    private TimeSheetStatus status;
    
    private Date start;
    private Date end;
    private Integer hoursDue;
    
    public TimeSheetEntity() {
    }

    public TimeSheetEntity(ContractEntity contract, TimeSheetStatus status, Date start, Date end, Integer hoursDue) {
        this.contract = contract;
        this.status = status;
        this.start = start;
        this.end = end;
        this.hoursDue = hoursDue;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public Set<TimeSheetEntryEntity> getTimeSheetEntries() {
        return timeSheetEntries;
    }

    public void setTimeSheetEntries(Set<TimeSheetEntryEntity> timeSheetEntries) {
        this.timeSheetEntries = timeSheetEntries;
    }

    public TimeSheetStatus getStatus() {
        return status;
    }

    public void setStatus(TimeSheetStatus status) {
        this.status = status;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getHoursDue() {
        return hoursDue;
    }

    public void setHoursDue(Integer hoursDue) {
        this.hoursDue = hoursDue;
    }
}
