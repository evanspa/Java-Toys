package name.paulevans.samplecodeprojects.marshaler.xml.impl;

import name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler;
import name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshalerTestCase;
import name.paulevans.samplecodeprojects.webservice.server.xmlws.marshaler.ServerSideCastorXmlMarshaler;

/**
 * <p>Concrete test case for {@link name.paulevans.samplecodeprojects.marshaler.xml.impl.castor.CastorXmlMarshaler}
 * class</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class TestServerSideCastorXmlMarshaler extends XmlMarshalerTestCase {

	@Override
	public XmlMarshaler getXmlMarshaler() {
		return new ServerSideCastorXmlMarshaler();
	}

}
