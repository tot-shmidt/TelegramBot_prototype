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
	 * Number of current week.
	 */
	private int currentWeek;
	
	/**
	 * List to store all the weeks of the course.
	 */
	private ArrayList<Week> weekList;
	/**
	 * Constructor for the Course object.
	 */
	public Course() {
		courseCreated = Instant.now();
		isActive = true;
		currentWeek = 1;
	}
	
	
	
	
	
	public String dateStarted() {
		return courseCreated.toString(); // FOR TESTING.
		 // TO-DO: вернуть dateStarted в форме строки.
	}
}
