/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.entities;

//import java.time.Duration;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(
            name = "ContractEntity.getAllContracts",
            query = "SELECT c FROM ContractEntity c"
            + " ORDER BY c.id DESC"),
    @NamedQuery(
            name = "ContractEntity.getContractById",
            query = "SELECT c FROM ContractEntity c"
            + " WHERE c.id = :id"),
    @NamedQuery(
            name = "ContractEntity.getContractsFiltered",
            query = "SELECT c FROM ContractEntity c")

})
@Entity
@Table(name = "CONTRACT")
public class ContractEntity extends AbstractEntity {

    private static final long serialVersionUID = -3380846376240623081L;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractType type;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractStatus status;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private String comment;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimeSheetFrequency frequency;
    
    @Column(nullable = false)
    private Integer hoursPerWeek;
    
    @Column(nullable = false)
    private Integer totalHoursDue;
    
    @Column(nullable = false)
    private Integer vacationHours;
    
    @Column(nullable = true)
    private Currency salary;
    
    @Column(nullable = false)
    private LocalDate start;
    
    @Column(nullable = false)
    private LocalDate end;
    
    @Column(nullable = false)
    private LocalDate abort;
    
    @Column(nullable = false)
    private Integer workingDaysPerWeek;
    
    @Column(nullable = false)
    private Integer vacationDaysPerYear;
    
    @OneToMany(mappedBy = "contract")
    private Set<TimeSheetEntity> timeSheets;
    
    @OneToOne(mappedBy = "contract")
    private SupervisorEntity supervisor;
    
    @OneToOne(mappedBy = "contract")
    private EmployeeEntity employee;
    
    @OneToMany(mappedBy = "contract")
    private Set<AssistantEntity> assistants;
    
    @OneToMany(mappedBy = "contract")
    private Set<SecretaryEntity> secretaries;
    
    @ManyToMany(mappedBy = "contracts")
    private Set<ProjectEntity> projects;
    
    public ContractEntity() {
        
    }

    public ContractEntity(ContractType type, ContractStatus status, String name,
            String description, String comment, TimeSheetFrequency frequency,
            Integer hoursPerWeek, Integer totalHoursDue, Integer vacationHours,
            Currency salary, LocalDate start, LocalDate end, LocalDate abort,
            Integer workingDaysPerWeek, Integer vacationDaysPerYear) {
        this.type = type;
        this.status = status;
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

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
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

    public Set<TimeSheetEntity> getTimeSheets() {
        return timeSheets;
    }

    public void setTimeSheets(Set<TimeSheetEntity> timeSheets) {
        this.timeSheets = timeSheets;
    }
}