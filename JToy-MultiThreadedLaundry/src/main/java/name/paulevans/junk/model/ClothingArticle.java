package name.paulevans.junk.model;

import java.util.ArrayList;
import java.util.List;

import name.paulevans.junk.model.event.FoldedEvent;
import name.paulevans.junk.model.listener.ClothingArticleListener;


/**
 * <p>Models an article of clothing in our laundry domain</p>
 *
 * @author Paul R Evans
 * @version $Id$
 */
public abstract class ClothingArticle {

	/**
	 * Indicates if this article of clothing is current folded or not
	 */
	private boolean isFolded;

	/**
	 * The fold-complexity associated with this article of clothing
	 */
	private FoldComplexity foldComplexity;

	/**
	 * The description of this article-of-clothing
	 */
	private String description;

	/**
	 * Collection of listeners
	 */
	private List<ClothingArticleListener> listeners;

	/**
	 * <p>Constructs a new article of clothing</p>
	 * 
	 * @param pDescription The description of this article of clothing
	 * @param pFoldComplexity The fold-complexity associated with this article
	 * of clothing
	 */
	public ClothingArticle(String pDescription, FoldComplexity pFoldComplexity) {		
		setDescription(pDescription);
		setFoldComplexity(pFoldComplexity);
		initialize();
	}

	/**
	 * <p>Generic initializer method</p>
	 */
	private final void initialize() {
		
		// create the listeners list...
		listeners = new ArrayList<ClothingArticleListener>();
	}
	
	/**
	 * <p>Registers the specific listener</p>
	 * 
	 * @param pListener
	 */
	public final void addListener(ClothingArticleListener pListener) {
		listeners.add(pListener);
	}

	/**
	 * <p>Returns if this article of clothing is currently folded or not</p>
	 * 
	 * @return <code>true</code> if this clothing article is currently folded;
	 * otherwise returns <code>false</code>
	 */
	public final boolean isFolded() {
		return isFolded;
	}
	
	/**
	 * <p>Creates event and notifies each registered listener.</p>
	 */
	private final void raiseNewFoldEvent() {
		
		FoldedEvent event;
		
		// create the event object...
		event = new FoldedEvent(this);
		
		// notify each listener...
		for (ClothingArticleListener listener : listeners) {
			listener.clothingArticleFolded(event);
		}
	}

	/**
	 * <p>Causes this article of clothing to become folded</p>
	 */
	public final void fold() {
		isFolded = true;
		
		// raise event...
		raiseNewFoldEvent();
	}
	
	/**
	 * <p>Sets the fold complexity</p>
	 * 
	 * @param pFoldComplexity
	 */
	private final void setFoldComplexity(FoldComplexity pFoldComplexity) {
		foldComplexity = pFoldComplexity;
	}

	/**
	 * <p>Returns the fold-complexity value associated with this article of
	 * clothing.</p>
	 * 
	 * @return the enumeration value indicating the complexity associated with
	 * folding this article of clothing
	 */
	public final FoldComplexity getFoldComplexity() {
		return foldComplexity;
	}
	
	/**
	 * <p>Sets the description</p>
	 * 
	 * @param pDescription
	 */
	private final void setDescription(String pDescription) {
		description = pDescription;
	}

	/**
	 * <p>Returns the description</p>
	 * 
	 * @return description value associated with this article of clothing
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @return string representation of the state of this object - the
	 * description, fold complexity and whether or not it is folded
	 */
	public final String toString() {
		
		// create the string representation of the state of this object...
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Article of clothing -- class: [");
		strBuilder.append(getClass().getName()).append("]\n\tDescription: [");
		strBuilder.append(getDescription()).append("]\n\tIs folded? [");
		strBuilder.append(isFolded()).append("]\n\tHash code: [");
		strBuilder.append(hashCode()).append("]");
		
		// return it!
		return strBuilder.toString();
	}
}