package com.apptraorchestratorapi.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

public class YmlUtils {

	public static void main(String[] args) throws IOException {
	    List<Object> result = ymlToList();
		Map<String, String> mapDevDocument = (Map<String, String>) GSonUtils.deserializeDeepFlat(GSonUtils.serialize(result.get(0)));
		Map<String, String> mapReleaseDocument = (Map<String, String>) GSonUtils.deserializeDeepFlat(GSonUtils.serialize(result.get(1)));
		
		
		for (Map.Entry<String, String> entry : mapReleaseDocument.entrySet()) {
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}


	}
	
	private static List<Object> ymlToList() throws FileNotFoundException, IOException {
		Yaml yaml = new Yaml();
	    ClassPathResource classPathResource = new ClassPathResource("application.yml");
	    Reader yamlFile = new FileReader(classPathResource.getFile());

	    Iterable<Object> list =  yaml.loadAll(yamlFile);
	    List<Object> result = new ArrayList<Object>();
		
	    while (list.iterator().hasNext()) {
			result.add(list.iterator().next());
		}
		return result;
	}

//	private static List<Object> ymlToList() throws FileNotFoundException, IOException {
//		Yaml yaml = new Yaml();
//	    ClassPathResource classPathResource = new ClassPathResource("application.yml");
//	    Reader yamlFile = new FileReader(classPathResource.getFile());
//
//	    Iterable<Object> list =  yaml.loadAll(yamlFile);
//	    List<Object> result = new ArrayList<Object>();
//		
//	    while (list.iterator().hasNext()) {
//			result.add(list.iterator().next());
//		}
//		return result;
//	}
}
