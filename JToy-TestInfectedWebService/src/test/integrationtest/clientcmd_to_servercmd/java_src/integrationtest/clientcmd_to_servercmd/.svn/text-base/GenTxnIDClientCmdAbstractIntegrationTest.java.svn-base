package integrationtest.clientcmd_to_servercmd;


import name.paulevans.distcmdfrmwk.CommandProcessingException;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand;
import name.paulevans.txnidgenerator.common.TxnIdentifier;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>This is our integration test definition.  It is abstract; sub-classes
 * are required to return a concrete client command instance from which the
 * integration tests will be around.</p>
 * 
 * <p>There should be a sub-class defined for each "type" of integration test.
 * For example, there should be a sub-classes for the following:
 *   <ul>
 *     <li>To test where the client command is configured to have a local
 *     delegate; the local delegate will serve as an in-jvm proxy to the 
 *     server command object (the simplest integration possible).</li>
 *     <li>To test where the server command is encapsulated and exposed as a
 *     SOAP-based web service and the client command is configured with a
 *     delegate that will invoke the web service (a rather complex integration).
 *     In this scenario, the sub-class would be responsible for starting-up
 *     the JEE container, deploy the web service, and return a client command
 *     instance such that it is configured with a delegate that knows how to
 *     invoke the web service.</li>
 *   </ul>
 * </p>
 * 
 * @author Paul R Evans
 */
public abstract class GenTxnIDClientCmdAbstractIntegrationTest {
	
	/**
	 * <p>Object that we'll use to test with; aka our system-under-test (SUT)</p>
	 */
	private GenTxnIDClientCommand genTxnIdentifierCmd;
	
	/**
	 * <p>Setup to run before each test method</p>
	 */
	@Before public void setUp() {
		
		// get the client command instance...
		genTxnIdentifierCmd = getClientCommand();
	}
	
	/**
	 * <p>Returns a new client command instance</p>
	 * 
	 * @return new client command instance
	 */
	public abstract GenTxnIDClientCommand getClientCommand();

	/**
	 * <p>Test for 
	 * {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand#execute()}
	 * </p>
	 * 
	 * <p>Specifically testing valid use case and valid channel values</p>
	 */
	@Test public final void testExecute_validUseCase_and_invalidChannel() {

		// sanity check...
		Assert.assertNotNull(genTxnIdentifierCmd);
		
		// give the client command some valid values...
		genTxnIdentifierCmd.setChannel("ESP");
		genTxnIdentifierCmd.setUseCase("Place Order");
		
		// execute the command...
		try {
			genTxnIdentifierCmd.execute();
			Assert.fail("should not have reached this point - should have " +
					"thrown CommandProcessingException");
		} catch (CommandProcessingException pExc) {
			// expected path...
		}
	}	
	
	/**
	 * <p>Test for 
	 * {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand#execute()}
	 * </p>
	 * 
	 * <p>Specifically testing invalid use case and invalid channel values</p>
	 */
	@Test public final void testExecute_invalidUseCase_and_invalidChannel() {

		// sanity check...
		Assert.assertNotNull(genTxnIdentifierCmd);
		
		// give the client command some valid values...
		genTxnIdentifierCmd.setChannel("ESP");
		genTxnIdentifierCmd.setUseCase("Pay with cash");
		
		// execute the command...
		try {
			genTxnIdentifierCmd.execute();
			Assert.fail("should not have reached this point - should have " +
				"thrown CommandProcessingException");
		} catch (CommandProcessingException pExc) {
			// expected path...
		}
	}	
	
	/**
	 * <p>Test for 
	 * {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand#execute()}
	 * </p>
	 * 
	 * <p>Specifically testing invalid use case and valid channel values</p>
	 */
	@Test public final void testExecute_invalidUseCase_and_validChannel() {

		// sanity check...
		Assert.assertNotNull(genTxnIdentifierCmd);
		
		// give the client command some valid values...
		genTxnIdentifierCmd.setChannel("Web");
		genTxnIdentifierCmd.setUseCase("Pay with cash");
		
		// execute the command...
		try {
			genTxnIdentifierCmd.execute();
			Assert.fail("should not have reached this point - should have " +
				"thrown CommandProcessingException");
		} catch (CommandProcessingException pExc) {
			// expected path...
		}
	}
	
	/**
	 * <p>Test for 
	 * {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand#execute()}
	 * </p>
	 * 
	 * <p>Specifically testing valid channel and use case combinations</p>
	 */
	@Test public final void testExecute_validUseCase_and_validChannel() {

		Object cmdExecutionResult;
		
		// sanity check...
		Assert.assertNotNull(genTxnIdentifierCmd);
		
		// give the client command some valid values...
		genTxnIdentifierCmd.setChannel("Web");
		genTxnIdentifierCmd.setUseCase("Place Order");
		
		// execute the command...
		cmdExecutionResult = genTxnIdentifierCmd.execute();
		
		// assert we got back a txn identifier object and its first 2 characters
		// are what we expect it to have...
		Assert.assertNotNull(cmdExecutionResult);
		Assert.assertTrue(cmdExecutionResult instanceof TxnIdentifier);
		Assert.assertEquals("PW", StringUtils.substring(
				((TxnIdentifier)cmdExecutionResult).getIdentifierValue(), 0, 2));
	}
}
