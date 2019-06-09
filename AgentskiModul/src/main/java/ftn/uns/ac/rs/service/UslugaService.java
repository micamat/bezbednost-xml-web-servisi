package ftn.uns.ac.rs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateUslugaRequest;
import ftn.uns.ac.rs.model.CreateUslugaResponse;
import ftn.uns.ac.rs.model.GetAllUslugaRequest;
import ftn.uns.ac.rs.model.GetAllUslugaResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.UslugaDTO;
import ftn.uns.ac.rs.repository.UslugaRepository;

@Service
public class UslugaService {
	@Autowired
	private UslugaRepository uslugaRepository;

	
	public List<UslugaDTO> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllUslugaRequest getUslugaRequest = new GetAllUslugaRequest();
		GetAllUslugaResponse getUslugaResponse = producerPort.getAllUsluga(getUslugaRequest);
		return getUslugaResponse.getUslugaDTO();
	};
	
	
	public int createSync(UslugaDTO smd){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateUslugaRequest getUslugaRequest = new CreateUslugaRequest();
		getUslugaRequest.setId(smd.getId());
		getUslugaRequest.setNaziv(smd.getNaziv());
		getUslugaRequest.setOpis(smd.getOpis());
		CreateUslugaResponse getUslugaResponse = producerPort.createUsluga(getUslugaRequest);
		return getUslugaResponse.getId();
	};
	
	/*public List<SifarnikDTO> getAll(){ 
		return uslugaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public SifarnikDTO getById(Long id) {
		if(!uslugaRepository.existsById(id)) {
			return null;
		}
		Usluge uslugaDTO = uslugaRepository.findById(id).orElse(null);
		return convertToDTO(uslugaDTO);
	}
	
	
	public boolean add(SifarnikDTO uslugaDTO) {
		uslugaDTO.setId(null);
		if(uslugaRepository.findAll().stream().filter(x -> uslugaDTO.getNaziv().equals(x.getNaziv())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			uslugaRepository.save(convertToEntity(uslugaDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(uslugaRepository.existsById(id)) {
			uslugaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private SifarnikDTO convertToDTO(Usluge usluga) {
		SifarnikDTO uslugaDTO = new SifarnikDTO();
		uslugaDTO.setId(usluga.getId());
		uslugaDTO.setNaziv(usluga.getNaziv());
		uslugaDTO.setOpis(usluga.getOpis());
		return uslugaDTO;
	}
	
	private Usluge convertToEntity(SifarnikDTO uslugaDTO) {
		Usluge usluga = new Usluge();
		usluga.setId(uslugaDTO.getId());
		usluga.setNaziv(uslugaDTO.getNaziv());
		usluga.setOpis(uslugaDTO.getOpis());
		return usluga;
	}*/
}
