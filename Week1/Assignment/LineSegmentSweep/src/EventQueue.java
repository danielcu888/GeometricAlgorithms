
public class EventQueue {
	private BinarySearchTree bst = null;
	
	public EventQueue() {
		this.bst = new BinarySearchTree();
	}
	
	public void push(double key, Event e) {
		this.bst.insert(key, e);
	}
	
	public Event top() {
		return this.bst.findMinimum().event;
	}
	
	public void pop() {
		this.bst.erase(this.bst.findMinimum().key);
	}
	
	public boolean isEmpty() {
		return this.bst.isEmpty();
	}
}
