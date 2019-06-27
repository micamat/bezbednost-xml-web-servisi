package ftn.uns.ac.rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Soba;
import ftn.uns.ac.rs.model.SobaDTO;
import ftn.uns.ac.rs.model.SobneUsluge;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.SobaRepository;
import ftn.uns.ac.rs.repository.SobneUslugeRepository;
import ftn.uns.ac.rs.repository.TipSobeRepository;
import ftn.uns.ac.rs.repository.UslugaRepository;

@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;
	
	@Autowired
	private SmestajRepository smestajRepository;
	
	@Autowired 
	private TipSobeRepository tipSobeRepo;

	@Autowired 
	private UslugaRepository uslugaRepo;
	
	@Autowired 
	private SobneUslugeRepository sobUslRepo;
	
	public int create(SobaDTO p){
		Soba s  = new Soba();
		s.setId(p.getId());
		s.setNaziv(p.getNaziv());
		s.setOpis(p.getOpis());
		s.setSmestaj(smestajRepository.findById(p.getIdSmestaj()).get());
		s.setTipSobe(tipSobeRepo.findById(p.getIdTipSobe()).get());
		Soba n = sobaRepository.save(s);
		
		for (Long i : p.getIdUsluga()) {
			SobneUsluge sob = new SobneUsluge();
			sob.setSoba(n);
			sob.setUsluga(uslugaRepo.findById(i).get());
			sobUslRepo.save(sob);
		}
		
		if(n != null) {
			return n.getId().intValue();
		}else {
			return -1;
		}
	}
}
