package name.paulevans.samplecodeprojects.marshaler.xml.impl.castor;

import java.io.StringWriter;

import name.paulevans.samplecodeprojects.command.Command;
import name.paulevans.samplecodeprojects.marshaler.MarshalerSupport;
import name.paulevans.samplecodeprojects.marshaler.xml.XmlMarshaler;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.XMLContext;
import org.xml.sax.InputSource;

/**
 * <p>Castor-based (http://www.castor.org) implementation</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public abstract class CastorXmlMarshaler extends MarshalerSupport
implements XmlMarshaler {
	
	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(
			CastorXmlMarshaler.class);

	/**
	 * Castor context instance
	 */
	private XMLContext xmlCastorContext;
	
	/**
	 * Default constructor
	 */
	public CastorXmlMarshaler() {
		
		// create the castor context...
		xmlCastorContext = new XMLContext();
		
		// configure this marshaler...
		configure();
	}
	
	/**
	 * <p>Castor mapping files on the classpath can be loaded via this
	 * method</p>
	 * 
	 * @param pFileName
	 */
	public void loadClasspathMapping(String pFileName) {
		
		Mapping mapping;
		
		try {
			mapping = xmlCastorContext.createMapping();
		
			// load the mappings...
			mapping.loadMapping(new InputSource(Thread.currentThread().
					getContextClassLoader().getResourceAsStream(pFileName)));
			// log it...
			logger.debug("castor mapping file: [" + pFileName + "] loaded " + 
				"successfully from the classpath");			
		
			// add the mapping to the context...
			xmlCastorContext.addMapping(mapping);
						
		} catch (Exception any) {
			logger.error(any);
			throw new RuntimeException(any);
		}		
	}
	
	@Override
	public Command unmarshalRequest(String pRequestXml) {
		
		Command command;
		
		// perform the unmarshal...
		command = (Command)unmarshalGeneric(pRequestXml);
		logger.info("unmarshalled into command object of type: [" + 
				command.getClass().getName() + "]");
			
		// return the unmarshal result...
		return command;
	}
	
	@Override
	public Object unmarshalCommandResult(String pResultXml) {
		
		Object resultObj;
		
		// perform the unmarshal...
		resultObj = unmarshalGeneric(pResultXml);
		logger.debug("unmarshalled into object of type: [" + resultObj.getClass().
				getName() + "]");
			
		// return the unmarshal result...
		return resultObj;
	}

	@Override
	public String marshalCommandResult(Object pObject) {		
		return marshalGeneric(pObject);
	}
	
	@Override
	public String marshalCommand(Command pCommand) {
		return marshalGeneric(pCommand);
	}
	
	/**
	 * <p>All un-marshalling code is consolidated within this method</p>
	 * 
	 * @param pXml
	 * @return
	 */
	private Object unmarshalGeneric(String pXml) {
		
		Object unmarshalResult;
		Unmarshaller unmarshaller;
		
		try {			
			// create Castor unmarshaller instance...
			unmarshaller = xmlCastorContext.createUnmarshaller();
			
			// perform the unmarshal...
			unmarshalResult = unmarshaller.unmarshal(new InputSource(
					IOUtils.toInputStream(pXml)));
			
			// return the unmarshal result...
			return unmarshalResult;
		} catch (Exception any) {
			logger.error(any);
			throw new RuntimeException(any);
		} 
	}
	
	/**
	 * <p>Marshaling code consolidated within this method</p>
	 * 
	 * @param pObj
	 * @return
	 */
	private String marshalGeneric(Object pObj) {
		
		Marshaller marshaller;
		StringWriter marshalResult;
		
		try {			
			// create Castor marshaller instance...
			marshaller = xmlCastorContext.createMarshaller();
			
			// create SringWriter to hold marshal result and inject it into
			// the marshaller instance...
			marshalResult = new StringWriter();
			marshaller.setWriter(marshalResult);
			marshaller.setEncoding("UTF-8");
			marshaller.setMarshalAsDocument(false);
			marshaller.setSuppressNamespaces(false);
			
			// do the marshal...
			marshaller.marshal(pObj);
						
			// return the marshal result...
			return marshalResult.getBuffer().toString();
				
		} catch (Exception any) {
			logger.error(any);
			throw new RuntimeException(any);
		}
	}
}
