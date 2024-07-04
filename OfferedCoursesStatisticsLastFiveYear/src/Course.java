import java.io.Serializable;
import java.text.DecimalFormat;
/*
--------------------------------------------------------------------------------------------
Title: Course class
Author:Arda Baran
Description:This class represents instance of course that offered in an academic term and each course has 
unique course code as id ,term ,name etc. Courses are taken from students so that each course has students 
and at the end of the term students get letter grades. If a student gets DD or DC its means he passed the course 
conditionally,if he gets CC or CB or BB or BA or AA it means that he become successfull.If a student gets F or FX it means
that he failed and become unsuccessful.Courses also has average and sections. Each section has gpa.
So;
numOfStudentsRegisteredToThisCourse indicates that number of students that enrolled to this course
succesfull means that number of students receive letter grade above CC from this course 
fail means that number of students receive letter grade F or FX from this course
conditional means that number of students receive letter grade DD or DC from this course
withdrawn means that number of students withdrawn from this course
occurence means that number of sections that belongs to this course
sumOfGpa means that sum of gpa's of these sections
average means that average of this course

If a student failed or withdrawn from this course it means that he is not passed this course so that we can say that
students that couldnt pass this course = fail + withdrawn

If a student successfull or conditional from this course it means that receives letter grade as DD,DC,CC,CB,BB,BA,AA it 
means that he passed this course so that we can say
students that passed this course = successful + conditional

In order to find success rate of a course ;

success rate =  students that passed this course   
                ----------------------------------- X 100     
                numOfStudentsRegisteredToThisCourse

In order to find fail rate of a course ;

success rate =  students that couldnt pass this course  
                --------------------------------------- X 100     
                numOfStudentsRegisteredToThisCourse
                
In order to find average

average =       sumOfGpa
          -------------------
           number of sections
*/
public class Course implements Serializable{
public static final long serialVersionUID = 1L;
String courseCode,term,CourseName;   
int year;
int courseCredit;
int numOfStudentsRegisteredToThisCourse,succesfull,fail,conditional,withdrawn;
double average,succesfulRate,failRate;
double sumOfGpa;
int occurence;
public Course(String courseCode) {
	this.courseCode=courseCode;
	this.term="";
	this.CourseName="";
	this.year=0;
    this.numOfStudentsRegisteredToThisCourse=0;
    this.succesfull=0;
    this.fail=0;
    this.conditional=0;
    this.withdrawn=0;
    this.average=0.0;    
    this.succesfulRate=0.0;    
    this.failRate=0.0;
this.courseCredit=0;
this.sumOfGpa=0.0;
this.occurence=0;
}
public double getSumOfGpa() {
	return sumOfGpa;
}
public void setSumOfGpa(double sumOfGpa) {
	this.sumOfGpa = sumOfGpa;
}
public int getOccurence() {
	return occurence;
}
public void setOccurence(int occurence) {
	this.occurence = occurence;
}

public String getCourseCode() {
	return courseCode;
}
public int getCourseCredit() {
	return courseCredit;
}
public void setCourseCredit(int courseCredit) {
	this.courseCredit = courseCredit;
}
public double getSuccesfulRate() {
	return succesfulRate;
}
public void setSuccesfulRate(double succesfulRate) {
	this.succesfulRate = succesfulRate;
}
public double getFailRate() {
	return failRate;
}
public void setFailRate(double failRate) {
	this.failRate = failRate;
}
public void setCourseCode(String courseCode) {
	this.courseCode = courseCode;
}
public String getTerm() {
	return term;
}
public void setTerm(String term) {
	this.term = term;
}
public String getCourseName() {
	return CourseName;
}
public void setCourseName(String courseName) {
	CourseName = courseName;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
public int getNumOfStudentsRegisteredToThisCourse() {
	return numOfStudentsRegisteredToThisCourse;
}
public void setNumOfStudentsRegisteredToThisCourse(int numOfStudentsRegisteredToThisCourse) {
	this.numOfStudentsRegisteredToThisCourse = numOfStudentsRegisteredToThisCourse;
}
public int getSuccesfull() {
	return succesfull;
}
public void setSuccesfull(int succesfull) {
	this.succesfull = succesfull;
}
public int getFail() {
	return fail;
}
public void setFail(int fail) {
	this.fail = fail;
}
public int getConditional() {
	return conditional;
}
public void setConditional(int conditional) {
	this.conditional = conditional;
}
public int getWithdrawn() {
	return withdrawn;
}
public void setWithdrawn(int withdrawn) {
	this.withdrawn = withdrawn;
}
public double getAverage() {
	return average;
}
public void setAverage(double average) {
	this.average = average;
}
public double findFailRate() {

double failed= (double)	getFail() + getWithdrawn();
double total= (double) getNumOfStudentsRegisteredToThisCourse();
	
return (failed / total) * 100.0; 
}
public double findSuccessRate() {
	double passed= (double)getSuccesfull() + getConditional();
	double total= (double) getNumOfStudentsRegisteredToThisCourse();		
	return (passed / total) * 100.0;
}
public int numOfStudentsPassedThisCourse() {
	return (getSuccesfull() + getConditional());
}
public int numOfStudentsFailedThisCourse() {
	return (getFail()+getWithdrawn());
}
public double findAverage() {
	double sumOfAverages = getSumOfGpa();
	double totalNumberOfSections= (double)getOccurence();
	return sumOfAverages / totalNumberOfSections;
}
public String toString() {
	DecimalFormat df = new DecimalFormat("#.##");
	return "============================================================================================================================"+"\n"+
			"Course Code: "+getCourseCode()+"\n"+"Course Name: "+getCourseName()+"\n"+"Course Credit: "+getCourseCredit()+"\n"
+"----------------------------------------------------------------------------------------------------------------------------"+"\n"
+"                            All Statistic Of The Course " +getCourseCode()+" From 2019 Fall Term To 2024 Spring Term"+"\n"
+"\n"+"Total Number Of Students That Registered This Course Last 5 Years : "+getNumOfStudentsRegisteredToThisCourse()
+"\n"+"Total Number Of Students That Become Successfull Which Means Received Letter Grade CC or above : "+getSuccesfull()
+"\n"+"Total Number Of Students That Become Unsuccessfull Which Means Received Letter Grade F or FX : "+getFail()+"\n"
+"Total Number Of Students Conditionally Passed The Course That Received Letter Grade DD or DC : "+getConditional()+"\n"
+"Total Number Of Students That Withdrawn From This Course : "+getWithdrawn()+"\n"
+"****************************************************************************************************************************\n"
+"                               Total Number Of Students Passed This Course : "+numOfStudentsPassedThisCourse()+"\n"
+"                               Total Number Of Students Failed This Course : "+numOfStudentsFailedThisCourse()+"\n"
+"                               Cumulative Average Of This Course : "+df.format(getAverage())+"\n"
+"                               Success Rate Of This Course : %"+df.format(getSuccesfulRate())+"\n"
+"                               Fail Rate Of This Course : %"+df.format(getFailRate())+"\n"
+	"============================================================================================================================"+"\n";
}

}
