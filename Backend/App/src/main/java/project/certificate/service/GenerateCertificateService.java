package project.certificate.service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.stereotype.Service;

import project.certificate.data.IssuerData;
import project.certificate.data.SubjectData;
import project.certificate.dto.CertificateDTO;
import project.certificate.generators.CertificateGenerator;
import project.certificate.generators.KeyGenerator;
import project.certificate.keystore.KeyStoreWriter;

@Service
public class GenerateCertificateService {
	
	public boolean createCertificate(CertificateDTO certificate)
	{
		//kreiramo par kljuceva
		KeyGenerator kg=new KeyGenerator();
		KeyPair keyPairSubject = kg.generateKeys();
		//napravimo podatke za onoga ko izdaje sertifikate
		SubjectData subjectData = generateSubjectData(certificate,keyPairSubject);
		IssuerData issuerData = null;
		
		//e sad ovde u zavisnosti da li je self signed onda ulazi u ovaj if jer ovo alias
		//ce biti nulll
		if(certificate.getAlias()==null) {
			//ovde je selfsigned
			//ja sam ovde odradio samo za ovo self signed
			issuerData = generateIssuerData(certificate,keyPairSubject.getPrivate());
		}
		else{
			//ovde iscipati iz baze
			//ovde treba da se odradi za ovaj sto ga neko potpisuje
			//issuerData = generateIssuerData(certificate,keyPairSubject.getPrivate());
		}
		System.out.println("Kljuce je : " + issuerData.getPrivateKey());
		 
	    
		//Generise se sertifikat za subjekta, potpisan od strane issuer-a
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		
		//ovde isto videti kako cuvati ...
		KeyStoreWriter wr = new KeyStoreWriter();
		wr.loadKeyStore(null, null);
		wr.write(cert.getSubjectDN().getName(), issuerData.getPrivateKey(), "maja".toCharArray(), cert);
		wr.saveKeyStore("keyStore1.jks", "maja".toCharArray());
		
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
