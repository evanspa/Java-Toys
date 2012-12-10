package name.paulevans.samplecodeprojects.util;

import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;

/**
 * <p>Contains various XML-related utility helper functions.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class XMLUtils {
	
	/**
	 * Static transformer factory
	 */
	private static TransformerFactory factory = TransformerFactory.newInstance();
	
	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(XMLUtils.class);
	
	/**
	 * <p>Convenience method to extract the XML string contents of the specified
	 * source object</p>
	 * 
	 * @param pSourceXml
	 * 
	 * @return returns the XML string contents of the specified source object
	 */
	public static final String toString(Source pSourceXml) {
		
		Transformer transformer;
		StringWriter transformHolder;
		Result transformResult;
		
		try {
			
			// create a transformer and do an identity transform...
			transformer = factory.newTransformer();
			transformHolder = new StringWriter();
			transformResult = new StreamResult(transformHolder);
			transformer.transform(pSourceXml, transformResult);
			
			// simply return the contents of the transform holder StringWriter...
			return transformHolder.getBuffer().toString();
			
		} catch (Exception any) {
			logger.error(any);
			throw new RuntimeException(any);
		}
	
	}

}
