import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RetrieveInsertionKeyVisitorTest {

    @Test
    void TestVisit1() {

        final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();

        final Coordinate s1 = new Coordinate(-6,1);
        final Coordinate e1 = new Coordinate(1,-6);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(-1,-6);
        final Coordinate e2 = new Coordinate(6,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        // LineSegment that is currently first in status.
        bst.insert(new ComparableInteger(0), ls1);

        // LineSegment that is currently second in status.
        bst.insert(new ComparableInteger(1), ls2);

        Assertions.assertEquals("0|N|1|N|N|", bst.serialise());

        final Coordinate c = new Coordinate(0,0);

        final RetrieveInsertionKeyVisitor v = new RetrieveInsertionKeyVisitor(c);
        bst.visit(v);

        final ComparableInteger ik = v.getInsertionKey();
        assertNotEquals(null, ik);
        assertEquals(0, ik.i);
    }

    @Test
    void TestVisit2() {

        final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();

        final Coordinate s1 = new Coordinate(-6,1);
        final Coordinate e1 = new Coordinate(1,-6);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(-1,-6);
        final Coordinate e2 = new Coordinate(6,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        // LineSegment that is currently first in status.
        bst.insert(new ComparableInteger(0), ls1);

        // LineSegment that is currently second in status.
        bst.insert(new ComparableInteger(1), ls2);

        Assertions.assertEquals("0|N|1|N|N|", bst.serialise());

        final Coordinate c = new Coordinate(-6,0);

        final RetrieveInsertionKeyVisitor v = new RetrieveInsertionKeyVisitor(c);
        bst.visit(v);

        final ComparableInteger ik = v.getInsertionKey();
        assertEquals(null, ik);
    }

    @Test
    void TestVisit3() {

        final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();

        final Coordinate s1 = new Coordinate(-6,1);
        final Coordinate e1 = new Coordinate(1,-6);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(-1,-6);
        final Coordinate e2 = new Coordinate(6,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        // LineSegment that is currently first in status.
        bst.insert(new ComparableInteger(0), ls1);

        // LineSegment that is currently second in status.
        bst.insert(new ComparableInteger(1), ls2);

        Assertions.assertEquals("0|N|1|N|N|", bst.serialise());

        final Coordinate c = new Coordinate(6,0);

        final RetrieveInsertionKeyVisitor v = new RetrieveInsertionKeyVisitor(c);
        bst.visit(v);

        final ComparableInteger ik = v.getInsertionKey();
        assertNotEquals(null, ik);
        assertEquals(1, ik.i);
    }

    @Test
    void TestVisit4() {

        final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();

        final Coordinate s1 = new Coordinate(-3,0);
        final Coordinate e1 = new Coordinate(-2,0);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(-1,-2);
        final Coordinate e2 = new Coordinate(2,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        // LineSegment that is currently first in status.
        bst.insert(new ComparableInteger(0), ls1);

        // LineSegment that is currently second in status.
        bst.insert(new ComparableInteger(1), ls2);

        Assertions.assertEquals("0|N|1|N|N|", bst.serialise());

        final Coordinate c = new Coordinate(-4,0);

        final RetrieveInsertionKeyVisitor v = new RetrieveInsertionKeyVisitor(c);
        bst.visit(v);

        final ComparableInteger ik = v.getInsertionKey();
        assertEquals(null, ik);
    }

    @Test
    void TestVisit5() {

        final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();

        final Coordinate s1 = new Coordinate(-3,0);
        final Coordinate e1 = new Coordinate(-2,0);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(-1,-2);
        final Coordinate e2 = new Coordinate(2,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        // LineSegment that is currently first in status.
        bst.insert(new ComparableInteger(0), ls1);

        // LineSegment that is currently second in status.
        bst.insert(new ComparableInteger(1), ls2);

        Assertions.assertEquals("0|N|1|N|N|", bst.serialise());

        final Coordinate c = new Coordinate(-2.5,0);

        final RetrieveInsertionKeyVisitor v = new RetrieveInsertionKeyVisitor(c);
        bst.visit(v);

        final ComparableInteger ik = v.getInsertionKey();
        assertNotEquals(null, ik);
        assertEquals(0, ik.i);
    }

    @Test
    void TestVisit6() {

        final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();

        final Coordinate s1 = new Coordinate(-3,0);
        final Coordinate e1 = new Coordinate(-2,0);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(-1,-2);
        final Coordinate e2 = new Coordinate(2,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        // LineSegment that is currently first in status.
        bst.insert(new ComparableInteger(0), ls1);

        // LineSegment that is currently second in status.
        bst.insert(new ComparableInteger(1), ls2);

        Assertions.assertEquals("0|N|1|N|N|", bst.serialise());

        final Coordinate c = new Coordinate(0,0);

        final RetrieveInsertionKeyVisitor v = new RetrieveInsertionKeyVisitor(c);
        bst.visit(v);

        final ComparableInteger ik = v.getInsertionKey();
        assertNotEquals(null, ik);
        assertEquals(0, ik.i);
    }

    @Test
    void TestVisit7() {

        final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();

        final Coordinate s1 = new Coordinate(-3,0);
        final Coordinate e1 = new Coordinate(-2,0);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(-1,-2);
        final Coordinate e2 = new Coordinate(2,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        // LineSegment that is currently first in status.
        bst.insert(new ComparableInteger(0), ls1);

        // LineSegment that is currently second in status.
        bst.insert(new ComparableInteger(1), ls2);

        Assertions.assertEquals("0|N|1|N|N|", bst.serialise());

        final Coordinate c = new Coordinate(1,0);

        final RetrieveInsertionKeyVisitor v = new RetrieveInsertionKeyVisitor(c);
        bst.visit(v);

        final ComparableInteger ik = v.getInsertionKey();
        assertNotEquals(null, ik);
        assertEquals(1, ik.i);
    }

    @Test
    void TestVisit8() {

        final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();

        final Coordinate s1 = new Coordinate(-3,0);
        final Coordinate e1 = new Coordinate(-2,0);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(-1,-2);
        final Coordinate e2 = new Coordinate(2,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        // LineSegment that is currently first in status.
        bst.insert(new ComparableInteger(0), ls1);

        // LineSegment that is currently second in status.
        bst.insert(new ComparableInteger(1), ls2);

        Assertions.assertEquals("0|N|1|N|N|", bst.serialise());

        final Coordinate c = new Coordinate(2,0);

        final RetrieveInsertionKeyVisitor v = new RetrieveInsertionKeyVisitor(c);
        bst.visit(v);

        final ComparableInteger ik = v.getInsertionKey();
        assertNotEquals(null, ik);
        assertEquals(1, ik.i);
    }

    @Test
    void TestVisit9() {

    	final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();

    	final Coordinate s1 = new Coordinate(-3,0);
    	final Coordinate e1 = new Coordinate(-2,0);
    	final LineSegment ls1 = new LineSegment(1,e1,s1);

    	final Coordinate s2 = new Coordinate(-1,-2);
    	final Coordinate e2 = new Coordinate(2,1);
    	final LineSegment ls2 = new LineSegment(2,s2,e2);

    	// LineSegment that is currently first in status.
        bst.insert(new ComparableInteger(0), ls1);

    	// LineSegment that is currently second in status.
        bst.insert(new ComparableInteger(1), ls2);

        Assertions.assertEquals("0|N|1|N|N|", bst.serialise());

    	final Coordinate c = new Coordinate(-2.5,0);

        final RetrieveInsertionKeyVisitor v = new RetrieveInsertionKeyVisitor(c);
        bst.visit(v);

        final ComparableInteger ik = v.getInsertionKey();
        assertNotEquals(null, ik);
        assertEquals(0, ik.i);
    }
}
