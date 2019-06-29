package ftn.uns.ac.rs.SearchMicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.SearchMicroservice.model.Smestaj;
import ftn.uns.ac.rs.SearchMicroservice.repository.SmestajRepository;
import ftn.uns.ac.rs.SearchMicroservice.specifications.SmestajSpecifications;

@Service
public class SmestajService {
	@Autowired
	SmestajRepository smestajRepo;
	
	public List<Smestaj> search(String lokacija, String tip, String kategorija){
		return smestajRepo.findAll(SmestajSpecifications.findByLokacijaTipKategorija(lokacija, tip, kategorija));
	}
}
