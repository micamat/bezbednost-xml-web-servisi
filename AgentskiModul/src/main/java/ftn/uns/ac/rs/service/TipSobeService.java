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
import ftn.uns.ac.rs.model.GetAllTipSobeRequest;
import ftn.uns.ac.rs.model.GetAllTipSobeResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.TipSobe;
import ftn.uns.ac.rs.repository.TipSobeRepository;

@Service
public class TipSobeService {
	@Autowired
	private TipSobeRepository tipSobeRepository;
	private Logger logger = LogManager.getLogger();
	 private static final Marker USER = MarkerManager
			   .getMarker("USER");

	public List<TipSobe> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();

		Auth.authenticateClient(producerPort);
		GetAllTipSobeRequest getTipSobeRequest = new GetAllTipSobeRequest();
		GetAllTipSobeResponse getTipSobeResponse = new GetAllTipSobeResponse();
		
		try {
			getTipSobeResponse = producerPort.getAllTipSobe(getTipSobeRequest);

			logger.info(USER, "Uspesna sinhronizacija Tipa sobe");
		} catch (Exception e) {
			logger.error(USER, "Neuspesna sinhronizacija Tipa sobe: " + e.getMessage());
		}
		
		for (TipSobe tipSobeDTO : getTipSobeResponse.getTipSobe()) {
			tipSobeRepository.save(tipSobeDTO);
		}
		for (TipSobe tipSobe : tipSobeRepository.findAll()) {
			boolean exists = false;
			for (TipSobe tipSobeDTO : getTipSobeResponse.getTipSobe()) {
				if (tipSobe.getId().equals(tipSobeDTO.getId())){
					exists = true;
					break;
				}
			}
			if (!exists) {
				tipSobeRepository.deleteById(tipSobe.getId());
			}
		}
		return getTipSobeResponse.getTipSobe();
	};
	
	public List<TipSobe> getAll(){ 
		return tipSobeRepository.findAll().stream().collect(Collectors.toList());
	};
	
	public TipSobe getById(Long id) {
		if(!tipSobeRepository.existsById(id)) {
			return null;
		}
		return tipSobeRepository.findById(id).orElse(null);
	}
}
