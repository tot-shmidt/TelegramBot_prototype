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
		
		String workingDir = System.getProperty("user.dir");
	    System.out.println("My Current Working Directory is: " + workingDir);
	    
		
		// STARTUP: Load all the date from the files into memory.
		dataManager.loadStudents();
		
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
		dataManager.writeStudentsToFile();
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
		stream.print("Выбор: ");
		return scnr.nextLine();
	}
	
	/**
	 * Handling admin input in the main menu.
	 * @param adminInput should be 1,2,3 or q.
	 */
	private static void processAdminInput(String adminInput) {
	  //Option 1: SHOW ALL STUDENTS.
		if (adminInput.equals("1")) {
			for (Student student : dataManager.getStudentList()) {
				stream.println(student.toString());
			}
			
	  // Option 2: ????	
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
			int payment = 0; // default value;
			
			while (true) {
				stream.print("Сумма платежа: ");
				String inputLine = scnr.nextLine(); // Read the full line
				
				try {
					// Try to convert input string to an integer.
					payment = Integer.parseInt(inputLine);
					
					// If it succeds, break out of the loop.
					break;
				} catch (NumberFormatException e) {
					// If it fails, print an error and the loop will automatically repeat.
					stream.println("Неверный ввод. Пожалуйста, введите целое число.");
				}
			}
			
			// Choose when to create a course: now or then.
			String courseChoiceInput;
			do {
				stream.print("Создать курс [s]ейчас или [p]озже: ");
				courseChoiceInput = scnr.nextLine();
			} while (!courseChoiceInput.equals("s") && !courseChoiceInput.equals("p"));
			
			// Create a course object. TO-DO!!!
			Course course = null;
			
			if (courseChoiceInput.equals("s")) {
				//Course course = createNewCourse();
			} else if (courseChoiceInput.equals("p")) {
				course = new Course(); //FOR TESTING!!!
			}
			
		// Assign student id.
			// TO-DO:
			
			// 1. Create the new Student object with the collected data.
			Student newStudent = new Student(name, telegramNick, phoneNumber, payment, course);
			
			// 2. Add the new student to the list in DataManager.
			dataManager.addStudent(newStudent);
			
			// 3. Confirm that the student was created.
		    stream.println("Студент " + name + " успешно создан!\n");
			
		// Wrong menu input was provided.	
		} else if (!adminInput.equals("q")) {
			stream.println("\"" + adminInput + "\"" + " is a wrong input! Type in [1],[2],[3] or [q]:\n");
		} 
	}
}
