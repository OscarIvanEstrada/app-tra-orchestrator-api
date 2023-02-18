package com.apptraorchestratorapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class ValueDTO {
    private String key;
    private String value;
    private List<ValueDTO> values;

    public ValueDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ValueDTO(){ }

}
