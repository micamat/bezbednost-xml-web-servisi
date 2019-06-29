package ftn.uns.ac.rs.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.configuration.Username;
import ftn.uns.ac.rs.model.Cenovnik;
import ftn.uns.ac.rs.model.CenovnikDTO;
import ftn.uns.ac.rs.repository.CenovnikRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.TipSobeRepository;

@Service
public class CenovnikService {
	
	@Autowired
	private SmestajRepository smestajRepo;
	
	@Autowired
	private TipSobeRepository tipSobaRepo;
	
	@Autowired
	private CenovnikRepository cenovnikRepo;
	
	private Logger logger = LogManager.getLogger();
	
	private static final Marker USER = MarkerManager
			   .getMarker("USER");
	
	public int create(CenovnikDTO p){
		Cenovnik sm = new Cenovnik();
		sm.setBrojDanaZaOtkazivanje(p.getBrojDanaZaOtkazivanje());
		sm.setCena(p.getCena());
		sm.setDatumDo(p.getDatumDo());
		sm.setDatumOd(p.getDatumOd());
		sm.setId(p.getId());
		sm.setSmestaj(smestajRepo.findById(p.getIdSmestaj()).get());
		sm.setTipSobe(tipSobaRepo.findById(p.getIdTipSobe()).get());
		ThreadContext.put("user", Username.getLoggedUser());
		try {
			Cenovnik s = cenovnikRepo.save(sm);
			logger.info(USER, "Cenovnik uspesno unet u bazu");

			return s.getId().intValue();
		} catch (Exception e) {
			logger.error(USER, "Greska prilikom upisa cenovnika u bazu: " + e.getMessage());
			return -1;
		}
	}

}
