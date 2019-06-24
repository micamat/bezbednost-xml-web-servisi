package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.RezervisaneSobe;
import ftn.uns.ac.rs.model.RezervisaneSobeDTO;
import ftn.uns.ac.rs.model.ShowRezervisaneSobeDTO;
import ftn.uns.ac.rs.model.UpdateRezervisaneSobeRequest;
import ftn.uns.ac.rs.model.UpdateRezervisaneSobeResponse;
import ftn.uns.ac.rs.repository.KorisnikRepository;
import ftn.uns.ac.rs.repository.RezervacijaRepository;
import ftn.uns.ac.rs.repository.RezervisaneSobeRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.SobaRepository;

@Service
public class RezervisaneSobeService {
	
	@Autowired
	private RezervisaneSobeRepository rezervisaneSobeRepository;
	
	
	public int createSync(RezervisaneSobeDTO rezervisaneSobeDTO){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		UpdateRezervisaneSobeRequest updateRezervisaneSobeRequest = new UpdateRezervisaneSobeRequest();
		updateRezervisaneSobeRequest.setRezervisaneSobeDTO(rezervisaneSobeDTO);
		UpdateRezervisaneSobeResponse updateRezervisaneSobeResponse = producerPort.updateRezervisaneSobe(updateRezervisaneSobeRequest);
		return updateRezervisaneSobeResponse.getId();
	};
	
	public List<ShowRezervisaneSobeDTO> getAll(){ 
		return rezervisaneSobeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public ShowRezervisaneSobeDTO getById(Long id) {
		if(!rezervisaneSobeRepository.existsById(id)) {
			return null;
		}
		RezervisaneSobe rezervisaneSobe = rezervisaneSobeRepository.findById(id).orElse(null);
		return convertToDTO(rezervisaneSobe);
	}
	
	public List<ShowRezervisaneSobeDTO> getBySmestaj(Long smestajId) {
		return rezervisaneSobeRepository.findAll().stream().filter(x -> smestajId.equals(x.getRezervacija().getSmestaj().getId())).map(this::convertToDTO).collect(Collectors.toList());
	}
	
	public boolean add(RezervisaneSobe rezervisaneSobe) {
		System.out.println("Y");
		rezervisaneSobe = rezervisaneSobeRepository.save(rezervisaneSobe);
		if(rezervisaneSobe != null) {
			//createSync(rezervacijaDTO);
			return true;
		}
		return false;
	}
	
	public ShowRezervisaneSobeDTO convertToDTO(RezervisaneSobe rezervisaneSobe) {
		ShowRezervisaneSobeDTO rezervisaneSobeDTO = new ShowRezervisaneSobeDTO();
		rezervisaneSobeDTO.setId(rezervisaneSobe.getId());
		rezervisaneSobeDTO.setCena(rezervisaneSobe.getCena());
		System.out.println(rezervisaneSobe.getSoba().getNaziv());
		rezervisaneSobeDTO.setNazivSoba(rezervisaneSobe.getSoba().getNaziv());
		rezervisaneSobeDTO.setNazivStatusRezervacije(rezervisaneSobe.getStatusRezervacije());
		return rezervisaneSobeDTO;
	}
	
}
