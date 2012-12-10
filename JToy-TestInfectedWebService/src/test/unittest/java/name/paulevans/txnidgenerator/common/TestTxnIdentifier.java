package name.paulevans.txnidgenerator.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Test for {@link name.paulevans.txnidgenerator.common.TxnIdentifier}</p>
 * 
 * @author Paul R Evans
 *
 */
public class TestTxnIdentifier {

	@Test
	public final void testTxnIdentifier() {
		
		TxnIdentifier txnIdentifier;
		
		// instantiate using default constructor and assert the transaction
		// identifier value returned by the getter returns null...
		txnIdentifier = new TxnIdentifier();
		Assert.assertNull(txnIdentifier.getIdentifierValue());
		
		// instantiate using single-arg constructor; pass-in null and
		// assert getter returns null...
		txnIdentifier = new TxnIdentifier(null);
		Assert.assertNull(txnIdentifier.getIdentifierValue());
		
		// try with the empty string...
		txnIdentifier = new TxnIdentifier("");
		Assert.assertEquals("", txnIdentifier.getIdentifierValue());
		
		// try with a non-empty string...
		txnIdentifier = new TxnIdentifier("ABC123");
		Assert.assertEquals("ABC123", txnIdentifier.getIdentifierValue());
	}
}
