package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Komentar;
import ftn.uns.ac.rs.model.KomentarDTO;
import ftn.uns.ac.rs.repository.KomentarRepository;

@Service
public class KomentarService {
	@Autowired
	private KomentarRepository komentarRepository;
	
	public List<KomentarDTO> getAll(){
		List<KomentarDTO> dtos = new ArrayList<KomentarDTO>();
		for(Komentar s : komentarRepository.findAll()) {
			KomentarDTO k = new KomentarDTO();
			k.setDatumKreiranja(s.getDatumKreiranja());
			k.setId(s.getId());
			k.setIdKorisnik(s.getKorisnik().getId());
			k.setIdSmestaj(s.getSmestaj().getId());
			k.setNaslov(s.getNaslov());
			k.setStatusKomentara(s.getStatusKomentara());
			k.setTekst(s.getTekst());
			dtos.add(k);
		}
		return dtos;
	}
}
