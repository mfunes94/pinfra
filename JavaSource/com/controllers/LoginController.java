package com.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.dto.PersonaDTO;
import com.exception.PersistenciaException;
import com.jwt.JwtUtil;
import com.service.EstudianteService;
import com.service.PersonaService;
import com.utils.ExceptionsTools;

import org.primefaces.PrimeFaces;

@Named(value="loginController")		//JEE8
@SessionScoped
public class LoginController implements Serializable {
	private String correo;
	private String contraseña;
	private String errorMessage;
	private PersonaDTO personaDTO;
	@Inject
    private PersonaService personaService;
	
	@Inject
    private EstudianteService estudianteService;
	
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void crearAdmin() throws PersistenciaException, IOException {
		
		boolean respuesta = personaService.hayPersonasActivas();
		
		if (!respuesta) {
			personaDTO = new PersonaDTO();
			personaDTO.setNombre("Admin");
			personaDTO.setApellido("Admin");
			personaDTO.setDireccion("Admin 1234");
			personaDTO.setFechaNacimiento( new Date());
			personaDTO.setCorreo("admin@estudiantes.utec.edu.uy");
			personaDTO.setContraseña("admin1234");
			personaDTO.setEstado("activo");
			
	        PersonaDTO nuevaPersonaDTO = personaService.agregarPersona(personaDTO);
		}
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	externalContext.redirect("login.jsf");
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



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getContraseña() {
		return contraseña;
	}



	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}



	public void login() throws PersistenciaException {
	    try {
	        boolean respuesta = personaService.login(correo, contraseña);
	        
	        if (respuesta==false) {
				respuesta=estudianteService.login(correo, contraseña);
			}
	        		
	        	

	        if (respuesta) {
	            String token = JwtUtil.generateToken(correo);
	            Long id = null;

	            if (personaService.login(correo, contraseña)) {
	                id = personaService.buscarPersonaIdPorCredenciales(correo, contraseña);
	            } else if (estudianteService.login(correo, contraseña)) {
	                id = estudianteService.buscarEstudianteIdPorCredenciales(correo, contraseña);
	            }

	            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	            session.setAttribute("token", token);
	            session.setAttribute("id", id);

	            if (session.getAttribute("token") != null && session.getAttribute("id") != null) {
	            	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	            	externalContext.redirect("menu.jsf");
	            }
	        } else {
	        	  PrimeFaces.current().executeScript("PF('dialogoError').show();");
	        	  setCorreo(null);
	              setContraseña(null);
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
