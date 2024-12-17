package assignment3;

import java.io.*;
import java.util.Scanner;

//This is the database class which also contains the dataBaseArray. Here I am able to manage operations
//within the database including adding, removing, and finding records

public class DataBase {
	IndexBST IdBST, firstBST, lastBST;
	DataBaseRecord[] DataBaseArray;
	int size;
	Scanner scan = new Scanner(System.in);

// new database dataArray object to store array of DataBase records
	public DataBase() {
		DataBaseArray = new DataBaseRecord[100];
		size = 0;
		IdBST = new IndexBST();
		firstBST = new IndexBST();
		lastBST = new IndexBST();

//here I set up a file input scanner to read in all the data from my input file. My eclipse was bugged
// so for some reason when I imported the file it kept failing to read it, so I just set the file
//path directly on my computer. I read the data in line by line, then remove excess whitespace, and
//then split the first and last name and ID number. I then read create the IndexRecord with the information
//unless the ID is already present in the database	
		File data = new File("/Users/omarhabeel/Downloads/data3.txt");
		try (Scanner fileScanner = new Scanner(data)) {
			while (fileScanner.hasNextLine()) {
				String[] line = fileScanner.nextLine().trim().toLowerCase().split("\\s+");
				String last = line[0];
				String first = line[1];
				String ID = line[2];
				if (IdBST.search(ID) == -1) {
					addRecord(ID, first, last);
				} else {
					System.out.println("duplicate removed");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

//addRecord is used to update tree indexes and add a record to the database by creating a new object,
//then adding the record to the database. Then the record is added to the appropriate tree index.
	public void addRecord(String ID, String first, String last) {
		DataBaseRecord record = new DataBaseRecord(ID, first, last);
		addRecord(record);
		int where = this.size - 1;
		IdBST.addIndex(new IndexRecord(ID, where));
		firstBST.addIndex(new IndexRecord(first, where));
		lastBST.addIndex(new IndexRecord(last, where));
	}

//The user inputs are taken and inputted into appropriate variables. This only happens if the ID isn't
//in use already.  I then set them to lowercase so that later when comparisons happen case isn't taken 
//into consideration. I then call addRecord to add it to the database.
	public void addIt() {
		System.out.print("Enter ID: ");
		String ID = scan.nextLine();
		if (IdBST.search(ID) != -1) {
			System.out.println("ID already in use");
			return;
		}
		System.out.print("Enter first name: ");
		String first = scan.nextLine().toLowerCase();
		System.out.print("Enter last name: ");
		String last = scan.nextLine().toLowerCase();
		addRecord(ID, first, last);
	}

//pulls the ID that the user enters and searches for it in the idBST. If the record is found then
//it deletes the id and first and last names and decrements the size accordingly. Otherwise nothing
//happens and record not found is printed.
	public void deleteIt() {
		System.out.print("Enter ID: ");
		String ID = scan.nextLine();
		int where = IdBST.search(ID);
		if (where != -1) {
			IdBST.deleteIndex(where);
			firstBST.deleteIndex(where);
			lastBST.deleteIndex(where);
			System.out.println("Deleted");
			size--;
		} else {
			System.out.println("Record not found");
		}
	}

//pulls the ID that the user enters and searches for it in the idBST using the search method in the 
//indexBST class. if the record is found it is returned back to the user with the first and last name
	public void findIt() {
		System.out.print("Enter ID: ");
		String ID = scan.nextLine();
		int index = IdBST.search(ID);
		if (index == -1) {
			System.out.println("Record not found");
			return;
		}
		System.out.println(getRecord(index));
	}

//this method prints out the records and has two situations, one ascending and one descending, where
//traversal of the tree changes based on where the iterator is initialized and prints the previous,
//or next records accordingly
	private void printIt(boolean ascending, IndexBST bst) {
		if (ascending) {
			bst.iteratorInitFront();
			while (bst.hasNext()) {
				System.out.println(getRecord(bst.getNext()));
			}
		} else {
			bst.iteratorInitBack();
			while (bst.hasPrevious()) {
				System.out.println(getRecord(bst.getPrevious()));
			}
		}
	}

	public void ListByIDAscending() {
		printIt(true, IdBST);
	}

	public void ListByIDDescending() {
		printIt(false, IdBST);
	}

	public void ListByFirstAscending() {
		printIt(true, firstBST);
	}

	public void ListByFirstDescending() {
		printIt(false, firstBST);
	}

	public void ListByLastAscending() {
		printIt(true, lastBST);
	}

	public void ListByLastDescending() {
		printIt(false, lastBST);
	}
//These are for listing the records out and use the printIt method,and the two parameters vary based 
//on which array is to be printed and which direction.

	public int getSize() {
		return size;
	}
	
//takes a database record object as an arg and just adds a record to the end, while incrementing the size.
	private void addRecord(DataBaseRecord record) {
		if (size < DataBaseArray.length) {
			DataBaseArray[size++] = record;
		}
	}

//An index is taken in as a param, and the databaserecord present at that index in the records array is 
//returned 
	private DataBaseRecord getRecord(int index) {
		return DataBaseArray[index];
	}

}