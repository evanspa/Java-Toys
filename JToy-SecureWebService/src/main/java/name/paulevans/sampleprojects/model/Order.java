package name.paulevans.sampleprojects.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Models an order<p>
 * 
 * @author Paul E Evans
 * @version $Id$
 *
 */
public class Order {
	
	/**
	 * The sub-total value of this order
	 */
	private BigDecimal subTotal;
	
	/**
	 * Collection of line items
	 */
	private List<LineItem> lineItems = new ArrayList<LineItem>();
	
	/**
	 * <p>Returns the line items object associated with this order</p>
	 * 
	 * @return the line items
	 */
	public final LineItem[] getLineItems() {
		return lineItems.toArray(new LineItem[lineItems.size()]);
	}
	
	/**
	 * <p>Adds a new line item to this order</p>
	 * 
	 * @param pItem
	 */
	public final void addLineItem(LineItem pItem) {
		lineItems.add(pItem);
	}
	
	/**
	 * <p>Sets the sub-total for this order</p>
	 * @param pSubTotal
	 */
	public final void setSubTotal(BigDecimal pSubTotal) {
		subTotal = pSubTotal;
	}

	/**
	 * <p>Returns the sub-total</p>
	 * 
	 * @return the sub-total
	 */
	public final BigDecimal getSubTotal() {
		return subTotal;
	}
}