package botPrototype;

import java.time.Duration;

public class Lesson {
	/**
	 * Content of the lesson.
	 */
	private final String content;
	/**
	 * When to send a lesson after the week starts.
	 */
	private final Duration sendAfter;
	/**
	 * Wether the lesson was already sent or not.
	 */
	private boolean isSent = false;
	
	/**
	 * Constructur for lesson object. We provide it with content of the lesson,
	 * and when to send this lesson after beginning of the week.
	 * @param content
	 * @param sendAfter
	 */
	public Lesson(String content, Duration sendAfter) {
		this.content = content;
		this.sendAfter = sendAfter;
	}
	
	// --- Getters and Setters ---
	/**
	 * Returns contents of the lesson.
	 * @return
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Returns when the lesson has to after start of the week.
	 * @return
	 */
	public Duration getSendAfter() {
		return sendAfter;
	}
	
	/**
	 * Returns wether the lesson was already sent or not.
	 * @return
	 */
	public boolean isSent() {
		return isSent;
	}
	
	/**
	 * Sets lesson's state to sent or not-sent position.
	 * @param sent
	 */
	public void setSent(boolean sent) {
		this.isSent = sent;
	}
}
