package com.kosmos.medicina.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitasDto {
	private String nombreDoctor;
	private String paciente;
	private String horario;
	private String fechaConsulta;
	private Integer numeroConsultorio;
	private Integer piso;
}
