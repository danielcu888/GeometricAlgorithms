/**
 * A class to encapsulate and manage a queue of Events, keyed
 * on the y-coordinate of each Event (in ascending order). Two
 * Events with the same y-coordinate will be ordered in ascending
 * order of their respective x-coordinate values. Events with
 * coincident Coordinates will be ordered based upon implementation
 * details relating to their order of insertion and could be subject
 * to change.
 */
public class EventQueue {

    // Private data members
    private BinarySearchTree<Coordinate, Event> bst = null;

    /**
     * Default constructor.
     */
    public EventQueue() {
        this.bst = new BinarySearchTree<Coordinate, Event>();
    }

    /**
     * Add a specified Event to the back of the queue.
     * @param e The Event to be added.
     */
    public void enqueue(Event e) {
        this.bst.insert(e.coord, e);
    }

    /**
     * Remove and return the Event at the front of the queue.
     * @return The Event at the front of the queue.
     */
    public Event dequeue() {
        if (this.empty()) {
            throw new IllegalStateException("dequeue - Cannot pop empty EventQueue.");
        }

        final Coordinate min = this.bst.minimum();
        final Event minVal = this.bst.find(min);
        this.bst.remove(min,null);

        return minVal;
    }

    /**
     * Returns whether this queue is empty.
     * @return Whether this queue is empty.
     */
    public boolean empty() {
        return this.bst.empty();
    }

    @Override
    public String toString() {
        return "EventQueue<" + this.bst.toString() + ">";
    }
}
