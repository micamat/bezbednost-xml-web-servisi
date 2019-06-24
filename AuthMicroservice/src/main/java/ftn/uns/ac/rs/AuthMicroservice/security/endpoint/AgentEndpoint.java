package ftn.uns.ac.rs.AuthMicroservice.security.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.AuthMicroservice.security.model.AgentLoginRequest;
import ftn.uns.ac.rs.AuthMicroservice.security.model.AgentLoginResponse;
import ftn.uns.ac.rs.AuthMicroservice.security.service.KorisnikService;

@Endpoint
public class AgentEndpoint {
	final String NAMESPACE = "http://AuthMicroservice.rs.ac.uns.ftn/security/Model";
	
	@Autowired
	KorisnikService korisnikService;
	
	//TODO: OVDE U GetAllSmestajResponse dodati setSuccessful za povratnu infoooo...
		@ResponsePayload
		@PayloadRoot(namespace = NAMESPACE, localPart = "AgentLoginRequest")
		public AgentLoginResponse agentLogin(@RequestPayload final AgentLoginRequest input) {
			AgentLoginResponse response = new AgentLoginResponse();
			response.setToken(korisnikService.signin(input.getKorisnickoIme(), input.getLozinka()));
			return response;
		}
}
