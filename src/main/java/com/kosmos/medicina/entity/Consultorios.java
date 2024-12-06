package com.kosmos.medicina.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Consultorios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long idConsultorio;
	private Integer numeroConsultorio;
	private Integer piso;
	
	public Consultorios(Consultorios consultorio) {
		this.numeroConsultorio = consultorio.getNumeroConsultorio();
		this.piso = consultorio.getPiso();
	}
	
}
