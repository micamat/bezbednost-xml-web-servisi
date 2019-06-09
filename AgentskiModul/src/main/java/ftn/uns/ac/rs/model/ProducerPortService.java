package ftn.uns.ac.rs.model;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.3.2 2019-06-08T10:32:41.390+02:00
 * Generated source version: 3.3.2
 *
 */
@WebServiceClient(name = "ProducerPortService", wsdlLocation = "classpath:wsdl/MegaTravel.wsdl", targetNamespace = "http://rs.ac.uns.ftn/Model")
public class ProducerPortService extends Service {

	public final static URL WSDL_LOCATION;

	public final static QName SERVICE = new QName("http://rs.ac.uns.ftn/Model", "ProducerPortService");
	public final static QName ProducerPortSoap11 = new QName("http://rs.ac.uns.ftn/Model", "ProducerPortSoap11");
	static {
		URL url = ProducerPortService.class.getClassLoader().getResource("wsdl/MegaTravel.wsdl");
		if (url == null) {
			java.util.logging.Logger.getLogger(ProducerPortService.class.getName()).log(java.util.logging.Level.INFO,
					"Can not initialize the default wsdl from {0}", "classpath:wsdl/MegaTravel.wsdl");
		}
		WSDL_LOCATION = url;
	}

	public ProducerPortService(URL wsdlLocation) {
		super(wsdlLocation, SERVICE);
	}

	public ProducerPortService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public ProducerPortService() {
		super(WSDL_LOCATION, SERVICE);
	}

	public ProducerPortService(WebServiceFeature... features) {
		super(WSDL_LOCATION, SERVICE, features);
	}

	public ProducerPortService(URL wsdlLocation, WebServiceFeature... features) {
		super(wsdlLocation, SERVICE, features);
	}

	public ProducerPortService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
		super(wsdlLocation, serviceName, features);
	}

	/**
	 *
	 * @return returns ProducerPort
	 */
	@WebEndpoint(name = "ProducerPortSoap11")
	public ProducerPort getProducerPortSoap11() {
		return super.getPort(ProducerPortSoap11, ProducerPort.class);
	}

	/**
	 *
	 * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure
	 *                 on the proxy. Supported features not in the
	 *                 <code>features</code> parameter will have their default
	 *                 values.
	 * @return returns ProducerPort
	 */
	@WebEndpoint(name = "ProducerPortSoap11")
	public ProducerPort getProducerPortSoap11(WebServiceFeature... features) {
		return super.getPort(ProducerPortSoap11, ProducerPort.class, features);
	}

}