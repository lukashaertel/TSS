/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUPERVISOR")
public class SupervisorEntity extends RoleEntity {
    
    @OneToOne(optional = false)
    private ContractEntity contract;

    public SupervisorEntity() {
    }
    
    public SupervisorEntity(PersonEntity person) {
        super(person);
    }
}
