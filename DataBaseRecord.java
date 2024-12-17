package assignment3;

//This class introduces the DataBaseRecord which is used elsewhere in the project

public class DataBaseRecord {
	private String ID;
	private String first;
	private String last;

	DataBaseRecord(String a, String b, String c) {
		ID = a;
		first = b;
		last = c;
	}

	public String toString() {
		return ID + " " + first + " " + last;
	}
}
