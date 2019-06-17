package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Cenovnik;
import ftn.uns.ac.rs.model.CenovnikDTO;
import ftn.uns.ac.rs.model.CreateCenovnikRequest;
import ftn.uns.ac.rs.model.CreateCenovnikResponse;
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

	public List<CenovnikDTO> getAll(){ 
		return cenovnikRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
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
		System.out.println(createCenovnikResponse.getId()+"ASDSDSDASDASD");
		return createCenovnikResponse.getId();
	};
	
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
		Cenovnik cenovnik = cenovnikRepository.save(convertToEntity(cenovnikDTO));
		if(cenovnik != null) {
			createSync(convertToDTO(cenovnik));
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
		cenovnikDTO.setIdTipSobe(cenovnik.getTipSobe().getId());
		cenovnikDTO.setIdSmestaj(cenovnik.getSmestaj().getId());
		return cenovnikDTO;
	}
	
	private Cenovnik convertToEntity(CenovnikDTO cenovnikDTO) {
		Cenovnik cenovnik = new Cenovnik();
		cenovnik.setId(cenovnikDTO.getId());
		cenovnik.setCena(cenovnikDTO.getCena());
		cenovnik.setDatumOd(cenovnikDTO.getDatumOd());
		cenovnik.setDatumDo(cenovnikDTO.getDatumDo());
		cenovnik.setTipSobe(tipSobeRepository.findById(cenovnikDTO.getIdTipSobe()).orElse(null));
		cenovnik.setSmestaj(smestajRepository.findById(cenovnikDTO.getIdSmestaj()).orElse(null));
		return cenovnik;
	}
}
