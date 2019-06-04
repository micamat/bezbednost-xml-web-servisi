package adminski.modul.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminski.modul.app.model.Korisnik;
import adminski.modul.app.repository.KorisnikRepository;

@Service
public class KorisnikService {
	@Autowired
	KorisnikRepository korisnikRepository;
	
	public boolean blockById(String id) {
		Korisnik entity = korisnikRepository.findById(Long.getLong(id)).orElse(null);
		
		if (entity != null) {
			entity.setAktivan(false);
			korisnikRepository.save(entity);
			
			return true;
		} else {
		
			return false;
		}
	}
	
	public boolean unblockById(String id) {
		Korisnik entity = korisnikRepository.findById(Long.getLong(id)).orElse(null);
		
		if (entity != null) {
			entity.setAktivan(true);
			korisnikRepository.save(entity);
			
			return true;
		} else {
		
			return false;
		}
	}
	
	public boolean removeById(String id) {
		Korisnik entity = korisnikRepository.findById(Long.getLong(id)).orElse(null);
		
		if (entity != null) {
			korisnikRepository.delete(entity);
			
			return true;
		} else {
		
			return false;
		}
	}
	
	public Korisnik findById(String id) {
		return korisnikRepository.findById(Long.getLong(id)).orElse(null);
	}
}
