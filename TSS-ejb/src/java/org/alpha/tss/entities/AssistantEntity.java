/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.alpha.tss.logic.dto.Contract;

@Entity
@Table(name = "ASSISTANT")
public class AssistantEntity extends RoleEntity {
    
    @ManyToOne(optional = false)
    private ContractEntity contract;
    @ManyToOne
    private ProjectEntity project;
    
    public AssistantEntity() {
    }
    
    public AssistantEntity(PersonEntity person) {
        super(person);
    }
}
