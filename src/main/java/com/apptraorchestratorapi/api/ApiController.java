package com.apptraorchestratorapi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.apptraorchestratorapi.business.ControllerBusiness;
import com.apptraorchestratorapi.dto.ResponseDTO;
import com.apptraorchestratorapi.model.Greeting;
import com.apptraorchestratorapi.model.HelloMessage;
import com.apptraorchestratorapi.model.Process;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@ApiResponses(value = { @ApiResponse(code = 200, message = "Accion exitosa"),
		@ApiResponse(code = 500, message = "Internal Server Error") })
@Api(value = "app-tra-orchestrator-api ApiController", description = "Orchestrator", tags = { "", "" })
public class ApiController {

	@Autowired
	ControllerBusiness controllerBusiness;

	@Value("${spring.application.version}")
	private String version;
	
	
	@GetMapping("version")
	public ResponseEntity<String> version() throws Exception {
		return new ResponseEntity(version, HttpStatus.OK);
	}

	@PostMapping("excecute")
	public ResponseEntity<ResponseDTO> excecute(@RequestBody String processName) {
		return new ResponseEntity(controllerBusiness.excecute(processName), HttpStatus.OK);
	}

	@PutMapping("process")
	public ResponseEntity<Process> saveProcess(@RequestBody Process process) {
		return new ResponseEntity(controllerBusiness.saveProcess(process), HttpStatus.OK);
	}

	@GetMapping("process/{processName}")
	public ResponseEntity<Process> findProcess(@PathVariable("processName") String processName) {
		return new ResponseEntity(controllerBusiness.findProcess(processName), HttpStatus.OK);
	}
	
	@GetMapping("process")
	public ResponseEntity<List<Process>> findAllProcess() {
		return new ResponseEntity(controllerBusiness.findAllProcess(), HttpStatus.OK);
	}
	

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting(HtmlUtils.htmlEscape(message.getResponse()));
    }
}
