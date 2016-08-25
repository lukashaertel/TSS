/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dto;

import javax.xml.bind.annotation.XmlRootElement;
import org.alpha.tss.entities.ProjectEntity;

@XmlRootElement
public class ProjectEntry extends AbstractTransferObject { 

    private static final long serialVersionUID = -5585822541641306586L;

    private String name;
    private String description;
    private Integer hoursDue;

    public ProjectEntry() {
    }

    public ProjectEntry(String name, String description, Integer hoursDue, long id) {
        super(id);
        this.name = name;
        this.description = description;
        this.hoursDue = hoursDue;
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