package com.apptraorchestratorapi.dto.apigee;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class ApigeeTokenReqDTO {

    private String clienteId;
	private String clienteSecreto;

}

