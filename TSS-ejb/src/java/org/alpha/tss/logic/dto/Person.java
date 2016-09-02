/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dto;

import java.time.LocalDate;
import java.util.Locale;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person extends AbstractTransferObject {

    private static final long serialVersionUID = 1L;

    private String firstname;

    private String lastname;

    private String email;

    private String title;

    private LocalDate dateOfBirth;
    
    private Locale preferredLocale;
    
    private boolean groupReminders;

    public Person() {
    }

    public Person(long id, String firstname, String lastname, String email, String title, LocalDate dateOfBirth, Locale preferredLocale,boolean groupReminders) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.title = title;
        this.dateOfBirth = dateOfBirth;
        this.preferredLocale = preferredLocale;
        this.groupReminders =groupReminders;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Locale getPreferredLocale() {
        return preferredLocale;
    }

    public void setPreferredLocale(Locale preferredLocale) {
        this.preferredLocale = preferredLocale;
    }

    public boolean isGroupReminders() {
        return groupReminders;
    }

    public void setGroupReminders(boolean groupReminders) {
        this.groupReminders = groupReminders;
    }
    
    
    
}
