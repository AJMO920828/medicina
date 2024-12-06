package com.kosmos.medicina.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Especialidad {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long idEspecialidad;
	
	private String nombre;
	
	
	public Especialidad(String nombre) {
		this.nombre = nombre;
	}
}
