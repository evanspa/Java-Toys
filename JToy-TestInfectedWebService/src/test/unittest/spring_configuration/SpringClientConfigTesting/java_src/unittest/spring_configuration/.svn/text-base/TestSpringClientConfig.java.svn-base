package unittest.spring_configuration;



import name.paulevans.distcmdfrmwk.CommandProcessingException;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand;
import name.paulevans.txnidgenerator.common.TxnIdentifier;
import net.javacrumbs.springws.test.simple.WsMockControl;
import net.javacrumbs.springws.test.simple.annotation.WsMockControlTestExecutionListener;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * <p>Test to ensure that our client Spring configuration is wired and
 * configured properly</p>
 * 
 * <p>We're going to be testing that request XML payloads are being created
 * properly from both a content and schema-validation perspective.</p>
 * 
 * <p>On the response side, we're going to make sure that pre-configured,
 * canned response XML payloads will be unmarshaled properly</p>
 * 
 * @author Paul R Evans
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-clientCmd_with_marshalingSoapWsDelegate.xml"})
@TestExecutionListeners({WsMockControlTestExecutionListener.class, 
	DependencyInjectionTestExecutionListener.class})
public class TestSpringClientConfig {

    /**
     * Spring-WS-test mock control object
     */
	@Autowired private WsMockControl mockControl;
    
    /**
     * <p>Client command object as configured in the Spring client configuration
     * file</p>
     */
    @Autowired private GenTxnIDClientCommand clientCommand;
	
	/**
	 * <p>Test to ensure proper behavior for all possible combinations
	 * of usecase and channel values on the client command object.</p>   
	 */
	@Test public void testValidRequestResponseCombinations()  {
		
		// the following 9 assertions are used to test all possible valid
		// usecase and channel combinations...
		assert_ExpectedRequest_ValidResponse(clientCommand, mockControl, 
				"Load Catalog", "IVR", "LI");
		assert_ExpectedRequest_ValidResponse(clientCommand, mockControl, 
				"Load Catalog", "Web", "LW");
		assert_ExpectedRequest_ValidResponse(clientCommand, mockControl, 
				"Load Catalog", "Call Center", "LC");
		assert_ExpectedRequest_ValidResponse(clientCommand, mockControl, 
				"Place Order", "IVR", "PI");
		assert_ExpectedRequest_ValidResponse(clientCommand, mockControl, 
				"Place Order", "Web", "PW");
		assert_ExpectedRequest_ValidResponse(clientCommand, mockControl, 
				"Place Order", "Call Center", "PC");
		assert_ExpectedRequest_ValidResponse(clientCommand, mockControl, 
				"Calculate Order Sub-total", "IVR", "CI");
		assert_ExpectedRequest_ValidResponse(clientCommand, mockControl, 
				"Calculate Order Sub-total", "Web", "CW");
		assert_ExpectedRequest_ValidResponse(clientCommand, mockControl, 
				"Calculate Order Sub-total", "Call Center", "CC");
	}
	
	/**
	 * <p>Test to ensure proper behavior when the soap ws delegate associated
	 * with the client command is configured with a mock web service template
	 * instance such that the web service template will return soap fault
	 * responses.  This test makes sure to ensure the proper exception
	 * is indeed thrown by the client command, and it contains the correct
	 * message text within it.</p>
	 */
	@Test public void testServerFault() {
	
		// ensure correctness when web service template is configured to
		// return a SERVER soap fault...
		assert_ExpectedRequest_FaultResponse(clientCommand, mockControl, 
				"Place Order", "Call Center", "SOAP-ENV:Server", 
				"test server fault");
		
		// ensure correctness when web service template is configured to
		// return a CLIENT soap fault...
		assert_ExpectedRequest_FaultResponse(clientCommand, mockControl, 
				"Place Orderr", "Call Center", "SOAP-ENV:Client", 
				"test client fault");
	}
	
	/**
	 * <p>Convenience method to make it easy to test the scenario in which
	 * the invocation of the web service results in a soap-fault response
	 * (client or server fault code).</p>
	 * 
	 * <p>This method will use the canned-response-fault.xml as the template
	 * XML for the response to be returned; the template can be passed the
	 * fault code and fault string values to use.</p>
	 * 
	 * @param pClientCommand client command instance
	 * @param pMockControl Spring-WS-test mock control instance
	 * @param pUseCase use case string
	 * @param pChannel channel string
	 * @param pFaultCode fault code to use
	 * @param pFaultString fault string to use
	 */
	private static final void assert_ExpectedRequest_FaultResponse(
			GenTxnIDClientCommand pClientCommand,
			WsMockControl pMockControl,
			String pUseCase, String pChannel, 
			String pFaultCode,
			String pFaultString) {
		
		// sanity check...
		Assert.assertNotNull(pClientCommand);

		pMockControl.
		useFreeMarkerTemplateProcessor().
			setTestContextAttribute("usecase", pUseCase).
			setTestContextAttribute("channel", pChannel).
			setTestContextAttribute("faultcode", pFaultCode).
			setTestContextAttribute("faultstring", pFaultString).
			expectRequest("expectedRequest_template.xml").
			returnResponse("cannedResponseFault_template.xml");

		// setup our command with some values...
		pClientCommand.setUseCase(pUseCase);
		pClientCommand.setChannel(pChannel);

		// execute the command and attempt to catch command processing
		// exception...the command processing exception caught is expected
		// to contain within its message the fault string...
		try {
			pClientCommand.execute();
			Assert.fail("Command processing exception should have been thrown");
			
		} catch (CommandProcessingException pCmdProcessingException) {
			
			// do some assertions...
			Assert.assertTrue(StringUtils.contains(ExceptionUtils.
					getRootCauseMessage(pCmdProcessingException), 
					pFaultString));
		}

		// instruct the mock to do its verification...
		pMockControl.verify();
	}
	
	/**
	 * <p>Convenience method to make it easy to test combinations of use case
	 * and channel values and the expected 2-character prefix of generated
	 * transaction identifier values.</p>
	 * 
	 * <p>The expected request and to-be generated response leverages
	 * FreeMarker so we don't have to have multiple one-off copies of
	 * expected-request and to-be response XML files; we can just maintain
	 * a single expected-request XML file (expected-request.xml) and a single
	 * to-be response XML file (canned-response.xml).</p>
	 * 
	 * <p>The request generated by the Spring configuration will be compared
	 * against expected-request.xml for equality and the generated request
	 * will be check for validity against the TransactionIdentifier-commands.xsd
	 * schema.</p>
	 * 
	 * @param pClientCommand transaction ID generator client command object
	 * @param pMockControl Spring-WS-test mock control instance
	 * @param pUseCase the use case value to use
	 * @param pChannel the channel value to use
	 * @param pExpectedTxnIdentifierPrefix the expected 2-character prefix of
	 * the transaction identifier value returned by the execution of the
	 * client command
	 */
	private static final void assert_ExpectedRequest_ValidResponse(
			GenTxnIDClientCommand pClientCommand,
			WsMockControl pMockControl,
			String pUseCase, String pChannel, 
			String pExpectedTxnIdentifierPrefix) {
		
		TxnIdentifier txnId;
		
		// sanity check...
		Assert.assertNotNull(pClientCommand);
		
		/*
		 * Here we are defining the behavior of the mock control.  This line of
		 * code does the following: it says that a request will be expected
		 * to be generated by Spring's web service template that matches the
		 * content in 'expected-request-1.xml' and will validate against the
		 * 'TransactionIdentifier-comamnds.xsd' schema.  The web service
		 * template instance defined in our spring-client-config.xml Spring
		 * configuration will be configured to return a response as defined in
		 * the 'valid-response-1.xml' file.
		 */
				
		// tell the mock control that the request generated by the Spring 
		// configuration will match the XML defined in 'expected-request-1.xml'
		// and will validate against our 'TransactionIdentifier-commands.xsd'
		// schema...
		pMockControl.
			setTestContextAttribute("usecase", pUseCase).
			setTestContextAttribute("channel", pChannel).
			useFreeMarkerTemplateProcessor().
			validateSchema("TransactionIdentifier-commands.xsd").
			expectRequest("expectedRequest_template.xml").
			returnResponse("cannedResponse_template.xml");
				
		// setup our command with some values...
		pClientCommand.setUseCase(pUseCase);
		pClientCommand.setChannel(pChannel);
		
		// execute the command and get back a transaction identifier object...
		txnId = (TxnIdentifier)pClientCommand.execute();
		
		// assert the txn identifier object is not null (sanity check)...
		Assert.assertNotNull(txnId);
		
		// asser that the first 2 characters in the txn identifier value match
		// our expected values of 'LI'...
		Assert.assertEquals(pExpectedTxnIdentifierPrefix, StringUtils.
				substring(txnId.getIdentifierValue(), 0, 2));
		
		// instruct the mock to do its verification...
		pMockControl.verify();
	}
}
