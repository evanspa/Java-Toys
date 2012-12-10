package name.paulevans.samplecodeprojects.webservice.server.xmlws.impl;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Provider;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

import name.paulevans.samplecodeprojects.util.XMLUtils;
import name.paulevans.samplecodeprojects.webservice.server.xmlws.DefaultXmlWsOrderServiceProvider;

import org.apache.commons.io.IOUtils;

/**
 * <p>JAX-WS Provider-based SOAP-based web service endpoint<p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
@ServiceMode(value=javax.xml.ws.Service.Mode.PAYLOAD)
@WebServiceProvider(
		portName="OrderSystemPort",
		serviceName="OrderService",
		targetNamespace="http://name.paulevans/samples/OrderSystem/services",
		wsdlLocation="WEB-INF/wsdl/OrderSystem-concrete-soap.wsdl"
)
public class JaxWsOrderWebService extends DefaultXmlWsOrderServiceProvider 
implements Provider<Source> {
	
	@Override
	public Source invoke(Source pRequest) {	
		
		String xmlResponse;
		
		// process the request and store the response xml...
		xmlResponse = invokeService(XMLUtils.toString(pRequest));
		
		// return the response XML as a stream source...
		return new StreamSource(IOUtils.toInputStream(xmlResponse));
	}
}