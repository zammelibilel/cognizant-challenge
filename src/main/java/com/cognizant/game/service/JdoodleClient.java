package com.cognizant.game.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cognizant.game._config.JdoodleConfig;
import com.cognizant.game.dto.ExecuteRequest;
import com.cognizant.game.dto.ExecuteResponse;

@Service
public class JdoodleClient {

	Logger logger = LogManager.getLogger(JdoodleClient.class);

	@Autowired
	private JdoodleConfig jdoodleConfig;

	@Autowired
	private RestTemplate restTemplate;

	public ExecuteResponse executeTest(ExecuteRequest testInput) {

		ExecuteResponse result = null;

		try {

			testInput.setClientId(jdoodleConfig.getClientId());
			testInput.setClientSecret(jdoodleConfig.getClientSecret());

			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", "application/json");
			headers.add("Content-Type", "application/json");
			headers.add("user-agent", "Application");
			
			HttpEntity<ExecuteRequest> request = new HttpEntity<ExecuteRequest>(testInput, headers);

			ResponseEntity<ExecuteResponse> response = restTemplate.postForEntity(jdoodleConfig.getBaseUrl(), request,
					ExecuteResponse.class);

			HttpStatus status = response.getStatusCode();

			if (!HttpStatus.OK.equals(status)) {
				logger.error("> Execution failed for [Script: {}, Language: {}]", testInput.getScript(),
						testInput.getLanguage());
				throw new RestClientException("Execution failed for [Script: {}, Language: {}]");

			}

			logger.info("> Success execution for [Script: {}, Language: {}]", testInput.getScript(),
					testInput.getLanguage());
			result = response.getBody();

		} catch (Exception e) {
			
			logger.error("> Error when executing script using doodle API [Script: {}, Language: {}]",
					testInput.getScript(), testInput.getLanguage());
			throw e;
		}

		return result;
	}

}
