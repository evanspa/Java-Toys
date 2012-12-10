package name.paulevans.junk.laundrysystem.event;

import java.util.EventObject;

/**
 * <p>An event which indicates a dryer machine has been added to the laundry
 * system</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class DryerMachineAddedEvent extends EventObject {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 6216984557435810746L;

	/**
	 * <p>Constructs a new 'dryer machine added' event</p>
	 * 
	 * @param pSource the event source
	 */
	public DryerMachineAddedEvent(Object pSource) {
		super(pSource);
	}
}
