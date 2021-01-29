import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class FindIntersectionsBruteForceTest {

    @Test
    void testFindIntersectionsPairs2() {

        /*
            MULTILINESTRING(
            (389 978, 376 404),
            (251 975, 12  925),
            (511 971, 469 6),
            (166 958, 956 487),
            (972 926, 529 562),
            (893 901, 151 139),
            (980 881, 815 474),
            (188 873, 16  87),
            (400 864, 197 841),
            (987 851, 19 836),
            (363 723, 394 546),
            (383 721, 642 15),
            (738 690, 999 596),
            (974 668, 94 512),
            (492 662, 926 222),
            (6   651, 994 386),
            (547 598, 819 157),
            (963 583, 487 32),
            (740 546, 512 432)
            (185 537, 581 82),
            (793 509, 912 328),
            (253 484, 867 204),
            (782 443, 983 416),
            (355 422, 342 36),
            (305 393, 433 214),
            )
        */

        final Coordinate s0 = new Coordinate(389,978);
        final Coordinate e0 = new Coordinate(376,404);
        final LineSegment ls0 = new LineSegment(0,s0,e0);

        final Coordinate s1 = new Coordinate(251,975);
        final Coordinate e1 = new Coordinate(12,925);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(511,971);
        final Coordinate e2 = new Coordinate(469,6);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(166,958);
        final Coordinate e3 = new Coordinate(956,487);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        final Coordinate s4 = new Coordinate(972,926);
        final Coordinate e4 = new Coordinate(529,562);
        final LineSegment ls4 = new LineSegment(4,s4,e4);

        final Coordinate s5 = new Coordinate(893,901);
        final Coordinate e5 = new Coordinate(151,139);
        final LineSegment ls5 = new LineSegment(5,s5,e5);

        final Coordinate s6 = new Coordinate(980,881);
        final Coordinate e6 = new Coordinate(815,474);
        final LineSegment ls6 = new LineSegment(6,s6,e6);

        final Coordinate s7 = new Coordinate(188,873);
        final Coordinate e7 = new Coordinate(16,87);
        final LineSegment ls7 = new LineSegment(7,s7,e7);

        final Coordinate s8 = new Coordinate(400,864);
        final Coordinate e8 = new Coordinate(197,841);
        final LineSegment ls8 = new LineSegment(8,s8,e8);

        final Coordinate s9 = new Coordinate(987,851);
        final Coordinate e9 = new Coordinate(19,836);
        final LineSegment ls9 = new LineSegment(9,s9,e9);

        final Coordinate s10 = new Coordinate(363,723);
        final Coordinate e10 = new Coordinate(394,546);
        final LineSegment ls10 = new LineSegment(10,s10,e10);

        final Coordinate s11 = new Coordinate(383,721);
        final Coordinate e11 = new Coordinate(642,15);
        final LineSegment ls11 = new LineSegment(11,s11,e11);

        final Coordinate s12 = new Coordinate(738,690);
        final Coordinate e12 = new Coordinate(999,596);
        final LineSegment ls12 = new LineSegment(12,s12,e12);

        final Coordinate s13 = new Coordinate(974,668);
        final Coordinate e13 = new Coordinate(94,512);
        final LineSegment ls13 = new LineSegment(13,s13,e13);

        final Coordinate s14 = new Coordinate(492,662);
        final Coordinate e14 = new Coordinate(926,222);
        final LineSegment ls14 = new LineSegment(14,s14,e14);

        final Coordinate s15 = new Coordinate(6,651);
        final Coordinate e15 = new Coordinate(994,386);
        final LineSegment ls15 = new LineSegment(15,s15,e15);

        final Coordinate s16 = new Coordinate(547,598);
        final Coordinate e16 = new Coordinate(819,157);
        final LineSegment ls16 = new LineSegment(16,s16,e16);

        final Coordinate s17 = new Coordinate(963,583);
        final Coordinate e17 = new Coordinate(487,32);
        final LineSegment ls17 = new LineSegment(17,s17,e17);

        final Coordinate s18 = new Coordinate(740,546);
        final Coordinate e18 = new Coordinate(512,432);
        final LineSegment ls18 = new LineSegment(18,s18,e18);

        final Coordinate s19 = new Coordinate(185,537);
        final Coordinate e19 = new Coordinate(581,82);
        final LineSegment ls19 = new LineSegment(19,s19,e19);

        final Coordinate s20 = new Coordinate(793,509);
        final Coordinate e20 = new Coordinate(912,328);
        final LineSegment ls20 = new LineSegment(20,s20,e20);

        final Coordinate s21 = new Coordinate(253,484);
        final Coordinate e21 = new Coordinate(867,204);
        final LineSegment ls21 = new LineSegment(21,s21,e21);

        final Coordinate s22 = new Coordinate(782,443);
        final Coordinate e22 = new Coordinate(983,416);
        final LineSegment ls22 = new LineSegment(22,s22,e22);

        final Coordinate s23 = new Coordinate(355,422);
        final Coordinate e23 = new Coordinate(342,36);
        final LineSegment ls23 = new LineSegment(23,s23,e23);

        final Coordinate s24 = new Coordinate(305,393);
        final Coordinate e24 = new Coordinate(433,214);
        final LineSegment ls24 = new LineSegment(24,s24,e24);

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
        segments.add(ls12);
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

        final FindIntersectionsBruteForce fi = new FindIntersectionsBruteForce(segments);
        assertNotEquals(null, fi);

        final ArrayList<Event> inxs = fi.intersections();
        assertNotEquals(null, inxs);
        assertEquals(74, inxs.size());
    }
}
