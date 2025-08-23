package botPrototype;

public class Student {
	// Name of the student.
	private String name;
	// Telegram nickname of the student.
	private String telegramNick;
	// Phone number of the student.
	private String phoneNumber;
	// How much the studen has paid.
	private int payment;
	// Course of the student
	private Course course;
	
	// Constructor to initialize a student.
	public Student(String name, String telegramNick, String phoneNumber, int payment, Course course) {
		this.name = name;
		this.telegramNick = telegramNick;
		this.phoneNumber = phoneNumber;
		this.payment = payment;
		this.course = course;
	}


//----- SETERS AND GETTERS ------ 
	
	// 1) For name:
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	// 2) For telegramNick:
	public void setTelegramNick(String telegramNick) {
		this.telegramNick = telegramNick;
	}
	
	public String getTelegramNick() {
		return this.telegramNick;
	}
	// 3) For phoneNumber:
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	// 4) For payment:
	public void addPayment(int payment) {
		this.payment += payment; // (there is an option to add negative payment, since no validation exists)
	}
	
	public int getPayment() {
		return this.payment;
	}
	// 5) For course:
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public Course getCourse() {
		return this.course;
	}
	
	@Override
	public String toString() {
		// The pattern is %[flags][width]specifier. - is left allignment. 20 - number of chars. s - Stirng type.
		String formatStr = "%-20s %-20s %-15s %10s";
		return String.format(formatStr, this.name, this.telegramNick, this.phoneNumber, this.payment);
	}
}
