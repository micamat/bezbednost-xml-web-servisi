package ftn.uns.ac.rs.service;

import org.springframework.stereotype.Service;

@Service
public class TipSobeService {
	/*@Autowired
	private TipSobeRepository TipSobeRepo;
	
	public List<TipSobeDTO> getAll(){
		List<TipSobeDTO> dtos = new ArrayList<TipSobeDTO>();
		for(TipSobe s : TipSobeRepo.findAll()) {
			dtos.add(TipSobeToDTO(s));
		}
		return dtos;
	}
	
	public int create(TipSobeDTO dto) {
		TipSobe ss = TipSobeToEntity(dto);
		int id = -1;
		TipSobe s = TipSobeRepo.save(ss);
		if(s == null) {
			return id;
		}
		else {
			return (int)s.getId();
		}
	}
	
	private TipSobeDTO TipSobeToDTO(TipSobe TipSobe) {
		TipSobeDTO dto = new TipSobeDTO();
		dto.setNaziv(TipSobe.getNaziv());
		dto.setId(TipSobe.getId());
		dto.setOpis(TipSobe.getOpis());
		return dto;
	}
	
	private TipSobe TipSobeToEntity(TipSobeDTO dto) {
		TipSobe ss = new TipSobe();
		ss.setId(dto.getId());
		ss.setNaziv(dto.getNaziv());
		ss.setOpis(dto.getOpis());
		return ss;
	}*/
}
