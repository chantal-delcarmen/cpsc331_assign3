package hashtable;

public class SchoolDatabase {
	private StudentList sl = new StudentList();

	
	//	2. Search(int ID)
	//	Precondition: ID is an integer.
	//	Postcondition: If the hash table contains a student by this ID.
	//	then return true
	// Otherwise return false
	// -- Checks whether student is in database
	public boolean search(int iD) {
		if(sl.contains)
		return false;
	}
	
	//	3. Retrieve(int ID)
	//	Precondition: ID is an integer.
	//	Postcondition: If the hash table contains a student with this ID, return the corresponding
	//	name. Otherwise, print a message indicating that no student with this ID
	//	was found in the hash table.
	// -- Return corresponding name

	//	4. Insert(int ID, String name)
	//	Precondition: ID is an integer and name is a string composed of alphanumeric characters
	//	Postcondition: If the hash table does not contain a Student of this name, then a
	//	Student with attributes ID and name is added to the appropriate linked list in the
	//	hash table. If a Student of this name is already in the hash table, then this Student
	//	has their name updated to the inputted name.
	// -- Insert/update student's info in database
	
	public void insert(int ID, String name) {
		Student student = new Student(ID, name);
		
		if(!search(ID)) {
			// ID is not in the hashtable
			// Add student to hashtable
			sl.addStudent(student);
		} else { // Else ID is in the hashtable
			// Update student name to inputted name
			// TODO
		}
	}


	
	//	5. Delete(int ID)
	//	Precondition: ID is an integer.
	//	Postcondition: If the hash table contains a Student with this ID, this Student is
	//	removed from the hash table. Otherwise, a message is printed indicating that no
	//	Student with this name was found in the hash table.
	// -- Remove student from database
	
	//	6. toString()
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
	
	//	1. HashValue(int ID)
	//	Precondition: ID is an integer.
	//	Postcondition: ID modulo LEN is returned.
}
