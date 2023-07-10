package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.PersonaDAO;
import com.dto.PersonaDTO;
import com.entities.Persona;
import com.exception.PersistenciaException;



@Stateless
@LocalBean
public class PersonaService{
	
	@EJB
	PersonaDAO personaPersistenciaDAO;
	

    public PersonaService() {
        
    }
    
	public PersonaDTO fromPersona(Persona personaEntity) {
		
		PersonaDTO personaDTO=new PersonaDTO();
		personaDTO.setId(personaEntity.getId().longValue());
		personaDTO.setNombre(personaEntity.getNombre());
		personaDTO.setApellido(personaEntity.getApellido());
		personaDTO.setDireccion(personaEntity.getDireccion());
		personaDTO.setCorreo(personaEntity.getCorreo());
		personaDTO.setContraseña(personaEntity.getContraseña());
		personaDTO.setFechaNacimiento(personaEntity.getFechaNacimiento());
		personaDTO.setEstado(personaEntity.getEstado());
		return personaDTO;
	}
	
	public Persona toPersona(PersonaDTO personaDTO) {
		Persona personaEntity =new Persona();
		personaEntity.setNombre(personaDTO.getNombre());
		personaEntity.setApellido(personaDTO.getApellido());
		personaEntity.setDireccion(personaDTO.getDireccion());
		personaEntity.setCorreo(personaDTO.getCorreo());
		personaEntity.setContraseña(personaDTO.getContraseña());
		personaEntity.setFechaNacimiento(personaDTO.getFechaNacimiento());
		personaEntity.setEstado(personaDTO.getEstado());
		return personaEntity;
	}
	
	public Persona toPersonaActualizar(PersonaDTO personaDTO) {
		Persona personaEntity =new Persona();
		personaEntity.setId(personaDTO.getId());
		personaEntity.setNombre(personaDTO.getNombre());
		personaEntity.setApellido(personaDTO.getApellido());
		personaEntity.setDireccion(personaDTO.getDireccion());
		personaEntity.setCorreo(personaDTO.getCorreo());
		personaEntity.setContraseña(personaDTO.getContraseña());
		personaEntity.setFechaNacimiento(personaDTO.getFechaNacimiento());
		personaEntity.setEstado(personaDTO.getEstado());
		return personaEntity;
	}
	


	
	public boolean hayPersonasActivas() {
		boolean respuesta = personaPersistenciaDAO.hayPersonasActivas();
		return respuesta;
	}


	

	

	public List<PersonaDTO> obtenerPersonasActivas() throws PersistenciaException {
		
		List<Persona> listaPersonaEntity = personaPersistenciaDAO.obtenerPersonasActivos();
		
		List<PersonaDTO> listaPersonaDTO=new ArrayList<PersonaDTO>();
		
		long diferenciaMilisegundos;
		PersonaDTO personaDTO;
		for (Persona personaEntity : listaPersonaEntity) {
			Date fechaActual = new Date();
			diferenciaMilisegundos= fechaActual.getTime()-personaEntity.getFechaNacimiento().getTime();
			long milisegundosEnUnAnio = 1000L * 60L * 60L * 24L * 365L;
			int edadEnAnios = (int) (diferenciaMilisegundos / milisegundosEnUnAnio);
			personaDTO =fromPersona(personaEntity);
			personaDTO.setEdad(edadEnAnios);
			listaPersonaDTO.add(personaDTO);
		}
		return listaPersonaDTO;
	}
	
	
	public List<PersonaDTO> obtenerPersonasInactivas() throws PersistenciaException {
		
		List<Persona> listaPersonaEntity = personaPersistenciaDAO.obtenerPersonasInactivos();
		
		List<PersonaDTO> listaPersonaDTO=new ArrayList<PersonaDTO>();
		
		long diferenciaMilisegundos;
		PersonaDTO personaDTO;
		for (Persona personaEntity : listaPersonaEntity) {
			Date fechaActual = new Date();
			diferenciaMilisegundos= fechaActual.getTime()-personaEntity.getFechaNacimiento().getTime();
			long milisegundosEnUnAnio = 1000L * 60L * 60L * 24L * 365L;
			int edadEnAnios = (int) (diferenciaMilisegundos / milisegundosEnUnAnio);
			personaDTO =fromPersona(personaEntity);
			personaDTO.setEdad(edadEnAnios);
			listaPersonaDTO.add(personaDTO);
		}
		return listaPersonaDTO;
	}


	
	public PersonaDTO buscarPersonaByID(Long id) {
		Persona personaEntity = personaPersistenciaDAO.obtenerPersona(id);
		return fromPersona(personaEntity);
	}
	
	public boolean obtenerPersonaIdBoolean(Long id) {
	    boolean respuesta = personaPersistenciaDAO.obtenerPersonaIdBoolean(id);
	    return respuesta;
	}

	
	public boolean login(String correo , String contraseña) {
		boolean respuesta = personaPersistenciaDAO.loginBoolean(correo,contraseña);
		return respuesta;
	}
	
	public Long buscarPersonaIdPorCredenciales(String correo, String contraseña) {
	    Long idPersonaEntity = personaPersistenciaDAO.buscarPersonaIdPorCredenciales(correo, contraseña);
	    return idPersonaEntity;
	}
	
	
	public PersonaDTO agregarPersona(PersonaDTO personaDTO) throws PersistenciaException   {
		Persona personaEntity = personaPersistenciaDAO.guardarPersona(toPersona(personaDTO));
		
		return fromPersona(personaEntity);
	}

	public void actualizarPersona(PersonaDTO personaSeleccionada) throws PersistenciaException   {
		Persona e = personaPersistenciaDAO.actualizarPersona(toPersonaActualizar(personaSeleccionada));
	}
	
	public void eliminarPersona(Long id) throws PersistenciaException {
	        personaPersistenciaDAO.eliminarPersona(id);
	   
	}
	
	public void activarPersona(Long id) throws PersistenciaException {
			personaPersistenciaDAO.activarPersona(id);
	}
	
	


}
