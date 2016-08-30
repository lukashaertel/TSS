/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dto.Project;

@RequestScoped
@Named
public class ProjectListBean implements Serializable {

    private static final long serialVersionUID = -7412985664169675528L;

    @EJB
    private TssLogic tssLogic;
    
    private List<Project> projects;

    public List<Project> getProjects() {
        this.projects = tssLogic.getProjects();
        return projects;
    }
    
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public String createProject() {
        if (projectName != null && projectName != "")
        {
            Project p = tssLogic.createProject(projectName);
            return "project-details.xhtml?faces-redirect=true&projectId=" 
                    + p.getId();
        }
        else
            return "";
    }
}
