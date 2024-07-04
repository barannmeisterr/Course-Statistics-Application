import java.io.Serializable;
/*Title : AvlNode class
 * Author : Arda Baran 
 * Description : This class represent Node of Avl Tree data structure.Node has 3 components :
 * Course instance as data
 * Left child
 * Right Child
 * Height
 */
public class AvlNode implements Serializable {
	Course course;
	AvlNode left,right;
	int height;		
	public AvlNode(Course course) {
		this.course=course;
		this.left=null;
		this.right=null;
	    this.height=1;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public AvlNode getLeft() {
		return left;
	}
	public void setLeft(AvlNode left) {
		this.left = left;
	}
	public AvlNode getRight() {
		return right;
	}
	public void setRight(AvlNode right) {
		this.right = right;
	}
}