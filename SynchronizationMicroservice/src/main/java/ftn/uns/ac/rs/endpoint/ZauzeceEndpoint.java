package ftn.uns.ac.rs.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class ZauzeceEndpoint {
	/*final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
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
	}*/
}
