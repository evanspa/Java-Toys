package name.paulevans.junk.laundrysystem.event;

import name.paulevans.junk.model.Person;

/**
 * <p>An event which indicates a new wet clothes producer has been added to
 * the laundry system.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class NewWetClothesProducerAddedEvent extends PersonEvent {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 6216984557435810746L;

	/**
	 * <p>Constructs a new 'wet clothes producer added' event</p>
	 * 
	 * @param pSource the event source
	 */
	public NewWetClothesProducerAddedEvent(Object pSource, Person pSubject) {
		super(pSource, pSubject);
	}
}
