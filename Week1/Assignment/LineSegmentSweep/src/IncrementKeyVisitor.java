/**
 *
 * A BinarySearchTreeVisitor to increment all keys in a specified
 * BinarySearchTree<ComparableInteger, LineSegment> greater than
 * a specified reference value.
 */
public class IncrementKeyVisitor implements BinarySearchTreeVisitor<ComparableInteger, LineSegment> {

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