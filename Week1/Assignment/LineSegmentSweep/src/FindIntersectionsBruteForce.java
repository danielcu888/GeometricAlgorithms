import java.util.ArrayList;

public class FindIntersectionsBruteForce {

        // Private data members
        private ArrayList<Event> inx;
        private static boolean PRINT_RESULTS = true;

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
    FindIntersectionsBruteForce(ArrayList<LineSegment> segments) {

        if (segments == null) {
            throw new IllegalArgumentException();
        }

        // Construct empty list to store the intersection Events of the sweep.
        this.inx = new ArrayList<Event>();

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
        }

        for (int i = 0; i < segments.size(); ++i) {
                for (int j = i+1; j < segments.size(); ++j) {
                        final LineSegment ls1 = segments.get(i);
                        final LineSegment ls2 = segments.get(j);

                        final InxData inx = LineSegment.intersection(ls1, ls2);
                        if (inx != null) {
                                switch (inx.dim)
                                {
                                case POINT:
                                        Event e = new Event(EventType.POINT_INTERSECTION, inx.coord, ls1, ls2);
                                        this.inx.add(e);
                                        break;
                                case LINE:
                                        this.inx.add(new Event(EventType.LINE_INTERSECTION, inx.coord, ls1, ls2));
                                        break;
                                default:
                                        throw new IllegalArgumentException("Unknown InxDim: " + inx.dim);
                                }
                        }
                }
        }

        if (FindIntersectionsBruteForce.PRINT_RESULTS) {

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
                        //System.out.println("(0 0," + e.coord.x + " " + e.coord.y + "),");
                }
            }
            System.out.println("******* Intersections - END *************");
        }
    }

    // Public methods

    /**
     * Return a collection of the intersection events.
     * @return Return a collection of the intersection events.
     */
    public ArrayList<Event> intersections() {
        return this.inx;
    }
}
