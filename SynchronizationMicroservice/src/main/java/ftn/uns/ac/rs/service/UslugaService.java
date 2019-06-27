package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Usluga;
import ftn.uns.ac.rs.repository.UslugaRepository;

@Service
public class UslugaService {
	@Autowired
	private UslugaRepository UslugaRepo;
	
	public List<Usluga> getAll(){
		List<Usluga> dtos = new ArrayList<Usluga>();
		for(Usluga s : UslugaRepo.findAll()) {
			dtos.add(s);
		}
		return dtos;
	}
	
}
