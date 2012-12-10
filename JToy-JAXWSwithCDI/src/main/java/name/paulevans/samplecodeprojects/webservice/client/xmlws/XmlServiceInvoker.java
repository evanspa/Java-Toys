package name.paulevans.samplecodeprojects.webservice.client.xmlws;

import name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler;
import name.paulevans.samplecodeprojects.webservice.client.ServiceInvoker;

public interface XmlServiceInvoker extends ServiceInvoker {
	
	void setXmlMarshaler(XmlMarshaler pMarshaler);
	
	XmlMarshaler getXmlMarshaler();
	
	

}
