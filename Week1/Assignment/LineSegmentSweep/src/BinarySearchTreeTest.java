import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	@Test
	void test0() {
		final BinarySearchTree tree = new BinarySearchTree();
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		
		assertEquals(tree.toString(), "5 ");
	}

	@Test
	void test1() {
		final BinarySearchTree tree = new BinarySearchTree();
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		
		assertEquals("3 5 ", tree.toString());
	}

	@Test
	void test2() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(4, new Event(EventType.UNKNOWN,null,null));

		assertEquals("1 3 4 5 ", tree.toString());
	}

	@Test
	void test3() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(4, new Event(EventType.UNKNOWN,null,null));
		tree.insert(9, new Event(EventType.UNKNOWN,null,null));
		tree.insert(8, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(6, new Event(EventType.UNKNOWN,null,null));
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		
		assertEquals("0 1 2 3 4 5 6 7 8 9 ", tree.toString());
	}
	
	@Test
	void test4() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(4, new Event(EventType.UNKNOWN,null,null));
		tree.insert(9, new Event(EventType.UNKNOWN,null,null));
		tree.insert(8, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(6, new Event(EventType.UNKNOWN,null,null));
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		
		BSTNode b = tree.find(5);
		assertEquals(5, b.key);
	}

	@Test
	void test5() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(4, new Event(EventType.UNKNOWN,null,null));
		tree.insert(9, new Event(EventType.UNKNOWN,null,null));
		tree.insert(8, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(6, new Event(EventType.UNKNOWN,null,null));
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		
		BSTNode b = tree.find(8);
		assertEquals(8, b.key);
	}

	@Test
	void test6() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(4, new Event(EventType.UNKNOWN,null,null));
		tree.insert(9, new Event(EventType.UNKNOWN,null,null));
		tree.insert(8, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(6, new Event(EventType.UNKNOWN,null,null));
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		
		BSTNode b = tree.find(1);
		assertEquals(1, b.key);
	}
	
	@Test
	void test7() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		
		assertEquals(3, tree.findSuccessor(1).key);
	}
	
	@Test
	void test8() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		
		assertEquals(5, tree.findSuccessor(3).key);
	}

	@Test
	void test9() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		
		assertEquals(7, tree.findSuccessor(5).key);
	}

	@Test
	void test10() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		
		assertEquals(null, tree.findSuccessor(7));
	}

	@Test
	void test11() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		
		assertEquals(null, tree.findPredecessor(1));
	}

	@Test
	void test12() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		
		assertEquals(1, tree.findPredecessor(3).key);
	}
	
	@Test
	void test13() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		
		assertEquals(3, tree.findPredecessor(5).key);
	}

	@Test
	void test14() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		
		assertEquals(5, tree.findPredecessor(7).key);
	}
	
	@Test
	void testEraseNode1() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		
		tree.erase(2);
		
		final String actual = tree.toString();
		final String expected = "0 1 ";
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testEraseNode2() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		
		tree.erase(3);
		
		final String actual = tree.toString();
		final String expected = "0 1 2 ";
		
		assertEquals(expected, actual);
	}

	@Test
	void testEraseNode3() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		
		tree.erase(2);
		
		final String actual = tree.toString();
		final String expected = "0 1 3 ";
		
		assertEquals(expected, actual);
	}

	@Test
	void testEraseNode4() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(4, new Event(EventType.UNKNOWN,null,null));
		
		tree.erase(3);
		
		final String actual = tree.toString();
		final String expected = "0 1 2 4 ";
		
		assertEquals(expected, actual);
	}

	@Test
	void testEraseNode5() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(4, new Event(EventType.UNKNOWN,null,null));
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		
		tree.erase(3);
		
		final String actual = tree.toString();
		final String expected = "0 1 2 4 5 ";
		
		assertEquals(expected, actual);
	}

	@Test
	void testEraseNode6() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(5, new Event(EventType.UNKNOWN,null,null));
		tree.insert(4, new Event(EventType.UNKNOWN,null,null));
		
		tree.erase(3);
		
		final String actual = tree.toString();
		final String expected = "0 1 2 4 5 ";
		
		assertEquals(expected, actual);
	}

	@Test
	void testEraseNode7() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(9, new Event(EventType.UNKNOWN,null,null));
		tree.insert(4, new Event(EventType.UNKNOWN,null,null));
		tree.insert(7, new Event(EventType.UNKNOWN,null,null));
		tree.insert(6, new Event(EventType.UNKNOWN,null,null));
		tree.insert(8, new Event(EventType.UNKNOWN,null,null));
		
		tree.erase(3);
		
		final String actual = tree.toString();
		final String expected = "0 1 2 4 6 7 8 9 ";
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testEraseNode8() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		tree.insert(-2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(-1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(-3, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		
		tree.erase(0);
		
		final String actual = tree.toString();
		final String expected = "-3 -2 -1 1 2 3 ";
		
		assertEquals(expected, actual);
	}

	@Test
	void testEraseNode9() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		tree.insert(2, new Event(EventType.UNKNOWN,null,null));
		tree.insert(1, new Event(EventType.UNKNOWN,null,null));
		tree.insert(3, new Event(EventType.UNKNOWN,null,null));
		
		tree.erase(0);
		
		final String actual = tree.toString();
		final String expected = "1 2 3 ";
		
		assertEquals(expected, actual);
	}


	@Test
	void testEraseNode0() {
		final BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(0, new Event(EventType.UNKNOWN,null,null));
		
		tree.erase(0);
		
		final String actual = tree.toString();
		final String expected = "";
		
		assertEquals(expected, actual);
	}

}
