package com.kosmos.medicina.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode(callSuper = false)
public class GenericResponseDTO<T> extends CommonResponse {
	private static final long serialVersionUID = -2036700957562926978L;
	
	@Setter
	@Getter
	private transient T body;
	
	public GenericResponseDTO(
			boolean seccess, 
			Integer httpStatus, 
			String messageResponse, 
			T body) {
		super(seccess, httpStatus, messageResponse);
		this.body = body;
	}
}
