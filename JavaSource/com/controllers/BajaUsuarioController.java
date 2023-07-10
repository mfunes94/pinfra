package com.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import com.service.EstudianteService;
import com.service.PersonaService;
import com.utils.ExceptionsTools;

import org.primefaces.PrimeFaces;

@Named(value="bajaUsuarioController")		//JEE8
@SessionScoped	
public class BajaUsuarioController implements Serializable {
	
	private String errorMessage;
	
	
	@Inject
    private PersonaService personaService;
	
	@Inject
    private EstudianteService estudianteService;
	
	private static final long serialVersionUID = 1L;
		
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void abrirDialogBaja() {
		PrimeFaces.current().executeScript("PF('dialogoConfirmacion').show();");
	}

	public void darBaja() {
	    try {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

	        String token = (String) session.getAttribute("token");
	        Long id = (Long) session.getAttribute("id");
	        boolean respuesta;

	        if (token != null && !token.isEmpty()) {
	                respuesta = personaService.obtenerPersonaIdBoolean(id);
	                if (respuesta == false) {
	                    respuesta = estudianteService.obtenerEstudianteIdBoolean(id);
	                    estudianteService.eliminarEstudiante(id);
	                    session.removeAttribute("token");
	                    ExternalContext externalContext = facesContext.getExternalContext();
	                    externalContext.redirect(externalContext.getRequestContextPath() + "/login.jsf");
	                    
	                } else if (respuesta) {
	                    personaService.eliminarPersona(id);
	                    session.removeAttribute("token");
	                    ExternalContext externalContext = facesContext.getExternalContext();
	                    externalContext.redirect(externalContext.getRequestContextPath() + "/login.jsf");
	                }
	           
	        }
	    } catch (Exception e) {
	        Throwable rootException = ExceptionsTools.getCause(e);
	        String msg1 = e.getMessage();
	        String msg2 = ExceptionsTools.formatedMsg(rootException);

	        errorMessage = msg1 + " " + msg2;

	        PrimeFaces.current().executeScript("PF('dialogoFallo').show();");

	        e.printStackTrace();
	    }
	}
	


	

}
