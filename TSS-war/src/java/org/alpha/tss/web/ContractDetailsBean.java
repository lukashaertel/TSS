/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dto.Contract;
import org.alpha.tss.logic.dto.TimeSheet;

@Named
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
        return this.contract.getContractStatus() == ContractStatus.PREPARED;
    }
    
    public boolean isAbortContractAllowed() {
        return tssLogic.isAbortContractAllowed(this.id);
    }
    
    public boolean isHasTimeSheets() {
        return !this.timeSheets.isEmpty();
    }
    
    public void startContract() {
        tssLogic.startContract(this.id);
        this.refreshContract(this.id);
    }
    
    public void abortContract() {
        try {
            // TO DO: 
            // Warn user if TimeSheets with status IN PROGRESS have entries
            if (tssLogic.abortContract(this.id) == false)
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage("Contract cannot be aborted"));
            else
                this.refreshContract(this.id);
        }
        catch (EJBAccessException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Invalid access rights"));
        }
    }
    
    public void deleteContract() {     
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            tssLogic.deleteContract(this.id);
            context.getExternalContext().redirect("contracts.xhtml");
        }
        catch(IOException e) {   
            context.addMessage(null, new FacesMessage(e.getLocalizedMessage()));
        }        
        catch(EJBAccessException e) {   
            context.addMessage(null, new FacesMessage("Invalid access rights"));
        }        
    }
    
    public void refreshContract(long id) {
        this.contract = tssLogic.getContractById(id);
        this.timeSheets = tssLogic.getTimeSheetsByContractId(id);
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        this.refreshContract(id);
    }
    
    public Contract getContract() {
        return contract;
    }
    
    public List<TimeSheet> getTimeSheets() {
        return timeSheets;
    }
}
