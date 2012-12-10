package name.paulevans.samplecodeprojects.model;

import java.math.BigDecimal;

import name.paulevans.sampleprojects.model.Product;

import junit.framework.TestCase;

/**
 * <p>Test case for {@link name.paulevans.sampleprojects.model.Product}</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class TestProduct extends TestCase {
	
	/**
	 * Product instance to use in test methods
	 */
	private Product product;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// create the product instance...
		product = new Product();
	}

	/**
	 * <p>Test for {@link name.paulevans.sampleprojects.model.Product#Product(java.math.BigDecimal, String)}</p>
	 */
	public final void testProductBigDecimalString() {
		
		// create the product...
		product = new Product(new BigDecimal("79.99"), "Acme Dynamite");
		
		// dumb sanity check...
		assertNotNull(product);
		
		// assert that the retail price and product name are initialized
		// to the expected values...
		assertEquals(new BigDecimal("79.99"), product.getRetailPrice());
		assertEquals("Acme Dynamite", product.getName());
		
		// try the constructor again, this time passing in nulls...
		try {
			new Product(new BigDecimal("82.95"), null);
			fail("null product name should not be allowed");
		} catch (IllegalArgumentException e) {}
		
		// more with nulls...
		try {
			new Product(null, "Acme Slingshot");
			fail("null product retail price should not be allowed");
		} catch (IllegalArgumentException e) {}
		
		// yes, more with nulls...
		try {
			new Product(null, null);
			fail("null product retail price and name should not be allowed");
		} catch (IllegalArgumentException e) {}
	}
	
	/**
	 * <p>Test for {@link name.paulevans.sampleprojects.model.Product#Product()}</p>
	 */
	public final void testProduct() {
		
		// create the product...
		product = new Product();
		
		// really dumb sanity check...
		assertNotNull(product);
		
		// assert the properties of the product are initialized properly (all
		// to null)...
		assertNull(product.getName());
		assertNull(product.getDescription());
		assertNull(product.getRetailPrice());
	}

	/**
	 * <p>Test for {@link name.paulevans.sampleprojects.model.Product#Product(Product)}</p>
	 */
	public final void testProductProduct() {
		
		// try with a null as input and assert that the product is initialized
		// properly (all to null)...
		product = new Product(null);
		assertNull(product.getName());
		assertNull(product.getDescription());
		assertNull(product.getRetailPrice());
		
		// now try to actually make a copy of a product and assert the fields
		// on the copy match what it is supposed to be a copy for...
		product = new Product(new BigDecimal("7.99"), "Acme Pogostick");
		product.setDescription("goofy pogostick");
		Product copy = new Product(product);
		assertEquals(new BigDecimal("7.99"), copy.getRetailPrice());
		assertEquals("Acme Pogostick", copy.getName());
		assertEquals("goofy pogostick", copy.getDescription());
	}

	/**
	 * <p>Test for both {@link name.paulevans.sampleprojects.model.Product#setDescription(String)}
	 * and {@link name.paulevans.sampleprojects.model.Product#getDescription()}</p>.
	 */
	public final void testGetSetDescription() {
		
		// assert getter currently returns null...
		assertNull(product.getDescription());
		
		// set some value and assert the getter returns it...
		product.setDescription("some description");
		assertEquals("some description", product.getDescription());
		
		// set null and assert the getter returns null...
		product.setDescription(null);
		assertNull(product.getDescription());
	}

	/**
	 * <p>Test for both {@link name.paulevans.sampleprojects.model.Product#setRetailPrice(BigDecimal)}
	 * and {@link name.paulevans.sampleprojects.model.Product#getRetailPrice()}</p>
	 */
	public final void testGetSetRetailPrice() {
		
		// assert getter currently returns null...
		assertNull(product.getRetailPrice());
		
		// set some value and assert the getter returns it...
		product.setRetailPrice(new BigDecimal("19.99"));
		assertEquals(new BigDecimal("19.99"), product.getRetailPrice());
		
		// attempt to set to null and verify that IllegaArgumentException
		// is thrown...
		try {
			product.setRetailPrice(null);
			fail("should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException exc) {
			// expected...
		}
	}

	/**
	 * <p>Test for both {@link name.paulevans.sampleprojects.model.Product#setName(String)}
	 * and {@link name.paulevans.sampleprojects.model.Product#getName()}</p>.
	 */
	public final void testGetSetName() {
		
		// assert getter currently returns null...
		assertNull(product.getName());
		
		// set some value and assert the getter returns it...
		product.setName("some name");
		assertEquals("some name", product.getName());
		
		// attempt to set to null and verify that IllegaArgumentException
		// is thrown...
		try {
			product.setName(null);
			fail("should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException exc) {
			// expected...
		}
	}
}
