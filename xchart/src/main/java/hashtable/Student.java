package hashtable;

public class Student {
	private String name = "";
	private int studentID = 0;
	
	
	public Student(int studentID, String name) {
		this.setStudentID(studentID);
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
}
