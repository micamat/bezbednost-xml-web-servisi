package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Korisnik;
import ftn.uns.ac.rs.model.KorisnikDTO;
import ftn.uns.ac.rs.repository.KorisnikRepository;

@Service
public class KorisnikService {
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public List<KorisnikDTO> getAll(){
		List<KorisnikDTO> dtos = new ArrayList<KorisnikDTO>();
		for(Korisnik s : korisnikRepository.findAll()) {
			KorisnikDTO k = new KorisnikDTO();
			k.setId(s.getId());
			k.setKorisnickoIme(s.getKorisnickoIme());
			dtos.add(k);
		}
		return dtos;
	}
}
