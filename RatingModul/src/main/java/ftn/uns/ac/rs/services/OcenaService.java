package ftn.uns.ac.rs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.DTOs.OcenaDTO;
import ftn.uns.ac.rs.model.Korisnik;
import ftn.uns.ac.rs.model.Ocena;
import ftn.uns.ac.rs.model.Rezervacija;
import ftn.uns.ac.rs.repository.KorisnikRepository;
import ftn.uns.ac.rs.repository.OcenaRepository;
import ftn.uns.ac.rs.repository.RezervacijaRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;

@Service
public class OcenaService {

	@Autowired
	private OcenaRepository ocenaRepository;

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private SmestajRepository smestajRepository;

	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	public double getProsecnaOcena(Long id) {
		List<Ocena> ocene = ocenaRepository.findOcenaByRezervacijaSmestajId(id);
		if (ocene.size() == 0) {
			return -1;
		}
		double suma = 0;
		for (Ocena o : ocene) {
			suma += o.getValue();
		}
		double prosek = suma / ocene.size();
		return prosek;
	}

	public boolean add(OcenaDTO ocenaDTO) {
		Rezervacija rez=rezervacijaRepository.findById(ocenaDTO.getIdRezervacije()).get();
		Korisnik k=korisnikRepository.findByKorisnickoIme(ocenaDTO.getUsernameKorisnika());
		Ocena o=new Ocena(ocenaDTO.getValue(), rez, k);
		Ocena snimljenaOcena=ocenaRepository.save(o);
		if(snimljenaOcena==null) {
			return false;
		}
		return true;
	}
	
}
