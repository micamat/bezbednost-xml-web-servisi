package ftn.uns.ac.rs.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.CreateSmestajRequest;
import ftn.uns.ac.rs.model.CreateSmestajResponse;
import ftn.uns.ac.rs.model.GetAllSmestajRequest;
import ftn.uns.ac.rs.model.GetAllSmestajResponse;
import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.service.SmestajService;

@Endpoint
public class SmestajServiceEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	SmestajService smestajService;
	
	//TODO: OVDE U GetAllSmestajResponse dodati setSuccessful za povratnu infoooo...
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllSmestajRequest")
	public GetAllSmestajResponse getAll(@RequestPayload final GetAllSmestajRequest input) {
		GetAllSmestajResponse response = new GetAllSmestajResponse();
		response.setSmestajDTO(smestajService.getAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateSmestajRequest")
	public CreateSmestajResponse create(@RequestPayload final CreateSmestajRequest input) {
		CreateSmestajResponse response = new CreateSmestajResponse();
		SmestajDTO sdto = new SmestajDTO();
		sdto.setId(input.getId());
		sdto.setIdKategorijaSmestaja(input.getIdKategorijaSmestaja());
		sdto.setIdLokacija(input.getIdLokacija());
		sdto.setIdTipSmestaja(input.getIdTipSmestaja());
		sdto.setNaziv(input.getNaziv());
		sdto.setOpis(input.getOpis());
		sdto.setSlika(input.getSlika());
		int id = smestajService.create(sdto);
		if(id == -1)
			response.setSuccessful(false);
		else {
			response.setId(id);
			response.setSuccessful(false);
		}
		return response;
	}
	
	
	
	
}
