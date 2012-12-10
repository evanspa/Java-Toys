package name.paulevans.sampleprojects.model;

import java.math.BigDecimal;

/**
 * <p>Models an order line item</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class LineItem {
	
	/**
	 * The quantity associated with this line item
	 */
	private int quantity;
	
	/**
	 * The product associated with this line item
	 */
	private Product product;
	
	/**
	 * The cost of this line item
	 */
	private BigDecimal cost;
	
	/**
	 * <p>Creates a new line item initialized with a quantity and product</p>
	 * 
	 * @param pQuantity the quantity
	 * @param pProduct the product
	 */
	public LineItem(int pQuantity, Product pProduct) {
		setQuantity(pQuantity);
		setProduct(pProduct);
	}
	
	/**
	 * <p>Copy constructor</p>
	 * 
	 * @param pItem the item to copy; if null, an empty line item will be
	 * created
	 */
	public LineItem(LineItem pItem) {
		if (pItem != null) {
			setQuantity(pItem.getQuantity());
			setProduct(new Product(pItem.getProduct()));
		}
	}
	
	/**
	 * <p>Default constructor</p>
	 */
	public LineItem() {
		// does nothing...
	}

	/**
	 * <p>Returns the quantity associated with this line item</p>
	 * 
	 * @return the quantity associated with this line item
	 */
	public final int getQuantity() {
		return quantity;
	}

	/**
	 * <p>Sets the quantity of this line item</p>
	 * 
	 * @param pQuantity the new quantity
	 * 
	 * @throws IllegalArgumentException if pQuantity is negative
	 */
	public final void setQuantity(int pQuantity) {
		if (pQuantity < 0) {
			throw new IllegalArgumentException("quantity cannot be negative");
		}
		quantity = pQuantity;
	}

	/**
	 * <p>Returns the product associated with this line item</p>
	 * 
	 * @return the product associated with this line item
	 */
	public final Product getProduct() {
		return product;
	}

	/**
	 * <p>Sets the product associated with this line item</p>
	 * 
	 * @param pProduct the new product
	 * 
	 * @throws IllegalArgumentException if specified product is null
	 */
	public final void setProduct(Product pProduct) {
		if (pProduct == null) {
			throw new IllegalArgumentException("product cannot be null");
		}
		product = pProduct;
	}
	
	/**
	 * <p>Sets the cost</p>
	 * @param pCost
	 */
	public final void setCost(BigDecimal pCost) {
		cost = pCost;
	}
	
	/**
	 * <p>Returns the cost of this line item.  If the associated product is
	 * null or the quantity is 0, then $0.00 is returned</p>
	 * 
	 * @return the cost of this line item
	 * 
	 */
	public final BigDecimal getCost() {
		return cost;
	}
}
