package botPrototype;

import java.time.Instant;
import java.util.ArrayList;

public class Course {
	/**
	 * Instance time in epoch of course creation.
	 */
	private Instant courseCreated;
	
	/**
	 * Is course active or paused.
	 */
	private boolean isActive;
	/**
	 * Index of current week.
	 */
	private int currentWeekIndex;
	
	/**
	 * List to store all the weeks of the course.
	 */
	private ArrayList<AbstractWeek> weekList;
	
	
	/**
	 * Constructor for the Course object.
	 */
	public Course() {
		this.courseCreated = Instant.now();
		this.isActive = true;
		this.currentWeekIndex = 0;
		this.weekList = addWeeks();
	}
	
// --- END OF FIELDS ---
	
	
	
	
	public String dateStarted() {
		return courseCreated.toString(); // FOR TESTING.
		 // TO-DO: вернуть dateStarted в форме строки.
	}
}
