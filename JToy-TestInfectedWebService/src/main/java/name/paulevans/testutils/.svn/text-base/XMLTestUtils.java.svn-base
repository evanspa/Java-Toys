package name.paulevans.testutils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.SimpleNamespaceContext;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.jaxp13.Validator;
import org.junit.Assert;
import org.springframework.util.ResourceUtils;

/**
 * <p>Helper class that provides some convenience methods for testing of
 * XML-based artifacts (e.g., asserting that an XML instance is valid against
 * a schema - a schema that can span across multiple .XSD files).</p>
 * 
 * @author Paul R Evans
 *
 */
public class XMLTestUtils {
	
	/**
	 * <p>No reason to instantiate this class</p>
	 */
	private XMLTestUtils () { }
	
	/**
	 * <p>Helper method that asserts the specified XML instance (as a string)
	 * is valid against the specified XML schema definition (as 1 or many
	 * XSD schema definition files found on the classpath).</p>
	 * 
	 * @param pXml the XML to validate
	 * @param pSchemaFileNames the XML schema definition(s) used to validate
	 * the specified XML
	 */
	public static void assertValidInstance(String pXml, 
			String... pSchemaFileNames) {
		
		Validator jaxpValidator;
		StreamSource xmlAsSource;
		@SuppressWarnings("rawtypes") List validationErrors;
		
		// create a new validator...
		jaxpValidator = newValidator(pSchemaFileNames);
		
		// get the inputed XML string as a stream source...
		xmlAsSource = new StreamSource(IOUtils.toInputStream(pXml));
		
		try {
			// assert the XML is valid...		
			Assert.assertTrue(jaxpValidator.isInstanceValid(xmlAsSource));
		
		} catch (AssertionError assertionError) {
			
			// lets try to get a clue as to what is wrong...
			validationErrors = jaxpValidator.getInstanceErrors(xmlAsSource);
			
			System.out.println("validationErrors null?: " + (validationErrors == null));
			if (validationErrors != null) {
				System.out.println("num validation errors: " + validationErrors.size());
			}
				
			// just get each error and throw it to standard out...
			for (Object err : validationErrors) {
				System.out.println(err);
			}
				
			// re-throw so test suite shows as failing...
			throw assertionError;
		}		
	}
	
	/**
	 * <p>Helper method that asserts the syntactical correctness of the
	 * schema file(s) specified (according to W3C XML Schema specification).  
	 * The specified file(s) are expected to be on the classpath.</p>
	 * 
	 * @param pSchemaFileNames the name(s) of the schema file(s) on the
	 * classpath
	 */
	public static void assertValidSchema(String... pSchemaFileNames) {
		
		Validator jaxpValidator;		
		@SuppressWarnings("rawtypes") List schemaErrors;
		
		// create a new validator...
		jaxpValidator = newValidator(pSchemaFileNames);
			
		try {
				
			// assert the schema is syntactically valid W3C XML Schema
			// definition...
			Assert.assertTrue(jaxpValidator.isSchemaValid());
				
		} catch (AssertionError assertionError) {
				
			// lets try to get a clue as to what is wrong...
			schemaErrors = jaxpValidator.getSchemaErrors();
				
			// just get each error and throw it to standard out...
			for (Object err : schemaErrors) {
				System.out.println(err);
			}
				
			// re-throw so test suite shows as failing...
			throw assertionError;
		}
	}
	
	/**
	 * <p>Creates a validator that will be associated with the schema
	 * definition(s) provided.</p>
	 * 
	 * @param pSchemaFileNames the name(s) of XML schema files to be on
	 * the classpath
	 * 
	 * @return validator instance associated with the supplied schema
	 * definitions
	 */
	private static Validator newValidator(String... pSchemaFileNames) {
		
		Validator jaxpValidator;		
		String schemaContents;
		
		// create the validator object...
		jaxpValidator = new Validator();
		
		try {
			
			// associate the specified schema(s) with the validator...
			for (String schemaFileName : pSchemaFileNames) {
				
				// get the schema contents as a string...
				schemaContents = FileUtils.readFileToString(ResourceUtils.
						getFile("classpath:" + schemaFileName));
				
				// add the schema source...
				jaxpValidator.addSchemaSource(new StreamSource(IOUtils.
						toInputStream(schemaContents)));
			}
			
			// return it!
			return jaxpValidator;
			
		} catch (Exception any) {
			
			// old habit...
			throw new RuntimeException(any);
		}
	}
	
	/**
	 * <p>Creates namespace mappings required by XMLUnit's runtime in order
	 * to process xpath expressions correctly.  The following namespaces
	 * are mapped:
	 *   <ul>
	 *     <li>Prefix: [TxnIDTypes], Namespace URI: [http://name.paulevans/samples/TransactionIdentifier-types]</li>
	 *     <li>Prefix: [TxnIdGenCmds], Namespace URI: [http://name.paulevans/samples/TransactionIdentifier-commands]</li>
	 *   </ul>
	 * </p>
	 */
	public static final void createNSMappingsForXMLUnitRuntime() {
		
		Map<String,String> namespaces;
		
		namespaces = new HashMap<String,String>();
	
		// create namespace mapping for our types and commands schemas...
		namespaces.put("TxnIDTypes", 
				"http://name.paulevans/samples/TransactionIdentifier-types");
		namespaces.put("TxnIdGenCmds", 
				"http://name.paulevans/samples/TransactionIdentifier-commands");
		
		// set the namespace map...
		XMLUnit.setXpathNamespaceContext(new SimpleNamespaceContext(
				namespaces));
	}
}
