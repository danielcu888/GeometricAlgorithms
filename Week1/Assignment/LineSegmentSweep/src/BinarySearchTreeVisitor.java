/**
 * An interface to be implemented by all classes that visit a
 * BinarySearchTree<Key,Value>
 * @param <Key> The Key type of the BinarySearchTree to be
 *   visited.
 * @param <Value> The Value type of the BinarySearchTree to be
 *   visited.
 */
public interface BinarySearchTreeVisitor<Key extends Comparable<Key>, Value> {

	/**
	 * Visit a specified BinarySearchTree.
	 * @param node The BinarySearchTreeNode at the root of the
	 *   BinarySearchTree to be visited.
	 */
    public void visit(BinarySearchTreeNode<Key, Value> node);
}
