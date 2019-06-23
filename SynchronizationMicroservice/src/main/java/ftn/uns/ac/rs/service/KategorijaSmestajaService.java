package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.KategorijaSmestaja;
import ftn.uns.ac.rs.model.KategorijaSmestajaDTO;
import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.repository.KategorijaSmestajaRepository;
import ftn.uns.ac.rs.repository.LokacijaRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.TipSmestajaRepository;

@Service
public class KategorijaSmestajaService {
	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;
	
	public List<KategorijaSmestajaDTO> getAll(){
		List<KategorijaSmestajaDTO> dtos = new ArrayList<>();
		for(KategorijaSmestaja s : kategorijaSmestajaRepository.findAll()) {
			dtos.add(kategorijaSmestajaToDTO(s));
		}
		return dtos;
	}
	
	public int create(KategorijaSmestajaDTO p){
		KategorijaSmestaja sm = kategorijaSmestajaToEntity(p);
		int id = -1;
		KategorijaSmestaja s = kategorijaSmestajaRepository.save(sm);
		if(s == null) {
			return id;
		}
		else {
			return (int)s.getId();
		}
	}
	
	private KategorijaSmestajaDTO kategorijaSmestajaToDTO(KategorijaSmestaja kategorijaSmestaja) {
		KategorijaSmestajaDTO dto = new KategorijaSmestajaDTO();
		dto.setId(kategorijaSmestaja.getId());
		dto.setNaziv(kategorijaSmestaja.getNaziv());
		dto.setOpis(kategorijaSmestaja.getOpis());
		return dto;
	}
	
	private KategorijaSmestaja kategorijaSmestajaToEntity(KategorijaSmestajaDTO kategorijaSmestaja) {
		KategorijaSmestaja sm = new KategorijaSmestaja();
		sm.setId(kategorijaSmestaja.getId());
		sm.setNaziv(kategorijaSmestaja.getNaziv());
		sm.setOpis(kategorijaSmestaja.getOpis());
		return sm;
	}
}
