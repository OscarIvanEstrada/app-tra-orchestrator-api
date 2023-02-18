package com.apptraorchestratorapi.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Utilidades usando la libreria gson
 *
 * @author Oscar Estrada
 * @since 22/06/2020
 */
public class GSonUtils {

    /**
     * Convierte un objeto a json
     *
     * @param src
     * @return
     */
    public static String serialize(Object src) {
        Gson gson = new Gson();
        return gson.toJson(src);
    }
    
    /**
     * Convierte un objeto a json
     *
     * @param src
     * @return
     */
    public static Map<String, String> deserializeDeepFlat(String json) {
    	
    	Type t = new TypeToken<Map<String, String>>(){}.getType();
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(t , new FlattenDeepDeserializer())
                .setPrettyPrinting()
                .create();
		
		Map<String, String> map = gson.fromJson(json, t);
    	
        return map;
    }
    
    /**
     * Convierte un objeto a json
     *
     * @param src
     * @return
     */
    public static Map<String, String> deserializeFlat(String json) {
    	
    	Type t = new TypeToken<Map<String, String>>(){}.getType();
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(t , new FlattenDeserializer())
                .setPrettyPrinting()
                .create();
		
		Map<String, String> map = gson.fromJson(json, t);
    	
        return map;
    }
    
    /**
     * Convierte un objeto a json
     *
     * @param src
     * @return
     */
    public static Map<String, String> deserializeSimpleFlat(String json) {
    	
    	Type t = new TypeToken<Map<String, String>>(){}.getType();
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(t , new SimpleFlattenDeserializer())
                .setPrettyPrinting()
                .create();
		
		Map<String, String> map = gson.fromJson(json, t);
    	
        return map;
    }
    
    
    /**
     * Convierte un objeto a json
     *
     * @param src
     * @return
     */
    public static Map<String, String> deserializeFlatIndex(String json) {
    	
    	Type t = new TypeToken<Map<String, String>>(){}.getType();
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(t , new FlattenDeserializerIndex())
                .setPrettyPrinting()
                .create();
		
		Map<String, String> map = gson.fromJson(json, t);
    	
        return map;
    }
    
    
    /**
     * Convierte un objeto a otro objeto
     *
     * @param src
     * @param dClass
     * @param <D>
     * @return
     */
    public static <D> D toObject(Object src, Class<D> dClass) {
        Gson gson = new Gson();
        String srcJson = gson.toJson(src);
        return gson.fromJson(srcJson, dClass);
    }

    /**
     * Convierte un objeto a otro objeto
     *
     * @param srcJson
     * @param dClass
     * @param <D>
     * @return
     */
    public static <D> D toObject(String srcJson, Class<D> dClass) {
        Gson gson = new Gson();
        return gson.fromJson(srcJson, dClass);
    }
    
    /**
     * Convierte un objeto a otro objeto
     *
     * @param src
     * @param dClass
     * @param <D>
     * @return
     * @throws Exception 
     */
    public static Map<String, Object> toMap(String src, String ... valueToCheck) throws Exception {
    	JSONObject parameters = new JSONObject(src);
    	
    	
    	if(parameters.toMap() != null && !parameters.isEmpty()) {
    		for(String key : valueToCheck) {
    			if(parameters.toMap().get(key) == null) {
    				throw new Exception("Error transformando a mapa, no se encontró la llave:"+key);	
    			}
    		}
    	}
    	
    	return parameters.toMap();
    }
    
    
    public static void main (String args[]) {
        
        Map<String, Map<String, Hora>> sitioMap = new HashMap<>();
        List<String> dispo = new ArrayList<>();
       
        dispo.add("restaurante,mesa1,08:30,horafin");
        dispo.add("restaurante,mesa2,09:30,horafin");
        dispo.add("restaurante,mesa3,10:30,horafin");
       
        String sit;
        String esp;
       
        for (int i = 0; i < dispo.size(); i++) {
        	sit = dispo.get(i).split(",")[0];
        	esp = dispo.get(i).split(",")[1];
            if(sitioMap.get(sit) == null) {
            	sitioMap.put(sit, new HashMap<>() );
            }
            
            
            Hora hora = new Hora();
            hora.setHoraInicio(dispo.get(i).split(",")[2]);
            hora.setHoraFin(dispo.get(i).split(",")[3]);
            
            sitioMap.get(sit).put(esp, hora);
        }
        
        System.out.println(sitioMap);
       
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Hora{
    	String horaInicio;
    	String horaFin;
    }

}

