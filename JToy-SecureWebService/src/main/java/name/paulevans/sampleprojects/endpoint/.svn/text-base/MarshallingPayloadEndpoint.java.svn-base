package name.paulevans.sampleprojects.endpoint;

import name.paulevans.sampleprojects.command.Command;
import name.paulevans.sampleprojects.command.CommandExecutor;

import org.springframework.ws.server.endpoint.AbstractMarshallingPayloadEndpoint;

/**
 * <p>Simple endpoint class - all it does is 'execute' the command object that
 * was un-marshaled from the request XML payload (the marshaling/un-marshaling
 * is handled by the Spring-WS framework.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class MarshallingPayloadEndpoint extends AbstractMarshallingPayloadEndpoint {

	@Override
	protected Object invokeInternal(Object pUnmarshaledRequest)
	throws Exception {
		
		Object commandResult;
		Command command;
		
		// cast-down for convenience...
		command = (Command)pUnmarshaledRequest;
		
		// log the class of the un-marshaled command object...
		logger.info("request un-marshaled into command object of type: [" + 
				command.getClass().getName() + "]");
		
		// execute the command!
		commandResult = new CommandExecutor().executeCommand(command);
		
		// log the result of the command execution...
		logger.info("command result: [" + commandResult.toString() + "]");
		
		// return the command result!
		return commandResult;
	}
}
