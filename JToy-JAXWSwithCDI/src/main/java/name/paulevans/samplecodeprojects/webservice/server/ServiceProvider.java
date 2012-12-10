package name.paulevans.samplecodeprojects.webservice.server;

/**
 * <p>Defines a generic contract for a "provider" (aka service)</p>
 * 
 * <p>A provider defines the channel in which clients can use to invoke
 * a service.  The actual handling of requests is delegated to an
 * endpoint object.</p>
 * 
 * <p>Really the intent of this interface is to support the concept of remoting - 
 * i.e. that this service will be running in separate JVM from clients</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public interface ServiceProvider {
	
	/**
	 * <p>Processes requests made to this service</p>
	 * 
	 * @param pRequest
	 * @return
	 */
	Object invokeService(Object pRequest);
}
