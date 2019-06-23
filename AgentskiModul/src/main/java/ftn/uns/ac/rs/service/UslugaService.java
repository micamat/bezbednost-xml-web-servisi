package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.GetAllUslugaRequest;
import ftn.uns.ac.rs.model.GetAllUslugaResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.SifarnikDTO;
import ftn.uns.ac.rs.model.Usluga;
import ftn.uns.ac.rs.repository.UslugaRepository;

@Service
public class UslugaService {
	@Autowired
	private UslugaRepository uslugaRepository;

	
	public List<SifarnikDTO> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllUslugaRequest getUslugaRequest = new GetAllUslugaRequest();
		GetAllUslugaResponse getUslugaResponse = producerPort.getAllUsluga(getUslugaRequest);
		for (SifarnikDTO uslugaDTO : getUslugaResponse.getUslugaDTO()) {
			uslugaRepository.save(convertToEntity(uslugaDTO));
		}
		
		for (Usluga usluga : uslugaRepository.findAll()) {
			boolean exists = false;
			for (SifarnikDTO uslugaDTO : getUslugaResponse.getUslugaDTO()) {
				if (usluga.getId().equals(uslugaDTO.getId())){
					exists = true;
					break;
				}
			}
			if (!exists) {
				uslugaRepository.deleteById(usluga.getId());
			}
		}
		return getUslugaResponse.getUslugaDTO();
	};
	
	public List<SifarnikDTO> getAll(){ 
		return uslugaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public SifarnikDTO getById(Long id) {
		if(!uslugaRepository.existsById(id)) {
			return null;
		}
		Usluga uslugaDTO = uslugaRepository.findById(id).orElse(null);
		return convertToDTO(uslugaDTO);
	}
	
	private SifarnikDTO convertToDTO(Usluga usluga) {
		SifarnikDTO uslugaDTO = new SifarnikDTO();
		uslugaDTO.setId(usluga.getId());
		uslugaDTO.setNaziv(usluga.getNaziv());
		uslugaDTO.setOpis(usluga.getOpis());
		return uslugaDTO;
	}
	
	private Usluga convertToEntity(SifarnikDTO uslugaDTO) {
		Usluga usluga = new Usluga();
		usluga.setId(uslugaDTO.getId());
		usluga.setNaziv(uslugaDTO.getNaziv());
		usluga.setOpis(uslugaDTO.getOpis());
		return usluga;
	}
}
