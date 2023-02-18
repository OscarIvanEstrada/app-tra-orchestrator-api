package com.apptraorchestratorapi.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class SimpleFlattenDeserializer implements JsonDeserializer<Map<String, String>> {
	

	@Override
	public Map<String, String> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		Map<String, String> map = new HashMap<>();

        if (json.isJsonArray()) {
        	int i= 0;
            for (JsonElement e : json.getAsJsonArray()) {
                map.putAll(deserialize(e, typeOfT, context));
                i++;
            }
        } else if (json.isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                if (entry.getValue().isJsonPrimitive()) {
                	map.put(entry.getKey(), entry.getValue().getAsString());
                } else {
                    map.putAll(deserialize(entry.getValue(), typeOfT, context));
                }
            }
        }
        return map;
	}

	

	
	public static void main(String[] args) {
		String input = "{\"correoElectronico\":\"oscar@microservicios.co\",\"nombre\":\"Oscar Ivan\",\"apellido\":\"Estrada\"}";
		GSonUtils.deserializeSimpleFlat(input);
	}

}