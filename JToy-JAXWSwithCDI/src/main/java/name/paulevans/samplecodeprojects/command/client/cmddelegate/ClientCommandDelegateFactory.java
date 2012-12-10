package name.paulevans.samplecodeprojects.command.client.cmddelegate;

import name.paulevans.samplecodeprojects.command.client.cmddelegate.ws.xml.XmlWebServiceClientCommandDelegate;
import name.paulevans.samplecodeprojects.command.client.marshaler.ClientSideCastorXmlMarshaler;
import name.paulevans.samplecodeprojects.webservice.client.xmlws.XmlServiceInvoker;
import name.paulevans.samplecodeprojects.webservice.client.xmlws.impl.JaxWsWebServiceInvoker;

/**
 * <p>Simple factory that creates client command delegate instances.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class ClientCommandDelegateFactory {

	/**
	 * <p>Client command delegate that invokes a remote web service using
	 * JAX-WS and uses a Castor-based marshaler to marshal requests to the service
	 * to XML and to un-marshaler the XML response.</p>
	 * 
	 * @return the client command delegate
	 */
	public static final ClientCommandDelegate
		newXmlWsInvokerCommandDelegate_withJaxWs_and_Castor() {
		
		XmlServiceInvoker xmlServiceInvoker;
		XmlWebServiceClientCommandDelegate commandDelegate;
		
		// create the JAX-WS web service invoker...
		xmlServiceInvoker = new JaxWsWebServiceInvoker();
		
		// provide the invoker with a Castor marshaler/unmarshaler...
		xmlServiceInvoker.setXmlMarshaler(new ClientSideCastorXmlMarshaler());
		
		// create a client command delegate (this delegate type is expecting
		// an 'xml web service invoker')...
		commandDelegate = new XmlWebServiceClientCommandDelegate();
		
		// set the invoker...
		commandDelegate.setXmlServiceInvoker(xmlServiceInvoker);
		
		// return it!
		return commandDelegate;		
	}
}
