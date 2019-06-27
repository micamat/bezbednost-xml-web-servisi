package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.config.Auth;
import ftn.uns.ac.rs.model.GetAllKorisnikRequest;
import ftn.uns.ac.rs.model.GetAllKorisnikResponse;
import ftn.uns.ac.rs.model.Korisnik;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.repository.KorisnikRepository;

@Service
public class KorisnikService {
	@Autowired
	private KorisnikRepository korisnikRepository;

	public List<Korisnik> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();

		Auth.authenticateClient(producerPort);
		GetAllKorisnikRequest getKorisnikRequest = new GetAllKorisnikRequest();
		GetAllKorisnikResponse getKorisnikResponse = producerPort.getAllKorisnik(getKorisnikRequest);
		
		for (Korisnik korisnik : getKorisnikResponse.getKorisnik()) {
			korisnikRepository.save(korisnik);
		}
		for (Korisnik korisnik : korisnikRepository.findAll()) {
			boolean exists = false;
			for (Korisnik korisnikDTO : getKorisnikResponse.getKorisnik()) {
				if (korisnik.getId().equals(korisnikDTO.getId())){
					exists = true;
					break;
				}
			}
			if (!exists) {
				korisnikRepository.deleteById(korisnik.getId());
			}
		}
		return getKorisnikResponse.getKorisnik();
	};
	
	public List<Korisnik> getAll(){ 
		return korisnikRepository.findAll().stream().collect(Collectors.toList());
	};
	
	public Korisnik getById(Long id) {
		if(!korisnikRepository.existsById(id)) {
			return null;
		}
		return korisnikRepository.findById(id).orElse(null);
	}
}
