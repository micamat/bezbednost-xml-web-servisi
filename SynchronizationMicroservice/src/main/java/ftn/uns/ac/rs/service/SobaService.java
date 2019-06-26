package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Soba;
import ftn.uns.ac.rs.model.SobaDTO;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.SobaRepository;
import ftn.uns.ac.rs.repository.TipSobeRepository;

@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;
	
	@Autowired
	private SmestajRepository smestajRepository;
	
	@Autowired 
	private TipSobeRepository tipSobeRepo;
	
	
	public List<SobaDTO> getAll(){
		List<SobaDTO> dtos = new ArrayList<>();
		for(Soba s : sobaRepository.findAll()) {
			dtos.add(sobaToDTO(s));
		}
		return dtos;
	}
	
	public int create(SobaDTO p){
		Soba sm = sobaToEntity(p);
		int id = -1;
		Soba s = sobaRepository.save(sm);
		if(s == null) {
			return id;
		}
		else {
			return (int)s.getId();
		}
	}
	
	private SobaDTO sobaToDTO(Soba soba) {
		SobaDTO dto = new SobaDTO();
		dto.setId(soba.getId());
		dto.setNaziv(soba.getNaziv());
		dto.setOpis(soba.getOpis());
		dto.setIdSmestaj(soba.getSmestaj().getId());
		dto.setIdTipSobe(soba.getTipSobe().getId());
		return dto;
	}
	
	private Soba sobaToEntity(SobaDTO soba) {
		Soba s = new Soba();
		s.setId(soba.getId());
		s.setNaziv(soba.getNaziv());
		s.setOpis(soba.getOpis());
		s.setSmestaj(smestajRepository.findById(soba.getIdSmestaj()).get());
		s.setTipSobe(tipSobeRepo.findById(soba.getIdTipSobe()).get());
		return s;
	}
}
