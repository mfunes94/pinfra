package com.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Persona;

/**
 * Session Bean implementation class PersonaDAO
 */
@Stateless
@LocalBean
public class PersonaDAO{

   
    public PersonaDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext
    private EntityManager entityManager;

    public Persona guardarPersona(Persona persona) {
        entityManager.persist(persona);
        entityManager.flush();
        return persona;
    }

    public Persona obtenerPersona(Long id) {
        TypedQuery<Persona> query = entityManager.createQuery(
            "SELECT p FROM Persona p WHERE p.id = :id AND p.estado = 'activo'",
            Persona.class
        );
        query.setParameter("id", id);
        query.setMaxResults(1); 
        
        List<Persona> resultados = query.getResultList();
        if (!resultados.isEmpty()) {
            return resultados.get(0);
        } else {
            return null;
        }
    }
    
    public Long buscarPersonaIdPorCredenciales(String correo, String contraseña) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT p.id FROM Persona p WHERE p.correo = :correo AND p.contraseña = :contraseña AND p.estado = 'activo'",
            Long.class
        );
        query.setParameter("correo", correo);
        query.setParameter("contraseña", contraseña);
        query.setMaxResults(1);

        List<Long> resultados = query.getResultList();
        if (!resultados.isEmpty()) {
            return resultados.get(0);
        } else {
            return null;
        }
    }

    
    public boolean loginBoolean(String correo, String contraseña) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(p) FROM Persona p WHERE p.correo = :correo AND p.contraseña = :contraseña AND p.estado = 'activo'",
            Long.class
        );
        query.setParameter("correo", correo);
        query.setParameter("contraseña", contraseña);
        
        Long count = query.getSingleResult();
        
        return count > 0;
    }
    
    public boolean obtenerPersonaIdBoolean(Long id) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(p) FROM Persona p WHERE p.id = :id AND p.estado = 'activo'",
            Long.class
        );
        query.setParameter("id", id);

        Long count = query.getSingleResult();

        return count > 0;
    }



   

    public Persona actualizarPersona(Persona persona) {
    	Persona personaExistente = entityManager.find(Persona.class, persona.getId());
    	personaExistente = persona;
    	
        entityManager.merge(personaExistente);
        entityManager.flush();
        return personaExistente;
    }

    public void eliminarPersona(Long id) {
    	Persona p1 = obtenerPersona(id);
		p1.setEstado("inactivo");
		
		entityManager.flush();
    }
    
    public void activarPersona(Long id) {
    	Persona p1 = entityManager.find(Persona.class, id);
		p1.setEstado("activo");
		
		entityManager.flush();
    }
    
    public List<Persona> obtenerPersonasActivos() {
        TypedQuery<Persona> query = entityManager.createQuery(
                "SELECT e FROM Persona e WHERE e.estado = 'activo'",
                Persona.class
        );
        return query.getResultList();
    }
    
    public boolean hayPersonasActivas() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(e) FROM Persona e WHERE e.estado = 'activo'",
                Long.class
        );
        Long count = query.getSingleResult();
        return count > 0;
    }

    
    public List<Persona> obtenerPersonasInactivos() {
        TypedQuery<Persona> query = entityManager.createQuery(
                "SELECT e FROM Persona e WHERE e.estado = 'inactivo'",
                Persona.class
        );
        return query.getResultList();
    }
    

}
