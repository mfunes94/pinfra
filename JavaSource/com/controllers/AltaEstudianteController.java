package com.controllers;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.dto.EstudianteDTO;
import com.dto.PersonaDTO;
import com.exception.PersistenciaException;
import com.service.EstudianteService;
import com.utils.ExceptionsTools;

import org.primefaces.PrimeFaces;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;	//JEE8




@Named(value="estudianteController")		//JEE8
@SessionScoped
public class AltaEstudianteController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EstudianteService estudianteService;
	
	private EstudianteDTO estudianteDTO = new EstudianteDTO();
	private String errorMessage;

	
	@PostConstruct
	public void init() {
		estudianteDTO=new EstudianteDTO();
		
	}

	
	
	public EstudianteDTO getEstudianteDTO() {
		return estudianteDTO;
	}



	public void setEstudianteDTO(EstudianteDTO estudianteDTO) {
		this.estudianteDTO = estudianteDTO;
	}
	
	

	public String getErrorMessage() {
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



	public void guardarEstudiante() {
		EstudianteDTO nuevoEstudianteDTO;
        try {
        	
        	if (estudianteDTO.getId() == null) {
                estudianteDTO.setId(null);
            } 
        	estudianteDTO.setEstado("inactivo");
            nuevoEstudianteDTO = estudianteService.agregarEstudiante(estudianteDTO);
          
            estudianteDTO = new EstudianteDTO();
            
            if (nuevoEstudianteDTO != null) {
                PrimeFaces.current().executeScript("PF('dialogoAltaEstudiante').show();");
       
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
