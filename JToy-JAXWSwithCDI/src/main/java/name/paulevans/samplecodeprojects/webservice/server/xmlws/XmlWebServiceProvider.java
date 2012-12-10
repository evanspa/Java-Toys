package name.paulevans.samplecodeprojects.webservice.server.xmlws;

import name.paulevans.samplecodeprojects.webservice.server.ServiceProvider;

/**
 * <p>Defines an XML-based web service provider</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public interface XmlWebServiceProvider extends ServiceProvider {
	
	/**
	 * <p>Processes XML-based requests made to this service</p>
	 * 
	 * @param pRequest
	 * @return
	 */
	String invokeService(String pRequest);

}
