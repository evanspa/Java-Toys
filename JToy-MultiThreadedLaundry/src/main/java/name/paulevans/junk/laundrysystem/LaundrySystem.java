package name.paulevans.junk.laundrysystem;

import java.util.ArrayList;
import java.util.List;

import name.paulevans.junk.laundrysystem.listener.LaundrySystemListener;
import name.paulevans.junk.model.DryerMachine;
import name.paulevans.junk.model.Person;

import org.apache.log4j.Logger;

/**
 * <p>This laundry system class acts as a sort of container that manages the
 * "ecosystem" of our laundromat.</p>
 *
 * @author Paul R Evans
 * @version $Id$
 */
public class LaundrySystem implements Runnable {

	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(LaundrySystem.class);
	
	/**
	 * Collection of registered listeners of this laundry system
	 */
	private List<LaundrySystemListener> listeners;
	
	/**
	 * Population of folders (consumers) and wet clothes producers
	 */
	private List<Person> population;
	
	/**
	 * Dryer machine
	 */
	private DryerMachine dryerMachine;
	
	/**
	 * <p>Default constructor</p>
	 */
	public LaundrySystem() {
		
		// create the list of listeners...
		listeners = new ArrayList<LaundrySystemListener>();
		
		// create the list to contain the population of folders
		// and wet clothes producers...
		population = new ArrayList<Person>();
	}
	
	/**
	 * <p>Sets the dryer machine</p>
	 * 
	 * @param pDryerMachine
	 */
	public final void setDryerMachine(DryerMachine pDryerMachine) {
		
		// do the assignment...
		dryerMachine = pDryerMachine;
		
		// attach listeners...
		
		
		// notify this thread...
		notify();
	}
	
	/**
	 * <p>Adds a user to this laundry system's population</p>
	 * 
	 * @param pPerson
	 */
	public final void addToPopulation(Person pPerson) {
		population.add(pPerson);	
		notify();
	}
	
	/**
	 * <p>Registers a new listener with this laundry system</p>
	 * 
	 * @param pListener
	 */
	public final void addListener(LaundrySystemListener pListener) {
		listeners.add(pListener);
	}

	/**
	 * <p>Execution point of this object</p>
	 */
	public void run() {
		
		// necessary check...
		if (dryerMachine == null) {
			throw new IllegalStateException("dryer machine cannot be null");
		}
		
		// activate the dryer machine...
		dryerMachine.activate();

		// run indefinitely...
		while (true) {
			
			// loop over the population and start those persons that are 
			// currently not running...
			for (Person person : population) {
				if (!person.isRunning()) {
					
					// attach listener...
					dryerMachine.addListener(person);
					
					// start the person...
					new Thread(person).start();
				}
			}
		
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
}









		