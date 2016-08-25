/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dao;

import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.alpha.tss.entities.ContractEntity;
import org.alpha.tss.entities.TimeSheetEntity;
import org.alpha.tss.entities.TimeSheetStatus;

@Stateless
public class TimeSheetAccess {
    
    @PersistenceContext(unitName = "TSS-PU")
    private EntityManager em;
    
    public TimeSheetEntity createTimeSheet(ContractEntity contract, 
            TimeSheetStatus status, Date start, Date end, Integer hoursDue) {
        
        TimeSheetEntity t = new TimeSheetEntity(contract, status, start, end,
            hoursDue);
        t.createId();
        em.persist(t);
        return t;
    }
    
    public List<TimeSheetEntity> getTimeSheets() {
    List<TimeSheetEntity> l = em.createNamedQuery("TimeSheetEntity.getAllTimeSheets", TimeSheetEntity.class)
            .getResultList();
    return l;
    }
    
    public List<TimeSheetEntity> getTimeSheetsFiltered(String filter) {
        return em.createNamedQuery(
                "TimeSheetEntity.getTimeSheetsFiltered",
                TimeSheetEntity.class)
                .setParameter("filter", filter.trim().toLowerCase() + "%")
                .getResultList();
    }
    
    public TimeSheetEntity getTimeSheetById(long id) {
        return em.createNamedQuery(
                "TimeSheetEntity.getTimeSheetById",
                TimeSheetEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public List<TimeSheetEntity> getTimeSheetsByContractId(long id) {
        return em.createNamedQuery(
                "TimeSheetEntity.getTimeSheetsByContractId",
                TimeSheetEntity.class)
                .setParameter("contractId", id)
                .getResultList();
    }
}