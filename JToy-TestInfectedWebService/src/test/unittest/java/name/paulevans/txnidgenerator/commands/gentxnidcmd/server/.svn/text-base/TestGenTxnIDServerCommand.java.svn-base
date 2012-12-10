package name.paulevans.txnidgenerator.commands.gentxnidcmd.server;

import name.paulevans.distcmdfrmwk.CommandProcessingException;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmdAbstractTest;
import name.paulevans.txnidgenerator.common.TxnIdentifier;

/**
 * <p>Test for {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.server.GenTxnIDServerCommand}
 * </p>
 * 
 * <p>The reality is that this class really only exists to override 
 * {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmdAbstractTest#getCommand()}
 * - the test definitions live in this class's parent.</p>
 * 
 * @author Paul R Evans
 *
 */
public class TestGenTxnIDServerCommand extends 
GenTxnIDCmdAbstractTest {
	
	/**
	 * Object that we'll use to test with
	 */
	private GenTxnIDServerCommand genTxnIdentifierCmd;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// create our object-under-test...
		genTxnIdentifierCmd = (GenTxnIDServerCommand)getCommand();
	}
	
	@Override
	public GenTxnIDCmd getCommand() {
		return new GenTxnIDServerCommand();
	}
	
	/**
	 * <p>Test for {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd#execute()}
	 * </p>
	 * 
	 * <p>This specifically tests the scenario in which a valid use case is
	 * set and a valid channel is set as well.</p>
	 */
	public final void testExecute_validUseCase_and_validChannel() {
		
		TxnIdentifier txnIdentifier;
		
		// first a sanity check...
		assertNotNull(genTxnIdentifierCmd);
		
		// set the use case and channel properties...
		genTxnIdentifierCmd.setUseCase("Place Order");
		genTxnIdentifierCmd.setChannel("Web");
		
		// execute the command...
		txnIdentifier = (TxnIdentifier)genTxnIdentifierCmd.execute();
		
		// only the first 2 characters can be verified, as the remaining
		// characters are random...
		assertTrue(txnIdentifier.getIdentifierValue().startsWith("PW"));
	}

	/**
	 * <p>Test for {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd#execute()}
	 * </p>
	 * 
	 * <p>This specifically tests the scenario in which an invalid use case is
	 * set, and valid channel is specified.</p>
	 */
	public final void testExecute_invalidUseCase_and_validChannel() {
		
		// first a sanity check...
		assertNotNull(genTxnIdentifierCmd);
		
		// set the use case and channel properties...
		genTxnIdentifierCmd.setUseCase("Shoplift"); // invalid value
		genTxnIdentifierCmd.setChannel("Web");
		
		// attempt to execute the command...
		try {
			genTxnIdentifierCmd.execute();
			fail("should not have reached this point");
		} catch (CommandProcessingException pExc) {
			assertEquals("invalid use case: [Shoplift]", pExc.getMessage());
		}
				
		// set the use case to the empty string and re-assert...
		genTxnIdentifierCmd.setUseCase("");
		try {
			genTxnIdentifierCmd.execute();
			fail("should not have reached this point");
		} catch (CommandProcessingException pExc) {
			assertEquals("invalid use case: []", pExc.getMessage());
		}
		
		// set the use case to some whitespace and re-assert...
		genTxnIdentifierCmd.setUseCase("    ");
		try {
			genTxnIdentifierCmd.execute();
			fail("should not have reached this point");
		} catch (CommandProcessingException pExc) {
			assertEquals("invalid use case: [    ]", pExc.getMessage());
		}		
	}
	
	/**
	 * <p>Test for {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd#execute()}
	 * </p>
	 * 
	 * <p>This specifically tests the scenario in which a valid use case is
	 * set, but an invalid channel is specified.</p>
	 */
	public final void testExecute_validUseCase_and_invalidChannel() {
		
		// first a sanity check...
		assertNotNull(genTxnIdentifierCmd);
		
		// set the use case and channel properties...
		genTxnIdentifierCmd.setUseCase("Load Catalog");
		genTxnIdentifierCmd.setChannel("Mail"); // invalid value
		
		// attempt to execute the command...
		try {
			genTxnIdentifierCmd.execute();
			fail("should not have reached this point");
		} catch (CommandProcessingException pExc) {
			assertEquals("invalid channel: [Mail]", pExc.getMessage());
		}
				
		// set the channel to the empty string and re-assert...
		genTxnIdentifierCmd.setChannel("");
		try {
			genTxnIdentifierCmd.execute();
			fail("should not have reached this point");
		} catch (CommandProcessingException pExc) {
			assertEquals("invalid channel: []", pExc.getMessage());
		}
		
		// set the channel to some whitespace and re-assert...
		genTxnIdentifierCmd.setChannel("    ");
		try {
			genTxnIdentifierCmd.execute();
			fail("should not have reached this point");
		} catch (CommandProcessingException pExc) {
			assertEquals("invalid channel: [    ]", pExc.getMessage());
		}		
	}
	
	/**
	 * <p>Test for {@link name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd#execute()}
	 * </p>
	 * 
	 * <p>This specifically tests the scenario in which both an invalid use case is
	 * set and invalid channels are set.</p>
	 */
	public final void testExecute_invalidUseCase_and_invalidChannel() {
		
		// first a sanity check...
		assertNotNull(genTxnIdentifierCmd);
		
		// set the use case and channel properties...
		genTxnIdentifierCmd.setUseCase("Complain to Manager");
		genTxnIdentifierCmd.setChannel("ESP");
		
		// execute the command...
		try {
			genTxnIdentifierCmd.execute();
			fail("should not have reached this point");
		} catch (CommandProcessingException pExc) {
			assertEquals("invalid use case: [Complain to Manager] and " +
					"invalid channel: [ESP]", pExc.getMessage());
		}
		
		// set the channel to the empty string and re-assert...
		genTxnIdentifierCmd.setChannel("");
		try {
			genTxnIdentifierCmd.execute();
			fail("should not have reached this point");
		} catch (CommandProcessingException pExc) {
			assertEquals("invalid use case: [Complain to Manager] and " +
					"invalid channel: []", pExc.getMessage());
		}
		
		// set the channel to some whitespace and re-assert...
		genTxnIdentifierCmd.setChannel("    ");
		try {
			genTxnIdentifierCmd.execute();
			fail("should not have reached this point");
		} catch (CommandProcessingException pExc) {
			assertEquals("invalid use case: [Complain to Manager] and " +
					"invalid channel: [    ]", pExc.getMessage());
		}
		
		// set the use case to the empty string and re-assert...
		genTxnIdentifierCmd.setUseCase("");
		try {
			genTxnIdentifierCmd.execute();
			fail("should not have reached this point");
		} catch (CommandProcessingException pExc) {
			assertEquals("invalid use case: [] and " +
					"invalid channel: [    ]", pExc.getMessage());
		}
		
		// set the use case to some whitespace and re-assert...
		genTxnIdentifierCmd.setUseCase("    ");
		try {
			genTxnIdentifierCmd.execute();
			fail("should not have reached this point");
		} catch (CommandProcessingException pExc) {
			assertEquals("invalid use case: [    ] and " +
					"invalid channel: [    ]", pExc.getMessage());
		}
	}
}
