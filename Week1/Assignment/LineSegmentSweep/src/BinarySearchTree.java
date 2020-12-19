import java.util.LinkedList;

class BinarySearchTreeNode<Key extends Comparable<Key>, Value> {

    public BinarySearchTreeNode(Key key_, Value val_) {
        this.key = key_;
        this.value = val_;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public Key key;
    public Value value;
    public BinarySearchTreeNode<Key,Value> left;
    public BinarySearchTreeNode<Key,Value> right;
    public BinarySearchTreeNode<Key,Value> parent;
}

interface BinarySearchTreeVisitor<Key extends Comparable<Key>, Value> {

    public void visit(BinarySearchTreeNode<Key, Value> node);
}

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private BinarySearchTreeNode<Key, Value> root;

    private BinarySearchTreeNode<Key,Value> minimum(BinarySearchTreeNode<Key,Value> root) {
        if (root == null) {
            return null;
        } else if (root.left != null) {
            return this.minimum(root.left);
        } else {
            return root;
        }
    }

    private BinarySearchTreeNode<Key,Value> insert(BinarySearchTreeNode<Key,Value> curr, BinarySearchTreeNode<Key,Value> n) {
        if (curr == null) {
            return n;
        }

        if (n.key.compareTo(curr.key) < 0) {
            curr.left = this.insert(curr.left, n);
            curr.left.parent = curr;
        } else {
            curr.right = this.insert(curr.right, n);
            curr.right.parent = curr;
        }

        return curr;
    }

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

    private BinarySearchTreeNode<Key,Value>
        find(BinarySearchTreeNode<Key,Value> root, Key key, Value val) {
        if (root == null) {
            return null;
        }

        final int c = key.compareTo(root.key);

        if (c == 0) {
            if ((val == null) || (root.value == val)) {
                return root;
            }
            while (root.right != null) {
                this.find(root.right, key, val);
            }
            return null;
        } else if (c < 0) {
            return this.find(root.left, key, val);
        }

        return this.find(root.right, key, val);
    }

    private BinarySearchTreeNode<Key,Value> maximum(BinarySearchTreeNode<Key,Value> n) {
        if (n == null) {
            return null;
        } else if (n.right == null) {
            return n;
        } else {
            return this.maximum(n.right);
        }
    }

    public BinarySearchTreeNode<Key,Value> findNode(Key key, Value val) {
        final BinarySearchTreeNode<Key,Value> n = this.find(this.root, key, val);
        return n;
    }

    private void serialise(StringBuffer sb, BinarySearchTreeNode<Key,Value> n) {
        if (n == null) {
            sb.append("N|");
        } else {
            sb.append(n.key + "|");

            this.serialise(sb, n.left);
            this.serialise(sb, n.right);
        }
    }

    private void serialiseAll(StringBuffer sb, BinarySearchTreeNode<Key,Value> n) {
        if (n == null) {
            sb.append("N|");
        } else {
            sb.append(n.key + "," + n.value + "|");

            this.serialiseAll(sb, n.left);
            this.serialiseAll(sb, n.right);
        }
    }

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(Key key, Value val) {
        BinarySearchTreeNode<Key,Value> n
            = new BinarySearchTreeNode<Key,Value>(key, val);

        this.root = this.insert(this.root, n);
    }

    public Key minimum() {
        if (this.root == null) {
            throw new IllegalStateException("mimimum - empty tree.");
        }

        return this.minimum(this.root).key;
    }

    public Key maximum() {
        if (this.root == null) {
            throw new IllegalStateException("maximum - empty tree.");
        }

        return this.maximum(this.root).key;
    }

    public Value find(Key key) {
        final BinarySearchTreeNode<Key,Value> n = this.find(this.root, key, null);
        if (n != null) {
            return n.value;
        }

        return null;
    }

    public Key successor(Key key) {
        final BinarySearchTreeNode<Key,Value> n = this.successorNode(key);
        if (n == null) {
            return null;
        }

        return n.key;
    }

    public BinarySearchTreeNode<Key,Value> successorNode(Key key) {
        final BinarySearchTreeNode<Key,Value> n = this.find(this.root, key, null);
        return this.successorNode(n);
    }

    public BinarySearchTreeNode<Key,Value> successorNode(BinarySearchTreeNode<Key,Value> n) {
        if (n == null) {
            throw new IllegalStateException("successorNode - No node with specified key can be found.");
        }

        if (n.right != null) {
            return this.minimum(n.right);
        }

        while (n.parent != null && n.parent.right == n) {
            n = n.parent;
        }

        if (n.parent == null) {
            return null;
        }

        if (n.parent.right == null) {
            return n.parent;
        }

        final BinarySearchTreeNode<Key,Value> n1 = this.minimum(n.parent.right);
        final BinarySearchTreeNode<Key,Value> n2 = n.parent;
        final Key k1 = n1.key;
        final Key k2 = n2.key;
        return k1.compareTo(k2) < 0 ? n1 : n2;
    }

    public Key predecessor(Key key) {
        final BinarySearchTreeNode<Key,Value> n = this.predecessorNode(key);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    public BinarySearchTreeNode<Key,Value> predecessorNode(Key key) {
        final BinarySearchTreeNode<Key,Value> n = this.find(this.root, key, null);
        return this.predecessorNode(n);
    }

    public BinarySearchTreeNode<Key,Value> predecessorNode(BinarySearchTreeNode<Key,Value> n) {
        if (n == null) {
            throw new IllegalStateException("predeccessorNode - No node with specified key can be found.");
        }

        if (n.left != null) {
            return this.maximum(n.left);
        }

        while (n.parent != null && n.parent.left == n) {
            n = n.parent;
        }

        if (n.parent == null) {
            return null;
        }

        if (n.parent.left == null) {
            return n.parent;
        }

        final BinarySearchTreeNode<Key,Value> n1 = this.maximum(n.parent.left);
        final BinarySearchTreeNode<Key,Value> n2 = n.parent;
        final Key k1 = n1.key;
        final Key k2 = n2.key;
        return k1.compareTo(k2) > 0 ? n1 : n2;
    }

    public void remove(Key key, Value val) {
        final BinarySearchTreeNode<Key,Value> n = this.findNode(key, val);
        this.remove(n);
    }

    public void remove(Key key) {
        this.remove(key, null);
    }

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

    public boolean empty() {
        return this.root == null;
    }

    public void visit(BinarySearchTreeVisitor<Key, Value> visitor) {
        visitor.visit(this.root);
    }

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

    public String serialise() {
        final StringBuffer sb = new StringBuffer();
        this.serialise(sb, this.root);
        return sb.toString();
    }

    public String serialiseAll() {
        final StringBuffer sb = new StringBuffer();
        this.serialiseAll(sb, this.root);
        return sb.toString();
    }
}
