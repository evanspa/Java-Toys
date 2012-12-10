package name.paulevans.sampleprojects.command;

/**
 * <p>Mechanism to execute commands and handle any sort of exception one might
 * throw.  Any exception caught is simply re-thrown as a runtime exception.</p>
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class CommandExecutor {
	
	/**
	 * <p>Executes the command object and returns the result of the 
	 * command's {@link name.paulevans.sampleprojects.command.Command#execute()}</p>
	 * 
	 * @param pCommand command object
	 * 
	 * @return the result of the 
	 * command's {@link name.paulevans.sampleprojects.command.Command#execute()}
	 */
	public Object executeCommand(Command pCommand) {
		try {
			return pCommand.execute();
		} catch (Throwable any) {
			
			// just re-throw as a runtime exception...
			throw new RuntimeException(any);
		}
	}
}
