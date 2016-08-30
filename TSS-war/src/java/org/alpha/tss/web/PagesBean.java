/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import net.bootsfaces.component.navLink.NavLink;

@ManagedBean
@SessionScoped
public class PagesBean implements Serializable {

    public boolean isActive(NavLink navLink) {
        // Get the actual request
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        // Check if requested resource points to the desired HREF
        return req.getRequestURI().endsWith(navLink.getHref());
    }
}