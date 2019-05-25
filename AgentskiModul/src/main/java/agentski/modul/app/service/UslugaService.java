package agentski.modul.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentski.modul.app.model.usluga.Usluga;
import agentski.modul.app.modelDTO.SifarnikDTO;
import agentski.modul.app.repository.UslugaRepository;

@Service
public class UslugaService {
	@Autowired
	private UslugaRepository uslugaRepository;

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
