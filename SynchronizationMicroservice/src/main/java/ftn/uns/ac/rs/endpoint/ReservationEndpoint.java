package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.GetAllRezervacijaResponse;
import ftn.uns.ac.rs.model.GetAllRezervacijaRequest;
import ftn.uns.ac.rs.service.ReservationService;

@Endpoint
public class ReservationEndpoint {
final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	ReservationService reservationService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllRezervacijaRequest")
	public GetAllRezervacijaResponse getAll(@RequestPayload final GetAllRezervacijaRequest input) {
		GetAllRezervacijaResponse response = new GetAllRezervacijaResponse();
		response.setRezervacija(reservationService.getAll());
		System.out.println(response.getRezervacija() + "ASDSDD");
		return response;
	}

}
