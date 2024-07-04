/*Title : MinHeap class
 * Author : Arda Baran
 * Description: This class represents Min Heap Data Structure which means Priority queue.The purpose of the usage is to 
 * find the top 5 hardest courses offered last 5 years and 10 terms by reading processed serialized .dat file
 * 
 */
public class MinHeap {
public HeapNode root;
public MinHeap() {
	this.root=null;	
}
public HeapNode getRoot() {
	return root;
}
public void setRoot(HeapNode root) {
	this.root = root;
}
public HeapNode findParent(HeapNode root,HeapNode node) {
    if (node == root) {
        return null; // root has no parent
    }

    HeapNode current = root;
    while (current != null && current.getNext() != node) {
        current = current.getNext();
    }
    return current;
}
public void heapifyUp(HeapNode node) {
    HeapNode current = node;
    while (current != root) {
        HeapNode parent = findParent(root, current);
        if (parent.getCourse().getSuccesfulRate() > current.getCourse().getSuccesfulRate()) {
            // Swap values
        	 Course temp = parent.getCourse();
            parent.setCourse((current.getCourse()));
            current.setCourse(temp);
            current = parent;
        } else {
            break;
        }
    }
}
public void heapifyDown(HeapNode node) {
    if (node == null) {
        return;
    }

    HeapNode smallest = node;
    HeapNode left = node.getNext();

    if (left != null && left.getCourse().getSuccesfulRate() < smallest.getCourse().getSuccesfulRate() ) {
        smallest = left;
    }

    HeapNode right = left != null ? left.getNext() : null;
    if (right != null && right.getCourse().getSuccesfulRate() < smallest.getCourse().getSuccesfulRate()) {
        smallest = right;
    }

    if (smallest != node) {
        // Swap values
        Course temp = node.getCourse();
        node.setCourse(smallest.getCourse());
        smallest.setCourse(temp);

        // Recursively heapify down
        heapifyDown(smallest);
    }
}
public Course delete() {
    if (root == null) {
        throw new IllegalStateException("Heap is empty");
    }

    Course deletedCourse = root.getCourse();
    HeapNode lastNode = root;
    HeapNode secondLastNode = null;
    while (lastNode.getNext() != null) {
        secondLastNode = lastNode;
        lastNode = lastNode.getNext();
    }

    // Replace root with last node value
    root.setCourse(lastNode.getCourse());

    // Remove last node
    if (secondLastNode != null) {
        secondLastNode.setNext(null);
    } else {
        root = null; // If there was only one node
    }

    // Heapify down to maintain min-heap property
    heapifyDown(root);

    return deletedCourse;
    
}
public void insertNodeToMinHeap(HeapNode current,HeapNode newNode) {
	if (current.getNext() == null) {
        current.setNext(newNode);
    } else {
        insertNodeToMinHeap(current.getNext(), newNode);
    }
    // After inserting, heapify up to maintain min-heap property
    heapifyUp(newNode);	
}
public void insertCourseToMinHeap(Course course) {
    HeapNode newNode = new HeapNode(course);
   if(course.getNumOfStudentsRegisteredToThisCourse() < 200) {
	   return;
   }
    if (root == null) {
        root = newNode;
    } else {
        insertNodeToMinHeap(root, newNode);
    }
}
public void printHardestCourses() {
System.out.println("                                           TOP 5 HARDEST COURSE                                                  ");
int i = 0;
while(i<5) {
Course hardest = delete();	
System.out.println(hardest);
i++;
}
}
}