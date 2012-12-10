package name.paulevans.samplecodeprojects.marshaler.xml;

import name.paulevans.samplecodeprojects.command.Command;
import name.paulevans.samplecodeprojects.marshaler.Marshaler;

/**
 * <p>Specialized marshaler that understands XML-based client requests</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public interface XmlMarshaler extends Marshaler {
	
	/**
	 * <p>Marshals a command object into some XML-based target suitable for
	 * consumption by the service</p>
	 * 
	 * <p>See {@link name.paulevans.samplecodeprojects.command.Command} implementations
	 * to understand requirements around what a command is required to have
	 * populated within it.</p>
	 * 
	 * <p>Used by clients</p>
	 * 
	 * @param pCommand
	 * @return
	 */
	String marshalCommand(Command pCommand);
	
	/**
	 * <p>Un-marshals an XML-based request to a command object</p>
	 * 
	 * <p>Used by servers</p>
	 * 
	 * @param pRequest
	 * 
	 * @return
	 */
	Command unmarshalRequest(String pRequestXml);
	
	/**
	 * <p>Marshals specified object into some XML-based target</p>
	 * 
	 * <p>Used by servers</p>
	 * 
	 * @param pObject
	 * 
	 * @return
	 */
	String marshalCommandResult(Object pObject);
	
	/**
	 * <p>Unmarshals XML reprsentation of command result to an object.</p>
	 * 
	 * <p>Used by clients</p>
	 * 
	 * @param pResultXml
	 * 
	 * @return
	 */
	Object unmarshalCommandResult(String pResultXml);

}
