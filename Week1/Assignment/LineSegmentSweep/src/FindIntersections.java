import java.util.ArrayList;

public class FindIntersections {

    private static boolean ENABLE_DEBUGGING = false;

    // Private data members
    private ArrayList<Event> inx;

    // Private methods

    /**
     * Process a specified StartEvent.
     * @param e The START Event to be processed.
     * @param q The EventQueue from which the Event was dequeued.
     * @param s The Status managing the LineSegments associated
     *   with the sweep pertaining to the specified Event.
     */
    private void handleStartEvent(Event e, EventQueue q, Status s) {

        // 1. Insert the LineSegment into the Status.
        s.insert(e.ls1);

        // 2. Retrieve the neighbouring LineSegments of the new LineSegment
        //    currently in the Status.

        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred
            = s.predecessor(e.ls1);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> succ
            = s.successor(e.ls1);

        // 3. Check for future intersections between the new LineSegment and
        //    those retrieved.

        InxData pred_inx = null;
        if (pred != null) {
            pred_inx = LineSegment.intersection(e.ls1, pred.value);
        }

        InxData succ_inx = null;
        if (succ != null) {
            succ_inx = LineSegment.intersection(e.ls1, succ.value);
        }

        // 4. If any intersections are found, enqueue them as Events, with the
        //    LineSegment with the smaller key being marked as ls1. (They must be
        //    at y >= to that of the current event since the current event is
        //    associated with the start of one of the intersecting lines).

        if (pred_inx != null) {

            final LineSegment ls1 = (e.ls1.id < pred.value.id) ? e.ls1 : pred.value;
            final LineSegment ls2 = (ls1 != e.ls1) ? e.ls1 : pred.value;

            switch (pred_inx.dim) {
            case POINT:
                q.enqueue(new Event(EventType.POINT_INTERSECTION, pred_inx.coord, ls1, ls2));
                break;
            case LINE:
                q.enqueue(new Event(EventType.LINE_INTERSECTION, pred_inx.coord, ls1, ls2));
                break;
            case UNKNOWN:
                throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
            default:
                throw new IllegalStateException("Unrecognised InxDim");
            }
        }

        if (succ_inx != null) {

            final LineSegment ls1 = (e.ls1.id < succ.value.id) ? e.ls1 : succ.value;
            final LineSegment ls2 = (ls1 != e.ls1) ? e.ls1 : succ.value;

            switch (succ_inx.dim) {
            case POINT:
                q.enqueue(new Event(EventType.POINT_INTERSECTION, succ_inx.coord, ls1, ls2));
                break;
            case LINE:
                q.enqueue(new Event(EventType.LINE_INTERSECTION, succ_inx.coord, ls1, ls2));
                break;
            case UNKNOWN:
                throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
            default:
                throw new IllegalStateException("Unrecognised InxDim");
            }
        }
    }

    /**
     * Process a specified EndEvent.
     * @param e The END Event to be processed.
     * @param q The EventQueue from which the Event was dequeued.
     * @param s The Status managing the LineSegments associated
     *   with the sweep pertaining to the specified Event.
     */
    private void handleEndEvent(Event e, EventQueue q, Status s) {

        // 1. Retrieve the Status neighbours of the LineSegment to be removed.

        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred
            = s.predecessor(e.ls1);

        final BinarySearchTreeNode<ComparableInteger, LineSegment> succ
            = s.successor(e.ls1);

        // 2. Remove the LineSegment from the Status.
        s.remove(e.ls1);

        // 3. Check for intersections between the Status neighbours of the
        //    removed LineSegment.
        InxData inx = null;
        if ((pred != null) && (succ != null)) {
            inx = LineSegment.intersection(pred.value, succ.value);
        }

        // 4. If an intersection is found then add it as an Event to the
        //    EventQueue, with the LineSegment with the smaller key being
        //    marked as ls1.
        //    We ignore intersections at y values < that of the current end
        //    event since we must have processed them before.

        if (inx != null) {
            if (inx.coord.y >= e.coord.y) {

                final LineSegment ls1 = (pred.value.id < succ.value.id) ? pred.value : succ.value;
                final LineSegment ls2 = (ls1 != pred.value) ? pred.value : succ.value;

                switch (inx.dim) {
                case POINT:
                    q.enqueue(new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2));
                    break;
                case LINE:
                    q.enqueue(new Event(EventType.LINE_INTERSECTION, inx.coord, ls1, ls2));
                    break;
                case UNKNOWN:
                    throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                default:
                    throw new IllegalStateException("Unrecognised InxDim");
                }
            }
        }
    }

    /**
     * Process a specified Intersection Event.
     * @param e The *_INTERSECTION Event to be processed.
     * @param q The EventQueue from which the Event was dequeued.
     * @param s The Status managing the LineSegments associated
     *   with the sweep pertaining to the specified Event.
     */
    private void handleInxEvent(Event e, EventQueue q, Status s) {

        // 1. Add the Event to the output collection.
        this.inx.add(e);

        // 2. Swap the order of the two associated LineSegments in the
        //    Status.
        s.swap(e.ls1, e.ls2);

        // 3. For each of the two intersecting LineSegments, check if their
        //    new Status neighbours intersect with them, and, if so, enqueue
        //    the intersections as Events to the EventQueue, with ls1 of that
        //    Event being the LineSegment with the smaller id.
        //    We skip processing predecessors or successors that are just
        //    one of the two LineSegments of the current event, since there can
        //    be only one intersection between any pair and we have just
        //    processed it.
        //    We also skip processing intersections at y smaller than that of the
        //    current Event.

        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred1
            = s.predecessor(e.ls1);

        if ((pred1 != null) && (pred1.value != e.ls2)) {

            final InxData inx = LineSegment.intersection(e.ls1, pred1.value);

            if (inx != null) {
                if (inx.coord.y >= e.coord.y) {

                    final LineSegment ls1 = (e.ls1.id < pred1.value.id) ? e.ls1 : pred1.value;
                    final LineSegment ls2 = (ls1 != e.ls1) ? e.ls1 : pred1.value;

                    switch (inx.dim) {
                    case POINT:
                        q.enqueue(new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2));
                        break;
                    case LINE:
                        q.enqueue(new Event(EventType.LINE_INTERSECTION, inx.coord, ls1, ls2));
                        break;
                    case UNKNOWN:
                        throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                    default:
                        throw new IllegalStateException("Unrecognised InxDim");
                    }
                }
            }
        }

        final BinarySearchTreeNode<ComparableInteger, LineSegment> suc1
            = s.successor(e.ls1);

        if ((suc1 != null) && (suc1.value != e.ls1)) {

            final InxData inx = LineSegment.intersection(e.ls1, suc1.value);

            if (inx != null) {
                if (inx.coord.y >= e.coord.y) {

                    final LineSegment ls1 = (e.ls1.id < suc1.value.id) ? e.ls1 : suc1.value;
                    final LineSegment ls2 = (ls1 != e.ls1) ? e.ls1 : suc1.value;

                    switch (inx.dim) {
                    case POINT:
                        q.enqueue(new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2));
                        break;
                    case LINE:
                        q.enqueue(new Event(EventType.LINE_INTERSECTION, inx.coord, ls1, ls2));
                        break;
                    case UNKNOWN:
                        throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                    default:
                        throw new IllegalStateException("Unrecognised InxDim");
                    }
                }
            }
        }

        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred2
            = s.predecessor(e.ls2);

        if ((pred2 != null) && (pred2.value != e.ls1)) {

            final InxData inx = LineSegment.intersection(e.ls2, pred2.value);

            if (inx != null) {
                if (inx.coord.y >= e.coord.y) {

                    final LineSegment ls1 = (e.ls2.id < pred2.value.id) ? e.ls2 : pred2.value;
                    final LineSegment ls2 = (ls1 != e.ls2) ? e.ls2 : pred2.value;

                    switch (inx.dim) {
                    case POINT:
                        q.enqueue(new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2));
                        break;
                    case LINE:
                        q.enqueue(new Event(EventType.LINE_INTERSECTION, inx.coord, ls1, ls2));
                        break;
                    case UNKNOWN:
                        throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                    default:
                        throw new IllegalStateException("Unrecognised InxDim");
                    }
                }
            }
        }

        final BinarySearchTreeNode<ComparableInteger, LineSegment> suc2
            = s.successor(e.ls2);

        if ((suc2 != null) && (suc2.value != e.ls1)) {

            final InxData inx = LineSegment.intersection(e.ls2, suc2.value);

            if (inx != null) {
                if (inx.coord.y >= e.coord.y) {

                    final LineSegment ls1 = (e.ls2.id < suc2.value.id) ? e.ls2 : suc2.value;
                    final LineSegment ls2 = (ls1 != e.ls2) ? e.ls2 : suc2.value;

                    switch (inx.dim) {
                    case POINT:
                        q.enqueue(new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2));
                        break;
                    case LINE:
                        q.enqueue(new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2));
                        break;
                    case UNKNOWN:
                        throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                    default:
                        throw new IllegalStateException("Unrecognised InxDim");
                    }
                }
            }
        }
    }

    // Public constructors

    /**
     * Construct using a collection of LineSegments whose
     * intersections are to be found.
     * @param segments A collection of LineSegments whose
     *   intersections are to be found.
     */
    FindIntersections(ArrayList<LineSegment> segments) {

        if (segments == null) {
            throw new IllegalArgumentException();
        }

        // Construct empty list to store the Events of the sweep.
        this.inx = new ArrayList<Event>();

        // Construct empty EventQueue to manage the Events.
        final EventQueue q = new EventQueue();

        // Populate the EventQueue with the START and END Events.
        for (LineSegment ls : segments) {

            if (ls == null) {
                throw new IllegalArgumentException("null LineSegment.");
            }

            // Clone and reverse any LineSegment that has start.y > end.y.
            if (ls.start.y > ls.end.y) {
                // Clone and reverse.
                ls = new LineSegment(ls.id,ls.end,ls.start);
            }

            // Add the endpoint events for the current LineSegment.

            q.enqueue(new Event(EventType.START, ls.start, ls));

            if (FindIntersections.ENABLE_DEBUGGING) {
                System.out.println(q.toString());
            }

            q.enqueue(new Event(EventType.END, ls.end, ls));

            if (FindIntersections.ENABLE_DEBUGGING) {
                System.out.println(q.toString());
            }
        }

        // Construct empty Status.
        final Status s = new Status();

        // Process Events at increasing y values.
        while (!q.empty()) {

            // Retrieve next Event.
            final Event e = q.dequeue();

            if (FindIntersections.ENABLE_DEBUGGING) {
                System.out.println("Processing event: " + e.toString() + "...");
                System.out.println(q.toString());
            }

            // Switch on type of current Event.
            final EventType et = e.type;
            switch (et) {
            case START:
                this.handleStartEvent(e, q, s);
                break;
            case END:
                this.handleEndEvent(e, q, s);
                break;
            case POINT_INTERSECTION:
                // Fallthrough.
            case LINE_INTERSECTION:
                this.handleInxEvent(e, q, s);
                break;
            default:
                throw new IllegalArgumentException("Unrecognised EventType: " + et);
            }
        }

        if (!this.inx.isEmpty()) {
            // Remove duplicate intersection Events.

            // 1. sort the intersection events in ascending y-value.
            this.inx.sort(new IntersectionEventComparator());

            // 2. Iterate through Events with the same y and remove all Events with
            //    that have the same LineSegment references. When an Event with a larger
            //    y is found, repeat the process using that Event as the reference.
            Event referenceEvent = null;
            for (Event e : this.inx) {

                // Check this Event is an intersection event.
                if ((e.type != EventType.POINT_INTERSECTION) && (e.type != EventType.LINE_INTERSECTION)) {
                    throw new IllegalStateException("Non-intersection Event found.");
                }

                if ((referenceEvent == null) || (referenceEvent.coord.y < e.coord.y)) {
                    // Set new reference Event.
                    referenceEvent = e;
                } else if ((e.ls1 == referenceEvent.ls1) && (e.ls2 == referenceEvent.ls2)) {
                    // There can only be 1 intersection Event involving the same 2 LineSegments - remove duplicate.
                    this.inx.remove(e);
                }
            }
        }
    }

    // Public methods

    /**
     * Return a collection of the intersection events associated with the
     * LineSegments associated with this FindIntersections object.
     * @return Return a collection of the intersection events associated
     *   with the LineSegments associated with this FindIntersections
     *   object.
     */
    public ArrayList<Event> intersections() {
        return this.inx;
    }
}
