package com.apptraorchestratorapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.http.HttpMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction implements Serializable {

    private static final long serialVersionUID = -3009157732242241609L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String transactionId;

    /* Nombre del parametro */
    private String parameters;
    
    /* Nombre del parametro */
    private String requestHeaders;
    
    /* Nombre del parametro */
    private String requestBody;
    
    /* Nombre del parametro */
    private String responseHeaders;
    
    /* Nombre del parametro */
    private String responseBody;
    
    /* Orden de ejecución */
    private Integer attemps;

    /* Orden de ejecución */
    private Integer status;

    private Date createDate;

    /* Descripción del servicio */
    private String sourceHost;

    /* Tipo de servicio a ajecutar REST=Servicio Rest */
    private String endpoint;

    /* Path del servicio a ajecutar REST=Servicio Rest */
    private String apiPath;
    
    private String serviceName;
    
    private String indexes;
    
    private String responseIndexes;
    /* Tipo de acción a ejecutar Q=Consulta, E=Ejecución */
    @Enumerated(EnumType.STRING)
    private HttpMethod method;
    
}