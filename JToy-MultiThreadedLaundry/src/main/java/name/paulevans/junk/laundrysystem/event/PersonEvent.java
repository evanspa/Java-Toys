package name.paulevans.junk.laundrysystem.event;

import java.util.EventObject;

import name.paulevans.junk.model.Person;

/**
 * <p>Base abstraction for modeling events associated with people</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public abstract class PersonEvent extends EventObject {
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 7889472542567043831L;
	
	/**
	 * The person/subject of the event
	 */
	private Person subject;

	/**
	 * <p>Constructs a new person event</p>
	 * 
	 * @param pSource the object that is the source of the event
	 * @param pSubject
	 */
	public PersonEvent(Object pSource, Person pSubject) {
		super(pSource);
		setSubject(pSubject);
	}
	
	/**
	 * <p>Sets the subject</p>
	 * 
	 * @param pSubject
	 */
	private final void setSubject(Person pSubject) {
		subject = pSubject;
	}
	
	/**
	 * <p>Returns the subject</p>
	 * 
	 * @return
	 */
	public final Person getSubject() {
		return subject;
	}

}
