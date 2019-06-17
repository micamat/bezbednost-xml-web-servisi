package adminski.modul.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminski.modul.app.model.Korisnik;
import adminski.modul.app.repository.KorisnikRepository;

@Service
public class KorisnikService {
	@Autowired
	KorisnikRepository korisnikRepository;
	
	public boolean blockById(Long id) {
		Korisnik entity = korisnikRepository.findById(id).orElse(null);
		
		if (entity != null) {
			entity.setAktivan(false);
			korisnikRepository.save(entity);
			
			return true;
		} else {
		
			return false;
		}
	}
	
	public boolean unblockById(Long id) {
		Korisnik entity = korisnikRepository.findById(id).orElse(null);
		
		if (entity != null) {
			entity.setAktivan(true);
			korisnikRepository.save(entity);
			
			return true;
		} else {
		
			return false;
		}
	}
	
	public boolean removeById(Long id) {
		Korisnik entity = korisnikRepository.findById(id).orElse(null);
		
		if (entity != null) {
			korisnikRepository.delete(entity);
			
			return true;
		} else {
		
			return false;
		}
	}
	
	public Korisnik findById(Long id) {
		return korisnikRepository.findById(id).orElse(null);
	}
}
