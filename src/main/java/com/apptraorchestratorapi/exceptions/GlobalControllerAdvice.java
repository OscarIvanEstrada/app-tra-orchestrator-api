package com.apptraorchestratorapi.exceptions;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apptraorchestratorapi.util.LoggerUtil;


@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalControllerAdvice //extends ResponseEntityExceptionHandler
{


	@Autowired
	LoggerUtil log;

	 @ExceptionHandler(Throwable.class) 
	    public ResponseEntity<ApiException> problem(final Throwable e) {
	    	String message = "Error causado por: "+e.getMessage()+" -> ";
	    	
	    	if(e.getCause() != null) {
	    		message += e.getCause().getMessage()+" -> ";
	    	}
	    	if(e.getStackTrace() != null && e.getStackTrace().length > 0) {
	    		for(StackTraceElement element : e.getStackTrace()) {
	    			message += element +":"+ element+ " -> ";	
	    		}
	    	}
			List<GeneralErrorResponse> r = new ArrayList<>();
			r.add(new GeneralErrorResponse("000", message));
			log.error(message);
			return new ResponseEntity(r, HttpStatus.INTERNAL_SERVER_ERROR);
	    }



	    @ExceptionHandler(ApiException.class)
	    public ResponseEntity<ApiException> handleApiException(ApiException ex) {
			String message = ex.getMessage();
			
			List<GeneralErrorResponse> r = new ArrayList<>();
			
			r.add(new GeneralErrorResponse(ex.getError().getCode(), ex.getError().getDescription()));
			log.error(message);

			return new ResponseEntity(r, ex.getError().getHttpCode());
	    }
   
}
