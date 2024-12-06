package com.kosmos.medicina.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kosmos.medicina.dto.GenericResponseDTO;
import com.kosmos.medicina.entity.Citas;
import com.kosmos.medicina.service.citasServices;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/citas")
@Slf4j
public class CitasController extends BaseController {

	@Autowired
	private citasServices citasServices;
	
	
	@PostMapping("/generarCita")
	public ResponseEntity<?> generaCita(@Valid @RequestBody Citas citas, BindingResult bindingResult) {
		try {
            if (bindingResult.hasErrors()) {
            	Map<String, String> errors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.ok(new GenericResponseDTO<>(ERROR, HTTP_BAD_REQUEST, ERROR_MESSAGE, errors));
            }
            citasServices.guardar(citas);
			return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS, HTTP_SUCCESS, SUCCESS_MESSAGE, "La cita se genero con éxito."));
        } catch (Exception e) {
        	return ResponseEntity.ok(new GenericResponseDTO<>(ERROR, HTTP_BAD_REQUEST, ERROR_MESSAGE, e.getMessage()));
		}
	}
	
	@PostMapping("/consultaCitas")
	public ResponseEntity<?> ConsultaCitas(@RequestBody Citas filtro) {
		try {
			return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS, HTTP_SUCCESS, SUCCESS_MESSAGE, citasServices.consultarCita(filtro)));
        } catch (Exception e) {
        	return ResponseEntity.ok(new GenericResponseDTO<>(ERROR, HTTP_BAD_REQUEST, ERROR_MESSAGE, e.getMessage()));
		}
	}
	
}
