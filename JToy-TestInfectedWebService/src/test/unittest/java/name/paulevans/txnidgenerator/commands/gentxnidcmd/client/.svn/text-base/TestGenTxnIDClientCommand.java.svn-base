package name.paulevans.txnidgenerator.commands.gentxnidcmd.client;

import name.paulevans.distcmdfrmwk.CommandProcessingException;
import name.paulevans.distcmdfrmwk.client.cmddelgt.ClientCommandDelegate;
import name.paulevans.distcmdfrmwk.client.cmddelgt.ClientCommandDelegateException;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmdAbstractTest;

import org.easymock.EasyMock;


/**
 * <p>Test for {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand}
 * </p>
 * 
 * @author Paul R Evans
 *
 */
public class TestGenTxnIDClientCommand extends GenTxnIDCmdAbstractTest {
	
	/**
	 * Object that we'll use to test with
	 */
	private GenTxnIDClientCommand genTxnIdentifierCmd;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// create our object-under-test...
		genTxnIdentifierCmd = (GenTxnIDClientCommand)getCommand();
	}

	@Override
	public GenTxnIDCmd getCommand() {
		
		// instantiate the command object...
		return new GenTxnIDClientCommand();
	}
	
	/**
	 * <p>Test for
	 * {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand#execute()}
	 * </p>
	 */
	public final void testExecute() {

		// mock delegate...
		ClientCommandDelegate mockClientDelegate;
		Object cmdExecutionResult;
		
		// sanity check...
		assertNotNull(genTxnIdentifierCmd);
		
		// create our mock (it is by default in 'record' mode)...
		mockClientDelegate = EasyMock.createMock(ClientCommandDelegate.class);	
		
		// create fictional result for client command execution...
		cmdExecutionResult = new Object();
		
		// define the intended behavior to be experienced by the mock...
		EasyMock.expect(mockClientDelegate.execute(genTxnIdentifierCmd)).
			andReturn(cmdExecutionResult);		
		
		// replay the mock (setting the mock into 'playback' mode)...
		EasyMock.replay(mockClientDelegate);
		
		// set the mock into our object-under-system...
		genTxnIdentifierCmd.setClientCommandDelegate(mockClientDelegate);
		
		// perform the tests / assertions...
		cmdExecutionResult = genTxnIdentifierCmd.execute();
		assertNotNull(cmdExecutionResult);
		
		// verify the mock...
		EasyMock.verify(mockClientDelegate);	
	}
	
	/**
	 * <p>Test for 
	 * {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand#execute()}
	 * </p>
	 * 
	 * <p>Specifically to ensure proper behavior of the client command instance 
	 * when its delegate is configured to throw a client command delegate
	 * exception.  The execute() method of the client command should throw
	 * a generic command processing exception.</p>
	 */
	public final void testCmdProcessingException() {

		// mock delegate...
		ClientCommandDelegate mockClientDelegate;
		
		// sanity check...
		assertNotNull(genTxnIdentifierCmd);
		
		// create our mock (it is by default in 'record' mode)...
		mockClientDelegate = EasyMock.createMock(ClientCommandDelegate.class);	
		
		// define the intended behavior to be experienced by the mock...
		EasyMock.expect(mockClientDelegate.execute(genTxnIdentifierCmd)).
			andThrow(new ClientCommandDelegateException(
					"some delegate exception", new RuntimeException(
							"test exception")));
		
		// replay the mock (setting the mock into 'playback' mode)...
		EasyMock.replay(mockClientDelegate);
		
		// set the mock into our object-under-system...
		genTxnIdentifierCmd.setClientCommandDelegate(mockClientDelegate);
		
		// perform the tests / assertions...
		try {
			genTxnIdentifierCmd.execute();
			fail("expected command processing exception to be thrown");
			
		} catch (CommandProcessingException pCmdProcessingException) {
			// intentionally empty...
		}
		
		// verify the mock...
		EasyMock.verify(mockClientDelegate);	
	}
}
