package ftn.uns.ac.rs.controller;

import java.security.cert.X509Certificate;
import java.util.List;

import org.bouncycastle.cert.CertIOException;
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

import ftn.uns.ac.rs.dto.CertificateDTO;
import ftn.uns.ac.rs.dto.CertificateDetailDTO;
import ftn.uns.ac.rs.dto.KeystoreDTO;
import ftn.uns.ac.rs.dto.SignedSertificateDTO;
import ftn.uns.ac.rs.keystore.KeyStoreReader;
import ftn.uns.ac.rs.model.CertificateM;
import ftn.uns.ac.rs.model.HierarchyModel;
import ftn.uns.ac.rs.service.CertificateService;
import ftn.uns.ac.rs.service.GenerateCertificateService;
import ftn.uns.ac.rs.service.HierarchyService;

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
	public ResponseEntity<String> create(@RequestBody CertificateDTO certificate, @PathVariable("store") String trustStore) throws CertIOException
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
	public ResponseEntity<List<CertificateM>> findAll(){
		return new ResponseEntity<List<CertificateM>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<CertificateM> findById(@RequestBody Long id){
		return new ResponseEntity<CertificateM>(service.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CertificateM> save(@RequestBody CertificateM cert){
		return new ResponseEntity<CertificateM>(service.save(cert), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{comonName}")
	public ResponseEntity<CertificateM> revoke(@PathVariable("comonName") String serialNumber) {	
		CertificateM cert = service.findByAlias(serialNumber);
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
			for(CertificateM c : service.findAll()) {
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
		return new ResponseEntity<CertificateM>(cert, HttpStatus.OK);
	}
	
}
