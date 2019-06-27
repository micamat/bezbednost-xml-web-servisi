package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.TipSobe;
import ftn.uns.ac.rs.repository.TipSobeRepository;

@Service
public class TipSobeService {
	@Autowired
	private TipSobeRepository TipSobeRepo;
	
	public List<TipSobe> getAll(){
		List<TipSobe> dtos = new ArrayList<TipSobe>();
		for(TipSobe s : TipSobeRepo.findAll()) {
			dtos.add(s);
		}
		return dtos;
	}
	
}
