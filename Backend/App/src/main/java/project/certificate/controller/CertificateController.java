package project.certificate.controller;

import java.security.cert.X509Certificate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.certificate.dto.CertificateDTO;
import project.certificate.dto.CertificateDetailDTO;
import project.certificate.dto.SignedSertificateDTO;
import project.certificate.keystore.KeyStoreReader;
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
	
	@PostMapping("/create/{store}")
	public ResponseEntity<String> create(@RequestBody CertificateDTO certificate, @PathVariable("store") String trustStore)
	{
		if(certificateService.createCertificate(certificate, trustStore) == true) {
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
	
	@GetMapping(value = "/getAllTransferKeystores")
	public ResponseEntity<List<KeystoreDTO>> getAllTransferKeystores(){
		if(certificateService.getAllTransferKeystores() == null) {
			return new ResponseEntity<List<KeystoreDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KeystoreDTO>>(certificateService.getAllTransferKeystores(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/certificates")
	public ResponseEntity<List<CertificateDetailDTO>> getCertificates(){
		if(certificateService.getAllCertificatesDetails() == null) {
			return new ResponseEntity<List<CertificateDetailDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CertificateDetailDTO>>(certificateService.getAllCertificatesDetails(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllCertificate")
	public ResponseEntity<List<SignedSertificateDTO>> getAllCertificate(){
		System.out.println("BLA BLA");
		if(certificateService.getAllCertificates() == null) {
			return new ResponseEntity<List<SignedSertificateDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SignedSertificateDTO>>(certificateService.getAllCertificates(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllCertificateNotCA")
	public ResponseEntity<List<SignedSertificateDTO>> getAllCertificateNotCA(){
		System.out.println("BLA BLA");
		if(certificateService.getAllCertificatesNotCA() == null) {
			return new ResponseEntity<List<SignedSertificateDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SignedSertificateDTO>>(certificateService.getAllCertificatesNotCA(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/transfer/{alias}/{keystore}")
	public ResponseEntity<Boolean> transfer(@PathVariable("alias") String alias, @PathVariable("keystore") String keystore){
		System.out.println(alias + ", " + keystore);
		if(certificateService.addToTransfer(alias,keystore) == false) {
			return new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT);
		}else {
			return ResponseEntity.ok().build();
		}
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

	@PutMapping(value = "/{comonName}")
	public ResponseEntity<CertificateModel> revoke(@PathVariable("comonName") String serialNumber) {	
		CertificateModel cert = service.findByAlias(serialNumber);
		cert.setRevoked(true);
		
		KeystoreDTO keyStore = null;
		for(KeystoreDTO k : certificateService.getAllAdminKeystores()) {
			if(k.getKeystoreName().equals(cert.getKeyStore())) {
				keyStore = k;
			}
		}
			
		KeyStoreReader ksr = new KeyStoreReader();
		X509Certificate cx = (X509Certificate)ksr.readCertificate("./keystore/admin/" + keyStore.getKeystoreName(), keyStore.getPassword(), serialNumber);
		SignedSertificateDTO cDTO = certificateService.makeCertDTOFromCert(cx, serialNumber);
		
		
		HierarchyModel node = nodeService.findByComonName(cDTO.getCommonName());
		List<HierarchyModel> children = nodeService.findChildren(node.getId());
		for(HierarchyModel h : children) {
			for(CertificateModel c : service.findAll()) {
				for(KeystoreDTO k : certificateService.getAllAdminKeystores()) {
					if(k.getKeystoreName().equals(c.getKeyStore())) {
						System.out.println("name: " + k.getKeystoreName() + ", pass: " + k.getPassword() + ", alias: " + c.getAlias());
						X509Certificate cx1 = (X509Certificate) ksr.readCertificate("./keystore/admin/" + k.getKeystoreName(), k.getPassword(), c.getAlias());
						SignedSertificateDTO cDTO1 = certificateService.makeCertDTOFromCert(cx1, c.getAlias());
						if(h.getComonName().equals(cDTO1.getCommonName())) {
							c.setRevoked(true);
							service.save(c);
						}
					}
				}
			}
		}
		service.save(cert);
		return new ResponseEntity<CertificateModel>(cert, HttpStatus.OK);
	}
	
}
