package ftn.uns.ac.rs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.repository.SmestajRepository;

@Service
public class SmestajService {
	@Autowired
	private SmestajRepository smestajRepo;
	
	public List<Smestaj> getAll(){
		return smestajRepo.findAll();
	}
}
