package name.paulevans.samplecodeprojects.webservice.server.xmlws.marshaler;

import name.paulevans.samplecodeprojects.marshaler.xml.impl.castor.CastorXmlMarshaler;

/**
 * <p>Loads Castor mappings that are server-side oriented.  I.e., this
 * class loads a set of "common" mappings as well as mappings for
 * server-side command classes (i.e. those found in
 * {@link name.paulevans.samplecodeprojects.command.server.servercmds})</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class ServerSideCastorXmlMarshaler extends CastorXmlMarshaler {

	@Override
	public void configure() {
		
		// load common types...
		loadClasspathMapping("castor-mapping_commonTypes.xml");
		
		// load server-centric commands...
		loadClasspathMapping("castor-mapping_serverCommands.xml");
	}

}
