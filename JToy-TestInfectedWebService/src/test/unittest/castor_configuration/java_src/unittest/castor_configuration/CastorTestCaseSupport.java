package unittest.castor_configuration;
import java.io.StringWriter;

import name.paulevans.testutils.XMLTestUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.XMLContext;
import org.springframework.util.ResourceUtils;
import org.xml.sax.InputSource;


public abstract class CastorTestCaseSupport {
	
	static {
		XMLTestUtils.createNSMappingsForXMLUnitRuntime();
	}
	
	/**
	 * Castor context instance
	 */
	private XMLContext xmlCastorContext;
	
	/**
	 * <p>Loads the Castor mapping configuration from the file with the
	 * given name on the classpath.</p>
	 * 
	 * <p>After this call, subclasses can safely retrieve the Castor
	 * context via {@link #getCastorContext()}</p>
	 * 
	 * @param pCastorMappingFilenames name of the Castor mapping configuration
	 * file names
	 */
	public void configureCastorMapping(String... pCastorMappingFilenames) {

		Mapping mapping;
		
		// create the castor context...
		xmlCastorContext = new XMLContext();

		try {
			mapping = new Mapping();
		
			// load the mappings...
			for (String mappingFile : pCastorMappingFilenames) {
				mapping.loadMapping(new InputSource(FileUtils.openInputStream(
						ResourceUtils.getFile("classpath:" + 
								mappingFile))));
			}					
		
			// add the mapping to the context...
			xmlCastorContext.addMapping(mapping);
			
		} catch (Exception any) {
			// re-throw as unchecked exception...
			throw new RuntimeException(any);
		}	
	}
	
	/**
	 * <p>Convenience method that will unmarshal the contents of the
	 * XML instance (found on the classpath) into an object and return it</p>
	 * 
	 * @param pClasspathResource the name of the XML file on the classpath
	 * @return
	 */
	public final Object unmarshalFromClasspathResource(String pClasspathResource) {
		
		try {
			return xmlCastorContext.createUnmarshaller().unmarshal(new InputSource(
				FileUtils.openInputStream(ResourceUtils.getFile(
						"classpath:" + pClasspathResource))));
		} catch (Exception any) {
			throw new RuntimeException(any);
		}		
	}
	
	/**
	 * <p>Convenience method that will marshal the inputed object and return
	 * the resulting XML as a sax input source object</p>
	 * 
	 * @param pObject the object to marshal into XML
	 * @return
	 */
	public final org.xml.sax.InputSource marshal(Object pObject) {
		
		Marshaller marshaller;
		StringWriter marshalResult;
		
		marshaller = xmlCastorContext.createMarshaller();
				
		try {
		
			// create SringWriter to hold marshal result and inject it into
			// the marshaller instance...
			marshalResult = new StringWriter();
			marshaller.setWriter(marshalResult);
			marshaller.setEncoding("UTF-8");
			marshaller.setMarshalAsDocument(false);
			marshaller.setSuppressNamespaces(false);

			// do the marshal...
			marshaller.marshal(pObject);

			// return the marshal result as a sax input source...
			return new org.xml.sax.InputSource(IOUtils.toInputStream(
					marshalResult.getBuffer().toString()));
		} catch (Exception any) {
			throw new RuntimeException(any);
		}
	}
}
