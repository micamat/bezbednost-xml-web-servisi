package project.certificate.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
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
import project.certificate.model.CertificateModel;
import project.certificate.repository.CertificateRepository;

@Service
public class GenerateCertificateService {
	
	@Autowired
	private KeystoreRepository keystoreRepository;
	
	@Autowired
	private CertificateRepository certificateRepository;
	
	/*@Autowired
	private HierarchyService HierarchyService;*/
	
	public boolean createCertificate(CertificateDTO certificate)
	{
		KeyGenerator kg = new KeyGenerator();
		KeyPair keyPairSubject = kg.generateKeys();
		
		if(keystoreRepository.existsByKeystoreName(certificate.getKeystore()) == false) {
			return false;
		}
		Keystore k = keystoreRepository.findByKeystoreName(certificate.getKeystore());
		if(!k.getPassword().equals(certificate.getPassword())) {
			return false;
		}
		if(!k.getPrivateKeyPassword().equals(certificate.getPrivatePassword())) {
			return false;
		}
		
		CertificateModel cm = new CertificateModel();
		cm.setCa(certificate.getCa());
		cm.setKeyStore(certificate.getKeystore());
		cm.setRevoked(false);
		cm.setAlias("");
		
		cm = certificateRepository.save(cm);
		cm.setAlias(cm.getId().toString());
		certificateRepository.save(cm);
		
		SubjectData subjectData = generateSubjectData(certificate,keyPairSubject,cm.getId().toString());
		IssuerData issuerData = null;

		if(certificate.getWho() == null) {
			//za selfsignes
			issuerData = generateIssuerData(certificate,keyPairSubject.getPrivate());
		}
		else{
			//ovde iscipati iz baza
				
		}

		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		
		
		
		
		KeyStoreWriter wr = new KeyStoreWriter();
		wr.loadKeyStore("./keystore/admin/" + certificate.getKeystore(), certificate.getPassword().toCharArray());
		wr.write(certificate.getToWhom().getOrganizationName(), issuerData.getPrivateKey(), certificate.getPrivatePassword().toCharArray(), cert);
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
		k.setPrivateKeyPassword(keystoreDTO.getPrivateKeyPassword());
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
				kd.setPassword(sample.getPassword());
				kDTO.add(kd);
			}
		}
		return kDTO;
	}
	
	public List<CertificateDetailDTO> getAllCertificatesDetails() {
		List<Certificate> certificates = new ArrayList<Certificate>();
		List<KeystoreDTO> keystoreDTO = getAllAdminKeystores();
		KeyStoreReader kr = new KeyStoreReader();
		Enumeration<String> aliases = null;
		System.out.println(keystoreDTO.size());

		for(int i = 0; i < keystoreDTO.size(); i++) {
			System.out.println("SD"+keystoreDTO.size());
			try {
				String path = "keystore/admin/" + keystoreDTO.get(i).getKeystoreName();

				FileInputStream out = new FileInputStream(path);
				System.out.println(keystoreDTO.get(i).getPassword());
				
				kr.getKeystore().load(out,  keystoreDTO.get(i).getPassword().toCharArray());
				
				aliases = kr.getKeystore().aliases();
				System.out.println("SDA"+aliases.hasMoreElements());
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(aliases != null) {
				while(aliases.hasMoreElements()) {
					certificates.add(kr.readCertificate("keystore/admin/"+keystoreDTO.get(i).getKeystoreName(),keystoreDTO.get(i).getPassword(),aliases.nextElement()));
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
				cert.getSubjectDN();
				cert.getIssuerDN();
				  String[] niz = cert.getSubjectDN().getName().split(",");
				  certificateDetailDTO.setCommonName(niz[niz.length-1].replace("CN=", ""));

				 /* certificateDetailDTO.setOrganization(cert.getSubjectDN().toString());
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
	
	public List<CertificateDTO> getAllCertificates(){
		
		
		
		return null;
	}
	
	
	
	private SubjectData generateSubjectData(CertificateDTO certificate,KeyPair keyPairSubject,String serial) {

		try {
			
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse(certificate.getStartDate());
			Date endDate = iso8601Formater.parse(certificate.getEndDate());
			
			//Serijski broj sertifikata
			String sn= serial;
			
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
