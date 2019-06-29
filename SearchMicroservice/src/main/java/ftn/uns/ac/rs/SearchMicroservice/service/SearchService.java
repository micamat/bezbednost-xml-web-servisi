package ftn.uns.ac.rs.SearchMicroservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.SearchMicroservice.model.Rezervacija;
import ftn.uns.ac.rs.SearchMicroservice.model.Smestaj;
import ftn.uns.ac.rs.SearchMicroservice.repository.RezervacijaRepository;
import ftn.uns.ac.rs.SearchMicroservice.repository.SmestajRepository;
import ftn.uns.ac.rs.SearchMicroservice.repository.SobneUslugeRepository;
import ftn.uns.ac.rs.SearchMicroservice.specifications.RezervacijaSpecifications;
import ftn.uns.ac.rs.SearchMicroservice.specifications.SmestajSpecifications;

@Service
public class SearchService {
	@Autowired
	SobneUslugeRepository sobneUslugeRepo;
	
	@Autowired
	RezervacijaRepository rezervacijaRepo;
	
	@Autowired
	SmestajRepository smestajRepo;
	
	public List<Smestaj> findSmestaj(String lokacija, Date datumOd, Date datumDo, int brojOsoba, String tipSmestaja, String katSmestaja, List<String> nazivUsluge){
		List<Smestaj> smestaji = smestajRepo.findAll(SmestajSpecifications.findByLokacijaTipKategorija(lokacija, tipSmestaja, katSmestaja, brojOsoba));
		List<Rezervacija> rezervacije = rezervacijaRepo.findAll(RezervacijaSpecifications.findByDate(datumOd, datumDo));
		
	}
}
