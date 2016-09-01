/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Dr. Volker Riediger <riediger@uni-koblenz.de>
 */
package org.alpha.tss.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;

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
                    name = "PersonEntity.getPersonById",
                    query = "SELECT p FROM PersonEntity p"
                    + " WHERE p.id = :id"),
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

    @Email
    @Column(length = 100)
    private String email;

    private String title;

    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "person")
    private Set<RoleEntity> roles;

    public PersonEntity() {
    }

    public PersonEntity(String firstname, String lastname, String email, String title, LocalDate dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.title = title;
        this.dateOfBirth = dateOfBirth;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
