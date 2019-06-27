package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.CreateSmestajRequest;
import ftn.uns.ac.rs.model.CreateSmestajResponse;
import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.service.SmestajService;

@Endpoint
public class SmestajEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	SmestajService smestajService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateSmestajRequest")
	public CreateSmestajResponse create(@RequestPayload final CreateSmestajRequest input) {
		System.out.println(input.getSmestajDTO().getGrad() + " fa " + input.getSmestajDTO().getUlica());
		System.out.println(input.getSmestajDTO().getIdKategorijaSmestaja() + " fa " + input.getSmestajDTO().getNaziv());
		System.out.println(input.getSmestajDTO().getSlika() + " fa " + input.getSmestajDTO().getOpis());
		System.out.println(input.getSmestajDTO().getIdAgent() + " fa " + input.getSmestajDTO().getIdTipSmestaja());
		CreateSmestajResponse response = new CreateSmestajResponse();
		
		SmestajDTO sdto = input.getSmestajDTO();
		int id = smestajService.create(sdto);
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
