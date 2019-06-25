package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.AgentLoginRequest;
import ftn.uns.ac.rs.model.AgentLoginResponse;

@Endpoint
public class AgentLoginEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	RestTemplate restTemplate;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "AgentLoginRequest")
	public AgentLoginResponse getAll(@RequestPayload final AgentLoginRequest input) {
		AgentLoginResponse response = new AgentLoginResponse();
		String token = restTemplate.postForObject("http://localhost:8765/auth/prijava?username=" + input.getusername() + "&password=" + input.getpassword(), null, String.class);
		response.setToken(token);
		return response;
	}
	
}
