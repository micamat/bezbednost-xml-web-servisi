package ftn.uns.ac.rs.config;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;

import ftn.uns.ac.rs.model.ProducerPort;

public class Auth {

	
	
	public static void authenticateClient(ProducerPort tempPort) {
		Client client = ClientProxy.getClient(tempPort);
		HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
		ftn.uns.ac.rs.config.SoapClientConfig soapClientConfig = new SoapClientConfig();
		KeyManagerFactory keyManagerFactory = soapClientConfig.getKeyManagerFactory();
		TrustManagerFactory trustManagerFactory = soapClientConfig.getTrustManagerFactory();
		TLSClientParameters tslClientParameters = httpConduit.getTlsClientParameters();
		if (tslClientParameters == null) {
			tslClientParameters = new TLSClientParameters();
		}
		tslClientParameters.setTrustManagers(trustManagerFactory.getTrustManagers());
		tslClientParameters.setKeyManagers(keyManagerFactory.getKeyManagers());
		tslClientParameters.setDisableCNCheck(true);
		httpConduit.setTlsClientParameters(tslClientParameters);
	}
}
