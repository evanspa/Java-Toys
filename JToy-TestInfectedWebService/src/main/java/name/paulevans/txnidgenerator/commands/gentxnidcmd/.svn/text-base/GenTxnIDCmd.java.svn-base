package name.paulevans.txnidgenerator.commands.gentxnidcmd;

import name.paulevans.distcmdfrmwk.Command;

/**
 * <p>Defines a command for generating a transaction identifier.  The 
 * generated identifier will be in the form of a string, and the first
 * 2 characters are directly influenced by the configured use case
 * ({@link #setUseCase(String)}) and channel ({@link #setChannel(String)}).</p>
 * 
 * <p>The following rules determine the first character of the generated
 * identifier:
 *   <ul>
 *     <li>if (use case == 'Calculate Order Sub-total'), char = 'C'</li>
 *     <li>if (use case == 'Place Order'), char = 'P'</li>
 *     <li>if (use case == 'Load Catalog'), char = 'L'</li>
 *   </ul>
 * </p> 
 * 
 * <p>The following rules determine the second character of the generated
 * identifier:
 *   <ul>
 *     <li>if (channel == 'Web'), char = 'W'</li>
 *     <li>if (channel == 'IVR'), char = 'I'</li>
 *     <li>if (channel == 'Phone'), char = 'P'</li>
 *   </ul>
 * </p> 
 *  
 * @author Paul R Evans
 *
 */
public interface GenTxnIDCmd extends Command {

	/**
	 * <p>Generates a unique transaction identifier in the form of a string and
	 * returns it.  The first 2 characters of the generated identifier are based
	 * on the value of the use case ({@link #setUseCase(String)}) and
	 * channel ({@link #setChannel(String)}).</p>
	 * 
	 * @return the transaction identifier value as a string
	 */
	@Override
	Object execute();
	
	/**
	 * <p>Sets the channel</p>
	 * 
	 * @param pUseCase the new use case value
	 */
	void setUseCase(final String pUseCase);
	
	/**
	 * <p>Sets the channel</p>
	 * 
	 * @param pChannel the new channel value
	 */
	void setChannel(final String pChannel);
	
	/**
	 * <p>Returns the use case value</p>
	 * 
	 * @return the use case value
	 */
	String getUseCase();

	/**
	 * <p>Returns the channel value</p>
	 * 
	 * @return the channel value
	 */
	String getChannel();	
}
