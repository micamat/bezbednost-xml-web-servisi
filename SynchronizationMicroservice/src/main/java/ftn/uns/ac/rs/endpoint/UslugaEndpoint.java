package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.GetAllUslugaRequest;
import ftn.uns.ac.rs.model.GetAllUslugaResponse;
import ftn.uns.ac.rs.service.UslugaService;

@Endpoint
public class UslugaEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	UslugaService UslugaService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllUslugaRequest")
	public GetAllUslugaResponse getAll(@RequestPayload final GetAllUslugaRequest input) {
		GetAllUslugaResponse response = new GetAllUslugaResponse();
		response.setUsluga(UslugaService.getAll());
		return response;
	}

}
