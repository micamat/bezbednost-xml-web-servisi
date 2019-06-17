package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		GetAllKategorijaSmestajaRequest getAllKategorijaSmestajaRequest = new GetAllKategorijaSmestajaRequest();
		GetAllKategorijaSmestajaResponse getAllKategorijaSmestajaResponse = producerPort.getAllKategorijaSmestaja(getAllKategorijaSmestajaRequest);
		
		for (SifarnikDTO kategorijaSmestajaDTO : getAllKategorijaSmestajaResponse.getKategorijaSmestajaDTO()) {
			kategorijaSmestajaRepository.save(convertToEntity(kategorijaSmestajaDTO));
		}
		for (KategorijaSmestaja kategorijaSmestaja : kategorijaSmestajaRepository.findAll()) {
			boolean exists = false;
			for (SifarnikDTO kategorijaSmestajaDTO : getAllKategorijaSmestajaResponse.getKategorijaSmestajaDTO()) {
				if (kategorijaSmestaja.getId().equals(kategorijaSmestajaDTO.getId())){
					exists = true;
					break;
				}
			}
			if (!exists) {
				kategorijaSmestajaRepository.deleteById(kategorijaSmestaja.getId());
			}
		}
		return getAllKategorijaSmestajaResponse.getKategorijaSmestajaDTO();
	};
	
	public SifarnikDTO getById(Long id) {
		if(!kategorijaSmestajaRepository.existsById(id)) {
			return null;
		}
		KategorijaSmestaja kategorijaSmestaja = kategorijaSmestajaRepository.findById(id).orElse(null);
		return convertToDTO(kategorijaSmestaja);
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
		kategorijaSmestaja.setId(kategorijaSmestajaDTO.getId());
		kategorijaSmestaja.setNaziv(kategorijaSmestajaDTO.getNaziv());
		kategorijaSmestaja.setOpis(kategorijaSmestajaDTO.getOpis());
		return kategorijaSmestaja;
	}

}
