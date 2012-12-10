package name.paulevans.samplecodeprojects.marshaler.xml;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import name.paulevans.samplecodeprojects.command.Command;
import name.paulevans.samplecodeprojects.command.CommandSupport;
import name.paulevans.samplecodeprojects.model.LineItem;
import name.paulevans.samplecodeprojects.model.Order;
import name.paulevans.samplecodeprojects.model.Product;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.NamespaceContext;
import org.custommonkey.xmlunit.SimpleNamespaceContext;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.InputSource;

/**
 * <p>Test case for {@link name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler}</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public abstract class XmlMarshalerTestCase extends XMLTestCase {
	
	/**
	 * XML marshaler instance to test with
	 */
	private XmlMarshaler xmlMarshaler;
	
	/*
	 * Set global configuration for XMLUnit runtime
	 */
	static {
		Map<String,String> namespaces = new HashMap<String,String>();
		namespaces.put("OSTypes", "http://name.paulevans/samples/OrderSystem-types");
		namespaces.put("OSCommands", "http://name.paulevans/samples/OrderSystem-commands");
		NamespaceContext nsCtx = new SimpleNamespaceContext(namespaces);
		XMLUnit.setXpathNamespaceContext(nsCtx);
	}

	@Override
	protected void setUp() throws Exception {
		xmlMarshaler = getXmlMarshaler();
	}
	
	/**
	 * <p>Test cases for concrete implementations of {@link name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler}
	 * should extend this class and provide an implementation of this method
	 * to return a new instance of itself.</p>
	 * 
	 * @return
	 */
	public abstract XmlMarshaler getXmlMarshaler();
	
	/**
	 * <p>Test for {@link name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler#marshalCommandResult(Object)} in which
	 * the order object has no line items (an empty order).</p>
	 */
	public final void teestMarshalCommandResult_emptyOrder() throws Exception {
		
		String marshalResult;
		
		// assert that our marshaler instance is not null (sanity check)...
		assertNotNull(xmlMarshaler);
		
		// do the marshal...
		marshalResult = xmlMarshaler.marshalCommandResult(new Order());
		
		// assert that there are no <LineItem> elements within the root
		// <Order> element...
		assertXpathEvaluatesTo("0", "count(/OSTypes:Order/OSTypes:LineItem)", 
				new InputSource(IOUtils.toInputStream(marshalResult)));
	}

	/**
	 * <p>Test for {@link name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler#marshalCommandResult(Object)} in which
	 * the order object has 3 line items with populated products.</p>
	 */
	public final void testMarshalCommandResult_populatedOrder() throws Exception {
		
		String marshalResult;
		
		// create a populated order...
		Product p1 = new Product();
		p1.setName("Titleist Pro V1");
		p1.setRetailPrice(new BigDecimal("46.99"));
		Product p2 = new Product();
		p2.setName("Callaway Great Big Bertha");
		p2.setRetailPrice(new BigDecimal("229.99"));
		Product p3 = new Product();
		p3.setName("Footjoy Dry Joys");
		p3.setRetailPrice(new BigDecimal("179.99"));
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
		
		// assert that our marshaler instance is not null (sanity check)...
		assertNotNull(xmlMarshaler);
		
		// do the marshal...
		marshalResult = xmlMarshaler.marshalCommandResult(order);
		
		// assert that there are 3 <LineItem> elements within the root
		// <Order> element...		
		assertXpathEvaluatesTo("3", "count(/OSTypes:Order/OSTypes:LineItem)", 
				new InputSource(IOUtils.toInputStream(marshalResult)));
		
		// assert the first product name is 'Titleist Pro V1'...
		assertXpathEvaluatesTo("Titleist Pro V1", 
				"/OSTypes:Order/OSTypes:LineItem[1]/OSTypes:Product/OSTypes:Name", 
				new InputSource(IOUtils.toInputStream(marshalResult)));
		
		// assert the quantity of the 3rd line item is 2...
		assertXpathEvaluatesTo("2", 
				"/OSTypes:Order/OSTypes:LineItem[3]/OSTypes:Quantity", 
				new InputSource(IOUtils.toInputStream(marshalResult)));
	}
	
	/**
	 * <p>Test for {@link name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler#unmarshalRequest(javax.xml.transform.stream.StreamSource)}
	 * with a payload that should unmarshal into a {@link name.paulevans.samplecodeprojects.command.server.servercmds.CalculateOrderSubTotalCommand}
	 * command object.</p>
	 * 
	 * <p>Specifically tests that an empty <CalculateOrderSubTotalCommand> can
	 * be unmarshaled properly (i.e. the created command object's {@link name.paulevans.samplecodeprojects.command.server.servercmds.CalculateOrderSubTotalCommand#getOrder()}
	 * method returns null</p>
	 */
	public final void testUnmarshalRequest_CalculateOrderSubTotalCommand_nullOrder() {
		
		Command command;
		CommandSupport calcTotalCmd;
		StringBuilder strBuilder;
		
		// assert that our marshaler instance is not null (sanity check)...
		assertNotNull(xmlMarshaler);
		
		// create sample request XML (an empty command)...
		strBuilder = new StringBuilder("<cmd:CalculateOrderSubTotalCommand ");
		strBuilder.append("xmlns:cmd=\"http://name.paulevans/samples/OrderSystem-commands\">");
		strBuilder.append("</cmd:CalculateOrderSubTotalCommand>");
		
		// unmarshal to a command object...
		command = xmlMarshaler.unmarshalRequest(strBuilder.toString());
		
		// assert the command is of the correct type...
		assertTrue(command instanceof Command);
		
		// for convenience...
		calcTotalCmd = (CommandSupport)command;
		
		// get the order and assert that it is null...
		assertNull(calcTotalCmd.getOrder());
	}
	
	/**
	 * <p>Test for {@link name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler#unmarshalRequest(javax.xml.transform.stream.StreamSource)}
	 * with a payload that should unmarshal into a {@link name.paulevans.samplecodeprojects.command.server.servercmds.CalculateOrderSubTotalCommand}
	 * command object.</p>
	 * 
	 * <p>Specifically tests that a <CalculateOrderSubTotalCommand> with a single, empty <order>
	 * element can be unmarshaled properly (i.e. the created command object's {@link name.paulevans.samplecodeprojects.command.server.servercmds.CalculateOrderSubTotalCommand#getOrder()}
	 * method returns a non-null order that has exactly 0 line items associated
	 * with it</p>
	 */
	public final void testUnmarshalRequest_CalculateOrderSubTotalCommand_emptyOrder() {
		
		Command command;
		CommandSupport calcTotalCmd;
		StringBuilder strBuilder;
		Order order;
		LineItem lineItems[];
		
		// assert that our marshaler instance is not null (sanity check)...
		assertNotNull(xmlMarshaler);
		
		// create sample request XML (a command with an empty Order)...
		strBuilder = new StringBuilder("<cmd:CalculateOrderSubTotalCommand ");
		strBuilder.append("xmlns:cmd=\"http://name.paulevans/samples/OrderSystem-commands\">");
		strBuilder.append("<typ:Order xmlns:typ=\"http://name.paulevans/samples/OrderSystem-types\"></typ:Order>");
		strBuilder.append("</cmd:CalculateOrderSubTotalCommand>");
		
		// unmarshal to a command object...
		command = xmlMarshaler.unmarshalRequest(strBuilder.toString());
		
		// assert the command is of the correct type...
		assertTrue(command instanceof Command);
		
		// for convenience...
		calcTotalCmd = (CommandSupport)command;
		
		// get the order and assert that it is not null...
		order = calcTotalCmd.getOrder();
		assertNotNull(order);
		
		// get the line items and assert that there are exactly 0...
		lineItems = order.getLineItems();
		assertEquals(0, lineItems.length);
	}
	
	/**
	 * <p>Test for {@link name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler#unmarshalRequest(javax.xml.transform.stream.StreamSource)}
	 * with a payload that should unmarshal into a {@link name.paulevans.samplecodeprojects.command.server.servercmds.CalculateOrderSubTotalCommand}
	 * command object.</p>
	 * 
	 * <p>Specifically tests that a <CalculateOrderSubTotalCommand> with a single <order>
	 * element with 1 or more <LineItem> elements can be unmarshaled properly
	 * (i.e. the created command object's {@link name.paulevans.samplecodeprojects.command.server.servercmds.CalculateOrderSubTotalCommand#getOrder()}
	 * method returns a non-null order that has populate line item objects
	 * associated with it.</p>
	 */
	public final void testUnmarshalRequest_CalculateOrderSubTotalCommand_populatedOrder() {
		
		Command command;
		CommandSupport calcTotalCmd;
		StringBuilder strBuilder;
		Order order;
		LineItem lineItems[];
		
		// assert that our marshaler instance is not null (sanity check)...
		assertNotNull(xmlMarshaler);
		
		// create sample request XML (an empty command)...
		strBuilder = new StringBuilder("<cmd:CalculateOrderSubTotalCommand ");
		strBuilder.append("xmlns:cmd=\"http://name.paulevans/samples/OrderSystem-commands\">");
		strBuilder.append("<typ:Order xmlns:typ=\"http://name.paulevans/samples/OrderSystem-types\">");
		strBuilder.append("		<typ:LineItem>");
		strBuilder.append("			<typ:Quantity>3</typ:Quantity>");
		strBuilder.append("		</typ:LineItem>");
		strBuilder.append("</typ:Order>");
		strBuilder.append("</cmd:CalculateOrderSubTotalCommand>");
		
		// unmarshal to a command object...
		command = xmlMarshaler.unmarshalRequest(strBuilder.toString());
		
		// assert the command is of the correct type...
		assertTrue(command instanceof Command);
		
		// for convenience...
		calcTotalCmd = (CommandSupport)command;
		
		// get the order and assert that it is not null...
		order = calcTotalCmd.getOrder();
		assertNotNull(order);
		
		// get the line items and assert that there is exactly 1...
		lineItems = order.getLineItems();
		assertEquals(1, lineItems.length);
		
		// assert the quantity and product name of the line item...
		assertEquals(3, lineItems[0].getQuantity());		
		
	}
	
	/**
	 * Test for {@link name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler#marshalCommand(name.paulevans.samplecodeprojects.command.Command)}
	 */
	public final void testMarshalCommand() {
		
		// assert that our marshaler instance is not null (sanity check)...
		assertNotNull(xmlMarshaler);
	}
	
	public final void testMarshalCommandResult() {
		
	}
	


}
