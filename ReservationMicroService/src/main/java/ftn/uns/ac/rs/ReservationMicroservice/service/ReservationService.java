package ftn.uns.ac.rs.ReservationMicroservice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.ReservationMicroservice.dto.ReservationDTO;
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
	
	public boolean add(ReservationDTO reservationDTO) {
		Rezervacija rez = new Rezervacija();
		rez.setDatumDo(reservationDTO.getDatumDo());
		rez.setDatumOd(reservationDTO.getDatumOd());
		rez.setCena(reservationDTO.getCena());
		rez.setKorisnik(korRepo.findById(reservationDTO.getIdKorisnika()).get());
		rez.setSoba(sobRepo.findById(reservationDTO.getIdSobe()).get());
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
