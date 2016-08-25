/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alpha.tss.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(
            name = "ProjectEntity.getAllProjects",
            query = "SELECT p FROM ProjectEntity p"
            + " ORDER BY p.id"),
    @NamedQuery(
            name = "ProjectEntity.getProjectById",
            query = "SELECT p FROM ProjectEntity p"
            + " WHERE p.id = :id"),
    @NamedQuery(
            name = "ProjectEntity.getProjectsFiltered",
            query = "SELECT p FROM ProjectEntity p")

})

@Entity
@Table(name = "PROJECT")
public class ProjectEntity extends AbstractEntity {
    
    private static final long serialVersionUID = -6157326038820562589L;
    
    @Column(nullable = false)
    private String name;
    @ManyToMany
    private Set<ContractEntity> contracts;
    @OneToMany(mappedBy = "project")
    private Set<ProjectEntryEntity> entries;
    @OneToMany(mappedBy = "project")
    private Set<AssistantEntity> owners;
    
    public ProjectEntity() {
    }

    public ProjectEntity(String name, Set<ContractEntity> contracts, Set<ProjectEntryEntity> entries, Set<AssistantEntity> owners) {
        this.name = name;
        this.contracts = contracts;
        this.entries = entries;
        this.owners = owners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ContractEntity> getContracts() {
        return contracts;
    }

    public void setContracts(Set<ContractEntity> contracts) {
        this.contracts = contracts;
    }

    public Set<ProjectEntryEntity> getEntries() {
        return entries;
    }

    public void setEntries(Set<ProjectEntryEntity> entries) {
        this.entries = entries;
    }

    public Set<AssistantEntity> getOwners() {
        return owners;
    }

    public void setOwners(Set<AssistantEntity> owners) {
        this.owners = owners;
    }
}
