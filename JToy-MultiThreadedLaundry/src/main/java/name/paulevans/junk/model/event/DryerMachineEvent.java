package name.paulevans.junk.model.event;

import java.util.EventObject;

import name.paulevans.junk.model.DryerMachine;

/**
 * <p>Models an event relative to dryer machine</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class DryerMachineEvent extends EventObject {
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 5114594599240302055L;

	/**
	 * <p>Constructs a new 'dryer machine' event</p>
	 * 
	 * @param pSource
	 */
	public DryerMachineEvent(DryerMachine pSource) {
		super(pSource);
	}
}
