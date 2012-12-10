package name.paulevans.samplecodeprojects.webservice.client;

import name.paulevans.samplecodeprojects.command.Command;
import name.paulevans.samplecodeprojects.marshaler.Marshaler;

/**
 * <p>Low-level interface for invoking a 'service'.  At this level of the
 * abstraction a 'service' could be anything; it could be a remote EJB, a 
 * local method call, a remote RESTful web service, a remote SOAP web service,
 * etc.</p>
 * 
 * <p>This invoker requires a marshaler; at this level of the abstraction,
 * a 'marshaler' is simply an object that prepares the request for consumption
 * by the service; it also prepares the response from the service for
 * consumption by the user of this object.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public interface ServiceInvoker {

	/**
	 * <p>Sets a marshaler into this service invoker.</p>
	 * 
	 * @param pMarshaler
	 */
	void setMarshaler(Marshaler pMarshaler);
	
	/**
	 * <p>Returns the marshaler associated with this service invoker</p>
	 * 
	 * @return
	 */
	Marshaler getMarshaler();
	
	/**
	 * <p>Invokes the service</p>
	 * 
	 * @param pRequest
	 * @return
	 */
	Object invoke(Command pRequest);
}
