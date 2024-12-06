package com.kosmos.medicina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosmos.medicina.entity.Consultorios;
import com.kosmos.medicina.repository.ConsultorioRepository;

@Service
public class ConsultoriosService {
	
	@Autowired
	private ConsultorioRepository consultorioRepository;
	
	public List<Consultorios> getConsultoriosAll(){
		return consultorioRepository.findAll();
	}
}
