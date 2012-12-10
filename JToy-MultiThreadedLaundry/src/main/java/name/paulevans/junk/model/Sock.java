package name.paulevans.junk.model;


/**
 * <p>Models a sock in our laundry domain</p>
 *
 * @author Paul R Evans
 * @version $Id$
 */
public class Sock extends ClothingArticle {

	/**
	 * <p>Constructs a new sock</p>
	 *
	 * @param pDescription the description of this new sock
	 */
	public Sock(String pDescription) {
		super(pDescription, FoldComplexity.LOW);
	}
}
