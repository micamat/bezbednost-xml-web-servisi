package ftn.uns.ac.rs.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class TipSobeEndpoint {
	/*final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	TipSobeService TipSobeService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllTipSobeRequest")
	public GetAllTipSobeResponse getAll(@RequestPayload final GetAllTipSobeRequest input) {
		GetAllTipSobeResponse response = new GetAllTipSobeResponse();
		response.setTipSobeDTO(TipSobeService.getAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateTipSobeRequest")
	public CreateTipSobeResponse create(@RequestPayload final CreateTipSobeRequest input) {
		CreateTipSobeResponse response = new CreateTipSobeResponse();
		TipSobeDTO sdto = new TipSobeDTO();
		sdto.setId(input.getId());
		sdto.setNaziv(input.getNaziv());
		sdto.setOpis(input.getOpis());
		int id = TipSobeService.create(sdto);
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
