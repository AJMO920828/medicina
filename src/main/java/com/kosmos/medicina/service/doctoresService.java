package com.kosmos.medicina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosmos.medicina.entity.Doctor;
import com.kosmos.medicina.repository.DoctoresRepository;

import jakarta.transaction.Transactional;

@Service
public class doctoresService {

	@Autowired
	private DoctoresRepository doctoresRepository;
	
	public List<Doctor> obtenerDoctores() {
		return doctoresRepository.findAll();
	}
	
	@Transactional
	public void save(Doctor doctor) {
		doctoresRepository.save(doctor);
	}
	
	@Transactional
	public void update(Doctor doctor) {
		doctoresRepository.save(doctor);
	}
	
	@Transactional
	public void delete(Long idDoctor) {
		doctoresRepository.deleteById(idDoctor);
	}
}
