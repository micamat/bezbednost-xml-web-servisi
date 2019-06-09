package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateKategorijaSmestajaRequest;
import ftn.uns.ac.rs.model.CreateKategorijaSmestajaResponse;
import ftn.uns.ac.rs.model.GetAllKategorijaSmestajaRequest;
import ftn.uns.ac.rs.model.GetAllKategorijaSmestajaResponse;
import ftn.uns.ac.rs.model.KategorijaSmestaja;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.SifarnikDTO;
import ftn.uns.ac.rs.repository.KategorijaSmestajaRepository;

@Service
public class KategorijaSmestajaService {
	
	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;

	public List<SifarnikDTO> getAll(){ 
		return kategorijaSmestajaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	//TODO: Implementirati poslovnu logiku ..... cuvanja u bazu kao i 
	public List<SifarnikDTO> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllKategorijaSmestajaRequest getAllKoordinateRequest = new GetAllKategorijaSmestajaRequest();
		GetAllKategorijaSmestajaResponse getAllKoordinateResponse = producerPort.getAllKategorijaSmestaja(getAllKoordinateRequest);
		return getAllKoordinateResponse.getKategorijaSmestajaDTO();
	};
	
	
	public int createSync(SifarnikDTO cmd){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateKategorijaSmestajaRequest createKategorijaSmestajaRequest = new CreateKategorijaSmestajaRequest();
		createKategorijaSmestajaRequest.setId(cmd.getId());
		createKategorijaSmestajaRequest.setNaziv(cmd.getNaziv());
		createKategorijaSmestajaRequest.setOpis(cmd.getOpis());
		
		CreateKategorijaSmestajaResponse kategorijaSmestajaResponse = producerPort.createKategorijaSmestaja(createKategorijaSmestajaRequest);
		return kategorijaSmestajaResponse.getId();
	};
	
	public SifarnikDTO getById(Long id) {
		if(!kategorijaSmestajaRepository.existsById(id)) {
			return null;
		}
		KategorijaSmestaja kategorijaSmestaja = kategorijaSmestajaRepository.findById(id).orElse(null);
		return convertToDTO(kategorijaSmestaja);
	}
	
	
	public boolean add(SifarnikDTO kategorijaSmestajaDTO) {
		kategorijaSmestajaDTO.setId(null);
		if(kategorijaSmestajaRepository.findAll().stream().filter(x -> kategorijaSmestajaDTO.getNaziv().equals(x.getNaziv())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			kategorijaSmestajaRepository.save(convertToEntity(kategorijaSmestajaDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(kategorijaSmestajaRepository.existsById(id)) {
			kategorijaSmestajaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private SifarnikDTO convertToDTO(KategorijaSmestaja kategorijaSmestaja) {
		SifarnikDTO kategorijaSmestajaDTO = new SifarnikDTO();
		kategorijaSmestajaDTO.setId(kategorijaSmestaja.getId());
		kategorijaSmestajaDTO.setNaziv(kategorijaSmestaja.getNaziv());
		kategorijaSmestajaDTO.setOpis(kategorijaSmestaja.getOpis());
		return kategorijaSmestajaDTO;
	}
	
	private KategorijaSmestaja convertToEntity(SifarnikDTO kategorijaSmestajaDTO) {
		KategorijaSmestaja kategorijaSmestaja = new KategorijaSmestaja();
		kategorijaSmestaja.setNaziv(kategorijaSmestajaDTO.getNaziv());
		kategorijaSmestaja.setOpis(kategorijaSmestajaDTO.getOpis());
		return kategorijaSmestaja;
	}
}
