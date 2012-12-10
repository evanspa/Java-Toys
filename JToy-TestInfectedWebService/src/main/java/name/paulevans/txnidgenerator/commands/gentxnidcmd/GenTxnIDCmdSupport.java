package name.paulevans.txnidgenerator.commands.gentxnidcmd;


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
 *     <li>if (channel == 'Call Center'), char = 'C'</li>
 *   </ul>
 * </p> 
 *  
 * @author Paul R Evans
 *
 */
public abstract class GenTxnIDCmdSupport implements GenTxnIDCmd {
	
	/**
	 * Stores the use case
	 */
	private String useCase;

	/**
	 * Stores the channel
	 */
	private String channel;

	@Override
	public final void setUseCase(final String pUseCase) {
		useCase = pUseCase;
	}
	
	@Override
	public final void setChannel(final String pChannel) {
		channel = pChannel;
	}
	
	@Override
	public final String getUseCase() {
		return useCase;
	}

	@Override
	public final String getChannel() {
		return channel;
	}	
}
