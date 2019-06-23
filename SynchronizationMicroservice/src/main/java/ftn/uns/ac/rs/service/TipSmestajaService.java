package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.TipSmestaja;
import ftn.uns.ac.rs.model.TipSmestajaDTO;
import ftn.uns.ac.rs.repository.TipSmestajaRepository;

@Service
public class TipSmestajaService {
	@Autowired
	private TipSmestajaRepository TipSmestajaRepo;
	
	public List<TipSmestajaDTO> getAll(){
		List<TipSmestajaDTO> dtos = new ArrayList<TipSmestajaDTO>();
		for(TipSmestaja s : TipSmestajaRepo.findAll()) {
			dtos.add(TipSmestajaToDTO(s));
		}
		return dtos;
	}
	
	public int create(TipSmestajaDTO dto) {
		TipSmestaja ss = TipSmestajaToEntity(dto);
		int id = -1;
		TipSmestaja s = TipSmestajaRepo.save(ss);
		if(s == null) {
			return id;
		}
		else {
			return (int)s.getId();
		}
	}
	
	private TipSmestajaDTO TipSmestajaToDTO(TipSmestaja TipSmestaja) {
		TipSmestajaDTO dto = new TipSmestajaDTO();
		dto.setNaziv(TipSmestaja.getNaziv());
		dto.setId(TipSmestaja.getId());
		dto.setOpis(TipSmestaja.getOpis());
		return dto;
	}
	
	private TipSmestaja TipSmestajaToEntity(TipSmestajaDTO dto) {
		TipSmestaja ss = new TipSmestaja();
		ss.setId(dto.getId());
		ss.setNaziv(dto.getNaziv());
		ss.setOpis(dto.getOpis());
		return ss;
	}
}
