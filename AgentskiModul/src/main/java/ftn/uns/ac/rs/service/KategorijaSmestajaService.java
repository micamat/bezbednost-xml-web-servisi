package ftn.uns.ac.rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.repository.KategorijaSmestajaRepository;

@Service
public class KategorijaSmestajaService {
	
	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;

	/*public List<SifarnikDTO> getAll(){ 
		return kategorijaSmestajaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public SifarnikDTO getById(Long id) {
		if(!kategorijaSmestajaRepository.existsById(id)) {
			return null;
		}
		KategorijaSmestaja kategorijaSmestaja = kategorijaSmestajaRepository.findById(id).orElse(null);
		return convertToDTO(kategorijaSmestaja);
	}
	
	
	public boolean add(SifarnikDTO kategorijaSmestajaDTO) {
		kategorijaSmestajaDTO.setId(null);
		if(kategorijaSmestajaRepository.findAll().stream().filter(x -> kategorijaSmestajaDTO.getNaziv().equals(x.getNaziv())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			kategorijaSmestajaRepository.save(convertToEntity(kategorijaSmestajaDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(kategorijaSmestajaRepository.existsById(id)) {
			kategorijaSmestajaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private SifarnikDTO convertToDTO(KategorijaSmestaja kategorijaSmestaja) {
		SifarnikDTO kategorijaSmestajaDTO = new SifarnikDTO();
		kategorijaSmestajaDTO.setId(kategorijaSmestaja.getId());
		kategorijaSmestajaDTO.setNaziv(kategorijaSmestaja.getNaziv());
		kategorijaSmestajaDTO.setOpis(kategorijaSmestaja.getOpis());
		return kategorijaSmestajaDTO;
	}
	
	private KategorijaSmestaja convertToEntity(SifarnikDTO kategorijaSmestajaDTO) {
		KategorijaSmestaja kategorijaSmestaja = new KategorijaSmestaja();
		kategorijaSmestaja.setNaziv(kategorijaSmestajaDTO.getNaziv());
		kategorijaSmestaja.setOpis(kategorijaSmestajaDTO.getOpis());
		return kategorijaSmestaja;
	}*/
}
