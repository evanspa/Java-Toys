package name.paulevans.samplecodeprojects.endpoint.xml;

import javax.inject.Inject;

import name.paulevans.samplecodeprojects.command.Command;
import name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler;

import org.apache.log4j.Logger;

/**
 * <p>General definition of an order service endpoint.  Endpoints are an
 * abstraction that model the processing/handling of requests from clients.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class DefaultXmlEndpoint implements XmlEndpoint {	
	
	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(
			DefaultXmlEndpoint.class);
	
	/**
	 * XML marshaler
	 */
	@Inject private XmlMarshaler xmlMarshaler;
	
	@Override
	public String processRequest(String pRequestXml) {
			
			Object commandResult;
			String marshalResult;
			
			// extrapolate command object from request...
			Command command = xmlMarshaler.unmarshalRequest(pRequestXml);
			logger.info("request un-marshaled into command object of type: [" + 
					command.getClass().getName() + "]");
			
			// marshal and return the object returned from the command
			// executor...
			commandResult = command.execute();
			logger.info("command result: [" + commandResult.toString() + "]");
			
			// marshal the command result...
			marshalResult = xmlMarshaler.marshalCommandResult(commandResult);
			logger.info("marshaled command result: [" + marshalResult + "]");
			
			// return the marshal result...
			return marshalResult;
	}
}
