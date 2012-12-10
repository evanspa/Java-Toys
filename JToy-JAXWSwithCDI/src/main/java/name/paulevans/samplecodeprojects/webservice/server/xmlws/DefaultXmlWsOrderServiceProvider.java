package name.paulevans.samplecodeprojects.webservice.server.xmlws;

import javax.inject.Inject;

import name.paulevans.samplecodeprojects.endpoint.xml.XmlEndpoint;
import name.paulevans.samplecodeprojects.webservice.server.ServiceProviderSupport;

/**
 * <p>Generic provider for an XML-based web service<p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class DefaultXmlWsOrderServiceProvider extends ServiceProviderSupport
implements XmlWebServiceProvider {	
	
	/**
	 * XML-based endpoint
	 */	
	@Inject private XmlEndpoint xmlEndpoint;

	@Override
	public String invokeService(String pRequestXml) {
		return xmlEndpoint.processRequest(pRequestXml);
	}
}
