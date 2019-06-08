package ftn.uns.ac.rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.repository.KoordinateRepository;
import ftn.uns.ac.rs.repository.LokacijaRepository;

@Service
public class LokacijaService {
	
	@Autowired
	private LokacijaRepository lokacijaRepository;
	
	@Autowired
	private KoordinateRepository koordinateRepository;

	/*public List<LokacijaDTO> getAll(){ 
		return lokacijaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public LokacijaDTO getById(Long id) {
		if(!lokacijaRepository.existsById(id)) {
			return null;
		}
		Lokacija lokacija = lokacijaRepository.findById(id).orElse(null);
		return convertToDTO(lokacija);
	}
	
	
	public boolean add(LokacijaDTO lokacijaDTO) {
		lokacijaDTO.setId(null);
		if(lokacijaRepository.save(convertToEntity(lokacijaDTO)) != null) {
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(lokacijaRepository.existsById(id)) {
			lokacijaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private LokacijaDTO convertToDTO(Lokacija lokacija) {
		LokacijaDTO lokacijaDTO = new LokacijaDTO();
		lokacijaDTO.setId(lokacija.getId());
		lokacijaDTO.setDrzava(lokacija.getDrzava());
		lokacijaDTO.setGrad(lokacija.getGrad());
		lokacijaDTO.setUlica(lokacija.getUlica());
		lokacijaDTO.setBroj(lokacija.getBroj());
		lokacijaDTO.setKoordinateId(lokacija.getKoordinate().getId());
		return lokacijaDTO;
	}
	
	private Lokacija convertToEntity(LokacijaDTO lokacijaDTO) {
		Lokacija lokacija = new Lokacija();
		lokacija.setId(lokacijaDTO.getId());
		lokacija.setDrzava(lokacijaDTO.getDrzava());
		lokacija.setGrad(lokacijaDTO.getGrad());
		lokacija.setUlica(lokacijaDTO.getUlica());
		lokacija.setBroj(lokacijaDTO.getBroj());
		lokacija.setKoordinate(koordinateRepository.findById(lokacijaDTO.getKoordinateId()).orElse(null));
		return lokacija;
	}*/
}
