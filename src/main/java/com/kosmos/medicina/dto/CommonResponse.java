package com.kosmos.medicina.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class CommonResponse implements Serializable{

	private static final long serialVersionUID = 4729234495968145032L;
	
	@Setter
	@Getter
	private transient StatusMessage status;
	
	public CommonResponse(boolean seccess, Integer httpStatus, String messageResponse) {
		super();
		this.status = new StatusMessage(seccess, httpStatus, messageResponse);
	}
}
