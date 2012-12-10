package name.paulevans.junk.model.event;

import java.util.EventObject;

/**
 * <p>Models an event to indicate an article of clothing was folded</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class FoldedEvent extends EventObject {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -4514906720468214300L;

	/**
	 * <p>Constructs a new 'article folded' event</p>
	 * 
	 * @param pSource
	 */
	public FoldedEvent(Object pSource) {
		super(pSource);
	}
}
