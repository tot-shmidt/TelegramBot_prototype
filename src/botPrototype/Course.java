package botPrototype;

import java.time.Duration;
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
	
	// --- END OF FIELDS ---
	/**
	 * Constructor for the Course object.
	 */
	public Course() {
		this.courseCreated = Instant.now();
		this.isActive = true;
		this.currentWeekIndex = 0;
		this.weekList = addWeeks();
		
		// Start the first week when the course is created.
		if (!this.weekList.isEmpty() ) {
			this.weekList.get(0).startWeek();
		}
	}
	
	/**
	 * a NEW constructor for recreating a course from a file.
	 */
	public Course(Instant courseCreated, int currentWeekIndex, boolean isActive, Instant currentWeekStart) {
		this.courseCreated = courseCreated;
		this.currentWeekIndex = currentWeekIndex;
		this.isActive = isActive;
		this.weekList = addWeeks();
		
		// Set the startTime for the current week.
		weekList.get(currentWeekIndex).setStartTime(currentWeekStart);
	}
	
	/**
	 * Add  weeks to the weekList of the course.
	 * @return
	 */
	public ArrayList<AbstractWeek> addWeeks() {
		// Create temporary ArrayList which will be returned filled with weeks.
		ArrayList<AbstractWeek> weeks = new ArrayList<>();
		
		// Add desired weeks into the course.
		weeks.add(new Week1());
		weeks.add(new Week2());
		
		return weeks;
	}
	
	/**
	 * 
	 * @return
	 */
	public void checkAndSendLessons() {
		// Checks wether the couse is in valid state.
		if (!isActive) {
			System.out.println("Course is not active");
			return;
		}
		if (currentWeekIndex >= weekList.size()) {
			System.out.println("Couse has finished");
			return;
		}
		
		// Get current week object and start time of that week.
		AbstractWeek currentWeek = weekList.get(currentWeekIndex);
		Instant weekStartTime = currentWeek.getStartTime();
		
		// 
		if (weekStartTime == null) {
			System.out.println("Error: Current week has not been started");
		}
		
		// Time past since the start of the week until now.
		Duration elapsedTime = Duration.between(weekStartTime, Instant.now());
		
		
		// Check for lessons to send.
		for (Lesson lesson : currentWeek.getLessons()) {
			if (!lesson.isSent() & elapsedTime.compareTo(lesson.getSendAfter()) >= 0 ) {
				System.out.println("--> SENDING TO TELEGRAM: " + lesson.getContent());
				lesson.setSent(true);
			}
		}
		
		// Check if week is over.
		if (elapsedTime.compareTo(Duration.ofDays(7)) >= 0) {
			System.out.println("Week " + (currentWeekIndex + 1) + " is over.");
			currentWeekIndex++; 
			
			// Still have some weeks ahead.
			if (currentWeekIndex < weekList.size()) {
				System.out.println("Starting next wekk...");
				weekList.get(currentWeekIndex).startWeek();
			// No weeks any more. Course is finished.	
			} else {
				System.out.println("Congratulations! The course is complete.");
				isActive = false;
			}
		}
	}
	
	/**
	 * Gives time in unix-time when the course was created.
	 * @return
	 */
	public Instant getCourseCreated() {
		return courseCreated;
	}
	
	public int getCurrentWeekIndex() {
		return currentWeekIndex;
	}
	
	/**
	 * Gives Week object which is currently active.
	 * @return
	 */
	public AbstractWeek getCurrentWeek() {
		if (currentWeekIndex < weekList.size()) {
			return weekList.get(currentWeekIndex);
		}
		return null;
	}
	
	/**
	 * Returns wether course is active or not.
	 */
	public boolean isActive() {
		return isActive;
	}
	
	public String dateStarted() {
		return courseCreated.toString(); // FOR TESTING.
		 // TO-DO: вернуть dateStarted в форме строки.
	}
}





































