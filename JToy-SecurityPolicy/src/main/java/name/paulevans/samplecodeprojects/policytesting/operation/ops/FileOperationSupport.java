package name.paulevans.samplecodeprojects.policytesting.operation.ops;

import name.paulevans.samplecodeprojects.policytesting.operation.Operation;

/**
 * <p>Represents an abstract file operation</p>
 *
 * @author Paul R Evans
 * @version $Id$
 */
public abstract class FileOperationSupport implements Operation {

	/**
	 * The name of the file an operation would be performed against
	 */
	private String fileName;

	/**
	 * <p>Returns the name of the file</p>
	 * 
	 * @return the name of the file
	 */
	public final String getFileName() {
		return fileName;
	}

	/**
	 * <p>Sets the name of the file</p>
	 * 
	 * @param pFileName the name of file
	 */
	public final void setFileName(String pFileName) {
		fileName = pFileName;
	}
}
