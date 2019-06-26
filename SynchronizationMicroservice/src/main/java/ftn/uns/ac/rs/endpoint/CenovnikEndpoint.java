package ftn.uns.ac.rs.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class CenovnikEndpoint {
	/*final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	CenovnikService cenovnikService;
	
<<<<<<< HEAD
	@ResponsePayload
=======
	//TODO: OVDE U GetAllSmestajResponse dodati setSuccessful za povratnu infoooo...
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllCenovnikRequest")
	public GetAllCenovnikResponse getAll(@RequestPayload final GetAllCenovnikRequest input) {
		GetAllCenovnikResponse response = new GetAllCenovnikResponse();
		response.setCenovnikDTO(cenovnikService.getAll());
		return response;
	}
	
	/*@ResponsePayload
>>>>>>> 147187d6c5a3737c2907e5a56e61306e217af6eb
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateCenovnikRequest")
	public CreateCenovnikResponse create(@RequestPayload final CreateCenovnikRequest input) {
		CreateCenovnikResponse response = new CreateCenovnikResponse();
		CenovnikDTO sdto = new CenovnikDTO();
		sdto = input.getCenovnikDTO();
		int id = cenovnikService.create(sdto);
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
