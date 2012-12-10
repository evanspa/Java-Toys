package name.paulevans.samplecodeprojects.policytesting;

import java.util.Map;

import name.paulevans.samplecodeprojects.policytesting.operation.Operation;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Simple application that performs "sensitive" operations such as:
 * <ul>
 * 	<li>Reading and writing to a file</li>
 * 	<li>Creating a network socket and writing to it</li>
 * </ul>
 * </p>
 * 
 * <p>The purpose is then to use Java's security policy to control the ability
 * of this program to perform the sensitive operations.  The point here is to
 * learn about and play with the security policy capabilities of Java.</p>
 *
 * @author Paul R Evans
 * @version $Id$
 */
public final class SimpleApp {

	/**
	 * Name of the Spring definitions file.
	 */
	private static final String SPRING_APP_CONTEXT_FILE = "beans.xml";

	/**
	 * Spring bean factory object.
	 */
	private static final BeanFactory beanFactory;

	static {

		// initialize the Spring bean factory...
		beanFactory = new ClassPathXmlApplicationContext(new String[] {
						SPRING_APP_CONTEXT_FILE});
	}

	/**
	 * The types/operations mapping object.
	 */
	private static final TypeOperationMapping TYPE_OPS_MAPPING = 
		(TypeOperationMapping) beanFactory.getBean("typeOpsMap");

	/**
	 * Logger instance.
	 */
	private static final Logger logger = Logger.getLogger(SimpleApp.class);

	/**
	 * <p>Main application entry point.</p>
	 *
	 * @param pArgs the command-line arguments
	 */
	public static void main(final String... pArgs) {

		// local variables...
		CommandLineParser parser;
		CommandLine cmd;
		HelpFormatter helpFormatter;
		Options options;

		// initializations...
		parser = new PosixParser();
		helpFormatter = new HelpFormatter();
		options = createCmdLineOptions();

		// parse the command-line options...
		try {
			cmd = parser.parse(options, pArgs);

			// get the operation-type value and take the necesary action...
			evaluateAndTakeAction(cmd.getOptionValue("type"));

		} catch (ParseException e) {
			logger.error(e);
			System.out.println("An error occured parsing the command-line options");
			helpFormatter.printHelp("SimpleApp", options);
		}
	}

	/**
	 * <p>Evaluates and executes the operation associated with the specified
	 * type.</p>
	 * 
	 * @param pOperationType the operation type
	 */
	private static void evaluateAndTakeAction(final String pOperationType) {

		// local variables...
		Operation operation;

		// log it...
		logger.debug("operation type-value: [" + pOperationType + "]");

		// get the operation instance from the types/ops map...
		operation = TYPE_OPS_MAPPING.getOperationClass(pOperationType);

		// invoke the operation and return its results...
		operation.doOperation();
	}

	/**
	 * <p>Defines the available command-line options for this application.</p>
	 *
	 * @return the command-line options supported by this application
	 */
	private static Options createCmdLineOptions() {

		// local variables...
		Options options;

		// create the options object...
		options = new Options();

		// create the appropriate options...
		options.addOption("type", true, "the type of operation to perform");

		// return it!
		return options;
	}
	
	/**
	 * <p>Private default constructor.</p>
	 */
	private SimpleApp() {
		// do nothing...
	}
}

/**
 * <p>Maps an operation type to an operation class.</p>
 */
class TypeOperationMapping {

	/**
	 * Map of operation types and operations classes.
	 */
	private Map<String, Operation> operations;

	/**
	 * <p>Constructs a new mapping instance.</p>
	 *
	 * @param pOperations the type/operations mapping
	 */
	public TypeOperationMapping(final Map<String, Operation> pOperations) {
		operations = pOperations;
	}

	/**
	 * <p>Returns the associated operations class given an operation type.</p>
	 *
	 * @param pType the association operation type
	 * @return the operations class associated with the type
	 */
	public final Operation getOperationClass(final String pType) {
		return operations.get(pType);
	}
}
