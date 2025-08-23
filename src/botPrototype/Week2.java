package botPrototype;

import java.time.Duration;
import java.util.ArrayList;

public class Week2 extends AbstractWeek {
	
	public Week2() {
		this.lessons = new ArrayList<>();
		
		//Hard-coded lesson content for this week:
		lessons.add(new Lesson("Lesson 2.1: To be", Duration.ofHours(24)));
		lessons.add(new Lesson("Lesson 2.2: Present Simple", Duration.ofHours(72)));
	}
}
