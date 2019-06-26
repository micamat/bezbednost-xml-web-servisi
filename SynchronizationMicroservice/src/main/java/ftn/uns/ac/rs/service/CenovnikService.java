package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Cenovnik;
import ftn.uns.ac.rs.model.CenovnikDTO;
import ftn.uns.ac.rs.repository.CenovnikRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.TipSobeRepository;

@Service
public class CenovnikService {
	/*
	@Autowired
	private SmestajRepository smestajRepo;
	
	@Autowired
	private TipSobeRepository tipSobaRepo;
	
	@Autowired
	private CenovnikRepository cenovnikRepo;
	
	
	public List<CenovnikDTO> getAll(){
		List<CenovnikDTO> dtos = new ArrayList<>();
		for(Cenovnik s : cenovnikRepo.findAll()) {
			dtos.add(cenovnikToDTO(s));
		}
		return dtos;
	}
	
	public int create(CenovnikDTO p){
		Cenovnik sm = cenovnikToEntity(p);
		int id = -1;
		Cenovnik s = cenovnikRepo.save(sm);
		if(s == null) {
			return id;
		}
		else {
			return (int)s.getId();
		}
	}
	
	private CenovnikDTO cenovnikToDTO(Cenovnik cenovnik) {
		CenovnikDTO dto = new CenovnikDTO();
		dto.setId(cenovnik.getId());
		dto.setCena(cenovnik.getCena());
		dto.setDatumDo(cenovnik.getDatumDo());
		dto.setDatumOd(cenovnik.getDatumOd());
		dto.setIdSmestaj(cenovnik.getSmestaj().getId());
		dto.setIdTipSobe(cenovnik.getTipSobe().getId());
		return dto;
	}
	
	private Cenovnik cenovnikToEntity(CenovnikDTO cenovnik) {
		Cenovnik c = new Cenovnik();
		c.setId(cenovnik.getId());
		c.setCena(cenovnik.getCena());
		c.setDatumDo(cenovnik.getDatumDo());
		c.setDatumOd(cenovnik.getDatumOd());
		c.setSmestaj(smestajRepo.findById(cenovnik.getIdSmestaj()).get());
		c.setTipSobe(tipSobaRepo.findById(cenovnik.getIdTipSobe()).get());
		return c;
	}*/
}
