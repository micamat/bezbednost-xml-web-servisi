package ftn.uns.ac.rs.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class TipSmestajaEndpoint {
	/*final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	TipSmestajaService TipSmestajaService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllTipSmestajaRequest")
	public GetAllTipSmestajaResponse getAll(@RequestPayload final GetAllTipSmestajaRequest input) {
		GetAllTipSmestajaResponse response = new GetAllTipSmestajaResponse();
		response.setTipSmestajaDTO(TipSmestajaService.getAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateTipSmestajaRequest")
	public CreateTipSmestajaResponse create(@RequestPayload final CreateTipSmestajaRequest input) {
		CreateTipSmestajaResponse response = new CreateTipSmestajaResponse();
		TipSmestajaDTO sdto = new TipSmestajaDTO();
		sdto.setId(input.getId());
		sdto.setNaziv(input.getNaziv());
		sdto.setOpis(input.getOpis());
		int id = TipSmestajaService.create(sdto);
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
