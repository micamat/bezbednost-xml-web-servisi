package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.TipSmestaja;
import ftn.uns.ac.rs.repository.TipSmestajaRepository;

@Service
public class TipSmestajaService {
	@Autowired
	private TipSmestajaRepository TipSmestajaRepo;
	
	public List<TipSmestaja> getAll(){
		List<TipSmestaja> dtos = new ArrayList<TipSmestaja>();
		for(TipSmestaja s : TipSmestajaRepo.findAll()) {
			dtos.add(s);
		}
		return dtos;
	}

}
