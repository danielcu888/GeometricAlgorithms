/**
 * A class representing the status of a line sweep algorithm.
 */
public class Status {

    /**
     * The BST containing nodes representing the LineSegments currently
     * in this Status.
     */
    private BinarySearchTree<ComparableInteger, LineSegment> bst;

    /**
     * Default constructor.
     */
    public Status() {
        bst = new BinarySearchTree<ComparableInteger, LineSegment>();
    }

    /**
     * Insert a new LineSegment into this Status object.
     * @param ls The LineSegment to be inserted.
     * @throws IllegalArgumentException
     */
    public void insert(LineSegment ls) {

        if (ls == null) {
            throw new IllegalArgumentException("Attempt to insert null LineSegment.");
        }

        if (this.bst.empty()) {

            // Insert as first entry if bst is empty.
            this.bst.insert(new ComparableInteger(0), ls);

        } else {

            // 1. Identify the key before that to be adopted by the
            //    new LineSegment.

            final RetrieveInsertionKeyVisitor rv = new RetrieveInsertionKeyVisitor(ls.start);
            this.bst.visit(rv);

            int ik = -1;
            if (rv.getInsertionKey() == null) {
                // The new LineSegment should be inserted at the beginning of the
                // Status if it has the smallest x - ordinate value at ls.start.y.
                ik = 0;
            } else {
                ik = rv.getInsertionKey().i;
            }

            // 2. Traverse the bst and increase the keys >= the identified key
            //    by +1 to make space for the insertion and preserve the index
            //    values of the bst nodes represented by the keys.
            final IncrementKeyVisitor iv
                = new IncrementKeyVisitor(new ComparableInteger(ik));
            this.bst.visit(iv);

            // 3. Insert a new Node into the bst with the retrieved insertionKey
            //    containing the new LineSegment.
            this.bst.insert(new ComparableInteger(ik), ls);
        }
    }

    /**
     * Remove the specified LineSegment from this Status.
     * @param ls The LineSegment to be removed.
     */
    public void remove(LineSegment ls) {

        // Find the node associated with the specified LineSegment.
        final RetrieveNodeByValueVisitor v = new RetrieveNodeByValueVisitor(ls);
        this.bst.visit(v);
        final BinarySearchTreeNode<ComparableInteger, LineSegment> node = v.getNode();

        // Remove the retrieved node.
        this.bst.remove(node);
    }

    /**
     * Retrieve the predecessor of the node with value equal to a
     * specified LineSegment.
     * @param ls The specified LineSegment.
     * @return The predecessor of the node with value ls, or null if
     *   there is no predecessor.
     * @throws IllegalArgumentException if no node with value ls exists.
     */
    public BinarySearchTreeNode<ComparableInteger, LineSegment> predecessor(LineSegment ls) {

        // Retrieve node with value ls.
        final RetrieveNodeByValueVisitor v = new RetrieveNodeByValueVisitor(ls);
        this.bst.visit(v);
        final BinarySearchTreeNode<ComparableInteger, LineSegment> node = v.getNode();

        if (node == null) {
            throw new IllegalArgumentException("No node with specified LineSegment in Status.");
        }

        // Check there is a predecessor, taking advantage of the fact that
        // the Status nodes have unique keys [0,1,2,3,...].
        if (node.key.i == 0) {
            // No predecessor.
            return null;
        }

        // Retrieve the node with key value one less than that retrieved.
        final ComparableInteger key = new ComparableInteger(node.key.i-1);
        return this.bst.findNode(key, null);
    }

    /**
     * Retrieve the successor of the node with value equal to a
     * specified LineSegment.
     * @param ls The specified LineSegment.
     * @return The successor of the node with value ls, or null if
     *   there is no successor.
     * @throws IllegalArgumentException if no node with value ls exists.
     */
    public BinarySearchTreeNode<ComparableInteger, LineSegment> successor(LineSegment ls) {

        // Retrieve node with value ls
        final RetrieveNodeByValueVisitor v = new RetrieveNodeByValueVisitor(ls);
        this.bst.visit(v);
        final BinarySearchTreeNode<ComparableInteger, LineSegment> node = v.getNode();

        if (node == null) {
            throw new IllegalArgumentException("No node with specified LineSegment in Status.");
        }

        // Check there is a successor, taking advantage of the fact that
        // the Status nodes have unique keys [0,1,2,3,...].
        if (node.key.i+1 == this.bst.size()) {
            // No successor
            return null;
        }

        // Retrieve the node with key value one greater than that retrieved.
        final ComparableInteger key = new ComparableInteger(node.key.i+1);
        return this.bst.findNode(key, null);
    }

    /**
     * Swap the values of the nodes whose values are equal to the specified
     * LineSegments.
     * @param ls1 The first LineSegment.
     * @param ls2 The second LineSegment.
     * @throws IllegalArgumentException if a node with value equal to either
     *   ls1 or ls2 could not be found.
     */
    public void swap(LineSegment ls1, LineSegment ls2) {

        // Retrieve the node with value, ls1.

        final RetrieveNodeByValueVisitor v1 = new RetrieveNodeByValueVisitor(ls1);
        this.bst.visit(v1);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> n1 = v1.getNode();

        if (n1 == null) {
            throw new IllegalArgumentException("swap - Node with value ls1 not found.");
        }

        // Retrieve the node with value, ls2.

        final RetrieveNodeByValueVisitor v2 = new RetrieveNodeByValueVisitor(ls2);
        this.bst.visit(v2);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> n2 = v2.getNode();

        if (n2 == null) {
            throw new IllegalArgumentException("swap - Node with value ls2 not found.");
        }

        // Swap the values of the retrieved nodes.
        final LineSegment ls = n1.value;
        n1.value = n2.value;
        n2.value = ls;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();

        sb.append("Status{");

        if (this.bst.empty()) {
            sb.append("EMPTY");
        } else {
            sb.append(this.bst.toString());
        }

        sb.append("}");

        return sb.toString();
    }
}
