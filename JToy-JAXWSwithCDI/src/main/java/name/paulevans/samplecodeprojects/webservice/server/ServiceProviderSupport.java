package name.paulevans.samplecodeprojects.webservice.server;

/**
 * <p>Convenience class to extend - provides 'default' implementation of
 * {@link #invokeService(Object)}</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public abstract class ServiceProviderSupport implements ServiceProvider {

	@Override
	public Object invokeService(Object pRequest) {
		throw new UnsupportedOperationException();
	}
}
