package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.CreateKategorijaSmestajaRequest;
import ftn.uns.ac.rs.model.CreateKategorijaSmestajaResponse;
import ftn.uns.ac.rs.model.GetAllKategorijaSmestajaRequest;
import ftn.uns.ac.rs.model.GetAllKategorijaSmestajaResponse;
import ftn.uns.ac.rs.model.KategorijaSmestajaDTO;
import ftn.uns.ac.rs.service.KategorijaSmestajaService;

@Endpoint
public class KategorijaSmestajaEndpoint {
final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	KategorijaSmestajaService kategorijaSmestajaService;
	
	/*//TODO: OVDE U GetAllSmestajResponse dodati setSuccessful za povratnu infoooo...
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllKategorijaSmestajaRequest")
	public GetAllKategorijaSmestajaResponse getAll(@RequestPayload final GetAllKategorijaSmestajaRequest input) {
		GetAllKategorijaSmestajaResponse response = new GetAllKategorijaSmestajaResponse();
		response.setKategorijaSmestajaDTO(kategorijaSmestajaService.getAll());
		return response;
	}*/
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateKategorijaSmestajaRequest")
	public CreateKategorijaSmestajaResponse create(@RequestPayload final CreateKategorijaSmestajaRequest input) {
		CreateKategorijaSmestajaResponse response = new CreateKategorijaSmestajaResponse();
		KategorijaSmestajaDTO sdto = new KategorijaSmestajaDTO();
		sdto.setId(input.getId());
		sdto.setNaziv(input.getNaziv());
		sdto.setOpis(input.getOpis());
		int id = kategorijaSmestajaService.create(sdto);
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
