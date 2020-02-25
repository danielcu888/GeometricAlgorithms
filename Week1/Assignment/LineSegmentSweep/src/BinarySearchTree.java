
public class BinarySearchTree {
	
	private BSTNode root = null;
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		this.serialise(this.root, sb);
		return sb.toString();
	}
	
	private void serialise(BSTNode node, StringBuilder sb) {
		if (node != null) {
			this.serialise(node.left, sb);
			sb.append(node.key + " ");
			this.serialise(node.right, sb);
		}
	}
		
	public BinarySearchTree() {
		this.root = null;
	}

	private BSTNode find(BSTNode node, int key) {
		if (node == null) {
			return null;
		} else if (key < node.key) {
			return this.find(node.left, key);
		} else if (key > node.key) {
			return this.find(node.right, key);
		} else {
			return node;
		}
	}
	
	public BSTNode find(int key) {
		BSTNode n = this.find(this.root, key);
		if (n.event == null) {
			return null;
		}
		
		return n;
	}
	
	private BSTNode findMinimum(BSTNode node) {
		if (node == null) {
			return null;
		}
				
		while (node.left != null) {
			node = node.left;
		}
		
		return node;
	}

	private BSTNode findMaximum(BSTNode node) {
		if (node == null) {
			return null;
		}
				
		while (node.right != null) {
			node = node.right;
		}
		
		return node;
	}
	
	private BSTNode findSuccessor(BSTNode node) {
		
		if (node.right != null) {
			return this.findMinimum(node.right);
		}
		
		BSTNode n = node;
		BSTNode p = n.parent;
		while (p != null && p.key <= node.key) {
			n = p;
			p = p.parent;
		}
		
		if (p == null) {
			return null;
		}
		
		if (p.right == null) {
			return p;
		}

		final BSTNode q = this.findMinimum(p.right);
		if (q != null && q.key < p.key) {
			return q;
		}
		
		return p;
	}
	
	public BSTNode findSuccessor(int key) {
		final BSTNode n = this.find(key);
		if (n == null) {
			return null;
		}
		
		BSTNode suc = n;
		do {
			suc = this.findSuccessor(suc);
		} while (suc != null && suc.event != null);
		
		return this.findSuccessor(n);
	}

	private BSTNode findPredecessor(BSTNode node) {
		
		if (node.left != null) {
			return this.findMaximum(node.left);
		}
		
		BSTNode n = node;
		BSTNode p = n.parent;
		while (p != null && p.key >= node.key) {
			n = p;
			p = p.parent;
		}

		if (p == null) {
			return null;
		}
		
		if (p.left == null) {
			return p;
		}

		final BSTNode q = this.findMaximum(p.left);
		if (q != null && q.key > p.key) {
			return q;
		}

		return p;
	}
	
	public BSTNode findPredecessor(int key) {
		final BSTNode n = this.find(key);
		if (n == null) {
			return null;
		}
		
		return this.findPredecessor(n);
	}
	
	private BSTNode insert(BSTNode node, int key, Event event) {
		final int k = node.key;
		if (key < k) {
			if (node.left != null) {
				node.left = this.insert(node.left, key, event);
			} else {
				node.left = new BSTNode(key, event, null, null, node);
			}
		} else if (key > k) {
			if (node.right != null) {
				node.right = this.insert(node.right, key, event);
			} else {
				node.right = new BSTNode(key, event, null, null, node);
			}
		} else {
			node.event = event;
		}

		return node;
	}	
	
	public void insert(int key, Event event) {
		if (this.root == null) {
			this.root = new BSTNode(key,event,null,null,null);
		} else {
			this.root = this.insert(this.root, key, event);
		}
	}
	
	public void erase(int key) {
		
		final BSTNode node = this.find(key);
		
		if (node != null) {
			if (node.parent == null) {
				// 0. Null parent.
				if (node.isLeaf()) {
					this.root = null;
				} else if (node.left != null) {
					final BSTNode pre = this.findPredecessor(node);
					pre.right = node.right;
					pre.right.parent = pre;
					this.root = node.left;
					this.root.parent = null;
				} else {
					this.root = node.right;
					if (this.root != null) {
						this.root.parent = null;
					}					
				}
			}
			else if (node.isLeaf()) {
				// 1. Non-null parent and no children.
				if (node.parent.left == node) {
					node.parent.left = null;
				} else {
					node.parent.right = null;
				}
			}
			else if (node.left != null && node.right == null) {
				// 2. Left child, no right child.
				final BSTNode tmp = node.left;
				node.key = tmp.key;
				node.event = tmp.event;
				node.left = tmp.left;
				node.right = tmp.right;				
			}
			else if (node.right != null && node.left == null) {
				// 3. Right child, no left child.
				final BSTNode tmp = node.right;
				node.key = tmp.key;
				node.event = tmp.event;
				node.left = tmp.left;
				node.right = tmp.right;				
			}
			else {
				if (node.right.left == null) {
					// 4,5. Has two children. Right child has no left child.
					final BSTNode tmp = node.right;
					node.key = tmp.key;
					node.event = tmp.event;
					node.right = tmp.right;
				}
				else {
					final BSTNode rmin = this.findMinimum(node.right);
					if ((rmin == this.findSuccessor(node))) {
						if (rmin.isLeaf()) {     
							// 6. X has two children. Right child has minimum that is the
							// successor of X, and it is a leaf.
							rmin.parent.left = null;
							node.key = rmin.key;
							node.event = rmin.event;
						} else {
							// 7. X has two children. Right child has minimum that is the
							// successor of X, and it is not a leaf.
							node.key = rmin.key;
							node.event = rmin.event;
							final BSTNode tmp = rmin.right;
							rmin.key = tmp.key;
							rmin.event = tmp.event;
							rmin.left = tmp.left;
							rmin.right = tmp.right;						
						}
					}
				}
			}	
		}
	}
}
