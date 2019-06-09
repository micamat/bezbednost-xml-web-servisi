package ftn.uns.ac.rs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CenovnikDTO;
import ftn.uns.ac.rs.model.CreateCenovnikRequest;
import ftn.uns.ac.rs.model.CreateCenovnikResponse;
import ftn.uns.ac.rs.model.GetAllCenovnikRequest;
import ftn.uns.ac.rs.model.GetAllCenovnikResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.repository.CenovnikRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.TipSobeRepository;

@Service
public class CenovnikService {
	@Autowired
	private SmestajRepository smestajRepository;

	@Autowired
	private TipSobeRepository tipSobeRepository;

	@Autowired
	private CenovnikRepository cenovnikRepository;

	/*public List<CenovnikDTO> getAll(){ 
		return cenovnikRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};*/
	
	//TODO: Implementirati poslovnu logiku ..... cuvanja u bazu kao i 
	public List<CenovnikDTO> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllCenovnikRequest getCenovnikRequest = new GetAllCenovnikRequest();
		GetAllCenovnikResponse getAllCenovnikResponse = producerPort.getAllCenovnik(getCenovnikRequest);
		return getAllCenovnikResponse.getCenovnikDTO();
	};
	
	
	public int createSync(CenovnikDTO cmd){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateCenovnikRequest createCenovnikRequest = new CreateCenovnikRequest();
		createCenovnikRequest.setId(cmd.getId());
		createCenovnikRequest.setCena(cmd.getCena());
		createCenovnikRequest.setDatumDo(cmd.getDatumDo());
		createCenovnikRequest.setDatumOd(cmd.getDatumOd());
		createCenovnikRequest.setIdSmestaj(cmd.getIdSmestaj());
		createCenovnikRequest.setIdTipSobe(cmd.getIdTipSobe());
		
		CreateCenovnikResponse createCenovnikResponse = producerPort.createCenovnik(createCenovnikRequest);
		return createCenovnikResponse.getId();
	};
	/*
	public CenovnikDTO getById(Long id) {
		if(!cenovnikRepository.existsById(id)) {
			return null;
		}
		Cenovnik cenovnik = cenovnikRepository.findById(id).orElse(null);
		return convertToDTO(cenovnik);
	}
	
	public List<CenovnikDTO> getBySmestaj(Long smestajId) {
		return cenovnikRepository.findAll().stream().filter(x -> smestajId.equals(x.getSmestaj().getId())).map(this::convertToDTO).collect(Collectors.toList());
	}
	
	
	public boolean add(CenovnikDTO cenovnikDTO) {
		cenovnikDTO.setId(null);
		if(cenovnikRepository.save(convertToEntity(cenovnikDTO)) != null) {
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(cenovnikRepository.existsById(id)) {
			cenovnikRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private CenovnikDTO convertToDTO(Cenovnik cenovnik) {
		CenovnikDTO cenovnikDTO = new CenovnikDTO();
		cenovnikDTO.setId(cenovnik.getId());
		cenovnikDTO.setDatumOd(cenovnik.getDatumOd());
		cenovnikDTO.setDatumOd(cenovnik.getDatumDo());
		cenovnikDTO.setTipSobeId(cenovnik.getTipSobe().getId());
		cenovnikDTO.setSmestajId(cenovnik.getSmestaj().getId());
		return cenovnikDTO;
	}
	
	private Cenovnik convertToEntity(CenovnikDTO cenovnikDTO) {
		Cenovnik cenovnik = new Cenovnik();
		cenovnik.setId(cenovnikDTO.getId());
		cenovnik.setDatumOd(cenovnikDTO.getDatumOd());
		cenovnik.setDatumOd(cenovnikDTO.getDatumDo());
		cenovnik.setTipSobe(tipSobeRepository.findById(cenovnikDTO.getTipSobeId()).orElse(null));
		cenovnik.setSmestaj(smestajRepository.findById(cenovnikDTO.getSmestajId()).orElse(null));
		return cenovnik;
	}*/
}
