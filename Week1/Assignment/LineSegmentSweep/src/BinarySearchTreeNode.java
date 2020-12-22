/**
 * A class to represent a node in a BinarySearchTree<Key,Value>.
 * @param <Key> The type of the node key.
 * @param <Value> The type of the node value.
 */
public class BinarySearchTreeNode<Key extends Comparable<Key>, Value> {

    public Key key = null;
    public Value value = null;
    public BinarySearchTreeNode<Key,Value> left = null;
    public BinarySearchTreeNode<Key,Value> right = null;
    public BinarySearchTreeNode<Key,Value> parent = null;

    /**
     * Two parameter constructor.
     * @param key_ The key to be adopted by this BinarySearchTreeNode.
     * @param val_ The value to be adopted by this BinarySearchTreeNode. 
     */
    public BinarySearchTreeNode(Key key_, Value val_) {
        this.key = key_;
        this.value = val_;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
