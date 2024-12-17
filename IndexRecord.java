package assignment3;

//This class introduces the IndexRecord used in other classes and utilizes two parameters: where and key
public class IndexRecord {
	private String key;
	private int where;

	// Constructor for initializing parameters
	public IndexRecord(String key, int where) {
		this.key = key;
		this.where = where;
	}

	// Getters for accessing key and where in other classes
	public String getKey() {
		return key;
	}

	public int getWhere() {
		return where;
	}
}

// compareTo method for comparing 2 different indexRecords with each other using
// their respective keys for
// ordering. This is used in the indexArray class
//	public int compareTo(IndexRecord other) {
//		return this.key.compareTo(other.key);
//	}
//}
