package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.GetAllKategorijaSmestajaRequest;
import ftn.uns.ac.rs.model.GetAllKategorijaSmestajaResponse;
import ftn.uns.ac.rs.service.KategorijaSmestajaService;

@Endpoint
public class KategorijaSmestajaEndpoint {
final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	KategorijaSmestajaService kategorijaSmestajaService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllKategorijaSmestajaRequest")
	public GetAllKategorijaSmestajaResponse getAll(@RequestPayload final GetAllKategorijaSmestajaRequest input) {
		GetAllKategorijaSmestajaResponse response = new GetAllKategorijaSmestajaResponse();
		response.setKategorijaSmestaja(kategorijaSmestajaService.getAll());
		return response;
	}
	

}
