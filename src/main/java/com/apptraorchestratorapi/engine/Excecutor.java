package com.apptraorchestratorapi.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import com.apptraorchestratorapi.dto.ResponseDTO;
import com.apptraorchestratorapi.enums.ParamCollectStrategyEnum;
import com.apptraorchestratorapi.enums.ServiceTypeEnum;
import com.apptraorchestratorapi.exceptions.ApiException;
import com.apptraorchestratorapi.model.HelloMessage;
import com.apptraorchestratorapi.model.Process;
import com.apptraorchestratorapi.model.Service;
import com.apptraorchestratorapi.model.Transaction;
import com.apptraorchestratorapi.repository.ProcessRepository;
import com.apptraorchestratorapi.repository.TransactionRepository;
import com.apptraorchestratorapi.services.ApigeeService;
import com.apptraorchestratorapi.util.GSonUtils;

@Component
public class Excecutor {

	

	@Autowired
	ProcessRepository processRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	ApigeeService apigeeService;
	

	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	
	/**
	 * Ejecuta un listado de servicios
	 * 
	 * @param apigeeService
	 * @param transactionRepository
	 * @param messagingTemplate 
	 * @param process
	 * @return
	 */
	public List<ResponseDTO> run(Process process) {

		String transactionId = UUID.randomUUID().toString();
		List<ResponseDTO> responses = new ArrayList<ResponseDTO>();
		List<Transaction> transactions = new ArrayList<Transaction>();

		Collections.sort(process.getServices());
		for (Service service : process.getServices()) {

			if (service.isEnabled()) {
				ResponseDTO responseService = new ResponseDTO();
				Transaction transaction = new Transaction();
				transaction.setServiceName(service.getServiceName());
				transaction.setApiPath(service.getApiPath());
				transaction.setEndpoint(service.getEndpoint());
				String params = "";

				if (service.getParamCollectStrategy().equals(ParamCollectStrategyEnum.JOIN_MERGE)) {
					JSONObject obj1 = null;
					if (service.getParams() == null || service.getParams().isEmpty()) {
						obj1 = new JSONObject();
					} else {
						obj1 = new JSONObject(service.getParams());
					}

					JSONObject obj2 = null;
					for (Transaction trx : transactions) {
						obj2 = new JSONObject((trx.getResponseBody() == null || trx.getResponseBody().isEmpty()) ? "{}":trx.getResponseBody());
						obj1 = ExcecutorHelper.mergeJSONObjects(obj1, obj2);
					}
					params = GSonUtils.serialize(obj1.toMap());
				} else if (service.getParamCollectStrategy().equals(ParamCollectStrategyEnum.NONE)) {
					params = service.getParams();
				}
				
				try {
					
					transaction.setIndexes(ExcecutorHelper.getIndexes(params));
					
					ResponseEntity<String> responseEntity = null;
					String headersString="";
					if (service.getType().equals(ServiceTypeEnum.REST)) {
						String body = ExcecutorHelper.replaceParams(service.getApiBody(), params);
						transaction.setRequestBody(body);
						
						Map<String, String> headers = ExcecutorHelper.getHeaders(service.getApiHeaders(), params);
						transaction.setRequestHeaders(GSonUtils.serialize(headers));
						
						String apiPath = ExcecutorHelper.replaceParams(service.getApiPath(), params);
						service.setApiPath(apiPath);
						
						responseEntity = apigeeService.call(body, service.getEndpoint(), apiPath,
								service.getMethod(), headers, service.getTimeout());
						
						transaction.setResponseBody(responseEntity.getBody());
						transaction.setStatus(responseEntity.getStatusCodeValue());
					}
				} catch (ApiException e) {
					transaction.setResponseBody(
							"{\"error\":\"" + HtmlUtils.htmlEscape(e.getError().toString()) + " - " + HtmlUtils.htmlEscape(e.getMessage()) + "\"}");
					transaction.setStatus(e.getError().getHttpCode().value());
				} catch (Exception e) {
					transaction.setResponseBody(
							"{\"error\":\"" + HtmlUtils.htmlEscape(e.getMessage()) + "\"}");
					transaction.setStatus(500);
				} finally {
					transaction.setTransactionId(transactionId);
					transaction.setCreateDate(new Date());
					transaction.setParameters(params);
					transaction.setMethod(service.getMethod());
					responseService.setTransactionId(transactionId);
					responseService.setStatus(transaction.getStatus());
					responseService.setService(service.getDescription());
					responseService.setResponse(transaction.getResponseBody());
					transaction.setResponseIndexes(ExcecutorHelper.getIndexes(transaction.getResponseBody()));
					
					responses.add(responseService);
					transactions.add(transaction);
					
					transactionRepository.save(transaction);

					if (responseService.getStatus() == 200 && service.getSuccessPattern() != null && transaction.getResponseBody() != null && !transaction.getResponseBody().contains(service.getSuccessPattern())) {
						responseService.setStatus(500);
					}

					messagingTemplate.convertAndSend("/topic/greetings", new HelloMessage(responseService.getResponse(),service.getDescription(),responseService.getStatus()));
					
					if (responseService.getStatus() != 200 && service.isMandatory()) {
						return responses;
					}
					
					
				}
			}

		}

		return responses;
	}

}
