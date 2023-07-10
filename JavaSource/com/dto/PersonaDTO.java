package com.dto;

import java.util.ArrayList;
import java.util.Date;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonaDTO {

    private Long id;
    
    @NotEmpty(message = "El Nombre es obligatorio.")
    @Size(max = 70, message = "El Nombre debe tener como máximo 70 caracteres.")
    private String nombre;

    @NotEmpty(message = "El Apellido es obligatorio.")
    @Size(max = 70, message = "El Apellido debe tener como máximo 70 caracteres.")
    private String apellido;

    @NotEmpty(message = "La Dirección es obligatoria.")
    @Size(max = 70, message = "La Dirección debe tener como máximo 70 caracteres.")
    private String direccion;

    @NotEmpty(message = "El Correo es obligatorio.")
    @Size(max = 50, message = "El Correo debe tener como máximo 50 caracteres.")
    private String correo;

    @NotEmpty(message = "La Contraseña es obligatoria.")
    @Size(max = 50, message = "La Contraseña debe tener como máximo 50 caracteres.")
    private String contraseña;

    @NotNull(message = "La Fecha de Nacimiento es obligatoria.")
    private Date fechaNacimiento;

    private String estado;
    
    private int edad;
    
 
    public PersonaDTO() {
        
    }
    

	public PersonaDTO(Long id,
			@NotEmpty(message = "El Nombre es obligatorio.") @Size(max = 70, message = "El Nombre debe tener como máximo 70 caracteres.") String nombre,
			@NotEmpty(message = "El Apellido es obligatorio.") @Size(max = 70, message = "El Apellido debe tener como máximo 70 caracteres.") String apellido,
			@NotEmpty(message = "La Dirección es obligatoria.") @Size(max = 70, message = "La Dirección debe tener como máximo 70 caracteres.") String direccion,
			@NotEmpty(message = "El Correo es obligatorio.") @Size(max = 50, message = "El Correo debe tener como máximo 50 caracteres.") String correo,
			@NotEmpty(message = "La Contraseña es obligatoria.") @Size(max = 50, message = "La Contraseña debe tener como máximo 50 caracteres.") String contraseña,
			@NotNull(message = "La Fecha de Nacimiento es obligatoria.") Date fechaNacimiento, String estado,
			int edad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.correo = correo;
		this.contraseña = contraseña;
		this.fechaNacimiento = fechaNacimiento;
		this.estado = estado;
		this.edad = edad;
	}


	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
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
    
    
    

}

