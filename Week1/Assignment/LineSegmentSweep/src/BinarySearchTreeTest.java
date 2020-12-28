import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

    @Test
    void TestInsert() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();
        for (int i = 0; i < 10; ++i) {
            bst.insert(i, i);
        }

        Assertions.assertEquals("0|N|1|N|2|N|3|N|4|N|5|N|6|N|7|N|8|N|9|N|N|", bst.serialise());
    }

    @Test
    void TestInsert2() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();
        bst.insert(5,null);
        bst.insert(0,null);
        bst.insert(8,null);
        bst.insert(2,null);
        bst.insert(6,null);
        bst.insert(9,null);
        bst.insert(1,null);
        bst.insert(7,null);
        bst.insert(3,null);
        bst.insert(4,null);

        Assertions.assertEquals("5|0|N|2|1|N|N|3|N|4|N|N|8|6|N|7|N|N|9|N|N|", bst.serialise());
    }

    @Test
    void TestInsert3() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();
        bst.insert(5,null);
        bst.insert(9,null);
        bst.insert(4,null);
        bst.insert(7,null);
        bst.insert(3,null);
        bst.insert(5,null);
        bst.insert(4,null);
        bst.insert(4,null);
        bst.insert(3,null);

        Assertions.assertEquals("5|4|3|N|3|N|N|4|N|4|N|N|9|7|5|N|N|N|N|", bst.serialise());
    }

    @Test
    void TestMinimum1() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();
        bst.insert(5,null);
        bst.insert(0,null);
        bst.insert(8,null);
        bst.insert(2,null);
        bst.insert(6,null);
        bst.insert(9,null);
        bst.insert(1,null);
        bst.insert(7,null);
        bst.insert(3,null);
        bst.insert(4,null);

        Assertions.assertEquals(0, bst.minimum());
    }

    @Test
    void TestMinimum2() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();
        bst.insert(5,null);
        bst.insert(8,null);
        bst.insert(2,null);
        bst.insert(6,null);
        bst.insert(9,null);
        bst.insert(1,null);
        bst.insert(7,null);
        bst.insert(3,null);
        bst.insert(4,null);

        Assertions.assertEquals(1, bst.minimum());
    }

    @Test
    void TestFind1() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();
        bst.insert(5,345);
        bst.insert(8,2321);
        bst.insert(2,5453);
        bst.insert(6,434);
        bst.insert(5,1111);
        bst.insert(9,7868);
        bst.insert(1,2342);
        bst.insert(7,453);
        bst.insert(3,222);
        bst.insert(4,899);

        Assertions.assertEquals(345, bst.find(5));
        Assertions.assertEquals(2321, bst.find(8));
        Assertions.assertEquals(899, bst.find(4));
        Assertions.assertEquals(null, bst.find(13));
    }

    @Test
    void TestSuccessor() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(1,1);
        bst.insert(10,10);
        bst.insert(4,4);
        bst.insert(3,3);

        Assertions.assertEquals(5, bst.successor(4));
    }

    @Test
    void TestSuccessor2() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(1,1);
        bst.insert(10,10);
        bst.insert(4,4);
        bst.insert(3,3);

        Assertions.assertEquals(10, bst.successor(5));
    }

    @Test
    void TestSuccessor3() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(1,1);
        bst.insert(10,10);
        bst.insert(4,4);
        bst.insert(3,3);

        Assertions.assertEquals(null, bst.successor(10));
    }

    @Test
    void TestPredecessor() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(1,1);
        bst.insert(10,10);
        bst.insert(3,3);
        bst.insert(4,4);

        Assertions.assertEquals(4, bst.predecessor(5));
    }

    @Test
    void TestPredecessor2() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(1,1);
        bst.insert(10,10);
        bst.insert(3,3);
        bst.insert(4,4);

        Assertions.assertEquals(5, bst.predecessor(10));
    }

    @Test
    void TestPredecessor3() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(1,1);
        bst.insert(10,10);
        bst.insert(3,3);
        bst.insert(4,4);

        Assertions.assertEquals(null, bst.predecessor(1));
    }

    @Test
    void TestPredecessor4() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(1,1);
        bst.insert(10,10);
        bst.insert(3,3);
        bst.insert(4,4);

        Assertions.assertEquals(3, bst.predecessor(4));
    }

    @Test
    void TestPredecessor5() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(1,1);
        bst.insert(10,10);
        bst.insert(3,3);
        bst.insert(4,4);

        Assertions.assertEquals(5, bst.predecessor(10));
    }

    @Test
    void TestRemove() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(2,2);
        bst.insert(7,7);
        bst.insert(1,1);
        bst.insert(3,3);
        bst.insert(6,6);
        bst.insert(9,9);
        bst.insert(4,4);
        bst.insert(10,10);

        bst.remove(10,null);

        Assertions.assertEquals("5|2|1|N|N|3|N|4|N|N|7|6|N|N|9|N|N|", bst.serialise());
    }

    @Test
    void TestRemove2() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(2,2);
        bst.insert(7,7);
        bst.insert(1,1);
        bst.insert(3,3);
        bst.insert(6,6);
        bst.insert(9,9);
        bst.insert(4,4);
        bst.insert(10,10);

        bst.remove(6,null);

        Assertions.assertEquals("5|2|1|N|N|3|N|4|N|N|7|N|9|N|10|N|N|", bst.serialise());
    }

    @Test
    void TestRemove3() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(2,2);
        bst.insert(7,7);
        bst.insert(1,1);
        bst.insert(3,3);
        bst.insert(6,6);
        bst.insert(9,9);
        bst.insert(4,4);
        bst.insert(10,10);

        bst.remove(4,null);

        Assertions.assertEquals("5|2|1|N|N|3|N|N|7|6|N|N|9|N|10|N|N|", bst.serialise());
    }

    @Test
    void TestRemove4() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(2,2);
        bst.insert(7,7);
        bst.insert(1,1);
        bst.insert(3,3);
        bst.insert(6,6);
        bst.insert(9,9);
        bst.insert(4,4);
        bst.insert(10,10);

        bst.remove(9,null);

        Assertions.assertEquals("5|2|1|N|N|3|N|4|N|N|7|6|N|N|10|N|N|", bst.serialise());
    }

    @Test
    void TestRemove5() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(2,2);
        bst.insert(7,7);
        bst.insert(1,1);
        bst.insert(3,3);
        bst.insert(6,6);
        bst.insert(9,9);
        bst.insert(4,4);
        bst.insert(10,10);

        bst.remove(3,null);

        Assertions.assertEquals("5|2|1|N|N|4|N|N|7|6|N|N|9|N|10|N|N|", bst.serialise());
    }

    @Test
    void TestRemove6() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);

        bst.remove(5,null);

        Assertions.assertEquals("N|", bst.serialise());
    }

    @Test
    void TestRemove7() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(1,1);

        bst.remove(5,null);

        Assertions.assertEquals("1|N|N|", bst.serialise());
    }

    @Test
    void TestRemove8() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(6,6);

        bst.remove(5,null);

        Assertions.assertEquals("6|N|N|", bst.serialise());
    }

    @Test
    void TestRemove9() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(2,2);
        bst.insert(7,7);
        bst.insert(1,1);
        bst.insert(3,3);
        bst.insert(6,6);
        bst.insert(9,9);
        bst.insert(4,4);
        bst.insert(10,10);

        bst.remove(7,null);

        Assertions.assertEquals("5|2|1|N|N|3|N|4|N|N|9|6|N|N|10|N|N|", bst.serialise());
    }

    @Test
    void TestRemove10() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(2,2);
        bst.insert(7,7);
        bst.insert(1,1);
        bst.insert(3,3);
        bst.insert(6,6);
        bst.insert(9,9);
        bst.insert(4,4);
        bst.insert(10,10);

        bst.remove(2,null);

        Assertions.assertEquals("5|3|1|N|N|4|N|N|7|6|N|N|9|N|10|N|N|", bst.serialise());
    }

    @Test
    void TestRemove11() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(2,2);
        bst.insert(7,7);
        bst.insert(1,1);
        bst.insert(3,3);
        bst.insert(6,6);
        bst.insert(9,9);
        bst.insert(4,4);
        bst.insert(10,10);

        bst.remove(5,null);

        Assertions.assertEquals("7|6|2|1|N|N|3|N|4|N|N|N|9|N|10|N|N|", bst.serialise());
    }

    @Test
    void TestRemove12() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(2,2);
        bst.insert(7,7);
        bst.insert(1,1);
        bst.insert(3,3);
        bst.insert(6,6);
        bst.insert(9,9);
        bst.insert(4,4);
        bst.insert(10,10);

        Assertions.assertEquals("5|2|1|N|N|3|N|4|N|N|7|6|N|N|9|N|10|N|N|", bst.serialise());

        bst.remove(5,null);
        Assertions.assertEquals("7|6|2|1|N|N|3|N|4|N|N|N|9|N|10|N|N|", bst.serialise());

        bst.remove(6,null);
        Assertions.assertEquals("7|2|1|N|N|3|N|4|N|N|9|N|10|N|N|", bst.serialise());

        bst.remove(7,null);
        Assertions.assertEquals("9|2|1|N|N|3|N|4|N|N|10|N|N|", bst.serialise());

        bst.remove(3,null);
        Assertions.assertEquals("9|2|1|N|N|4|N|N|10|N|N|", bst.serialise());

        bst.remove(2,null);
        Assertions.assertEquals("9|4|1|N|N|N|10|N|N|", bst.serialise());

        bst.remove(4,null);
        Assertions.assertEquals("9|1|N|N|10|N|N|", bst.serialise());

        bst.remove(4,null);
        Assertions.assertEquals("9|1|N|N|10|N|N|", bst.serialise());

        bst.remove(9,null);
        Assertions.assertEquals("10|1|N|N|N|", bst.serialise());
    }

    @Test
    void TestEmpty() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        Assertions.assertFalse(bst.empty());
    }

    @Test
    void TestEmpty2() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        Assertions.assertTrue(bst.empty());
    }

    @Test
    void TestSize1() {
        final BinarySearchTree<Integer,Integer> bst
                = new BinarySearchTree<Integer,Integer>();

        Assertions.assertEquals(0, bst.size());
    }

    @Test
    void TestSize2() {
        final BinarySearchTree<Integer,Integer> bst
            = new BinarySearchTree<Integer,Integer>();

        bst.insert(5,5);
        bst.insert(2,2);
        bst.insert(7,7);
        bst.insert(1,1);
        bst.insert(3,3);
        bst.insert(6,6);
        bst.insert(9,9);
        bst.insert(4,4);
        bst.insert(10,10);

        Assertions.assertEquals(9, bst.size());
    }
}
