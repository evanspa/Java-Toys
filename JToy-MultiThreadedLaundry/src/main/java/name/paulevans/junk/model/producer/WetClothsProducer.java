package name.paulevans.junk.model.producer;

import name.paulevans.junk.model.DryerMachine;
import name.paulevans.junk.model.Person;
import name.paulevans.junk.model.Sock;
import name.paulevans.junk.model.event.ClothingArticleEvent;
import name.paulevans.junk.model.event.DryerMachineEvent;
import name.paulevans.junk.model.listener.DryerMachineListener;

import org.apache.log4j.Logger;

/**
 * <p>This type of person has 1 purpose in life: to add wet clothes to
 * the dryer machine</p>
 *
 * @author Paul R Evans
 * @version $Id$
 */
public class WetClothsProducer extends Person implements DryerMachineListener {

	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(DryerMachine.class);
	
	/**
	 * The number of clothing articles to add in a particular cycle
	 */
	private static final int NUM_CLOTHING_ARTICLES_TO_ADD_PER_CYCLE = 5;
	
	/**
	 * The number of milliseconds to sleep for before adding more clothes
	 * to the dryer
	 */
	private static final int SLEEP_TIME_BEFORE_ADDING_MORE_CLOTHES = 60000;

	/**
	 * <p>Constructs a new WetClothsProducer object</p>
	 *
	 * @param pFirstName The person's first name
	 * @param pLastName The person's last name
	 * @param pDryerMachine Reference to the dryer machine
	 */
	public WetClothsProducer(String pFirstName, String pLastName) {
		super(pFirstName, pLastName);
	}

	@Override
	public final void clothingArticledAdded(ClothingArticleEvent pEvent) {
		// ignore this event...		
	}

	@Override
	public final void clothingArticleRemoved(ClothingArticleEvent pEvent) {
		// ignore this event...
	}

	@Override
	public final void dryerMachineActivated(DryerMachineEvent pEvent) {
		
		DryerMachine dryerMachine;
		int loop;
		
		// get the dryer machine...
		dryerMachine = (DryerMachine)pEvent.getSource();
		
		// working like a slave!
		while (dryerMachine.isActive()) {

			// need to obtain lock on the dryerMachine object...
			synchronized (dryerMachine) {

				// log the fact we are about to add clothing articles...
				logger.debug("Wet cloths producer about to add 5 new" +
						" clothing articles to the dryer");

				// add a set of clothing articles...
				for (loop = 0; loop < NUM_CLOTHING_ARTICLES_TO_ADD_PER_CYCLE; 
				loop++) {
					dryerMachine.addClothingArticle(new Sock("NS" + loop));
				}

				// notify the dryer that we're done adding clothes to it...
				dryerMachine.notifyAll();
			}

			// sleep for some time before adding more wet clothes to the
			// dryer machine...
			try {
				Thread.sleep(SLEEP_TIME_BEFORE_ADDING_MORE_CLOTHES);
			} catch (InterruptedException e) {

				// log the exception...
				logger.error(e);

				// re-throw as a runtime exception as we don't expect callers
				// to be able to deal with an InterruptedException...
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public final void dryerMachineDeactivated(DryerMachineEvent pEvent) {
		// ignore this event...
	}
}
