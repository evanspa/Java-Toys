package unittest.castor_configuration;

import name.paulevans.testutils.XMLTestUtils;
import name.paulevans.txnidgenerator.common.TxnIdentifier;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.XMLAssert;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Test to ensure correctness of the Castor mapping configuration file
 * of 'common types' - the mapping file is located at:
 * src/main/config/castor-mapping_commonTypes.xml
 * </p>
 * 
 * @author Paul R Evans
 *
 */
public class TestCastorConfig_commonTypes extends CastorTestCaseSupport {
	
	/**
	 * <p>Loads the Castor mapping configuration</p>
	 */
	public TestCastorConfig_commonTypes() throws Exception {
		
		// configure Castor...
		configureCastorMapping("castor-commonTypes.xml");
	}
	
	/**
	 * <p>Test to ensure a TxnIdentifier object can marshal correctly
	 * into XML</p>
	 */
	@Test public void testMarshalTxnIdentifierIntoXml() throws Exception {
		
		TxnIdentifier txnIdentifier;
		org.xml.sax.InputSource xmlResult;
		String xmlResultAsStr;
		
		// first we'll test with null...
		txnIdentifier = new TxnIdentifier(null);
		
		// marshal into XML...
		xmlResult = marshal(txnIdentifier);
		
		// convert XML result into String for convenience...
		xmlResultAsStr = IOUtils.toString(xmlResult.getByteStream());
		
		// sanity check...
		Assert.assertNotNull(xmlResult);
		
		// make sure the generated XML conforms to the 'types' schema...
		XMLTestUtils.assertValidInstance(xmlResultAsStr, 
				"TransactionIdentifier-types.xsd");
		
		// assert the value of the 'identifierValue' attribute in the XML
		// is empty/blank...
		XMLAssert.assertXpathEvaluatesTo("", 
				"/TxnIDTypes:TransactionIdentifier/@identifierValue", 
				xmlResultAsStr);
		
		// now try with a txn identifier object containing a non-null
		// value...
		txnIdentifier = new TxnIdentifier("ABC123");
		xmlResult = marshal(txnIdentifier);
		xmlResultAsStr = IOUtils.toString(xmlResult.getByteStream());
		XMLTestUtils.assertValidInstance(xmlResultAsStr, 
				"TransactionIdentifier-types.xsd");
		Assert.assertNotNull(xmlResult);
		XMLAssert.assertXpathEvaluatesTo("ABC123", 
				"/TxnIDTypes:TransactionIdentifier/@identifierValue", 
				xmlResultAsStr);
	}
	
	/**
	 * <p>Test to ensure a valid XML instance of a transaction identifier
	 * can be unmarshaled properly into a TransactionIdentifier object</p>
	 */
	@Test public void testUnmarshalXmlIntoTxnIdentifier() {
		
		Object unmarshalResult;
		TxnIdentifier txnIdentifier;
			
		// perform the unmarshal...
		unmarshalResult = unmarshalFromClasspathResource(
			"txnidentifier-valid-1.xml");
			
		// assert the type is correct
		Assert.assertTrue(unmarshalResult instanceof TxnIdentifier);
			
		// cast-down for convenience...
		txnIdentifier = (TxnIdentifier)unmarshalResult;
			
		// assert the expected value of the identifier value...
		Assert.assertEquals("PW0209328123ASDFA102341", txnIdentifier.
			getIdentifierValue());
	}
}
