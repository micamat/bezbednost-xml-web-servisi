package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.StatusSobe;
import ftn.uns.ac.rs.model.StatusSobeDTO;
import ftn.uns.ac.rs.repository.StatusSobeRepository;

@Service
public class StatusSobeService {
	@Autowired
	private StatusSobeRepository statusSobeRepo;
	
	public List<StatusSobeDTO> getAll(){
		List<StatusSobeDTO> dtos = new ArrayList<StatusSobeDTO>();
		for(StatusSobe s : statusSobeRepo.findAll()) {
			dtos.add(statusSobeToDTO(s));
		}
		return dtos;
	}
	
	public int create(StatusSobeDTO dto) {
		StatusSobe ss = statusSobeToEntity(dto);
		int id = -1;
		StatusSobe s = statusSobeRepo.save(ss);
		if(s == null) {
			return id;
		}
		else {
			return (int)s.getId();
		}
	}
	
	private StatusSobeDTO statusSobeToDTO(StatusSobe statusSobe) {
		StatusSobeDTO dto = new StatusSobeDTO();
		dto.setNaziv(statusSobe.getNaziv());
		dto.setId(statusSobe.getId());
		dto.setOpis(statusSobe.getOpis());
		return dto;
	}
	
	private StatusSobe statusSobeToEntity(StatusSobeDTO dto) {
		StatusSobe ss = new StatusSobe();
		ss.setId(dto.getId());
		ss.setNaziv(dto.getNaziv());
		ss.setOpis(dto.getOpis());
		return ss;
	}
}
