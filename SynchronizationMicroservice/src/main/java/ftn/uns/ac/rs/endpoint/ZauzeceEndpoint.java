package ftn.uns.ac.rs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.CreateZauzeceRequest;
import ftn.uns.ac.rs.model.CreateZauzeceResponse;
import ftn.uns.ac.rs.model.GetAllZauzeceRequest;
import ftn.uns.ac.rs.model.GetAllZauzeceResponse;
import ftn.uns.ac.rs.model.ZauzeceDTO;
import ftn.uns.ac.rs.service.ZauzeceService;

@Endpoint
public class ZauzeceEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	ZauzeceService zauzeceService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllZauzeceRequest")
	public GetAllZauzeceResponse getAll(@RequestPayload final GetAllZauzeceRequest input) {
		GetAllZauzeceResponse response = new GetAllZauzeceResponse();
		response.setZauzeceDTO(zauzeceService.getAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateZauzeceRequest")
	public CreateZauzeceResponse create(@RequestPayload final CreateZauzeceRequest input) {
		CreateZauzeceResponse response = new CreateZauzeceResponse();
		ZauzeceDTO sdto = new ZauzeceDTO();
		sdto.setId(input.getId());
		sdto.setDatumDo(input.getDatumDo());
		sdto.setDatumOd(input.getDatumOd());
		sdto.setIdSoba(input.getIdSoba());
		sdto.setIdStatusSobe(input.getIdStatusSobe());
		int id = zauzeceService.create(sdto);
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
