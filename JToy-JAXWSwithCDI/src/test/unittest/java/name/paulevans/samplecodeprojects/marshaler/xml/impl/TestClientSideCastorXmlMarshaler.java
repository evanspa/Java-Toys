package name.paulevans.samplecodeprojects.marshaler.xml.impl;

import name.paulevans.samplecodeprojects.command.client.marshaler.ClientSideCastorXmlMarshaler;
import name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler;
import name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshalerTestCase;

/**
 * <p>Concrete test case for {@link name.paulevans.samplecodeprojects.marshaler.xml.impl.castor.CastorXmlMarshaler}
 * class</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class TestClientSideCastorXmlMarshaler extends XmlMarshalerTestCase {

	@Override
	public XmlMarshaler getXmlMarshaler() {
		return new ClientSideCastorXmlMarshaler();
	}

}
