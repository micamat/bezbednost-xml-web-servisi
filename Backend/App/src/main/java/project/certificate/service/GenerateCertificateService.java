package project.certificate.service;

import java.security.KeyPair;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.certificate.data.IssuerData;
import project.certificate.data.SubjectData;
import project.certificate.dto.CertificateDTO;
import project.certificate.dto.CertificateDetailDTO;
import project.certificate.generators.CertificateGenerator;
import project.certificate.generators.KeyGenerator;
import project.certificate.keystore.KeyStoreReader;
import project.certificate.keystore.KeyStoreWriter;
import project.certificate.keystore.Keystore;
import project.certificate.keystore.KeystoreDTO;
import project.certificate.keystore.KeystoreRepository;

@Service
public class GenerateCertificateService {
	
	@Autowired
	private KeystoreRepository keystoreRepository;
	
	public boolean createCertificate(CertificateDTO certificate)
	{
		KeyGenerator kg = new KeyGenerator();
		KeyPair keyPairSubject = kg.generateKeys();
		
		SubjectData subjectData = generateSubjectData(certificate,keyPairSubject);
		IssuerData issuerData = null;

		if(certificate.getWho() == null) {
			//za selfsignes
			issuerData = generateIssuerData(certificate,keyPairSubject.getPrivate());
		}
		else{
			//ovde iscipati iz baze
		}

		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		
		//proveriti dal je dobra sifra keystora....
		/*List<Keystore> k = new ArrayList<Keystore>();
		System.out.println("Aj ispisi zivotat ti " + certificate.getKeystore() + certificate.getPassword());
		k = keystoreRepository.findAll();
		boolean temp = true;
		for (Keystore sample : k) {
				if(sample.getKeystoreName().equals(certificate.getKeystore())) {
					if(sample.getPassword().equals(certificate.getPassword())) {
						temp = false;
					}
				}
		}
		if(temp) {
			return false;
		}*/
		
		KeyStoreWriter wr = new KeyStoreWriter();
		wr.loadKeyStore("./keystore/admin/" + certificate.getKeystore(), certificate.getPassword().toCharArray());
		wr.write(certificate.getToWhom().getOrganizationName(), issuerData.getPrivateKey(), certificate.getPassword().toCharArray(), cert);
		wr.saveKeyStore("./keystore/admin/" + certificate.getKeystore(), certificate.getPassword().toCharArray());
		
		return true;
	}
	
	public boolean createKeystore(KeystoreDTO keystoreDTO){
		if(keystoreRepository.existsByKeystoreName(keystoreDTO.getKeystoreName())) {
			return false;
		}
		
		Keystore k = new Keystore();
		k.setKeystoreName(keystoreDTO.getKeystoreName());
		k.setPassword(keystoreDTO.getPassword());
		k.setRole(keystoreDTO.getRole());
		keystoreRepository.save(k);
		
		KeyStoreWriter wr = new KeyStoreWriter();
		wr.loadKeyStore(null, null);
		if(keystoreDTO.getRole().equals("Admin store")) {
			wr.saveKeyStore("./keystore/admin/"+keystoreDTO.getKeystoreName(), keystoreDTO.getPassword().toCharArray());
		}else if(keystoreDTO.getRole().equals("Transfer store")){
			wr.saveKeyStore("./keystore/distribution/transfer/"+keystoreDTO.getKeystoreName(), keystoreDTO.getPassword().toCharArray());
		}else {
			wr.saveKeyStore("./keystore/distribution/trusted/"+keystoreDTO.getKeystoreName(), keystoreDTO.getPassword().toCharArray());
		}
		
		return true;
	}
	
	public List<KeystoreDTO> getAllAdminKeystores() {
		
		List<Keystore> k = new ArrayList<Keystore>();
		List<KeystoreDTO> kDTO = new ArrayList<KeystoreDTO>();
		
		k = keystoreRepository.findAll();
		for (Keystore sample : k) {
			if(sample.getRole().equals("Admin store")) {
				KeystoreDTO kd = new KeystoreDTO();
				kd.setKeystoreName(sample.getKeystoreName());
				kDTO.add(kd);
			}
		}
		return kDTO;
	}
	
	public List<CertificateDetailDTO> getAllCertificates() {
		List<Certificate> certificates = new ArrayList<Certificate>();
		List<KeystoreDTO> keystoreDTO = getAllAdminKeystores();
		KeyStoreReader kr = new KeyStoreReader();
		Enumeration<String> aliases = null;
		
		for(int i = 0; i < keystoreDTO.size(); i++) {
			try {
				aliases = kr.getKeystore().aliases();
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(aliases != null) {
				while(aliases.nextElement() != null) {
					certificates.add(kr.readCertificate(keystoreDTO.get(i).getKeystoreName(),keystoreDTO.get(i).getPassword(),aliases.nextElement()));
				}
			}
		}
		
		
		return CertificatesToCertificatesDTO(certificates);
	}
	
	public List<CertificateDetailDTO> CertificatesToCertificatesDTO(List<Certificate> certificates) {
		List<CertificateDetailDTO> certificatesDTO = new ArrayList<CertificateDetailDTO>();
		for(int i = 0; i < certificates.size(); i++) {
			CertificateDetailDTO certificateDetailDTO = new CertificateDetailDTO();
			if(certificates.get(i) instanceof X509Certificate) {
				X509Certificate cert = (X509Certificate) certificates.get(i);
				certificateDetailDTO.setCommonName(cert.toString());
				certificatesDTO.add(certificateDetailDTO);
				/*
				 * certificateDetailDTO.setCommonNameIssuer(cert.toString());
				 * certificateDetailDTO.setOrganization(cert.getSubjectDN().toString());
				 * certificateDetailDTO.setOrganizationalUnit(cert.getSubjectDN().toString());
				 * certificateDetailDTO.setOrganizationalUnitIssuer(cert.getSubjectDN().toString
				 * ());
				 * certificateDetailDTO.setOrganizationIssuer(cert.getSubjectDN().toString());
				 * certificateDetailDTO.setSerialNumber(cert.getSubjectDN().toString());
				 * certificateDetailDTO.set(cert.getSubjectDN().toString());
				 * certificateDetailDTO.setSerialNumber(cert.getSubjectDN().toString());
				 * certificateDetailDTO.setSerialNumber(cert.getSubjectDN().toString());
				 */
			}
		}
		return certificatesDTO;
	}
	
	private SubjectData generateSubjectData(CertificateDTO certificate,KeyPair keyPairSubject) {
		try {
			
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse(certificate.getStartDate());
			Date endDate = iso8601Formater.parse(certificate.getEndDate());
			
			//Serijski broj sertifikata
			String sn= "123456";
			
			System.out.println("Ajde ispisi ovo " + certificate.getToWhom().getComonName());
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, certificate.getToWhom().getComonName());
		    builder.addRDN(BCStyle.SURNAME, certificate.getToWhom().getSurname());
		    builder.addRDN(BCStyle.GIVENNAME, certificate.getToWhom().getGivenName());
		    builder.addRDN(BCStyle.O, certificate.getToWhom().getOrganizationName());
		    builder.addRDN(BCStyle.OU, certificate.getToWhom().getOrganizationUnitName());
		    builder.addRDN(BCStyle.C, certificate.getToWhom().getCountryName());
		    builder.addRDN(BCStyle.E, certificate.getToWhom().getEmail());
		    
		    //UID (USER ID) je ID korisnika
		    builder.addRDN(BCStyle.UID, "123456");
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private IssuerData generateIssuerData(CertificateDTO certificate,PrivateKey issuerKey) {
		
		//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	    builder.addRDN(BCStyle.CN, certificate.getToWhom().getComonName());
	    builder.addRDN(BCStyle.SURNAME, certificate.getToWhom().getSurname());
	    builder.addRDN(BCStyle.GIVENNAME, certificate.getToWhom().getGivenName());
	    builder.addRDN(BCStyle.O, certificate.getToWhom().getOrganizationName());
	    builder.addRDN(BCStyle.OU, certificate.getToWhom().getOrganizationUnitName());
	    builder.addRDN(BCStyle.C, certificate.getToWhom().getCountryName());
	    builder.addRDN(BCStyle.E, certificate.getToWhom().getEmail());
	    
	    //UID (USER ID) je ID korisnika
	    builder.addRDN(BCStyle.UID, "123456");

		//Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
	    // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
	    // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
		return new IssuerData(issuerKey, builder.build());
	}
}
