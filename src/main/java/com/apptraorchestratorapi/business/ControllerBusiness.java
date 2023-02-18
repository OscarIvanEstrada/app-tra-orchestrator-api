package com.apptraorchestratorapi.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import com.apptraorchestratorapi.dto.ResponseDTO;
import com.apptraorchestratorapi.engine.Excecutor;
import com.apptraorchestratorapi.enums.ErrorEnum;
import com.apptraorchestratorapi.exceptions.ApiException;
import com.apptraorchestratorapi.model.Process;
import com.apptraorchestratorapi.repository.ProcessRepository;
import com.apptraorchestratorapi.repository.TransactionRepository;
import com.apptraorchestratorapi.services.ApigeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class ControllerBusiness {

	@Autowired
	ProcessRepository processRepository;
	
	@Autowired
	Excecutor excecutor;
	

	@HystrixCommand(fallbackMethod = "reliable")
	public List<Process> findServiceByName(String service) {
		return (List<Process>) processRepository.findProcessProcessName(service);
	}

	public List<Process> reliable() {
		return new ArrayList<>();
	}

	public List<ResponseDTO> excecute(String processName) {

		Process process = processRepository.findProcessProcessName(processName);

		if (process == null || process.getServices() == null || process.getServices().size() == 0) {
			throw new ApiException(ErrorEnum.ORCHESTRATOR_SERVICE, "");
		}

		return excecutor.run(process);
	}

	public Process saveProcess(Process process) {
		return processRepository.save(process);
	}

	public Process findProcess(String processName) {
		return processRepository.findProcessProcessName(processName);
	}
	
	public List<Process> findAllProcess() {
		return processRepository.findAll();
	}

}
