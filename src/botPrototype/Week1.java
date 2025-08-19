package botPrototype;

import java.time.Duration;
import java.util.ArrayList;

public class Week1 extends AbstractWeek {
	
	public Week1() {
		this.lessons = new ArrayList<>();
		
		//Hard-coded lesson content for this week:
		lessons.add(new Lesson("Lesson 1.1: Introduction", Duration.ofHours(24)));
		lessons.add(new Lesson("Lesson 1.2: Introducing Yourself", Duration.ofHours(72)));
	}
}
