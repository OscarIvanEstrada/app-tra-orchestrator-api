package com.apptraorchestratorapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelloMessage {

    private String response;
    private String service;
    private int status;

}