/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dao;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.alpha.tss.entities.ContractEntity;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.entities.ContractType;
import org.alpha.tss.entities.TimeSheetFrequency;

@Stateless
public class ContractAccess {
    
    @PersistenceContext(unitName = "TSS-PU")
    private EntityManager em;
    
    public ContractEntity createContract(ContractType contractType,
            String name, String description,
            String comment, TimeSheetFrequency frequency, Integer hoursPerWeek,
            Integer totalHoursDue, Integer vacationHours, Currency salary,
            LocalDate start, LocalDate end, LocalDate abort, Integer workingDaysPerWeek,
            Integer vacationDaysPerYear) {
        
        ContractEntity c = new ContractEntity(contractType,
            ContractStatus.PREPARED, name, description, comment, frequency, 
            hoursPerWeek, totalHoursDue, vacationHours, salary, start, end, abort, 
            workingDaysPerWeek, vacationDaysPerYear);
        em.persist(c); 
        em.flush();
        return c;
    }
    
    public ContractEntity getContract(long id) {
        try {
            return em.createNamedQuery("ContractEntity.getContractById", ContractEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<ContractEntity> getContracts() {
        List<ContractEntity> l = em.createNamedQuery("ContractEntity.getAllContracts", ContractEntity.class)
                .getResultList();
        return l;
    }

    public List<ContractEntity> getContractsFiltered(String filter) {
        return em.createNamedQuery(
                "ContractEntity.getContractsFiltered",
                ContractEntity.class)
                .setParameter("filter", filter.trim().toLowerCase() + "%")
                .getResultList();
    }
    
    public ContractEntity setContractStatus(long id, ContractStatus status) {
        ContractEntity c = em.find(ContractEntity.class, id);
        c.setStatus(status);
        return em.merge(c);
    }
    
    public ContractEntity updateContract(long id, LocalDate start, LocalDate end, 
            TimeSheetFrequency frequency, int hoursPerWeek, int totalHoursDue,
            int workingDaysPerWeek, int vacationDaysPerYear) {
        ContractEntity c = em.find(ContractEntity.class, id);
        c.setStart(start);
        c.setEnd(end);
        c.setFrequency(frequency);
        c.setHoursPerWeek(hoursPerWeek);
        c.setTotalHoursDue(totalHoursDue);
        c.setWorkingDaysPerWeek(workingDaysPerWeek);
        c.setVacationDaysPerYear(vacationDaysPerYear);
        em.merge(c);
        return em.find(ContractEntity.class, id);
    }
    
    public void abortContract(long id) {
        ContractEntity c = em.find(ContractEntity.class, id);
        c.setAbort(LocalDate.now());
        c.setStatus(ContractStatus.ABORTED);
    }
    
    public void deleteContract(long id) {
        ContractEntity c = em.find(ContractEntity.class, id);
        em.remove(c);
    }
}
