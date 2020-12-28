import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RetrieveNodeByValueVisitorTest {

    @Test
    void testVisit1() {

        final BinarySearchTree<ComparableInteger, LineSegment> bst
            = new BinarySearchTree<ComparableInteger, LineSegment>();

        final Coordinate s = new Coordinate(0,0);
        final Coordinate e = new Coordinate(1,1);
        final LineSegment[] lss = new LineSegment[5];
        for (int i = 0; i < 5; ++i) {
            lss[i] = new LineSegment(i,s,e);
            bst.insert(new ComparableInteger(i), lss[i]);
        }

        final RetrieveNodeByValueVisitor v = new RetrieveNodeByValueVisitor(lss[3]);
        bst.visit(v);

        final BinarySearchTreeNode<ComparableInteger,LineSegment> n
            = v.getNode();

        assertNotEquals(null, n);
        assertEquals(lss[3].id, n.value.id);
    }

    @Test
    void testVisit2() {

        final BinarySearchTree<ComparableInteger, LineSegment> bst
            = new BinarySearchTree<ComparableInteger, LineSegment>();

        final Coordinate s = new Coordinate(0,0);
        final Coordinate e = new Coordinate(1,1);
        final LineSegment[] lss = new LineSegment[5];
        for (int i = 0; i < 5; ++i) {
            lss[i] = new LineSegment(i,s,e);
            bst.insert(new ComparableInteger(i), lss[i]);
        }

        final RetrieveNodeByValueVisitor v = new RetrieveNodeByValueVisitor(lss[0]);
        bst.visit(v);

        final BinarySearchTreeNode<ComparableInteger,LineSegment> n
            = v.getNode();

        assertNotEquals(null, n);
        assertEquals(lss[0].id, n.value.id);
    }

    @Test
    void testVisit3() {

        final BinarySearchTree<ComparableInteger, LineSegment> bst
            = new BinarySearchTree<ComparableInteger, LineSegment>();

        final Coordinate s = new Coordinate(0,0);
        final Coordinate e = new Coordinate(1,1);
        final LineSegment[] lss = new LineSegment[5];
        for (int i = 0; i < 5; ++i) {
            lss[i] = new LineSegment(i,s,e);
            bst.insert(new ComparableInteger(i), lss[i]);
        }

        final RetrieveNodeByValueVisitor v = new RetrieveNodeByValueVisitor(lss[4]);
        bst.visit(v);

        final BinarySearchTreeNode<ComparableInteger,LineSegment> n
            = v.getNode();

        assertNotEquals(null, n);
        assertEquals(lss[4].id, n.value.id);
    }

    @Test
    void testVisit4() {

        final BinarySearchTree<ComparableInteger, LineSegment> bst
            = new BinarySearchTree<ComparableInteger, LineSegment>();

        final Coordinate s = new Coordinate(0,0);
        final Coordinate e = new Coordinate(1,1);
        final LineSegment[] lss = new LineSegment[5];
        for (int i = 0; i < 5; ++i) {
            lss[i] = new LineSegment(i,s,e);

            if (i != 4) {
                bst.insert(new ComparableInteger(i), lss[i]);
            }
        }

        final RetrieveNodeByValueVisitor v = new RetrieveNodeByValueVisitor(lss[4]);
        bst.visit(v);

        final BinarySearchTreeNode<ComparableInteger,LineSegment> n
            = v.getNode();

        assertEquals(null, n);
    }

}
