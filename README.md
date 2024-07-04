# Course Statistics Application

## Overview

This application provides statistical data on courses offered over the last five academic years, specifically from the 2019 Fall term to the 2024 Spring term. It processes data from Excel files and stores it in efficient data structures for quick querying and analysis. The main goal is to offer insights into course performance, including success and failure rates, average GPAs, and other relevant metrics.
 ## Author

- Arda Baran
## Features
-**Get Statistics of All Offered Courses: Displays statistics for all courses from 2019 Fall to 2024 Spring.**
-**Search a Specific Course: Allows the user to search for a specific course and view its statistics.**
-**Get Statistics of All Courses in a Specific Term: Displays statistics for courses offered in a selected academic term.**
-**See Courses with Greatest Fail and Success Rates: Displays the courses with the highest and lowest success rates.**
- **LinearProbingHashTable:** A hash table implemented using linear probing to handle course statistics efficiently.
- **AvlTree:** A self-balancing binary search tree used to store and retrieve course data efficiently.
- **MinHeap and MaxHeap:** Priority queues used to identify the hardest and easiest courses, respectively.
- **Serialization:** The application serializes the hash table to save processed data for quick access in future runs, avoiding the need to reprocess Excel files.

## Technologies And Data Structures Used
- Java
- Avl Tree Data Structure
- Priority Queue
- Linear Probing Hash Table
- Serilization
- Deserilization
- Object Oriented Programming
- Min Heap Implementation as Singly Linked List
- Max Heap Implementation as Singly Linked List
- xlsx files
- Serilizated dat file
### Course Class
Represents a course with the following attributes:
- **Course Code:** Unique identifier for the course.
- **Term:** Academic term in which the course is offered.
- **Name:** Name of the course.
- **Students:** List of students enrolled in the course.
- **Grades:** Final grades of students in the course.
- **Statistics:**
  - **numOfStudentsRegisteredToThisCourse:** Number of students enrolled.
  - **successful:** Number of students with grades above CC.
  - **fail:** Number of students with grades F or FX.
  - **conditional:** Number of students with grades DD or DC.
  - **withdrawn:** Number of students who withdrew.
  - **occurrence:** Number of sections of the course.
  - **sumOfGpa:** Sum of GPAs for all sections.
  - **average:** Average GPA of the course.

### LinearProbingHashTable Class
Handles course statistics by reading Excel files, processing the data, and storing it in a hash table. The data includes:
- Number of students taking each course.
- Success and failure rates.
- Number of course sections.
- Average GPA.

### AvlNode Class
Represents a node in an AVL tree, containing:
- **Course:** Course data.
- **Left Child:** Left child node.
- **Right Child:** Right child node.
- **Height:** Height of the node.

### AvlTree Class
A balanced binary search tree used to:
- Look up courses.
- Sort course statistics.
- Efficiently retrieve data with O(log N) complexity.

### HeapNode Class
Represents a node in a heap data structure, containing:
- **Course:** Course data.
- **Next:** Pointer to the next node.

### MinHeap and MaxHeap Classes
Priority queues used to:
- Find the top 5 hardest courses (MinHeap).
- Find the top 5 easiest courses (MaxHeap).

## File Structure
- src/: Contains the Java source code
- resources/: xlsx files (e.g., FALL2019.xlsx)
- /: Serialized dat file.
