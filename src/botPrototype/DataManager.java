package botPrototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
	
	//private Path studentsListPathString = Path.of("programData/studentsList.txt");
	private File studentsListFilePath = new File("programData/studentsList.txt");
	
	/**
	 * List to store student objects in memory.
	 */
	private List<Student> studentsList = new ArrayList<>();
	
	/**
	 * Loads student objects into memory after starting the program by calling helper methods.
	 * @return List of Student objects.
	 */
	public List<Student> loadStudents() {
		// 1. Call helper method to read Students data from the file.
		List<String> linesToParse = readStudentsFromFile();
		
		// 2. Parse lines to create Student objects.
		// TO-DO:
		return null;
	}
	
	/**
	 *  Reads the studentsList.txt file, 
	 * and creates student objects. Then adds them to the studentsList ArrayList.
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
}
