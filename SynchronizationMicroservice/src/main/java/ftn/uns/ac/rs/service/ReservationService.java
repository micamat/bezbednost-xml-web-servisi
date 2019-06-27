package ftn.uns.ac.rs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Rezervacija;
import ftn.uns.ac.rs.repository.RezervacijaRepository;

@Service
public class ReservationService {
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	public List<Rezervacija> getAll(){
		return rezervacijaRepository.findAll();
	}
}
