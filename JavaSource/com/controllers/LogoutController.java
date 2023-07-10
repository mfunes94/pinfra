package com.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import com.utils.ExceptionsTools;

@Named(value="logoutController")		
@SessionScoped
public class LogoutController  implements Serializable {
	
	private String errorMessage;
	
	private static final long serialVersionUID = 1L;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void abrirDialogLogout() {
		PrimeFaces.current().executeScript("PF('dialogoLogout').show();");
	}
	
	public void logout() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
	        session.removeAttribute("token");
	        ExternalContext externalContext = facesContext.getExternalContext();
	        externalContext.redirect(externalContext.getRequestContextPath() + "/login.jsf");
	         
		} catch (Exception e) {
			Throwable rootException = ExceptionsTools.getCause(e);
    	    String msg1 = e.getMessage();
    	    String msg2 = ExceptionsTools.formatedMsg(rootException);
    	    
    	    errorMessage = msg1 + " " + msg2;
    	 
    	    PrimeFaces.current().executeScript("PF('dialogoFalloLogout').show();");
    	    
    	    e.printStackTrace();
		}
      
        
	}
	
	
	
	

}
