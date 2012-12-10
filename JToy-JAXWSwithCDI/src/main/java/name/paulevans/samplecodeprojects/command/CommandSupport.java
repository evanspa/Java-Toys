package name.paulevans.samplecodeprojects.command;

import name.paulevans.samplecodeprojects.model.Order;

public abstract class CommandSupport implements Command {

	/**
	 * Order instance
	 */
	private Order order;

	/**
	 * @param pOrder the order to set
	 */
	public void setOrder(Order pOrder) {
		this.order = pOrder;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

}
