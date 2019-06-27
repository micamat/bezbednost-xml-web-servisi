package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.CreateSobaRequest;
import ftn.uns.ac.rs.model.CreateSobaResponse;
import ftn.uns.ac.rs.model.SobaDTO;
import ftn.uns.ac.rs.service.SobaService;

@Endpoint
public class SobaEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	SobaService sobaService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateSobaRequest")
	public CreateSobaResponse create(@RequestPayload final CreateSobaRequest input) {
		CreateSobaResponse response = new CreateSobaResponse();
		SobaDTO sdto = input.getSobaDTO();
		int id = sobaService.create(sdto);
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
