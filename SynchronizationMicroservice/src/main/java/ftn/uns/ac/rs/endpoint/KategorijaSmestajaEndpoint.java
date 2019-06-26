package ftn.uns.ac.rs.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class KategorijaSmestajaEndpoint {
final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	/*@Autowired
	KategorijaSmestajaService kategorijaSmestajaService;
	

	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateKategorijaSmestajaRequest")
	public CreateKategorijaSmestajaResponse create(@RequestPayload final CreateKategorijaSmestajaRequest input) {
		CreateKategorijaSmestajaResponse response = new CreateKategorijaSmestajaResponse();
		KategorijaSmestajaDTO sdto = new KategorijaSmestajaDTO();
		sdto.setId(input.getId());
		sdto.setNaziv(input.getNaziv());
		sdto.setOpis(input.getOpis());
		int id = kategorijaSmestajaService.create(sdto);
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
