package hashtable;

import java.util.LinkedList;

public class HashTable {
	private final int LEN = 8;
	private LinkedList<Student>[] hashTable = new LinkedList[10];
	
	/**
	 * @param list
	 * @return boolean (list == null)
	 */
	public boolean isEmpty(LinkedList<Student> list) {
		return (list == null);
	}
	
	/**
	 * Precondition: ID is an integer and name is a string composed of alphanumeric characters
	 * 
	 * Postcondition: If the hash table does not contain a Student of this name, then a 		
	 * Student with attributes ID and name is added to the appropriate linked list in the
	 * hash table. If a Student of this name is already in the hash table, then this Student
	 * has their name updated to the inputted name.
	 * 
	 * @param ID
	 * @param name
	 */
	public void insert(int ID, String name) {
		int index = hashValue(ID);
		LinkedList<Student> list = hashTable[index];
		
		// If the list at the hash table index is empty, create a new list
		// and new Student -> add this student to the list then put it at
		// the index
		if(isEmpty(list)) {
			list = new LinkedList<>();
			Student newStudent = new Student(ID, name);
			list.add(newStudent);
			hashTable[index] = list;
			System.out.println("At index " + index + " - " + newStudent.getStudentID() + ":" + newStudent.getName() + " added");
		} else if(!search(ID)) {
			// If list is not empty and hash table does not contain Student,
			// add student to list and position at the index in hash table
			Student newStudent = new Student(ID, name);
			list.add(newStudent);
			hashTable[index] = list;
			System.out.println("At index " + index + " - " + newStudent.getStudentID() + ":" + newStudent.getName() + " added");
		} else { 
			// Else, student is already in the hash table
			// Update name to inputed name
			update(ID, name);			
		}
	}
	
	/**
	 * @param ID
	 * @param name
	 */
	public void update(int ID, String name) {
		int index = hashValue(ID);
		LinkedList<Student> list = hashTable[index];
		
		for(Student s : list) {
			if(s.getStudentID() == ID) {
				s.setName(name);
				System.out.println(name + "'s name updated in the database");
			}
		}
	}


	/**
	 * Precondition: ID is an integer.	 * 
	 * Postcondition: If the hash table contains a student by this ID,
	 * then return true
	 * 
	 * @param ID
	 * @return isFound
	 */
	public boolean search(int ID) {
		boolean isFound = false;
		int index = hashValue(ID);
		LinkedList<Student> list = hashTable[index];
		
		int key = ID;
		
		for(Student s : list) {
			if(s.getStudentID() == key) {
				isFound = true;
			}
		}		
		return isFound;
	}
	
	/**
	 * Precondition: ID is an integer.	 * 
	 * Postcondition: If the hash table contains a student with this ID, return the corresponding
	 * name. Otherwise, print a message indicating that no student with this ID
	 * was found in the hash table.
	 * 
	 * @param ID
	 * @return name
	 */
	public String retrieve(int ID) {
		int index = hashValue(ID);
		LinkedList<Student> list = hashTable[index];
		String name = "";
		
		if(search(ID)) {
			for(Student s : list) {
				if(s.getStudentID() == ID) {
					name = s.getName();	
				}
			}
		} else {
			System.out.println("\nNo student with this ID was found!\nCannot retrieve student record");
		}
		return name;
	}
	
	/**
	 * Precondition: ID is an integer.
	 * Postcondition: ID modulo LEN is returned.
	 * 
	 * @param ID
	 * @return hashVal
	 */
	public int hashValue(int ID) {
		int hashVal = ID % LEN;
		return hashVal;
	}
	
	/**
	 * Precondition: ID is an integer.
	 * Postcondition: If the hash table contains a Student with this ID, this Student is
	 * removed from the hash table. Otherwise, a message is printed indicating that no
	 * Student with this name was found in the hash table.
	 * 
	 * @param ID
	 */
	public void delete(int ID) {
		int index = hashValue(ID);
		LinkedList<Student> list = hashTable[index];
		boolean isDeleted = false;
		
		for(Student s : list) {
			if(s.getStudentID() == ID) {
				list.remove(s);
				isDeleted = true;
				System.out.println("\nStudent record with ID " + ID + " was deleted");
			}
		}
		
		if(isDeleted == false) {
			System.out.println("\nStudent with ID " + ID + " not found,\ncannot delete student record");
		}
	}
	
	/**
	 * Precondition: arr is an array of LinkedList of Student.
	 * Postcondition: A string is returned consisting of
	 * • One line per entry of the array, including empty linked lists (it is acceptable to
	 * have an extra newline at the end)
	 * • Each line takes the form
	 * ArrayIndex: [FirstStudentID:FirstStudentName, SecondStudentID:SecondStudentName,
	 * . . . , LastStudentID:LastStudentName]
	 * 
	 * @return string
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		int index = 0;
		str.append("\n\n");
		
		for(LinkedList<Student> list : hashTable) {
			if(isEmpty(list)) {
				str.append(index + ": [ ");				
			} else {
				str.append(index + ": [");				
				for(Student s : list) {
					str.append(s.getStudentID() + ":" + s.getName() + ", ");
				}
				str.setLength(str.length() - 2);
			}
			str.append("]\n");
			index++;
		}
		return str.toString();
	}	
	
}
	
	

