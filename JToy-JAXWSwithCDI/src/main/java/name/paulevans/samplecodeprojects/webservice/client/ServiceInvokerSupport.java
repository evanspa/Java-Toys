package name.paulevans.samplecodeprojects.webservice.client;

import name.paulevans.samplecodeprojects.command.Command;
import name.paulevans.samplecodeprojects.marshaler.Marshaler;

/**
 * <p>Convenience class for concrete service invokers to extend</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public abstract class ServiceInvokerSupport implements ServiceInvoker {

	/**
	 * Marshaler object
	 */
	private Marshaler marshaler;
	
	@Override
	public final void setMarshaler(Marshaler pMarshaler) {
		marshaler = pMarshaler;
	}
	
	@Override
	public final Marshaler getMarshaler() {
		return marshaler;
	}
	
	@Override
	public Object invoke(Command pRequest) {
		throw new UnsupportedOperationException();
	}
}
