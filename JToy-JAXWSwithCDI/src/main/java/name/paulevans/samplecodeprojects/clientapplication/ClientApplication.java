package name.paulevans.samplecodeprojects.clientapplication;

import java.math.BigDecimal;

import name.paulevans.samplecodeprojects.command.client.ClientCommandFactory;
import name.paulevans.samplecodeprojects.command.client.clientcmds.CalculateOrderSubTotalCommand;
import name.paulevans.samplecodeprojects.model.LineItem;
import name.paulevans.samplecodeprojects.model.Order;
import name.paulevans.samplecodeprojects.model.Product;

/**
 * <p>Application to demonstrate how a client application would invoke
 * our command-centric web service.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class ClientApplication {
	
	/**
	 * <p>Program entry point</p>
	 * 
	 * @param pArgs command-line arguments
	 */
	public static void main(String pArgs[]) {
				
		// he he - just trying to make it interesting :)
		new Thread(new Scenario_SpoofedProductPrice()).start();
		new Thread(new Scenario_InvalidProductName()).start();
		new Thread(new Scenario_BlankProductName()).start();
		new Thread(new Scenario_ValidOrder()).start();
	}	
}

/**
 * <p>Scenario in which the client sends along a valid order.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 */
class Scenario_ValidOrder implements Runnable {

	@Override
	public void run() {
		
		CalculateOrderSubTotalCommand calcSubTotalCommand;
		
		// build up the order object...
		Product p1 = new Product();
		p1.setName("Titleist Pro V1");
		Product p2 = new Product();
		p2.setName("Calaway Great Big Bertha");
		Product p3 = new Product();
		p3.setName("Footjoy Dry Joys");
		LineItem l1 = new LineItem();
		l1.setProduct(p1);
		l1.setQuantity(4);
		LineItem l2 = new LineItem();
		l2.setProduct(p2);
		l2.setQuantity(1);
		LineItem l3 = new LineItem();
		l3.setProduct(p3);
		l3.setQuantity(2);
		Order order = new Order();
		order.addLineItem(l1);
		order.addLineItem(l2);
		order.addLineItem(l3);
		
		// create client command object...
		calcSubTotalCommand = ClientCommandFactory.
			newCalculateOrderSubTotalCommand();
		
		// set the order...
		calcSubTotalCommand.setOrder(order);
		
		// execute the command...
		order = (Order)calcSubTotalCommand.execute();
		
		// output the resulting sub-total...
		System.out.println("(+) Server-side-calculated sub-total of valid order" +
				": [$" + order.getSubTotal() + "]\n");
	}
}

/**
 * <p>Scenario in which a blank product name is supplied</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 */
class Scenario_BlankProductName implements Runnable {

	@Override
	public void run() {
		
		CalculateOrderSubTotalCommand calcSubTotalCommand;
		
		try {
			// build up the order object - in fact, the product constructor
			// should throw an exception because blank product names are not
			// allowed.  So in this use case, we never even make it to attempting
			// to invoke the web service...
			Product p1 = new Product(new BigDecimal("1.95"), " ");
			LineItem l1 = new LineItem();
			l1.setProduct(p1);
			l1.setQuantity(2);
			Order order = new Order();
			order.addLineItem(l1);

			// create client command object...
			calcSubTotalCommand = ClientCommandFactory.
				newCalculateOrderSubTotalCommand();

			// set the order...
			calcSubTotalCommand.setOrder(order);
		
			// execute the command...		
			order = (Order)calcSubTotalCommand.execute();
		} catch (RuntimeException any) {
			System.out.println("(+) Message when attempting to use a " +
					"blank product name: [" + any.getMessage() + "]\n");
		}
	}
}

/**
 * <p>Scenario in which an invalid product name is supplied</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 */
class Scenario_InvalidProductName implements Runnable {

	@Override
	public void run() {
		
		CalculateOrderSubTotalCommand calcSubTotalCommand;
		
		try {
			// build up the order object...
			Product p1 = new Product(new BigDecimal("1.95"), "Gum");
			LineItem l1 = new LineItem();
			l1.setProduct(p1);
			l1.setQuantity(2);
			Order order = new Order();
			order.addLineItem(l1);

			// create client command object...
			calcSubTotalCommand = ClientCommandFactory.
				newCalculateOrderSubTotalCommand();

			// set the order...
			calcSubTotalCommand.setOrder(order);
		
			// execute the command...		
			order = (Order)calcSubTotalCommand.execute();
		} catch (RuntimeException any) {
			System.out.println("(+) Message when attempting to use an " +
					"invalid product name: [" + any.getMessage() + "]\n");
		}
	}
}

/**
 * <p>Scenario in which the client attempts to spoof the retail price of a 
 * product.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 */
class Scenario_SpoofedProductPrice implements Runnable {

	@Override
	public void run() {
		
		CalculateOrderSubTotalCommand calcSubTotalCommand;
		
		// build up the order object...
		Product p1 = new Product(new BigDecimal("20.95"), "Footjoy Dry Joys");
		LineItem l1 = new LineItem();
		l1.setProduct(p1);
		l1.setQuantity(2);
		Order order = new Order();
		order.addLineItem(l1);
		
		// create client command object...
		calcSubTotalCommand = ClientCommandFactory.
			newCalculateOrderSubTotalCommand();
		
		// set the order...
		calcSubTotalCommand.setOrder(order);
		
		// execute the command...
		order = (Order)calcSubTotalCommand.execute();
		
		// output the resulting sub-total...
		System.out.println("(+) Server-side-calculated sub-total of order " +
				"containing spoofed product price: [$" + order.getSubTotal() + 
				"] - (spoofed price was: $" + new BigDecimal(
						"20.95").multiply(new BigDecimal("2")) + ")\n");
	}
}
