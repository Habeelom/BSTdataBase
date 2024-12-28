
package assignment3;

import java.util.*;

public class COSC311Driver {

	public static void main(String[] args) {

		DataBase d = new DataBase();
		int response;
		Scanner keyboard = new Scanner(System.in);

		do {
			System.out.println(" 1 Add a new student");
			System.out.println(" 2 Delete a student");
			System.out.println(" 3 Find a student by ID");
			System.out.println(" 4 List students by ID increasing");
			System.out.println(" 5 List students by first name increasing");
			System.out.println(" 6 List students by last name increasing");
			System.out.println(" 7 List students by ID decreasing");
			System.out.println(" 8 List students by first name decreasing");
			System.out.println(" 9 List students by last name decreasing");
			System.out.println(" ");
			System.out.println(" 0 End");

			response = keyboard.nextInt();

			switch (response) {
			case 1:
				d.addIt();
				break;
			case 2:
				d.deleteIt();
				break;
			case 3:
				d.findIt();
				break;
			case 4:
				d.ListByIDAscending();
				break;
			case 5:
				d.ListByFirstAscending();
				break;
			case 6:
				d.ListByLastAscending();
				break;
			case 7:
				d.ListByIDDescending();
				break;
			case 8:
				d.ListByFirstDescending();
				break;
			case 9:
				d.ListByLastDescending();
				break;
			default:
			}

		} while (response != 0);
	}
}
