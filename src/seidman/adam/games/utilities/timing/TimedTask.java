package seidman.adam.games.utilities.timing;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * Create a task to be implemented by a developer. It can be ran at a schedule
 * time or it can be scheduled to run periodically.
 * 
 * @author Adam Seidman
 *
 */
public abstract class TimedTask {

	private final long TIME;

	private boolean _nextTaskHasRan;
	private Timer _timer;

	/**
	 * Create a TimedTask.
	 * 
	 * @param seconds
	 *            A double- number of seconds before task.
	 */
	public TimedTask(double seconds) {
		TIME = (long) (seconds * 1000.0);
		_timer = new Timer();
		_nextTaskHasRan = false;
	}

	/**
	 * @return A double- the amount of seconds desired by the user.
	 */
	public final double getSeconds() {
		return ((double) TIME) / 1000.0;
	}

	/**
	 * @return A long- the converted amount of time the user desired into a
	 *         milliseconds.
	 */
	public final long getMillis() {
		return TIME;
	}
	
	/**
	 * Get the Task in Timer Task.
	 * 
	 * @return Desired task as java.util.TimerTask
	 */
	private TimerTask getTask() {
		final TimedTask tt = this;
		return new TimerTask() {
			public void run() {
				task();
				tt._nextTaskHasRan = true;
			}
		};
	}
	
	/**
	 * @return True if the user hasn't checked back since the last time the task
	 *         was called.
	 */
	public boolean hasNextTaskRan() {
		boolean ret = _nextTaskHasRan;
		_nextTaskHasRan = false;
		return ret;
	}
	
	/**
	 * Method ran for each task.
	 */
	public final void run() {
		task();
		_nextTaskHasRan = true;
	}


	/**
	 * Schedule the task once in the future.
	 */
	public void schedule() {
		new Timer().schedule(getTask(), TIME);
	}
	
	/**
	 * Put the task desired on repeat.
	 */
	public void start() {
		try {
			_timer.cancel();
		} catch (Exception e) {
			// Attempting to stop just in case, so this isn't a problem.
		}
		_timer = new Timer();
		try {
			_timer.schedule(getTask(), TIME, TIME);
		} catch (IllegalStateException e) {
			stop();
			start(); // Redo method if it was already running before.
		}
	}

	/**
	 * Stop task from repeatedly running.
	 */
	public void stop() {
		_timer.cancel();
	}
	
	/**
	 * Abstract task method to be implemented by the user. This is the first
	 * thing ran when the task is going.
	 */
	public abstract void task();

}
