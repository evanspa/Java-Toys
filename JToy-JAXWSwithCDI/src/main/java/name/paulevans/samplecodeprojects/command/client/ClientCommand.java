package name.paulevans.samplecodeprojects.command.client;

import name.paulevans.samplecodeprojects.command.Command;
import name.paulevans.samplecodeprojects.command.client.cmddelegate.ClientCommandDelegate;

public interface ClientCommand extends Command {

	/**
	 * @param pClientCommandDelegate the delegate to set
	 */
	void setClientCommandDelegate(ClientCommandDelegate pClientCommandDelegate);
	
}
