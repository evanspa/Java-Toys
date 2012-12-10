package name.paulevans.sampleprojects.clientapplication;

import java.math.BigDecimal;

import name.paulevans.sampleprojects.command.CommandExecutor;
import name.paulevans.sampleprojects.command.client.clientcmds.CalculateOrderSubTotalCommand;
import name.paulevans.sampleprojects.model.LineItem;
import name.paulevans.sampleprojects.model.Order;
import name.paulevans.sampleprojects.model.Product;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
	 * Name of the Spring definitions file.
	 */
	private static final String SPRING_APP_CONTEXT_FILE = "spring.xml";

	/**
	 * Spring bean factory object.
	 */
	private static final BeanFactory beanFactory = new 
	ClassPathXmlApplicationContext(new String[] {SPRING_APP_CONTEXT_FILE});

	/**
	 * <p>Returns the configured bean factory</p>
	 * 
	 * @return returns the configured bean factory
	 */
	public static final BeanFactory getBeanFactory() {
		return beanFactory;
	}

	/**
	 * <p>Program entry point</p>
	 * 
	 * @param pArgs command-line arguments
	 */
	public static void main(String pArgs[]) {

		// run the various scenarios...
		Scenario_BlankProductName();
		Scenario_SpoofedProductPrice();
		Scenario_InvalidProductName();
		Scenario_BlankProductName();
		Scenario_ValidOrder();
	}	

	/**
	 * <p>Scenario in which the client sends along a valid order.</p>
	 * 
	 * @author Paul R Evans
	 * @version $Id$
	 */
	private static final void Scenario_ValidOrder() {

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
		calcSubTotalCommand = (CalculateOrderSubTotalCommand)
		ClientApplication.getBeanFactory().getBean(
		"calculateSubTotalCommand");

		// set the order...
		calcSubTotalCommand.setOrder(order);

		// execute the command...
		order = (Order)new CommandExecutor().executeCommand(calcSubTotalCommand);

		// output the resulting sub-total...
		System.out.println("(+) Server-side-calculated sub-total of valid order" +
				": [$" + order.getSubTotal() + "]\n");
	}

	/**
	 * <p>Scenario in which a blank product name is supplied</p>
	 */
	private static final void Scenario_BlankProductName() {

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
			calcSubTotalCommand = (CalculateOrderSubTotalCommand)
			ClientApplication.getBeanFactory().getBean(
			"calculateSubTotalCommand");

			// set the order...
			calcSubTotalCommand.setOrder(order);

			// execute the command...		
			order = (Order)new CommandExecutor().executeCommand(calcSubTotalCommand);
		} catch (RuntimeException any) {
			System.out.println("(+) Message when attempting to use a " +
					"blank product name: [" + any.getMessage() + "]\n");
		}
	}

	/**
	 * <p>Scenario in which an invalid product name is supplied</p>
	 */
	private static final void Scenario_InvalidProductName() {

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
			calcSubTotalCommand = (CalculateOrderSubTotalCommand)
			ClientApplication.getBeanFactory().getBean(
			"calculateSubTotalCommand");

			// set the order...
			calcSubTotalCommand.setOrder(order);

			// execute the command...		
			order = (Order)new CommandExecutor().executeCommand(calcSubTotalCommand);
		} catch (RuntimeException any) {
			System.out.println("(+) Message when attempting to use an " +
					"invalid product name: [" + any.getMessage() + "]\n");
		}
	}

	/**
	 * <p>Scenario in which the client attempts to spoof the retail price of a 
	 * product.</p>
	 */
	private static final void Scenario_SpoofedProductPrice() {

		CalculateOrderSubTotalCommand calcSubTotalCommand;

		// build up the order object...
		Product p1 = new Product(new BigDecimal("20.95"), "Footjoy Dry Joys");
		LineItem l1 = new LineItem();
		l1.setProduct(p1);
		l1.setQuantity(2);
		Order order = new Order();
		order.addLineItem(l1);

		// create client command object...
		calcSubTotalCommand = (CalculateOrderSubTotalCommand)
		ClientApplication.getBeanFactory().getBean(
		"calculateSubTotalCommand");

		// set the order...
		calcSubTotalCommand.setOrder(order);

		// execute the command...
		order = (Order)new CommandExecutor().executeCommand(calcSubTotalCommand);

		// output the resulting sub-total...
		System.out.println("(+) Server-side-calculated sub-total of order " +
				"containing spoofed product price: [$" + order.getSubTotal() + 
				"] - (spoofed price was: $" + new BigDecimal(
				"20.95").multiply(new BigDecimal("2")) + ")\n");
	}
}
