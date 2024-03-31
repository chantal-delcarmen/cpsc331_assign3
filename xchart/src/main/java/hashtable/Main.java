package hashtable;

// Problem 2

/* 
Do the following steps in order:

1. Insert the students with the following information: 
”20500120:Bob”, 
”20700200:Alice”,
”30100230:Cathy”, 
and ”20200156:Ali”.

2. Call the ToString function to print the entire database.

3. Insert ”20500120:Bobby” to update Bob’s name.

4. Call the Search function for the ID ”20500120”

5. Retrieve the value associated with the ID ”20700200”.

6. Remove the student with the ID ”20700200”.

7. Again call the Remove function with the ID ”20700200”. (Error Handling: Should
print an appropriate message)

8. Now call the Retrieve function with the ID ”20700200”. (Error Handling: Should
print an appropriate message)

9. Call the ToString function again to print the updated database.
 */

public class Main {
	public static void main(String[] args) {
		HashTable ht = new HashTable();
		
		ht.insert(20500120, "Bob");		
		ht.insert(20700200, "Alice");		
		ht.insert(30100230, "Cathy");		
		ht.insert(20200156, "Ali");
		
		System.out.println(ht.toString());
		
	}

}
