package name.paulevans.sampleprojects.command.server.servercmds;

import java.math.BigDecimal;

import name.paulevans.sampleprojects.command.CommandSupport;
import name.paulevans.sampleprojects.command.server.productpriceresolver.ProductPriceResolver;
import name.paulevans.sampleprojects.command.server.productpriceresolver.impl.InMemoryProductPriceResolver;
import name.paulevans.sampleprojects.model.LineItem;
import name.paulevans.sampleprojects.model.Product;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Calculates the sub-total of the {@link #getOrder()}</p>
 * 
 * <p>Instances of this command class are required to have its 'order'
 * property set (via {@link #setOrder(name.paulevans.sampleprojects.model.Order)})</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class CalculateOrderSubTotalCommand extends CommandSupport {
	
	/**
	 * Product price resolver
	 */
	private ProductPriceResolver productPriceResolver;
	
	/**
	 * Default constructor
	 */
	public CalculateOrderSubTotalCommand() {
		
		/*
		 * <p>okay - so I don't like this because of the tight coupling to the
		 * InMemoryProductPriceResolver implementation class, but, I'm not
		 * sure how to rectify this because this class is instantiated at runtime
		 * by Castor...</p>
		 * 
		 * <p>I'm sure there's a better design, but, I have to stop putting
		 * engineering effort into this sample at some point :) </p>
		 */
		productPriceResolver = new InMemoryProductPriceResolver();
	}
	
	@Override
	public Object execute() {
		
		// total the line items...
		calculateLineItemTotals();
		
		// calculate the order sub-total...
		calculateOrderTotal();
		
		// return the order...
		return getOrder();
	}
	
	/**
	 * <p>Calculates the sub-total of the order as a whole</p>
	 * 
	 * @param pOrder
	 */
	private final void calculateOrderTotal() {
		
		BigDecimal orderTotal;
		LineItem items[];
		
		orderTotal = new BigDecimal("0");
		items = getOrder().getLineItems();
		for (LineItem item : items) {
			orderTotal = orderTotal.add(item.getCost());
		}
		getOrder().setSubTotal(orderTotal);
	}
	
	/**
	 * <p>Computes the cost of each line item in the specified order</p>
	 * 
	 * @param pOrder
	 */
	private final void calculateLineItemTotals() {
		
		// the cost value to return...
		BigDecimal lineItemCost;
		Product product;
		String productName;
		BigDecimal productRetailPrice;
		int lineItemQuantity;
		LineItem lineItems[];
		
		lineItems = getOrder().getLineItems();
		
		for (LineItem lineItem : lineItems) {
			
			product = lineItem.getProduct();
			lineItemQuantity = lineItem.getQuantity();
		
			if (product == null) {

				// return 0 if the product associated with this line items null...
				lineItemCost = new BigDecimal("0.00");
			} else if (lineItemQuantity == 0) {

				// return 0 if quantity is 0 or less...
				lineItemCost = new BigDecimal("0.00");
			} else {
				
				// get the product name and make sure it is valid...
				productName = product.getName();
				if (StringUtils.isBlank(productName)) {
					throw new RuntimeException("product name cannot be blank");
				} else {
					productRetailPrice = productPriceResolver.getRetailPrice(
							product);
					if (productRetailPrice == null) {
						throw new RuntimeException("product: {" + productName + 
								"} not found in product catalog");
					} else {
						
						// okay, so we have a valid product...do the line item
						// totaling...
						product.setRetailPrice(productRetailPrice);
						lineItemCost = productRetailPrice.multiply(BigDecimal.
								valueOf(lineItemQuantity));
						
						// set the line item cost on the item...
						lineItem.setCost(lineItemCost);
					}
				}				
			} 							
		}
	}
	
}
