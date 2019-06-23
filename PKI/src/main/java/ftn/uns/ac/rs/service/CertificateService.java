package ftn.uns.ac.rs.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CertificateM;
import ftn.uns.ac.rs.repository.CertificateRepository;
import ftn.uns.ac.rs.repository.HierarchyRepository;

@Service
public class CertificateService {
	@Autowired
	CertificateRepository repository;
	
	@Autowired
	HierarchyRepository nodeRepo;
	
	
	public List<CertificateM> findAll(){
		return repository.findAll();
	}
	
	public CertificateM findById(Long id) {
		return repository.findById(id).get();
	}
	
	public CertificateM save(CertificateM cert) {
		return repository.save(cert);
	}
	
	public CertificateM findByAlias(String alias) {
		return repository.findByAlias(alias);
	}
}
