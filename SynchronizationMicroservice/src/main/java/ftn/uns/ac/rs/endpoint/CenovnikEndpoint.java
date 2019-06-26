package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.CenovnikDTO;
import ftn.uns.ac.rs.model.CreateCenovnikRequest;
import ftn.uns.ac.rs.model.CreateCenovnikResponse;
import ftn.uns.ac.rs.model.GetAllCenovnikRequest;
import ftn.uns.ac.rs.model.GetAllCenovnikResponse;
import ftn.uns.ac.rs.service.CenovnikService;

@Endpoint
public class CenovnikEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	CenovnikService cenovnikService;
	
	//TODO: OVDE U GetAllSmestajResponse dodati setSuccessful za povratnu infoooo...
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllCenovnikRequest")
	public GetAllCenovnikResponse getAll(@RequestPayload final GetAllCenovnikRequest input) {
		GetAllCenovnikResponse response = new GetAllCenovnikResponse();
		response.setCenovnikDTO(cenovnikService.getAll());
		return response;
	}
	
	/*@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateCenovnikRequest")
	public CreateCenovnikResponse create(@RequestPayload final CreateCenovnikRequest input) {
		CreateCenovnikResponse response = new CreateCenovnikResponse();
		CenovnikDTO sdto = new CenovnikDTO();
		sdto.setId(input.getId());
		sdto.setDatumDo(input.getDatumDo());
		sdto.setDatumOd(input.getDatumOd());
		sdto.setIdSmestaj(input.getIdSmestaj());
		sdto.setIdTipSobe(input.getIdTipSobe());
		sdto.setCena(input.getCena());
		int id = cenovnikService.create(sdto);
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
