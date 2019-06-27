package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.GetAllKomentarRequest;
import ftn.uns.ac.rs.model.GetAllKomentarResponse;
import ftn.uns.ac.rs.model.GetAllTipSobeResponse;
import ftn.uns.ac.rs.service.KomentarService;

@Endpoint
public class KomentarEndpoint {
final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	KomentarService komentarService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllKomentarRequest")
	public GetAllKomentarResponse getAll(@RequestPayload final GetAllKomentarRequest input) {
		GetAllKomentarResponse response = new GetAllKomentarResponse();
		response.setKomentarDTO(komentarService.getAll());
		return response;
	}
	
}
