package name.paulevans.junk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import name.paulevans.junk.model.event.ClothingArticleEvent;
import name.paulevans.junk.model.event.DryerMachineEvent;
import name.paulevans.junk.model.listener.DryerMachineListener;

import org.apache.log4j.Logger;

/**
 * <p>Models a drying machine within our laundry domain.  This abstraction
 * contains a single thread-safe method, removeClothingArticle(), which
 * removes a single article of clothing from the machine.</p>
 * 
 * <p>This class contains operations for removing, adding, querying the
 * number of clothing articles contained within the machine.</p>
 *
 * @author Paul R Evans
 * @version $Id$
 */
public class DryerMachine {

	/**
	 * Constant to store the fixed time (in milliseconds) it takes for any
	 * person to remove an article of clothing from the dryer machine
	 */
	private static final int FIXED_REMOVE_TIME = 3000;
	
	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(DryerMachine.class);
	
	/**
	 * Stores whether or not this dryer machine is active
	 */
	private boolean isActive;

	/**
	 * The collection of clothing articles takes the form of a list
	 */
	private List<ClothingArticle> cloths;

	/**
	 * A random number generator to use by this dryer machine
	 */
	private Random randomGenerator;
	
	/**
	 * Collection of listeners
	 */
	private List<DryerMachineListener> listeners;

	/**
	 * <p>Default constructor</p>
	 */
	public DryerMachine() {
		initialize();
	}
	
	/**
	 * <p>Generic initializer method</p>
	 */
	private final void initialize() {
		
		// create the clothes collection...
		cloths = new ArrayList<ClothingArticle>();
		
		// create random number generator (which will be used to remove
		// random articles of clothing from itself)...
		randomGenerator = new Random();
		
		// create listeners collection...
		listeners = new ArrayList<DryerMachineListener>();
	}
	
	/**
	 * <p>Registers the specific listener</p>
	 * 
	 * @param pListener
	 */
	public final void addListener(DryerMachineListener pListener) {
		listeners.add(pListener);
	}	
	
	/**
	 * <p>Activates the dryer machine</p>
	 */
	public final void activate() {
		
		// set active flag to true...
		isActive = true;
		
		// raise activation event...
		raiseActivationEvent();
	}
	
	/**
	 * <p>Deactivates the dryer machine</p>
	 */
	public final void deactivate() {
		
		// set activate flag to false
		isActive = false;
	}
	
	/**
	 * <p>Returns the activation state of this dryer machine</p>
	 * 
	 * @return
	 */
	public final boolean isActive() {
		return isActive;
	}
	
	/**
	 * <p>Creates activation event and notifies each registered listener</p>
	 */
	private final void raiseActivationEvent() {
		
		DryerMachineEvent event;
		
		// create the event object...
		event = new DryerMachineEvent(this);
		
		// notify each listener...
		for (DryerMachineListener listener : listeners) {
			listener.dryerMachineActivated(event);
		}
	}

	/**
	 * <p>Creates event and notifies each registered listener of addition of
	 * article of clothing</p>
	 */
	private final void raiseArticleAddedEvent(ClothingArticle
			pClothingArticle) {
		
		ClothingArticleEvent event;
		
		// create the event object...
		event = new ClothingArticleEvent(pClothingArticle);
		
		// notify each listener...
		for (DryerMachineListener listener : listeners) {
			listener.clothingArticledAdded(event);
		}
	}	
	
	/**
	 * <p>Creates event and notifies each registered listener of removal of
	 * article of clothing</p>
	 */
	private final void raiseArticleRemovedEvent(ClothingArticle
			pClothingArticle) {
		
		ClothingArticleEvent event;
		
		// create the event object...
		event = new ClothingArticleEvent(pClothingArticle);
		
		// notify each listener...
		for (DryerMachineListener listener : listeners) {
			listener.clothingArticleRemoved(event);
		}
	}

	/**
	 * <p>Thread-safe method for removing an article of clothing from this
	 * dryer machine.</p>
	 *
	 * @return The article of clothing that has been removed
	 */
	public synchronized ClothingArticle removeClothingArticle() {
		
		ClothingArticle removedItem;

		try {

			// fixed set of time it takes to remove an article of clothing from
			// the dryer machine...
			Thread.sleep(FIXED_REMOVE_TIME);

			// remove and return a random article of clothing from the
			// dryer machine...
			if (isClothsRemaining()) {
				
				// remove a random article of clothing...
				removedItem = cloths.remove(randomGenerator.nextInt(
						getNumCloths()));
				
				// raise the event...
				raiseArticleRemovedEvent(removedItem);
				
				// return it!
				return removedItem;
			} else {
				logger.debug("Oh well, somebody's trying to remove an item" +
						"from the dryer but I'm empty");
			}
			return null;
			
		} catch (InterruptedException e) {
			
			// log the exception...
			logger.error(e);

			// the assumption here is that callers won't be able to do anything
			// with an InterruptedException, so we'll re-throw it as a runtime
			// exception...
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>Adds an article of clothing to this dryer machine</p>
	 *
	 * @param pClothingArticle ClothingArticle The clothing article to be
	 * added to the this dryer
	 */
	public synchronized void addClothingArticle(ClothingArticle
			pClothingArticle) {
		
		// add the article of clothing...
		cloths.add(pClothingArticle);
		
		// raise event...
		raiseArticleAddedEvent(pClothingArticle);
	}

	/**
	 * <p>Returns the number of clothing articles contained within this
	 * dryer machine</p>
	 *
	 * @return the number of clothing articles within this dryer machine
	 */
	public final int getNumCloths() {
		return cloths.size();
	}

	/**
	 * <p>Returns if any article of clothing remains in this dryer machine</p>
	 * 
	 * @return <code>true</code> if there is at least 1 article of clothing
	 * within this dryer machine; otherwise returns <code>false</code>
	 */
	public final boolean isClothsRemaining() {
		return getNumCloths() > 0;
	}
}