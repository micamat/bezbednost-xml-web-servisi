package project.certificate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.certificate.dto.CertificateDTO;
import project.certificate.dto.CertificateDetailDTO;
import project.certificate.keystore.KeystoreDTO;
import project.certificate.model.CertificateModel;
import project.certificate.service.CertificateService;
import project.certificate.service.GenerateCertificateService;
import project.hierarchy.HierarchyModel;
import project.hierarchy.HierarchyService;

@RestController
@RequestMapping(value = "/certificate")
public class CertificateController {

	@Autowired
	private GenerateCertificateService certificateService;
	
	@Autowired
	private CertificateService service;
	
	@Autowired
	private HierarchyService nodeService;
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody CertificateDTO certificate)
	{
		if(certificateService.createCertificate(certificate) == true) {
			return new ResponseEntity<String>("User create certificate!",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("User fail to create certificate!",HttpStatus.CONFLICT);
		}
	
	}
	
	@PostMapping("/createKeystore")
	public ResponseEntity<String> createKeystore(@RequestBody KeystoreDTO keystoreDTO)
	{
		if(certificateService.createKeystore(keystoreDTO) == true) {
			return new ResponseEntity<String>("User create keystore!",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("User fail to create keystore!",HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping(value = "/getAllAdminKeystores")
	public ResponseEntity<List<KeystoreDTO>> getAllKeystores(){
		if(certificateService.getAllAdminKeystores() == null) {
			return new ResponseEntity<List<KeystoreDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KeystoreDTO>>(certificateService.getAllAdminKeystores(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/certificates")
	public ResponseEntity<List<CertificateDetailDTO>> getCertificates(){
		if(certificateService.getAllCertificatesDetails() == null) {
			return new ResponseEntity<List<CertificateDetailDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CertificateDetailDTO>>(certificateService.getAllCertificatesDetails(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllCertificate")
	public ResponseEntity<List<CertificateDTO>> getAllCertificate(){
		if(certificateService.getAllCertificates() == null) {
			return new ResponseEntity<List<CertificateDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CertificateDTO>>(certificateService.getAllCertificates(),HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<CertificateModel>> findAll(){
		return new ResponseEntity<List<CertificateModel>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<CertificateModel> findById(@RequestBody Long id){
		return new ResponseEntity<CertificateModel>(service.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CertificateModel> save(@RequestBody CertificateModel cert){
		return new ResponseEntity<CertificateModel>(service.save(cert), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity revoke(@RequestBody CertificateModel cert) {
		cert.setRevoked(true);
		
		//iz tabele certificate model povuci red sa alisom cert.getAlias()
		//us pomoc name kestora iz tabele keysotre povuces pasworde
		
		
		HierarchyModel node = nodeService.findByComonName(cert.getAlias());
		List<HierarchyModel> children = nodeService.findChildren(node.getId());
		for(HierarchyModel h : children) {
			for(CertificateModel c : service.findAll()) {
				if(c.getAlias().equals(h.getComonName())) {
					c.setRevoked(true);
					service.save(c);
				}
			}
		}
		service.save(cert);
		return ResponseEntity.ok().build();
	}
	
}
