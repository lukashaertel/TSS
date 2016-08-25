/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dao;

import java.sql.Date;
import java.util.Currency;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
            ContractStatus contractStatus, String name, String description,
            String comment, TimeSheetFrequency frequency, Integer hoursPerWeek,
            Integer totalHoursDue, Integer vacationHours, Currency salary,
            Date start, Date end, Date abort, Integer workingDaysPerWeek,
            Integer vacationDaysPerYear) {
        
        ContractEntity c = new ContractEntity(contractType,
            contractStatus, name, description, comment, frequency, hoursPerWeek,
            totalHoursDue, vacationHours, salary, start, end, abort, 
            workingDaysPerWeek, vacationDaysPerYear);
        c.createId();
        em.persist(c);
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
}
