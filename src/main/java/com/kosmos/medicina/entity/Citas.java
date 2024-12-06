package com.kosmos.medicina.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Citas {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long idCita;
	
	@NotNull(message="El campo consultorio no debe ser nulo.")
	@ManyToOne
    @JoinColumn(name = "id_consultorio")
	private Consultorios consultorio;
	
	@NotNull(message="El campo doctor no debe ser nulo.")
	@ManyToOne
    @JoinColumn(name = "id_doctor")
	private Doctor doctor;
	
	@NotNull(message="El campo horario no debe ser nulo.")
	private String horarioConsulta;
	
	@NotNull(message="El campo fecha consulta no debe ser nulo.")
	private Date fechaConsulta;
	
	@NotNull(message="El campo nombre del paciente no debe ser nulo.")
	private String nombrePaciente;

}
