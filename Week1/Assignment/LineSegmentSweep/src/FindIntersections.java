import java.util.ArrayList;
import java.util.Collections;

public class FindIntersections {

    // Private data members

    private static boolean ENABLE_DEBUGGING = false;
    private static boolean PRINT_RESULTS = false;
    private static boolean PRINT_SUBMISSION_RESULTS = true;
    private ArrayList<Event> inx = null;
    private ArrayList<Event> events = null;
    private boolean[][] marked = null;

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
            // Check we haven't already found the intersection between these two
            // LineSegments.
            if (!this.marked[e.ls1.id][pred.value.id]) {
                pred_inx = LineSegment.intersection(e.ls1, pred.value);
            } else if (FindIntersections.ENABLE_DEBUGGING) {
                System.out.println("Already found intersection between segments: "
                				   + e.ls1.id
                                   + " and "
                                   + pred.value.id
                                  );
            }
        }

        InxData succ_inx = null;
        if (succ != null) {
            // Check we haven't already found the intersection between these two
            // LineSegments.
            if (!this.marked[e.ls1.id][succ.value.id]) {
                succ_inx = LineSegment.intersection(e.ls1, succ.value);
            } else if (FindIntersections.ENABLE_DEBUGGING) {
                System.out.println("Already found intersection between segments: "
                                   + e.ls1.id
                                   + " and "
                                   + succ.value.id
                                  );
            }
        }

        // 4. If any intersections are found, enqueue them as Events, with the
        //    LineSegment with the smaller key being marked as ls1. (They must be
        //    at y >= to that of the current event since the current event is
        //    associated with the start of one of the intersecting lines).

        if (pred_inx != null) {
            if ((pred_inx.coord.y <= e.coord.y)) {
                final LineSegment ls1 = (e.ls1.id < pred.value.id) ? e.ls1 : pred.value;
                final LineSegment ls2 = (ls1 != e.ls1) ? e.ls1 : pred.value;

                Event new_event = null;
                switch (pred_inx.dim) {
                case POINT:
                    new_event = new Event(EventType.POINT_INTERSECTION, pred_inx.coord, ls1, ls2);
                    break;
                case LINE:
                    new_event = new Event(EventType.LINE_INTERSECTION, pred_inx.coord, ls1, ls2);
                    break;
                case UNKNOWN:
                    throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                default:
                    throw new IllegalStateException("Unrecognised InxDim");
                }
                
                if (FindIntersections.ENABLE_DEBUGGING) {
                	System.out.println("Found intersection: " + new_event);
                }
                
                q.enqueue(new_event);

                // Update marked with new intersection.
                this.marked[ls1.id][ls2.id] = this.marked[ls2.id][ls1.id] = true;
            }
        }

        if (succ_inx != null) {
            if ((succ_inx.coord.y <= e.coord.y) ) {
                final LineSegment ls1 = (e.ls1.id < succ.value.id) ? e.ls1 : succ.value;
                final LineSegment ls2 = (ls1 != e.ls1) ? e.ls1 : succ.value;

                Event new_event = null;
                switch (succ_inx.dim) {
                case POINT:
                    new_event = new Event(EventType.POINT_INTERSECTION, succ_inx.coord, ls1, ls2);
                    break;
                case LINE:
                    new_event = new Event(EventType.LINE_INTERSECTION, succ_inx.coord, ls1, ls2);
                    break;
                case UNKNOWN:
                    throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                default:
                    throw new IllegalStateException("Unrecognised InxDim");
                }

                if (FindIntersections.ENABLE_DEBUGGING) {
                	System.out.println("Found intersection: " + new_event);
                }
                
                q.enqueue(new_event);

                // Update marked with new intersection.
                this.marked[ls1.id][ls2.id] = this.marked[ls2.id][ls1.id] = true;
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
            // Check we haven't already found the intersection between these two
            // LineSegments.
            if (!this.marked[pred.value.id][succ.value.id]) {
            	inx = LineSegment.intersection(pred.value, succ.value);
            } else if (FindIntersections.ENABLE_DEBUGGING) {
                System.out.println("Already found intersection between segments: "
	                               + pred.value.id
	                               + " and "
	                               + succ.value.id
	                              );
            }
        }

        // 4. If an intersection is found then add it as an Event to the
        //    EventQueue, with the LineSegment with the smaller key being
        //    marked as ls1.
        //    We ignore intersections at y values < that of the current end
        //    event since we must have processed them before.

        if (inx != null) {
            if (inx.coord.y <= e.coord.y) {

                final LineSegment ls1 = (pred.value.id < succ.value.id) ? pred.value : succ.value;
                final LineSegment ls2 = (ls1 != pred.value) ? pred.value : succ.value;

                Event new_event = null;
                switch (inx.dim) {
                case POINT:
                    new_event = new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2);
                    break;
                case LINE:
                    new_event = new Event(EventType.LINE_INTERSECTION, inx.coord, ls1, ls2);
                    break;
                case UNKNOWN:
                    throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                default:
                    throw new IllegalStateException("Unrecognised InxDim");
                }

                if (FindIntersections.ENABLE_DEBUGGING) {
                	System.out.println("Found intersection: " + new_event);
                }

            	q.enqueue(new_event);

                // Update marked with new intersection.
                this.marked[ls1.id][ls2.id] = this.marked[ls2.id][ls1.id] = true;
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

            InxData inx = null;
                // Check we haven't already found the intersection between these two
                // LineSegments.
                if (!this.marked[pred1.value.id][e.ls1.id]) {
                	inx = LineSegment.intersection(e.ls1, pred1.value);
                } else if (FindIntersections.ENABLE_DEBUGGING) {
                    System.out.println("Already found intersection between segments: "
                                       + pred1.value.id
                                       + " and "
                                       + e.ls1.id
                                      );
                }

            if (inx != null) {
                if (inx.coord.y <= e.coord.y) {

                    final LineSegment ls1 = (e.ls1.id < pred1.value.id) ? e.ls1 : pred1.value;
                    final LineSegment ls2 = (ls1 != e.ls1) ? e.ls1 : pred1.value;

                    Event new_event = null;
                    switch (inx.dim) {
                    case POINT:
                        new_event = new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2);
                        break;
                    case LINE:
                        new_event = new Event(EventType.LINE_INTERSECTION, inx.coord, ls1, ls2);
                        break;
                    case UNKNOWN:
                        throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                    default:
                        throw new IllegalStateException("Unrecognised InxDim");
                    }

                    if (FindIntersections.ENABLE_DEBUGGING) {
                    	System.out.println("Found intersection: " + new_event);
                    }
                    
                    q.enqueue(new_event);

                    // Update marked with new intersection.
                    this.marked[ls1.id][ls2.id] = this.marked[ls2.id][ls1.id] = true;
                }
            }
        }

        final BinarySearchTreeNode<ComparableInteger, LineSegment> suc1
            = s.successor(e.ls1);

        if ((suc1 != null) && (suc1.value != e.ls1)) {

            InxData inx = null;
                // Check we haven't already found the intersection between these two
                // LineSegments.
                if (!this.marked[suc1.value.id][e.ls1.id]) {
                	inx = LineSegment.intersection(e.ls1, suc1.value);
                } else if (FindIntersections.ENABLE_DEBUGGING) {
                    System.out.println("Already found intersection between segments: "
                                       + suc1.value.id
                                       + " and "
                                       + e.ls1.id
                                      );
                }

            if (inx != null) {
                if (inx.coord.y <= e.coord.y) {

                    final LineSegment ls1 = (e.ls1.id < suc1.value.id) ? e.ls1 : suc1.value;
                    final LineSegment ls2 = (ls1 != e.ls1) ? e.ls1 : suc1.value;

                    Event new_event = null;
                    switch (inx.dim) {
                    case POINT:
                        new_event = new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2);
                        break;
                    case LINE:
                        new_event = new Event(EventType.LINE_INTERSECTION, inx.coord, ls1, ls2);
                        break;
                    case UNKNOWN:
                        throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                    default:
                        throw new IllegalStateException("Unrecognised InxDim");
                    }

                    if (FindIntersections.ENABLE_DEBUGGING) {
                    	System.out.println("Found intersection: " + new_event);
                    }

                    q.enqueue(new_event);

                    // Update marked with new intersection.
                    this.marked[ls1.id][ls2.id] = this.marked[ls2.id][ls1.id] = true;
                }
            }
        }

        final BinarySearchTreeNode<ComparableInteger, LineSegment> pred2
            = s.predecessor(e.ls2);

        if ((pred2 != null) && (pred2.value != e.ls1)) {

            InxData inx = null;
                // Check we haven't already found the intersection between these two
                // LineSegments.
                if (!this.marked[pred2.value.id][e.ls2.id]) {
                	inx = LineSegment.intersection(e.ls2, pred2.value);
                } else if (FindIntersections.ENABLE_DEBUGGING) {
                    System.out.println("Already found intersection between segments: "
                                       + pred2.value.id
                                       + " and "
                                       + e.ls2.id
                                      );
                }

            if (inx != null) {
                if (inx.coord.y <= e.coord.y) {

                    final LineSegment ls1 = (e.ls2.id < pred2.value.id) ? e.ls2 : pred2.value;
                    final LineSegment ls2 = (ls1 != e.ls2) ? e.ls2 : pred2.value;

                    Event new_event = null;
                    switch (inx.dim) {
                    case POINT:
                        new_event = new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2);
                        break;
                    case LINE:
                        new_event = new Event(EventType.LINE_INTERSECTION, inx.coord, ls1, ls2);
                        break;
                    case UNKNOWN:
                        throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                    default:
                        throw new IllegalStateException("Unrecognised InxDim");
                    }

                    if (FindIntersections.ENABLE_DEBUGGING) {
                    	System.out.println("Found intersection: " + new_event);
                    }

                    q.enqueue(new_event);

                    // Update marked with new intersection.
                    this.marked[ls1.id][ls2.id] = this.marked[ls2.id][ls1.id] = true;
                }
            }
        }

        final BinarySearchTreeNode<ComparableInteger, LineSegment> suc2
            = s.successor(e.ls2);

        if ((suc2 != null) && (suc2.value != e.ls1)) {

            InxData inx = null;
                // Check we haven't already found the intersection between these two
                // LineSegments.
                if (!this.marked[suc2.value.id][e.ls2.id]) {
                	inx = LineSegment.intersection(e.ls2, suc2.value);
                } else if (FindIntersections.ENABLE_DEBUGGING) {
                    System.out.println("Already found intersection between segments: "
	                                   + suc2.value.id
	                                   + " and "
	                                   + e.ls2.id
	                                  );
                }

            if (inx != null) {
                if (inx.coord.y <= e.coord.y) {

                    final LineSegment ls1 = (e.ls2.id < suc2.value.id) ? e.ls2 : suc2.value;
                    final LineSegment ls2 = (ls1 != e.ls2) ? e.ls2 : suc2.value;

                    Event new_event = null;
                    switch (inx.dim) {
                    case POINT:
                        new_event = new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2);
                        break;
                    case LINE:
                        new_event = new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2);
                        break;
                    case UNKNOWN:
                        throw new IllegalStateException("Did not expect UNKNOWN InxDim.");
                    default:
                        throw new IllegalStateException("Unrecognised InxDim");
                    }

                    if (FindIntersections.ENABLE_DEBUGGING) {
                    	System.out.println("Found intersection: " + new_event);
                    }
                    
                    q.enqueue(new_event);

                    // Update marked with new intersection.
                    this.marked[ls1.id][ls2.id] = this.marked[ls2.id][ls1.id] = true;
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
     * @warning This will overwrite the ids of the segments
     *   so that they are equal to the respective index of the
     *   ArrayList at which each resides.
     */
    FindIntersections(ArrayList<LineSegment> segments) {

        if (segments == null) {
            throw new IllegalArgumentException();
        }

        // Construct empty list to store the intersection Events of the sweep.
        this.inx = new ArrayList<Event>();

        // Construct empty list to store the Events of the sweep.
        this.events = new ArrayList<Event>();

        // Construct empty EventQueue to manage the Events.
        final EventQueue q = new EventQueue();

        // Populate the EventQueue with the START and END Events.
        int id = 0;
        for (LineSegment ls : segments) {

            if (ls == null) {
                throw new IllegalArgumentException("null LineSegment.");
            }

            // Clone and reverse any LineSegment that has end.y > start.y.
            if (ls.end.y > ls.start.y) {
                // Clone and reverse.
                ls = new LineSegment(ls.id,ls.end,ls.start);
            }

            // Set id
            ls.id = id++;

            // Add the endpoint events for the current LineSegment.

            final Event s = new Event(EventType.START, ls.start, ls);
            q.enqueue(s);
            this.events.add(s);

            if (FindIntersections.ENABLE_DEBUGGING) {
                System.out.println(q.toString());
            }

            final Event e = new Event(EventType.END, ls.end, ls);
            q.enqueue(e);
            this.events.add(e);

            if (FindIntersections.ENABLE_DEBUGGING) {
                System.out.println(q.toString());
            }
        }

        // Initialise marked array.

        // 1. Initialise all segments as non-intersecting.
        this.marked = new boolean[segments.size()][];
        for (int i = 0; i < segments.size(); ++i) {
            this.marked[i] = new boolean[segments.size()];
        }

        // 2. Initialise segments as self-intersecting.
        for (int i = 0; i < segments.size(); ++i) {
            this.marked[i][i] = true;
        }

        // Construct empty Status.
        final Status s = new Status();

        // Process Events at decreasing y values.
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

            if (FindIntersections.ENABLE_DEBUGGING) {
                System.out.println(s.toString());
                System.out.println();
            }
        }

        if (!this.inx.isEmpty()) {
            // Remove duplicate intersection Events.

            ArrayList<Event> filtered_inx = new ArrayList<Event>();

            // 1. sort the intersection events in descending y-value.
            this.inx.sort(new IntersectionEventComparator());
            Collections.reverse(this.inx);

            // 2. Iterate through Events with the same y and remove all Events with
            //    that have the same LineSegment references. When an Event with a smaller
            //    y is found, repeat the process using that Event as the reference.
            Event referenceEvent = null;
            for (Event e : this.inx) {

                // Check this Event is an intersection event.
                if ((e.type != EventType.POINT_INTERSECTION) && (e.type != EventType.LINE_INTERSECTION)) {
                    throw new IllegalStateException("Non-intersection Event found.");
                }

                if (referenceEvent == null) {
                    // Add the reference Event to the filtered Event set
                    // and the total Event set.
                    filtered_inx.add(e);
                    this.events.add(e);

                    // Set new reference Event.
                    referenceEvent = e;
                } else if ((Math.abs(e.coord.y - referenceEvent.coord.y) > 1e-11) ||
                           (e.ls1 != referenceEvent.ls1) ||
                           (e.ls2 != referenceEvent.ls2)) {
                    // Event e occurs at different y to reference Event or
                    // is a non-duplicate Event at the same y.

                    // Set new reference Event.
                    referenceEvent = e;

                    // Add the reference Event to the filtered Event set
                        // and the total Event set.
                    filtered_inx.add(referenceEvent);
                    this.events.add(e);
                }
            }

            this.inx = filtered_inx;

            // Sort total Events list in descending order of y values.
            this.events.sort(new IntersectionEventComparator());
            Collections.reverse(this.events);

            if (FindIntersections.PRINT_RESULTS) {

                // Print intersection Event summary.
                System.out.println("\n******* Intersections - START ***********");
                if (this.inx.isEmpty()) {
                    System.out.println("\n======= No Intersections Found ========");
                } else {
                    System.out.println("======= "
                                       + this.inx.size()
                                       + " Intersection(s) Found: ========"
                                      );
                    int count = 1;
                    for (Event e : this.inx) {
                        System.out.println(count++ + ": " + e);
                    }
                }
                System.out.println("******* Intersections - END *************");

                // Print total Event summary.
                System.out.println("\n******* All Events - START **************");
                if (this.events.isEmpty()) {
                    System.out.println("======= No Events Found ==============");
                } else {
                    System.out.println("======= "
                                       + this.events.size()
                                       + " Events(s) Found: =============="
                                      );
                    int count = 1;
                    for (Event e : this.events) {
                        System.out.println(count++ + ": " + e);
                    }
                }
                System.out.println("******* All Events - END ****************");
            }
            
            if (FindIntersections.PRINT_SUBMISSION_RESULTS) {
            	System.out.println("Total #intersections: " + this.inx.size());
            	System.out.println("Total #events: " + this.events.size());
            	if (this.events.size() > 2) {
            		System.out.println(
            			"3rd event was of type: "
            		    + Event.convertEventTypeToInteger(this.events.get(2))
            		);
            	}

            	if (this.events.size() > 16) {
            		System.out.println(
            			"17th event was of type: "
            			+ Event.convertEventTypeToInteger(this.events.get(16))
            		);
            	}

            	if (this.events.size() > 98) {
            		System.out.println(
            			"99th event was of type: "
            			+ Event.convertEventTypeToInteger(this.events.get(98))
            		);
            	}
            	
            	System.out.println();
            }
        }
    }

    // Public methods

    /**
     * Return a collection of the intersection events associated with the
     * LineSegments associated with this FindIntersections object in order
     * of descending y-value (then descending x-value).
     * @return Return a collection of the intersection events associated
     *   with the LineSegments associated with this FindIntersections
     *   object in order of descending y-value (then descending x-value).
     */
    public ArrayList<Event> intersections() {
        return this.inx;
    }

    /**
     * Return a collection of the events associated with the LineSegments
     * associated with this FindIntersections object in order of descending
     * y-value (then descending x-value).
     * @return Return a collection of the events associated with the
     * LineSegments associated with this FindIntersections object in order
     * of descending y-value (then descending x-value).
     */
    public ArrayList<Event> events() {
        return this.events;
    }
}
