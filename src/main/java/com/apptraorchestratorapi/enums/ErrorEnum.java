package com.apptraorchestratorapi.enums;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {
    APIGEE_ERROR("001","Error llamando servicio de apigee",HttpStatus.INTERNAL_SERVER_ERROR),
    ORCHESTRATOR_SERVICE("101","No se encuentra el servicio a ejecutar",HttpStatus.INTERNAL_SERVER_ERROR),
    ORCHESTRATOR_SERVICE_REQUIRED("102","El servicio es mandatory y se suspende la ejecución",HttpStatus.INTERNAL_SERVER_ERROR),
    ORCHESTRATOR_SERVICE_URL("103","El servicio no cuenta con una url válida",HttpStatus.INTERNAL_SERVER_ERROR),
    ORCHESTRATOR_SERVICE_RESOURCE("104","No se encuentra recurso asociado al servicio ",HttpStatus.INTERNAL_SERVER_ERROR),
    ORCHESTRATOR_SERVICE_TIMEOUT("105","Tiempo de espera superado",HttpStatus.REQUEST_TIMEOUT),
    ORCHESTRATOR_CORE("106","Pipe extractor error",HttpStatus.INTERNAL_SERVER_ERROR),
    ;
	
	private String code;
	private String description;
	private HttpStatus httpCode;
	
	private ErrorEnum(String code, String description,HttpStatus httpCode) {
		this.code=code;
		this.description = description;
		this.httpCode = httpCode;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public HttpStatus getHttpCode() {
		return httpCode;
	}
	
	
	
}
