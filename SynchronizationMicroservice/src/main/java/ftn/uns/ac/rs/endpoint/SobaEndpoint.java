package ftn.uns.ac.rs.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class SobaEndpoint {
	/*final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	SobaService sobaService;
	
	//TODO: OVDE U GetAllSmestajResponse dodati setSuccessful za povratnu infoooo...
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllSobaRequest")
	public GetAllSobaResponse getAll(@RequestPayload final GetAllSobaRequest input) {
		GetAllSobaResponse response = new GetAllSobaResponse();
		response.setSobaDTO(sobaService.getAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "CreateSobaRequest")
	public CreateSobaResponse create(@RequestPayload final CreateSobaRequest input) {
		CreateSobaResponse response = new CreateSobaResponse();
		SobaDTO sdto = new SobaDTO();
		sdto.setId(input.getId());
		sdto.setNaziv(input.getNaziv());
		sdto.setOpis(input.getOpis());
		sdto.setSlika(input.getSlika());
		sdto.setIdSmestaj(input.getIdSmestaj());
		sdto.setIdTipSobe(input.getIdTipSobe());
		int id = sobaService.create(sdto);
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
