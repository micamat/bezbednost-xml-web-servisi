package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Usluga;
import ftn.uns.ac.rs.model.UslugaDTO;
import ftn.uns.ac.rs.repository.UslugaRepository;

@Service
public class UslugaService {
	@Autowired
	private UslugaRepository UslugaRepo;
	
	public List<UslugaDTO> getAll(){
		List<UslugaDTO> dtos = new ArrayList<UslugaDTO>();
		for(Usluga s : UslugaRepo.findAll()) {
			dtos.add(UslugaToDTO(s));
		}
		return dtos;
	}
	
	public int create(UslugaDTO dto) {
		Usluga ss = UslugaToEntity(dto);
		int id = -1;
		Usluga s = UslugaRepo.save(ss);
		if(s == null) {
			return id;
		}
		else {
			return (int)s.getId();
		}
	}
	
	private UslugaDTO UslugaToDTO(Usluga Usluga) {
		UslugaDTO dto = new UslugaDTO();
		dto.setNaziv(Usluga.getNaziv());
		dto.setId(Usluga.getId());
		dto.setOpis(Usluga.getOpis());
		return dto;
	}
	
	private Usluga UslugaToEntity(UslugaDTO dto) {
		Usluga ss = new Usluga();
		ss.setId(dto.getId());
		ss.setNaziv(dto.getNaziv());
		ss.setOpis(dto.getOpis());
		return ss;
	}
}
