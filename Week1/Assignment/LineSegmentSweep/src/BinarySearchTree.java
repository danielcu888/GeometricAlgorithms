import java.util.LinkedList;

/**
 * A class representing a binary search tree.
 * @param <Key> A Comparable Object type to store the key
 *   of each node of this BinarySearchTree.
 * @param <Value> The Object type used to store the value
 *   of each node of this BinarySearchTree.
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    // Private data members
    private BinarySearchTreeNode<Key, Value> root = null;

    // Private methods

    /**
     * Returns the number of nodes in the specified BinarySearchTree.
     * @param n The root of the BinarySearchTree to be counted.
     * @param count The count carried forward.
     * @return The number of nodes in the specified BinarySearchTree.
     */
    private int size(BinarySearchTreeNode<Key,Value> n, int count) {
        if (n == null) {
                return count;
        }

        ++count;
        count = this.size(n.left, count);
        count = this.size(n.right, count);

        return count;
    }

    /**
     * Find the minimum of the specified BST.
     * @param root The root node of the BST to be searched.
     * @return The node of the specified BST with the
     *   minimum value.
     */
    private BinarySearchTreeNode<Key,Value> minimum(BinarySearchTreeNode<Key,Value> root) {
        if (root == null) {
            return null;
        } else if (root.left != null) {
            // Search left subtree.
            return this.minimum(root.left);
        } else {
            // root has no left child - it is the minimum.
            return root;
        }
    }

    /**
     * Find the maximum of the specified BST.
     * @param root The root node of the BST to be searched.
     * @return The node of the specified BST with the
     *   maximum value.
     */
    private BinarySearchTreeNode<Key,Value> maximum(BinarySearchTreeNode<Key,Value> root) {
        if (root == null) {
            return null;
        } else if (root.right != null) {
            // Search right subtree.
            return this.maximum(root.right);
        } else {
            // root has no right child - it is the maximum.
            return root;
        }
    }

    /**
     * Find the node in the specified BST that has a key equal
     * to that specified, and optionally, has value equal to
     * that specified.
     * @param root The root node of the BST to be searched.
     * @param key The key to be matched.
     * @param val The value to be matched. If null is specified
     *   then value matching will not be used in addition to key
     *   matching.
     * @return The node in the specified BST that has a matching
     *   key, and optionally, value specified.
     */
    private BinarySearchTreeNode<Key,Value> find(BinarySearchTreeNode<Key,Value> root, Key key, Value val) {
        if (root == null) {
            return null;
        }

        // Compare specified key with that of current node.
        final int c = key.compareTo(root.key);

        if (c == 0) {
            // Keys are equal.

            if ((val == null) || (root.value == val)) {
                // We've matched values or the did not wish to
                // use value matching.
                return root;
            }

            // We are using value matching and did not match
            // values with current node.
            //
            // Check right subtree, since only there can
            // there exist nodes with keys equal to
            // that of the current node.
            this.find(root.right, key, val);

            // No matching node.
            return null;

        } else if (c < 0) {
            // key is less than that of current node -
            // search left subtree.
            return this.find(root.left, key, val);
        }

        // key is greater than that of current node -
        // search right subtree.
        return this.find(root.right, key, val);
    }

    /**
     * Insert the specified node into the specified BST.
     * @param root The root node of the target BST.
     * @param n The node to be inserted.
     * @return The updated root node of the target BST.
     */
    private BinarySearchTreeNode<Key,Value> insert(BinarySearchTreeNode<Key,Value> root, BinarySearchTreeNode<Key,Value> n) {
        if (root == null) {
            return n;
        }

        if (n.key.compareTo(root.key) < 0) {
            // insertion key is less than that of the current node -
            // insert into left subtree.
            root.left = this.insert(root.left, n);
            root.left.parent = root;
        } else {
            // insertion key is greater or equal to that of the current node -
            // insert into right subtree.
            root.right = this.insert(root.right, n);
            root.right.parent = root;
        }

        // Return updated node.
        return root;
    }

    /**
     * Print a specified BST to the standard output stream.
     * @param root The root node of the BST to be printed.
     * @param nodes A collection used to store collections
     *   of nodes each containing the nodes at each level
     *   of depth of the BST to be printed.
     * @param level The current level at which root resides
     *   in the host BST.
     * @warning Only for use with public print() method.
     */
    private void print(BinarySearchTreeNode<Key,Value> root, LinkedList<LinkedList<Key>> nodes, int level) {
        if (nodes.size() <= level) {
            nodes.add(level, new LinkedList<Key>());
        }

        if (root == null) {
            nodes.get(level).add(null);
        } else {
            nodes.get(level).add(root.key);
            this.print(root.left, nodes, level+1);
            this.print(root.right, nodes, level+1);
        }
    }

    /**
     * Serialise the keys of the nodes of the specified BST
     * as a '|' separated string with the following notation:
     *
     * "N" - null
     * "<key>" - non-null node
     *
     * Each node key is separated by terminating '|'s.
     *
     * @param sb A buffer used to construct the desired string.
     * @param root The root node of the BST to be serialised.
     * @warning Only for use with public serialise() method.
     */
    private void serialise(StringBuffer sb, BinarySearchTreeNode<Key,Value> root) {
        if (root == null) {
            sb.append("N|");
        } else {
            sb.append(root.key + "|");

            this.serialise(sb, root.left);
            this.serialise(sb, root.right);
        }
    }

    /**
     * Serialise the data of the nodes of the specified BST
     * as a '|' separated string with the following notation:
     *
     * "N" - null
     * "<key,value>" - non-null node
     *
     * Each node data is separated by terminating '|'s.
     *
     * @param sb A buffer used to construct the desired string.
     * @param root The root node of the BST to be serialised.
     * @warning Only for use with public serialise() method.
     */
    private void serialiseAll(StringBuffer sb, BinarySearchTreeNode<Key,Value> root) {
        if (root == null) {
            sb.append("N|");
        } else {
            sb.append(root.key + "," + root.value + "|");

            this.serialiseAll(sb, root.left);
            this.serialiseAll(sb, root.right);
        }
    }

    // Public methods

    /**
     * Return the key of the node in this BST with the minimum
     * value.
     * @return The key of the node in this BST with the minimum
     *   value.
     */
    public Key minimum() {
        if (this.root == null) {
            throw new IllegalStateException("mimimum - empty tree.");
        }

        return this.minimum(this.root).key;
    }

    /**
     * Return the key of the node in this BST with the maximum
     * value.
     * @return The key of the node in this BST with the maximum
     *   value
     */
    public Key maximum() {
        if (this.root == null) {
            throw new IllegalStateException("maximum - empty tree.");
        }

        return this.maximum(this.root).key;
    }

    /**
     * Return the value of the node with the specified key.
     * @param key The key of the node whose value is required.
     * @return The value of the node with the specified key.
     *   If multiple nodes exist with the specified key, then
     *   the value of that first found will be returned.
     */
    public Value find(Key key) {
        final BinarySearchTreeNode<Key,Value> n = this.find(this.root, key, null);

        if (n != null) {
            return n.value;
        }

        return null;
    }

    /**
     * Return the node in this BST with the specified key and,
     * value. If one simply wants to match keys, then val should
     * be specified as null.
     * @param key The key of the node to be found.
     * @param val The value of the node to be found, or null if
     *   no value matching is required.
     * @return The node in this BST with the specified key and
     *   value. If multiple nodes are present with the specified
     *   key, and value, if not null, then the first matched node
     *   found will be returned. If no such node exists then
     *   null will be returned.
     */
    public BinarySearchTreeNode<Key,Value> findNode(Key key, Value val) {
        return this.find(this.root, key, val);
    }

    /**
     * Insert a node with the specified key and value into this BST.
     * @param key The key of the node to be inserted.
     * @param val The value of the node to be inserted.
     */
    public void insert(Key key, Value val) {
        final BinarySearchTreeNode<Key,Value> n
            = new BinarySearchTreeNode<Key,Value>(key, val);

        this.root = this.insert(this.root, n);
    }

    /**
     * Return the key of the successor of the node with the
     *   specified key.
     * @param key The key of the node whose successor is to be
     *   found.
     * @return The key of the successor of the node with the
     *   specified key. If no node with the specified key
     *   exists in this BST, or there is no successor,
     *   then null will be returned. If there are multiple
     *   nodes with the specified key in this BST then the
     *   key of the successor of the first found will be
     *   returned.
     */
    public Key successor(Key key) {
        final BinarySearchTreeNode<Key,Value> n = this.successorNode(key);

        if (n == null) {
            return null;
        }

        return n.key;
    }

    /**
     * Returns the successor of the node with the specified key.
     * @param key The key of the node whose successor is to be
     *   found.
     * @return The successor of the node with the specified key.
     *   If no node with the specified key exists in this BST,
     *   or there is no successor, then null will be returned.
     *   If there are multiple nodes with the specified key in
     *   this BST then the successor of the first found will be
     *   returned.
     */
    public BinarySearchTreeNode<Key,Value> successorNode(Key key) {
        final BinarySearchTreeNode<Key,Value> n
            = this.find(this.root, key, null);

        return this.successorNode(n);
    }

    /**
     * Returns the successor of the specified node.
     * @param n The node whose successor is to be found.
     * @return The successor of the specified node. If
     *   the specified node has no successor, then null
     *   will be returned.
     */
    public BinarySearchTreeNode<Key,Value> successorNode(BinarySearchTreeNode<Key,Value> n) {
        if (n == null) {
            throw new IllegalStateException("successorNode - No node with specified key can be found.");
        }

        // Test for right subtree.
        if (n.right != null) {
            // n has a right subtree - its minimum is its successor.
            return this.minimum(n.right);
        }

        // n has no right subtree - it is the maximum of its subtree.
        // Hence, check if the parent supertrees have right subtrees
        // that are not equal to the subtree with n at its root.
        while (n.parent != null && n.parent.right == n) {
            n = n.parent;
        }

        if (n.parent == null) {
            // n is the maximum of all of its supertrees.
            return null;
        }

        if (n.parent.right == null) {
            // Sought parent has no right subtree, but is itself a successor
            // of n.
            return n.parent;
        }

        // General case - right subtree of candidate supertree has been found.
        // n's successor is then the miniumum of the right subtree of the current
        // root, or the root itself, whichever has the smaller key.

        final BinarySearchTreeNode<Key,Value> n1 = this.minimum(n.parent.right);
        final BinarySearchTreeNode<Key,Value> n2 = n.parent;
        final Key k1 = n1.key;
        final Key k2 = n2.key;
        return k1.compareTo(k2) < 0 ? n1 : n2;
    }

    /**
     * Return the key of the predecessor of the node with the
     *   specified key.
     * @param key The key of the node whose predecessor is to be
     *   found.
     * @return The key of the predecessor of the node with the
     *   specified key. If no node with the specified key
     *   exists in this BST, or there is no predecessor,
     *   then null will be returned. If there are multiple
     *   nodes with the specified key in this BST then the
     *   key of the predecessor of the first found will be
     *   returned.
     */
    public Key predecessor(Key key) {
        final BinarySearchTreeNode<Key,Value> n = this.predecessorNode(key);

        if (n == null) {
            return null;
        }
        return n.key;
    }

    /**
     * Returns the predecessor of the node with the specified key.
     * @param key The key of the node whose predecessor is to be
     *   found.
     * @return The predecessor of the node with the specified key.
     *   If no node with the specified key exists in this BST,
     *   or there is no predecessor, then null will be returned.
     *   If there are multiple nodes with the specified key in
     *   this BST then the predecessor of the first found will be
     *   returned.
     */
    public BinarySearchTreeNode<Key,Value> predecessorNode(Key key) {
        final BinarySearchTreeNode<Key,Value> n = this.find(this.root, key, null);
        return this.predecessorNode(n);
    }

    /**
     * Returns the predecessor of the specified node.
     * @param n The node whose predecessor is to be found.
     * @return The predecessor of the specified node. If
     *   the specified node has no predecessor, then null
     *   will be returned.
     */
    public BinarySearchTreeNode<Key,Value> predecessorNode(BinarySearchTreeNode<Key,Value> n) {
        if (n == null) {
            throw new IllegalStateException("predeccessorNode - No node with specified key can be found.");
        }

        // Check if n has a left subtree.
        if (n.left != null) {
            // n has a left subtree - n's predecessor is the
            // maximum.
            return this.maximum(n.left);
        }

        // n has no left subtree. Hence it is the minimum of
        // its subtree. Check if it is the minimum of its
        // supertrees.
        while (n.parent != null && n.parent.left == n) {
            n = n.parent;
        }

        if (n.parent == null) {
            // n is the minimum of all of its supertrees and hence
            // has no predecessor.
            return null;
        }

        // n is not the minimum of all of its supertrees.

        if (n.parent.left == null) {
            // Sought parent has no left subtree, but it is itself a
            // predecessor.
            return n.parent;
        }

        // General case - left subtree of candidate supertree has been found.
        // n's predecessor is then the maximum of the left subtree of the current
        // root, or the root itself, whichever has the larger key.

        final BinarySearchTreeNode<Key,Value> n1 = this.maximum(n.parent.left);
        final BinarySearchTreeNode<Key,Value> n2 = n.parent;
        final Key k1 = n1.key;
        final Key k2 = n2.key;
        return k1.compareTo(k2) > 0 ? n1 : n2;
    }

    /**
     * Remove the node with the specified key and value from this BST. If
     * no such node exists then the BST will remain unchanged.
     * @param key The key of the node to be removed.
     * @param val The value of the node to be removed. If value matching
     *   is not required then set this to null, resulting in the first
     *   found node with the specified key being removed, if one
     *   exists.
     */
    public void remove(Key key, Value val) {
        final BinarySearchTreeNode<Key,Value> n = this.findNode(key, val);
        this.remove(n);
    }

    /**
     * Remove the node with the specified key from this BST. If
     * no such node exists then the BST will remain unchanged. If
     * multiple nodes exist with the specified key, then the first
     * found will be removed.
     * @param key The key of the node to be removed.
     */
    public void remove(Key key) {
        this.remove(key, null);
    }

    /**
     * Remove the specified node from this BST.
     */
    public void remove(BinarySearchTreeNode<Key,Value> n) {
        if (n != null) {
            // 1. n is a leaf.
            if ((n.left == null) && (n.right == null)) {
                if (n.parent != null) {
                    if (n.parent.left == n) {
                        n.parent.left = null;
                    } else {
                        n.parent.right = null;
                    }
                } else {
                    this.root = null;
                }
            }
            // 2. n has a right child only.
            else if ((n.left == null) ) {
                if (n.parent != null) {
                    if (n.parent.left == n) {
                        n.parent.left = n.right;
                    } else {
                        n.parent.right = n.right;
                    }
                } else {
                    this.root = n.right;
                }
                n.right.parent = n.parent;
            }
            // 3. n has a left child only.
            else if ((n.right == null)) {
                if (n.parent != null) {
                    if (n.parent.left == n) {
                        n.parent.left = n.left;
                    } else {
                        n.parent.right = n.left;
                    }
                } else {
                    this.root = n.left;
                }
                n.left.parent = n.parent;
            }
            // 4. n has both children.
            else if ((n.left != null) && (n.right != null)) {
                if (n.parent != null) {
                    if (n.parent.right == n) {
                        n.parent.right = n.right;
                        this.minimum(n.right).left = n.left;
                        n.parent.right.parent = n.parent;
                    } else {
                        n.parent.left = n.right;
                        this.minimum(n.right).left = n.left;
                        n.parent.left.parent = n.parent;
                    }
                } else {
                    BinarySearchTreeNode<Key,Value> p = this.minimum(n.right);
                    p.left = n.left;
                    n.left.parent = p;
                    this.root = n.right;
                    this.root.parent = null;
                }
            }
        }
    }

    /**
     * Return if this BST empty (i.e., has no nodes).
     * @return If this BST is empty.
     */
    public boolean empty() {
        return this.root == null;
    }

    /**
     * Returns the number of nodes in this BinarySearchTree.
     * @return The number of nodes in this BinarySearchTree.
     */
    public int size() {
        return this.size(this.root, 0);
    }

    /**
     * Visit this BST using the specified BinarySearchTreeVisitor.
     * @param visitor The specified BinarySearchTreeVisitor.
     */
    public void visit(BinarySearchTreeVisitor<Key, Value> visitor) {
        visitor.visit(this.root);
    }

    /**
     * Print the keys of this BST in the following format:
     *    "L0: <space-separated list of keys of nodes of level 0>"
     *    "L1: <space-separated list of keys of nodes of level 1>"
     *    ...
     *
     * where each node is a printed as:
     *
     * "<key>" - non-null node
     * "null" - null node.
     */
    public void print() {
        final LinkedList<LinkedList<Key>> nodes = new LinkedList<LinkedList<Key>>();
        this.print(this.root, nodes, 0);

        for (int i = 0; i < nodes.size(); ++i) {
            final LinkedList<Key> l = nodes.get(i);
            System.out.print("L" + i + ": ");
            for (Key k : l) {
                if (k == null) {
                    System.out.print("null ");
                } else {
                    System.out.print(k.toString() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Serialise the keys of the nodes of the specified BST
     * as a '|' separated string with the following notation:
     *
     * "N" - null
     * "<key>" - non-null node
     *
     * Each node key is separated by terminating '|'s.
     */
    public String serialise() {
        final StringBuffer sb = new StringBuffer();
        this.serialise(sb, this.root);
        return sb.toString();
    }

    /**
     * Serialise the data of the nodes of the specified BST
     * as a '|' separated string with the following notation:
     *
     * "N" - null
     * "<key,value>" - non-null node
     *
     * Each node data is separated by terminating '|'s.
     */
    public String serialiseAll() {
        final StringBuffer sb = new StringBuffer();
        this.serialiseAll(sb, this.root);
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.serialiseAll();
    }
}
