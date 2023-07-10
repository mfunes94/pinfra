package com.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Estudiante;
import com.entities.Persona;

/**
 * Session Bean implementation class EstudianteDAO
 */
@Stateless
@LocalBean
public class EstudianteDAO{

    /**
     * Default constructor. 
     */
    public EstudianteDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext
    private EntityManager entityManager;

    public Estudiante guardarEstudiante(Estudiante estudiante) {
        entityManager.persist(estudiante);
        entityManager.flush();
        return estudiante;
    }

    public Estudiante obtenerEstudiante(Long id) {
        TypedQuery<Estudiante> query = entityManager.createQuery(
            "SELECT e FROM Estudiante e WHERE e.id = :id AND e.estado = 'activo'",
            Estudiante.class
        );
        query.setParameter("id", id);
        query.setMaxResults(1); 
        
        List<Estudiante> resultados = query.getResultList();
        if (!resultados.isEmpty()) {
            return resultados.get(0);
        } else {
            return null;
        }
    }
    
    public Long buscarEstudianteIdPorCredenciales(String correo, String contraseña) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT e.id FROM Estudiante e WHERE e.correo = :correo AND e.contraseña = :contraseña AND e.estado = 'activo'",
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
            "SELECT COUNT(e) FROM Estudiante e WHERE e.correo = :correo AND e.contraseña = :contraseña AND e.estado = 'activo'",
            Long.class
        );
        query.setParameter("correo", correo);
        query.setParameter("contraseña", contraseña);
        
        Long count = query.getSingleResult();
        
        return count > 0;
    }
   public boolean obtenerEstudianteIdBoolean(Long id) {
	    TypedQuery<Long> query = entityManager.createQuery(
	        "SELECT COUNT(e) FROM Estudiante e WHERE e.id = :id AND e.estado = 'activo'",
	        Long.class
	    );
	    query.setParameter("id", id);

	    Long count = query.getSingleResult();

	    return count > 0;
	}




    public Estudiante actualizarEstudiante(Estudiante estudiante) {
    	Estudiante estudianteExistente = entityManager.find(Estudiante.class, estudiante.getId());
    	estudianteExistente = estudiante;
        entityManager.merge(estudianteExistente);
        entityManager.flush();
        return estudianteExistente;
    }

    public void eliminarEstudiante(Long id) {
    	Estudiante e1 = obtenerEstudiante(id);
		e1.setEstado("inactivo");
		
		entityManager.flush();
    }
    
    public void activarEstudiante(Long id) {
    	Estudiante e1 = entityManager.find(Estudiante.class, id);
		e1.setEstado("activo");
		
		entityManager.flush();
    }
    
    public List<Estudiante> obtenerEstudiantesActivos() {
        TypedQuery<Estudiante> query = entityManager.createQuery(
                "SELECT e FROM Estudiante e WHERE e.estado = 'activo'",
                Estudiante.class
        );
        return query.getResultList();
    }
    
    public List<Estudiante> obtenerEstudiantesInactivos() {
        TypedQuery<Estudiante> query = entityManager.createQuery(
                "SELECT e FROM Estudiante e WHERE e.estado = 'inactivo'",
                Estudiante.class
        );
        return query.getResultList();
    }
    
    
    
    

}
