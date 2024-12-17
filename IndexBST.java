//In the IndexArray class I implement recursive binary search trees that store the 
//records.
package assignment3;

// I use the traversalArray or in order traversal of the tree
public class IndexBST {
	binarySearchTree root;
	int sz;
	binarySearchTree[] traversalArray;
	int arraySize;
	int current;

//This is my constructor for the indexBST where I set current to -1 to indicate that i haven't started 
//traversing yet, and I set traversalArray to size 100 similar to the dbArray. Everything else is set
//to 0 or null to start
	public IndexBST() {
		root = null;
		sz = 0;
		traversalArray = new binarySearchTree[100];
		arraySize = 0;
		current = -1;
	}

//This is used for adding an indexRecord to the tree using a key and position of the record, and 
//increases the size accordingly. this actually does the inserting in a sense
	public void addIndex(IndexRecord record) {
		root = insert(root, record.getKey(), record.getWhere());
		sz++;
	}

//This actually adds the node in the right place in the tree by looking at the other records in the tree
// and seeing if it should go left or right depending on the size comparison. If the key is smaller
//it goes to the left subTree and if its bigger vice versa. 
	private binarySearchTree insert(binarySearchTree node, String key, int where) {
		if (node == null) {
			return new binarySearchTree(key, where);
		}

		int compVal = key.compareTo(node.key);
		if (compVal < 0) {
			node.leftChild = insert(node.leftChild, key, where);
		} else if (compVal > 0) {
			node.rightChild = insert(node.rightChild, key, where);
		}
		return node;
	}
	
//initializes iterator to front 
	public void iteratorInitFront() {
		arraySize = 0;
		inorderTraversal(root);
		if (arraySize > 0) {
			current = 0;
		}
	}
//initializes iterator to back
	public void iteratorInitBack() {
		arraySize = 0;
		inorderTraversal(root);
		if (arraySize > 0) {
			current = arraySize - 1;
		}
	}

//This is a method for in order traversal of the tree that stores the nodes in the traversalArray 
//declared earlier
	private void inorderTraversal(binarySearchTree node) {
		if (node != null && arraySize < traversalArray.length) {
			inorderTraversal(node.leftChild);
			traversalArray[arraySize++] = node;
			inorderTraversal(node.rightChild);
		}
	}

//checks if next elem is present by seeing if it can be put within bounds
	public boolean hasNext() {
		return current >= 0 && current < arraySize;
	}

//opposite of hasnext
	public boolean hasPrevious() {
		return current >= 0 && current < arraySize;
	}

//getNext returns the position of the next node, then moves the pointer forward by 1 if there is a next
	public int getNext() {
		if (!hasNext())
			return -1;
		return traversalArray[current++].where;
	}
	
//opposite of getNext. decrements pointer by 1 when and if previous node is found
	public int getPrevious() {
		if (!hasPrevious())
			return -1;
		return traversalArray[current--].where;
	}

//the index is taken in, and the key of that position is found. The node with that key is then deleted
//and the size of the tree is decremented
	public void deleteIndex(int index) {
		String keyToDelete = findKeyByWhere(root, index);
		if (keyToDelete != null) {
			root = delete(root, keyToDelete);
			sz--;
		}
	}

//used to find the key itself by taking in the current node and where the record to be found is. if the 
//node is null, then null is returned. if the current node matches then the current node is returned.
//otherwise the tree is searched recursivley left and right for the where. the key is returned when
//and if found
	private String findKeyByWhere(binarySearchTree node, int where) {
		if (node == null)
			return null;

		if (node.where == where)
			return node.key;

		String leftResult = findKeyByWhere(node.leftChild, where);
		if (leftResult != null)
			return leftResult;

		return findKeyByWhere(node.rightChild, where);
	}

//the delete method is used to delete a node from the tree. The key of the element to be deleted and the 
//current node are taken in. if the tree is empty nothing is deleted. Then the key is compared with the
//current nodes key and the program goes left if smaller and right if bigger. if the key matches the 
//current nodes key then its deleted. otherwise if there is one child it returns the child. If it has
// 2 then it finds the in order successor and repalces the current nodes key and position with the
//successors info, then recursivley deletes it.
	private binarySearchTree delete(binarySearchTree node, String key) {
		if (node == null)
			return null;

		int compareResult = key.compareTo(node.key);
		if (compareResult < 0) {
			node.leftChild = delete(node.leftChild, key);
		} else if (compareResult > 0) {
			node.rightChild = delete(node.rightChild, key);
		} else {
			if (node.leftChild == null) {
				return node.rightChild;
			} else if (node.rightChild == null) {
				return node.leftChild;
			}
			binarySearchTree successor = getMin(node.rightChild);
			node.key = successor.key;
			node.where = successor.where;
			node.rightChild = delete(node.rightChild, successor.key);
		}
		return node;
	}

//this is used to find the node with the smallest key in the tree by taking in the current position, then
//traversing left until it cant, then returning the leftmost node in the tree.
	private binarySearchTree getMin(binarySearchTree node) {
		while (node.leftChild != null) {
			node = node.leftChild;
		}
		return node;
	}
	
//uses a key to find a node in the tree. if the current node is null, then the node wasn't found. 
//otherwise the current node is comapred to the node to be found and if the new node is smaller, it goes
//left, and if bigger goes right. if the key and current postion match then the current node is returned.
	private binarySearchTree find(binarySearchTree node, String key) {
		if (node == null)
			return null;

		int compareResult = key.compareTo(node.key);
		if (compareResult == 0)
			return node;
		if (compareResult < 0)
			return find(node.leftChild, key);
		return find(node.rightChild, key);
	}
	
//takes in a given key and searches the tree from the root using the find method and returns the where 
//of the node. this actually does the searching in a sense

	public int search(String key) {
		binarySearchTree result = find(root, key);
		if (result != null) {
			return result.where;
		} else {
			return -1;
		}
	}



	public int getSize() {
		return sz;
	}
}
