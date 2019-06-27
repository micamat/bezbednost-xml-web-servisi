package ftn.uns.ac.rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public int create(CenovnikDTO p){
		Cenovnik sm = new Cenovnik();
		sm.setBrojDanaZaOtkazivanje(p.getBrojDanaZaOtkazivanje());
		sm.setCena(p.getCena());
		sm.setDatumDo(p.getDatumDo());
		sm.setDatumOd(p.getDatumOd());
		sm.setId(p.getId());
		sm.setSmestaj(smestajRepo.findById(p.getIdSmestaj()).get());
		sm.setTipSobe(tipSobaRepo.findById(p.getIdTipSobe()).get());
		Cenovnik s = cenovnikRepo.save(sm);
		if(s == null) {
			return -1;
		}
		else {
			return s.getId().intValue();
		}
	}

}
