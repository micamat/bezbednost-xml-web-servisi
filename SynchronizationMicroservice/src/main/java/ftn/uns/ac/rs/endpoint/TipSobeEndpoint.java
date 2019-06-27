package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.GetAllTipSobeRequest;
import ftn.uns.ac.rs.model.GetAllTipSobeResponse;
import ftn.uns.ac.rs.service.TipSobeService;

@Endpoint
public class TipSobeEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	TipSobeService TipSobeService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllTipSobeRequest")
	public GetAllTipSobeResponse getAll(@RequestPayload final GetAllTipSobeRequest input) {
		GetAllTipSobeResponse response = new GetAllTipSobeResponse();
		response.setTipSobe(TipSobeService.getAll());
		return response;
	}
	
	
}
