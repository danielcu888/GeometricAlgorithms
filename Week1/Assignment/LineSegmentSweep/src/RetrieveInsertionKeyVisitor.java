/**
 * A BinarySearchVisitor to retrieve the key of the node in the visited
 * BinarySearchTree whose LineString has an x ordinate value, calculated
 * at a Coordinate specified upon construction, that is largest but less
 * than that of the supplied Coordinate.
 */
public class RetrieveInsertionKeyVisitor implements BinarySearchTreeVisitor<ComparableInteger, LineSegment> {

    /**
     * A Coordinate to be used for comparison.
     */
    private final Coordinate c;

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

                // Therefore the target LineSegment should be inserted after the
                // LineSegment of the current Node.
                final ComparableInteger candidateInsertionKey = new ComparableInteger(node.key.i+1);

                // Choose the largest of candidateInsertionKey and insertionKey.
                if ((this.insertionKey == null) || (candidateInsertionKey.compareTo(this.insertionKey) == 1)) {
                    this.insertionKey = candidateInsertionKey;
                }
            }

            this.visit(node.left);
            this.visit(node.right);
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
