package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.CreateKoordinateRequest;
import ftn.uns.ac.rs.model.CreateKoordinateResponse;
import ftn.uns.ac.rs.model.GetAllKoordinateRequest;
import ftn.uns.ac.rs.model.GetAllKoordinateResponse;
import ftn.uns.ac.rs.model.KoordinateDTO;
import ftn.uns.ac.rs.service.KoordinateService;

@Endpoint
public class KoordinateEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	KoordinateService koordinateService;
	
	//TODO: OVDE U GetAllSmestajResponse dodati setSuccessful za povratnu infoooo...
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllKoordinateRequest")
	public GetAllKoordinateResponse getAll(@RequestPayload final GetAllKoordinateRequest input) {
		GetAllKoordinateResponse response = new GetAllKoordinateResponse();
		response.setKoordinateDTO(koordinateService.getAll());
		return response;
	}
	
	/*@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateKoordinateRequest")
	public CreateKoordinateResponse create(@RequestPayload final CreateKoordinateRequest input) {
		CreateKoordinateResponse response = new CreateKoordinateResponse();
		KoordinateDTO sdto = new KoordinateDTO();
		sdto.setId(input.getId());
		sdto.setDuzina(input.getDuzina());
		sdto.setSirina(input.getSirina());
		int id = koordinateService.create(sdto);
		if(id == -1) {
			response.setSuccessful(false);
		}
		else {
			response.setId(id);
			response.setSuccessful(true);
		}
		return response;
	}*/
}
