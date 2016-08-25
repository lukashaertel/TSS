/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dao;

import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.alpha.tss.entities.AssistantEntity;
import org.alpha.tss.entities.ContractEntity;
import org.alpha.tss.entities.ProjectEntity;
import org.alpha.tss.entities.ProjectEntryEntity;

@Stateless
public class ProjectAccess {
    
    @PersistenceContext(unitName = "TSS-PU")
    private EntityManager em;
    
    public ProjectEntity createProject(String name, 
            Set<ContractEntity> contracts, Set<ProjectEntryEntity> entries, 
            Set<AssistantEntity> owners) {
        
        ProjectEntity t = new ProjectEntity(name, contracts, entries, owners);
        t.createId();
        em.persist(t);
        return t;
    }
    
    public List<ProjectEntity> getProjects() {
    List<ProjectEntity> l = em.createNamedQuery("ProjectEntity.getAllProjects", ProjectEntity.class)
            .getResultList();
    return l;
    }
    
    public List<ProjectEntity> getProjectsFiltered(String filter) {
        return em.createNamedQuery(
                "ProjectEntity.getProjectsFiltered",
                ProjectEntity.class)
                .setParameter("filter", filter.trim().toLowerCase() + "%")
                .getResultList();
    }
    
    public ProjectEntity getProjectById(long id) {
        return em.createNamedQuery(
                "ProjectEntity.getProjectById",
                ProjectEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public List<ProjectEntity> getProjectsByContractId(long id) {
        return em.createNamedQuery(
                "ProjectEntity.getProjectsByContractId",
                ProjectEntity.class)
                .setParameter("contractId", id)
                .getResultList();
    }
}