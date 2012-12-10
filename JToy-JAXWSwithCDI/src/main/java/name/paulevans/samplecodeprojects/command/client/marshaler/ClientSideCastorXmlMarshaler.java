package name.paulevans.samplecodeprojects.command.client.marshaler;

import name.paulevans.samplecodeprojects.marshaler.xml.impl.castor.CastorXmlMarshaler;

/**
 * <p>Loads Castor mappings that are client-side oriented.  I.e., this
 * class loads a set of "common" mappings as well as mappings for
 * client-side command classes (i.e. those found in
 * {@link name.paulevans.samplecodeprojects.command.client.clientcmds})</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class ClientSideCastorXmlMarshaler extends CastorXmlMarshaler {

	@Override
	public void configure() {
		
		// load mappings for common types...
		loadClasspathMapping("castor-mapping_commonTypes.xml");
		
		// load mappings for client-centric command classes...
		loadClasspathMapping("castor-mapping_clientCommands.xml");
	}

}
