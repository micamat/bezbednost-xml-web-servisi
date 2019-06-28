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

import ftn.uns.ac.rs.configuration.Username;
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

	static final String URL = "http://localhost:8765/auth/prijava";

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "AgentLoginRequest")
	public AgentLoginResponse getAll(@RequestPayload final AgentLoginRequest input) {
		RestTemplate restTemplate = new RestTemplate();
		AgentLoginDTO agent = new AgentLoginDTO(input.getusername(), input.getpassword());
		
		AgentLoginResponse response = new AgentLoginResponse();
		
		System.out.println("Jel tu neka greska ?");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AgentLoginDTO> requestBody = new HttpEntity<AgentLoginDTO>(agent, headers);
		LoggedUser user = restTemplate.postForObject(URL, requestBody, LoggedUser.class);
		if (user != null) {
			Username.setLoggedUser(user.getUsername());
		} else {
			Username.setLoggedUser(null);
		}
		response.setUsername(user.getUsername());
		response.setToken(user.getToken());
		System.out.println("dsad " + response.getToken());
		System.out.println("ispisi molim te: " + response.getUsername());
		return response;
	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "UpdateAgentRequest")
	public UpdateAgentResponse update(@RequestPayload final UpdateAgentRequest input) {
		UpdateAgentResponse response = new UpdateAgentResponse();
		AgentDTO d = input.getAgentDTO();
		int id = agentService.update(d);
		if (id == -1) {
			response.setSuccessful(false);
		} else {
			response.setId(id);
			response.setSuccessful(true);
		}
		return response;
	}

}
