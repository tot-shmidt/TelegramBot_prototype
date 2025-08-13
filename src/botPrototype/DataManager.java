package botPrototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
	
	//private Path studentsListPathString = Path.of("programData/studentsList.txt");
	/**
	 * File object of the path of the .txt file that keep students data.
	 */
	private File studentsListFilePath = new File("programData/studentsList.txt");
	// "E:\\Programming\\Java\\Eclipse\\TelegramBot_prototype_V.1\\programData\\studentsList.txt"
	/**
	 * List to store student objects in memory.
	 */
	private List<Student> studentsList = new ArrayList<>();
	
	/**
	 * Symbol to separate entries in fiels.
	 */
	private String delimiter = ";";
	
// ---------------------- Methods ----------------------------------------------------	
	
	/**
	 * Loads student objects into memory after starting the program by calling helper methods.
	 * @return List of Student objects.
	 */
	public void loadStudents() {
		// 1. Call helper method to read Students data from the file.
		List<String> linesToParse = readStudentsFromFile();
		
		// 2. Parse lines to create Student objects.
		studentsList = studentsDataParser((ArrayList<String>) linesToParse);
	}
	
	/**
	 * Reads the studentsList.txt file, and creates student objects. Then adds them to the studentsList ArrayList.
	 * I am implementing old, low-level way of interacting with files and reading from it.
	 * Modern way includes using Path and Files classes but is more "high-level".
	 * @return List with Student strings, which needed to be parsed into objects.
	 */
	private List<String> readStudentsFromFile() {
		// Will hold lines we read from the file.
		List<String> lines = new ArrayList<>();
		
		/* FileInputStream is meant for reading streams of raw bytes from a file. InputStream is an abstract 
		 * class that acts as a blueprint, while FileInputStream is a concrete class that you can actually use.*/
		FileInputStream fileInputStream = null;
		
		/* An InputStreamReader is a bridge from byte streams to character streams:
		 * It reads bytes and decodes them into characters using a specified charset.*/
		InputStreamReader inputStreamReader = null;
		
		/* Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
		 * The buffer size may be specified. The default is large enough for most purposes. */
		BufferedReader bufferedReader = null;
		
		try {
			// 1. Connect to the file with a byte stream
			fileInputStream = new FileInputStream(studentsListFilePath);
			
			// 2. Bridge from bytes to characters, explicitly using UTF-8 encoding
			inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
			
			// 3. BufferedReader takes Reader as parameter. InputStreamReader is a sub-class of Reader.
			bufferedReader = new BufferedReader(inputStreamReader);
			
			// 4. Read the file until the end.
			String currentLine;
			currentLine = bufferedReader.readLine();
			
			while (currentLine != null) {
				lines.add(currentLine);
				currentLine = bufferedReader.readLine();
			}
			
		} catch (IOException e1) {
			// err is a standart output stream, like "in" and "out".
			System.err.println("Error reading the file: " + e1.getMessage());
		} finally {
			// 5.  Manually close every stream in reverse order of creation.
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e2) {
				System.err.println("Error closing the streams: " + e2.getMessage());
			}
		}
		
		return lines;
	}
	
	/**
	 * Takes list of student objects, creates a data string for each student,
	 * and saves all the strings into list of strings. After, writes every string to the
	 * file in a manuall "low-level" way for educational purpuses.
	 */
	public void writeStudentsToFile() {
		// StringBuilder object for students data.	
		StringBuilder sb = new StringBuilder();
		// List to store all the strings created.
		ArrayList<String> studentStringsList = new ArrayList<>();
		
		// Looping over each student, creating a string, adding to the ArrayList.
		for (Student student : studentsList) {
			// Getting all the data.
			sb.append(student.getName() + delimiter);
			sb.append(student.getTelegramNick() + delimiter);
			sb.append(student.getPhoneNumber() + delimiter);
			sb.append(student.getPayment() + delimiter);
			sb.append(student.getCourse().dateStarted());
			
			// Create a string from the final sb object.
			String studentDataString = sb.toString();
			
			// Add received string to the ArrayList.
			studentStringsList.add(studentDataString);
			
			// Emtying StringBuilder for the next iteration.
			sb.setLength(0);
		}
		
		// Declaring streams for writing to the file.
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		PrintWriter printWriter = null;
		
		// Chaining streams and writing to the file.
		try {
			// 1. Connect to the file with a byte stream.
			fileOutputStream = new FileOutputStream(studentsListFilePath);
			
			// 2. Bridge from characters to bytes using UTF-8 encoding.
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
			
			// 3. Add buffering and convenient print methods.
			printWriter = new PrintWriter(outputStreamWriter);
			
			// 4. Write each line to the file.
			for (String line : studentStringsList) {
				printWriter.println(line);
			}
		} catch (IOException e1) {
			System.err.println("Error writing to the file: " + e1.getMessage());
		} finally {
			try {
				// 5. Manually close every stream in reverse order of creation.
				if (printWriter != null) {
					printWriter.close();
				}
				if (outputStreamWriter != null) {
					outputStreamWriter.close();
				}
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
				
			} catch (IOException e2) {
				System.err.println("Error closing the streams: " + e2.getMessage());
			}
		}	
	}
	
	/**
	 * Creates student objects from the data in strings.
	 * @param linesToParse - stores data strings of each student.
	 * @return
	 */
	private ArrayList<Student> studentsDataParser(ArrayList<String> linesToParse) {
		// ArrayList for storing all the Student objects.
		ArrayList<Student> studentObjects = new ArrayList<>();
		
		// Recreating student objects.
		for (String studentString : linesToParse) {
			String[] arr = studentString.split(delimiter);
			
			if (arr.length > 0) {
				String name = arr[0];
				String telegramNick = arr[1];
				String phoneNumber = arr[2];
				int payment = Integer.parseInt(arr[3]);
				String dateStarted = arr[4];
				
				// I create a student but Course object is bogus!!! I will have somehow also have files with Couse
				// Information of each student, and from these files I will have to recreate course objects.
				Student student = new Student(name, telegramNick, phoneNumber, payment, new Course());
				
				studentObjects.add(student);		
			}
		}
		
		return studentObjects;
	}
	
	/**
	 * Getter method for studentList
	 * @return list with student objects.
	 */
	public List<Student> getStudentList() {
		return studentsList;
	}
	
	/**
	 * Method to add students to the studentsList.
	 * @param student
	 */
	public void addStudent(Student student) {
		this.studentsList.add(student);
	}
}