/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic;

import java.sql.Date;
import java.util.Currency;
import java.util.List;
import javax.ejb.Remote;
import org.alpha.tss.entities.ContractEntity;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.entities.ContractType;
import org.alpha.tss.entities.TimeSheetFrequency;
import org.alpha.tss.entities.TimeSheetStatus;
import org.alpha.tss.logic.dto.Contract;
import org.alpha.tss.logic.dto.TimeSheet;
import org.alpha.tss.logic.dto.Project;

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
    
    /*
     *  TimeSheet
     */
    public TimeSheet createTimeSheet(ContractEntity contract, 
            TimeSheetStatus status, Date start, Date end, Integer hoursDue);
    public TimeSheet getTimeSheetById(long id);
    public List<TimeSheet> getTimeSheetsByContractId(long contractId);
    public List<TimeSheet> getTimeSheetsFiltered(String filter);
    
    /*
     *  Project
     */
    public List<Project> getProjects();
}
