package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

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

	
	public List<Usluga> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllUslugaRequest getUslugaRequest = new GetAllUslugaRequest();
		GetAllUslugaResponse getUslugaResponse = producerPort.getAllUsluga(getUslugaRequest);
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
