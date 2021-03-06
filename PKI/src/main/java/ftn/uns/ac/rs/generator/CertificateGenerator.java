package ftn.uns.ac.rs.generator;

import java.math.BigInteger;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import ftn.uns.ac.rs.data.IssuerData;
import ftn.uns.ac.rs.data.SubjectData;
import ftn.uns.ac.rs.security.Username;

public class CertificateGenerator {
	

	private Logger logger = LogManager.getLogger();
	
	private static final Marker USER = MarkerManager
			   .getMarker("USER");
	public CertificateGenerator() {}
	
	public X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData, boolean isCa) throws CertIOException {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			//Posto klasa za generisanje sertifiakta ne moze da primi direktno privatni kljuc pravi se builder za objekat
			//Ovaj objekat sadrzi privatni kljuc izdavaoca sertifikata i koristiti se za potpisivanje sertifikata
			//Parametar koji se prosledjuje je algoritam koji se koristi za potpisivanje sertifiakta
			JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
			//Takodje se navodi koji provider za potpisivanje se koristi, u ovom slucaju Bouncy Castle
			builder = builder.setProvider("BC");

			//Formira se objekat koji ce sadrzati privatni kljuc i koji ce se koristiti za potpisivanje sertifikata
			ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

			//Postavljaju se podaci za generisanje sertifiakta
			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerData.getX500name(),
					new BigInteger(subjectData.getSerialNumber()),
					subjectData.getStartDate(),
					subjectData.getEndDate(),
					subjectData.getX500name(),
					subjectData.getPublicKey());
			
			if(isCa==true) {
		    	// Basic Constraint
			    BasicConstraints basicConstraints = new BasicConstraints(true); // <-- true for CA, false for EndEntity
				certGen.addExtension(new ASN1ObjectIdentifier("2.5.29.19"), true, basicConstraints); // Basic Constraints is usually marked as critical.
		    }
		    else {
		    	// Basic Constraint
			    BasicConstraints basicConstraints = new BasicConstraints(false); // <-- true for CA, false for EndEntity
				certGen.addExtension(new ASN1ObjectIdentifier("2.5.29.19"), true, basicConstraints); // Basic Constraints is usually marked as critical.
		    }

			
			//Generise se sertifikat
			X509CertificateHolder certHolder = certGen.build(contentSigner);

			//Builder generise sertifikat kao objekat klase X509CertificateHolder
			//Nakon toga je potrebno certHolder konvertovati u sertifikat, za sta se koristi certConverter
			JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
			certConverter = certConverter.setProvider("BC");
			ThreadContext.put("user", Username.getLoggedUser());
			//Konvertuje objekat u sertifikat
			logger.info(USER, "Generisan sertifikat");
			return certConverter.getCertificate(certHolder);
		} catch (CertificateEncodingException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalStateException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (OperatorCreationException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (CertificateException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
