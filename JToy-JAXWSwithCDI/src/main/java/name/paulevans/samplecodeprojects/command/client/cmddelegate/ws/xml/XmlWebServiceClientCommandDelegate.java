package name.paulevans.samplecodeprojects.command.client.cmddelegate.ws.xml;

import name.paulevans.samplecodeprojects.command.client.ClientCommand;
import name.paulevans.samplecodeprojects.command.client.cmddelegate.ClientCommandDelegate;
import name.paulevans.samplecodeprojects.webservice.client.xmlws.XmlServiceInvoker;

/**
 * <p>This client command delegate will invoke a remote XML-based (SOAP probably)
 * web service.  This client command class uses an XML service invoker to call
 * the remote web service.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class XmlWebServiceClientCommandDelegate implements
		ClientCommandDelegate {
	
	/**
	 * XML service invoker
	 */
	private XmlServiceInvoker xmlServiceInvoker;

	@Override
	public Object execute(ClientCommand pCommand) {
		
		// invoke the service and simply return the result of the invocation...
		return xmlServiceInvoker.invoke(pCommand);
	}
	
	/**
	 * <p>Sets the XML web service invoker</p>
	 * 
	 * @param pXmlServiceInvoker
	 */
	public final void setXmlServiceInvoker(XmlServiceInvoker pXmlServiceInvoker) {
		xmlServiceInvoker = pXmlServiceInvoker;
	}
}
