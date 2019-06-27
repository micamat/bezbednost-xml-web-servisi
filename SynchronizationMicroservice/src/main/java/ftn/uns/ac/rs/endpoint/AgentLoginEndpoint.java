package ftn.uns.ac.rs.endpoint;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.AgentDTO;
import ftn.uns.ac.rs.model.AgentLoginDTO;
import ftn.uns.ac.rs.model.AgentLoginRequest;
import ftn.uns.ac.rs.model.AgentLoginResponse;
import ftn.uns.ac.rs.model.LoggedUser;
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
		RestTemplate restTemplate = new RestTemplate();
		AgentLoginDTO agent = new AgentLoginDTO(input.getusername(), input.getpassword());
		
		//HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<AgentLoginDTO> requestEntity = new HttpEntity<>(agent, null);

		AgentLoginResponse loggedUser = restTemplate.postForObject("http://localhost:8765/auth/prijava", requestEntity, AgentLoginResponse.class);
		System.out.println("da vidimo: " + loggedUser.getUsername() + ", token: " + loggedUser.getToken());
		
		return loggedUser;
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
