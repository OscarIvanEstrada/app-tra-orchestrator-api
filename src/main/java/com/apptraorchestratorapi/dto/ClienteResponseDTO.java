package com.apptraorchestratorapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ClienteResponseDTO Description")
public class ClienteResponseDTO {

    @ApiModelProperty(value = "Data Exmaple for ClienteResponseDTO", required = true, example = "nombre")
    private String nombre;
    @ApiModelProperty(value = "Data Exmaple for ClienteResponseDTO", required = true, example = "apellido")
    private String apellido;
    @ApiModelProperty(value = "Data Exmaple for ClienteResponseDTO", required = true, example = "documento")
    private String documento;
}
