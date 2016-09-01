/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alpha.tss.logic;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.alpha.tss.logic.dto.Person;
import org.alpha.tss.util.mail.MessageBuilder;

/**
 *
 * @author pazuzu
 */
@Stateless
@LocalBean
public class ReminderBean {
    @EJB
    private TssLogic logic;

    @Resource(lookup = "mail/uniko-mail")
    private Session mailSession;

    // TODO: Persistent might need to be true, requires keep-state setting to be true
    @Schedule(dayOfWeek = "7", hour = "17", persistent = false)
    public void endOfWeekReminders(){
        
    }
    
    @Schedule(dayOfMonth = "Last", hour = "17", persistent = false)
    public void endOfMonthReminders(){
        
    }
    
    private boolean groupRemindersFor(Person person){
        return false;
    }
    
    
    private void dispatchMessages() {
        try {
            
            MessageBuilder.on(mailSession)
                    .to("lukashaertel@uni-koblenz.de")
                    .subject("Test from the mail system")
                    .text("It works!")
                    .send();
        } catch (MessagingException ex) {
            Logger.getLogger(ReminderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
