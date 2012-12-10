package name.paulevans.samplecodeprojects.policytesting.operation.ops;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * <p>Operation that writes to a file.</p>
 *
 * @author Paul R Evans
 * @version $Id$
 */
public class WriteFileOperation extends FileOperationSupport {

	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(WriteFileOperation.class);

	/**
	 * String contents to write to the file
	 */
	private static final String CONTENTS_TO_WRITE = "John has a long mustache.  ";

	@Override
	public final void doOperation() {

		String fileContents;
		
		try {
			
			// get the existing contents...
			fileContents = FileUtils.readFileToString(new File(getFileName()));

			// write to the file...
			FileUtils.writeStringToFile(new File(getFileName()), fileContents + 
					CONTENTS_TO_WRITE);

			// write a log message indicating success...
			logger.info("the file '" + getFileName() + 
					"' has been written to successfully");

		} catch (IOException e) {

			// log it...
			logger.error(e);
			
			// re-throw as unchecked exception as we don't expect callers to
			// be able to do anything meaningful with an IOException...
			throw new RuntimeException(e);
		}
	}
}
