package name.paulevans.junk.model;

import name.paulevans.junk.model.listener.DryerMachineListener;

import org.apache.log4j.Logger;


/**
 * <p>Models a person in our laundry domain, as such is marked as being
 * abstract.</p>
 *
 * @author Paul Evans
 * @version $Id$
 */
public abstract class Person implements Runnable, DryerMachineListener {
	
	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(Person.class);

	/**
	 * Stores the first name
	 */
	private String lastName;

	/**
	 * Stores the last name
	 */
	private String firstName;
	
	/**
	 * Stores whether this person is in a 'running' state or not
	 */
	private boolean isRunning;

	/**
	 * <p>Constructs a new Person object</p>
	 *
	 * @param pFirstName The first name of this person
	 * @param pLastName The last name of this person
	 * @param pDryerMachine A reference to the dryer machine
	 */
	public Person(String pFirstName, String pLastName) {
		
		// set the state...
		setFirstName(pFirstName);
		setLastName(pLastName);
	}

	/**
	 * <p>Returns this person's first name</p>
	 *
	 * @return this person's first name
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * <p>Sets the last name</p>
	 * 
	 * @param pLastName
	 */
	private final void setLastName(String pLastName) {
		lastName = pLastName;
	}

	/**
	 * <p>Sets the first name</p>
	 * 
	 * @param pFirstName
	 */
	private final void setFirstName(String pFirstName) {
		firstName = pFirstName;
	}

	/**
	 * <p>Returns this person's last name</p>
	 *
	 * @return this person's last name
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * @param pIsRunning the isRunning to set
	 */
	public final void setRunning(boolean pIsRunning) {
		isRunning = pIsRunning;
	}

	/**
	 * @return the isRunning
	 */
	public final boolean isRunning() {
		return isRunning;
	}
	
	@Override
	public void run() {

		// update our state properly...
		setRunning(true);

		// working like slaves!
		while (true) {

			// wait...
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					logger.error(e);
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * <p>Returns a string representation of this person - the first and
	 * last name</p>
	 *
	 * @return string representation of this person - the first and last names
	 */
	public String toString() {
		return "[" + getFirstName() + " " + getLastName() + "]";
	}
}