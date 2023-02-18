package com.apptraorchestratorapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ClienteRequestDTO Description")
public class ClienteRequestDTO {

    @ApiModelProperty(value = "Data Exmaple for ClienteRequestDTO", required = true, example = "nombre")
    private String nombre;
	
    @ApiModelProperty(value = "Data Exmaple for ClienteRequestDTO", required = true, example = "apellido")
    private String apellido;
	
    @ApiModelProperty(value = "Data Exmaple for ClienteRequestDTO", required = true, example = "documento")
    private String documento;
	
}
