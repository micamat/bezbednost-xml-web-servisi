package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateLokacijaRequest;
import ftn.uns.ac.rs.model.CreateLokacijaResponse;
import ftn.uns.ac.rs.model.GetAllLokacijaRequest;
import ftn.uns.ac.rs.model.GetAllLokacijaResponse;
import ftn.uns.ac.rs.model.Lokacija;
import ftn.uns.ac.rs.model.LokacijaDTO;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.repository.KoordinateRepository;
import ftn.uns.ac.rs.repository.LokacijaRepository;

@Service
public class LokacijaService {
	
	@Autowired
	private LokacijaRepository lokacijaRepository;
	
	@Autowired
	private KoordinateRepository koordinateRepository;

	public List<LokacijaDTO> getAll(){ 
		return lokacijaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public int createSync(LokacijaDTO cmd){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateLokacijaRequest createLokacijaRequest = new CreateLokacijaRequest();
		createLokacijaRequest.setId(cmd.getId());
		createLokacijaRequest.setBroj(cmd.getBroj());
		createLokacijaRequest.setDrzava(cmd.getDrzava());
		createLokacijaRequest.setGrad(cmd.getGrad());
		createLokacijaRequest.setUlica(cmd.getUlica());
		//createLokacijaRequest.setIdKoordinate(cmd.getIdKoordinate());
		CreateLokacijaResponse createLokacijaResponse = producerPort.createLokacija(createLokacijaRequest);
		return createLokacijaResponse.getId();
	};
	
	
	
	public LokacijaDTO getById(Long id) {
		if(!lokacijaRepository.existsById(id)) {
			return null;
		}
		Lokacija lokacija = lokacijaRepository.findById(id).orElse(null);
		return convertToDTO(lokacija);
	}
	
	
	public boolean add(LokacijaDTO lokacijaDTO) {
		lokacijaDTO.setId(lokacijaDTO.getId());
		Lokacija  lokacija = lokacijaRepository.save(convertToEntity(lokacijaDTO));
		if(lokacija != null) {
			createSync(convertToDTO(lokacija));
			return true;
		}
		return false;
	}
	
	public Lokacija add(Lokacija lokacija) {
		lokacija.setId(lokacija.getId());
		Lokacija l = lokacijaRepository.save(lokacija);
		if(l != null) {
			return l;
		}
		return null;
	}
	
	public boolean delete(Long id) {
		if(lokacijaRepository.existsById(id)) {
			lokacijaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private LokacijaDTO convertToDTO(Lokacija lokacija) {
		LokacijaDTO lokacijaDTO = new LokacijaDTO();
		lokacijaDTO.setId(lokacija.getId());
		lokacijaDTO.setDrzava(lokacija.getDrzava());
		lokacijaDTO.setGrad(lokacija.getGrad());
		lokacijaDTO.setUlica(lokacija.getUlica());
		lokacijaDTO.setBroj(lokacija.getBroj());
		//lokacijaDTO.setIdKoordinate(lokacija.getKoordinate().getId());
		return lokacijaDTO;
	}
	
	private Lokacija convertToEntity(LokacijaDTO lokacijaDTO) {
		Lokacija lokacija = new Lokacija();
		lokacija.setId(lokacijaDTO.getId());
		lokacija.setDrzava(lokacijaDTO.getDrzava());
		lokacija.setGrad(lokacijaDTO.getGrad());
		lokacija.setUlica(lokacijaDTO.getUlica());
		lokacija.setBroj(lokacijaDTO.getBroj());
		//lokacija.setKoordinate(koordinateRepository.findById(lokacijaDTO.getIdKoordinate()).orElse(null));
		return lokacija;
	}
}
