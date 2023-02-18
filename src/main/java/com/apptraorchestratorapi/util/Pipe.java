package com.apptraorchestratorapi.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.apptraorchestratorapi.enums.ErrorEnum;
import com.apptraorchestratorapi.exceptions.ApiException;

public class Pipe {

	
	
	
	public static String excecute(String command) {
		for(String c : command.split("\\|")) {
			if(c.split(" ")[0].equals("date")) {
				return DateUtils.getDateString(c.split(" ")[1]);
			}
		}
		return "";
	}
	
	
	
	public static void main(String[] args) {
		//String command = "date now|dateAdd 1|datepattern pattern yyyy-MM-dd";
		String command = "date yyyy-MM-dd";
		command = command.trim().toLowerCase();
		excecute(command);
		
    }
	
}
