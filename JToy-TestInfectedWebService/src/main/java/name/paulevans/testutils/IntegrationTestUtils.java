package name.paulevans.testutils;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.cargo.container.ContainerType;
import org.codehaus.cargo.container.InstalledLocalContainer;
import org.codehaus.cargo.container.configuration.ConfigurationType;
import org.codehaus.cargo.container.configuration.LocalConfiguration;
import org.codehaus.cargo.container.deployable.WAR;
import org.codehaus.cargo.container.internal.RunnableContainer;
import org.codehaus.cargo.generic.DefaultContainerFactory;
import org.codehaus.cargo.generic.configuration.DefaultConfigurationFactory;

/**
 * <p></p>
 * 
 * @author Paul R Evans
 *
 */
public class IntegrationTestUtils {
	
	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(
			IntegrationTestUtils.class);
	
	/**
	 * The name of the environment property in which the value is the location
	 * and name of the WAR file to be deployed (i.e. the WAR file that
	 * encapsulates our transaction id generator service) as part of an
	 * integration test
	 */
	private static final String CARGO_WARFILELOCATION_ENVPROPNAME = 
		"integrationtest.warfilelocation";
	
	/**
	 * The name of the environment property in which the value is the location
	 * of the base folder that Cargo will write temporary files to when
	 * starting/stopping containers.
	 */
	private static final String CARGO_CONFIG_TARGET_BASE_FOLDER = 
		"integrationtest.cargoconfigtargetbasefolder";
	
	/**
	 * <p>Returns the location/name of the WAR file (i.e. the WAR file that
	 * would need to be deployed as part of an integration test).</p>
	 * 
	 * @return the location/name of the WAR file
	 */
	public static final String getWarFileLocationFromEnv() {
		return System.getProperty(CARGO_WARFILELOCATION_ENVPROPNAME);
	}
	
	/**
	 * <p>Returns the base folder in which Cargo will use to write temporary
	 * configuration files as part of the processing of starting and stopping
	 * JEE containers.</p>
	 * 
	 * @return
	 */
	private static final String getCargoConfigBaseTargetFolderFromEnv() {
		return System.getProperty(CARGO_CONFIG_TARGET_BASE_FOLDER);
	}
	
	/**
	 * <p>Utility method that will start a JEE container using Cargo (from
	 * Codehaus), and will deploy a WAR file (the location/name of the WAR is
	 * passed as a parameter).</p>
	 * 
	 * <p>See the Cargo Codehaus web site for more details regarding
	 * container support and container IDs:
	 * http://cargo.codehaus.org/</p>
	 * 
	 * <p>The pContainerProperties parameter allows an arbitrary number of
	 * property name/value pairs to be supplied to Cargo.  This string
	 * parameter should be formatted as follows:
	 * 
	 * propname1=propvalue1,propname2=propvalue2,propname3=propvalue3
	 * </p>
	 * 
	 * @param pContainerId the Cargo container ID
	 * @param pContainerInstallHomeFolder the installation/home folder of the
	 * JEE container to be started
	 * @param pWarFile the location/name of the WAR file to be deployed
	 * @param pContainerProperties arbitrary set of name/value properties
	 *  
	 * @return the Cargo container instance
	 */
	public static RunnableContainer startJEEContainerAndDeployWar(
			String pContainerId,
			String pContainerInstallHomeFolder,
			String pWarFile,
			String pContainerProperties) {

		InstalledLocalContainer container;
		LocalConfiguration configuration;
		String cargoConfigFolder;
		
		// build the string that is the cargo config target folder...
		cargoConfigFolder = getCargoConfigBaseTargetFolderFromEnv() + 
			IOUtils.DIR_SEPARATOR + pContainerId;

		// create container configuration...
		configuration = (LocalConfiguration) new DefaultConfigurationFactory().
		createConfiguration(
				pContainerId, 
				ContainerType.INSTALLED, 
				ConfigurationType.STANDALONE,
				cargoConfigFolder);

		logger.info("successfully created cargo configuration instance; " +
				"container Id: [" + pContainerId + "], " +
				"container type: [" + ContainerType.INSTALLED + "], " + 
				"configuration type: [" + ConfigurationType.STANDALONE + "], " +
				"configuration folder: [" + cargoConfigFolder + "]");

		// set configuration properties from the environment...
		setConfigurationPropertiesFromEnv(configuration, pContainerProperties);

		// create the container...
		container = (InstalledLocalContainer) new DefaultContainerFactory().
			createContainer(pContainerId, 
				ContainerType.INSTALLED, 
				configuration);

		logger.info("successfully created container instance from the " +
		"configuration");

		// define the installation/home folder of the local container...
		container.setHome(pContainerInstallHomeFolder);

		logger.info("container installation home folder set to: [" + 
				pContainerInstallHomeFolder + "]");

		// deploy the web service WAR...
		configuration.addDeployable(new WAR(pWarFile));

		logger.info("war file deployed to container; war file used: [" + 
				pWarFile + "]");

		// start the container...
		container.start();

		logger.info("container started successfully");

		// return the container...
		return container;
	}

	/**
	 * <p>Convenience method that will pull from the environment the supplied
	 * set of name/value configuration properties that need to be set on the
	 * supplied configuration object.</p>
	 * 
	 * <p>This method expects an environment variable to be present with the
	 * name: {@link #CARGO_CONFIGPROPS_ENVPROPNAME}.</p>
	 * 
	 * <p>The value of this environment property should be of the form:
	 * 		propname1=propvalue1,propname2=propvalue2,propname3=propvalue3
	 * </p>
	 * 
	 * <p>This method will take care of parsing this string into individual
	 * properties that will be set onto the configuration object.</p>
	 * 
	 * @param pConfiguration the cargo configuration instance
	 */
	private static final void setConfigurationPropertiesFromEnv(
			final LocalConfiguration pConfiguration,
			final String pConfigProperties) {

		String configNVPairs[];
		String configNVPairVals[];

		// split it to give us an array of strings (the delimeter is the ','
		// characther); each string in this array is a single name/value 
		// pair (delimeted with a '=') which we'll have to split in just a
		// moment...
		configNVPairs = pConfigProperties.split(",");

		// iterate over each name/value pair - for each pair we need to split
		// based on the '=' character - the result will be an array with 2
		// elements; the property name and the property value; these values
		// will then be set on the cargo configuration object supplied to this
		// method...
		if (configNVPairs != null) {
			for (String configNVPair : configNVPairs) {

				// split to get a 2-size array containing the property name and
				// property value strings...
				configNVPairVals = configNVPair.split("=");

				// finally set the property on the cargo configuration...
				pConfiguration.setProperty(configNVPairVals[0], 
						configNVPairVals[1]);

				logger.info("Configuration property set: [" + 
						configNVPairVals[0] + "] with value: [" + 
						configNVPairVals[1] + "]");
			}
		}
	}	
}
