/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {

    public static final long serialVersionUID = 1L;

    private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        setLocale(new Locale(language));
    }

    public String getLanguageName() {
        return locale.getDisplayName(locale);
    }

    public boolean isLanguage(String language) {
        return getLanguage().equals(language);
    }

    public List<Locale> getLocales() {
        List<Locale> result = new ArrayList<>();

        for (Iterator<Locale> li = FacesContext.getCurrentInstance().getApplication().getSupportedLocales(); li.hasNext();) {
            result.add(li.next());
        }

        return result;
    }

    public boolean isLocale(Locale locale) {
        return this.locale.equals(locale);
    }
}
