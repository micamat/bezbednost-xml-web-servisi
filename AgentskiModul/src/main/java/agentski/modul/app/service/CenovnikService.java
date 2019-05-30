package agentski.modul.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentski.modul.app.model.cenovnik.Cenovnik;
import agentski.modul.app.modelDTO.CenovnikDTO;
import agentski.modul.app.repository.CenovnikRepository;
import agentski.modul.app.repository.SmestajRepository;
import agentski.modul.app.repository.TipSobeRepository;

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
	}
}
