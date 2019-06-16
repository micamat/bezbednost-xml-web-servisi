package adminski.modul.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminski.modul.app.model.KategorijaSmestaja;
import adminski.modul.app.repository.KategorijaSmestajaRepository;

@Service
public class KategorijaSmestajaService {
	
	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;
	
	public List<KategorijaSmestaja> getAllCategoryAccommodations() {
		return kategorijaSmestajaRepository.findAll();
	}
	
	public KategorijaSmestaja getCategoryAccommodationById(String id) {
		return kategorijaSmestajaRepository.findById(Long.getLong(id)).orElse(null);
	}
	
	public boolean removeCategoryAccommodation(String id) {
		KategorijaSmestaja kategorija = getCategoryAccommodationById(id);
		
		if (kategorija != null) {
			kategorijaSmestajaRepository.delete(kategorija);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean createCategoryAccommodation(KategorijaSmestaja kategorija) {
		
		kategorijaSmestajaRepository.save(kategorija);
		return true;
	}
	
	public boolean updateCategoryAccommodation(String id,KategorijaSmestaja kategorija) {
		KategorijaSmestaja postojeca = getCategoryAccommodationById(id);
		
		if (postojeca == null) {
			return false;
		} else {
			postojeca.setNaziv(kategorija.getNaziv());
			postojeca.setOpis(kategorija.getOpis());
			
			
			kategorijaSmestajaRepository.save(postojeca);
			return true;	
		}
	}
}
