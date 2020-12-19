import java.util.ArrayList;

public class FindIntersections {

    ArrayList<Event> inx;

    FindIntersections(ArrayList<LineSegment> segments) {
        this.inx = new ArrayList<Event>();

        // Construct empty EventQueue.
        final EventQueue q = new EventQueue();

        // Populate the EventQueue with the START and END Events.
        for (LineSegment ls : segments) {
            q.enqueue(new Event(EventType.START, ls.start, ls));
            q.enqueue(new Event(EventType.END, ls.end, ls));
        }

        // Construct Status.
        final Status s = new Status();

        // Process Events.
        while (!q.empty()) {

            // Retrieve next Event.
            final Event e = q.dequeue();

            // Switch on type of current Event.
            final EventType et = e.type;
            switch (et) {
            case START:
                this.handleStartEvent(e, q, s);
                break;
            case END:
                this.handleEndEvent(e, q, s);
                break;
            case INTERSECTION:
                this.handleInxEvent(e, q, s);
                break;
            default:
                throw new IllegalArgumentException("Unrecognised EventType: " + et);
            }
        }
    }

    ArrayList<Event> intersections() {
        return this.inx;
    }

    private void handleStartEvent(Event e, EventQueue q, Status s) {
        // 1. Insert the LineSegment into the Status.
        s.insert(e.ls1);

        // 2. Retrieve the neighbouring LineSegments of the new LineSegment.
        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred
            = s.predecessor(e.ls1);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> succ
            = s.successor(e.ls1);

        // 3. Check for future intersections between the new LineSegment and its
        //    neighbours.

        InxData pred_inx = null;
        if (pred != null) {
            pred_inx = LineSegment.intersection(e.ls1, pred.value);
        }

        InxData succ_inx = null;
        if (succ != null) {
            succ_inx = LineSegment.intersection(e.ls1, succ.value);
        }

        // 4. If any intersections are found, add the found intersections as Events
        //    to the EventQueue.

        if (pred_inx != null) {
            switch (pred_inx.dim) {
            case POINT:
                q.enqueue(new Event(EventType.INTERSECTION, pred_inx.coord, e.ls1, pred.value));
                break;
            case LINE:
                /**
                 * @todo - Process line intersections.
                 */
                break;
            case UNKNOWN:
                throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
            default:
                throw new IllegalStateException("Unrecognised InxDim");
            }
        }

        if (succ_inx != null) {
            switch (succ_inx.dim) {
            case POINT:
                q.enqueue(new Event(EventType.INTERSECTION, succ_inx.coord, e.ls1, succ.value));
                break;
            case LINE:
                /**
                 * @todo - Process line intersections.
                 */
                break;
            case UNKNOWN:
                throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
            default:
                throw new IllegalStateException("Unrecognised InxDim");
            }
        }
    }

    private void handleEndEvent(Event e, EventQueue q, Status s) {

        // 1. Retrieve the neighbours of the LineSegment to be removed.

        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred
            = s.predecessor(e.ls1);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> succ
            = s.successor(e.ls1);

        // 2. Remove the LineSegment from the Status.
        s.remove(e.ls1);

        // 3. Check for future intersections involving the neighbours of the
        //    removed LineSegment.
        InxData inx = null;
        if ((pred != null) && (succ != null)) {
            inx = LineSegment.intersection(pred.value, succ.value);
        }

        // 4. If any intersections are found then add them as Event to the
        //    EventQueue.
        if (inx != null) {
            switch (inx.dim) {
            case POINT:
                q.enqueue(new Event(EventType.INTERSECTION, inx.coord, pred.value, succ.value));
                break;
            case LINE:
                /**
                 * @todo - Process line intersections.
                 */
                break;
            case UNKNOWN:
                throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
            default:
                throw new IllegalStateException("Unrecognised InxDim");
            }
        }
    }

    private void handleInxEvent(Event e, EventQueue q, Status s) {
        // 1. Add the Event to this.inx.
        this.inx.add(e);

        // 2. Swap the order of the two associated LineSegments in the
        //    Status.
        s.swap(e.ls1, e.ls2);

        // 3. For each of the two modified LineSegments, check if their
        //    new neighbours intersect with them in the future,and if found
        //    add to the EventQueue.

        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred1
            = s.predecessor(e.ls1);
        if (pred1 != null) {
            final InxData inx = LineSegment.intersection(e.ls1, pred1.value);

            switch (inx.dim) {
            case POINT:
                q.enqueue(new Event(EventType.INTERSECTION, inx.coord, e.ls1, pred1.value));
                break;
            case LINE:
                /**
                 * @todo - Process line intersections.
                 */
                break;
            case UNKNOWN:
                throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
            default:
                throw new IllegalStateException("Unrecognised InxDim");
            }
        }

        final BinarySearchTreeNode<ComparableInteger, LineSegment> suc1
            = s.successor(e.ls1);

        if (suc1 != null) {
            final InxData inx = LineSegment.intersection(e.ls1, suc1.value);

            switch (inx.dim) {
            case POINT:
                q.enqueue(new Event(EventType.INTERSECTION, inx.coord, e.ls1, suc1.value));
                break;
            case LINE:
                /**
                 * @todo - Process line intersections.
                 */
                break;
            case UNKNOWN:
                throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
            default:
                throw new IllegalStateException("Unrecognised InxDim");
            }
        }

        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred2
            = s.predecessor(e.ls2);

        if (pred2 != null) {
            final InxData inx = LineSegment.intersection(e.ls2, pred2.value);

            switch (inx.dim) {
            case POINT:
                q.enqueue(new Event(EventType.INTERSECTION, inx.coord, e.ls2, pred2.value));
                break;
            case LINE:
                /**
                 * @todo - Process line intersections.
                 */
                break;
            case UNKNOWN:
                throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
            default:
                throw new IllegalStateException("Unrecognised InxDim");
            }
        }

        final BinarySearchTreeNode<ComparableInteger, LineSegment> suc2
            = s.successor(e.ls2);

        if (suc2 != null) {
            final InxData inx = LineSegment.intersection(e.ls2, suc2.value);

            switch (inx.dim) {
            case POINT:
                q.enqueue(new Event(EventType.INTERSECTION, inx.coord, e.ls2, suc2.value));
                break;
            case LINE:
                /**
                 * @todo - Process line intersections.
                 */
                break;
            case UNKNOWN:
                throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
            default:
                throw new IllegalStateException("Unrecognised InxDim");
            }
        }
    }
}
