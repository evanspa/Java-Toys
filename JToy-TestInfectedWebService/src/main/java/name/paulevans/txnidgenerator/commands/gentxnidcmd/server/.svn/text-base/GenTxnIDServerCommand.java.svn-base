package name.paulevans.txnidgenerator.commands.gentxnidcmd.server;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmdSupport;
import name.paulevans.txnidgenerator.common.InvalidUseCaseOrChannelException;
import name.paulevans.txnidgenerator.common.TxnIdentifier;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Concrete client-side command object for the generate transaction
 * identifier command.</p>
 * 
 * @author Paul R Evans
 *
 */
public class GenTxnIDServerCommand extends GenTxnIDCmdSupport {
	
	/**
	 * Contains use case-to-character mappings
	 */
	private static final Map<String,String> USE_CASES = 
		new HashMap<String,String>();
	
	/**
	 * Contains channel-to-character mappings
	 */
	private static final Map<String,String> CHANNELS = 
		new HashMap<String,String>();
	
	static {
		// populate the use cases map...
		USE_CASES.put("Calculate Order Sub-total", "C");
		USE_CASES.put("Place Order", 			   "P");
		USE_CASES.put("Load Catalog", 			   "L");
		
		// populate the channels map...
		CHANNELS.put("Web",	  "W");
		CHANNELS.put("IVR",	  "I");
		CHANNELS.put("Call Center", "C");
	}
	
	/**
	 * <p>Helper used to get char value from one of the maps.  If the supplied
	 * key is not found, then the char with the key 'Non-specified' will be
	 * returned the supplied map.</p>
	 * 
	 * @return character value from supplied map based on the supplied key.
	 */
	private final String getChar(final Map<String,String> pMap, 
			final String pKey) {
		
		if (StringUtils.isBlank(pKey) || !pMap.containsKey(pKey)) {
			return null;
		}
		return pMap.get(pKey);
	}
	
	@Override
	public Object execute() {
		
		StringBuilder txnIdentifierStr;
		String useCaseChar;
		String channelChar;
		
		// check if use case and channel are valid and throw exception if
		// necessary...
		useCaseChar = getChar(USE_CASES, getUseCase());
		channelChar = getChar(CHANNELS, getChannel());
		if (useCaseChar == null && channelChar == null) {
			throw new InvalidUseCaseOrChannelException("invalid use case: [" + 
				getUseCase() + "] and invalid channel: [" + getChannel() + "]");
		}
		if (useCaseChar == null) {
			throw new InvalidUseCaseOrChannelException("invalid use case: [" + 
					getUseCase() + "]");
		}
		if (channelChar == null) {
			throw new InvalidUseCaseOrChannelException("invalid channel: [" + 
					getChannel() + "]");
		}
		
		// create the string builder...
		txnIdentifierStr = new StringBuilder();
		
		// add character indicating the use case...
		txnIdentifierStr.append(useCaseChar);
		
		// add character indicating the channel...
		txnIdentifierStr.append(channelChar);
		
		// append a random UUID value...
		txnIdentifierStr.append(UUID.randomUUID().toString());
		
		// return it!
		return new TxnIdentifier(txnIdentifierStr.toString());
	}
}
