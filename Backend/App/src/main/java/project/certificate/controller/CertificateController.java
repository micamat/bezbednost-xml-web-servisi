package project.certificate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.certificate.dto.CertificateDTO;
import project.certificate.keystore.KeystoreDTO;
import project.certificate.service.GenerateCertificateService;

@RestController
@RequestMapping(value = "/certificate")
public class CertificateController {

	@Autowired
	private GenerateCertificateService certificateService;
	
	
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
}
