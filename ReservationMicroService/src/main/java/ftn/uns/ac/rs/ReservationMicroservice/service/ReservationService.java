package ftn.uns.ac.rs.ReservationMicroservice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.ReservationMicroservice.dto.RezervacijaDTO;
import ftn.uns.ac.rs.ReservationMicroservice.model.Cenovnik;
import ftn.uns.ac.rs.ReservationMicroservice.model.IstorijaRezervacije;
import ftn.uns.ac.rs.ReservationMicroservice.model.Rezervacija;
import ftn.uns.ac.rs.ReservationMicroservice.model.StatusRezervacije;
import ftn.uns.ac.rs.ReservationMicroservice.repository.IstorijaRezervacijeRepository;
import ftn.uns.ac.rs.ReservationMicroservice.repository.KorisnikRepository;
import ftn.uns.ac.rs.ReservationMicroservice.repository.ReservationRepository;
import ftn.uns.ac.rs.ReservationMicroservice.repository.SobaRepository;
import ftn.uns.ac.rs.ReservationMicroservice.repository.StatusRezervacijeRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository resRepo;
	
	@Autowired
	private KorisnikRepository korRepo;
	
	@Autowired
	private SobaRepository sobRepo;
	
	@Autowired 
	private StatusRezervacijeRepository sreRepo;
	
	@Autowired
	private IstorijaRezervacijeRepository ireRepo;
	
	public boolean add(RezervacijaDTO reservationDTO) {
		Rezervacija rez = new Rezervacija();
		int brSoba = 0;
		for (Integer i : reservationDTO.getBrojSoba()) {
			brSoba+=i;
		}
		rez.setBrojSoba(brSoba);
		rez.setDatumDo(reservationDTO.getDatumTo());
		rez.setDatumOd(reservationDTO.getDatumFrom());
		//rez.setCena(reservationDTO.getCena());
		//rez.setKorisnik(korRepo.findById(reservationDTO.getIdKorisnika()).get());
		//rez.setSoba(sobRepo.findById(reservationDTO.getIdSobe()).get());
		resRepo.save(rez);
		
		StatusRezervacije sre = sreRepo.findByStatus("Rezervisano");
		Date d = new Date();
		IstorijaRezervacije ire = new IstorijaRezervacije();
		ire.setStatusRezervacije(sre);
		ire.setRezervacija(rez);
		ire.setDatum(d);
		
		ireRepo.save(ire);
		
		return true;
	}
}
