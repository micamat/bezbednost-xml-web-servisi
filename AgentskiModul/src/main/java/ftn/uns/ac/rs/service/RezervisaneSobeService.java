package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.config.Auth;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.RezervisaneSobe;
import ftn.uns.ac.rs.model.RezervisaneSobeDTO;
import ftn.uns.ac.rs.model.ShowRezervisaneSobeDTO;
import ftn.uns.ac.rs.model.UpdateRezervisaneSobeRequest;
import ftn.uns.ac.rs.model.UpdateRezervisaneSobeResponse;
import ftn.uns.ac.rs.repository.RezervisaneSobeRepository;

@Service
public class RezervisaneSobeService {
	
	@Autowired
	private RezervisaneSobeRepository rezervisaneSobeRepository;
	private Logger logger = LogManager.getLogger();
	
	private static final Marker USER = MarkerManager
			   .getMarker("USER");
	
	public int createSync(RezervisaneSobeDTO rezervisaneSobeDTO){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();

		Auth.authenticateClient(producerPort);
		UpdateRezervisaneSobeRequest updateRezervisaneSobeRequest = new UpdateRezervisaneSobeRequest();
		updateRezervisaneSobeRequest.setRezervisaneSobeDTO(rezervisaneSobeDTO);
		UpdateRezervisaneSobeResponse updateRezervisaneSobeResponse = producerPort.updateRezervisaneSobe(updateRezervisaneSobeRequest);
		return updateRezervisaneSobeResponse.getId();
	};
	
	public List<ShowRezervisaneSobeDTO> getAll(){ 
		return rezervisaneSobeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public ShowRezervisaneSobeDTO getById(Long id) {
		if(!rezervisaneSobeRepository.existsById(id)) {
			return null;
		}
		RezervisaneSobe rezervisaneSobe = rezervisaneSobeRepository.findById(id).orElse(null);
		return convertToDTO(rezervisaneSobe);
	}
	
	public List<ShowRezervisaneSobeDTO> getBySmestaj(Long smestajId) {
		return rezervisaneSobeRepository.findAll().stream().filter(x -> smestajId.equals(x.getRezervacija().getSmestaj().getId())).map(this::convertToDTO).collect(Collectors.toList());
	}
	
	public boolean add(RezervisaneSobe rezervisaneSobe) {
		try {
			rezervisaneSobe = rezervisaneSobeRepository.save(rezervisaneSobe);
			logger.info(USER, "Soba " + rezervisaneSobe.getSoba().getId() + " uspesno rezervisana");
			return true;
		} catch (Exception e) {
			logger.error(USER, "Greska prilikom rezrvisanja sobe " + rezervisaneSobe.getSoba().getId() + ": " + e.getMessage());
		}
		return false;
	}
	
	public ShowRezervisaneSobeDTO convertToDTO(RezervisaneSobe rezervisaneSobe) {
		ShowRezervisaneSobeDTO rezervisaneSobeDTO = new ShowRezervisaneSobeDTO();
		rezervisaneSobeDTO.setId(rezervisaneSobe.getId());
		rezervisaneSobeDTO.setCena(rezervisaneSobe.getCena());
		rezervisaneSobeDTO.setNazivSoba(rezervisaneSobe.getSoba().getNaziv());
		rezervisaneSobeDTO.setNazivStatusRezervacije(rezervisaneSobe.getStatusRezervacije());
		return rezervisaneSobeDTO;
	}
	
}
