package com.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EstudianteDTO extends PersonaDTO{
	
	@NotEmpty(message = "El Numero de identificación estudiantil es obligatoria.")
	@Size(max = 70, message = "Debe tener como máximo 70 caracteres.")
	private String numeroIdEstudiante;
	
	@NotEmpty(message = "La Carrera ó Especializacion es obligatoria.")
	private String carreraEspecializacion;
	
	private int edad;
	

	public EstudianteDTO() {
		super();
		//TODO Auto-generated constructor stub
	}

	


	public EstudianteDTO(
			@NotEmpty(message = "El Numero de identificación estudiantil es obligatoria.") @Size(max = 70, message = "Debe tener como máximo 70 caracteres.") String numeroIdEstudiante,
			@NotEmpty(message = "La Carrera ó Especializacion es obligatoria.") String carreraEspecializacion,
			int edad) {
		super();
		this.numeroIdEstudiante = numeroIdEstudiante;
		this.carreraEspecializacion = carreraEspecializacion;
		this.edad = edad;
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




	public int getEdad() {
		return edad;
	}




	public void setEdad(int edad) {
		this.edad = edad;
	}

	


	
}
