package agentski.modul.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentski.modul.app.model.tipsmestaja.TipSmestaja;
import agentski.modul.app.modelDTO.TipSmestajaDTO;
import agentski.modul.app.repository.TipSmestajaRepository;

@Service
public class TipSmestajaService {
	
	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;

	public List<TipSmestajaDTO> getAll(){ 
		return tipSmestajaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public TipSmestajaDTO getById(Long id) {
		if(!tipSmestajaRepository.existsById(id)) {
			return null;
		}
		TipSmestaja tipSmestaja = tipSmestajaRepository.findById(id).orElse(null);
		return convertToDTO(tipSmestaja);
	}
	
	
	public boolean add(TipSmestajaDTO tipSmestajaDTO) {
		tipSmestajaDTO.setId(null);
		if(tipSmestajaRepository.findAll().stream().filter(x -> tipSmestajaDTO.getNaziv().equals(x.getNaziv())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			tipSmestajaRepository.save(convertToEntity(tipSmestajaDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(tipSmestajaRepository.existsById(id)) {
			tipSmestajaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private TipSmestajaDTO convertToDTO(TipSmestaja tipSmestaja) {
		TipSmestajaDTO tipSmestajaDTO = new TipSmestajaDTO();
		tipSmestajaDTO.setId(tipSmestaja.getId());
		tipSmestajaDTO.setNaziv(tipSmestaja.getNaziv());
		tipSmestajaDTO.setOpis(tipSmestaja.getOpis());
		return tipSmestajaDTO;
	}
	
	private TipSmestaja convertToEntity(TipSmestajaDTO tipSmestajaDTO) {
		TipSmestaja tipSmestaja = new TipSmestaja();
		tipSmestaja.setId(tipSmestajaDTO.getId());
		tipSmestaja.setNaziv(tipSmestajaDTO.getNaziv());
		tipSmestaja.setOpis(tipSmestajaDTO.getOpis());
		return tipSmestaja;
	}
}
