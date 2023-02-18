package com.apptraorchestratorapi.exceptions;

import com.apptraorchestratorapi.enums.ErrorEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiException extends RuntimeException{

	 private ErrorEnum error;
	 private String message;

}
