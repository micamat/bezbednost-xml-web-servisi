package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Zauzece;
import ftn.uns.ac.rs.model.ZauzeceDTO;
import ftn.uns.ac.rs.repository.SobaRepository;
import ftn.uns.ac.rs.repository.StatusSobeRepository;
import ftn.uns.ac.rs.repository.ZauzeceRepository;

@Service
public class ZauzeceService {
	@Autowired
	private ZauzeceRepository ZauzeceRepo;
	
	@Autowired
	SobaRepository sobaRepo;
	
	@Autowired
	StatusSobeRepository statusSobeRepo;
	
	public List<ZauzeceDTO> getAll(){
		List<ZauzeceDTO> dtos = new ArrayList<ZauzeceDTO>();
		for(Zauzece s : ZauzeceRepo.findAll()) {
			dtos.add(ZauzeceToDTO(s));
		}
		return dtos;
	}
	
	public int create(ZauzeceDTO dto) {
		Zauzece ss = ZauzeceToEntity(dto);
		int id = -1;
		Zauzece s = ZauzeceRepo.save(ss);
		if(s == null) {
			return id;
		}
		else {
			return (int)s.getId();
		}
	}
	
	private ZauzeceDTO ZauzeceToDTO(Zauzece zauzece) {
		ZauzeceDTO dto = new ZauzeceDTO();
		dto.setId(zauzece.getId());
		dto.setDatumDo(zauzece.getDatumDo());
		dto.setDatumOd(zauzece.getDatumOd());
		dto.setIdSoba(zauzece.getSoba().getId());
		dto.setIdStatusSobe(zauzece.getStatusSobe().getId());
		return dto;
	}
	
	private Zauzece ZauzeceToEntity(ZauzeceDTO dto) {
		System.out.println("ZAUZECEDTO: " + dto);
		Zauzece ss = new Zauzece();
		ss.setId(dto.getId());
		ss.setDatumDo(dto.getDatumDo());
		ss.setDatumOd(dto.getDatumOd());
		ss.setSoba(sobaRepo.findById(dto.getIdSoba()).get());
		System.out.println("*****PRVA*****");
		ss.setStatusSobe(statusSobeRepo.findById(dto.getIdStatusSobe()).get());
		System.out.println("*****DRUGA*****");
		return ss;
	}
}
