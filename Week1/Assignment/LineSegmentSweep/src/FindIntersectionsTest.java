import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class FindIntersectionsTest {

	private static double TOL = 1e-12;

    private void verifyEvent( ArrayList<Event> events
                            , int index
                            , EventType type
                            , double x
                            , double y
                            , Integer id1
                            , Integer id2
                            )
    {
        assertNotEquals(null, events);
        final Event e = events.get(index);
        assertNotEquals(null, e);
        assertEquals(type, e.type);
        assertTrue(Math.abs(x - e.coord.x) < TOL);
        assertTrue(Math.abs(y - e.coord.y) < TOL);
        assertEquals(id1, e.ls1.id);
        assertEquals(id2, e.ls2.id);
    }

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
        final Coordinate s0 = new Coordinate(0,0);
        final Coordinate e0 = new Coordinate(1,1);
        final LineSegment ls0 = new LineSegment(0,s0,e0);

        final Coordinate s1 = new Coordinate(1,0);
        final Coordinate e1 = new Coordinate(0,1);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        segments.add(ls0);
        segments.add(ls1);

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
        assertEquals(ls0.id, inx.ls1.id);
        assertEquals(0, inx.ls1.start.compareTo(e0));
        assertEquals(0, inx.ls1.end.compareTo(s0));
        assertEquals(ls1.id, inx.ls2.id);
        assertEquals(0, inx.ls2.start.compareTo(e1));
        assertEquals(0, inx.ls2.end.compareTo(s1));
    }

    @Test
    void testFindIntersectionsPairs() {

        /*
                MULTILINESTRING(
                        (897 914,172 214),
                        (73 914,626 779),
                        (634 896,233 68),
                        (874 890,648 114),
                        (51 823,255 695),
                        (832 799,409 469),
                        (439 750,850 558),
                        (939 747,687 715),
                        (131 747,481 151),
                        (580 716,271 427),
                        (703 692,2 675),
                        (294 668,128 290),
                        (722 649,638 243),
                        (383 639,35 318),
                        (654 626,810 543),
                        (87 616,149 23),
                        (308 601,828 75),
                        (850 538,672 225),
                        (314 491,247 283),
                        (463 450,878 233),
                        (215 444,206 148),
                        (786 433,463 58),
                        (760 353,91 179),
                        (932 188,780 139),
                        (701 107,364 127))
                */

        final Coordinate s0 = new Coordinate(897,914);
        final Coordinate e0 = new Coordinate(172,214);
        final LineSegment ls0 = new LineSegment(0,s0,e0);

        final Coordinate s1 = new Coordinate(73,914);
        final Coordinate e1 = new Coordinate(626,779);
        final LineSegment ls1 = new LineSegment(1,s1,e1);

        final Coordinate s2 = new Coordinate(634,896);
        final Coordinate e2 = new Coordinate(233,68);
        final LineSegment ls2 = new LineSegment(2,s2,e2);

        final Coordinate s3 = new Coordinate(874,890);
        final Coordinate e3 = new Coordinate(648,114);
        final LineSegment ls3 = new LineSegment(3,s3,e3);

        final Coordinate s4 = new Coordinate(51,823);
        final Coordinate e4 = new Coordinate(255,695);
        final LineSegment ls4 = new LineSegment(4,s4,e4);

        final Coordinate s5 = new Coordinate(832,799);
        final Coordinate e5 = new Coordinate(409,469);
        final LineSegment ls5 = new LineSegment(5,s5,e5);

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

        final Coordinate s12 = new Coordinate(722,649);
        final Coordinate e12 = new Coordinate(638,243);
        final LineSegment ls12 = new LineSegment(12,s12,e12);

        final Coordinate s13 = new Coordinate(383,639);
        final Coordinate e13 = new Coordinate(35,318);
        final LineSegment ls13 = new LineSegment(13,s13,e13);

        final Coordinate s14 = new Coordinate(654,626);
        final Coordinate e14 = new Coordinate(810,543);
        final LineSegment ls14 = new LineSegment(14,s14,e14);

        final Coordinate s15 = new Coordinate(87,616);
        final Coordinate e15 = new Coordinate(149,23);
        final LineSegment ls15 = new LineSegment(15,s15,e15);

        final Coordinate s16 = new Coordinate(308,601);
        final Coordinate e16 = new Coordinate(828,75);
        final LineSegment ls16 = new LineSegment(16,s16,e16);

        final Coordinate s17 = new Coordinate(850,538);
        final Coordinate e17 = new Coordinate(672,225);
        final LineSegment ls17 = new LineSegment(17,s17,e17);

        final Coordinate s18 = new Coordinate(314,491);
        final Coordinate e18 = new Coordinate(247,283);
        final LineSegment ls18 = new LineSegment(18,s18,e18);

        final Coordinate s19 = new Coordinate(463,450);
        final Coordinate e19 = new Coordinate(878,233);
        final LineSegment ls19 = new LineSegment(19,s19,e19);

        final Coordinate s20 = new Coordinate(215,444);
        final Coordinate e20 = new Coordinate(206,148);
        final LineSegment ls20 = new LineSegment(20,s20,e20);

        final Coordinate s21 = new Coordinate(786,433);
        final Coordinate e21 = new Coordinate(463,58);
        final LineSegment ls21 = new LineSegment(21,s21,e21);

        final Coordinate s22 = new Coordinate(760,353);
        final Coordinate e22 = new Coordinate(91,179);
        final LineSegment ls22 = new LineSegment(22,s22,e22);

        final Coordinate s23 = new Coordinate(932,188);
        final Coordinate e23 = new Coordinate(780,139);
        final LineSegment ls23 = new LineSegment(23,s23,e23);

        final Coordinate s24 = new Coordinate(701,107);
        final Coordinate e24 = new Coordinate(364,127);
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

        final FindIntersections fi = new FindIntersections(segments);
        assertNotEquals(null, fi);

        final ArrayList<Event> inxs = fi.intersections();
        assertNotEquals(null, inxs);
        assertEquals(60, inxs.size());

        /*
            MULTILINESTRING(
                        (0 0,582.4820270341531 789.623736619149),
                        (0 0,828.2575615972812 732.9374681393374),
                        (0 0,731.5842812823165 720.6614960358497),
                        (0 0,691.4823759791124 715.5691906005222),
                        (0 0,540.3603019410496 702.6492020129403),
                        (0 0,557.2761205342856 694.7469217942023),
                        (0 0,694.5838341386373 691.795898973405),
                        (0 0,666.1457538541939 691.1062451006011),
                        (0 0,570.0576144767232 688.7760049159833),
                        (0 0,550.3818107187741 688.2988456237078),
                        (0 0,533.2087372801429 687.8823802193473),
                        (0 0,170.87683659550765 679.0954439687927),
                        (0 0,633.1876317638792 659.2846099789178),
                        (0 0,519.358173231497 659.2832105627917),
                        (0 0,645.5234913256413 653.5218726653939),
                        (0 0,716.1129217351388 620.5457883865042),
                        (0 0,710.9701314217443 595.6889685384308),
                        (0 0,786.0146386699346 587.890971716235),
                        (0 0,324.12295981346864 584.6910060348375),
                        (0 0,550.1860158311347 579.1451187335094),
                        (0 0,777.9127412133264 560.0720671749614),
                        (0 0,244.10868549879777 554.3920669791901),
                        (0 0,379.60152678588554 528.5723017512005),
                        (0 0,260.6811964047811 526.1714484078584),
                        (0 0,438.25869379663544 491.8259313307085),
                        (0 0,425.651751594781 481.99072819451004),
                        (0 0,211.99271639960133 481.2605228858391),
                        (0 0,430.9164159467237 476.6653177154295),
                        (0 0,437.3285033876702 470.17924465016443),
                        (0 0,303.03598387850985 456.96245741388134),
                        (0 0,301.93097429498727 455.92896948625025),
                        (0 0,302.4295852109427 455.07990632650876),
                        (0 0,419.38469962453075 452.85419274092624),
                        (0 0,110.84248627721354 387.9581554453608),
                        (0 0,345.5824102791316 381.5968099246788),
                        (0 0,717.8674004508795 353.89868473399326),
                        (0 0,742.1525010508617 348.35804960067253),
                        (0 0,659.5625702931694 347.2190897503186),
                        (0 0,367.1161014833698 344.92801004546175),
                        (0 0,714.1312117043891 341.0700012504689),
                        (0 0,704.7061996270668 338.6186528178022),
                        (0 0,685.5425401250326 333.6343826334165),
                        (0 0,695.7953711743962 328.27326374736384),
                        (0 0,655.1145612028109 325.7203791469194),
                        (0 0,708.4736149961465 321.64391697791854),
                        (0 0,722.723150230535 314.19295518065996),
                        (0 0,595.4743994900244 310.20858820816784),
                        (0 0,248.59600577478346 287.95476419634264),
                        (0 0,642.9487143459322 266.91878600533926),
                        (0 0,640.7704728444661 264.3898678534823),
                        (0 0,415.0608845491097 263.28489373923037),
                        (0 0,642.139065584558 263.00548365869713),
                        (0 0,689.068348419647 255.01344413117704),
                        (0 0,209.09577532405183 249.8166106577052),
                        (0 0,314.96862800986463 237.25193015503208),
                        (0 0,674.8159196218231 229.951588997925),
                        (0 0,680.1870764707235 224.5184572623067),
                        (0 0,207.86676541550864 209.3958403322848),
                        (0 0,131.58604925092942 189.5560128096588),
                        (0 0,514.7271728083713 118.05476719238153))
         */

        this.verifyEvent(inxs,0,EventType.POINT_INTERSECTION,582.4820270341531,789.623736619149,1,2);
        this.verifyEvent(inxs,1,EventType.POINT_INTERSECTION,828.2575615972812,732.9374681393374,3,7);
        this.verifyEvent(inxs,2,EventType.POINT_INTERSECTION,731.5842812823165,720.6614960358497,5,7);
        this.verifyEvent(inxs,3,EventType.POINT_INTERSECTION,691.4823759791124,715.5691906005222,0,7);
        this.verifyEvent(inxs,4,EventType.POINT_INTERSECTION,540.3603019410496,702.6492020129403,2,6);
        this.verifyEvent(inxs,5,EventType.POINT_INTERSECTION,557.2761205342856,694.7469217942023,6,9);
        this.verifyEvent(inxs,6,EventType.POINT_INTERSECTION,694.5838341386373,691.795898973405,5,10);
        this.verifyEvent(inxs,7,EventType.POINT_INTERSECTION,666.1457538541939,691.1062451006011,0,10);
        this.verifyEvent(inxs,8,EventType.POINT_INTERSECTION,570.0576144767232,688.7760049159833,6,10);
        this.verifyEvent(inxs,9,EventType.POINT_INTERSECTION,550.3818107187741,688.2988456237078,9,10);
        this.verifyEvent(inxs,10,EventType.POINT_INTERSECTION,533.2087372801429,687.8823802193473,2,10);
        this.verifyEvent(inxs,11,EventType.POINT_INTERSECTION,170.87683659550765,679.0954439687927,8,10);
        this.verifyEvent(inxs,12,EventType.POINT_INTERSECTION,633.1876317638792,659.2846099789178,0,6);
        this.verifyEvent(inxs,13,EventType.POINT_INTERSECTION,519.358173231497,659.2832105627917,2,9);
        this.verifyEvent(inxs,14,EventType.POINT_INTERSECTION,645.5234913256413,653.5218726653939,5,6);
        this.verifyEvent(inxs,15,EventType.POINT_INTERSECTION,716.1129217351388,620.5457883865042,6,12);
        this.verifyEvent(inxs,16,EventType.POINT_INTERSECTION,710.9701314217443,595.6889685384308,12,14);
        this.verifyEvent(inxs,17,EventType.POINT_INTERSECTION,786.0146386699346,587.890971716235,3,6);
        this.verifyEvent(inxs,18,EventType.POINT_INTERSECTION,324.12295981346864,584.6910060348375,13,16);
        this.verifyEvent(inxs,19,EventType.POINT_INTERSECTION,550.1860158311347,579.1451187335094,0,5);
        this.verifyEvent(inxs,20,EventType.POINT_INTERSECTION,777.9127412133264,560.0720671749614,3,14);
        this.verifyEvent(inxs,21,EventType.POINT_INTERSECTION,244.10868549879777,554.3920669791901,8,11);
        this.verifyEvent(inxs,22,EventType.POINT_INTERSECTION,379.60152678588554,528.5723017512005,9,16);
        this.verifyEvent(inxs,23,EventType.POINT_INTERSECTION,260.6811964047811,526.1714484078584,8,13);
        this.verifyEvent(inxs,24,EventType.POINT_INTERSECTION,438.25869379663544,491.8259313307085,2,5);
        this.verifyEvent(inxs,25,EventType.POINT_INTERSECTION,425.651751594781,481.99072819451004,5,16);
        this.verifyEvent(inxs,26,EventType.POINT_INTERSECTION,211.99271639960133,481.2605228858391,11,13);
        this.verifyEvent(inxs,27,EventType.POINT_INTERSECTION,430.9164159467237,476.6653177154295,2,16);
        this.verifyEvent(inxs,28,EventType.POINT_INTERSECTION,437.3285033876702,470.17924465016443,0,16);
        this.verifyEvent(inxs,29,EventType.POINT_INTERSECTION,303.03598387850985,456.96245741388134,9,18);
        this.verifyEvent(inxs,30,EventType.POINT_INTERSECTION,301.93097429498727,455.92896948625025,8,9);
        this.verifyEvent(inxs,31,EventType.POINT_INTERSECTION,302.4295852109427,455.07990632650876,8,18);
        this.verifyEvent(inxs,32,EventType.POINT_INTERSECTION,419.38469962453075,452.85419274092624,0,2);
        this.verifyEvent(inxs,33,EventType.POINT_INTERSECTION,110.84248627721354,387.9581554453608,13,15);
        this.verifyEvent(inxs,34,EventType.POINT_INTERSECTION,345.5824102791316,381.5968099246788,0,8);
        this.verifyEvent(inxs,35,EventType.POINT_INTERSECTION,717.8674004508795,353.89868473399326,3,21);
        this.verifyEvent(inxs,36,EventType.POINT_INTERSECTION,742.1525010508617,348.35804960067253,17,22);
        this.verifyEvent(inxs,37,EventType.POINT_INTERSECTION,659.5625702931694,347.2190897503186,12,19);
        this.verifyEvent(inxs,38,EventType.POINT_INTERSECTION,367.1161014833698,344.92801004546175,2,8);
        this.verifyEvent(inxs,39,EventType.POINT_INTERSECTION,714.1312117043891,341.0700012504689,3,22);
        this.verifyEvent(inxs,40,EventType.POINT_INTERSECTION,704.7061996270668,338.6186528178022,21,22);
        this.verifyEvent(inxs,41,EventType.POINT_INTERSECTION,685.5425401250326,333.6343826334165,19,22);
        this.verifyEvent(inxs,42,EventType.POINT_INTERSECTION,695.7953711743962,328.27326374736384,19,21);
        this.verifyEvent(inxs,43,EventType.POINT_INTERSECTION,655.1145612028109,325.7203791469194,12,22);
        this.verifyEvent(inxs,44,EventType.POINT_INTERSECTION,708.4736149961465,321.64391697791854,3,19);
        this.verifyEvent(inxs,45,EventType.POINT_INTERSECTION,722.723150230535,314.19295518065996,17,19);
        this.verifyEvent(inxs,46,EventType.POINT_INTERSECTION,595.4743994900244,310.20858820816784,16,22);
        this.verifyEvent(inxs,47,EventType.POINT_INTERSECTION,248.59600577478346,287.95476419634264,0,18);
        this.verifyEvent(inxs,48,EventType.POINT_INTERSECTION,642.9487143459322,266.91878600533926,12,21);
        this.verifyEvent(inxs,49,EventType.POINT_INTERSECTION,640.7704728444661,264.3898678534823,16,21);
        this.verifyEvent(inxs,50,EventType.POINT_INTERSECTION,415.0608845491097,263.28489373923037,8,22);
        this.verifyEvent(inxs,51,EventType.POINT_INTERSECTION,642.139065584558,263.00548365869713,12,16);
        this.verifyEvent(inxs,52,EventType.POINT_INTERSECTION,689.068348419647,255.01344413117704,3,17);
        this.verifyEvent(inxs,53,EventType.POINT_INTERSECTION,209.09577532405183,249.8166106577052,0,20);
        this.verifyEvent(inxs,54,EventType.POINT_INTERSECTION,314.96862800986463,237.25193015503208,2,22);
        this.verifyEvent(inxs,55,EventType.POINT_INTERSECTION,674.8159196218231,229.951588997925,16,17);
        this.verifyEvent(inxs,56,EventType.POINT_INTERSECTION,680.1870764707235,224.5184572623067,3,16);
        this.verifyEvent(inxs,57,EventType.POINT_INTERSECTION,207.86676541550864,209.3958403322848,20,22);
        this.verifyEvent(inxs,58,EventType.POINT_INTERSECTION,131.58604925092942,189.5560128096588,15,22);
        this.verifyEvent(inxs,59,EventType.POINT_INTERSECTION,514.7271728083713,118.05476719238153,21,24);
    }
}
