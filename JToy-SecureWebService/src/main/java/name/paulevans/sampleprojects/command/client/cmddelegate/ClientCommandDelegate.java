package name.paulevans.sampleprojects.command.client.cmddelegate;

import name.paulevans.sampleprojects.command.client.ClientCommand;

/**
 * <p>Defines abstraction that client command objects will use to actually
 * perform their execution.</p>
 * 
 * <p>Providing this layer of indirection allows the implementation of how
 * a client command performs its own execution to be pluggable.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public interface ClientCommandDelegate {

	/**
	 * <p>Executes the supplied command</p>
	 * 
	 * @param pCommand the command to execute
	 * 
	 * @return the result of the command processing
	 */
	Object execute(ClientCommand pCommand);
}
