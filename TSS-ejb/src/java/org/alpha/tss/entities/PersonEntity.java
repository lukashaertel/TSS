/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Dr. Volker Riediger <riediger@uni-koblenz.de>
 */
package org.alpha.tss.entities;

import java.sql.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Dr. Volker Riediger <riediger@uni-koblenz.de>
 */
@Entity
@Table(name = "PERSON")
@NamedQueries(
        {
            @NamedQuery(
                    name = "PersonEntity.getPersonsFiltered",
                    query = "SELECT p FROM PersonEntity p"
                    + " WHERE LOWER(p.lastname) like :filter"
                    + " OR LOWER(p.firstname) like :filter"
                    + " ORDER BY p.lastname, p.firstname"),
            @NamedQuery(
                    name = "PersonEntity.getAllPersons",
                    query = "SELECT p FROM PersonEntity p"
                    + " ORDER BY p.lastname, p.firstname")
        })
public class PersonEntity extends AbstractEntity {

    @Column(length = 100)
    private String firstname;

    @Column(length = 100)
    private String lastname;
    
    private String title;
    
    private Date dateOfBirth;

    @OneToMany(mappedBy = "person")
    private Set<RoleEntity> roles;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
