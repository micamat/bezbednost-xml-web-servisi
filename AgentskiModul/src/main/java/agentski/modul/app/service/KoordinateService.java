package agentski.modul.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentski.modul.app.model.kordinate.Koordinate;
import agentski.modul.app.modelDTO.KoordinateDTO;
import agentski.modul.app.repository.KoordinateRepository;

@Service
public class KoordinateService {
	
	@Autowired
	private KoordinateRepository koordinateRepository;

	public List<KoordinateDTO> getAll(){ 
		return koordinateRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public KoordinateDTO getById(Long id) {
		if(!koordinateRepository.existsById(id)) {
			return null;
		}
		Koordinate koordinate = koordinateRepository.findById(id).orElse(null);
		return convertToDTO(koordinate);
	}
	
	
	public boolean add(KoordinateDTO koordinateDTO) {
		koordinateDTO.setId(null);
		if(koordinateRepository.save(convertToEntity(koordinateDTO)) != null){
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(koordinateRepository.existsById(id)) {
			koordinateRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private KoordinateDTO convertToDTO(Koordinate koordinate) {
		KoordinateDTO koordinateDTO = new KoordinateDTO();
		koordinateDTO.setId(koordinate.getId());
		koordinateDTO.setSirina(koordinate.getSirina());
		koordinateDTO.setDuzina(koordinate.getDuzina());
		return koordinateDTO;
	}
	
	private Koordinate convertToEntity(KoordinateDTO koordinateDTO) {
		Koordinate koordinate = new Koordinate();
		koordinate.setId(koordinateDTO.getId());
		koordinate.setSirina(koordinateDTO.getSirina());
		koordinate.setDuzina(koordinateDTO.getDuzina());
		return koordinate;
	}
}
