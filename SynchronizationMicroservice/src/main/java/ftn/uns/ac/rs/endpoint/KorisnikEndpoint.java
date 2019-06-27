package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.GetAllKorisnikRequest;
import ftn.uns.ac.rs.model.GetAllKorisnikResponse;
import ftn.uns.ac.rs.service.KorisnikService;

@Endpoint
public class KorisnikEndpoint {
final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	KorisnikService korisnikService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllKorisnikRequest")
	public GetAllKorisnikResponse getAll(@RequestPayload final GetAllKorisnikRequest input) {
		GetAllKorisnikResponse response = new GetAllKorisnikResponse();
		response.setKorisnik(korisnikService.getAll());
		return response;
	}
}
