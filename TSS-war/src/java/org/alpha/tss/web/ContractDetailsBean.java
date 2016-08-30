/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.web;

import static com.sun.faces.context.flash.ELFlash.getFlash;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dto.Contract;
import org.alpha.tss.logic.dto.TimeSheet;

@ManagedBean(name="contractDetailsBean")
@ViewScoped
public class ContractDetailsBean implements Serializable {
    
    private static final long serialVersionUID = 6865684774561400428L;
    
    @EJB
    private TssLogic tssLogic;

    private long id;
    private Contract contract;
    private List<TimeSheet> timeSheets;
    private boolean editmode;

    public void edit() {
        editmode = true;
    }

    public void save() {
        this.contract = tssLogic.updateContract(this.contract);
        editmode = false;
    }

    public boolean isEditmode() {
        return editmode;
    }    
    
    public boolean isEditContractAllowed() {
        if (this.contract.getContractStatus() == ContractStatus.PREPARED)
            return true;
        else
            return false;
    }
    
    public void startContract() {
        this.contract = tssLogic.setContractStatus(this.id, ContractStatus.STARTED);
    }
    
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
}
