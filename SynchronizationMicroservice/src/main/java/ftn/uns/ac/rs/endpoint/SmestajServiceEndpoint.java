package ftn.uns.ac.rs.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ftn.uns.ac.rs.model.GetAllSmestajRequest;
import ftn.uns.ac.rs.model.GetAllSmestajResponse;
import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.service.SmestajService;

@Endpoint
public class SmestajServiceEndpoint {
	final String NAMESPACE = "http://rs.ac.uns.ftn/Model";
	
	@Autowired
	SmestajService smestajService;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllSmestajRequest")
	public GetAllSmestajResponse getAll(@RequestPayload final GetAllSmestajRequest input) {
		GetAllSmestajResponse response = new GetAllSmestajResponse();
		List<SmestajDTO> dtos = new ArrayList<>();
		for(Smestaj s : smestajService.getAll()) {
			dtos.add(smestajToDTO(s));
		}
		response.setSmestajDTO(dtos);
		return response;
	}
	
	private SmestajDTO smestajToDTO(Smestaj smestaj) {
		SmestajDTO dto = new SmestajDTO();
		dto.setId(smestaj.getId());
		dto.setIdKategorijaSmestaja(smestaj.getKategorijaSmestaja().getId());
		dto.setIdLokacija(smestaj.getLokacija().getId());
		dto.setIdTipSmestaja(smestaj.getTipSmestaja().getId());
		dto.setNaziv(smestaj.getNaziv());
		dto.setOpis(smestaj.getOpis());
		dto.setSlika(smestaj.getSlika());
		return dto;
	}
}
