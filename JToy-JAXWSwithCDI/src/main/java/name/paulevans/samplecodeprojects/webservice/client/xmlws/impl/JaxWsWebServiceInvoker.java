package name.paulevans.samplecodeprojects.webservice.client.xmlws.impl;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import name.paulevans.samplecodeprojects.command.Command;
import name.paulevans.samplecodeprojects.util.XMLUtils;
import name.paulevans.samplecodeprojects.webservice.client.xmlws.XmlServiceInvokerSupport;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * <p></p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class JaxWsWebServiceInvoker extends XmlServiceInvokerSupport {
	
	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(
			JaxWsWebServiceInvoker.class);
	
	/**
	 * Namespace URI of the service
	 */
	private static final String NAMESPACE_URI = 
		"http://name.paulevans/samples/OrderSystem/services";

	/**
	 * The name of the service
	 */
	private static final String SERVICE_NAME = "OrderService"; 

	/**
	 * The port name of the service.
	 */
	private static final String PORT_NAME = "OrderSystemPort";
	
	/**
	 * The endpoint URL of the web service
	 */
	private static final String SERVICE_URL = 
		"http://localhost:8088/JaxWsProviderApi/OrderService";

	@Override
	public Object invoke(Command pRequest) {
		
		Service jaxWsServiceClient;
		Dispatch<Source> jaxWsDispatch;
		String commandXml;
		Source responseSource;
		String responseXml;
		Object responseObj;
		
		try {
		
			// create the jax-ws service client object...
			jaxWsServiceClient = Service.create(new URL(SERVICE_URL), new QName(
				NAMESPACE_URI, SERVICE_NAME));
			
			// create the jax-ws dispatch object...
			jaxWsDispatch = jaxWsServiceClient.createDispatch(new QName(
					NAMESPACE_URI, PORT_NAME), Source.class, 
					Service.Mode.PAYLOAD);
			
			// marshal the command object into an XML string...
			commandXml = getXmlMarshaler().marshalCommand(pRequest);
					
			// invoke the service...
			responseSource = jaxWsDispatch.invoke(new StreamSource(IOUtils.
					toInputStream(commandXml)));
			
			// convert the response Source object into a string...
			responseXml = XMLUtils.toString(responseSource);
			
			// unmarshaler the response XML string into an object...
			responseObj = getXmlMarshaler().unmarshalCommandResult(responseXml);
		
			// log it...
			logger.debug("response XML payload unmarshaled into object of type: [" + 
					responseObj.getClass().getName() + "]");
			
			// return it...
			return responseObj;			
			
		} catch (Exception any) {
			logger.error(any);
			throw new RuntimeException(any);
		}
	}

}
