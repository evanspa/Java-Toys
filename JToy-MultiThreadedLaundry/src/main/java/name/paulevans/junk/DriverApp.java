package name.paulevans.junk;

import name.paulevans.junk.laundrysystem.LaundrySystem;
import name.paulevans.junk.model.DryerMachine;
import name.paulevans.junk.model.Sock;
import name.paulevans.junk.model.consumer.Folder;
import name.paulevans.junk.model.producer.WetClothsProducer;

import org.apache.log4j.Logger;

public class DriverApp {
	
	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(DriverApp.class);
	
	/**
	 * Private constructor (duh)
	 */
	private DriverApp() {

		DryerMachine dryerMachine;
		LaundrySystem laundrySystem;
		
		// create the laundry system...
		laundrySystem = new LaundrySystem();
		
		// create a dryer machine and add some wet clothes to it...
		dryerMachine = new DryerMachine();
		dryerMachine.addClothingArticle(new Sock("sock 1"));
		dryerMachine.addClothingArticle(new Sock("sock 2"));
		dryerMachine.addClothingArticle(new Sock("sock 3"));
		dryerMachine.addClothingArticle(new Sock("sock 4"));
		
		// add folders...
		laundrySystem.addToPopulation(new Folder("Paul", "Evans"));
		laundrySystem.addToPopulation(new Folder("Caroline", "Evans"));
		
		// add wet clothes producers...
		laundrySystem.addToPopulation(new WetClothsProducer("Sarah", "Evans"));
		
		// associate the dryer machine...
		laundrySystem.setDryerMachine(dryerMachine);
		
		// start the laundry system...
		new Thread(laundrySystem).start();
		
		// this code is necessary to cause the main application thread to go
		// into a 'wait' state so that the JVM does not terminate...
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException exc) {
				logger.error(exc);
				throw new RuntimeException(exc);
			}
		}
	}
	
	/**
	 * <p>Program entry point</p>
	 * 
	 * @param pArgs
	 */
	public static final void main(String pArgs[]) {
		
		// create the driver app...
		new DriverApp();
	}
}
