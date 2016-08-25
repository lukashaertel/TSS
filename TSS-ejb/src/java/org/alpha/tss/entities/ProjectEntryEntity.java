/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alpha.tss.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECTENTRY")
public class ProjectEntryEntity extends AbstractEntity {
    
    private static final long serialVersionUID = -8146650030880831558L;
    
    private String name;
    private String description;
    private Integer hoursDue;
    @ManyToOne
    private ProjectEntity project;
    
    public ProjectEntryEntity() {
    }
    
    public ProjectEntryEntity(String name, String description, Integer hoursDue, ProjectEntity project) {
        this.name = name;
        this.description = description;
        this.hoursDue = hoursDue;
        this.project = project;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHoursDue() {
        return hoursDue;
    }

    public void setHoursDue(Integer hoursDue) {
        this.hoursDue = hoursDue;
    }
}
