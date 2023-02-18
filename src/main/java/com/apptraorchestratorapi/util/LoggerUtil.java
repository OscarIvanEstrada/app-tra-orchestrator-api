package com.apptraorchestratorapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import brave.Tracer;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class LoggerUtil {

	@Value("${spring.application.name}")
	private String app;
	@Value("${spring.application.version}")
	private String version;

	@Autowired
	Tracer tracer;

	@Async
	public void info(String message) {
		log.info(message);
	}

	@Async
	public void error(String message) {
		log.error(message);
	}
}
