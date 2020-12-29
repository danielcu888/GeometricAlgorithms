import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StatusTest {

    @Test
    void TestConstruction() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        Assertions.assertTrue(st.empty());
    }

    @Test
    void TestInsertion1() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s = new Coordinate(0,0);
        final Coordinate e = new Coordinate(1,1);
        final LineSegment ls = new LineSegment(1,s,e);
        st.insert(ls);

        Assertions.assertEquals( "Status{0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|}"
                               , st.toString()
                               );
    }

    @Test
    void TestInsertion2() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);
        st.insert(ls1);

        final Coordinate s2 = new Coordinate(1,1);
        final Coordinate e2 = new Coordinate(3,3);
        final LineSegment ls2 = new LineSegment(2,s2,e2);
        st.insert(ls2);

        Assertions.assertEquals("Status{0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|" +
                                       "1,L[2,C(1.0,1.0),C(3.0,3.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );
    }

    @Test
    void TestInsertion3() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);
        st.insert(ls1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);
        st.insert(ls2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);
        st.insert(ls3);

        Assertions.assertEquals("Status{0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|" +
                                       "1,L[3,C(1.0,0.0),C(2.0,1.0)]|N|N|N|" +
                                      "}"
                               , st.toString()
                               );
    }

    @Test
    void TestInsertion5() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls3);
        st.insert(ls2);
        st.insert(ls1);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|" +
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );
    }

    @Test
    void TestInsertion6() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls3);
        st.insert(ls1);
        st.insert(ls2);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|" +
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );
    }

    @Test
    void TestRemove1() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls3);
        st.insert(ls1);
        st.insert(ls2);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|" +
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );

        st.remove(ls1);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );
    }

    @Test
    void TestRemove2() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls3);
        st.insert(ls1);
        st.insert(ls2);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|" +
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );

        st.remove(ls3);

        Assertions.assertEquals("Status{2,L[2,C(2.0,0.0),C(3.0,1.0)]|" +
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|N|" +
                                      "}"
                               , st.toString()
                               );
    }

    @Test
    void TestRemove3() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls3);
        st.insert(ls1);
        st.insert(ls2);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|" +
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );

        st.remove(ls2);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|"+
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|"+
                                       "N|N|N|"+
                                      "}"
                               , st.toString()
                               );
    }

    @Test
    void TestRemove4() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls3);
        st.insert(ls1);
        st.insert(ls2);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|" +
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );

        st.remove(ls2);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|"+
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|"+
                                       "N|N|N|"+
                                      "}"
                               , st.toString()
                               );

        st.remove(ls1);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|"+
                                       "N|N|"+
                                      "}"
                               , st.toString()
                               );

        st.remove(ls3);
        Assertions.assertTrue(st.empty());
    }

    @Test
    void TestPredecessor1() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls1);
        st.insert(ls2);
        st.insert(ls3);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred
            = st.predecessor(ls1);
        Assertions.assertEquals(null, pred);
    }

    @Test
    void TestPredecessor2() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls1);
        st.insert(ls2);
        st.insert(ls3);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred
            = st.predecessor(ls2);
        Assertions.assertNotEquals(null, pred);
        Assertions.assertEquals(1, pred.key.i);
        Assertions.assertEquals(ls3, pred.value);
    }

    @Test
    void TestPredecessor3() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls1);
        st.insert(ls2);
        st.insert(ls3);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred
            = st.predecessor(ls3);
        Assertions.assertNotEquals(null, pred);
        Assertions.assertEquals(0, pred.key.i);
        Assertions.assertEquals(ls1, pred.value);
    }

    @Test
    void TestSuccessor1() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls1);
        st.insert(ls2);
        st.insert(ls3);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> succ
            = st.successor(ls1);
        Assertions.assertNotEquals(null, succ);
        Assertions.assertEquals(1, succ.key.i);
        Assertions.assertEquals(ls3, succ.value);
    }

    @Test
    void TestSuccessor2() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls1);
        st.insert(ls2);
        st.insert(ls3);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> succ
            = st.successor(ls2);
        Assertions.assertEquals(null, succ);
    }

    @Test
    void TestSuccessor3() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls1);
        st.insert(ls2);
        st.insert(ls3);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> succ
            = st.successor(ls3);
        Assertions.assertNotEquals(null, succ);
        Assertions.assertEquals(2, succ.key.i);
        Assertions.assertEquals(ls2, succ.value);
    }

    @Test
    void TestSwap1() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls3);
        st.insert(ls2);
        st.insert(ls1);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|" +
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );

        st.swap(ls1, ls2);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|" +
                                       "0,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                       "2,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );
    }

    @Test
    void TestSwap2() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls3);
        st.insert(ls2);
        st.insert(ls1);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|" +
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );

        st.swap(ls1, ls3);

        Assertions.assertEquals("Status{1,L[1,C(0.0,0.0),C(1.0,1.0)]|" +
                                       "0,L[3,C(1.0,0.0),C(2.0,1.0)]|N|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );
    }

    @Test
    void TestSwap3() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(1,0);
        final Coordinate e3 = new Coordinate(2,1);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        st.insert(ls3);
        st.insert(ls2);
        st.insert(ls1);

        Assertions.assertEquals("Status{1,L[3,C(1.0,0.0),C(2.0,1.0)]|" +
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|" +
                                       "2,L[2,C(2.0,0.0),C(3.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );

        st.swap(ls2, ls3);

        Assertions.assertEquals("Status{1,L[2,C(2.0,0.0),C(3.0,1.0)]|" +
                                       "0,L[1,C(0.0,0.0),C(1.0,1.0)]|N|N|" +
                                       "2,L[3,C(1.0,0.0),C(2.0,1.0)]|N|N|" +
                                      "}"
                               , st.toString()
                               );
    }

    @Test
    void TestEmpty() {
        final Status st = new Status();
        Assertions.assertNotEquals(null, st);

        final Coordinate s = new Coordinate(0,0);
        final Coordinate e = new Coordinate(1,1);
        final LineSegment ls = new LineSegment(1,s,e);

        st.insert(ls);

        Assertions.assertFalse(st.empty());
    }
}
