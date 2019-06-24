package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.GetAllUslugaRequest;
import ftn.uns.ac.rs.model.GetAllUslugaResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.Usluga;
import ftn.uns.ac.rs.repository.UslugaRepository;

@Service
public class UslugaService {
	@Autowired
	private UslugaRepository uslugaRepository;
	
	private Logger logger = LogManager.getLogger();
	 private static final Marker USER = MarkerManager
			   .getMarker("USER");
	
	
	public List<Usluga> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllUslugaRequest getUslugaRequest = new GetAllUslugaRequest();
		GetAllUslugaResponse getUslugaResponse = new GetAllUslugaResponse();
		try {
			getUslugaResponse = producerPort.getAllUsluga(getUslugaRequest);
		} catch (Exception e) {
			logger.error(USER, "Neuspesna sinhronizacija Usluga: " + e.getMessage());
		}
		for (Usluga uslugaDTO : getUslugaResponse.getUsluga()) {
			uslugaRepository.save(uslugaDTO);
		}
		
		for (Usluga usluga : uslugaRepository.findAll()) {
			boolean exists = false;
			for (Usluga uslugaDTO : getUslugaResponse.getUsluga()) {
				if (usluga.getId().equals(uslugaDTO.getId())){
					exists = true;
					break;
				}
			}
			if (!exists) {
				uslugaRepository.deleteById(usluga.getId());
			}
		}
		return getUslugaResponse.getUsluga();
	};
	
	public List<Usluga> getAll(){ 
		return uslugaRepository.findAll().stream().collect(Collectors.toList());
	};
	
	public Usluga getById(Long id) {
		if(!uslugaRepository.existsById(id)) {
			return null;
		}
		Usluga usluga = uslugaRepository.findById(id).orElse(null);
		return usluga;
	}
}
