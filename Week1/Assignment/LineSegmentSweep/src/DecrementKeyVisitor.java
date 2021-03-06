/**
 * A BinarySearchTreeVisitor to decrement all keys in a specified
 * BinarySearchTree<ComparableInteger, LineSegment> > than
 * a specified reference value.
 */
public class DecrementKeyVisitor implements BinarySearchTreeVisitor<ComparableInteger, LineSegment> {

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
            --key.i;
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

            this.visit(node.left);
            this.visit(node.right);
        }
    }

    /**
     * One-parameter constructor.
     * @param referenceKey_ The key to use for the comparison predicate.
     */
    public DecrementKeyVisitor(ComparableInteger referenceKey_) {
        this.referenceKey = new ComparableInteger(referenceKey_.i);
    }

    /**
     * Retrieve reference key.
     * @return The reference key of this object.
     */
    public ComparableInteger getReferenceKey() {
        return this.referenceKey;
    }
}