package integrationtest.clientcmd_to_servercmd.marshalingsoapws.in.glassfish3x;



import integrationtest.clientcmd_to_servercmd.GenTxnIDClientCmdAbstractIntegrationTest;
import name.paulevans.testutils.IntegrationTestUtils;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand;

import org.apache.log4j.Logger;
import org.codehaus.cargo.container.internal.RunnableContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Concrete end-to-end integration test with client and server commands.  
 * This test specifically tests correct integration in which the client
 * is configured with a marshaling soap web service delegate.</p>
 * 
 * <p>The web service will be deployed in a Glassfish version 3x JEE
 * application server.</p>
 * 
 * @author Paul R Evans
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-clientCmd_with_marshalingSoapWsDelegate.xml"})
public class TestGenTxnIDClientCmd_GlassFish_301B22
extends GenTxnIDClientCmdAbstractIntegrationTest {
	
	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(
		TestGenTxnIDClientCmd_GlassFish_301B22.class);
		
    /**
     * <p>Client command object as configured in the Spring client configuration
     * file</p>
     */
    @Autowired private GenTxnIDClientCommand genTxnIdentifierCmd;
    
    /**
     * Cargo container object that represents the container in which our
     * web service WAR will be deployed-to.
     */
    private static RunnableContainer container;
    
    /**
     * Need to start the JEE container and deploy the web service (WAR file)
     */
    @BeforeClass public static void startContainerAndDeployWs() {
    
    	// start the container and deploy web service WAR file...
    	container = IntegrationTestUtils.startJEEContainerAndDeployWar(
    			"glassfish3x", 
    			"C:/JEEContainers/GlassFish/GlassFish-3.0.1-B22", 
    			IntegrationTestUtils.getWarFileLocationFromEnv(), 
    			"cargo.servlet.port=8080,cargo.remote.username=admin,cargo.remote.password=admin,cargo.glassfish.domain.name=domain1");
    }
    
    /**
     * Used to stop the container
     */
    @AfterClass public static void stopContainer() {
    	
    	// stop the container...
    	container.stop();
    	
    	logger.info("container stopped successfully");
    }

	@Override
	public GenTxnIDClientCommand getClientCommand() {
		
		// return client command instance...
		return genTxnIdentifierCmd;
	}
}
