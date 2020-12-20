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
        assert(Double.isNaN(ls.intercept()));
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





}
