package ftn.uns.ac.rs.generator;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;

import ftn.uns.ac.rs.security.Username;

public class KeyGenerator {
private Logger logger = LogManager.getLogger();
	
	private static final Marker USER = MarkerManager
			   .getMarker("USER");
	public KeyGenerator(){

	}

	public KeyPair generateKeys() {
		ThreadContext.put("user", Username.getLoggedUser());

        try {
			//Generator para kljuceva
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			//Za kreiranje kljuceva neophodno je definisati generator pseudoslucajnih brojeva
			//Ovaj generator mora biti bezbedan (nije jednostavno predvideti koje brojeve ce RNG generisati)
			//U ovom primeru se koristi generator zasnovan na SHA1 algoritmu, gde je SUN provajder
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			//inicijalizacija generatora, 2048 bitni kljuc
			keyGen.initialize(2048, random);
			logger.info(USER, "Generisan par kljuceva");
			//generise par kljuceva koji se sastoji od javnog i privatnog kljuca
			return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
        	logger.error(USER, "Greska: " + e.getMessage());
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
        	logger.error(USER, "Greska: " + e.getMessage());

			e.printStackTrace();
		}
        return null;
	}
}
