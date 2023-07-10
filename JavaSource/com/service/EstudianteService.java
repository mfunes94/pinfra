package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.EstudianteDAO;
import com.dto.EstudianteDTO;
import com.dto.PersonaDTO;
import com.entities.Estudiante;
import com.entities.Persona;
import com.exception.PersistenciaException;



@Stateless
@LocalBean
public class EstudianteService {
	
	
	@EJB
	EstudianteDAO  estudiantePersistenciaDAO;
  
    public EstudianteService() {
    
    }
    
    public EstudianteDTO fromEstudiante(Estudiante estudianteEntity) {
		
    	EstudianteDTO estudianteDTO=new EstudianteDTO();
		estudianteDTO.setId(estudianteEntity.getId().longValue());
		estudianteDTO.setNombre(estudianteEntity.getNombre());
		estudianteDTO.setApellido(estudianteEntity.getApellido());
		estudianteDTO.setDireccion(estudianteEntity.getDireccion());
		estudianteDTO.setCorreo(estudianteEntity.getCorreo());
		estudianteDTO.setContraseña(estudianteEntity.getContraseña());
		estudianteDTO.setFechaNacimiento(estudianteEntity.getFechaNacimiento());
		estudianteDTO.setEstado(estudianteEntity.getEstado());
		estudianteDTO.setNumeroIdEstudiante(estudianteEntity.getNumeroIdEstudiante());
		estudianteDTO.setCarreraEspecializacion(estudianteEntity.getCarreraEspecializacion());
		return estudianteDTO;
	}
	
	public Estudiante toEstudiante(EstudianteDTO estudianteDTO) {
		Estudiante estudianteEntity =new Estudiante();
		estudianteEntity.setNombre(estudianteDTO.getNombre());
		estudianteEntity.setApellido(estudianteDTO.getApellido());
		estudianteEntity.setDireccion(estudianteDTO.getDireccion());
		estudianteEntity.setCorreo(estudianteDTO.getCorreo());
		estudianteEntity.setContraseña(estudianteDTO.getContraseña());
		estudianteEntity.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
		estudianteEntity.setEstado(estudianteDTO.getEstado());
		estudianteEntity.setNumeroIdEstudiante(estudianteDTO.getNumeroIdEstudiante());
		estudianteEntity.setCarreraEspecializacion(estudianteDTO.getCarreraEspecializacion());
		return estudianteEntity;
	}
	
	public Estudiante toEstudianteActualizar(EstudianteDTO estudianteDTO) {
		Estudiante estudianteEntity =new Estudiante();
		estudianteEntity.setId(estudianteDTO.getId());
		estudianteEntity.setNombre(estudianteDTO.getNombre());
		estudianteEntity.setApellido(estudianteDTO.getApellido());
		estudianteEntity.setDireccion(estudianteDTO.getDireccion());
		estudianteEntity.setCorreo(estudianteDTO.getCorreo());
		estudianteEntity.setContraseña(estudianteDTO.getContraseña());
		estudianteEntity.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
		estudianteEntity.setEstado(estudianteDTO.getEstado());
		estudianteEntity.setNumeroIdEstudiante(estudianteDTO.getNumeroIdEstudiante());
		estudianteEntity.setCarreraEspecializacion(estudianteDTO.getCarreraEspecializacion());
		return estudianteEntity;
	}
	


	


	

	

	public List<EstudianteDTO> obtenerEstudiantesActivos() throws PersistenciaException {
		
		List<Estudiante> listaEstudianteEntity = estudiantePersistenciaDAO.obtenerEstudiantesActivos();
		
		List<EstudianteDTO> listaEstudianteDTO=new ArrayList<EstudianteDTO>();
		long diferenciaMilisegundos;
		EstudianteDTO estudianteDTO;
		
		for (Estudiante estudianteEntity : listaEstudianteEntity) {
			Date fechaActual = new Date();
			diferenciaMilisegundos= fechaActual.getTime() - estudianteEntity.getFechaNacimiento().getTime();
			long milisegundosEnUnAnio = 1000L * 60L * 60L * 24L * 365L;
			int edadEnAnios = (int) (diferenciaMilisegundos / milisegundosEnUnAnio);
			estudianteDTO = fromEstudiante(estudianteEntity);
			estudianteDTO.setEdad(edadEnAnios);
			listaEstudianteDTO.add(estudianteDTO);
		}
		return listaEstudianteDTO;
	}
	

	
	
	public List<EstudianteDTO> obtenerEstudiantesInactivos() throws PersistenciaException {
		
		List<Estudiante> listaEstudianteEntity = estudiantePersistenciaDAO.obtenerEstudiantesInactivos();
		
		List<EstudianteDTO> listaEstudianteDTO=new ArrayList<EstudianteDTO>();
		long diferenciaMilisegundos;
		EstudianteDTO estudianteDTO;
		
		for (Estudiante estudianteEntity : listaEstudianteEntity) {
			Date fechaActual = new Date();
			diferenciaMilisegundos= fechaActual.getTime()- estudianteEntity.getFechaNacimiento().getTime();
			long milisegundosEnUnAnio = 1000L * 60L * 60L * 24L * 365L;
			int edadEnAnios = (int) (diferenciaMilisegundos / milisegundosEnUnAnio);
			estudianteDTO = fromEstudiante(estudianteEntity);
			estudianteDTO.setEdad(edadEnAnios);
			listaEstudianteDTO.add(fromEstudiante(estudianteEntity));
		}
		return listaEstudianteDTO;
	}


	
	public EstudianteDTO buscarEstudianteByID(Long id) {
		Estudiante estudianteEntity = estudiantePersistenciaDAO.obtenerEstudiante(id);
		return fromEstudiante(estudianteEntity);
	}
	
	public Long buscarEstudianteIdPorCredenciales(String correo, String contraseña) {
	    Long idEstudianteEntity = estudiantePersistenciaDAO.buscarEstudianteIdPorCredenciales(correo, contraseña);
	    return idEstudianteEntity;
	}

	
	public boolean login(String correo, String contraseña) {
		boolean respuesta = estudiantePersistenciaDAO.loginBoolean(correo,contraseña);
		return respuesta;
	}
	
	public boolean obtenerEstudianteIdBoolean(Long id) {
	    boolean respuesta = estudiantePersistenciaDAO.obtenerEstudianteIdBoolean(id);
	    return respuesta;
	}


	
	
	public EstudianteDTO agregarEstudiante(EstudianteDTO estudianteDTO) throws PersistenciaException   {
		Estudiante estudianteEntity = estudiantePersistenciaDAO.guardarEstudiante(toEstudiante(estudianteDTO));
		return fromEstudiante(estudianteEntity);
	}

	public void actualizarEstudiante(EstudianteDTO estudianteSeleccionado) throws PersistenciaException   {
		Estudiante e = estudiantePersistenciaDAO.actualizarEstudiante(toEstudianteActualizar(estudianteSeleccionado));
	}
	
	public void eliminarEstudiante(Long id) throws PersistenciaException {
	        estudiantePersistenciaDAO.eliminarEstudiante(id);
	        
	  
	}
	
	public void activarEstudiante(Long id) throws PersistenciaException {
        estudiantePersistenciaDAO.activarEstudiante(id);
        
  
	}


}
