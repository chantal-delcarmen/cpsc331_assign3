package hashtable;

import java.util.LinkedList;

public class StudentList {
	private LinkedList<Student> list = new LinkedList<>();

	public void addStudent(Student newStudent) {	
		
		if(!list.contains(newStudent)) {
			list.add(newStudent);
		}
	}
	
	public Student getStudent(int index) {
		Student retrievedStudent = list.get(index);
		return retrievedStudent;
	}
	
	public boolean contains(int ID) {
		boolean result = false;
		
		if(list.contains()) {
			result = true;
		}
		return result;
	}
}
