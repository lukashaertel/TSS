/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.logic.dao;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.alpha.tss.entities.ContractEntity;
import org.alpha.tss.entities.ContractStatus;
import org.alpha.tss.entities.ContractType;
import org.alpha.tss.entities.PersonEntity;
import org.alpha.tss.entities.TimeSheetFrequency;

@Stateless
public class PersonAccess {

    @PersistenceContext(unitName = "TSS-PU")
    private EntityManager em;

    public PersonEntity createPerson(String firstname, String lastname, String email, String title, LocalDate dateOfBirth, Locale preferredLocale, boolean groupReminders) {
        PersonEntity p = new PersonEntity(firstname, lastname, email, title, dateOfBirth, preferredLocale, groupReminders);
        em.persist(p);
        em.flush();
        return p;
    }

    public PersonEntity getPersonById(long id) {
        try {
            return em.createNamedQuery("PersonEntity.getPersonById", PersonEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public PersonEntity getPersonByMail(String mail) {
        try {
            return em.createNamedQuery("PersonEntity.getPersonByMail", PersonEntity.class)
                    .setParameter("email", mail)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<PersonEntity> getPersons() {
        List<PersonEntity> l = em.createNamedQuery("PersonEntity.getAllPersons", PersonEntity.class)
                .getResultList();
        return l;
    }

    public List<PersonEntity> getPersonsFiltered(String filter) {
        return em.createNamedQuery(
                "PersonEntity.getPersonsFiltered",
                PersonEntity.class)
                .setParameter("filter", filter.trim().toLowerCase() + "%")
                .getResultList();
    }

    public PersonEntity updatePerson(long id, String firstname, String lastname, String email, String title, LocalDate dateOfBirth) {
        PersonEntity p = em.find(PersonEntity.class, id);
        p.setFirstname(firstname);
        p.setLastname(lastname);
        p.setEmail(email);
        p.setTitle(title);
        p.setDateOfBirth(dateOfBirth);
        em.merge(p);
        return em.find(PersonEntity.class, id);
    }

    public void deletePerson(long id) {
        PersonEntity p = em.find(PersonEntity.class, id);
        em.remove(p);
    }
}
