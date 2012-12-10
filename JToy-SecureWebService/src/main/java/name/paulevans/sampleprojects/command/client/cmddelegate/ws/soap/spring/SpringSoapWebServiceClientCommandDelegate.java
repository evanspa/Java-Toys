package name.paulevans.sampleprojects.command.client.cmddelegate.ws.soap.spring;

import name.paulevans.sampleprojects.command.client.ClientCommand;
import name.paulevans.sampleprojects.command.client.cmddelegate.ClientCommandDelegate;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;

/**
 * <p>Client command delegate that invokes a SOAP-based web service endpoint
 * using the Spring framework</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class SpringSoapWebServiceClientCommandDelegate implements
ClientCommandDelegate {
	
	/**
	 * Spring web service template
	 */
	private WebServiceTemplate webServiceTemplate;
	
	/**
	 * Stores the soap action to be used on outgoing SOAP requests
	 */
	private String soapAction;
	
	@Override
	public Object execute(ClientCommand pCommand) {
		
		// invoke the service and simply return the result of the invocation...
		return webServiceTemplate.marshalSendAndReceive(pCommand,
				new WebServiceMessageCallback() {

			// set the soap action...
	        public void doWithMessage(WebServiceMessage pMessage) {
	            ((SoapMessage)pMessage).setSoapAction(soapAction);
	        }
	    });
	}

	/**
	 * @param pWebServiceTemplate the webServiceTemplate to set
	 */
	public final void setWebServiceTemplate(final WebServiceTemplate
			pWebServiceTemplate) {
		webServiceTemplate = pWebServiceTemplate;
	}

	/**
	 * @param pSoapAction the soapAction to set
	 */
	public final void setSoapAction(final String pSoapAction) {
		soapAction = pSoapAction;
	}
}
