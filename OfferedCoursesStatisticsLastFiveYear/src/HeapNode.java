/*-----------------------------------------------------------------------
 * Title : HeapNode class
 * Author: Arda Baran
 * Description : This class represents Node of Heap Data Structure.Each Node has course structure as data and next pointer
 *               that indicates next node of node
 */
public class HeapNode {
Course course;
HeapNode next;
public HeapNode(Course course) {
	this.course=course;
	this.next=null;
}
public Course getCourse() {
	return course;
}
public void setCourse(Course course) {
	this.course = course;
}
public HeapNode getNext() {
	return next;
}
public void setNext(HeapNode next) {
	this.next = next;
}
}
