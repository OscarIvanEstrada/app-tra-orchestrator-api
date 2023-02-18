package com.apptraorchestratorapi;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableAsync
@RestController
@CrossOrigin(origins = "*")
public class Application {

   @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
    }

	public static void main(String[ ]  args) {
		SpringApplication.run(Application.class, args);
	}
}
