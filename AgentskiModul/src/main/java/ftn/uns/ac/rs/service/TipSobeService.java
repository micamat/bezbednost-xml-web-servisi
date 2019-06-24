package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<TipSobe> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllTipSobeRequest getTipSobeRequest = new GetAllTipSobeRequest();
		GetAllTipSobeResponse getTipSobeResponse = producerPort.getAllTipSobe(getTipSobeRequest);
		
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
