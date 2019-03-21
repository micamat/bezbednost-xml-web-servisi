package project.certificate.service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.certificate.data.IssuerData;
import project.certificate.data.SubjectData;
import project.certificate.dto.CertificateDTO;
import project.certificate.generators.CertificateGenerator;
import project.certificate.generators.KeyGenerator;
import project.certificate.keystore.KeyStoreWriter;
import project.certificate.keystore.Keystore;
import project.certificate.keystore.KeystoreDTO;
import project.certificate.keystore.KeystoreRepository;
import project.user.model.User;
import project.user.repository.UserRepository;

@Service
public class GenerateCertificateService {
	
	@Autowired
	private KeystoreRepository keystoreRepository;
	
	public boolean createCertificate(CertificateDTO certificate)
	{
		KeyGenerator kg=new KeyGenerator();
		KeyPair keyPairSubject = kg.generateKeys();
		
		SubjectData subjectData = generateSubjectData(certificate,keyPairSubject);
		IssuerData issuerData = null;

		if(certificate.getAlias()==null) {
			//
			issuerData = generateIssuerData(certificate,keyPairSubject.getPrivate());
		}
		else{
			//ovde iscipati iz baze
		}

		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		
		KeyStoreWriter wr = new KeyStoreWriter();
		wr.loadKeyStore(null, null);
		wr.write(cert.getSubjectDN().getName(), issuerData.getPrivateKey(), "maja".toCharArray(), cert);
		wr.saveKeyStore("keyStore1.jks", "maja".toCharArray());
		
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
	
	private SubjectData generateSubjectData(CertificateDTO certificate,KeyPair keyPairSubject) {
		try {
			
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse(certificate.getStartDate());
			Date endDate = iso8601Formater.parse(certificate.getEndDate());
			
			//Serijski broj sertifikata
			String sn= "123456";
			
			System.out.println("Ajde ispisi ovo " + certificate.getComonName());
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, certificate.getComonName());
		    builder.addRDN(BCStyle.SURNAME, certificate.getSurname());
		    builder.addRDN(BCStyle.GIVENNAME, certificate.getGivenName());
		    builder.addRDN(BCStyle.O, certificate.getOrganizationName());
		    builder.addRDN(BCStyle.OU, certificate.getOrganizationUnitName());
		    builder.addRDN(BCStyle.C, certificate.getCountryName());
		    builder.addRDN(BCStyle.E, certificate.getEmail());
		    
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
	    builder.addRDN(BCStyle.CN, certificate.getComonName());
	    builder.addRDN(BCStyle.SURNAME, certificate.getSurname());
	    builder.addRDN(BCStyle.GIVENNAME, certificate.getGivenName());
	    builder.addRDN(BCStyle.O, certificate.getOrganizationName());
	    builder.addRDN(BCStyle.OU, certificate.getOrganizationUnitName());
	    builder.addRDN(BCStyle.C, certificate.getCountryName());
	    builder.addRDN(BCStyle.E, certificate.getEmail());
	    
	    //UID (USER ID) je ID korisnika
	    builder.addRDN(BCStyle.UID, "123456");

		//Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
	    // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
	    // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
		return new IssuerData(issuerKey, builder.build());
	}
}
