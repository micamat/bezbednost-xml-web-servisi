package ftn.uns.ac.rs.service;

import ftn.uns.ac.rs.config.Auth;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.ValidateTokenRequest;
import ftn.uns.ac.rs.model.ValidateTokenResponse;

public class ValidationService {
	public static boolean validate(String token) {
		System.out.println();
		if (token == null) {
			return false;
		}
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		// autentifikacija pomocu sertifikata
		Auth.authenticateClient(producerPort);
		System.out.println("ispis u agentskom validation servisu");
		ValidateTokenRequest validateTokenRequest = new ValidateTokenRequest();
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse();
		validateTokenRequest.setToken(token);

		validateTokenResponse = producerPort.validateToken(validateTokenRequest);
		
		if(validateTokenResponse.isSuccessful()) {
			return true;
		}
		return false;
	}
}
