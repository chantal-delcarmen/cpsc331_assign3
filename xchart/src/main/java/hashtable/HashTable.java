package hashtable;

import java.util.LinkedList;
import java.util.Set;

public class HashTable {
	private final int LEN = 8;
	private LinkedList<Student>[] hashTable = new LinkedList[10];
//	private int index;
//	private int size = 0;

	
//	public int getSize() {
//		return size;
//	}
	
	public boolean isEmpty(LinkedList<Student> list) {
//		return (size == 0);
		return (list == null);
	}
	
	//	Insert(int ID, String name)
	//	Precondition: ID is an integer and name is a string composed of alphanumeric characters
	//	Postcondition: If the hash table does not contain a Student of this name, then a
	//	Student with attributes ID and name is added to the appropriate linked list in the
	//	hash table. If a Student of this name is already in the hash table, then this Student
	//	has their name updated to the inputted name.
	// -- Insert/update student's info in database
	public void insert(int ID, String name) {
		int index = hashValue(ID);
		LinkedList<Student> list = hashTable[index];
//		LinkedList<Student> list = null;
		
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
	
	public void update(int ID, String name) {
		int index = hashValue(ID);
		LinkedList<Student> list = hashTable[index];
		
		for(Student s : list) {
			if(s.getStudentID() == ID) {
				s.setName(name);
			}
		}
	}

	//	Search(int ID)
	//	Precondition: ID is an integer.
	//	Postcondition: If the hash table contains a student by this ID.
	//	then return true
	// Otherwise return false
	// -- Checks whether student is in database
	public boolean search(int ID) {
		boolean result = false;
		int index = hashValue(ID);
		LinkedList<Student> list = hashTable[index];
		
		int key = ID;
		
		for(Student s : list) {
			if(s.getStudentID() == key) {
				result = true;
			}
		}		
		return result;
	}
	
	//	Retrieve(int ID)
	//	Precondition: ID is an integer.
	//	Postcondition: If the hash table contains a student with this ID, return the corresponding
	//	name. Otherwise, print a message indicating that no student with this ID
	//	was found in the hash table.
	// -- Return corresponding name
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
			System.out.println("No student with this ID was found!");
		}
		return name;
	}
	
	//	HashValue(int ID)
	//	Precondition: ID is an integer.
	//	Postcondition: ID modulo LEN is returned.
	public int hashValue(int ID) {
		int hashVal = ID % LEN;
		System.out.println("Hashvalue: " + hashVal);
		return hashVal;
	}
	
	//	Delete(int ID)
	//	Precondition: ID is an integer.
	//	Postcondition: If the hash table contains a Student with this ID, this Student is
	//	removed from the hash table. Otherwise, a message is printed indicating that no
	//	Student with this name was found in the hash table.
	// -- Remove student from database
	public void delete(int ID) {
		int index = hashValue(ID);
		LinkedList<Student> list = hashTable[index];
		
		for(Student s : list) {
			if(s.getStudentID() == ID) {
				list.remove(s);
			}
		}
	}
	
	//	toString()
	//	Precondition: arr is an array of LinkedList of Student.
	//	Postcondition: A string is returned consisting of
	//	• One line per entry of the array, including empty linked lists (it is acceptable to
	//	have an extra newline at the end)
	//	• Each line takes the form
	//	ArrayIndex: [FirstStudentID:FirstStudentName, SecondStudentID:SecondStudentName,
	//	. . . , LastStudentID:LastStudentName]
	//	Example: If 20500120:Bob, 20700200:Alice, 30100230:Cathy, 20200156:Ali are
	//	inserted (in that order), toString() produces the following:
	//	5
	//	0: [20500120:Bob, 20700200:Alice]
	//	1: [ ]
	//	2: [ ]
	//	3: [ ]
	//	4: [20200156:Ali]
	//	5: [ ]
	//	6: [30100230:Cathy]
	//	7: [ ]
	// -- Print the whole database
	public String toString() {
		String str = "";
		int index = 0;
		
		for(LinkedList<Student> list : hashTable) {
			if(isEmpty(list)) {
				str += "[ ]";
			} else {
				for(Student s : list) {
					str += index + ": [" + s.getStudentID() + ":" + s.getName() + "]\n";
				}
			}
		}
		return str;
	}	
	
}
	
	

