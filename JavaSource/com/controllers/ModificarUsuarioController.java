package com.controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.dto.EstudianteDTO;
import com.dto.PersonaDTO;
import com.service.EstudianteService;
import com.service.PersonaService;
import com.utils.ExceptionsTools;

import org.primefaces.PrimeFaces;

@Named(value="modificarUsuarioController")	
@RequestScoped
public class ModificarUsuarioController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private PersonaService personaService;
	@Inject
    private EstudianteService estudianteService;
	private Long id;
	private PersonaDTO personaDTO;
	private EstudianteDTO estudianteDTO;
	private String errorMessage;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public EstudianteDTO getEstudianteDTO() {
		return estudianteDTO;
	}
	public void setEstudianteDTO(EstudianteDTO estudianteDTO) {
		this.estudianteDTO = estudianteDTO;
	}
	@PostConstruct
	public void init() {
	   FacesContext facesContext = FacesContext.getCurrentInstance();
	   HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

	       
	   id = (Long) session.getAttribute("id");
	        
	   boolean respuesta = personaService.obtenerPersonaIdBoolean(id);
	   if (respuesta == false) {
		   respuesta = estudianteService.obtenerEstudianteIdBoolean(id);
		   if (respuesta) {
			 estudianteDTO = estudianteService.buscarEstudianteByID(id);
		   }  
	   }else if (respuesta) {
		   personaDTO = personaService.buscarPersonaByID(id);
	   }
 
	}
	
	public void irAModificarDatos() throws IOException {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		 id = (Long) session.getAttribute("id");
		 
		 boolean respuesta = personaService.obtenerPersonaIdBoolean(id);
		   if (respuesta == false) {
			   respuesta = estudianteService.obtenerEstudianteIdBoolean(id);
			   if (respuesta) {
				   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		       	   externalContext.redirect("modificarEstudiante.jsf");
			   }  
		   }else if (respuesta) {
			   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	       	   externalContext.redirect("modificarPersona.jsf");
		   }
		     
		 
	 }
	
	
	public void modificarPersona() {
	    try {
	       personaService.actualizarPersona(personaDTO); 
	       
	       ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	       externalContext.redirect("tablaPersona.jsf");
	       
	    } catch (Exception e) {
	        Throwable rootException = ExceptionsTools.getCause(e);
	        String msg1 = e.getMessage();
	        String msg2 = ExceptionsTools.formatedMsg(rootException);

	        errorMessage = msg1 + " " + msg2;

	        PrimeFaces.current().executeScript("PF('dialogoFallo').show();");

	        e.printStackTrace();
	    }
	}
	
	public void modificarEstudiante() {
	    try {
	       estudianteService.actualizarEstudiante(estudianteDTO);
	       ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	       externalContext.redirect("tablaEstudiante.jsf");
	       
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
