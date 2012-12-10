package name.paulevans.junk.model.event;

import java.util.EventObject;

import name.paulevans.junk.model.ClothingArticle;

/**
 * <p>Models an event relative to articles of clothing</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class ClothingArticleEvent extends EventObject {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -3099639882833048678L;

	/**
	 * <p>Constructs a new 'clothing article' event</p>
	 * 
	 * @param pSource
	 */
	public ClothingArticleEvent(ClothingArticle pSource) {
		super(pSource);
	}
}
