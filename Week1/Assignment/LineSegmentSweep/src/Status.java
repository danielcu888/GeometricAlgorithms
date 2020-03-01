import java.util.Vector;

// At every event the Status' nodes, keyed upon the x position of the
// respective LineSegment's they represent, need to be re-evaluated.
// This should not alter their relative positions and hence the
// Status need not be rebuilt, its an O(n) process.
// Once the updated keys have been calculated, the event needs to be
// processed. If this requires insertion, we now can do so correctly,
// since the key of the new start event has an x-value at the current
// y-value as now do the other nodes.
// For intersection events, two keys will be identical, which is fine.
// To identify the LineSegment in relation to the event being processed
// they need to have unique immutable id's. These are the id members of
// each LineSegment, which must not be used as keys.

public class Status {
	
	private BinarySearchTree bst = null;
	private Vector<Double> keys = null;
	private Vector<LineSegment> lss = null;
	
	public Status(int N) {
		this.bst = new BinarySearchTree();
		this.keys = new Vector<Double>(N);
		this.lss = new Vector<LineSegment>(N);
	}
	
	public void updateKeys() {
		// Conduct a pre-order scan and iteratively update
		// the keys of the associated LineSegment with the
		// current x positions associated with the current
		// line.
	}
	
	public void insert(double x, LineSegment ls) {
		this.bst.insert(x, new Event(EventType.UNKNOWN, ls));
		this.keys.set(ls.id(), x);
		this.lss.set(ls.id(), ls);
	}

	public int predecessor(double x) {
		return this.bst.findPredecessor(x).event.ls1.id();
	}
	
	public int successor(double x) {
		return this.bst.findSuccessor(x).event.ls1.id();
	}
	
	public void remove(int id) {
		
	}
}
