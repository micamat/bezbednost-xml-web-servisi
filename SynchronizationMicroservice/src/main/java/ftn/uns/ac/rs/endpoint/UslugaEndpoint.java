package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.CreateUslugaRequest;
import ftn.uns.ac.rs.model.CreateUslugaResponse;
import ftn.uns.ac.rs.model.GetAllUslugaRequest;
import ftn.uns.ac.rs.model.GetAllUslugaResponse;
import ftn.uns.ac.rs.model.UslugaDTO;
import ftn.uns.ac.rs.service.UslugaService;

@Endpoint
public class UslugaEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	UslugaService UslugaService;
	
	/*@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllUslugaRequest")
	public GetAllUslugaResponse getAll(@RequestPayload final GetAllUslugaRequest input) {
		GetAllUslugaResponse response = new GetAllUslugaResponse();
		response.setUslugaDTO(UslugaService.getAll());
		return response;
	}*/
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateUslugaRequest")
	public CreateUslugaResponse create(@RequestPayload final CreateUslugaRequest input) {
		CreateUslugaResponse response = new CreateUslugaResponse();
		UslugaDTO sdto = new UslugaDTO();
		sdto.setId(input.getId());
		sdto.setNaziv(input.getNaziv());
		sdto.setOpis(input.getOpis());
		int id = UslugaService.create(sdto);
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
