package com.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.dto.PersonaDTO;
import com.exception.PersistenciaException;
import com.service.PersonaService;
import com.utils.ExceptionsTools;

import org.primefaces.PrimeFaces;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;//JEE8
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;



@Named(value="personaController")		//JEE8
@SessionScoped					        
public class AltaPersonaController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private PersonaService personaService;
	
	private PersonaDTO personaDTO = new PersonaDTO();
	private String errorMessage;
	
	
	@PostConstruct
	public void init() {
		personaDTO=new PersonaDTO();
	}

	public PersonaDTO getPersonaDTO() {
		return personaDTO;
	}

	public void setPersonaDTO(PersonaDTO personaDTO) {
		this.personaDTO = personaDTO;
	}
	
	
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void guardarPersona() {
		PersonaDTO nuevaPersonaDTO;
        try {
        
        	if (personaDTO.getId() == null) {
                personaDTO.setId(null); 
            } 
        	personaDTO.setEstado("inactivo");
            nuevaPersonaDTO = personaService.agregarPersona(personaDTO);
          
            personaDTO = new PersonaDTO();
            
            if (nuevaPersonaDTO != null) {
                PrimeFaces.current().executeScript("PF('dialogoAltaPersona').show();");
       
            }
            
      
        } catch (PersistenciaException e) {
        	 Throwable rootException = ExceptionsTools.getCause(e);
        	    String msg1 = e.getMessage();
        	    String msg2 = ExceptionsTools.formatedMsg(rootException);
        	    
        	    errorMessage = msg1 + " " + msg2;
        	 
        	    PrimeFaces.current().executeScript("PF('dialogoFallo').show();");
        	    
        	    e.printStackTrace();
        }
        finally {
			
		}
		

    }
	
	

}
