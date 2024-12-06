package com.kosmos.medicina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.medicina.dto.GenericResponseDTO;
import com.kosmos.medicina.service.ConsultoriosService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Consultorios")
@Slf4j
public class ConsultoriosController extends BaseController {

	@Autowired
	private ConsultoriosService consultoriosService;
	
	@GetMapping
	public ResponseEntity<?> getConsultorios() {
		try {
			return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS, HTTP_SUCCESS, SUCCESS_MESSAGE, consultoriosService.getConsultoriosAll()));
        } catch (Exception e) {
        	return ResponseEntity.ok(new GenericResponseDTO<>(ERROR, HTTP_BAD_REQUEST, ERROR_MESSAGE, e.getMessage()));
        }
	}
	
	
}
