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
import ftn.uns.ac.rs.model.GetAllTipSmestajaRequest;
import ftn.uns.ac.rs.model.GetAllTipSmestajaResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.TipSmestaja;
import ftn.uns.ac.rs.repository.TipSmestajaRepository;

@Service
public class TipSmestajaService {
	
	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;
	
	private Logger logger = LogManager.getLogger();
	 private static final Marker USER = MarkerManager
			   .getMarker("USER");


	public List<TipSmestaja> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();

		Auth.authenticateClient(producerPort);
		GetAllTipSmestajaRequest getTipSmestajaRequest = new GetAllTipSmestajaRequest();
		GetAllTipSmestajaResponse getTipSmestajaResponse = new GetAllTipSmestajaResponse();
		
		try {
			getTipSmestajaResponse = producerPort.getAllTipSmestaja(getTipSmestajaRequest);

			logger.info(USER, "Uspesna sinhronizacija Tipa sobe");
		} catch (Exception e) {
			logger.error(USER, "Neuspesna sinhronizacija Tipa sobe: " + e.getMessage());
		}
		
		for (TipSmestaja tipSmestajaDTO : getTipSmestajaResponse.getTipSmestaja()) {
			tipSmestajaRepository.save(tipSmestajaDTO);
		}
		boolean exists = false;
		for (TipSmestaja tipSmestaja : tipSmestajaRepository.findAll()) {
			for (TipSmestaja tipSmestajaDTO : getTipSmestajaResponse.getTipSmestaja()) {
				if (tipSmestaja.getId().equals(tipSmestajaDTO.getId())){
					exists = true;
				}
			}
			if (!exists) {
				tipSmestajaRepository.deleteById(tipSmestaja.getId());
			}
		}
		return getTipSmestajaResponse.getTipSmestaja();
	};
	
	public List<TipSmestaja> getAll(){ 
		return tipSmestajaRepository.findAll().stream().collect(Collectors.toList());
	};
	
	public TipSmestaja getById(Long id) {
		if(!tipSmestajaRepository.existsById(id)) {
			return null;
		}
		return tipSmestajaRepository.findById(id).orElse(null);
	}
}
