package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.CenovnikDTO;
import ftn.uns.ac.rs.model.CreateCenovnikRequest;
import ftn.uns.ac.rs.model.CreateCenovnikResponse;
import ftn.uns.ac.rs.service.CenovnikService;

@Endpoint
public class CenovnikEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	CenovnikService cenovnikService;

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateCenovnikRequest")
	public CreateCenovnikResponse create(@RequestPayload final CreateCenovnikRequest input) {
		CreateCenovnikResponse response = new CreateCenovnikResponse();
		CenovnikDTO sdto = input.getCenovnikDTO();
		
		System.out.println(sdto.getDatumDo() + " asf " + sdto.getDatumOd());
		int id = cenovnikService.create(sdto);
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
