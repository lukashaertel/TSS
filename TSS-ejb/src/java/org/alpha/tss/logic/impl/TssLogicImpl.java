/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.impl;

import static java.lang.Math.toIntExact;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.alpha.tss.entities.ContractEntity;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.entities.ContractType;
import org.alpha.tss.entities.TimeSheetEntity;
import org.alpha.tss.entities.TimeSheetFrequency;
import org.alpha.tss.entities.TimeSheetStatus;
import org.alpha.tss.entities.ProjectEntity;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dao.ContractAccess;
import org.alpha.tss.logic.dao.TimeSheetAccess;
import org.alpha.tss.logic.dao.TimeSheetEntryAccess;
import org.alpha.tss.logic.dao.ProjectAccess;
import org.alpha.tss.logic.dto.Contract;
import org.alpha.tss.logic.dto.TimeSheet;
import org.alpha.tss.logic.dto.Project;
import org.alpha.tss.logic.dto.TimeSheetEntry;

@Stateless
public class TssLogicImpl implements TssLogic {

    @EJB
    private ContractAccess ca;
    @EJB
    private TimeSheetAccess ta;
    @EJB
    private TimeSheetEntryAccess tea;
    @EJB
    private ProjectAccess pa;
    
    @Override
    //@RolesAllowed("ACCTMGR")
    public Contract getContractById(long id) {
        Contract result = new Contract();
        result = createContractTO(ca.getContract(id));
        return result;
    }
    
    @Override
    //@RolesAllowed("ACCTMGR")
    public List<Contract> getContracts() {
        List<Contract> result = new ArrayList<>();
        for (ContractEntity c : ca.getContracts()) {
            result.add(createContractTO(c));
        }
        return result;
    }

    //@RolesAllowed("ACCTMGR")
    private Contract createContractTO(ContractEntity c) {
        if (c == null) {
            return null;
        }
        return new Contract(c.getId(), c.getType(), c.getStatus(), c.getName(),
            c.getDescription(), c.getComment(), c.getFrequency(),
            c.getHoursPerWeek(), c.getTotalHoursDue(), c.getVacationHours(),
            c.getSalary(), c.getStart(), c.getEnd(), c.getAbort(),
            c.getWorkingDaysPerWeek(), c.getVacationDaysPerYear());
    }

    @Override
    //@RolesAllowed("ACCTMGR")
    public List<Contract> getContractsFiltered(String filter) {
        List<Contract> result = new ArrayList<>();
        for (ContractEntity c : ca.getContractsFiltered(filter)) {
            result.add(createContractTO(c));
        }
        return result;
    }
    
    @Override
    //@RolesAllowed("assistant")
    public Contract createContract(ContractType contractType,
            ContractStatus contractStatus, String name, String description,
            String comment, TimeSheetFrequency frequency, Integer hoursPerWeek,
            Integer totalHoursDue, Integer vacationHours, Currency salary,
            Date start, Date end, Date abort, Integer workingDaysPerWeek,
            Integer vacationDaysPerWeek) {
        ContractEntity c = ca.createContract(contractType,
            contractStatus, name, description, comment, frequency, hoursPerWeek,
            totalHoursDue, vacationHours, salary, start, end, abort, 
            workingDaysPerWeek, vacationDaysPerWeek);       
        
        return createContractTO(c);
    }
    
    @Override
    public Contract setContractStatus(long id, ContractStatus status) {
        return createContractTO(ca.setContractStatus(id, status));
    }
    
    @Override
    public Contract updateContract(Contract c) {
        return createContractTO(ca.updateContract(c.getId(), c.getStart(),
                c.getEnd(), c.getFrequency(), c.getHoursPerWeek(),
                c.getTotalHoursDue(), c.getWorkingDaysPerWeek(), 
                c.getVacationDaysPerYear()));
        }
    
    @Override
    //@RolesAllowed("assistant")
    public TimeSheet createTimeSheet(ContractEntity contract, 
            TimeSheetStatus status, Date start, Date end, Integer hoursDue) {
        TimeSheetEntity t = ta.createTimeSheet(contract, status, start, end,
                hoursDue);
        return new TimeSheet(t.getId(), t.getStatus(), t.getStart(), t.getEnd(),
                t.getHoursDue());
    }
    
    //@RolesAllowed("ACCTMGR")
    private TimeSheet createTimeSheetTO(TimeSheetEntity t) {
        if (t == null) {
            return null;
        }
        return new TimeSheet(t.getId(), t.getStatus(), t.getStart(), t.getEnd(),
            t.getHoursDue());
    }
    
    @Override
    //@RolesAllowed("ACCTMGR")
    public List<TimeSheet> getTimeSheetsFiltered(String filter) {
        List<TimeSheet> result = new ArrayList<>();
        for (TimeSheetEntity t : ta.getTimeSheetsFiltered(filter)) {
            result.add(createTimeSheetTO(t));
        }
        return result;
    }

    @Override
    public TimeSheet getTimeSheetById(long id) {
        TimeSheet result = createTimeSheetTO(ta.getTimeSheetById(id));
        return result;
    }

    @Override
    public List<TimeSheet> getTimeSheetsByContractId(long contractId) {
        List<TimeSheet> result = new ArrayList<>();
        for (TimeSheetEntity t : ta.getTimeSheetsByContractId(contractId)) {
            result.add(createTimeSheetTO(t));
        }
        return result;
    }
    
    @Override
    public Project createProject(String name) {
        ProjectEntity p = pa.createProject(name);
        return createProjectTO(p);
    }
    
    //@RolesAllowed("ACCTMGR")
    private Project createProjectTO(ProjectEntity p) {
        if (p == null) {
            return null;
        }
        return new Project(p.getId(), p.getName(), p.getContracts(), 
                p.getEntries(), p.getOwners());
    }
    
    @Override
    public Project getProjectById(long id) {
        ProjectEntity p = pa.getProjectById(id);
        return createProjectTO(p);
    }
    
    @Override
    public List<Project> getProjects(){
        List<Project> result = new ArrayList<>();
        for (ProjectEntity p : pa.getProjects()) {
            result.add(createProjectTO(p));
        }
        return result;
    }

    @Override
    public TimeSheetEntry createTimeSheetEntry(TimeSheetEntity timesheet, 
            String descriptionOfWork, String comment, Date date, Integer hours) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TimeSheetEntry getTimeSheetEntryById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TimeSheetEntry> getTimeSheetEntriesByContractId(long contractId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteTimeSheetById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteTimeSheetsByContractId(long contractId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}