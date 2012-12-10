package name.paulevans.samplecodeprojects.command;

/**
 * <p>Generic command definition.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 */
public interface Command {
	
	/**
	 * <p>Executes this command</p>
	 * 
	 * @return the result of executing this command
	 */
	Object execute();

}
