/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dao;

import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.alpha.tss.entities.TimeSheetEntity;
import org.alpha.tss.entities.TimeSheetEntryEntity;

@Stateless
public class TimeSheetEntryAccess {
    
    @PersistenceContext(unitName = "TSS-PU")
    private EntityManager em;
    
    public TimeSheetEntryEntity createTimeSheetEntry(TimeSheetEntity timeSheet,
            String descriptionOfWork, String comment, LocalDate date, int hours) {
        TimeSheetEntryEntity te = new TimeSheetEntryEntity(timeSheet,
            descriptionOfWork, comment, date, hours);
        em.persist(te);
        em.flush();
        return te;
    }
    
    public List<TimeSheetEntryEntity> getTimeSheetEntries() {
    List<TimeSheetEntryEntity> l = em.createNamedQuery("TimeSheetEntryEntity.getAllTimeSheetEntries", TimeSheetEntryEntity.class)
            .getResultList();
    return l;
    }
    
    public List<TimeSheetEntryEntity> getTimeSheetEntriesByTimeSheetId(long timeSheetId) {
        List<TimeSheetEntryEntity> t = em.createNamedQuery(
            "TimeSheetEntryEntity.getTimeSheetEntriesByTimeSheetId",
            TimeSheetEntryEntity.class)
            .setParameter("timeSheetId", timeSheetId)
            .getResultList();
        return t;
    }
}