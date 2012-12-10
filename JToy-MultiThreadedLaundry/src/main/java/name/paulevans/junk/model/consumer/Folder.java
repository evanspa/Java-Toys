package name.paulevans.junk.model.consumer;

import name.paulevans.junk.model.ClothingArticle;
import name.paulevans.junk.model.DryerMachine;
import name.paulevans.junk.model.Person;
import name.paulevans.junk.model.event.ClothingArticleEvent;
import name.paulevans.junk.model.event.DryerMachineEvent;
import name.paulevans.junk.model.listener.DryerMachineListener;

import org.apache.log4j.Logger;

/**
 * <p>Models a person who is responsible for folding clothes.  Folders do
 * one thing in life - they take dry clothes out of the dryer machine and they
 * fold and place them into a stack.</p>
 *
 * @author Paul E Evans
 * @version $Id$
 */
public class Folder extends Person implements DryerMachineListener {
	
	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(Folder.class);

	/**
	 * Maintains a count of the number of articles of clothing folded by this
	 * person
	 */
	private int numArticlesFolded;

	/**
	 * Maintains a count of the number of times this folder object has had
	 * to wait for more clothes to be added to the dryer machine
	 */
	private int waitCount;

	/**
	 * <p>Constructs a new folder object</p>
	 * 
	 * @param pFirstName The first name of this person
	 * @param pLastName The last name of this person
	 * @param pDryerMachine A reference to the dryer machine
	 */
	public Folder(String pFirstName, String pLastName) {
		super(pFirstName, pLastName);
	}

	/**
	 * <p>Returns the wait count for this folder</p>
	 *
	 * @return the number of times this folder has had to wait on the dryer
	 * machine for more clothes to be added to it
	 */
	public final int getWaitCount() {
		return waitCount;
	}

	/**
	 * <p>Returns the folded count for this folder</p>
	 *
	 * @return Number of articles of clothing folded by this folder person
	 */
	public final int getNumArticlesFolded() {
		return numArticlesFolded;
	}

	@Override
	public String toString() {

		StringBuilder strBuilder = new StringBuilder();
		
		// build up the string...
		strBuilder.append("Folder:\n\tName: [").append(getFirstName());
		strBuilder.append(" ").append(getLastName()).append("]\n\t");
		strBuilder.append("Wait count: [").append(getWaitCount());
		strBuilder.append("]\n\tFolded count: [").append(
				getNumArticlesFolded()).append("]");

		// return it!
		return strBuilder.toString();
	}

	@Override
	public final void clothingArticledAdded(ClothingArticleEvent pEvent) {
		
		DryerMachine dryerMachine;
		ClothingArticle clothingArticle;
	
		// get the dryer machine associated with the event...
		dryerMachine = (DryerMachine)pEvent.getSource();
		
		// attempt to obtain lock on dryer machine...
		synchronized (dryerMachine) {
			
			// okay, now that we have the lock on the dryer machine, check to
			// really see if there are clothes ready to be folded (even though
			// the 'add' event was raised, some other folder could have gotten
			// to the item already...
			
			// check if the dryer machine has any clothes in it that need to
			// be folded...
			if (dryerMachine.isClothsRemaining()) {
				
				// okay, so it looks like we have something to fold...
				clothingArticle = dryerMachine.removeClothingArticle();
				
				// log it...
				logger.debug("Article of clothing: [" + clothingArticle + 
					"] was removed from dryer machine by: [" + this + "]");
				
				// increment our counter...
				numArticlesFolded++;
			}
		}
	}

	@Override
	public final void clothingArticleRemoved(ClothingArticleEvent pEvent) {
		// ignore this event...
	}

	@Override
	public void dryerMachineActivated(DryerMachineEvent pEvent) {
		// ignore this event...
	}

	@Override
	public void dryerMachineDeactivated(DryerMachineEvent pEvent) {
		// ignore this event...
	}
}