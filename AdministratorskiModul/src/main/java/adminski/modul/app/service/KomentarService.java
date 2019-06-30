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
	
	public Komentar getCommentById(Long id) {
		return komentarRepository.findById(id).orElse(null);
	}
	
	public void hideComment(Long id) {
		System.out.println("ID: " + id);
		Komentar kom = komentarRepository.findById(id).get();
		kom.setStatusKomentara("neodobren");
		komentarRepository.save(kom);
	}
	
	public boolean removeComment(Long id) {
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
	
	public boolean updateComment(Long id,Komentar komentar) {
		Komentar postojeci = getCommentById(id);
		
		if (postojeci == null) {
			return false;
		} else {
			postojeci.setNaslov(komentar.getNaslov());
			postojeci.setTekst(komentar.getTekst());
			postojeci.setDatumKreiranja(komentar.getDatumKreiranja());
			postojeci.setKorisnik(komentar.getKorisnik());
			postojeci.setIdSobe(komentar.getIdSobe());
			postojeci.setStatusKomentara(komentar.getStatusKomentara());
			
			komentarRepository.save(postojeci);
			return true;	
		}
	}
}
