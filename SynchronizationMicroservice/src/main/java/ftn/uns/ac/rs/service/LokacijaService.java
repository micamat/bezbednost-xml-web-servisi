package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Koordinate;
import ftn.uns.ac.rs.model.KoordinateDTO;
import ftn.uns.ac.rs.model.Lokacija;
import ftn.uns.ac.rs.model.LokacijaDTO;
import ftn.uns.ac.rs.repository.KoordinateRepository;
import ftn.uns.ac.rs.repository.LokacijaRepository;

@Service
public class LokacijaService {

	@Autowired
	private LokacijaRepository lokacijaRepo;

	@Autowired
	private KoordinateRepository koordinateRepository;
	
	
	public List<LokacijaDTO> getAll(){
		List<LokacijaDTO> dtos = new ArrayList<>();
		for(Lokacija s : lokacijaRepo.findAll()) {
			dtos.add(lokacijaToDTO(s));
		}
		return dtos;
	}
	
	public int create(LokacijaDTO p){
		Lokacija sm = lokacijaToEntity(p);
		int id = -1;
		Lokacija s = lokacijaRepo.save(sm);
		if(s == null) {
			return id;
		}
		else {
			return (int)s.getId();
		}
	}
	
	private LokacijaDTO lokacijaToDTO(Lokacija lokacija) {
		LokacijaDTO dto = new LokacijaDTO();
		dto.setId(lokacija.getId());
		dto.setGrad(lokacija.getGrad());
		dto.setDrzava(lokacija.getDrzava());
		dto.setUlica(lokacija.getUlica());
		dto.setBroj(lokacija.getBroj());
		dto.setIdKoordinate(lokacija.getKoordinate().getId());
		return dto;
	}
	
	private Lokacija lokacijaToEntity(LokacijaDTO lokacija) {
		Lokacija l = new Lokacija();
		l.setId(lokacija.getId());
		l.setGrad(lokacija.getGrad());
		l.setDrzava(lokacija.getDrzava());
		l.setUlica(lokacija.getUlica());
		l.setBroj(lokacija.getBroj());
		l.setKoordinate(koordinateRepository.findById(lokacija.getIdKoordinate()).get());
		return l;
	}
}
