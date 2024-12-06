package com.kosmos.medicina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosmos.medicina.entity.Citas;
import com.kosmos.medicina.entity.Consultorios;
import com.kosmos.medicina.entity.Doctor;

import java.util.Date;
import java.util.List;


public interface CitasRepository extends JpaRepository<Citas, Long>{

	List<Citas> findByHorarioConsulta(String horarioConsulta);
	List<Citas> findByFechaConsulta(Date fechaConsulta);
	List<Citas> findByDoctor(Doctor doctor);
	List<Citas> findByConsultorio(Consultorios consultorio);
 	
}
