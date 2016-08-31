/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

// To convert LocalDate to string and vice versa because
// LocalDate does not exist in JSF 2.2 standard converter
@FacesConverter(value="localDateTimeConverter")
public class LocalDateTimeConverter implements javax.faces.convert.Converter {
    
    String dateFormat = "";
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (this.dateFormat.isEmpty())
            // DateFormat from Resource Bundle
            this.dateFormat = FacesContext.getCurrentInstance().getApplication().
                evaluateExpressionGet(context, "#{msg}", ResourceBundle.class).
                getString("dateformat");
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(dateFormat));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (this.dateFormat.isEmpty())
            // DateFormat from Resource Bundle
            this.dateFormat = FacesContext.getCurrentInstance().getApplication().
                evaluateExpressionGet(context, "#{msg}", ResourceBundle.class).
                getString("dateformat");
        LocalDate dateValue = (LocalDate) value;                
        return dateValue.format(DateTimeFormatter.ofPattern(dateFormat));
    }
}