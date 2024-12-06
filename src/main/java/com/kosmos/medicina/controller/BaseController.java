package com.kosmos.medicina.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kosmos.medicina.dto.GenericResponseDTO;
public class BaseController {
	
	public static final boolean SUCCESS = true;
	public static final boolean ERROR = false;
	
	public static final int ACTIVO = 1;
	public static final int INACTIVO = 0;
	
	public static final int HTTP_SUCCESS = 200;
	public static final int HTTP_BAD_REQUEST = 400;
	
	public static final String SUCCESS_MESSAGE = "SERVICIO EJECUTADO EXITOSAMENTE.";
	public static final String ERROR_MESSAGE = "Ocurrió un error al realizar la petición";
	
	public ResponseEntity<?> internalServerError(Exception e){
		return new ResponseEntity<>(new GenericResponseDTO<>(ERROR, HTTP_BAD_REQUEST, e.getMessage(), ERROR_MESSAGE),HttpStatus.OK);
	}

}
