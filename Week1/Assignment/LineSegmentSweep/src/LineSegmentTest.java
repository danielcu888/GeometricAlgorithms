import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LineSegmentTest {

    @Test
    void testConstruct() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(1.0, 1.0);
        final LineSegment ls = new LineSegment(0,s,e);

        assertEquals(0,ls.id);
        assertEquals(1,ls.gradient());
        assertEquals(0,ls.intercept());
    }

    @Test
    void testConstruct1() {
        final Coordinate s = new Coordinate(0.0, 1.0);
        final Coordinate e = new Coordinate(1.0, 0.0);
        final LineSegment ls = new LineSegment(0,s,e);

        assertEquals(0,ls.id);
        assertEquals(-1,ls.gradient());
        assertEquals(1,ls.intercept());
    }

    @Test
    void testConstruct2() {
        final Coordinate s = new Coordinate(-10.0, -10.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);

        assertEquals(0,ls.id);
        assertEquals(1,ls.gradient());
        assertEquals(0,ls.intercept());
    }

    @Test
    void testConstruct3() {
        final Coordinate s = new Coordinate(-10.0, 10.0);
        final Coordinate e = new Coordinate(10.0, 0.0);
        final LineSegment ls = new LineSegment(0,s,e);

        assertEquals(0,ls.id);
        assertEquals(-0.5,ls.gradient());
        assertEquals(5,ls.intercept());
    }

    @Test
    void testConstruct4() {
        final Coordinate s = new Coordinate(0.0, 1.0);
        final Coordinate e = new Coordinate(10.0, 1.0);
        final LineSegment ls = new LineSegment(0,s,e);

        assertEquals(0,ls.id);
        assertEquals(0,ls.gradient());
        assertEquals(1,ls.intercept());
    }

    @Test
    void testConstruct5() {
        final Coordinate s = new Coordinate(1.0, 1.0);
        final Coordinate e = new Coordinate(10.0, 1.0);
        final LineSegment ls = new LineSegment(0,s,e);

        assertEquals(0,ls.id);
        assertEquals(0,ls.gradient());
        assertEquals(1,ls.intercept());
    }

    @Test
    void testConstruct6() {
        final Coordinate s = new Coordinate(1.0, -10.0);
        final Coordinate e = new Coordinate(1.0, 10.0);
        final LineSegment ls = new LineSegment(12,s,e);

        assertEquals(12,ls.id);
        assertEquals(Double.POSITIVE_INFINITY,ls.gradient());
        assert(Double.isNaN(ls.intercept()));
    }

    @Test
    void testConstruct7() {
        final Coordinate s = new Coordinate(1.0, 10.0);
        final Coordinate e = new Coordinate(1.0, -10.0);
        final LineSegment ls = new LineSegment(12,s,e);

        assertEquals(12,ls.id);
        assertEquals(Double.NEGATIVE_INFINITY,ls.gradient());
        assert(Double.isNaN(ls.intercept()));
    }

    @Test
    void testConstruct8() {
        final Coordinate s = new Coordinate(0.0, 10.0);
        final Coordinate e = new Coordinate(0.0, -10.0);
        final LineSegment ls = new LineSegment(12,s,e);

        assertEquals(12,ls.id);
        assertEquals(Double.NEGATIVE_INFINITY,ls.gradient());
        assertEquals(10.0,ls.intercept());
    }

    @Test
    void testConstruct9() {
        final Coordinate s = new Coordinate(0.0, -10.0);
        final Coordinate e = new Coordinate(0.0, 10.0);
        final LineSegment ls = new LineSegment(12,s,e);

        assertEquals(12,ls.id);
        assertEquals(Double.POSITIVE_INFINITY,ls.gradient());
        assertEquals(-10.0,ls.intercept());
    }

    @Test
    void testY() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(5,ls.y(5));
    }

    @Test
    void testY2() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(10.0, 0.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(0,ls.y(5));
    }

    @Test
    void testY3() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(0.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(0,ls.y(0));
    }

    @Test
    void testY4() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(0.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(null,ls.y(1));
    }

    @Test
    void testY5() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(0.0, 10.0);
        final LineSegment ls = new LineSegment(0,e,s);
        assertEquals(0,ls.y(0));
    }

    @Test
    void testX() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(5,ls.x(5));
    }

    @Test
    void testX2() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(10.0, 0.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(0,ls.x(0));
    }

    @Test
    void testX3() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(0.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(0,ls.x(0));
    }

    @Test
    void testX4() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(10.0, 0.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(null,ls.x(1));
    }

    @Test
    void testX5() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(10.0, 0.0);
        final LineSegment ls = new LineSegment(0,e,s);
        assertEquals(0,ls.x(0));
    }
    
    @Test
    void testGradient() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(1,ls.gradient());
    }

    @Test
    void testGradient2() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(10.0, 0.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(0,ls.gradient());
    }

    @Test
    void testGradient3() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(0.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(Double.POSITIVE_INFINITY,ls.gradient());
    }

    @Test
    void testGradient4() {
        final Coordinate s = new Coordinate(0.0, 0.0);
        final Coordinate e = new Coordinate(0.0, -10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(Double.NEGATIVE_INFINITY,ls.gradient());
    }

    @Test
    void testIntercept() {
        final Coordinate s = new Coordinate(-10.0, -10.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(0,ls.intercept());
    }

    @Test
    void testIntercept2() {
        final Coordinate s = new Coordinate(-10.0, -20.0);
        final Coordinate e = new Coordinate(10.0, 0.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(-10,ls.intercept());
    }

    @Test
    void testIntercept3() {
        final Coordinate s = new Coordinate(-10.0, 0.0);
        final Coordinate e = new Coordinate(10.0, 20.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(10,ls.intercept());
    }

    @Test
    void testIntercept4() {
        final Coordinate s = new Coordinate(1.0, 1.0);
        final Coordinate e = new Coordinate(2.0, 1.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(1,ls.intercept());
    }

    @Test
    void testIntercept5() {
        final Coordinate s = new Coordinate(0.0, -10.0);
        final Coordinate e = new Coordinate(0.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(-10,ls.intercept());
    }

    @Test
    void testIntercept6() {
        final Coordinate s = new Coordinate(-2.0, 1.0);
        final Coordinate e = new Coordinate(-1.0, 1.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assertEquals(1,ls.intercept());
    }

    @Test
    void testContains() {
        final Coordinate s = new Coordinate(-10.0, -10.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assert(ls.contains(new Coordinate(-10,-10)));
    }

    @Test
    void testContains2() {
        final Coordinate s = new Coordinate(-10.0, -10.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assert(ls.contains(new Coordinate(-9,-9)));
    }

    @Test
    void testContains3() {
        final Coordinate s = new Coordinate(-10.0, -10.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assert(ls.contains(new Coordinate(9,9)));
    }

    @Test
    void testContains4() {
        final Coordinate s = new Coordinate(-10.0, -10.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assert(ls.contains(new Coordinate(10,10)));
    }

    @Test
    void testContains5() {
        final Coordinate s = new Coordinate(-10.0, -10.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assert(!ls.contains(new Coordinate(-11,-11)));
    }

    @Test
    void testContains6() {
        final Coordinate s = new Coordinate(-10.0, -10.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assert(!ls.contains(new Coordinate(11,11)));
    }

    @Test
    void testContains7() {
        final Coordinate s = new Coordinate(-10.0, -10.0);
        final Coordinate e = new Coordinate(10.0, 10.0);
        final LineSegment ls = new LineSegment(0,s,e);
        assert(!ls.contains(new Coordinate(1,2)));
    }

    @Test
    void testIntersection() {
        final Coordinate s1 = new Coordinate(-10.0, 0.0);
        final Coordinate e1 = new Coordinate(10.0, 0.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(0.0, -10.0);
        final Coordinate e2 = new Coordinate(0.0, 10.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls1,ls2);

        assertEquals(0,inx.coord.compareTo(new Coordinate(0,0)));
        assertEquals(InxDim.POINT,inx.dim);
    }

    @Test
    void testIntersection1() {
        final Coordinate s1 = new Coordinate(-10.0, 0.0);
        final Coordinate e1 = new Coordinate(10.0, 0.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(0.0, 0.0);
        final Coordinate e2 = new Coordinate(0.0, 10.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls1,ls2);

        assertEquals(0,inx.coord.compareTo(new Coordinate(0,0)));
        assertEquals(InxDim.POINT,inx.dim);
    }

    @Test
    void testIntersection2() {
        final Coordinate s1 = new Coordinate(-10.0, 0.0);
        final Coordinate e1 = new Coordinate(10.0, 0.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(0.0, -10.0);
        final Coordinate e2 = new Coordinate(0.0, 0.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls1,ls2);

        assertEquals(0,inx.coord.compareTo(new Coordinate(0,0)));
        assertEquals(InxDim.POINT,inx.dim);
    }

    @Test
    void testIntersection3() {
        final Coordinate s1 = new Coordinate(0.0, 0.0);
        final Coordinate e1 = new Coordinate(10.0, 0.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(5.0, 0.0);
        final Coordinate e2 = new Coordinate(15.0, 0.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls1,ls2);

        assertEquals(0,inx.coord.compareTo(new Coordinate(5,0)));
        assertEquals(InxDim.LINE,inx.dim);
    }

    @Test
    void testIntersection4() {
        final Coordinate s1 = new Coordinate(0.0, 0.0);
        final Coordinate e1 = new Coordinate(1.0, 1.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(2.0, 2.0);
        final Coordinate e2 = new Coordinate(3.0, 3.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls1,ls2);

        assertEquals(null,inx);
    }

    @Test
    void testIntersection5() {
        final Coordinate s1 = new Coordinate(0.0, -10.0);
        final Coordinate e1 = new Coordinate(0.0, 10.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(0.0, -5.0);
        final Coordinate e2 = new Coordinate(0.0, 5.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls1,ls2);

        assertEquals(0,inx.coord.compareTo(new Coordinate(0,-5)));
        assertEquals(InxDim.LINE,inx.dim);
    }

    @Test
    void testIntersection6() {
        final Coordinate s1 = new Coordinate(0.0, -10.0);
        final Coordinate e1 = new Coordinate(0.0, 10.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(0.0, 5.0);
        final Coordinate e2 = new Coordinate(0.0, -5.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls1,ls2);

        assertEquals(0,inx.coord.compareTo(new Coordinate(0,5)));
        assertEquals(InxDim.LINE,inx.dim);
    }

    @Test
    void testIntersection7() {
        final Coordinate s1 = new Coordinate(0.0, -10.0);
        final Coordinate e1 = new Coordinate(0.0, 10.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(0.0, -5.0);
        final Coordinate e2 = new Coordinate(0.0, 5.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls2,ls1);

        assertEquals(0,inx.coord.compareTo(new Coordinate(0,-5)));
        assertEquals(InxDim.LINE,inx.dim);
    }

    @Test
    void testIntersection8() {
        final Coordinate s1 = new Coordinate(0.0, 10.0);
        final Coordinate e1 = new Coordinate(0.0, -10.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(0.0, 5.0);
        final Coordinate e2 = new Coordinate(0.0, -5.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls2,ls1);

        assertEquals(0,inx.coord.compareTo(new Coordinate(0,5)));
        assertEquals(InxDim.LINE,inx.dim);
    }

    @Test
    void testIntersection9() {
        final Coordinate s1 = new Coordinate(0.0, 0.0);
        final Coordinate e1 = new Coordinate(0.0, 10.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(-5.0, 0.0);
        final Coordinate e2 = new Coordinate(5.0, 10.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls1,ls2);

        assertEquals(0,inx.coord.compareTo(new Coordinate(0,5)));
        assertEquals(InxDim.POINT,inx.dim);
    }

    @Test
    void testIntersection10() {
        final Coordinate s1 = new Coordinate(0.0, 0.0);
        final Coordinate e1 = new Coordinate(0.0, 10.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(-5.0, 0.0);
        final Coordinate e2 = new Coordinate(5.0, 10.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls2,ls1);

        assertEquals(0,inx.coord.compareTo(new Coordinate(0,5)));
        assertEquals(InxDim.POINT,inx.dim);
    }

    @Test
    void testIntersection11() {
        final Coordinate s1 = new Coordinate(-10.0, 0.0);
        final Coordinate e1 = new Coordinate(0.0, 0.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(0.0, 0.0);
        final Coordinate e2 = new Coordinate(10.0, 10.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls1,ls2);

        assertEquals(0,inx.coord.compareTo(new Coordinate(0,0)));
        assertEquals(InxDim.POINT,inx.dim);
    }

    @Test
    void testIntersection12() {
        final Coordinate s1 = new Coordinate(0.0, -10.0);
        final Coordinate e1 = new Coordinate(0.0, 0.0);
        final LineSegment ls1 = new LineSegment(0,s1,e1);

        final Coordinate s2 = new Coordinate(0.0, 0.0);
        final Coordinate e2 = new Coordinate(0.0, 10.0);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final InxData inx = LineSegment.intersection(ls1,ls2);

        assertEquals(0,inx.coord.compareTo(new Coordinate(0,0)));
        assertEquals(InxDim.POINT,inx.dim);
    }
}
