public class EventQueue {

    private BinarySearchTree<Coordinate, Event> bst;

    public EventQueue() {
        bst = new BinarySearchTree<Coordinate, Event>();
    }

    public void enqueue(Event e) {
        bst.insert(e.coord, e);
    }

    public Event dequeue() {
        if (this.empty()) {
            throw new IllegalStateException("dequeue - Cannot pop empty EventQueue.");
        }

        final Coordinate min = this.bst.minimum();
        final Event minVal = this.bst.find(min);
        this.bst.remove(min,null);

        return minVal;
    }

    public boolean empty() {
        return this.bst.empty();
    }
}
