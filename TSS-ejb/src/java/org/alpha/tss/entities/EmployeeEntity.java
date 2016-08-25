/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity extends RoleEntity {
    
    @OneToOne(optional = false)
    private ContractEntity contract;

    public ContractEntity getContract() {
        return contract;
    }
    
    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }
    
    public EmployeeEntity() {
    }
    
    public EmployeeEntity(PersonEntity person) {
        super(person);
    }
}