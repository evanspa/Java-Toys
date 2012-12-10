package name.paulevans.samplecodeprojects.command.client;

import name.paulevans.samplecodeprojects.command.CommandSupport;
import name.paulevans.samplecodeprojects.command.client.cmddelegate.ClientCommandDelegate;

/**
 * <p>Convenience class for concrete command classes to extend</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public abstract class ClientCommandSupport extends CommandSupport
implements ClientCommand {
	
	/**
	 * Spring web service template
	 */
	private ClientCommandDelegate clientCommandDelegate;
	
	@Override
	public Object execute() {
		
		// use the delegate to carry out the execution of the command...
		return clientCommandDelegate.execute(this);
	}

	/**
	 * @param pClientCommandDelegate the delegate to set
	 */
	public final void setClientCommandDelegate(final ClientCommandDelegate
			pClientCommandDelegate) {
		clientCommandDelegate = pClientCommandDelegate;
	}
}
