/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import javax.ejb.Remote;
import org.alpha.tss.entities.ContractEntity;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.entities.ContractType;
import org.alpha.tss.entities.TimeSheetEntity;
import org.alpha.tss.entities.TimeSheetFrequency;
import org.alpha.tss.entities.TimeSheetStatus;
import org.alpha.tss.logic.dto.Contract;
import org.alpha.tss.logic.dto.Person;
import org.alpha.tss.logic.dto.TimeSheet;
import org.alpha.tss.logic.dto.Project;
import org.alpha.tss.logic.dto.TimeSheetEntry;

@Remote
public interface TssLogic {
    /*
     *  Contract
     */
    public Contract createContract(ContractType contractType,
            String name, String description,
            String comment, TimeSheetFrequency frequency, Integer hoursPerWeek,
            Integer totalHoursDue, Integer vacationHours, Currency salary,
            LocalDate start, LocalDate end, LocalDate abort, Integer workingDaysPerWeek,
            Integer vacationDaysPerWeek);
    
    public Contract getContractById(long id);
    public List<Contract> getContracts();    
    public List<Contract> getContractsFiltered(String filter);
    public Contract setContractStatus(long id, ContractStatus status);
    public Contract updateContract(Contract contract);
    public Contract startContract(long id);
    public boolean isAbortContractAllowed(long id);
    public boolean abortContract(long id);
    public void deleteContract(long id);
    
    /*
     *  TimeSheet
     */
    public TimeSheet createTimeSheet(ContractEntity contract, 
            TimeSheetStatus status, LocalDate start, LocalDate end, Integer hoursDue);
    public TimeSheet getTimeSheetById(long id);
    public List<TimeSheet> getTimeSheets();
    public List<TimeSheet> getTimeSheetsByContractId(long contractId);
    public List<TimeSheet> getTimeSheetsFiltered(String filter);
    public void deleteTimeSheetById(long id);
    public void deleteTimeSheetsByContractId(long contractId);
    
    /*
     *  TimeSheetEntry
     */
    public TimeSheetEntry createTimeSheetEntry(long timeSheetId,
            String descriptionOfWork, String comment, LocalDate date, Integer hours);
    public TimeSheetEntry getTimeSheetEntryById(long id);
    public List<TimeSheetEntry> getTimeSheetEntriesByContractId(long contractId);
    public List<TimeSheetEntry> getTimeSheetEntriesByTimeSheetId(long timeSheetId);
    
    /*
     *  Project
     */
    public Project getProjectById(long id);
    public List<Project> getProjects();
    public Project createProject(String name);
    
    /*
     * Person
     */
    public Person getPersonById(long id);
    public Person getPersonByMail(String mail);
    public List<Person> getPersons();
    public Person createPerson(String firstname, String lastname, String email, String title, LocalDate dateOfBirth, Locale preferredLocale, boolean groupReminders);
}
