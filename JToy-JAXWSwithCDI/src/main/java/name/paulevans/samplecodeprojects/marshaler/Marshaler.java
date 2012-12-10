package name.paulevans.samplecodeprojects.marshaler;

import name.paulevans.samplecodeprojects.command.Command;

/**
 * <p>Generic interface for un-marshaling client requests into command objects,
 * and marshaling objects into a target for consumption by the client</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public interface Marshaler {
	
	/**
	 * <p>Marshals a command object into some target suitable for consumption
	 * by the service</p>
	 * 
	 * @param pCommand
	 * @return
	 */
	Object marshalCommand(Command pCommand);
	
	/**
	 * <p>Un-marshals a client request into a command object</p>
	 * 
	 * @param pRequest
	 * @return
	 */
	Command unmarshalRequest(Object pRequest);
	
	/**
	 * <p>Marshals an object into some target format suitable for consumption
	 * by the client.</p>
	 * 
	 * @param pRequest
	 * @return
	 */
	Object marshalCommandResult(Object pRequest);
	
	/**
	 * Hook to allow this marshaler to be configured
	 */
	void configure();

}
