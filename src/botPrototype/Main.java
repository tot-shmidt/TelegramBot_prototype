package botPrototype;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

	// Objects for taking input and providing output.
	private static Scanner scnr = new Scanner(System.in);
	private static PrintStream stream = System.out;
	
	// Object to handle reading/writing operations.
	private static DataManager dataManager = new DataManager();
	
	
	public static void main(String[] args) {
		/**
		String workingDir = System.getProperty("user.dir");
	    System.out.println("My Current Working Directory is: " + workingDir);
	    */
		
		// STARTUP: Load all the date from the files into memory.
		List<Student> studentsList = dataManager.loadStudents();
		
		String adminInput;
		
		do {
			// Print available actions.
			displayMenu();
		
			// Take input from admin.
			adminInput = getAdminInput();
		
			// Perform selected action.
			processAdminInput(adminInput);
				
		// If admin provided wrong or not q for input.	
		} while (!adminInput.equals("q"));
		
		// Escaped do-while loop. Now SHUTDOWN: Save the final data from memory back to the files.
		//dataManager.saveData();
		stream.println("Data was saved. Good-bye!");
	}
	
    /**
     * Displays the main menu to the user.
     */
	private static void displayMenu() {
		stream.println("--- MENU ---\n");
		stream.println("[1] - Список всех студентов");
		stream.println("[2] - Действия со студентом");
		stream.println("[3] - Создать нового студента");
		stream.println("[q] - Выйти");
	}
	
	/**
	 * Gets the next menu choice from the admin.
	 */
	private static String getAdminInput() {
		stream.println("Выбор: ");
		return scnr.nextLine();
	}
	
	/**
	 * Handling admin input in the main menu.
	 * @param adminInput should be 1,2,3 or q.
	 */
	private static void processAdminInput(String adminInput) {
		//Option 1: SHOW ALL STUDENTS.
		if (adminInput.equals("1")) {
			
			
			
		} else if (adminInput.equals("2")) {
		
		// Option 3: CREATE NEW STUDENT.
		} else if (adminInput.equals("3")) {
		// Get frist and last name.
			stream.print("Имя Фамилия: ");
			String name = scnr.nextLine();
			
		// Get telegram nickname.
			stream.print("Телеграм ник: ");
			String telegramNick = scnr.nextLine();
			
		// Get phone number.
			stream.print("Номер телефона: ");
			String phoneNumber = scnr.nextLine();
			
			// Get payment amount.
			stream.print("Сумма платежа: ");
			int payment = scnr.nextInt();
			
		// Create course now or skip for later.
			String courseChoiceInput = scnr.nextLine();
			
			// Choose when to create a course: now or then.
			do {
				stream.print("Создать курс [s]ейчас или [p]озже: ");
				courseChoiceInput = scnr.nextLine();
			} while (!courseChoiceInput.equals("s") && !courseChoiceInput.equals("p"));
			
			// Create a course object.
			if (courseChoiceInput.equals("s")) {
				//Course course = createNewCourse();
			} else if (courseChoiceInput.equals("p")) {
				Course course = null;
			}
			
		// Assign student id.
			// TO-DO:
			
		// Wrong menu input was provided.	
		} else if (!adminInput.equals("q")) {
			stream.println("\"" + adminInput + "\"" + " is a wrong input! Type in [1],[2],[3] or [q]:\n");
		} 
	}
}
























