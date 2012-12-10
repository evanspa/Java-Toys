package unittest.canonical_schemas;

import name.paulevans.testutils.XMLTestUtils;

import org.junit.Test;

/**
 * <p>Test to simply ensure syntactical correctness of our Canonical
 * schema definitions (XSD files):
 *   <ul>
 *     <li>TransactionIdentifier-types.xsd</li>
 *     <li>TransactionIdentifier-commands.xsd</li>
 *   </ul>
 * </p>
 * 
 * @author Paul R Evans
 *
 */
public class TestCanonicalSchemas {
	
	/**
	 * <p>Test to assert the correctness of our TransactionIdentifier-types.xsd
	 * definition.</p>
	 */
	@Test public void testTransactionIdentifierTypes() {
	
		// easy peasy...
		XMLTestUtils.assertValidSchema("TransactionIdentifier-types.xsd");
	}
	
	/**
	 * <p>Test to assert the correctness of our TransactionIdentifier-commands.xsd
	 * definition.  Because the commands schema imports the types schema, both
	 * need to be supplied to the validator.</p>
	 */
	@Test public void testTransactionIdentifierCommands() {
	
		// easy peasy...
		XMLTestUtils.assertValidSchema("TransactionIdentifier-commands.xsd");		
	}
}
