package project.certificate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.certificate.model.CertificateModel;
import project.certificate.repository.CertificateRepository;
import project.hierarchy.HierarchyRepository;

@Service
public class CertificateService {
	@Autowired
	CertificateRepository repository;
	
	@Autowired
	HierarchyRepository nodeRepo;
	
	
	public List<CertificateModel> findAll(){
		return repository.findAll();
	}
	
	public CertificateModel findById(Long id) {
		return repository.findById(id).get();
	}
	
	public CertificateModel save(CertificateModel cert) {
		cert.setRevoked(false);
		return repository.save(cert);
	}
}
