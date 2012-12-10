package unittest.castor_configuration;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.client.GenTxnIDClientCommand;



/**
 * <p>Test to ensure correctness of the Castor mapping configuration file:
 * src/main/config/castor/commands/gentxnidcmd/castor-GenTxnIDClientCmd.xml</p>
 * 
 * @author Paul R Evans
 *
 */
public class TestCastorConfig_GenTxnIDClientCmd extends 
CastorConfig_GenTxnIDCmd {

	@Override
	public GenTxnIDCmd newCommand() {
		return new GenTxnIDClientCommand();
	}

	@Override
	public Object unmarshalCmdFromClasspathResource() {
		return unmarshalFromClasspathResource("clienttxngeneratorcmd-1.xml");
	}
}
