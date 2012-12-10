package name.paulevans.sampleprojects.util.client.http;

import org.apache.commons.httpclient.HostConfiguration;

/**
 * <p>This stupid class is needed because the old Apache HttpClient 3.1
 * "HostConfiguration" violates the JavaBean specification in that the
 * return type of its 'getProxyHost()' is different than the parameter
 * type of its 'setProxyHost()' method.  Because of this we can't use
 * Spring to configure the proxy information.</p>
 * 
 * <p>So, we have this class which provides a convenience method that WILL
 * allow us to configure the proxy information from Spring.</p>
 * 
 * <p>I copied this code from
 * <a href="http://onebyteatatime.wordpress.com/2009/04/08/spring-web-service-call-using-proxy-per-connection/">this following blog entry</a>.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class HttpHostConfiguration extends HostConfiguration {
	
	/**
	 * Delimeter (used in {@link #setProxyHostPlusPort(String)})
	 */
	private static final String DELIMITER = ":";

	/**
	 * <p>Convenience method to configure the proxy information.  The supplied
	 * value should be of the form:</p>
	 * <p><strong>HOST:PORT</strong></p>
	 * <p>For example, you would supply: <strong>name.paulevans.proxy:8081</strong></p>
	 * 
	 * @param pProxyHostPlusPort the host and port of the proxy in the form:
	 * HOST:PORT
	 * 
	 * @throws IllegalArgumentException if the supplied proxy host and port
	 * are not in the HOST:PORT format
	 */
	public final void setProxyHostPlusPort(String pProxyHostPlusPort) {

		String[] hostPlusPort;
		
		// validate the input...
		if (pProxyHostPlusPort.indexOf(DELIMITER) == -1) {
			throw new IllegalArgumentException("proxyHostPlusPort string is" +
					" expected in 'host:port' format");
		}

		// tokenize the input...
		hostPlusPort = pProxyHostPlusPort.split(DELIMITER);

		// set the host and port...
		setProxy(hostPlusPort[0], new Integer(hostPlusPort[1]).intValue());
	}
}
