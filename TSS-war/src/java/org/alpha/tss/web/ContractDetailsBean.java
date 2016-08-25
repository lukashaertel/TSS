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
import org.alpha.tss.logic.dto.TimeSheet;

@RequestScoped
@Named
public class ContractDetailsBean implements Serializable {
    
    private static final long serialVersionUID = 6865684774561400428L;
    
    @EJB
    private TssLogic tssLogic;

    private long id;
    private Contract contract;
    private List<TimeSheet> timeSheets;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        this.contract = tssLogic.getContractById(id);
        this.timeSheets = tssLogic.getTimeSheetsByContractId(id);
    }
    
    public Contract getContract() {
        return contract;
    }
    
    public List<TimeSheet> getTimeSheets() {
        return timeSheets;
    }
    
    public void test() {
        return;
    }
}
