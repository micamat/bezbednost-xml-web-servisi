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
import ftn.uns.ac.rs.model.Sifarnik;
import ftn.uns.ac.rs.repository.KategorijaSmestajaRepository;

@Service
public class KategorijaSmestajaService {
	
	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;

	public List<KategorijaSmestaja> getAll(){ 
		return kategorijaSmestajaRepository.findAll().stream().collect(Collectors.toList());
	};
	
	//TODO: Implementirati poslovnu logiku ..... cuvanja u bazu kao i 
	public List<KategorijaSmestaja> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllKategorijaSmestajaRequest getAllKategorijaSmestajaRequest = new GetAllKategorijaSmestajaRequest();
		GetAllKategorijaSmestajaResponse getAllKategorijaSmestajaResponse = producerPort.getAllKategorijaSmestaja(getAllKategorijaSmestajaRequest);
		
		for (KategorijaSmestaja kategorijaSmestajaDTO : getAllKategorijaSmestajaResponse.getKategorijaSmestaja()) {
			kategorijaSmestajaRepository.save(convertToEntity(kategorijaSmestajaDTO));
		}
		for (KategorijaSmestaja kategorijaSmestaja : kategorijaSmestajaRepository.findAll()) {
			boolean exists = false;
			for (KategorijaSmestaja kategorijaSmestajaDTO : getAllKategorijaSmestajaResponse.getKategorijaSmestaja()) {
				if (kategorijaSmestaja.getId() == kategorijaSmestajaDTO.getId()){
					exists = true;
					break;
				}
			}
			if (!exists) {
				kategorijaSmestajaRepository.deleteById(kategorijaSmestaja.getId());
			}
		}
		return getAllKategorijaSmestajaResponse.getKategorijaSmestaja();
	};
	
	public KategorijaSmestaja getById(Long id) {
		if(!kategorijaSmestajaRepository.existsById(id)) {
			return null;
		}
		return kategorijaSmestajaRepository.findById(id).orElse(null);
		
	}
	
	private KategorijaSmestaja convertToEntity(Sifarnik kategorijaSmestajaDTO) {
		KategorijaSmestaja kategorijaSmestaja = new KategorijaSmestaja();
		kategorijaSmestaja.setId(kategorijaSmestajaDTO.getId());
		kategorijaSmestaja.setNaziv(kategorijaSmestajaDTO.getNaziv());
		return kategorijaSmestaja;
	}

}
