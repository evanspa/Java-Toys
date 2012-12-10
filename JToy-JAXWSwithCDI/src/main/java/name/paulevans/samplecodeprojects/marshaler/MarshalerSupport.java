package name.paulevans.samplecodeprojects.marshaler;

import name.paulevans.samplecodeprojects.command.Command;

/**
 * <p>Convenience class for concrete marshalers to extend.  This class provides
 * default implementations of {@link #unmarshalRequest(Object)} and 
 * {@link #marshalCommandResult(Object)}</p>
 * 
 * <p>Each of the methods throws an
 * {@link java.lang.UnsupportedOperationException}</p>
 * 
 * @author Paul R Evans
 * @version $Id
 *
 */
public abstract class MarshalerSupport implements Marshaler {

	@Override
	public Command unmarshalRequest(Object pRequest) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object marshalCommandResult(Object pRequest) {
		throw new UnsupportedOperationException();
	}
}
