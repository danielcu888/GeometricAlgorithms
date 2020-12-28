import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IncrementKeyVisitorTest {

    @Test
    void TestVisit1() {
        final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();
        for (int i = 0; i < 10; ++i) {
            bst.insert(new ComparableInteger(i), null);
        }

        Assertions.assertEquals(
            "0|N|1|N|2|N|3|N|4|N|5|N|6|N|7|N|8|N|9|N|N|",
            bst.serialise()
            );

        final IncrementKeyVisitor v
            = new IncrementKeyVisitor(new ComparableInteger(3));
        bst.visit(v);

        Assertions.assertEquals(
            "0|N|1|N|2|N|4|N|5|N|6|N|7|N|8|N|9|N|10|N|N|",
            bst.serialise()
            );
    }

    @Test
    void TestVisit2() {
        final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();
        for (int i = 0; i < 10; ++i) {
            bst.insert(new ComparableInteger(i), null);
        }

        Assertions.assertEquals(
            "0|N|1|N|2|N|3|N|4|N|5|N|6|N|7|N|8|N|9|N|N|",
            bst.serialise()
            );

        final IncrementKeyVisitor v
            = new IncrementKeyVisitor(new ComparableInteger(-10));
        bst.visit(v);

        Assertions.assertEquals(
            "1|N|2|N|3|N|4|N|5|N|6|N|7|N|8|N|9|N|10|N|N|",
            bst.serialise()
            );
    }

    @Test
    void TestVisit3() {
        final BinarySearchTree<ComparableInteger,LineSegment> bst
            = new BinarySearchTree<ComparableInteger,LineSegment>();
        for (int i = 0; i < 10; ++i) {
            bst.insert(new ComparableInteger(i), null);
        }

        Assertions.assertEquals(
            "0|N|1|N|2|N|3|N|4|N|5|N|6|N|7|N|8|N|9|N|N|",
            bst.serialise()
            );

        final IncrementKeyVisitor v
            = new IncrementKeyVisitor(new ComparableInteger(9));
        bst.visit(v);

        Assertions.assertEquals(
            "0|N|1|N|2|N|3|N|4|N|5|N|6|N|7|N|8|N|10|N|N|",
            bst.serialise()
            );
    }
}
