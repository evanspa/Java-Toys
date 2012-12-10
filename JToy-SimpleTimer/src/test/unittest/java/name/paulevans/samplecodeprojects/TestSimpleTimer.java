package name.paulevans.samplecodeprojects;

import junit.framework.TestCase;

/**
 * <p>Test case for {@link name.paulevans.samplecodeprojects.SimpleTimer}
 * 
 * @author Paul R Evans
 * @version $Id$
 *
 */
public class TestSimpleTimer extends TestCase {
	
	/**
	 * The instance to test with
	 */
	private SimpleTimer simpleTimer;

	@Override
	protected void setUp() {
		
		// create the instance to test with...
		simpleTimer = new SimpleTimer();
	}
	
	/**
	 * <p>Test for {@link name.paulevans.samplecodeprojects.SimpleTimer#actionPerformed(java.awt.event.ActionEvent)}</p>
	 */
	public void testActionPerformed() {

		// sanity check (duh!)...
		assertNotNull(simpleTimer);
		
		// umm - that's all I got...there's nothing to really unit test with
		// the target method...it's contract documentation doesn't indicate
		// any sort of testable behavior...
	}
}