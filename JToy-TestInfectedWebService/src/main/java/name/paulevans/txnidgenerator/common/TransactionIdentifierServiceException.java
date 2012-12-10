package name.paulevans.txnidgenerator.common;

import name.paulevans.distcmdfrmwk.CommandProcessingException;

/**
 * <p>Root of the hierarchy of transaction identifier service exceptions.</p>
 * 
 * @author Paul R Evans
 *
 */
public abstract class TransactionIdentifierServiceException extends 
CommandProcessingException {

	/**
	 * <p>Default serial version UID</p>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * <p>Constructs a new transaction identifier service exception</p>
	 * 
	 * @param pMessage exception message
	 */
	public TransactionIdentifierServiceException(String pMessage) {
		super(pMessage);
	}
}
