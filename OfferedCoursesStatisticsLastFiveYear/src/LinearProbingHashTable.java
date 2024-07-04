import java.io.Serializable;
/*
 * Title : LinearProbingHashTable class
 * Author : Arda Baran
 * Description : This class represents a linear probing hash table data structure. 
 * The purpose of using this data structure is to obtain pure course statistics. 
 * This project aims to obtain all course statistics by reading Excel files from the last 5 years and there are 10 terms. 
 * The goal is to find out how many students in the university have taken each course in the last 5 years, 
 * how many of them were successful, conditionally successful, failed, or withdrawn. Additionally, the project aims 
 * to determine how many sections were opened for each course over the last 5 years, the fail rate, success rate, 
 * average GPA, etc. To manage multiple occurrences of a course and obtain pure data, the hash table is the best choice.
 * After managing multiple occurences of a course and obtaining its pure data , I serialized the hash table because reading 
 * pure obtained data from a .dat or a .bin file then inserting that pure data to avl tree data structure and min heap data structure is 
 * in order to search query and determine the hardest and easiest course with respect to statistics that obtained from hash table
 * more fast and efficient than dealing with 10 or more excel .xlsx files.If there is a dat file no need to deal with excel file,
 * just read the dat file and place all courses to heap and avl tree.
 * else we need to read and process to excel file and serialize the hash table in order to obtain pure data and statistics of
 * courses that offered in last 5 academic year and last 10 academic term.  
 */

public class LinearProbingHashTable implements Serializable {	
public static final long serialVersionUID = 1L;
int tableSize;
String [] courseCodes;//keys
Course[] values;//values
int[] occurencesForAverage;
public LinearProbingHashTable(int tableSize)  {
	this.tableSize=tableSize;
	this.courseCodes=new String[getTableSize()];
	this.values=new Course[getTableSize()];
	this.occurencesForAverage=new int[getTableSize()];
}
public int getTableSize() {
	return tableSize;
}
public void setTableSize(int tableSize) {
	this.tableSize = tableSize;
}
public String[] getCourseCodes() {
	return courseCodes;
}
public void setCourseCodes(String[] courseCodes) {
	this.courseCodes = courseCodes;
}
public Course[] getValues() {
	return values;
}
public void setValues(Course[] values) {
	this.values = values;
}
public int hash(String code) {
	return (code.hashCode() & 0x7777777f) % getTableSize();
}
public void addCourseToHashTable(String key,Course val) {	
	int i;
	for(i=hash(key);courseCodes[i]!=null; i = (i + 1) % getTableSize()) {	    		
		if(isSameCourse(courseCodes[i],key)) { // if course is already present in the hash table,update components of course
			occurencesForAverage[i]++;
			int conditional = values[i].getConditional() + val.getConditional();
			int succesfull= values[i].getSuccesfull() + val.getSuccesfull() ;
			int fail = values[i].getFail() + val.getFail();
			int allStudents=values[i].getNumOfStudentsRegisteredToThisCourse() + val.getNumOfStudentsRegisteredToThisCourse();
			int withdrawn=values[i].getWithdrawn()+val.getWithdrawn();
			double sumOfAverages=values[i].getSumOfGpa()+val.getSumOfGpa();
			
			values[i].setSuccesfull(succesfull );
	           
	            values[i].setNumOfStudentsRegisteredToThisCourse(allStudents);
	            values[i].setFail(fail);
	            values[i].setConditional(conditional);
	            values[i].setWithdrawn(withdrawn);
	            values[i].setSuccesfulRate(values[i].findSuccessRate());
	            values[i].setFailRate(values[i].findFailRate());
	          values[i].setSumOfGpa(sumOfAverages);
	          values[i].setOccurence(occurencesForAverage[i]);
	          values[i].setAverage(values[i].findAverage());
	          return;		
	
	}			
	}
	courseCodes[i] = key;
    values[i] = val;
	occurencesForAverage[i]++;
	values[i].setOccurence(occurencesForAverage[i]);
	values[i].setSuccesfulRate(values[i].findSuccessRate());
      values[i].setFailRate(values[i].findFailRate());
      values[i].setAverage(values[i].findAverage());
}
public boolean isSameCourse(String code1,String code2) {
	return (code1.equals(code2));
}
public Course searchCourseFromHashTable(String key) {
    for (int i = hash(key); courseCodes[i] != null; i = (i + 1) % getTableSize()) {
        if (courseCodes[i].equals(key)) {
            return values[i];
        }
    }
    return null;
}	
public void getCourseFromHashTable(String key) {
	Course searchedCourse = searchCourseFromHashTable(key);
	if(searchedCourse!=null) {
		System.out.println(searchedCourse);
	}else {
		System.out.println("Course not found...");
	}

}

}
