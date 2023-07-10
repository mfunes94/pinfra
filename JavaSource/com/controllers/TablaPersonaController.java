package com.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.dto.PersonaDTO;
import com.exception.PersistenciaException;
import com.service.PersonaService;
import com.utils.ExceptionsTools;

import org.primefaces.PrimeFaces;

@Named(value="tablaPersonaController")		//JEE8
@RequestScoped
public class TablaPersonaController implements Serializable {
	
	private List<PersonaDTO> personas;
	private String errorMessage;
	
	private static final long serialVersionUID = 1L;

	@Inject
    private PersonaService personaService;

	public List<PersonaDTO> getPersonas() {
		return personas;
	}

	public void setPersonas(List<PersonaDTO> personas) {
		this.personas = personas;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	} 
	
	@PostConstruct
	public void obtenerPersonasActivas() {
		try {
			personas = personaService.obtenerPersonasActivas();
		} catch (PersistenciaException e) {
       	 Throwable rootException = ExceptionsTools.getCause(e);
 	    String msg1 = e.getMessage();
 	    String msg2 = ExceptionsTools.formatedMsg(rootException);
 	    
 	    errorMessage = msg1 + " " + msg2;
 	 
 	    PrimeFaces.current().executeScript("PF('dialogoFallo').show();");
 	    
 	    e.printStackTrace();
		}
		
	}
	

	
	
}
