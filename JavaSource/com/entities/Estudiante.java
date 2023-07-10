package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Estudiante
 *
 */
@Entity

public class Estudiante  implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_ESTUDIANTE")
	private Long id;
	
	@Column(length=70,nullable=false, unique = false)
	private String nombre;
	
	
	@Column(length=70,nullable=false, unique = false)
	private String apellido;
	
	@Column(length=70,nullable=false, unique = false)
	private String direccion;
	

	@Column(length=50,nullable=false, unique = true)
	private String correo;
	
	@Column(length=50,nullable=false, unique = false)
	private String contraseña;
	
	
	@Column(name="fecha_nacimiento", nullable=false, unique = false)
	private Date fechaNacimiento;
	
	
	
	@Column(length=50,nullable=false, unique = false)
	private String estado;
	
	@Column(length=70,nullable=false, unique = false)
	private String numeroIdEstudiante;
	
	@Column(length=70,nullable=false, unique = false)
	private String carreraEspecializacion;
	
	private static final long serialVersionUID = 1L;

	public Estudiante() {
		super();
	}
	
	
	
	

	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public String getApellido() {
		return apellido;
	}





	public void setApellido(String apellido) {
		this.apellido = apellido;
	}





	public String getDireccion() {
		return direccion;
	}





	public void setDireccion(String direccion) {
		this.direccion = direccion;
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





	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}





	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}





	public String getEstado() {
		return estado;
	}





	public void setEstado(String estado) {
		this.estado = estado;
	}





	public String getNumeroIdEstudiante() {
		return numeroIdEstudiante;
	}





	public void setNumeroIdEstudiante(String numeroIdEstudiante) {
		this.numeroIdEstudiante = numeroIdEstudiante;
	}





	public String getCarreraEspecializacion() {
		return carreraEspecializacion;
	}





	public void setCarreraEspecializacion(String carreraEspecializacion) {
		this.carreraEspecializacion = carreraEspecializacion;
	}





	public Estudiante(Long id, String nombre, String apellido, String direccion, String correo, String contraseña,
			Date fechaNacimiento, String estado, String numeroIdEstudiante, String carreraEspecializacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.correo = correo;
		this.contraseña = contraseña;
		this.fechaNacimiento = fechaNacimiento;
		this.estado = estado;
		this.numeroIdEstudiante = numeroIdEstudiante;
		this.carreraEspecializacion = carreraEspecializacion;
	}





	





	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ ", correo=" + correo + ", contraseña=" + contraseña + ", fechaNacimiento=" + fechaNacimiento
				+ ", estado=" + estado + ", numeroIdEstudiante=" + numeroIdEstudiante + ", carreraEspecializacion="
				+ carreraEspecializacion + "]";
	}
	
	
   
}
