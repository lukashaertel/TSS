/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SECRETARY")
public class SecretaryEntity extends RoleEntity {
    
    @ManyToOne(optional = false)
    private ContractEntity contract;

    public SecretaryEntity() {
    }
    
    public SecretaryEntity(PersonEntity person) {
        super(person);
    }
}
