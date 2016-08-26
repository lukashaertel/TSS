/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Lukas Härtel <lukashaertel@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.Serializable;
import java.sql.Date;
import java.util.Currency;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.entities.ContractType;
import org.alpha.tss.entities.TimeSheetFrequency;
import org.alpha.tss.logic.TssLogic;
import org.alpha.tss.logic.dto.Contract;
import org.alpha.tss.logic.dto.Project;
import org.alpha.tss.logic.dto.ProjectEntry;

@RequestScoped
@Named
public class SampleFillBean implements Serializable {

    private static final long serialVersionUID = 318991485487412552L;

    @EJB
    private TssLogic tssLogic;

    public String sampleContract() {

        Contract contract = tssLogic.createContract(
                ContractType.FIXED_HOURS,
                ContractStatus.PREPARED,
                "Example Contract Fixed Hours",
                "An example contract with fixed hours as mode",
                "Created by sample fill",
                TimeSheetFrequency.WEEKLY,
                40,
                2000, 240,
                Currency.getInstance("EUR"),
                Date.valueOf("2017-10-10"),
                Date.valueOf("2018-10-10"),
                Date.valueOf("2018-10-10"),
                6,
                1);

        return "contract-details.xhtml?faces-redirect=true&contractId=" + contract.getId();
    }
}
