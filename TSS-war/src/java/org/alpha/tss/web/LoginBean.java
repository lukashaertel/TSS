/*
 * Java EE Web Applications / Summer Term 2016
 * (C) Robin Brehmert <rbrehmert@uni-koblenz.de>
 */
package org.alpha.tss.web;

import java.io.IOException;
import java.security.Principal;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Stateless
@Named
public class LoginBean {
  private String username;
  private String password;

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  //ldap
  /*
    // Set up the environment for creating the initial context
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, 
        "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldaps://ldap.uni-koblenz.de:636");

    // Authenticate as S. User and password "mysecret"
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, "uid=" + username + ",ou=people,ou=koblenz,dc=Uni-Koblenz-landau,dc=de");
    env.put(Context.SECURITY_CREDENTIALS, password);

    try {
        // Create the initial context
        DirContext context = new InitialDirContext(env);
    } catch (NamingException ex) {
        Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  */
 
  public void login () {
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) 
        context.getExternalContext().getRequest();
    try {        
        if (request.getUserPrincipal() == null)
            request.login(this.username, this.password);
        context.getExternalContext().redirect("fe2/contracts.xhtml");
    } catch (ServletException e) {
      context.addMessage(null, new FacesMessage(e.getLocalizedMessage()));
      return;
    }
    catch (IOException e) {
        context.addMessage(null, new FacesMessage(e.getLocalizedMessage()));
    }
    }

  public void logout() throws IOException {      
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) 
        context.getExternalContext().getRequest();
    try {
      request.logout();
      context.getExternalContext().redirect("../login.xhtml");
    } catch (ServletException e) {
      context.addMessage(null, new FacesMessage("Logout failed."));
    }
    catch (IOException e) {
        context.addMessage(null, new FacesMessage(e.getLocalizedMessage()));
    }
  }
}
