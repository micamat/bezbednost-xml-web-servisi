package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreatePorukaRequest;
import ftn.uns.ac.rs.model.CreatePorukaResponse;
import ftn.uns.ac.rs.model.Poruka;
import ftn.uns.ac.rs.model.PorukaDTO;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.ShowPorukaDTO;
import ftn.uns.ac.rs.repository.PorukaRepository;
import ftn.uns.ac.rs.repository.RezervacijaRepository;

@Service
public class PorukaService {
	@Autowired
	private PorukaRepository porukaRepository;
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	private Logger logger = LogManager.getLogger();
	
	private static final Marker USER = MarkerManager
			   .getMarker("USER");
	
	public int createSync(PorukaDTO porukaDTO){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreatePorukaRequest createPorukaRequest = new CreatePorukaRequest();
		createPorukaRequest.setPorukaDTO(porukaDTO);
		CreatePorukaResponse createPorukaResponse = producerPort.createPoruka(createPorukaRequest);
		return createPorukaResponse.getId();
	};
	
	public List<ShowPorukaDTO> getAllByRezervacija(Long rezervacijaId) { 
		return porukaRepository.findAll().stream().filter(x -> x.getRezervacija().getId().equals(rezervacijaId)).map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public ShowPorukaDTO getById(Long id) {
		if(!porukaRepository.existsById(id)) {
			return null;
		}
		Poruka poruka = porukaRepository.findById(id).orElse(null);
		return convertToDTO(poruka);
	}
	
	public boolean add(PorukaDTO porukaDTO) {
		porukaDTO.setId(null);
		try {
			porukaRepository.save(convertToEntity(porukaDTO));
			//createSync(porukaDTO);
			logger.info(USER, "Poruka uspesno poslata");
			return true;
		} catch (Exception e) {

			logger.info(USER, "Poruka nije poslata");
		}
		return false;
	}
	
	private ShowPorukaDTO convertToDTO(Poruka poruka) {
		ShowPorukaDTO porukaDTO = new ShowPorukaDTO();
		porukaDTO.setId(poruka.getId());
		porukaDTO.setDatum(poruka.getDatum());
		if (poruka.isKorisnikPosiljalac()) {
			porukaDTO.setKorisnickoIme(poruka.getRezervacija().getKorisnik().getKorisnickoIme());
		} else {
			porukaDTO.setKorisnickoIme(poruka.getRezervacija().getSmestaj().getAgent().getKorisnickoIme());
		}
		porukaDTO.setNaslov(poruka.getNaslov());
		porukaDTO.setSadrzaj(poruka.getSadrzaj());
		return porukaDTO;
	}
	
	private Poruka convertToEntity(PorukaDTO porukaDTO) {
		Poruka poruka = new Poruka();
		poruka.setId(porukaDTO.getId());
		poruka.setDatum(porukaDTO.getDatum());
		poruka.setRezervacija(rezervacijaRepository.findById(porukaDTO.getIdRezervacije()).orElse(null));
		poruka.setNaslov(porukaDTO.getNaslov());
		poruka.setSadrzaj(porukaDTO.getSadrzaj());
		poruka.setKorisnikPosiljalac(porukaDTO.isKorisnikPosiljalac());
		return poruka;
	}
}
