package name.paulevans.txnidgenerator.commands.gentxnidcmd.client;

import name.paulevans.distcmdfrmwk.CommandProcessingException;
import name.paulevans.distcmdfrmwk.client.ClientCommand;
import name.paulevans.distcmdfrmwk.client.cmddelgt.ClientCommandDelegate;
import name.paulevans.distcmdfrmwk.client.cmddelgt.ClientCommandDelegateException;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmdSupport;

/**
 * <p>Concrete client-side command object for the generate transaction
 * identifier command.</p>
 * 
 * @author Paul R Evans
 *
 */
public class GenTxnIDClientCommand extends GenTxnIDCmdSupport
implements ClientCommand {
	
	/**
	 * The command delegate
	 */
	private ClientCommandDelegate clientCommandDelegate;

	/**
	 * <p>This method serves as a proxy for a "server-side" command
	 * object that contains the actual business logic associated
	 * with this command.</p>
	 * 
	 * <p>A client command delegate is used to handle the invocation
	 * of the server-side command object.  The delegate's execute
	 * method receives this command object as input, and carries out
	 * the execution.</p>
	 */
	public Object execute() {
		
		try {
			// delegate to the command delegate...
			return clientCommandDelegate.execute(this);
			
		} catch (ClientCommandDelegateException pAny) {
			
			// catch any client command delegate exception and re-throw as
			// generic command processing exception...
			throw new CommandProcessingException(pAny);
		}
	}

	@Override
	public void setClientCommandDelegate(final ClientCommandDelegate 
			pClientCommandDelegate) {
		clientCommandDelegate = pClientCommandDelegate;
	}
}
