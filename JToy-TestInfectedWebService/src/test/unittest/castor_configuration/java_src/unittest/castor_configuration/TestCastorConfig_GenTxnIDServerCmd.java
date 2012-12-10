package unittest.castor_configuration;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.GenTxnIDCmd;
import name.paulevans.txnidgenerator.commands.gentxnidcmd.server.GenTxnIDServerCommand;



/**
 * <p>Test to ensure correctness of the Castor mapping configuration file:
 * src/main/config/castor/commands/gentxnidcmd/castor-GenTxnIDServerCmd.xml</p>
 * 
 * @author Paul R Evans
 *
 */
public class TestCastorConfig_GenTxnIDServerCmd extends 
CastorConfig_GenTxnIDCmd {

	@Override
	public GenTxnIDCmd newCommand() {
		return new GenTxnIDServerCommand();
	}

	@Override
	public Object unmarshalCmdFromClasspathResource() {
		return unmarshalFromClasspathResource("servertxngeneratorcmd-1.xml");
	}
}
