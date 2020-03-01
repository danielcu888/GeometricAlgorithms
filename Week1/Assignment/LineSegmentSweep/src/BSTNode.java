
public class BSTNode {
	public double key;
	public Event event;
	public BSTNode left;
	public BSTNode right;
	public BSTNode parent;
	
	public BSTNode(double key_,
				   Event event_,
				   BSTNode left_,
				   BSTNode right_,
				   BSTNode parent_) {
		this.key = key_;
		this.event = event_;
		this.left = left_;
		this.right = right_;
		this.parent = parent_;
	}
	
	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}
}
