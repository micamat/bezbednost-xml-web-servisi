package ftn.uns.ac.rs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateKoordinateRequest;
import ftn.uns.ac.rs.model.CreateKoordinateResponse;
import ftn.uns.ac.rs.model.GetAllKoordinateRequest;
import ftn.uns.ac.rs.model.GetAllKoordinateResponse;
import ftn.uns.ac.rs.model.KoordinateDTO;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.repository.KoordinateRepository;

@Service
public class KoordinateService {
	
	@Autowired
	private KoordinateRepository koordinateRepository;

	/*public List<KoordinateDTO> getAll(){ 
		return koordinateRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};*/
	
	//TODO: Implementirati poslovnu logiku ..... cuvanja u bazu kao i 
			public List<KoordinateDTO> getAllSync(){
				ProducerPortService producerPortService = new ProducerPortService();
				ProducerPort producerPort = producerPortService.getProducerPortSoap11();
				
				GetAllKoordinateRequest getAllKoordinateRequest = new GetAllKoordinateRequest();
				GetAllKoordinateResponse getAllKoordinateResponse = producerPort.getAllKoordinate(getAllKoordinateRequest);
				return getAllKoordinateResponse.getKoordinateDTO();
			};
			
			
			public int createSync(KoordinateDTO cmd){
				ProducerPortService producerPortService = new ProducerPortService();
				ProducerPort producerPort = producerPortService.getProducerPortSoap11();
				
				CreateKoordinateRequest createKoordinateRequest = new CreateKoordinateRequest();
				createKoordinateRequest.setId(cmd.getId());
				createKoordinateRequest.setDuzina(cmd.getDuzina());
				createKoordinateRequest.setSirina(cmd.getSirina());
				
				CreateKoordinateResponse createKoordinateResponse = producerPort.createKoordinate(createKoordinateRequest);
				return createKoordinateResponse.getId();
			};
		
	
	/*
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
	}*/
}
