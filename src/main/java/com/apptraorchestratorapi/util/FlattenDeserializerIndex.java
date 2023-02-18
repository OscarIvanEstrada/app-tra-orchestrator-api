package com.apptraorchestratorapi.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Transforma un json en un cojunto de claves sin valores. Objetivo identificar los indices
 * @author oscar
 *
 */
public class FlattenDeserializerIndex implements JsonDeserializer<Map<String, String>> {
	

	@Override
	public Map<String, String> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
	        return deserializePather("","",0,json,typeOfT,context);
	}

	
	public Map<String, String> deserializePather(String pather,String type,int index, JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		 Map<String, String> map = new HashMap<>();

	        if (json.isJsonArray()) {
	        	int i= 0;
	            for (JsonElement e : json.getAsJsonArray()) {
	                map.putAll(deserializePather(pather,"array",i,e, typeOfT, context));
	                i++;
	            }
	        } else if (json.isJsonObject()) {
	            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
	                if (entry.getValue().isJsonPrimitive()) {
	                	
	                    if(pather.isEmpty()) {
	                    	map.put(entry.getKey(), "");
	                    }else {
	                    	if(pather.startsWith(".")) {
	                    		pather = pather.replaceFirst(".", "");
	                    	}
	                    	if(type.equals("array") && !pather.contains("["+index+"]")) {
		                		pather += "["+index+"]";
		                	}
	                    	map.put(pather + "."+entry.getKey(), "");
	                    }
	                } else {
	                    map.putAll(deserializePather(pather+"."+entry.getKey(),"single",0,entry.getValue(), typeOfT, context));
	                }
	            }
	        }
	        return map;
	}

	
	public static void main(String[] args) {
		
		
		String input = "{\r\n" + 
				"    \"alimentacion\": {\r\n" + 
				"        \"id\": 1\r\n" + 
				"    },\r\n" + 
				"    \"alumno\": {\r\n" + 
				"        \"fechaNacimiento\": \"2003-01-29\",\r\n" + 
				"        \"identificacion\": {\r\n" + 
				"            \"tipo\": 2,\r\n" + 
				"            \"codigo\": \"1023652028\"\r\n" + 
				"        },\r\n" + 
				"        \"nombre\": {\r\n" + 
				"            \"primero\": \"Antonio\",\r\n" + 
				"            \"segundo\": \" Alfredo\",\r\n" + 
				"            \"primerApellido\": \"Londoño\",\r\n" + 
				"            \"segundoApellido\": \"Ramírez\"\r\n" + 
				"        },\r\n" + 
				"        \"sexo\": \"M\"\r\n" + 
				"    },\r\n" + 
				"    \"idProceso\": \"210236520282020\",\r\n" + 
				"    \"beneficioAlimentacion\": 0,\r\n" + 
				"    \"beneficioMatricula\": 0,\r\n" + 
				"    \"calculoTarifa\": \"C\",\r\n" + 
				"    \"valorCarneEstudiantil\": 3700,\r\n" + 
				"    \"colegio\": \"COLEGIO CHICALA\",\r\n" + 
				"    \"fechaInscripcion\": \"2020-08-13 18:05:20\",\r\n" + 
				"    \"fechaPagoOportuno\": \"0001-07-05 10:12:13\",\r\n" + 
				"    \"fechaPagoExtemporaneo\": \"0001-10-05 10:12:13\",\r\n" + 
				"    \"grado\": 11,\r\n" + 
				"    \"valorPagoOportuno\": 711650,\r\n" + 
				"    \"periodo\": 2020,\r\n" + 
				"    \"valorRecargoMatriculaExtemporanea\": 60700,\r\n" + 
				"    \"valorSeguroColectivo\": 17500,\r\n" + 
				"    \"valorSistematizacionBoletines\": 0,\r\n" + 
				"    \"valorMatricula\": 690450,\r\n" + 
				"    \"valorSubsidio\": 0,\r\n" + 
				"    \"valorAPagar\": 690450,\r\n" + 
				"    \"totalAPagarMatricula\": 711650,\r\n" + 
				"    \"totalAPagarMatriculaConRecargo\": 772350,\r\n" + 
				"    \"contrato\": {\r\n" + 
				"        \"tipo\": \"1\",\r\n" + 
				"        \"descripcion\": \"\"\r\n" + 
				"    },\r\n" + 
				"    \"convenio\": {\r\n" + 
				"        \"codigo\": \"1\",\r\n" + 
				"        \"descripcion\": \"\"\r\n" + 
				"    },\r\n" + 
				"     \"responsables\": [\r\n" + 
				"         {\r\n" + 
				"            \"direccion\": {\r\n" + 
				"                \"ciudad\": \"Bello\",\r\n" + 
				"                \"detalle\": \"madera natural\"\r\n" + 
				"            },\r\n" + 
				"            \"estadoCivil\": {\r\n" + 
				"                \"id\": 1\r\n" + 
				"            },\r\n" + 
				"            \"identificacion\": {\r\n" + 
				"                \"tipo\": 1,\r\n" + 
				"                \"codigo\": \"71794606\",\r\n" + 
				"                \"fechaExpedicionDocumento\": \"2009-01-22\",\r\n" + 
				"                \"lugarExpedicionDocumento\": \"Bello\"\r\n" + 
				"            },\r\n" + 
				"            \"nombre\": {\r\n" + 
				"                \"primero\": \"jesus\",\r\n" + 
				"                \"segundo\": \"\",\r\n" + 
				"                \"primerApellido\": \"Matiz\",\r\n" + 
				"                \"segundoApellido\": \"Ramírez\"\r\n" + 
				"            },\r\n" + 
				"            \"empresa\": {\r\n" + 
				"                \"nombreEmpresa\": \"Ingeneo\",\r\n" + 
				"                \"razonSocial\": \"tecnologia y soluciones\",\r\n" + 
				"                \"fechaIngresoEmpresa\": \"2002-03-16 07:30:00\"\r\n" + 
				"            },\r\n" + 
				"            \"sexo\": \"M\",\r\n" + 
				"            \"fechaNacimiento\": \"1998-11-30\",\r\n" + 
				"            \"celular\": \"3054223409\",\r\n" + 
				"            \"correo\": \"jesus.matiz@ingeneo.com.co\",\r\n" + 
				"            \"telefono\": \"3002207\",\r\n" + 
				"            \"tipo\": {\r\n" + 
				"                \"tipoId\": \"T\",\r\n" + 
				"                \"descripcion\": \"Titular\"\r\n" + 
				"            }\r\n" + 
				"         },\r\n" + 
				"          {\r\n" + 
				"            \"direccion\": {\r\n" + 
				"                \"ciudad\": \"Bello\",\r\n" + 
				"                \"detalle\": \"madera natural\"\r\n" + 
				"            },\r\n" + 
				"            \"estadoCivil\": {\r\n" + 
				"                \"id\": 1\r\n" + 
				"            },\r\n" + 
				"            \"identificacion\": {\r\n" + 
				"                \"tipo\": 1,\r\n" + 
				"                \"codigo\": \"1017254655\",\r\n" + 
				"                \"fechaExpedicionDocumento\": \"2009-01-22\",\r\n" + 
				"                \"lugarExpedicionDocumento\": \"Bello\"\r\n" + 
				"            },\r\n" + 
				"            \"nombre\": {\r\n" + 
				"                \"primero\": \"Samuel\",\r\n" + 
				"                \"segundo\": \"\",\r\n" + 
				"                \"primerApellido\": \"Arboleda\",\r\n" + 
				"                \"segundoApellido\": \"Ramírez\"\r\n" + 
				"            },\r\n" + 
				"            \"empresa\": {\r\n" + 
				"                \"nombreEmpresa\": \"Ingeneo\",\r\n" + 
				"                \"razonSocial\": \"tecnologia y soluciones\",\r\n" + 
				"                \"fechaIngresoEmpresa\": \"2002-03-16 07:30:00\"\r\n" + 
				"            },\r\n" + 
				"            \"sexo\": \"M\",\r\n" + 
				"            \"fechaNacimiento\": \"1998-11-30\",\r\n" + 
				"            \"celular\": \"3054223409\",\r\n" + 
				"            \"correo\": \"samuel.arboleda@ingeneo.com.co\",\r\n" + 
				"            \"telefono\": \"3002207\",\r\n" + 
				"            \"tipo\": {\r\n" + 
				"                \"tipoId\": \"C\",\r\n" + 
				"                \"descripcion\": \"Codeudor\"\r\n" + 
				"            }\r\n" + 
				"         }        \r\n" + 
				"    \r\n" + 
				"    ]\r\n" + 
				"}";
		
		GSonUtils.deserializeFlat(input);
	}

}