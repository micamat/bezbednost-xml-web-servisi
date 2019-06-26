package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.KategorijaSmestaja;
import ftn.uns.ac.rs.repository.KategorijaSmestajaRepository;

@Service
public class KategorijaSmestajaService {
	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;
	
	public List<KategorijaSmestaja> getAll(){
		List<KategorijaSmestaja> dtos = new ArrayList<>();
		for(KategorijaSmestaja s : kategorijaSmestajaRepository.findAll()) {
			dtos.add(s);
		}
		return dtos;
	}

}
