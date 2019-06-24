package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<TipSmestaja> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllTipSmestajaRequest getTipSmestajaRequest = new GetAllTipSmestajaRequest();
		GetAllTipSmestajaResponse getTipSmestajaResponse = producerPort.getAllTipSmestaja(getTipSmestajaRequest);
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
