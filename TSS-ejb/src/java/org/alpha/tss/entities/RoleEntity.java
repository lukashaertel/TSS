/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Dr. Volker Riediger <riediger@uni-koblenz.de>
 */
package org.alpha.tss.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "PERSONROLE")
public abstract class RoleEntity extends AbstractEntity {

    @ManyToOne(optional = false)
    private PersonEntity person;

    public RoleEntity() {
    }

    protected RoleEntity(PersonEntity p) {
        this.person = p;
    }

    public PersonEntity getPerson() {
        return person;
    }
}