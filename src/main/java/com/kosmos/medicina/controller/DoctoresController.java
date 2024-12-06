package com.kosmos.medicina.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kosmos.medicina.dto.GenericResponseDTO;
import com.kosmos.medicina.entity.Doctor;
import com.kosmos.medicina.service.doctoresService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@Api(tags = "Documentos Service",description = "Servicios para obtener documentos")
@RequestMapping("/api/doctores")
@Slf4j
public class DoctoresController extends BaseController{
	
	@Autowired
	private doctoresService doctoresService;
	
	@GetMapping()
	public ResponseEntity<?> getMethodName() {
		return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS, HTTP_SUCCESS, SUCCESS_MESSAGE, doctoresService.obtenerDoctores()));
	}
	
	@PostMapping
    public ResponseEntity<?> crearDoctor(@Valid @RequestBody Doctor doctor, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
            	Map<String, String> errors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.ok(new GenericResponseDTO<>(ERROR, HTTP_BAD_REQUEST, ERROR_MESSAGE, errors));
            }
            doctoresService.save(doctor);
			return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS, HTTP_SUCCESS, SUCCESS_MESSAGE, "Doctor creado con éxito."));
        } catch (Exception e) {
        	return ResponseEntity.ok(new GenericResponseDTO<>(ERROR, HTTP_BAD_REQUEST, ERROR_MESSAGE, e.getMessage()));
		}
    }

	
	@PutMapping
	public ResponseEntity<?> doctoresUpdate(@RequestBody Doctor doctor, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
            	Map<String, String> errors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.ok(new GenericResponseDTO<>(ERROR, HTTP_BAD_REQUEST, ERROR_MESSAGE, errors));
            }
			doctoresService.update(doctor);
			return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS, HTTP_SUCCESS, SUCCESS_MESSAGE, "El doctor fue modificado con éxito."));
		} catch (Exception e) {
			return ResponseEntity.ok(new GenericResponseDTO<>(ERROR, HTTP_BAD_REQUEST, ERROR_MESSAGE, e.getMessage()));
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> doctores(@PathVariable Long id) {
		try {
			doctoresService.delete(id);
			return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS, HTTP_SUCCESS, SUCCESS_MESSAGE, "El doctor fue dado de baja con éxito."));
		} catch (Exception e) {
			return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS, HTTP_SUCCESS, SUCCESS_MESSAGE, e.getMessage()));
		}
	}

}
