package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.GetAllAgentRequest;
import ftn.uns.ac.rs.model.GetAllAgentResponse;
import ftn.uns.ac.rs.service.AgentService;

@Endpoint
public class AgentEndpoint {
final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	AgentService agentService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllAgentRequest")
	public GetAllAgentResponse getAll(@RequestPayload final GetAllAgentRequest input) {
		GetAllAgentResponse response = new GetAllAgentResponse();
		response.setAgent(agentService.getAll());
		return response;
	}
	
}
