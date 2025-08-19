package botPrototype;

import java.time.Instant;
import java.util.ArrayList;

public abstract class AbstractWeek {
	/**
	 * When the week has started.
	 */
	protected Instant startTime;
	/**
	 * Keeps Lesson objects.
	 */
	protected ArrayList<Lesson> lessons;
	
	/**
	 * Set the startTime. Only set the start time if it hasn't been set before.
	 */
	public void startWeek() {
		if (this.startTime == null) {
			this.startTime = Instant.now();
		}
	}
	
	// --- Getters and Setters ---
	public Instant getStartTime() {
		return startTime;
	}
	
	public ArrayList<Lesson> getLessons() {
		return lessons;
	}
}
