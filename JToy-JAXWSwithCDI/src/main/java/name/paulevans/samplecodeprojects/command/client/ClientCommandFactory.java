package name.paulevans.samplecodeprojects.command.client;

import name.paulevans.samplecodeprojects.command.client.clientcmds.CalculateOrderSubTotalCommand;
import name.paulevans.samplecodeprojects.command.client.cmddelegate.ClientCommandDelegateFactory;

/**
 * <p>Simple client command factory class</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class ClientCommandFactory {
	
	/**
	 * <p>Creates a new 'calculate order sub-total' client command.</p>
	 * 
	 * @return a new 'calculate order sub-total' client command object
	 */
	public static final CalculateOrderSubTotalCommand
		newCalculateOrderSubTotalCommand() {
		
		CalculateOrderSubTotalCommand command;
		
		// create the client command instance...
		command = new CalculateOrderSubTotalCommand();
		
		// set a client command delegate...
		command.setClientCommandDelegate(ClientCommandDelegateFactory.
				newXmlWsInvokerCommandDelegate_withJaxWs_and_Castor());
		
		// return it!
		return command;
	}
}
