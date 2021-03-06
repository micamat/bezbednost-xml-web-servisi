package ftn.uns.ac.rs.keystore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;

import ftn.uns.ac.rs.security.Username;

public class KeyStoreWriter {
	//KeyStore je Java klasa za citanje specijalizovanih datoteka koje se koriste za cuvanje kljuceva
	//Tri tipa entiteta koji se obicno nalaze u ovakvim datotekama su:
	// - Sertifikati koji ukljucuju javni kljuc
	// - Privatni kljucevi
	// - Tajni kljucevi, koji se koriste u simetricnima siframa
	private KeyStore keyStore;
private Logger logger = LogManager.getLogger();
	
	private static final Marker USER = MarkerManager
			   .getMarker("USER");
	
	public KeyStoreWriter() {
		try {
			keyStore = KeyStore.getInstance("JKS", "SUN");
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}
	
	public void loadKeyStore(String fileName, char[] password) {
		ThreadContext.put("user", Username.getLoggedUser());

		try {
			if(fileName != null) {
				keyStore.load(new FileInputStream(fileName), password);
				logger.info(USER, "Ucitan keystore");

			} else {
				//Ako je cilj kreirati novi KeyStore poziva se i dalje load, pri cemu je prvi parametar null
				keyStore.load(null, password);
				logger.info(USER, "Kreiran keystore");

			}

		} catch (NoSuchAlgorithmException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (CertificateException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void saveKeyStore(String fileName, char[] password) {
		ThreadContext.put("user", Username.getLoggedUser());

		try {
			keyStore.store(new FileOutputStream(fileName), password);
		} catch (KeyStoreException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (CertificateException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void write(String alias, PrivateKey privateKey, char[] password, Certificate certificate, Certificate certSigner,String signerAlias) {
		ThreadContext.put("user", Username.getLoggedUser());

		try {
			keyStore.setKeyEntry(alias, privateKey, password, new Certificate[] {certificate,certSigner});
			keyStore.setCertificateEntry(signerAlias, certSigner);
		} catch (KeyStoreException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public void write(String alias, PrivateKey privateKey, char[] password, Certificate certificate) {
		ThreadContext.put("user", Username.getLoggedUser());

		try {
			keyStore.setKeyEntry(alias, privateKey, password, new Certificate[] {certificate});
		} catch (KeyStoreException e) {
			logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
