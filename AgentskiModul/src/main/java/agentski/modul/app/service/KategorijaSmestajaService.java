package agentski.modul.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentski.modul.app.model.kategorijasmestaja.KategorijaSmestaja;
import agentski.modul.app.modelDTO.KategorijaSmestajaDTO;
import agentski.modul.app.repository.KategorijaSmestajaRepository;

@Service
public class KategorijaSmestajaService {
	
	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;

	public List<KategorijaSmestajaDTO> getAll(){ 
		return kategorijaSmestajaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public KategorijaSmestajaDTO getById(Long id) {
		if(!kategorijaSmestajaRepository.existsById(id)) {
			return null;
		}
		KategorijaSmestaja kategorijaSmestaja = kategorijaSmestajaRepository.findById(id).orElse(null);
		return convertToDTO(kategorijaSmestaja);
	}
	
	
	public boolean add(KategorijaSmestajaDTO tipSmestajaDTO) {
		tipSmestajaDTO.setId(null);
		if(kategorijaSmestajaRepository.findAll().stream().filter(x -> tipSmestajaDTO.getNaziv().equals(x.getNaziv())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			kategorijaSmestajaRepository.save(convertToEntity(tipSmestajaDTO));
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
	
	private KategorijaSmestajaDTO convertToDTO(KategorijaSmestaja kategorijaSmestaja) {
		KategorijaSmestajaDTO kategorijaSmestajaDTO = new KategorijaSmestajaDTO();
		kategorijaSmestajaDTO.setId(kategorijaSmestaja.getId());
		kategorijaSmestajaDTO.setNaziv(kategorijaSmestaja.getNaziv());
		kategorijaSmestajaDTO.setOpis(kategorijaSmestaja.getOpis());
		return kategorijaSmestajaDTO;
	}
	
	private KategorijaSmestaja convertToEntity(KategorijaSmestajaDTO kategorijaSmestajaDTO) {
		KategorijaSmestaja kategorijaSmestaja = new KategorijaSmestaja();
		kategorijaSmestaja.setNaziv(kategorijaSmestajaDTO.getNaziv());
		kategorijaSmestaja.setOpis(kategorijaSmestajaDTO.getOpis());
		return kategorijaSmestaja;
	}
}
