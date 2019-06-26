package ftn.uns.ac.rs.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateKoordinateRequest;
import ftn.uns.ac.rs.model.CreateKoordinateResponse;
import ftn.uns.ac.rs.model.Koordinate;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.repository.KoordinateRepository;

@Service
public class KoordinateService {
	
	@Autowired
	private KoordinateRepository koordinateRepository;
	
	private Logger logger = LogManager.getLogger();
	 private static final Marker USER = MarkerManager
			   .getMarker("USER");
	
	
	public int createSync(Koordinate koordinate){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateKoordinateRequest createKoordinateRequest = new CreateKoordinateRequest();
		createKoordinateRequest.setKoordinate(koordinate);
		CreateKoordinateResponse createLokacijaResponse = producerPort.createKoordinate(createKoordinateRequest);
		return createLokacijaResponse.getId();
	};
	
	
	
	public Koordinate getById(Long id) {
		if(!koordinateRepository.existsById(id)) {
			return null;
		}
		return koordinateRepository.findById(id).orElse(null);
	}
	
	
	public Long add(Koordinate koordinate) {
		koordinate = koordinateRepository.save(koordinate);
		if(koordinate != null) {
			//createSync(koordinate);
			return koordinate.getId();
		}
		return null;
	}
	
	/*
	 * public Lokacija add(Lokacija lokacija) { lokacija.setId(lokacija.getId());
	 * Lokacija l = lokacijaRepository.save(lokacija); if(l != null) { return l; }
	 * return null; }
	 */
	
	public boolean delete(Long id) {
		ThreadContext.put("user", "A");
		if(koordinateRepository.existsById(id)) {
			koordinateRepository.deleteById(id);
			logger.info(USER, "Koordinate " + id + "uspesno izbrisane");
			return true;
		}
		logger.warn(USER, "Koordinate " + id + "ne postoje u bazi");
		return false;
	}
}
