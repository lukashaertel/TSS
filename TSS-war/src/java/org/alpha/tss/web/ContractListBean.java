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
import org.alpha.tss.logic.dto.Contract;

@RequestScoped
@Named
public class ContractListBean implements Serializable {

    private static final long serialVersionUID = 8862754931724334631L;
    
    @EJB
    private TssLogic tssLogic;

    private List<Contract> contracts;
    
    public List<Contract> getContracts() {
        //{
            contracts = tssLogic.getContracts();
        //}
        return contracts;
    }
}
