/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alpha.tss.logic;

import java.util.Date;
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

    @Schedule(hour = "14", minute = "35")
    public void collectAndSendReminders() throws MessagingException {
        Message message = new MimeMessage(mailSession);
        message.setSubject("Test from mail system");
        message.setSentDate(new Date());
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("lukashaertel@uni-koblenz.de", false));
        message.setText("It works!");
        Transport.send(message);
    }
}
