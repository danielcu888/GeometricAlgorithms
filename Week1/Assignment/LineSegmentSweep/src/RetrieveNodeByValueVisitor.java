/**
 * A BinarySearchTreeVisitor that retrieves a node in the visited BST whose
 * LineSegment value is that specified upon construction.
 */
public class RetrieveNodeByValueVisitor implements BinarySearchTreeVisitor<ComparableInteger, LineSegment> {

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
