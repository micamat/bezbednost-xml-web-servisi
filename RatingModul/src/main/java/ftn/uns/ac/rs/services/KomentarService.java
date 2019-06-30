package ftn.uns.ac.rs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.DTOs.KomentarDTO;
import ftn.uns.ac.rs.model.Komentar;
import ftn.uns.ac.rs.model.Korisnik;
import ftn.uns.ac.rs.model.Rezervacija;
import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.repository.KomentarRepository;
import ftn.uns.ac.rs.repository.KorisnikRepository;
import ftn.uns.ac.rs.repository.RezervacijaRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;

@Service
public class KomentarService {

	@Autowired
	private KomentarRepository komentarRepository;

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private SmestajRepository smestajRepository;

	@Autowired
	private RezervacijaRepository rezervacijaRepository;

	public List<KomentarDTO> getAll() {
		List<Komentar> komentari = komentarRepository.findAll();
		List<KomentarDTO> ret = new ArrayList<KomentarDTO>();
		for (Komentar k : komentari) {
			KomentarDTO kom = new KomentarDTO(k.getId(), k.getNaslov(), k.getTekst(), k.getStatusKomentara(),
					k.getDatumKreiranja(), k.getRezervacija().getId(), k.getSmestaj().getId(),
					k.getKorisnik().getKorisnickoIme());
			ret.add(kom);
		}
		return ret;
	}

	public List<KomentarDTO> getNeodobreni() {
		List<Komentar> komentari = komentarRepository.findAllKomentarByStatusKomentara("neodobren");
		List<KomentarDTO> ret = new ArrayList<KomentarDTO>();
		for (Komentar k : komentari) {
			KomentarDTO kom = new KomentarDTO(k.getId(), k.getNaslov(), k.getTekst(), k.getStatusKomentara(),
					k.getDatumKreiranja(), k.getRezervacija().getId(), k.getSmestaj().getId(),
					k.getKorisnik().getKorisnickoIme());
			ret.add(kom);
		}
		return ret;
	}

	public List<KomentarDTO> getOdobreni() {
		List<Komentar> komentari = komentarRepository.findAllKomentarByStatusKomentara("odobren");
		List<KomentarDTO> ret = new ArrayList<KomentarDTO>();
		for (Komentar k : komentari) {
			KomentarDTO kom = new KomentarDTO(k.getId(), k.getNaslov(), k.getTekst(), k.getStatusKomentara(),
					k.getDatumKreiranja(), k.getRezervacija().getId(), k.getSmestaj().getId(),
					k.getKorisnik().getKorisnickoIme());
			ret.add(kom);
		}
		return ret;
	}

	public KomentarDTO changeStatus(Long id) {
		Optional<Komentar> k = komentarRepository.findById(id);
		if (k.isPresent()) {
			Komentar kom = k.get();
			kom.setStatusKomentara("odobren");
			komentarRepository.save(kom);
			KomentarDTO ret = new KomentarDTO(kom.getId(), kom.getNaslov(), kom.getTekst(), kom.getStatusKomentara(),
					kom.getDatumKreiranja(), kom.getRezervacija().getId(), kom.getSmestaj().getId(),
					kom.getKorisnik().getKorisnickoIme());
			return ret;
		} else {
			return null;
		}
	}

	public Boolean delete(Long id) {
		Optional<Komentar> k = komentarRepository.findById(id);
		if (k.isPresent()) {
			komentarRepository.delete(k.get());
			return true;
		} else {
			return false;
		}
	}

	public Boolean add(KomentarDTO k) {
		
		Korisnik korisnik=korisnikRepository.findByKorisnickoIme(k.getUsernameKorisnika());
		Smestaj sm=smestajRepository.findById(k.getIdSmestaja()).get();
		Rezervacija r=rezervacijaRepository.findById(k.getIdRezervacije()).get();
		
		Komentar komentar= new Komentar(k.getId(), k.getNaslov(), k.getTekst(), k.getStatusKomentara(), k.getDatumKreiranja(), r, sm, korisnik);
		Komentar kom = komentarRepository.save(komentar);
		List<Komentar>sviKomentariSmestaja=sm.getKomentar();
		sviKomentariSmestaja.add(kom);
		sm.setKomentar(sviKomentariSmestaja);
		smestajRepository.save(sm);
		if (kom != null) {
			return true;
		}
		return false;
	}

}
