package integrationtest.clientcmd_to_servercmd.local;



import integrationtest.clientcmd_to_servercmd.GenTxnIDClientCmdAbstractIntegrationTest;
import name.paulevans.distcmdfrmwk.client.cmddelgt.local.LocalDelegate;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.server.GenTxnIDServerCommand;

/**
 * <p>Concrete end-to-end integration test with client and server commands.  
 * This test specifically tests correct integration in which the client
 * is configured with a local delegate proxy-ing to the server command instance.</p>
 * 
 * <p>This is the most simplest integration between client and server commands;
 * in this case they are both running in the same JVM.  The "proxy-ing" performed
 * by the delegate amounts to nothing more than local method invocations.</p>
 * 
 * <p>The actual test methods are defined in the abstract super class.</p>
 * 
 * @author Paul R Evans
 *
 */
public class TestGenTxnIDClientCmd_withLocalDelegate
extends GenTxnIDClientCmdAbstractIntegrationTest {

	@Override
	public GenTxnIDClientCommand getClientCommand() {
		
		LocalDelegate localDelegate;
		GenTxnIDClientCommand genTxnIdentifierCmd;
		
		// instantiate the command object...
		genTxnIdentifierCmd = new GenTxnIDClientCommand();
		
		// create local delegate and configure mapping between our client
		// and server command classes...
		localDelegate = new LocalDelegate();
		localDelegate.addMapping(GenTxnIDClientCommand.class,
			GenTxnIDServerCommand.class);
		
		// specify the local delegate...
		genTxnIdentifierCmd.setClientCommandDelegate(localDelegate);
		
		// return it!
		return genTxnIdentifierCmd;
	}
}
