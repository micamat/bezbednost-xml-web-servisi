package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.CreateSobaRequest;
import ftn.uns.ac.rs.model.CreateSobaResponse;
import ftn.uns.ac.rs.model.GetAllSobaRequest;
import ftn.uns.ac.rs.model.GetAllSobaResponse;
import ftn.uns.ac.rs.model.SobaDTO;
import ftn.uns.ac.rs.service.SobaService;

@Endpoint
public class SobaEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	SobaService sobaService;
	
	//TODO: OVDE U GetAllSmestajResponse dodati setSuccessful za povratnu infoooo...
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllSobaRequest")
	public GetAllSobaResponse getAll(@RequestPayload final GetAllSobaRequest input) {
		GetAllSobaResponse response = new GetAllSobaResponse();
		response.setSobaDTO(sobaService.getAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateSobaRequest")
	public CreateSobaResponse create(@RequestPayload final CreateSobaRequest input) {
		CreateSobaResponse response = new CreateSobaResponse();
		SobaDTO sdto = new SobaDTO();
		sdto.setId(input.getId());
		sdto.setNaziv(input.getNaziv());
		sdto.setOpis(input.getOpis());
		sdto.setSlika(input.getSlika());
		sdto.setIdSmestaj(input.getIdSmestaj());
		sdto.setIdTipSobe(input.getIdTipSobe());
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
