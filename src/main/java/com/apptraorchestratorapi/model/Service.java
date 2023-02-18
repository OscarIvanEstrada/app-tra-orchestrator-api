package com.apptraorchestratorapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.http.HttpMethod;

import com.apptraorchestratorapi.enums.ParamCollectStrategyEnum;
import com.apptraorchestratorapi.enums.ServiceTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Service implements Serializable, Comparable<Service> {

    private static final long serialVersionUID = -3009157732242241608L;

    /* Identificador del servicio remoto */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    /* Tipo de acción a ejecutar Q=Consulta, E=Ejecución */
    @Enumerated(EnumType.STRING)
    private HttpMethod method;

    /* Determina si el servicio remoto será o no ejecutado */
    private boolean enabled;

    /* Descripción del servicio */
    private String serviceName;

    /* Descripción larga del servicio */
    private String description;

    /* Tipo de servicio a ajecutar REST, APIGEE */
    @Enumerated(EnumType.STRING)
    private ServiceTypeEnum type;

    /* Tipo de servicio a ajecutar REST=Servicio Rest */
    private String endpoint;

    /* Orden de ejecución */
    private Integer order;

    /* Determinante de parada en la ejecución del proceso general 1=Para el proceso 0=No para el proceso */
    @Column(name = "MANDATORY")
    private boolean mandatory;

    /* Path del servicio a ajecutar REST=Servicio Rest */
    private String apiPath;

    private String params;

    /* Body inicial */
    private String apiBody;
    
    /* Body inicial */
    private String apiHeaders;
    
    private String successPattern;
    
    private int timeout;
    
    
    /* Tipo de servicio a ajecutar REST, APIGEE */
    @Enumerated(EnumType.STRING)
    private ParamCollectStrategyEnum paramCollectStrategy;


    @Override
    public int compareTo(Service o) {
        if(this.getOrder() == null || o.getOrder() == null){
            return 0;
        }

        if(this.getOrder().intValue() == o.getOrder().intValue()){
            return 0;
        }

        if(this.getOrder().intValue() > o.getOrder().intValue()){
            return  1;
        }else{
            return  -1;
        }
    }
}