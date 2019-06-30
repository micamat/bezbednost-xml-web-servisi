package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Rezervacija;
import ftn.uns.ac.rs.model.TipSmestaja;
import ftn.uns.ac.rs.repository.RezervacijaRepository;

@Service
public class ReservationService {
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	public List<Rezervacija> getAll(){
		List<Rezervacija> dtos = new ArrayList<Rezervacija>();
		for(Rezervacija s : rezervacijaRepository.findAll()) {
			dtos.add(s);
		}
		return dtos;
	}
}
