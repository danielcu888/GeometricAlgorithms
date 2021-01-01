import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class FindIntersectionsTest {

    @Test
    void testConstruct1() {
        final FindIntersections fi = new FindIntersections(new ArrayList<LineSegment>());
        assertNotEquals(null, fi);
    }

    @Test
    void testConstruct2() {
        final ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        final FindIntersections fi = new FindIntersections(segments);
        assertNotEquals(null, fi);
    }

    @Test
    void testFindIntersections() {
        final ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        final FindIntersections fi = new FindIntersections(segments);
        assertNotEquals(null, fi);

        final ArrayList<Event> inxs = fi.intersections();
        assertNotEquals(null, inxs);
        assertTrue(inxs.isEmpty());
    }

    @Test
    void testFindIntersections2() {
        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(1,0);
        final Coordinate e2 = new Coordinate(0,1);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        segments.add(ls1);
        segments.add(ls2);

        final FindIntersections fi = new FindIntersections(segments);
        assertNotEquals(null, fi);

        final ArrayList<Event> inxs = fi.intersections();
        assertNotEquals(null, inxs);
        assertEquals(1, inxs.size());

        final Event inx = inxs.get(0);
        assertNotEquals(null, inx);
        assertEquals(EventType.POINT_INTERSECTION, inx.type);
        assertEquals(0.5, inx.coord.x);
        assertEquals(0.5, inx.coord.y);
        assertEquals(ls1, inx.ls1);
        assertEquals(ls2, inx.ls2);
    }
}
