/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dto.Project;
import org.alpha.tss.logic.dto.ProjectEntry;

@RequestScoped
@Named
public class ProjectDetailsBean implements Serializable {

    private static final long serialVersionUID = 318991485429781552L;

    @EJB
    private TssLogic tssLogic;
    
    private long id;
    
    private Project project;
    private List<ProjectEntry> entries;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        project = null;
        for(Project p : tssLogic.getProjects())
            if(p.getId() == id){
                project = p;
                break;
        }
        
        // Not implemented in logic
        entries = Collections.emptyList();
    }

    public Project getProject() {
        return project;
    }

    public List<ProjectEntry> getEntries() {
        return entries;
    }
}
