package unittest.castor_configuration;
import name.paulevans.testutils.XMLTestUtils;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.XMLAssert;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.InputSource;


/**
 * <p>Test to ensure correctness of the Castor mapping configuration file:
 * src/main/config/castor/commands/gentxnidcmd/castor-GenTxnIDCmd.xml</p>
 * 
 * @author Paul R Evans
 *
 */
public abstract class CastorConfig_GenTxnIDCmd extends CastorTestCaseSupport {
	
	/**
	 * Default constructor
	 */
	public CastorConfig_GenTxnIDCmd() {
		
		// configure the Castor runtime with our mapping configuration
		// files...
		configureCastorMapping(
				"castor-commonTypes.xml",
				"castor-GenTxnIDCmd.xml", 		 // abstract cmd...
				"castor-GenTxnIDClientCmd.xml",  // client cmd...
				"castor-GenTxnIDServerCmd.xml"); // server cmd...		
	}
	
	/**
	 * <p>Creates a new command instance</p>
	 * 
	 * @return a new command instance
	 */
	public abstract GenTxnIDCmd newCommand();
	
	/**
	 * <p>Returns unmarshaling result; the result object should be a
	 * GenTxnIDCmd instance with the following state:
	 *   <ul>
	 *     <li>Use case: Search Products</li>
	 *     <li>Channel: Web</li>
	 *   </ul>
	 * </p>
	 * 
	 * @return GenTxnIDCmd instance
	 */
	public abstract Object unmarshalCmdFromClasspathResource();
	
	/**
	 * <p>Test to ensure the 'castor-GenTxnIDCmd.xml' file is configured
	 * properly for marshaling</p>
	 */
	@Test public final void testGenericCmdMarshalConfig() throws Exception {
		
		GenTxnIDCmd command;
		org.xml.sax.InputSource xmlResult;
		String xmlResultAsStr;
		
		// create an empty command instance and do sanity-check assert...
		command = newCommand();
		Assert.assertNotNull(command);
		
		// marshal our empty command into XML, and do some asserts on the
		// returned XML...
		xmlResult = marshal(command);
		
		// convert result to a string so we can assert against it multiple
		// times (keeping it as a stream is cumbersome because after the first
		// read, you get EOF issues on the stream because the cursor is at
		// the end of the stream)...
		xmlResultAsStr = IOUtils.toString(xmlResult.getByteStream());
		
		// make sure the generated XML conforms to the 'commands' schema...
		XMLTestUtils.assertValidInstance(xmlResultAsStr, 
				"TransactionIdentifier-commands.xsd");
		
		// assert that both the <usecase> and <channel> elements are empty...
		XMLAssert.assertXpathEvaluatesTo("", 
			"/TxnIdGenCmds:TransactionIdGeneratorCommand/TxnIdGenCmds:usecase", 
			new InputSource(IOUtils.toInputStream(xmlResultAsStr)));
		XMLAssert.assertXpathEvaluatesTo("", 
			"/TxnIdGenCmds:TransactionIdGeneratorCommand/TxnIdGenCmds:channel", 
			new InputSource(IOUtils.toInputStream(xmlResultAsStr)));
		
		// set the use case to something, and assert it appears in the XML
		// after marshaling...
		command.setUseCase("Order Checkout");
		xmlResultAsStr = IOUtils.toString(marshal(command).getByteStream());
		XMLAssert.assertXpathEvaluatesTo("Order Checkout", 
				"/TxnIdGenCmds:TransactionIdGeneratorCommand/TxnIdGenCmds:usecase", 
				new InputSource(IOUtils.toInputStream(xmlResultAsStr)));
		
		// set the channel to something, and assert it appears in the XML
		// after marshaling...
		command.setChannel("Call Center");
		xmlResultAsStr = IOUtils.toString(marshal(command).getByteStream());
		XMLAssert.assertXpathEvaluatesTo("Call Center", 
				"/TxnIdGenCmds:TransactionIdGeneratorCommand/TxnIdGenCmds:channel", 
				new InputSource(IOUtils.toInputStream(xmlResultAsStr)));
	}
	
	/**
	 * <p>Test to ensure the 'castor-GenTxnIDCmd.xml' file is configured
	 * properly for unmarshaling</p>
	 */
	@Test public void testGenericCmdUnmarshalConfig() throws Exception {
		
		Object unmarshalResult;
		GenTxnIDCmd txnIDCmd;
			
		// perform the unmarshal...
		unmarshalResult = unmarshalCmdFromClasspathResource();
			
		// assert the type is correct
		Assert.assertTrue(unmarshalResult instanceof GenTxnIDCmd);
			
		// cast-down for convenience...
		txnIDCmd = (GenTxnIDCmd)unmarshalResult;
			
		// assert the expected value of the use case value...
		Assert.assertEquals("Search Products", txnIDCmd.getUseCase());
		
		// assert the expected value of the channel value...
		Assert.assertEquals("Web", txnIDCmd.getChannel());
	}
}
