package name.paulevans.sampleprojects.command.server.servercmds;

import name.paulevans.sampleprojects.command.CommandSupport;
import name.paulevans.sampleprojects.command.server.productpriceresolver.ProductPriceResolver;
import name.paulevans.sampleprojects.command.server.productpriceresolver.impl.InMemoryProductPriceResolver;
import name.paulevans.sampleprojects.model.LineItem;

/**
 * <p>Goofy command class for the sake of having a second command class</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class WhatAGreatPriceCommand extends CommandSupport {
	
	/**
	 * Product price resolver
	 */
	private ProductPriceResolver productPriceResolver;
	
	/**
	 * Default constructor
	 */
	public WhatAGreatPriceCommand() {
		
		/*
		 * <p>okay - so I don't like this because of the tight coupling to the
		 * InMemoryProductPriceResolver implementation class, but, I'm not
		 * sure how to rectify this because this class is instantiated at runtime
		 * by Castor; and because Castor 1.3 is not built around CDI, I think
		 * I'm stuck...</p>
		 * 
		 * <p>I'm sure there's a better design, but, I have to stop putting
		 * engineering effort into this sample at some point :) </p>
		 * 
		 * 
		 */
		productPriceResolver = new InMemoryProductPriceResolver();
	}
	
	@Override
	public Object execute() {

		LineItem lineItems[] = getOrder().getLineItems();
		
		// set the text: "What a Great Price!" to the description of
		// each product...
		for (LineItem item : lineItems) {
			
			// get and set the price of each product...
			item.getProduct().setRetailPrice(productPriceResolver.
					getRetailPrice(item.getProduct()));
			
			// set the description text...
			item.getProduct().setDescription("What a Great Price!");
		}
		
		// return the order...
		return getOrder();
	}
}
