package name.paulevans.samplecodeprojects.webservice.client.xmlws;

import name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler;
import name.paulevans.samplecodeprojects.webservice.client.ServiceInvokerSupport;

/**
 * <p>Convenience class for concrete XML service invokers to extend</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public abstract class XmlServiceInvokerSupport extends ServiceInvokerSupport
implements XmlServiceInvoker {

	/**
	 * Marshaler object
	 */
	private XmlMarshaler marshaler;
	
	@Override
	public final void setXmlMarshaler(XmlMarshaler pMarshaler) {
		marshaler = pMarshaler;
	}
	
	@Override
	public final XmlMarshaler getXmlMarshaler() {
		return marshaler;
	}
}
