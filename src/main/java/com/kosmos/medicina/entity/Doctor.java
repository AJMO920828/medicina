package com.kosmos.medicina.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long idDoctor;
	
	@NotNull(message="El campo nombre no debe ser nulo.")
	private String nombre;
	
	@NotNull(message="El campo apellido paterno no debe ser nulo.")
	private String apellidoPaterno;
	
	@NotNull(message="El campo apellido materno no debe ser nulo.")
	private String apellidoMaterno;
	
	@NotNull(message="El campo especialidad no debe ser nulo.")
	@ManyToOne
    @JoinColumn(name = "id_especialidad")
	private Especialidad especialidad;

	public Doctor(@NotNull(message = "El campo nombre no debe ser nulo.") String nombre,
			@NotNull(message = "El campo apellido paterno no debe ser nulo.") String apellidoPaterno,
			@NotNull(message = "El campo apellido materno no debe ser nulo.") String apellidoMaterno,
			@NotNull(message = "El campo especialidad no debe ser nulo.") Especialidad especialidad) {
		super();
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.especialidad = especialidad;
	}
	
	public Doctor(Doctor doc) {
		super();
		this.nombre = doc.getNombre();
		this.apellidoPaterno = doc.getApellidoPaterno();
		this.apellidoMaterno = doc.getApellidoMaterno();
		this.especialidad = doc.getEspecialidad();
	}
	
}

