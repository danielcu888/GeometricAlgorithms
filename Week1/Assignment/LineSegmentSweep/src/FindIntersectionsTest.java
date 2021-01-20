import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class FindIntersectionsTest {

/*
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
    void testFindIntersectionsNoSegments() {
        final ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        final FindIntersections fi = new FindIntersections(segments);
        assertNotEquals(null, fi);

        final ArrayList<Event> inxs = fi.intersections();
        assertNotEquals(null, inxs);
        assertTrue(inxs.isEmpty());
    }

    @Test
    void testFindIntersectionsTwoSegments() {
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
        assertEquals(ls1.id, inx.ls1.id);
        assertEquals(0, inx.ls1.start.compareTo(e1));
        assertEquals(0, inx.ls1.end.compareTo(s1));
        assertEquals(ls2.id, inx.ls2.id);
        assertEquals(0, inx.ls2.start.compareTo(e2));
        assertEquals(0, inx.ls2.end.compareTo(s2));
    }
*/

    @Test
    void testFindIntersectionsPairs() {

        // LINESTRING(897 914,172 214)
        final Coordinate s0 = new Coordinate(897,914);
        final Coordinate e0 = new Coordinate(172,214);
        final LineSegment ls0 = new LineSegment(0,s0,e0);

        // LINESTRING(73 914,626 779)
        final Coordinate s1 = new Coordinate(73,914);
        final Coordinate e1 = new Coordinate(626,779);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        // LINESTRING(634 896,233 68)
        final Coordinate s2 = new Coordinate(634,896);
        final Coordinate e2 = new Coordinate(233,68);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        // LINESTRING(874 890,648 114)
        final Coordinate s3 = new Coordinate(874,890);
        final Coordinate e3 = new Coordinate(648,114);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        // LINESTRING(51 823,255 695)
        final Coordinate s4 = new Coordinate(51,823);
        final Coordinate e4 = new Coordinate(255,695);
        final LineSegment ls4 = new LineSegment(4,s4,e4);

        // LINESTRING(832 799,409 469)
        final Coordinate s5 = new Coordinate(832,799);
        final Coordinate e5 = new Coordinate(409,469);
        final LineSegment ls5 = new LineSegment(5,s5,e5);

        // LINESTRING(439 750,850 558)
        final Coordinate s6 = new Coordinate(439,750);
        final Coordinate e6 = new Coordinate(850,558);
        final LineSegment ls6 = new LineSegment(6,s6,e6);

        final Coordinate s7 = new Coordinate(939,747);
        final Coordinate e7 = new Coordinate(687,715);
        final LineSegment ls7 = new LineSegment(7,s7,e7);

        final Coordinate s8 = new Coordinate(131,747);
        final Coordinate e8 = new Coordinate(481,151);
        final LineSegment ls8 = new LineSegment(8,s8,e8);

        final Coordinate s9 = new Coordinate(580,716);
        final Coordinate e9 = new Coordinate(271,427);
        final LineSegment ls9 = new LineSegment(9,s9,e9);

        final Coordinate s10 = new Coordinate(703,692);
        final Coordinate e10 = new Coordinate(2,675);
        final LineSegment ls10 = new LineSegment(10,s10,e10);

        final Coordinate s11 = new Coordinate(294,668);
        final Coordinate e11 = new Coordinate(128,290);
        final LineSegment ls11 = new LineSegment(11,s11,e11);

        final ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        segments.add(ls0);
        segments.add(ls1);
        segments.add(ls2);
        segments.add(ls3);
        segments.add(ls4);
        segments.add(ls5);
        segments.add(ls6);
        segments.add(ls7);
        segments.add(ls8);
        segments.add(ls9);
        segments.add(ls10);
        segments.add(ls11);

        /*
        final Coordinate s13 = new Coordinate(722,649);
        final Coordinate e13 = new Coordinate(638,243);
        final LineSegment ls13 = new LineSegment(12,s13,e13);

        final Coordinate s14 = new Coordinate(383,639);
        final Coordinate e14 = new Coordinate(35,318);
        final LineSegment ls14 = new LineSegment(13,s14,e14);

        final Coordinate s15 = new Coordinate(654,626);
        final Coordinate e15 = new Coordinate(810,543);
        final LineSegment ls15 = new LineSegment(14,s15,e15);

        final Coordinate s16 = new Coordinate(87,616);
        final Coordinate e16 = new Coordinate(149,23);
        final LineSegment ls16 = new LineSegment(15,s16,e16);

        final Coordinate s17 = new Coordinate(308,601);
        final Coordinate e17 = new Coordinate(828,75);
        final LineSegment ls17 = new LineSegment(16,s17,e17);

        final Coordinate s18 = new Coordinate(850,538);
        final Coordinate e18 = new Coordinate(672,225);
        final LineSegment ls18 = new LineSegment(17,s18,e18);

        final Coordinate s19 = new Coordinate(314,491);
        final Coordinate e19 = new Coordinate(247,283);
        final LineSegment ls19 = new LineSegment(18,s19,e19);

        final Coordinate s20 = new Coordinate(463,450);
        final Coordinate e20 = new Coordinate(878,233);
        final LineSegment ls20 = new LineSegment(19,s20,e20);

        final Coordinate s21 = new Coordinate(215,444);
        final Coordinate e21 = new Coordinate(206,148);
        final LineSegment ls21 = new LineSegment(20,s21,e21);

        final Coordinate s22 = new Coordinate(786,433);
        final Coordinate e22 = new Coordinate(463,58);
        final LineSegment ls22 = new LineSegment(21,s22,e22);

        final Coordinate s23 = new Coordinate(760,353);
        final Coordinate e23 = new Coordinate(91,179);
        final LineSegment ls23 = new LineSegment(22,s23,e23);

        final Coordinate s24 = new Coordinate(932,188);
        final Coordinate e24 = new Coordinate(780,139);
        final LineSegment ls24 = new LineSegment(23,s24,e24);

        final Coordinate s25 = new Coordinate(701,107);
        final Coordinate e25 = new Coordinate(364,127);
        final LineSegment ls25 = new LineSegment(24,s25,e25);

        segments.add(ls13);
        segments.add(ls14);
        segments.add(ls15);
        segments.add(ls16);
        segments.add(ls17);
        segments.add(ls18);
        segments.add(ls19);
        segments.add(ls20);
        segments.add(ls21);
        segments.add(ls22);
        segments.add(ls23);
        segments.add(ls24);
        segments.add(ls25);
        */

        final FindIntersections fi = new FindIntersections(segments);
        assertNotEquals(null, fi);

        final ArrayList<Event> inxs = fi.intersections();
        assertNotEquals(null, inxs);
        assertEquals(57, inxs.size());
    }
}
