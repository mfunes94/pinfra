package com.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.dto.EstudianteDTO;
import com.dto.PersonaDTO;
import com.exception.PersistenciaException;
import com.service.EstudianteService;
import com.service.PersonaService;
import com.utils.ExceptionsTools;

import org.primefaces.PrimeFaces;

@Named(value="tablaEstudianteController")		
@RequestScoped
public class TablaEstudianteController implements Serializable {
	
	private List<EstudianteDTO> estudiantes;
	private String errorMessage;
	
	private static final long serialVersionUID = 1L;

	@Inject
    private EstudianteService estudianteService;

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
	
	@PostConstruct
	public void obtenerEstudiantesActivos() {
		try {
			estudiantes = estudianteService.obtenerEstudiantesActivos();
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
