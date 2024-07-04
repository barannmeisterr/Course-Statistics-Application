import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*
 * Title : Main class
 * Author : Arda Baran
 * Description : 
 * This class serves as the main entry point for the course statistics application.
 * The application reads course data from Excel files, processes the data, and stores it
 * in a linear probing hash table and an AVL tree. It provides functionality for
 * searching courses, obtaining statistics for all courses, and retrieving statistics
 * for specific academic terms. The application also allows the user to see courses
 * with the highest and lowest success rates. The processed data is serialized for
 * efficient subsequent usage.
 */
public class Main {
	public static void main(String[] args)  {
	      String serializedFileName = "courses.dat";
	        LinearProbingHashTable courseTable = null;
          Scanner sc = new Scanner(System.in);
	        // Check if serialized file exists
	        File serializedFile = new File(serializedFileName);
	        if (serializedFile.exists()) {
	            courseTable = deserializeHashTable(serializedFileName);
	            if (courseTable != null) {
	                System.out.println("Deserialized hash table from " + serializedFileName);
	            }
	        } else {
	            courseTable = new LinearProbingHashTable(241);
	            readExcelFiles(courseTable);
	            serializeHashTable(courseTable, serializedFileName);
	        }

	      
	        AvlTree courseAVLTree = new AvlTree();
	       
	        for (Course course : courseTable.getValues()) {
	            if (course != null) {
	                courseAVLTree.insertCourseToAvl(course);
	              
	            }
	        }
while(true) {
	 System.out.println("=======================================================================================");
     System.out.println("|Press 1 Get Statistics Of All Offered Courses From 2019 Fall To 2024 Spring          |");
     System.out.println("|Press 2 To Search A Specific Course From 2019 Fall To 2024 Spring                    |");
     System.out.println("|Press 3 To Get Statistics Of All Courses That Offered In A Specific Academic Term    |");
     System.out.println("|Press 4 To See Courses That Has Greatest Fail And Success Rate                       |");
     System.out.println("|Write exit to terminate the program                                                  |");
     System.out.println("=======================================================================================");
     
     String choice  = sc.next(); // Read and trim input
	switch(choice) {
	case "1":
		courseAVLTree.inOrder();
	System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		break;
	case "2":
		sc.nextLine();
		String query;
		System.out.println("Please Enter The Code Of The Course");
		 query = sc.nextLine();
		courseAVLTree.searchCourse(query);
	break;
   
	case "3":
	sc.nextLine();
		String  term;
		System.out.println("Please Enter Academic Term (e.g., FALL 2019 for 2019-2020 Academic Year Fall Term) (e.g., SPRING 2020 for 2019-2020 Academic Year Spring Term )");
		 term = sc.nextLine();
		 academicTermStatistics(term);
	break;
	case "4":
		MinHeap hardestCourses = new MinHeap();
        MaxHeap easiestCourses = new MaxHeap();
		for (Course course : courseTable.getValues()) {
            if (course != null) {
            	hardestCourses.insertCourseToMinHeap(course);;
                easiestCourses.insertCourseToMaxHeap(course);
            }
        }	
      
		hardestCourses.printHardestCourses();
	System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
	easiestCourses.printEasiestCourses();
		break;
	 case "exit":
         System.out.println("Program Terminated...");
         return; // Exit the main method, terminating the program
         
	 default:
         System.out.println("Invalid Command");
         break;
	}


		
	          
}	    
}
	
	public static void readExcelFiles(LinearProbingHashTable courseTable) {
		// Path to the directory containing Excel files
        String directoryPath = "src/resources";
        
        // List all files in the directory
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".xlsx"));

        if (files == null || files.length == 0) {
            System.out.println("No Excel files found in the directory: " + directoryPath);
            return;
        }
        for (File file : files) {
            String filePath = file.getAbsolutePath();
            System.out.println("Reading file: " + filePath);

            // Perform your Excel reading operations here
            try (FileInputStream fis = new FileInputStream(file);
                 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

                // Example code to process the workbook
                Sheet sheet = workbook.getSheetAt(0);
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        // Process each row as before
                    	String courseCode = getStringValue(row.getCell(2));
                        String courseName = getStringValue(row.getCell(3));
                        int credit = getIntegerValue(row.getCell(9));
                        int numOfStudentsRegisteredToThisCourse = getIntegerValue(row.getCell(15));
                        int successful = getIntegerValue(row.getCell(16));
                        int fail = getIntegerValue(row.getCell(17));
                        int conditional =getIntegerValue(row.getCell(18));
                        int withdrawn = getIntegerValue(row.getCell(19));
                        double sumOfAverages = getNumericValue(row.getCell(20));

                        Course course = new Course(courseCode);
                        course.setCourseName(courseName);
                        course.setCourseCredit(credit);
                        course.setNumOfStudentsRegisteredToThisCourse(numOfStudentsRegisteredToThisCourse);
                        course.setSuccesfull(successful);
                        course.setFail(fail);
                        course.setConditional(conditional);
                        course.setWithdrawn(withdrawn);
                        course.setSumOfGpa(sumOfAverages);

                        courseTable.addCourseToHashTable(courseCode, course);
                      
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All files processed successfully.");
    }
	public static void academicTermStatistics(String term)  {
		String F_2019= "src/resources/FALL2019.xlsx";
	    String S_2020="src/resources/SPRING2020.xlsx";
	    String F_2020= "src/resources/FALL2020.xlsx";
	    String S_2021="src/resources/SPRING2021.xlsx";
	    String F_2021= "src/resources/FALL2021.xlsx";
	    String S_2022="src/resources/SPRING2022.xlsx";
	    String F_2022= "src/resources/FALL2022.xlsx";
	    String S_2023="src/resources/SPRING2023.xlsx";
	    String F_2023= "src/resources/FALL2023.xlsx";
	    String S_2024="src/resources/SPRING2024.xlsx";
	

	    
	    switch(term) {
	
	case "FALL 2019":
		readSingleExcelFile(F_2019);
	break;
	case "SPRING 2020":
		readSingleExcelFile(S_2020);
	break;
	case "FALL 2020":
		readSingleExcelFile(F_2020);
	break;
	case "SPRING 2021":
		readSingleExcelFile(S_2021);
	break;
	case "FALL 2021":
		readSingleExcelFile(F_2021);
	break;
	case "SPRING 2022":
		readSingleExcelFile(S_2022);
	break;
	case "FALL 2022":
		readSingleExcelFile(F_2022);
	break;
	case "SPRING 2023":
		readSingleExcelFile(S_2023);
	break;
	case "FALL 2023":
		readSingleExcelFile(F_2023);
	break;
	case "SPRING 2024":
		readSingleExcelFile(S_2024);
	break;
	default:
		System.out.println("Invalid Term...Please Enter Input Correct");
	break;
	}	
	}
	public static void readSingleExcelFile(String filePath) {
		System.out.println("Reading file: " + filePath);
LinearProbingHashTable hashTable = new LinearProbingHashTable(241);
AvlTree avl = new AvlTree();
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

              Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    // Process each row as before
                	String courseCode = getStringValue(row.getCell(2));
                    String courseName = getStringValue(row.getCell(3));
                    int credit = getIntegerValue(row.getCell(9));
                    int numOfStudentsRegisteredToThisCourse = getIntegerValue(row.getCell(15));
                    int successful = getIntegerValue(row.getCell(16));
                    int fail = getIntegerValue(row.getCell(17));
                    int conditional =getIntegerValue(row.getCell(18));
                    int withdrawn = getIntegerValue(row.getCell(19));
                    double sumOfAverages = getNumericValue(row.getCell(20));

                    Course course = new Course(courseCode);
                    course.setCourseName(courseName);
                    course.setCourseCredit(credit);
                    course.setNumOfStudentsRegisteredToThisCourse(numOfStudentsRegisteredToThisCourse);
                    course.setSuccesfull(successful);
                    course.setFail(fail);
                    course.setConditional(conditional);
                    course.setWithdrawn(withdrawn);
                    course.setSumOfGpa(sumOfAverages);

                    hashTable.addCourseToHashTable(courseCode, course);
                }
            }
            for (int i = 0; i < hashTable.getTableSize(); i++) {
                if (hashTable.getCourseCodes()[i] != null) {
                    avl.insertCourseToAvl(hashTable.getValues()[i]);
                }
            }
            
           avl.inOrder(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public static void serializeHashTable(LinearProbingHashTable courseTable, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(courseTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinearProbingHashTable deserializeHashTable(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (LinearProbingHashTable) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

	
	
    public static String getStringValue(Cell cell) {
        if (cell != null) {
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        }
        return "";
    }
    public static double getNumericValue(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == CellType.STRING) {
               //convert excel cell to numeric value
                try {
                    return Double.parseDouble(cell.getStringCellValue().replace(",", "."));
                } catch (NumberFormatException e) {
                  
                    return 0.0;
                }
            } else if (cell.getCellType() == CellType.NUMERIC) {
                // if cell is numeric ,then return the numeric value
                return cell.getNumericCellValue();
            }
        }
        return 0.0;
    }
    public static int getIntegerValue(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == CellType.STRING) {
                try {
                    return Integer.parseInt(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0; // or handle the error as needed
                }
            } else if (cell.getCellType() == CellType.NUMERIC) {
                return (int) cell.getNumericCellValue();
            }
        }
        return 0;
    }
}