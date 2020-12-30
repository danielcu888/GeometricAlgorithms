import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EventQueueTest {

    @Test
    void testConstruct() {
        final EventQueue q = new EventQueue();
        assertTrue(q.empty());
    }

    @Test
    void testEnqueue() {
        final EventQueue q = new EventQueue();
        final Coordinate s = new Coordinate(0,0);
        final Coordinate e = new Coordinate(1,0);
        final LineSegment ls = new LineSegment(0,s,e);
        final Event start = new Event(EventType.START,s,ls);
        final Event end = new Event(EventType.END,e,ls);
        q.enqueue(start);
        q.enqueue(end);

        final Event e1 = q.dequeue();
        assertNotEquals(null, e1);
        assertEquals(s, e1.coord);
        assertEquals(ls, e1.ls1);
        assertEquals(null, e1.ls2);
        assertEquals(EventType.START, e1.type);

        final Event e2 = q.dequeue();
        assertNotEquals(null, e2);
        assertEquals(e, e2.coord);
        assertEquals(ls, e2.ls1);
        assertEquals(null, e2.ls2);
        assertEquals(EventType.END, e2.type);
    }

    @Test
    void testEnqueue2() {
        final EventQueue q = new EventQueue();

        final Coordinate s2 =  new Coordinate(0.5,-1.0);
        final Coordinate s1 =  new Coordinate(0.0,-0.5);
        final Coordinate inx = new Coordinate(0.0, 0.0);
        final Coordinate e1 =  new Coordinate(1.0, 0.5);
        final Coordinate e2 =  new Coordinate(0.5, 1.0);

        final LineSegment ls1 = new LineSegment(0,s1,e1);
        final LineSegment ls2 = new LineSegment(0,s2,e2);

        final Event start1 = new Event(EventType.START,s1,ls1);
        final Event end1 = new Event(EventType.END,e1,ls1);

        final Event start2 = new Event(EventType.START,s2,ls2);
        final Event end2 = new Event(EventType.END,e2,ls2);

        final Event inx12 = new Event(EventType.POINT_INTERSECTION,inx,ls1,ls2);

        // Add events to queue in random order.
        q.enqueue(end2);
        q.enqueue(start1);
        q.enqueue(start2);
        q.enqueue(end1);
        q.enqueue(inx12);

        // Start of second LineSegment
        assertFalse(q.empty());
        final Event ev1 = q.dequeue();
        assertNotEquals(null, ev1);
        assertEquals(s2, ev1.coord);
        assertEquals(ls2, ev1.ls1);
        assertEquals(null, ev1.ls2);
        assertEquals(EventType.START, ev1.type);

        // Start of first LineSegment
        assertFalse(q.empty());
        final Event ev2 = q.dequeue();
        assertNotEquals(null, ev2);
        assertEquals(s1, ev2.coord);
        assertEquals(ls1, ev2.ls1);
        assertEquals(null, ev2.ls2);
        assertEquals(EventType.START, ev2.type);

        // Intersection
        assertFalse(q.empty());
        final Event ev3 = q.dequeue();
        assertNotEquals(null, ev3);
        assertEquals(inx, ev3.coord);
        assertEquals(ls1, ev3.ls1);
        assertEquals(ls2, ev3.ls2);
        assertEquals(EventType.POINT_INTERSECTION, ev3.type);

        // End of first LineSegment
        assertFalse(q.empty());
        final Event ev4 = q.dequeue();
        assertNotEquals(null, ev4);
        assertEquals(e1, ev4.coord);
        assertEquals(ls1, ev4.ls1);
        assertEquals(null, ev4.ls2);
        assertEquals(EventType.END, ev4.type);

        // End of second LineSegment
        assertFalse(q.empty());
        final Event ev5 = q.dequeue();
        assertNotEquals(null, ev5);
        assertEquals(e2, ev5.coord);
        assertEquals(ls2, ev5.ls1);
        assertEquals(null, ev5.ls2);
        assertEquals(EventType.END, ev5.type);

        assertTrue(q.empty());
    }

    @Test
    void testEnqueue3() {
        final EventQueue q = new EventQueue();
        final Coordinate s = new Coordinate(0,0);
        final Coordinate e = new Coordinate(1,0);
        final LineSegment ls = new LineSegment(0,s,e);
        final Event start = new Event(EventType.START,s,ls);
        final Event end = new Event(EventType.END,e,ls);

        // Add events at same y in reverse order.
        q.enqueue(end);
        q.enqueue(start);

        final Event e1 = q.dequeue();
        assertNotEquals(null, e1);
        assertEquals(s, e1.coord);
        assertEquals(ls, e1.ls1);
        assertEquals(null, e1.ls2);
        assertEquals(EventType.START, e1.type);

        final Event e2 = q.dequeue();
        assertNotEquals(null, e2);
        assertEquals(e, e2.coord);
        assertEquals(ls, e2.ls1);
        assertEquals(null, e2.ls2);
        assertEquals(EventType.END, e2.type);
    }

    @Test
    void testEnqueue4() {
        final EventQueue q = new EventQueue();
        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,0);
        final Coordinate inx = e1;
        final Coordinate s2 = inx;
        final Coordinate e2 = new Coordinate(2,0);

        final LineSegment ls1 = new LineSegment(0,s1,e1);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final Event ev1 = new Event(EventType.START,s1,ls1);
        final Event ev2 = new Event(EventType.END,e1,ls1);
        final Event ev3 = new Event(EventType.POINT_INTERSECTION,inx,ls1,ls2);
        final Event ev4 = new Event(EventType.START,s2,ls2);
        final Event ev5 = new Event(EventType.END,e2,ls2);

        // Add events to queue in random order.
        q.enqueue(ev2);
        q.enqueue(ev4);
        q.enqueue(ev1);
        q.enqueue(ev5);
        q.enqueue(ev3);

        // Start of first LineSegment
        assertFalse(q.empty());
        final Event aev1 = q.dequeue();
        assertNotEquals(null, aev1);
        assertEquals(s1, aev1.coord);
        assertEquals(ls1, aev1.ls1);
        assertEquals(null, aev1.ls2);
        assertEquals(EventType.START, aev1.type);

        // End of first LineSegment
        assertFalse(q.empty());
        final Event aev2 = q.dequeue();
        assertNotEquals(null, aev2);
        assertEquals(e1, aev2.coord);
        assertEquals(ls1, aev2.ls1);
        assertEquals(null, aev2.ls2);
        assertEquals(EventType.END, aev2.type);

        // Start of second LineSegment
        assertFalse(q.empty());
        final Event aev4 = q.dequeue();
        assertNotEquals(null, aev4);
        assertEquals(s2, aev4.coord);
        assertEquals(ls2, aev4.ls1);
        assertEquals(null, aev4.ls2);
        assertEquals(EventType.START, aev4.type);

        // Intersection
        assertFalse(q.empty());
        final Event aev3 = q.dequeue();
        assertNotEquals(null, aev3);
        assertEquals(inx, aev3.coord);
        assertEquals(ls1, aev3.ls1);
        assertEquals(ls2, aev3.ls2);
        assertEquals(EventType.POINT_INTERSECTION, aev3.type);

        // End of second LineSegment
        assertFalse(q.empty());
        final Event aev5 = q.dequeue();
        assertNotEquals(null, aev5);
        assertEquals(e2, aev5.coord);
        assertEquals(ls2, aev5.ls1);
        assertEquals(null, aev5.ls2);
        assertEquals(EventType.END, aev5.type);

        assertTrue(q.empty());
    }

    @Test
    void testEnqueue5() {
        final EventQueue q = new EventQueue();
        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,0);
        final Coordinate inx = e1;
        final Coordinate s2 = inx;
        final Coordinate e2 = new Coordinate(2,0);

        final LineSegment ls1 = new LineSegment(0,s1,e1);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final Event ev1 = new Event(EventType.START,s1,ls1);
        final Event ev2 = new Event(EventType.END,e1,ls1);
        final Event ev3 = new Event(EventType.POINT_INTERSECTION,inx,ls1,ls2);
        final Event ev4 = new Event(EventType.START,s2,ls2);
        final Event ev5 = new Event(EventType.END,e2,ls2);

        // Add events to queue in random order.
        q.enqueue(ev2);
        q.enqueue(ev1);
        q.enqueue(ev5);
        q.enqueue(ev3);
        q.enqueue(ev4);

        // Start of first LineSegment
        assertFalse(q.empty());
        final Event aev1 = q.dequeue();
        assertNotEquals(null, aev1);
        assertEquals(s1, aev1.coord);
        assertEquals(ls1, aev1.ls1);
        assertEquals(null, aev1.ls2);
        assertEquals(EventType.START, aev1.type);

        // End of first LineSegment
        assertFalse(q.empty());
        final Event aev2 = q.dequeue();
        assertNotEquals(null, aev2);
        assertEquals(e1, aev2.coord);
        assertEquals(ls1, aev2.ls1);
        assertEquals(null, aev2.ls2);
        assertEquals(EventType.END, aev2.type);

        // Intersection
        assertFalse(q.empty());
        final Event aev3 = q.dequeue();
        assertNotEquals(null, aev3);
        assertEquals(inx, aev3.coord);
        assertEquals(ls1, aev3.ls1);
        assertEquals(ls2, aev3.ls2);
        assertEquals(EventType.POINT_INTERSECTION, aev3.type);

        // Start of second LineSegment
        assertFalse(q.empty());
        final Event aev4 = q.dequeue();
        assertNotEquals(null, aev4);
        assertEquals(s2, aev4.coord);
        assertEquals(ls2, aev4.ls1);
        assertEquals(null, aev4.ls2);
        assertEquals(EventType.START, aev4.type);

        // End of second LineSegment
        assertFalse(q.empty());
        final Event aev5 = q.dequeue();
        assertNotEquals(null, aev5);
        assertEquals(e2, aev5.coord);
        assertEquals(ls2, aev5.ls1);
        assertEquals(null, aev5.ls2);
        assertEquals(EventType.END, aev5.type);

        assertTrue(q.empty());
    }

    @Test
    void testEnqueue6() {
        final EventQueue q = new EventQueue();
        final Coordinate s1 = new Coordinate(0,0);
        final Coordinate e1 = new Coordinate(1,0);

        final Coordinate s2 = new Coordinate(2,0);
        final Coordinate e2 = new Coordinate(3,0);

        final LineSegment ls1 = new LineSegment(0,s1,e1);
        final LineSegment ls2 = new LineSegment(1,s2,e2);

        final Event ev1 = new Event(EventType.START,s1,ls1);
        final Event ev2 = new Event(EventType.END,e1,ls1);

        final Event ev3 = new Event(EventType.START,s2,ls2);
        final Event ev4 = new Event(EventType.END,e2,ls2);

        // Add events to queue in random order.
        q.enqueue(ev2);
        q.enqueue(ev1);
        q.enqueue(ev3);
        q.enqueue(ev4);

        // Start of first LineSegment
        assertFalse(q.empty());
        final Event aev1 = q.dequeue();
        assertNotEquals(null, aev1);
        assertEquals(s1, aev1.coord);
        assertEquals(ls1, aev1.ls1);
        assertEquals(null, aev1.ls2);
        assertEquals(EventType.START, aev1.type);

        // End of first LineSegment
        assertFalse(q.empty());
        final Event aev2 = q.dequeue();
        assertNotEquals(null, aev2);
        assertEquals(e1, aev2.coord);
        assertEquals(ls1, aev2.ls1);
        assertEquals(null, aev2.ls2);
        assertEquals(EventType.END, aev2.type);

        // Start of second LineSegment
        assertFalse(q.empty());
        final Event aev3 = q.dequeue();
        assertNotEquals(null, aev3);
        assertEquals(s2, aev3.coord);
        assertEquals(ls2, aev3.ls1);
        assertEquals(null, aev3.ls2);
        assertEquals(EventType.START, aev3.type);

        // End of second LineSegment
        assertFalse(q.empty());
        final Event aev4 = q.dequeue();
        assertNotEquals(null, aev4);
        assertEquals(e2, aev4.coord);
        assertEquals(ls2, aev4.ls1);
        assertEquals(null, aev4.ls2);
        assertEquals(EventType.END, aev4.type);

        assertTrue(q.empty());
    }

}
