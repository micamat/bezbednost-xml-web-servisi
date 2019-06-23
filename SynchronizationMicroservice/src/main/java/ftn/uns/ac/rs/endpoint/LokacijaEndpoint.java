package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.CreateLokacijaRequest;
import ftn.uns.ac.rs.model.CreateLokacijaResponse;
import ftn.uns.ac.rs.model.GetAllLokacijaRequest;
import ftn.uns.ac.rs.model.GetAllLokacijaResponse;
import ftn.uns.ac.rs.model.LokacijaDTO;
import ftn.uns.ac.rs.service.LokacijaService;

@Endpoint
public class LokacijaEndpoint {
final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	LokacijaService lokacijaService;
	
	//TODO: OVDE U GetAllSmestajResponse dodati setSuccessful za povratnu infoooo...
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllLokacijaRequest")
	public GetAllLokacijaResponse getAll(@RequestPayload final GetAllLokacijaRequest input) {
		GetAllLokacijaResponse response = new GetAllLokacijaResponse();
		response.setLokacijaDTO(lokacijaService.getAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateLokacijaRequest")
	public CreateLokacijaResponse create(@RequestPayload final CreateLokacijaRequest input) {
		CreateLokacijaResponse response = new CreateLokacijaResponse();
		LokacijaDTO sdto = new LokacijaDTO();
		sdto.setId(input.getId());
		sdto.setGrad(input.getGrad());
		sdto.setDrzava(input.getDrzava());
		sdto.setUlica(input.getUlica());
		sdto.setBroj(input.getBroj());
		sdto.setIdKoordinate(input.getIdKoordinate());
		int id = lokacijaService.create(sdto);
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
