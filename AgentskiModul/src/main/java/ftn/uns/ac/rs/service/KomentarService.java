package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.GetAllKomentarRequest;
import ftn.uns.ac.rs.model.GetAllKomentarResponse;
import ftn.uns.ac.rs.model.Komentar;
import ftn.uns.ac.rs.model.KomentarDTO;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.ShowKomentarDTO;
import ftn.uns.ac.rs.repository.KomentarRepository;
import ftn.uns.ac.rs.repository.KorisnikRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;

@Service
public class KomentarService {
	@Autowired
	private KomentarRepository komentarRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private SmestajRepository smestajRepository;

	public List<KomentarDTO> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllKomentarRequest getAllKomentarRequest = new GetAllKomentarRequest();
		GetAllKomentarResponse getAllKomentarResponse = producerPort.getAllKomentar(getAllKomentarRequest);
		
		for (KomentarDTO komentarDTO : getAllKomentarResponse.getKomentarDTO()) {
			komentarRepository.save(convertToEntity(komentarDTO));
		}
		for (Komentar komentar : komentarRepository.findAll()) {
			boolean exists = false;
			for (KomentarDTO komentarDTO : getAllKomentarResponse.getKomentarDTO()) {
				if (komentar.getId().equals(komentarDTO.getId())){
					exists = true;
					break;
				}
			}
			if (!exists) {
				komentarRepository.deleteById(komentar.getId());
			}
		}
		return getAllKomentarResponse.getKomentarDTO();
	};
	
	public List<ShowKomentarDTO> getAll(){ 
		return komentarRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public ShowKomentarDTO getById(Long id) {
		if(!komentarRepository.existsById(id)) {
			return null;
		}
		return komentarRepository.findById(id).map(this::convertToDTO).orElse(null);
	}
	
	private Komentar convertToEntity(KomentarDTO komentarDTO) {
		Komentar komentar = new Komentar();
		komentar.setId(komentarDTO.getId());
		komentar.setDatumKreiranja(komentarDTO.getDatumKreiranja());
		komentar.setKorisnik(korisnikRepository.findById(komentarDTO.getIdKorisnik()).orElse(null));
		komentar.setNaslov(komentarDTO.getNaslov());
		komentar.setSmestaj(smestajRepository.findById(komentarDTO.getIdSmestaj()).orElse(null));
		komentar.setStatusKomentara(komentarDTO.getStatusKomentara());
		komentar.setTekst(komentarDTO.getTekst());
		return komentar;
	}
	
	private ShowKomentarDTO convertToDTO(Komentar komentar) {
		ShowKomentarDTO komentarDTO = new ShowKomentarDTO();
		komentarDTO.setId(komentar.getId());
		komentarDTO.setDatumKreiranja(komentar.getDatumKreiranja());
		komentarDTO.setKorisnickoImeKorisnik(komentar.getKorisnik().getKorisnickoIme());
		komentarDTO.setNaslov(komentar.getNaslov());
		komentarDTO.setNazivSmestaj(komentar.getSmestaj().getNaziv());
		komentarDTO.setStatusKomentara(komentar.getStatusKomentara());
		komentarDTO.setTekst(komentar.getTekst());
		return komentarDTO;
	}
}
