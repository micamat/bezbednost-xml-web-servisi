package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.ValidateTokenRequest;
import ftn.uns.ac.rs.model.ValidateTokenResponse;

@Endpoint
public class ValidateTokenEndpoint {
final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	RestTemplate restTemplate;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "ValidateTokenRequest")
	public ValidateTokenResponse validateToken(@RequestPayload final ValidateTokenRequest input) {
		ValidateTokenResponse response = new ValidateTokenResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+ input.getToken());

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Boolean isValid = restTemplate.postForObject("https://localhost:8765/validate", entity, Boolean.class);
		response.setSuccessful(isValid);
		return response;
	}
}
