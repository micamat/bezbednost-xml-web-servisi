package adminski.modul.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminski.modul.app.model.TipUsluge;
import adminski.modul.app.repository.TipUslugeRepository;

@Service
public class TipUslugeService {

	@Autowired
	private TipUslugeRepository tipUslugeRepository;
	
	public List<TipUsluge> getAllTypesServices() {
		return tipUslugeRepository.findAll();
	}
	
	public TipUsluge getTypeServiceById(String id) {
		return tipUslugeRepository.findById(Long.getLong(id)).orElse(null);
	}
	
	public boolean removeTypeService(String id) {
		TipUsluge tipUsluge = getTypeServiceById(id);
		
		if (tipUsluge != null) {
			tipUslugeRepository.delete(tipUsluge);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean createTypeService(TipUsluge tipUsluge) {
		
		tipUslugeRepository.save(tipUsluge);
		return true;
	}
	
	public boolean updateTypeService(String id,TipUsluge tipUsluge) {
		TipUsluge postojeca = getTypeServiceById(id);
		
		if (postojeca == null) {
			return false;
		} else {
			postojeca.setNaziv(tipUsluge.getNaziv());
			postojeca.setOpis(tipUsluge.getOpis());
			
			
			tipUslugeRepository.save(postojeca);
			return true;	
		}
	}
	
}
