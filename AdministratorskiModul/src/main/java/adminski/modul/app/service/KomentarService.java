package adminski.modul.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminski.modul.app.model.Komentar;
import adminski.modul.app.repository.KomentarRepository;

@Service
public class KomentarService {
	
	@Autowired
	private KomentarRepository komentarRepository;
	
	public List<Komentar> getAllComments() {
		return komentarRepository.findAll();
	}
	
	public Komentar getCommentById(String id) {
		return komentarRepository.findById(Long.getLong(id)).orElse(null);
	}
	
	public boolean removeComment(String id) {
		Komentar komentar = getCommentById(id);
		
		if (komentar != null) {
			komentarRepository.delete(komentar);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean createComment(Komentar komentar) {
		
		komentarRepository.save(komentar);
		return true;
	}
	
	public boolean updateComment(String id,Komentar komentar) {
		Komentar postojeci = getCommentById(id);
		
		if (postojeci == null) {
			return false;
		} else {
			postojeci.setNaslov(komentar.getNaslov());
			postojeci.setTekst(komentar.getTekst());
			postojeci.setDatum(komentar.getDatum());
			postojeci.setKorisnik(komentar.getKorisnik());
			postojeci.setIdSobe(komentar.getIdSobe());
			
			komentarRepository.save(postojeci);
			return true;	
		}
	}
}
