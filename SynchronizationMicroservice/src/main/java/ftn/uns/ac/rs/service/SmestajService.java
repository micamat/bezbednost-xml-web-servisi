package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.KategorijaSmestaja;
import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.repository.KategorijaSmestajaRepository;
import ftn.uns.ac.rs.repository.LokacijaRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.TipSmestajaRepository;

@Service
public class SmestajService {
	@Autowired
	private SmestajRepository smestajRepo;
	
	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;
	
	@Autowired
	private LokacijaRepository lokacijaRepository;
	
	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;
	
	public List<SmestajDTO> getAll(){
		List<SmestajDTO> dtos = new ArrayList<>();
		for(Smestaj s : smestajRepo.findAll()) {
			dtos.add(smestajToDTO(s));
		}
		return dtos;
	}
	
	public int create(SmestajDTO p){
		Smestaj sm = smestajToEntity(p);
		int id = -1;
		Smestaj s = smestajRepo.save(sm);
		if(s == null) {
			return id;
		}
		else {
			return (int)s.getId();
		}
	}
	
	private SmestajDTO smestajToDTO(Smestaj smestaj) {
		SmestajDTO dto = new SmestajDTO();
		dto.setId(smestaj.getId());
		dto.setIdKategorijaSmestaja(smestaj.getKategorijaSmestaja().getId());
		dto.setIdLokacija(smestaj.getLokacija().getId());
		dto.setIdTipSmestaja(smestaj.getTipSmestaja().getId());
		dto.setNaziv(smestaj.getNaziv());
		dto.setOpis(smestaj.getOpis());
		dto.setSlika(smestaj.getSlika());
		return dto;
	}
	
	private Smestaj smestajToEntity(SmestajDTO smestaj) {
		Smestaj sm = new Smestaj();
		sm.setNaziv(smestaj.getNaziv());
		sm.setOpis(smestaj.getOpis());
		sm.setSlika(smestaj.getSlika());
		sm.setKategorijaSmestaja(kategorijaSmestajaRepository.findById(smestaj.getIdKategorijaSmestaja()).get());
		sm.setLokacija(lokacijaRepository.findById(smestaj.getIdLokacija()).get());
		sm.setTipSmestaja(tipSmestajaRepository.findById(smestaj.getIdTipSmestaja()).get());
		return sm;
	}
}
