package ftn.uns.ac.rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.repository.TipSobeRepository;

@Service
public class TipSobeService {
	@Autowired
	private TipSobeRepository tipSobeRepository;

	/*public List<SifarnikDTO> getAll(){ 
		return tipSobeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public SifarnikDTO getById(Long id) {
		if(!tipSobeRepository.existsById(id)) {
			return null;
		}
		TipSobe tipSobeDTO = tipSobeRepository.findById(id).orElse(null);
		return convertToDTO(tipSobeDTO);
	}
	
	
	public boolean add(SifarnikDTO tipSobeDTO) {
		tipSobeDTO.setId(null);
		if(tipSobeRepository.findAll().stream().filter(x -> tipSobeDTO.getNaziv().equals(x.getNaziv())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			tipSobeRepository.save(convertToEntity(tipSobeDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(tipSobeRepository.existsById(id)) {
			tipSobeRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private SifarnikDTO convertToDTO(TipSobe tipSobe) {
		SifarnikDTO tipSobeDTO = new SifarnikDTO();
		tipSobeDTO.setId(tipSobe.getId());
		tipSobeDTO.setNaziv(tipSobe.getNaziv());
		tipSobeDTO.setOpis(tipSobe.getOpis());
		return tipSobeDTO;
	}
	
	private TipSobe convertToEntity(SifarnikDTO tipSobeDTO) {
		TipSobe tipSobe = new TipSobe();
		tipSobe.setId(tipSobeDTO.getId());
		tipSobe.setNaziv(tipSobeDTO.getNaziv());
		tipSobe.setOpis(tipSobeDTO.getOpis());
		return tipSobe;
	}*/
}
