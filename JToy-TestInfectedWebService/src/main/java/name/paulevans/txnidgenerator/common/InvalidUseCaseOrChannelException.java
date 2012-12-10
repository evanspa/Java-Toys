package name.paulevans.txnidgenerator.common;

/**
 * <p>Exception to indicate the request contained either an invalid use
 * case value or invalid channel value.</p>
 * 
 * @author Paul R Evans
 *
 */
public class InvalidUseCaseOrChannelException extends 
TransactionIdentifierServiceException {

	/**
	 * <p>Default serial version UID</p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>Constructs a new InvalidUseCaseOrChannelException exception</p>
	 * 
	 * @param pMessage exception message
	 */
	public InvalidUseCaseOrChannelException(String pMessage) {
		super(pMessage);
	}
}
