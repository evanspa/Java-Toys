package name.paulevans.txnidgenerator.commands.gentxnidcmd;

import junit.framework.TestCase;

/**
 * <p>Test for {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmdSupport}
 * </p>
 * 
 * @author Paul R Evans
 *
 */
public abstract class GenTxnIDCmdAbstractTest
extends TestCase {

	/**
	 * Object that we'll use to test with
	 */
	private GenTxnIDCmd generateTxnIdentifierCmd;
	
	/**
	 * <p>Sub-classes to return concrete 'generate txn identifier' command
	 * object.</p>
	 * 
	 * @return concrete 'generate txn identifer' command object
	 */
	public abstract GenTxnIDCmd getCommand();
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// create our object-under-test...
		generateTxnIdentifierCmd = getCommand();
	}

	/**
	 * <p>Test for both {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd#setUseCase(String)}
	 * and {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd#getUseCase()}
	 * </p>
	 */
	public final void testGet_and_SetUseCase() {
		
		// sanity check...assert the object we're using to test with was
		// in fact created in the test setup...
		assertNotNull(generateTxnIdentifierCmd);
		
		// assert that getter currently returns null...
		assertNull(generateTxnIdentifierCmd.getUseCase());
		
		// set some non-null value...
		generateTxnIdentifierCmd.setUseCase("Submit Order");
		
		// assert the getter return the value that we just set...
		assertEquals("Submit Order", generateTxnIdentifierCmd.getUseCase());
		
		// set the use case to the empty string and then assert the getter
		// returns back the empty string (crazy stuff!)...
		generateTxnIdentifierCmd.setUseCase("");
		assertEquals("", generateTxnIdentifierCmd.getUseCase());
		
		// set the use case to null and then assert the getter
		// returns back null...
		generateTxnIdentifierCmd.setUseCase(null);
		assertNull(generateTxnIdentifierCmd.getUseCase());				
	}

	/**
	 * <p>Test for both {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd#setChannel(String)}
	 * and {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd#getChannel()}
	 * </p>
	 */
	public final void testGet_and_SetChannel() {
		
		// sanity check...assert the object we're using to test with was
		// in fact created in the test setup...
		assertNotNull(generateTxnIdentifierCmd);
		
		// assert that getter currently returns null...
		assertNull(generateTxnIdentifierCmd.getChannel());
		
		// set some non-null value...
		generateTxnIdentifierCmd.setChannel("Web");
		
		// assert the getter return the value that we just set...
		assertEquals("Web", generateTxnIdentifierCmd.getChannel());
		
		// set the channel to the empty string and then assert the getter
		// returns back the empty string (wacky stuff!)...
		generateTxnIdentifierCmd.setChannel("");
		assertEquals("", generateTxnIdentifierCmd.getChannel());
		
		// set the channel to null and then assert the getter
		// returns back null...
		generateTxnIdentifierCmd.setChannel(null);
		assertNull(generateTxnIdentifierCmd.getChannel());	
	}
}
