/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dto;

import java.time.LocalDate;
import java.util.Currency;
import javax.xml.bind.annotation.XmlRootElement;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.entities.ContractType;
import org.alpha.tss.entities.TimeSheetFrequency;

@XmlRootElement
public class Contract extends AbstractTransferObject {
    private static final long serialVersionUID = 2302756521389773356L;
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
    private LocalDate start;
    private LocalDate end;
    private LocalDate abort;
    private Integer workingDaysPerWeek;
    private Integer vacationDaysPerYear;
    
    public Contract() {
    }
    
    public Contract(long id, ContractType contractType,
            ContractStatus contractStatus, String name, String description,
            String comment, TimeSheetFrequency frequency, Integer hoursPerWeek,
            Integer totalHoursDue, Integer vacationHours, Currency salary,
            LocalDate start, LocalDate end, LocalDate abort, Integer workingDaysPerWeek,
            Integer vacationDaysPerYear)
    {
        super(id);
        this.contractType = contractType;
        this.contractStatus = contractStatus;
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.frequency = frequency;
        this.hoursPerWeek = hoursPerWeek;
        this.totalHoursDue = totalHoursDue;
        this.vacationHours = vacationHours;
        this.salary = salary;
        this.start = start;
        this.end = end;
        this.abort = abort;
        this.workingDaysPerWeek = workingDaysPerWeek;
        this.vacationDaysPerYear = vacationDaysPerYear;
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