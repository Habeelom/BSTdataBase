# Student Database Management System

A Java implementation of a student database management system using Binary Search Trees (BST) for efficient indexing and searching. The system maintains student records with ID, first name, and last name, allowing for various sorting and searching operations.

## About The Project

This database system demonstrates the implementation of:
- Multiple Binary Search Trees for indexing
- File I/O operations
- Record management (CRUD operations)
- Bidirectional traversal
- Multi-key sorting capabilities

## Built With

- Java
- Binary Search Trees
- File I/O operations
- Scanner for user input

## Core Components

### Classes
1. `Driver`: Main program interface
2. `DataBase`: Core database operations
3. `DataBaseRecord`: Student record structure
4. `IndexRecord`: Index management
5. `binarySearchTree`: BST node implementation

### Data Structures
- Three BSTs for indexing:
  - ID index
  - First name index
  - Last name index
- Array-based record storage
- Node-based tree traversal

## Features

- Add new student records
- Delete existing records
- Find students by ID
- List students by:
  - ID (ascending/descending)
  - First name (ascending/descending)
  - Last name (ascending/descending)
- Duplicate ID prevention
- Case-insensitive search
- File-based data persistence

## Usage

1. Run the program
2. Choose from menu options (0-9):
```
 1 Add a new student
 2 Delete a student
 3 Find a student by ID
 4 List students by ID increasing
 5 List students by first name increasing
 6 List students by last name increasing
 7 List students by ID decreasing
 8 List students by first name decreasing
 9 List students by last name decreasing
 0 End
```

## Implementation Details

### Database Operations
- Records stored in array (max 100 entries)
- BST indexing for O(log n) search operations
- Bidirectional traversal support
- Automatic data loading from file

### BST Structure
- Each node contains:
  - Key (search/sort value)
  - Position reference (where)
  - Left and right child pointers

### File Operations
- Reads initial data from external file
- Handles whitespace and case normalization
- Removes duplicate entries automatically

## Optimizations

- Case-insensitive comparisons
- Efficient index management
- Memory-efficient record storage
- Duplicate prevention
- Error handling for file operations

- Java documentation
