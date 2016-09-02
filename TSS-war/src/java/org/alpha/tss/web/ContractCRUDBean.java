/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Mike Scheja <mscheja@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.entities.ContractType;
import static org.alpha.tss.entities.TimeSheetEntryEntity_.date;
import org.alpha.tss.entities.TimeSheetFrequency;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dto.Contract;

@Named
@RequestScoped
public class ContractCRUDBean implements Serializable {
    
    @EJB
    private TssLogic tssLogic;

    // contract fields
    private ContractType contractType;
    private ContractStatus contractStatus;
    private String name;
    private String description;
    private String comment;
    private TimeSheetFrequency frequency;
    private Integer hoursPerWeek;
    private Integer totalHoursDue;
    private Integer vacationHours;
    private Currency salary;
    private Date start;
    private Date end;
    private LocalDate abort;
    private Integer workingDaysPerWeek;
    private Integer vacationDaysPerYear;
    
    public String createContract(){
        
        Instant instant = Instant.ofEpochMilli(start.getTime());
        LocalDate startDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

        Instant instant_end = Instant.ofEpochMilli(end.getTime());
        LocalDate endDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        
        Contract contract = tssLogic.createContract(
                this.contractType,
                this.name,
                this.description,
                this.comment,
                this.frequency,
                this.hoursPerWeek,
                2000, 240,
                Currency.getInstance("EUR"),
                startDate,
                endDate,
                LocalDate.of(2016, 10, 10),
                this.workingDaysPerWeek,
                1);

        return "contract-details.xhtml?faces-redirect=true&contractId=" + contract.getId();
    }

    public TssLogic getTssLogic() {
        return tssLogic;
    }

    public void setTssLogic(TssLogic tssLogic) {
        this.tssLogic = tssLogic;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TimeSheetFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(TimeSheetFrequency frequency) {
        this.frequency = frequency;
    }

    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public Integer getTotalHoursDue() {
        return totalHoursDue;
    }

    public void setTotalHoursDue(Integer totalHoursDue) {
        this.totalHoursDue = totalHoursDue;
    }

    public Integer getVacationHours() {
        return vacationHours;
    }

    public void setVacationHours(Integer vacationHours) {
        this.vacationHours = vacationHours;
    }

    public Currency getSalary() {
        return salary;
    }

    public void setSalary(Currency salary) {
        this.salary = salary;
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

    public LocalDate getAbort() {
        return abort;
    }

    public void setAbort(LocalDate abort) {
        this.abort = abort;
    }

    public Integer getWorkingDaysPerWeek() {
        return workingDaysPerWeek;
    }

    public void setWorkingDaysPerWeek(Integer workingDaysPerWeek) {
        this.workingDaysPerWeek = workingDaysPerWeek;
    }

    public Integer getVacationDaysPerYear() {
        return vacationDaysPerYear;
    }

    public void setVacationDaysPerYear(Integer vacationDaysPerYear) {
        this.vacationDaysPerYear = vacationDaysPerYear;
    }
    
    }
