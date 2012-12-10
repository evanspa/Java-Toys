package name.paulevans.sampleprojects.model;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

/**
 * Models a product
 * 
 * @author Paul R Evans
 * @version $Id: Product.java,v 1.2 2008/12/01 05:23:32 evanspa Exp $
 * 
 */
public class Product {
	
	/**
	 * Stores the price of this order
	 */
	private BigDecimal retailPrice;
	
	/**
	 * Stores the description of this order
	 */
	private String description;
	
	/**
	 * Stores the name of the product
	 */
	private String name;

	/**
	 * <p>Creates a new product initialized with a retail price and
	 * name</p>
	 * 
	 * @param pRetailPrice the retail price
	 * @param pName the product name
	 * 
	 * @throws IllegalArgumentException if specific retail price is null
	 * @throws IllegalArgumentException if specified description is null
	 */
	public Product(BigDecimal pRetailPrice, String pName) {
		setRetailPrice(pRetailPrice);
		setName(pName);
	}
	
	/**
	 * <p>Copy constructor</p>
	 * 
	 * @param pProduct the product to copy; if null, a blank product will be
	 * created
	 */
	public Product(Product pProduct) {
		if (pProduct != null) {
			setRetailPrice(pProduct.getRetailPrice());
			setDescription(pProduct.getDescription());
			setName(pProduct.getName());
		}
	}
	
	/**
	 * <p>Default constructor</p>
	 */
	public Product() {
		// does nothing...
	}
	
	/**
	 * <p>Returns the description</p>
	 * 
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * <p>Sets a new description</p>
	 * 
	 * @param pDescription
	 */
	public final void setDescription(String pDescription) {
		description = pDescription;
	}

	/**
	 * <p>Returns the retail price value</p>
	 * 
	 * @return
	 */
	public final BigDecimal getRetailPrice() {
		return retailPrice;
	}

	/**
	 * <p>Sets the retail price value</p>
	 * 
	 * @param pRetailPrice
	 * 
	 * @throws IllegalArgumentException if null is supplied
	 */
	public final void setRetailPrice(BigDecimal pRetailPrice) {
		
		// enforce the contract...
		if (pRetailPrice == null) {
			throw new IllegalArgumentException("retail price cannot be null");
		}
		
		// do the assignment...
		retailPrice = pRetailPrice;
	}

	/**
	 * @param pName the name to set
	 * 
	 * @throws IllegalArgumentException if null or blank is supplied
	 */
	public void setName(String pName) {
		
		// enforce the contract...
		if (StringUtils.isBlank(pName)) {
			throw new IllegalArgumentException("name cannot be blank");
		}
		
		// do the assignment...
		name = pName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}