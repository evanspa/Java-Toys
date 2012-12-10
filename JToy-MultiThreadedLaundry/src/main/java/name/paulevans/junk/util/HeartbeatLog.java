package name.paulevans.junk.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import name.paulevans.junk.laundrysystem.event.DryerMachineAddedEvent;
import name.paulevans.junk.laundrysystem.event.NewFolderAddedEvent;
import name.paulevans.junk.laundrysystem.event.NewWetClothesProducerAddedEvent;
import name.paulevans.junk.laundrysystem.listener.LaundrySystemListener;
import name.paulevans.junk.model.DryerMachine;
import name.paulevans.junk.model.Person;

import org.apache.log4j.Logger;

/**
 * <p>Contains information about all of the objects in the system and writes a
 * 'heart-beat' style log message.</p>
 * 
 * <p>The {@link #getLogMessage()} method returns a formatted string
 * representation of the state of the objects that can be used.</p>
 *
 * @author Paul R Evans
 * @version $Id$
 */
public class HeartbeatLog implements LaundrySystemListener {
	
	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(HeartbeatLog.class);
	
	/**
	  * Used to set the timer period (3 seconds)
	  */
	private static final int TIMER_PERIOD = 3000;

	/**
	 * Contains a list of the people that are part of the laundry system
	 */
	private List<Person> people;

	/**
	 * Contains a reference to the dryer machine
	 */
	private DryerMachine dryerMachine;
	
	public HeartbeatLog() {
		
		// Create a timer object...
		Timer timer = new Timer(TIMER_PERIOD, new ActionListener() {
			
			public void actionPerformed(ActionEvent pEvent) {
				
				logger.info(getLogMessage());
			}
		});
		
		// start the timer...under the covers, this actually results in 2 threads being created in the JVM.  1.) AWT-Windows (daemon) and 
		// 2.) TimerQueue (daemon)...so these 2 threads alone, since they are both daemon threads, will not keep the
		// JVM alive...
		timer.start();
	}

	/**
	 * Constructs a new heartbeat log object
	 *
	 * @param aDryerMachine A reference to the dryer machine object
	 */
	public HeartbeatLog(DryerMachine aDryerMachine) {
		dryerMachine = aDryerMachine;
		people = new ArrayList<Person>();
	}

	/**
	 * Adds a person (presumably from the laundry system) to this object
	 *
	 * @param aPerson The person reference to add
	 */
	public void addPerson(Person aPerson) {
		people.add(aPerson);
	}

	/**
	 * Returns a string that combines the 'toString()' calls of each of the contained objects.
	 *
	 * @return A formatted string that combines the 'toString()' calls of each of the contained objects
	 */
	private String getLogMessage() {

		StringBuffer msg;

		msg = new StringBuffer();
		msg.append("\n--------------------------------------------------------\n");
		msg.append("Dryer machine: {").append(dryerMachine).append("}\n");
		for (Person person : people) {
			msg.append("Person: {").append(person).append("}\n");
		}
		msg.append("--------------------------------------------------------\n");
		return msg.toString();
	}

	@Override
	public void newFolderAdded(NewFolderAddedEvent pEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newWetClothesProducerAdded(
			NewWetClothesProducerAddedEvent pEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dryerMachineAdded(DryerMachineAddedEvent pEvent) {
		// TODO Auto-generated method stub
		
	}
}
