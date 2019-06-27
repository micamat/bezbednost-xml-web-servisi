package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.AgentDTO;
import ftn.uns.ac.rs.model.AgentLoginRequest;
import ftn.uns.ac.rs.model.AgentLoginResponse;
import ftn.uns.ac.rs.model.UpdateAgentRequest;
import ftn.uns.ac.rs.model.UpdateAgentResponse;
import ftn.uns.ac.rs.service.AgentService;

@Endpoint
public class AgentLoginEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AgentService agentService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "AgentLoginRequest")
	public AgentLoginResponse getAll(@RequestPayload final AgentLoginRequest input) {
		System.out.println("STIGO DOVDE :)");
		AgentLoginResponse response = new AgentLoginResponse();
		String token = restTemplate.postForObject("https://localhost:8765/auth/prijava?username=" + input.getusername() + "&password=" + input.getpassword(), null, String.class);
		response.setToken(token);
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "UpdateAgentRequest")
	public UpdateAgentResponse update(@RequestPayload final UpdateAgentRequest input) {
		UpdateAgentResponse response = new UpdateAgentResponse();
		
		AgentDTO d = input.getAgentDTO();
		int id = agentService.update(d);
		if(id == -1) {
			response.setSuccessful(false);
		}
		else {
			response.setId(id);
			response.setSuccessful(true);
		}
		return response;
	}
	
}
