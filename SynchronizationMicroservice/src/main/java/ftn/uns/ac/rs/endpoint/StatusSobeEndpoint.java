package ftn.uns.ac.rs.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class StatusSobeEndpoint {
	/*final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	StatusSobeService statusSobeService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllStatusSobeRequest")
	public GetAllStatusSobeResponse getAll(@RequestPayload final GetAllStatusSobeRequest input) {
		GetAllStatusSobeResponse response = new GetAllStatusSobeResponse();
		response.setStatusSobeDTO(statusSobeService.getAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateStatusSobeRequest")
	public CreateStatusSobeResponse create(@RequestPayload final CreateStatusSobeRequest input) {
		CreateStatusSobeResponse response = new CreateStatusSobeResponse();
		StatusSobeDTO sdto = new StatusSobeDTO();
		sdto.setId(input.getId());
		sdto.setNaziv(input.getNaziv());
		sdto.setOpis(input.getOpis());
		int id = statusSobeService.create(sdto);
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
