package name.paulevans.samplecodeprojects.endpoint.xml;


/**
 * <p>Endpoint abstraction to handle XML-based requests</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public interface XmlEndpoint {

	/**
	 * <p>Processes the request</p>
	 * 
	 * @param pRequest
	 * @return
	 */
	String processRequest(String pRequestXml);
}
