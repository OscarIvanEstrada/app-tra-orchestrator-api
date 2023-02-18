package com.apptraorchestratorapi.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private int status;
    private String transactionId;
    private String service;
    private String response;
    public ResponseDTO(){
    }


}
