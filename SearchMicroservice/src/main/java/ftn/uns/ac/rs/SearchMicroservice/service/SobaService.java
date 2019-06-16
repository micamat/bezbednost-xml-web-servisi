package ftn.uns.ac.rs.SearchMicroservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.SearchMicroservice.model.Soba;
import ftn.uns.ac.rs.SearchMicroservice.model.Zauzece;
import ftn.uns.ac.rs.SearchMicroservice.repository.SobaRepository;
import ftn.uns.ac.rs.SearchMicroservice.repository.ZauzeceRepository;
import ftn.uns.ac.rs.SearchMicroservice.specifications.SobaSpecifications;
import ftn.uns.ac.rs.SearchMicroservice.specifications.ZauzeceSpecifications;

@Service
public class SobaService {

	@Autowired
	SobaRepository sobaRepo;
	
	@Autowired
	ZauzeceRepository zauzeceRepo;

	public List<Soba> search(String tip, Long smestaj, List<Long> usluge, Date begin, Date end){
		
		List<Zauzece> zauzeca = zauzeceRepo.findAll(ZauzeceSpecifications.findByDate(begin, end));
		List<Long> idZauzeca = new ArrayList<Long>();
		for(Zauzece z : zauzeca) {
			idZauzeca.add(z.getId());
		}
		
		List<Soba> sobeTipSmestaj = sobaRepo.findAll(SobaSpecifications.findByTipSmestaj(tip, smestaj));
		List<Long> idSobeTipSmestaj = new ArrayList<Long>();
		for(Soba s : sobeTipSmestaj) {
			idSobeTipSmestaj.add(s.getId());
		}
		
		if(usluge != null) {
			
			List<Long> ret = new ArrayList<Long>();
			for(Long soba : idSobeTipSmestaj) {
				Long brojac = 0l;
				Long soba_usluga = null;
				for(Long usluga : usluge) {
					soba_usluga = sobaRepo.findBySobaUsluga(soba, usluga);
					if(soba_usluga != null)
						brojac++;
				}
				if(brojac == usluge.size() && soba_usluga != null)
					ret.add(soba_usluga);
			}
			List<Long> retFinal = zauzeceRepo.findBySobaZauzece(ret, idZauzeca);
			for(Long id : sobaRepo.findBySobeNotZauzece(zauzeceRepo.findSobe(), ret)) {
				retFinal.add(id);
			}
			retFinal = retFinal.stream().distinct().collect(Collectors.toList());
			return sobaRepo.findAllSobe(retFinal);
		}
		List<Long> retFinal = zauzeceRepo.findBySobaZauzece(idSobeTipSmestaj, idZauzeca);
		for(Long id : sobaRepo.findBySobeNotZauzece(zauzeceRepo.findSobe(), idSobeTipSmestaj)) {
			retFinal.add(id);
		}
		retFinal = retFinal.stream().distinct().collect(Collectors.toList());
		return sobaRepo.findAllSobe(retFinal);
	}
}
