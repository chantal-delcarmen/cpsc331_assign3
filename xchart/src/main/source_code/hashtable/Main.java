package hashtable;

/**
 * Main class for Problem 2, hashtable
 */
public class Main {
	public static void main(String[] args) {
		HashTable ht = new HashTable();
		
//		1. Insert the students with the following information: 
//			”20500120:Bob”, 
//			”20700200:Alice”,
//			”30100230:Cathy”, 
//			and ”20200156:Ali”.
		System.out.println("\n----------------------------------------\n1. Students inserted");
		ht.insert(20500120, "Bob");		
		ht.insert(20700200, "Alice");		
		ht.insert(30100230, "Cathy");		
		ht.insert(20200156, "Ali");
		
//		2. Call the ToString function to print the entire database.
		System.out.println("\n----------------------------------------\n2. Print database" + ht.toString());
		
//		3. Insert ”20500120:Bobby” to update Bob’s name.
		System.out.println("\n----------------------------------------\n3. Update Bobby's name");
		ht.insert(20500120, "Bobby");

//		4. Call the Search function for the ID ”20500120”
		System.out.println("\n----------------------------------------\n4. Searching for student with ID 20500120"
				+ "\nStudent found in database: " + ht.search(20500120));

//		5. Retrieve the value associated with the ID ”20700200”.
		System.out.println("\n----------------------------------------\n5. Retrieving student"
				+ "\nName associated with ID 20700200: " + ht.retrieve(20700200));

//		6. Remove the student with the ID ”20700200”.
		System.out.print("\n----------------------------------------\n6. Deleting student");
		ht.delete(20700200);

//		7. Again call the Remove function with the ID ”20700200”. (Error Handling: Should
//		print an appropriate message)
		System.out.print("\n----------------------------------------\n7. Deleting student with no record");
		ht.delete(20700200);

//		8. Now call the Retrieve function with the ID ”20700200”. (Error Handling: Should
//		print an appropriate message)
		System.out.print("\n----------------------------------------\n8. Retrieving student with no record");
		ht.retrieve(20700200);

//		9. Call the ToString function again to print the updated database.
		System.out.print("\n----------------------------------------\n9. Print database");
		System.out.println(ht.toString());		
	}

}
