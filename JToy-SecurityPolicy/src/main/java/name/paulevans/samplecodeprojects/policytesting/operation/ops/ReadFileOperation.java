package name.paulevans.samplecodeprojects.policytesting.operation.ops;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * <p>Operation that reads the contents of the file.</p>
 *
 * @author Paul R Evans
 * @version $Id$
 */
public class ReadFileOperation extends FileOperationSupport {

	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(ReadFileOperation.class);

	@Override
	public final void doOperation() {

		// local variables...
		String fileContents;

		try {

			// read the contents of the file...
			fileContents = FileUtils.readFileToString(new File(getFileName()));

			// log the contents...
			logger.info("contents of '" + getFileName() + "': [" + 
					fileContents + "]");
			
		} catch (IOException e) {

			// log it...
			logger.error(e);
			
			// re-throw as unchecked exception as we don't expect callers to
			// be able to do anything meaningful with an IOException...
			throw new RuntimeException(e);
		}
	}
}
