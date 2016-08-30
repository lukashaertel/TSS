/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import org.alpha.tss.entities.ContractEntity;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.entities.ContractType;
import org.alpha.tss.entities.TimeSheetEntity;
import org.alpha.tss.entities.TimeSheetFrequency;
import org.alpha.tss.entities.TimeSheetStatus;
import org.alpha.tss.logic.dto.Contract;
import org.alpha.tss.logic.dto.TimeSheet;
import org.alpha.tss.logic.dto.Project;
import org.alpha.tss.logic.dto.TimeSheetEntry;

@Remote
public interface TssLogic {
    /*
     *  Contract
     */
    public Contract createContract(ContractType contractType,
            ContractStatus contractStatus, String name, String description,
            String comment, TimeSheetFrequency frequency, Integer hoursPerWeek,
            Integer totalHoursDue, Integer vacationHours, Currency salary,
            Date start, Date end, Date abort, Integer workingDaysPerWeek,
            Integer vacationDaysPerWeek);
    
    public Contract getContractById(long id);
    public List<Contract> getContracts();    
    public List<Contract> getContractsFiltered(String filter);
    public Contract setContractStatus(long id, ContractStatus status);
    public Contract updateContract(Contract contract);
    
    /*
     *  TimeSheet
     */
    public TimeSheet createTimeSheet(ContractEntity contract, 
            TimeSheetStatus status, Date start, Date end, Integer hoursDue);
    public TimeSheet getTimeSheetById(long id);
    public List<TimeSheet> getTimeSheetsByContractId(long contractId);
    public List<TimeSheet> getTimeSheetsFiltered(String filter);
    public void deleteTimeSheetById(long id);
    public void deleteTimeSheetsByContractId(long contractId);
    
    /*
     *  TimeSheetEntry
     */
    public TimeSheetEntry createTimeSheetEntry(TimeSheetEntity timesheet,
            String descriptionOfWork, String comment, Date date, Integer hours);
    public TimeSheetEntry getTimeSheetEntryById(long id);
    public List<TimeSheetEntry> getTimeSheetEntriesByContractId(long contractId);
    
    /*
     *  Project
     */
    public Project getProjectById(long id);
    public List<Project> getProjects();
    public Project createProject(String name);
}
