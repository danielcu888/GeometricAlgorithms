/**
 *
 * A BinarySearchTreeVisitor to increment the value if all the (ComaparableInteger)
 * keys of the nodes in a specified BinarySearchTree that possess values greater
 * than the value of the reference key specified on construction.
 *
 */
class IncrementKeyVisitor implements BinarySearchTreeVisitor<ComparableInteger, LineSegment> {

    /**
     * The reference key to be used.
     */
    private ComparableInteger referenceKey;

    /**
     * Update the specified key if greater than the value
     * of referenceKey.
     * @param key The key to be compared.
     */
    private void updateKey(ComparableInteger key) {
        if (key.compareTo(referenceKey) > 0) {
            ++key.i;
        }
    }

    /**
     * Visit the specified node of the BinarySearchTree currently
     * being visited by this object.
     * @param node The node to be visited.
     */
    @Override
    public void visit(BinarySearchTreeNode<ComparableInteger, LineSegment> node) {
        if (node != null) {
            this.updateKey(node.key);

            if (node.left != null) {
                this.visit(node.left);
            }

            if (node.right != null) {
                this.visit(node.right);
            }
        }
    }

    /**
     * One-parameter constructor.
     * @param referenceKey_ The key to use for the comparison predicate.
     */
    public IncrementKeyVisitor(ComparableInteger referenceKey_) {
        this.referenceKey = referenceKey_;
    }
}

/**
 * A BinarySearchVisitor to retrieve the key of the node in the visited
 * BinarySearchTree whose LineString has an x ordinate value, calculated
 * at a Coordinate specified upon construction, that is largest but less
 * than that of the supplied Coordinate.
 */
class RetrieveInsertionKeyVisitor implements BinarySearchTreeVisitor<ComparableInteger, LineSegment> {

    /**
     * A Coordinate to be used for comparison.
     */
    private Coordinate c;

    /**
     * The node key to be retrieved.
     */
    private ComparableInteger insertionKey;

    /**
     * Visit the specified node of the currently visited BST. The value of the
     * x ordinate at this.c.y is calculated and compared to this.c.x. If it is
     * less than this.c.x the node is cached if the value of the calculated x
     * ordinate is the largest found hitherto.
     */
    @Override
    public void visit(BinarySearchTreeNode<ComparableInteger, LineSegment> node) {
        if (node != null) {

            // 1. Calculate x(c.y) of LineSegment of the current Node.
            final LineSegment ls = node.value;
            final Double x = ls.x(c.y);
            if (x == null) {
                throw new IllegalStateException("Shouldn't be comparing x's at this y for this LineString - out of range.");
            }

            // 2. Compare c.x with x from (1).
            if (x <= c.x) {

                // Therefore the current LineSegment should be inserted after the
                // LineSegment of the current Node.

                // Choose the largest of node.key and insertionKey.
                if ((this.insertionKey == null) || (this.insertionKey.compareTo(node.key) < 1)){
                    this.insertionKey = node.key;
                }
            }
        }
    }

    /**
     * Return the desired key.
     * @return The desired key.
     */
    public ComparableInteger getInsertionKey() {
        return this.insertionKey;
    }

    /**
     * One-parameter constructor.
     * @param c_ The Coordinate to be used to compare.
     */
    public RetrieveInsertionKeyVisitor(Coordinate c_) {
        this.c = c_;
        insertionKey = null;
    }
}

/**
 * A BinarySearchTreeVisitor that retrieves a node in the visited BST whose
 * LineSegment value is that specified upon construction.
 */
class RetrieveNodeByValueVisitor implements BinarySearchTreeVisitor<ComparableInteger, LineSegment> {

    /**
     * The LineSegment to be used for comparisons.
     */
    private LineSegment ls;

    /**
     * The node to be retrieved.
     */
    private BinarySearchTreeNode<ComparableInteger, LineSegment> node;

    /**
     * Visits the specified node in the currently visited BST. If the LineSegment
     * is equal to this.ls then the node is cached.
     */
    @Override
    public void visit(BinarySearchTreeNode<ComparableInteger, LineSegment> n) {
        if (this.node != null) {
            return;
        }

        if (n != null) {
            if (n.value == ls) {
                this.node = n;
                return;
            }

            this.visit(n.left);
            this.visit(n.right);
        }
    }

    /**
     * Returns the sought node with LineSegment value equal to this.ls,
     * otherwise null if none where found.
     * @return The node with LineSegment equal to this.ls, otherwise null.
     */
    public BinarySearchTreeNode<ComparableInteger, LineSegment> getNode() {
        return this.node;
    }

    /**
     * One-parameter constructor.
     * @param ls_ The LineSegment to be compared.
     */
    public RetrieveNodeByValueVisitor(LineSegment ls_) {
        this.ls = ls_;
        this.node = null;
    }
}

/**
 * A class wrapping an int that can be compared.
 */
class ComparableInteger implements Comparable<ComparableInteger> {

    /**
     * The wrapped int.
     */
    public int i;

    /**
     * The comparison predicate.
     * @param o The object to be compared.
     * @retval -1 If this.i < o.i.
     * @retval 0 If this.i == o.i.
     * @retval +1 If this.i > o.i.
     */
    @Override
    public int compareTo(ComparableInteger o) {
        if (this.i < o.i) {
            return -1;
        } else if (this.i > o.i) {
            return +1;
        } else {
            return 0;
        }
    }

    /**
     * One parameter constructor.
     * @param i_ The int to be wrapped by this object.
     */
    public ComparableInteger(Integer i_) {
        this.i = i_;
    }
}

/**
 * A class representing the status of a line sweep algorithm. */
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

        // 1. Insert as first entry if bst is empty.
        if (this.bst.empty()) {
            this.bst.insert(new ComparableInteger(0), ls);
        }

        // 2. Identify the key before that to be adopted by the
        //    new LineSegment.

        final RetrieveInsertionKeyVisitor rv = new RetrieveInsertionKeyVisitor(ls.start);
        this.bst.visit(rv);

        ComparableInteger ik = rv.getInsertionKey();
        if (ik == null) {
            // The new LineSegment should be inserted at the beginning of the
            // Status if it has the smallest x - ordinate value at ls.start.y.
            ik = new ComparableInteger(0);
        }

        // 3. Traverse the bst and increase the keys > the identified key
        //    by +1 to make space for the insertion and preserve the index
        //    values of the bst nodes represented by the keys.
        final IncrementKeyVisitor iv = new IncrementKeyVisitor(ik);
        this.bst.visit(iv);

        // 4. Insert a new Node into the bst with the retrieved insertionKey
        //    containing the new LineSegment.
        this.bst.insert(new ComparableInteger(ik.i), ls);
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
