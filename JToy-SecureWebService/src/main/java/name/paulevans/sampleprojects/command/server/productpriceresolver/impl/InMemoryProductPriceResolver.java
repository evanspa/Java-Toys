package name.paulevans.sampleprojects.command.server.productpriceresolver.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import name.paulevans.sampleprojects.command.server.productpriceresolver.ProductPriceResolver;
import name.paulevans.sampleprojects.model.Product;

/**
 * <p>Basic implementation of {@link name.paulevans.sampleprojects.command.server.productpriceresolver.ProductPriceResolver}
 * interface - price amounts are simply hard-coded and held in memory.</p>.
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class InMemoryProductPriceResolver implements ProductPriceResolver {
	
	/**
	 * Holds product prices
	 */
	private static final Map<String,BigDecimal> PRODUCT_PRICES = 
		new HashMap<String,BigDecimal>();
	
	// populate the product prices map...
	static {		
		PRODUCT_PRICES.put("Titleist Pro V1", new BigDecimal("46.99"));
		PRODUCT_PRICES.put("Footjoy Dry Joys", new BigDecimal("129.99"));
		PRODUCT_PRICES.put("Calaway Great Big Bertha", new BigDecimal("229.99"));
	}

	@Override
	public BigDecimal getRetailPrice(Product pProduct) {
		return PRODUCT_PRICES.get(pProduct.getName());
	}
}
