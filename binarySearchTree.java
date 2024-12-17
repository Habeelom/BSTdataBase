package assignment3;

//This class introduces the node objects of the binary search trees used in the program
public class binarySearchTree {
    String key;
    int where;
    binarySearchTree leftChild;
    binarySearchTree rightChild;
 // The key variable is used for searching and sorting, where is used for position is DBarray, and left
 //and right child represent different paths of branches on the tree.
    
    public binarySearchTree(String key, int where) {
        this.key = key;
        this.where = where;
        this.leftChild = null;
        this.rightChild = null;
    }
}

//This is the constructor to initialize said node with a position and a key for searching and sorting