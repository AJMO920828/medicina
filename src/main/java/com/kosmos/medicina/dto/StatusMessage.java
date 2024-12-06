package com.kosmos.medicina.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatusMessage {

	private boolean success;
	private Integer httpStatus;
	private String messageResponse;
	
	
}
