package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Koordinate;
import ftn.uns.ac.rs.model.KoordinateDTO;
import ftn.uns.ac.rs.repository.KoordinateRepository;

@Service
public class KoordinateService {
	
	@Autowired
	private KoordinateRepository koordinateRepo;

	
	
	public List<KoordinateDTO> getAll(){
		List<KoordinateDTO> dtos = new ArrayList<>();
		for(Koordinate s : koordinateRepo.findAll()) {
			dtos.add(koordinateToDTO(s));
		}
		return dtos;
	}
	
	public int create(KoordinateDTO p){
		Koordinate sm = koordinateToEntity(p);
		int id = -1;
		Koordinate s = koordinateRepo.save(sm);
		if(s == null) {
			return id;
		}
		else {
			return (int)s.getId();
		}
	}
	
	private KoordinateDTO koordinateToDTO(Koordinate koordinate) {
		KoordinateDTO dto = new KoordinateDTO();
		dto.setId(koordinate.getId());
		dto.setDuzina(koordinate.getDuzina());
		dto.setSirina(koordinate.getSirina());
		return dto;
	}
	
	private Koordinate koordinateToEntity(KoordinateDTO koordinate) {
		Koordinate k = new Koordinate();
		k.setId(koordinate.getId());
		k.setDuzina(koordinate.getDuzina());
		k.setSirina(koordinate.getSirina());
		return k;
	}
}
