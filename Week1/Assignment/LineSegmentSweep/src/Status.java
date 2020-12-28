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
     */
    public void insert(LineSegment ls) {

        if (this.bst.empty()) {

            // Insert as first entry if bst is empty.
            this.bst.insert(new ComparableInteger(0), ls);

        } else {

            // 1. Identify the key before that to be adopted by the
            //    new LineSegment.

            final RetrieveInsertionKeyVisitor rv = new RetrieveInsertionKeyVisitor(ls.start);
            this.bst.visit(rv);

            ComparableInteger ik = rv.getInsertionKey();
            if (ik == null) {
                // The new LineSegment should be inserted at the beginning of the
                // Status if it has the smallest x - ordinate value at ls.start.y.
                ik = new ComparableInteger(0);
            }

            // 2. Traverse the bst and increase the keys > the identified key
            //    by +1 to make space for the insertion and preserve the index
            //    values of the bst nodes represented by the keys.
            final IncrementKeyVisitor iv = new IncrementKeyVisitor(ik);
            this.bst.visit(iv);

            // 3. Insert a new Node into the bst with the retrieved insertionKey
            //    containing the new LineSegment.
            this.bst.insert(ik, ls);
        }
    }

    public void remove(LineSegment ls) {
        final RetrieveNodeByValueVisitor v = new RetrieveNodeByValueVisitor(ls);
        this.bst.visit(v);
        final BinarySearchTreeNode<ComparableInteger, LineSegment> node = v.getNode();
        this.bst.remove(node);
    }

    public BinarySearchTreeNode<ComparableInteger, LineSegment> predecessor(LineSegment ls) {
        /// @Take advantage that Status keys are unique, ordered and >= 0.
        final RetrieveNodeByValueVisitor v = new RetrieveNodeByValueVisitor(ls);
        this.bst.visit(v);
        final BinarySearchTreeNode<ComparableInteger, LineSegment> node = v.getNode();
        if (node == null) {
            return null;
        }
        final ComparableInteger key = new ComparableInteger(node.key.i-1);
        return this.bst.findNode(key, null);
    }

    public BinarySearchTreeNode<ComparableInteger, LineSegment> successor(LineSegment ls) {
        /// @Take advantage that Status keys are unique, ordered and >= 0.
        final RetrieveNodeByValueVisitor v = new RetrieveNodeByValueVisitor(ls);
        this.bst.visit(v);
        final BinarySearchTreeNode<ComparableInteger, LineSegment> node = v.getNode();
        if (node == null) {
            return null;
        }
        final ComparableInteger key = new ComparableInteger(node.key.i+1);
        return this.bst.findNode(key, null);
    }

    public void swap(LineSegment ls1, LineSegment ls2) {
        final RetrieveNodeByValueVisitor v1 = new RetrieveNodeByValueVisitor(ls1);
        this.bst.visit(v1);
        final BinarySearchTreeNode<ComparableInteger, LineSegment> n1 = v1.getNode();
        if (n1 == null) {
            throw new IllegalStateException("swap - Node with value ls1 not found.");
        }

        final RetrieveNodeByValueVisitor v2 = new RetrieveNodeByValueVisitor(ls2);
        this.bst.visit(v2);
        final BinarySearchTreeNode<ComparableInteger, LineSegment> n2 = v2.getNode();
        if (n2 == null) {
            throw new IllegalStateException("swap - Node with value ls2 not found.");
        }

        final LineSegment ls = n1.value;
        n1.value = n2.value;
        n2.value = ls;
    }
}
