package com.apptraorchestratorapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Process implements Serializable {

    private static final long serialVersionUID = -3009157732242241607L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    /* Código del servicio */
    private String processName;

    /* Determina si el servicio será ejecutado o no */
    private Integer enabled;

   
   /* Descripción del servicio */
    private String description;


    /* Tipo de proceso de negocio */
    private String type;

    private List<Service> services = new ArrayList<>();


   
}