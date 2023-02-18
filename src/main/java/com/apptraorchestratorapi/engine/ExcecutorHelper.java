package com.apptraorchestratorapi.engine;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.apptraorchestratorapi.util.GSonUtils;
import com.apptraorchestratorapi.util.Pipe;

public class ExcecutorHelper {

	public static final String VARIABLES = "(\\{[\\w+\\s+\\-]*\\}|\\{[\\w+\\S]*\\})";

	public static String replaceParams(String source, String params) {
		Map<String, String> mapParameters = GSonUtils.deserializeDeepFlat(params);

		Pattern pattern = Pattern.compile(VARIABLES);
		if (mapParameters != null && source != null) {
			Matcher matcher = pattern.matcher(source);
			// check all occurance
			while (matcher.find()) {
				String key = matcher.group();
				String value = matcher.group();
				value = value.replace("{", "");
				value = value.replace("}", "");
				if(value.split(" ").length > 1) {
					source = source.replace(key, Pipe.excecute(value));
				}
				if (mapParameters.get(value) != null) {
					source = source.replace(key, mapParameters.get(value));
				}
			}
		}
		

		return source;
	}
	
	
	public static Map<String, String> getHeaders(String headers, String params) {
		String headersReplaced = replaceParams(headers, params); 
		return GSonUtils.deserializeSimpleFlat(headersReplaced);
	}
	
	
	public static String getIndexes(String params) {
		if(params == null) {
			params = "{}";
		}
		Map<String, String> obj = GSonUtils.deserializeFlatIndex(params);
		return GSonUtils.serialize(obj);
	}

	public static JSONObject mergeJSONObjects(JSONObject json1, JSONObject json2) {
		JSONObject mergedJSON = new JSONObject();
		try {
			
			if(JSONObject.getNames(json1) != null) {
				for (String crunchifyKey : JSONObject.getNames(json1)) {
					mergedJSON.put(crunchifyKey, json1.get(crunchifyKey));
				}
			}
			
			if(JSONObject.getNames(json2) != null) {
				for (String crunchifyKey : JSONObject.getNames(json2)) {
					mergedJSON.put(crunchifyKey, json2.get(crunchifyKey));
				}
			}
		} catch (JSONException e) {
			throw new RuntimeException("JSON Exception" + e);
		}
		return mergedJSON;
	}
	
	public static void main(String[] args) {
		JSONObject a = new JSONObject();
		a.put("a", "xxxx");
		JSONObject b = new JSONObject();			
		JSONObject r =mergeJSONObjects(a, b);
		GSonUtils.serialize(r.toMap());
		
    }

}
