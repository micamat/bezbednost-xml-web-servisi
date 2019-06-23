package adminski.modul.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminski.modul.app.repository.KategorijaSmestajaRepository;
import adminski.modul.app.repository.TipSmestajaRepository;
import adminski.modul.app.repository.TipUslugeRepository;
import adminski.modul.app.model.TipSmestaja;

@Service
public class TipSmestajaService {

	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;
	
	@Autowired
	private TipUslugeRepository tipUslugeRepository;
	
	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;
	
	public List<TipSmestaja> getAllTipSmestaja() {
		return tipSmestajaRepository.findAll();
	}
	
	public TipSmestaja getTipSmestajaById(Long id) {
		return tipSmestajaRepository.findById(id).orElse(null);
	}
	
	public boolean removeTipSmestaja(Long id) {
		TipSmestaja ts = getTipSmestajaById(id);
		
		if (ts != null) {
			tipSmestajaRepository.delete(ts);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean createTipSmestaja(TipSmestaja tipSmestaja) {
		tipSmestajaRepository.save(tipSmestaja);
		
		return true;
	}
	
	public boolean updateTipSmestaja(Long id,TipSmestaja tipSmestaja) {
		TipSmestaja postojeci = getTipSmestajaById(id);
		
		if (postojeci == null) {
			return false;
		} else {
			postojeci.setNaziv(tipSmestaja.getNaziv());
			postojeci.setOpis(tipSmestaja.getOpis());
			
			tipSmestajaRepository.save(postojeci);
			return true;	
		}
	}
}
