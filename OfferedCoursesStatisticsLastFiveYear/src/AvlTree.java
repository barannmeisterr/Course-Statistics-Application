import java.io.Serializable;
/*Title: AvlTree class
 * Author : Arda Baran
 * Description: This class represents Avl Tree data structure which is balanced binary search tree.Avl tree is the good 
 * choice in order to look up a course and sorting the all courses statistics like dictionary.
 * Since Avl tree is a self-balancing binary search tree and the complexity of insertion and search takes O(log N ) 
 * ,I choice Avl tree data structure in order to read processed  offered courses database from serialized dat file for 
 * last five academic year.
 */
public class AvlTree implements Serializable {
AvlNode root;
public AvlTree(){
	this.root=null;
}
public int findMax(int a ,int b) {
	return Math.max(a, b);
}
public int getHeight(AvlNode node) {
	return (node==null) ? 0 : node.height;
}
public void updateHeight(AvlNode node) {
	int leftChild=getHeight(node.getLeft());
	int rightChild = getHeight(node.getRight());
if(node!=null) {
	node.height=findMax(leftChild,rightChild);
}
}
public AvlNode leftRotation(AvlNode node) {
	if (node == null || node.getRight() == null) {
        return node;
    }
	AvlNode rightChild= node.getRight();
	node.setRight(rightChild.getLeft());
    rightChild.setLeft(node);
    updateHeight(node);
    updateHeight(rightChild);
    return rightChild;
}
public AvlNode rightRotation(AvlNode node) {
	if (node == null || node.getRight() == null) {
        return node;
    }
	AvlNode leftChild = node.getLeft();
	node.setLeft(leftChild.getRight());
    leftChild.setRight(node);
    updateHeight(node);
    updateHeight(leftChild);
    return leftChild;    
}
public int balanceFactor(AvlNode node) {
	return (node==null) ? 0 : getHeight(node.getLeft()) - getHeight(node.getRight());
}
public int compareCourseCodes(AvlNode node1,AvlNode node2) {
	if(node1==null||node2==null) {
		return 0 ;	
	}
return node1.getCourse().getCourseCode().compareToIgnoreCase(node2.getCourse().getCourseCode());
}
public AvlNode balance(AvlNode node) {
	  if (node == null) {
	        return null;
	    }
	  int leftComprasion=compareCourseCodes(node.getLeft(),node);
	  int rightComprasion=compareCourseCodes(node,node.getRight());
      int balanceF=balanceFactor(node);
if(balanceF >= 1) {
	if(leftComprasion >= 0) {
		return rightRotation(node);
	}else {
		node.setLeft(leftRotation(node.getLeft()));
	return rightRotation(node);
	}
}else if (balanceF < -1) {
	if(rightComprasion <=0) {
		return leftRotation(node);
	}else {
		 node.setRight(rightRotation(node.getRight()));
         return leftRotation(node);
	}
}
return node;
}
public AvlNode insertCourseToAvl(AvlNode node , Course course ) {
	if(node == null) {
		return new AvlNode(course);
	}
if(course.getCourseCode().compareToIgnoreCase(node.getCourse().getCourseCode()) < 0) {
	node.setLeft(insertCourseToAvl(node.getLeft(),course));
}else if (course.getCourseCode().compareToIgnoreCase(node.getCourse().getCourseCode()) > 0) {
	node.setRight(insertCourseToAvl(node.getRight(),course));
}else {
	return node;
}
updateHeight(node);
return balance(node);
}
public void insertCourseToAvl(Course course) {
	root = insertCourseToAvl(root,course);
}
public boolean search(AvlNode node ,String query) {
	if(node==null) {
		return false;
	}
	int comprasion = query.compareToIgnoreCase(node.getCourse().getCourseCode());
	if(comprasion == 0) {
		return true;
	}
if(comprasion < 0) {
	return search(node.getLeft(),query);
}else {
	return search(node.getRight(),query);
}
}

public void searchCourse(AvlNode node,String query) {
	if(node==null) {
		System.out.println("There is no record,database is empty.Please insert record first...");
		return ;
	}
	if(!search(node,query) && !search(node.getLeft(),query)&& !search(node.getRight(),query)) {
		System.out.println(query+" is not found in the database...");
	}
	
	int comprasion = query.compareToIgnoreCase(node.getCourse().getCourseCode());
if(comprasion == 0 && search(node,query)) {
	System.out.println(node.getCourse());
}
if(comprasion < 0 && search(node.getLeft(),query)) {
	searchCourse(node.getLeft(),query);	
}
if(comprasion > 0 && search(node.getRight(),query)) {
	searchCourse(node.getRight(),query);	
}
}
public void searchCourse(String query) {
	searchCourse(root,query);
}
public void inOrder() {
    inOrderRecursive(root);
}

public void inOrderRecursive(AvlNode node) {
    if (node != null) {
        inOrderRecursive(node.getLeft());
        System.out.println(node.getCourse());
        inOrderRecursive(node.getRight());
    }
}

}
