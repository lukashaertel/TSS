/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dto;

import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import org.alpha.tss.entities.ContractEntity;
import org.alpha.tss.entities.ProjectEntryEntity;
import org.alpha.tss.entities.AssistantEntity;

@XmlRootElement
public class Project extends AbstractTransferObject { 

    private static final long serialVersionUID = -8224232069134457589L;

    private String name;
    private Set<ContractEntity> contracts;
    private Set<ProjectEntryEntity> entries;
    private Set<AssistantEntity> owners;

    public Project() {
    }

    public Project(long id, String name, Set<ContractEntity> contracts, 
            Set<ProjectEntryEntity> entries, Set<AssistantEntity> owners) {
        super(id);
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