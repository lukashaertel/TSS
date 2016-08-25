/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.alpha.tss.entities.TimeSheetEntryEntity;

@Stateless
public class TimeSheetEntryAccess {
    
    @PersistenceContext(unitName = "TSS-PU")
    private EntityManager em;
    
    public List<TimeSheetEntryEntity> getTimeSheetEntries() {
    List<TimeSheetEntryEntity> l = em.createNamedQuery("TimeSheetEntryEntity.getAllTimeSheetEntries", TimeSheetEntryEntity.class)
            .getResultList();
    return l;
    }
}