package com.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.dto.EstudianteDTO;
import com.dto.PersonaDTO;
import com.exception.PersistenciaException;
import com.service.EstudianteService;
import com.service.PersonaService;
import com.utils.ExceptionsTools;

import org.primefaces.PrimeFaces;

@Named(value="tablaUsuariosInactivoController")		//JEE8
@RequestScoped
public class TablaUsuariosInactivoController implements Serializable {
	
	private Collection<Object> usuarios =  new ArrayList<>();
	private List<PersonaDTO> personas =  new ArrayList<>();
	private List<EstudianteDTO> estudiantes =  new ArrayList<>();
	private String errorMessage;
	
	
	@Inject
    private PersonaService personaService;
	
	@Inject
    private EstudianteService estudianteService;
	
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void obtenerPersonasInactivas() {
		try {
			personas = personaService.obtenerPersonasInactivas();
			estudiantes = estudianteService.obtenerEstudiantesInactivos();
			usuarios.addAll(personas);
			usuarios.addAll(estudiantes);
			
		     for (Object item : usuarios) {
		            if (item instanceof PersonaDTO) {
		                PersonaDTO persona = (PersonaDTO) item;
		            } else if (item instanceof EstudianteDTO) {
		                EstudianteDTO estudiante = (EstudianteDTO) item;
		            }
		     }
			
		} catch (PersistenciaException e) {
       	 Throwable rootException = ExceptionsTools.getCause(e);
 	    String msg1 = e.getMessage();
 	    String msg2 = ExceptionsTools.formatedMsg(rootException);
 	    
 	    errorMessage = msg1 + " " + msg2;
 	 
 	    PrimeFaces.current().executeScript("PF('dialogoFallo').show();");
 	    
 	    e.printStackTrace();
		}
		
	}

	public Collection<Object> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Object> usuarios) {
		this.usuarios = usuarios;
	}

	public List<PersonaDTO> getPersonas() {
		return personas;
	}

	public void setPersonas(List<PersonaDTO> personas) {
		this.personas = personas;
	}

	public List<EstudianteDTO> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<EstudianteDTO> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void activarUsuario(Object usuario) throws IOException  {
		try {
			 FacesContext facesContext = FacesContext.getCurrentInstance();
			 ExternalContext externalContext = facesContext.getExternalContext();
		      if (usuario instanceof EstudianteDTO) {
		            EstudianteDTO estudiante = (EstudianteDTO) usuario;
		            estudianteService.activarEstudiante(estudiante.getId());  
		            externalContext.redirect(externalContext.getRequestContextPath() + "/tablaUsuariosInactivos.jsf");
		        } else if (usuario instanceof PersonaDTO) {
		            PersonaDTO persona = (PersonaDTO) usuario;	  
		            personaService.activarPersona(persona.getId());	 
		            externalContext.redirect(externalContext.getRequestContextPath() + "/tablaUsuariosInactivos.jsf");
		        }
					
		}catch (Exception e) {
			
			Throwable rootException = ExceptionsTools.getCause(e);
	        String msg1 = e.getMessage();
	        String msg2 = ExceptionsTools.formatedMsg(rootException);

	        errorMessage = msg1 + " " + msg2;

	        PrimeFaces.current().executeScript("PF('dialogoFallo').show();");

	        e.printStackTrace();
						      
	    } 
	 }
		
	
}
