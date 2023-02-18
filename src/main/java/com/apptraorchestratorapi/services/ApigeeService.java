package com.apptraorchestratorapi.services;

import java.net.SocketTimeoutException;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.apptraorchestratorapi.enums.ErrorEnum;
import com.apptraorchestratorapi.exceptions.ApiException;

@Component
public class ApigeeService {


	public ResponseEntity<String> call(String request, String url, String path, HttpMethod method,
			Map<String, String> headers, int timeout) {

		if (url == null || url.isEmpty()) {
			throw new ApiException(ErrorEnum.ORCHESTRATOR_SERVICE_URL, "");
		}
		if (path == null || path.isEmpty()) {
			throw new ApiException(ErrorEnum.ORCHESTRATOR_SERVICE_RESOURCE, "");
		}

		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
			restTemplate.setErrorHandler(new MyErrorHandler());

			HttpHeaders h = new HttpHeaders();
			if(headers != null) {
				for (String i : headers.keySet()) {
					if (headers.get(i) != null) {
						h.add(i, headers.get(i));
					}
				}
			}

			byte[] myBytes = null;
			if(request != null) {
				myBytes = request.getBytes("UTF-8");
			}else {
				myBytes = "".getBytes("UTF-8");
			}

			UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url + path);
			HttpEntity<String> entity = new HttpEntity<String>(new String(myBytes), h);
			
			HttpComponentsClientHttpRequestFactory rf =
				       (HttpComponentsClientHttpRequestFactory) restTemplate.getRequestFactory();
				rf.setReadTimeout(timeout * 1000);
				rf.setConnectTimeout(timeout * 1000);
				restTemplate.setRequestFactory(rf);
			
			ResponseEntity<String> result = restTemplate.exchange(uri.toUriString(), method, entity, String.class);

			return result;
		} catch (Exception e) {
			if (e.getCause() instanceof SocketTimeoutException) {
				throw new ApiException(ErrorEnum.ORCHESTRATOR_SERVICE_TIMEOUT, e.getMessage());
			}
			throw new ApiException(ErrorEnum.APIGEE_ERROR, e.getMessage());
		}
	}

}
