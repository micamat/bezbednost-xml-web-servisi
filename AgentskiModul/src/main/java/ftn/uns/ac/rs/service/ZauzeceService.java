package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateZauzeceRequest;
import ftn.uns.ac.rs.model.CreateZauzeceResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.Zauzece;
import ftn.uns.ac.rs.model.ZauzeceDTO;
import ftn.uns.ac.rs.repository.SobaRepository;
import ftn.uns.ac.rs.repository.StatusSobeRepository;
import ftn.uns.ac.rs.repository.ZauzeceRepository;

@Service
public class ZauzeceService {
	
	@Autowired
	private SobaRepository sobaRepository;

	@Autowired
	private StatusSobeRepository statusSobeRepository;

	@Autowired
	private ZauzeceRepository zauzeceRepository;
	
	public int createSync(Zauzece zauzece){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateZauzeceRequest getZauzeceRequest = new CreateZauzeceRequest(zauzece);
		CreateZauzeceResponse getZauzeceResponse = producerPort.createZauzece(getZauzeceRequest);
		return getZauzeceResponse.getId();
	};
	
	public List<ZauzeceDTO> getAll(){ 
		return zauzeceRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public ZauzeceDTO getById(Long id) {
		if(!zauzeceRepository.existsById(id)) {
			return null;
		}
		Zauzece zauzece = zauzeceRepository.findById(id).orElse(null);
		return convertToDTO(zauzece);
	}
	
	public List<ZauzeceDTO> getBySmestaj(Long smestajId) {
		return zauzeceRepository.findAll().stream().filter(x -> smestajId.equals(x.getSoba().getSmestaj().getId())).map(this::convertToDTO).collect(Collectors.toList());
	}
	
	public boolean add(Zauzece zauzece) {
		zauzece.setId(null);
		zauzece = zauzeceRepository.save(zauzece);
		if(zauzece != null) {
			createSync(zauzece);
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(zauzeceRepository.existsById(id)) {
			zauzeceRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private ZauzeceDTO convertToDTO(Zauzece zauzece) {
		ZauzeceDTO zauzeceDTO = new ZauzeceDTO();
		zauzeceDTO.setId(zauzece.getId());
		zauzeceDTO.setDatumOd(zauzece.getDatumOd());
		zauzeceDTO.setDatumOd(zauzece.getDatumDo());
		zauzeceDTO.setIdSoba(zauzece.getSoba().getId());
		zauzeceDTO.setIdStatusSobe(zauzece.getStatusSobe().getId());
		return zauzeceDTO;
	}
	
	private Zauzece convertToEntity(ZauzeceDTO zauzeceDTO) {
		Zauzece zauzece = new Zauzece();
		zauzece.setId(zauzeceDTO.getId());
		zauzece.setDatumOd(zauzeceDTO.getDatumOd());
		zauzece.setDatumOd(zauzeceDTO.getDatumDo());
		zauzece.setSoba(sobaRepository.findById(zauzeceDTO.getIdSoba()).orElse(null));
		zauzece.setStatusSobe(statusSobeRepository.findById(zauzeceDTO.getIdStatusSobe()).orElse(null));
		return zauzece;
	}
}
