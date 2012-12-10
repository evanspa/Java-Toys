package name.paulevans.samplecodeprojects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.apache.log4j.Logger;

/**
 * <p>Simple program to demonstrate the usage of the javax.swing.Timer</p>
 * 
 * <p>This program creates a timer, starts it, and simply writes the value
 * of a counter variable ({@link #counter}) to the logger ({@link #logger}).
 * </p>
 * 
 * <p>The program will terminate after
 * {@link #NUM_TIMER_EVENTS_BEFORE_TERMINATION}
 * number of timer events.</p>
 *
 * @author Paul R Evans
 * @version $id$
 */
public class SimpleTimer implements ActionListener {

	/**
	 * Logger instance
	 */
	private static final Logger logger = Logger.getLogger(SimpleTimer.class);

	/**
	 * Stores the frequency of timer of events (in seconds)
	 */
	private static final int TIMER_EVENT_FREQUENCY = 3;
	
	/**
	 * The number of timer events that will be processed before the application
	 * terminates itself
	 */
	private static final int NUM_TIMER_EVENTS_BEFORE_TERMINATION = 10;

	/**
	 * The timer instance
	 */
	private Timer timer;

	/**
	 * Maintains a count of the timer events
	 */
	private int counter;

	/**
	 * Default constructor
	 */
	public SimpleTimer() {

		// create the timer...
		timer = new Timer(TIMER_EVENT_FREQUENCY * 1000, this);
		
		// initialize counter...
		counter = 0;

		/*
		 * Start the timer - this actually causes 2 threads to start under the
		 * covers:
		 * 
		 * (1) AWT-Windows daemon thread
		 * (2) TimerQueue daemon thread
		 * 
		 * Because both of these threads are of type 'daemon,' we need to do
		 * something extra to ensure the JVM doesn't shutdown...
		 */
		timer.start();
	}

	/**
	 * <p>Timer event handler call back</p>
	 *
	 * @param pEvent Represents the timer event
	 */
	public void actionPerformed(ActionEvent pEvent) {

		// increment the counter...
		counter++;

		// log the counter variable's value...
		logger.info(counter + " ");

		// after 10 events, notify any threads waiting on the current
		// instance...
		if (counter == NUM_TIMER_EVENTS_BEFORE_TERMINATION) {
			synchronized (this) {
				notifyAll();
			}
		}
	}

	/**
	 * <p>Application entry point</p>
	 *
	 * @param pArgs command-line arguments
	 */
	public static void main(String pArgs[]) throws Exception {

		// local declarations...
		SimpleTimer simpleTimer;

		// construct the simpler timer...
		simpleTimer = new SimpleTimer();

		// causes the 'main' thread to go into a wait state so the JVM doesn't 
		// immediately terminate (the JVM will terminate when all non-daemon
		// threads are stopped; the 'main' thread is of the non-daemon
		// variety...
		synchronized (simpleTimer) {
			simpleTimer.wait();
		}
	}
}