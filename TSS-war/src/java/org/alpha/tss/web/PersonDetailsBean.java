/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dto.Person;
import org.alpha.tss.logic.dto.Project;
import org.alpha.tss.logic.dto.ProjectEntry;

@ViewScoped
@Named
public class PersonDetailsBean implements Serializable {

    private static final long serialVersionUID = 318991485429781552L;

    @EJB
    private TssLogic tssLogic;

    private long id;

    private Person person;

    public Person getPerson() {
        return person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        this.person = tssLogic.getPersonById(id);
    }
}
