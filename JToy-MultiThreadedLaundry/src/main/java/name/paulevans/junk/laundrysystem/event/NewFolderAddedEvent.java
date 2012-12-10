package name.paulevans.junk.laundrysystem.event;

import name.paulevans.junk.model.Person;

/**
 * <p>An event which indicates a new folder has been added to the laundry
 * system.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class NewFolderAddedEvent extends PersonEvent {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 6216984557435810746L;

	/**
	 * <p>Constructs a new 'folder added' event</p>
	 * 
	 * @param pSource the event source
	 */
	public NewFolderAddedEvent(Object pSource, Person pSubject) {
		super(pSource, pSubject);
	}
}
