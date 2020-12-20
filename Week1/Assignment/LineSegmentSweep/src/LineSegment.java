
public class LineSegment {

    public Integer id = -1;

    public Coordinate start = null;
    public Coordinate end = null;

    private boolean isHorizontal = false;
    private boolean isVertical = false;

    private double gradient = Double.NaN;
    private double intercept = Double.NaN;

    private double minx = Double.NaN;
    private double maxx = Double.NaN;
    private double miny = Double.NaN;
    private double maxy = Double.NaN;

    /**
     * Two-parameter constructor.
     * @param id_ The ID of this LineSegment.
     * @param start_ The start of this LineSegment.
     * @param end_ The end of this LineSegment.
     */
    public LineSegment(int id_, Coordinate start_, Coordinate end_) {

        // Initialize id.
        this.id = id_;

        // Initialize end points.
        this.start = start_;
        this.end = end_;

        // Determine whether this LineSegment is aligned with the
        // coordinate axes.
        this.isHorizontal = (this.end.y == this.start.y);
        this.isVertical = (this.end.x == this.start.x);

        // Verify we have a non-zero length LineSegment.
        if (this.isHorizontal && this.isVertical) {
            throw new IllegalStateException("Zero length LineSegment.");
        }

        // Set extent.

        this.minx = Double.min(this.start.x, this.end.x);
        this.maxx = Double.max(this.start.x, this.end.x);
        this.miny = Double.min(this.start.y, this.end.y);
        this.maxy = Double.max(this.start.y, this.end.y);

        // Calculate dy/dx and the y-intercept.
        if (this.isVertical) {

            // Vertical line.

            if (this.end.y > this.start.y) {
                this.gradient = Double.POSITIVE_INFINITY;
            } else {
                this.gradient = Double.NEGATIVE_INFINITY;
            }

            if (this.start.x == 0) {
                this.intercept = this.start.y;
            }
        } else if (this.isHorizontal) {

            // Horizontal line.

            this.gradient = 0;
            if ((this.start.x * this.end.x) <= 0) {
                this.intercept = this.start.y;
            }
        } else {

            // General case.

            this.gradient = (this.end.y - this.start.y)/
                (this.end.x - this.start.x);

            this.intercept = this.start.y - (this.gradient * this.start.x);
        }
    }

    /**
     * Return the y-ordinate of the Coordinate of the
     * specified x-ordinate that is coi with this LineSegment.
     * @param x The specified x-ordinate.
     * @return The y-ordinate of the Coordinate of the
     *   specified x-ordinate that is coi with this LineSegment.
     * @return null If x is out of range of the x-extent
     *    of this LineSegment.
     */
    public Double y(double x) {

        if ((x < this.minx) || (x > this.maxx)) {
            return null;
        }

        return (this.gradient * x) + this.intercept;
    }

    /**
     * Return the x-ordinate of the Coordinate of the
     * specified y-ordinate that is coi with this LineSegment.
     * @param y The specified x-ordinate.
     * @return The x-ordinate of the Coordinate of the
     *   specified y-ordinate that is coi with this LineSegment.
     * @return null If y is out of range of the y-extent
     *    of this LineSegment.
     */
    public Double x(double y) {

        if ((y < this.miny) || (y > this.maxy)) {
            return null;
        }

        return (y - this.intercept) / this.gradient;
    }

    /**
     * Return the gradient, dy/dx, of this LineSegment.
     * @return The gradient, dy/dx, of this LineSegment.
     * @return Double.POSITIVE_INFINITY if this LineSegment
     *   is vertical and directed along the +y-axis, or
     *   Double.NEGATIVE_INFINITY if directed along the
     *   -y-axis.
     */
    public double gradient() {
        return this.gradient;
    }

    /**
     * Return the y-intercept of this LineSegment.
     * @return The y-intercept of this LineSegment.
     * @return Double.NaN if there is no y-intercept.
     * @return start.y if this LineSegment is horizontal
     *   and intercepts the y-axis.
     */
    public double intercept() {
        return this.intercept;
    }

    /**
     * Returns whether the specified Coordinate is coi.
     * with this LineSegment.
     * @param c The specified Coordinate.
     * @return Whether the specified Coordinate is coi
     *   with this LineSegment.
     * @throws NullPointerException if c is null.
     */
    public boolean contains(Coordinate c) {

        if (c == null) {
            throw new NullPointerException("contains - c is null.");
        }

        final Double y = this.y(c.x);
        if (y == null) {
            return false;
        }

        return y == c.x;
    }

    /**
     * Returns the location(s) and dimensionality of the intersection
     * of the two specified LineSegments.
     * @param ls1 The first LineSegment.
     * @param ls2 The second LineSegment.
     * @return An InxData containing the location and dimensionality of
     *    the intersection(s) of ls1 and ls2.
     * @return null If there are no intersections.
     */
    public static InxData intersection(LineSegment ls1, LineSegment ls2) {

        // Check lines are non-null.

        if (ls1 == null) {
            throw new NullPointerException("First LineSegment is null.");
        }

        if (ls2 == null) {
            throw new NullPointerException("Second LineSegment is null.");
        }

        InxData ret = null;

        if (ls1.gradient() == ls2.gradient()) {

            // Identical gradients - either line inx or no intersections.

            if (ls1.intercept() != ls2.intercept()) {

                // Identical gradients, different intercepts - No intersection.
                return ret;

            } else {

                // Identical gradients, identical intercepts - line intersection.

                if (ls1.contains(ls2.start)) {

                    // ls2.start is contained by ls1.

                    ret = new InxData();
                    ret.coord = ls2.start;
                    ret.dim = InxDim.LINE;

                } else if (ls1.contains(ls2.end)) {

                    // ls2.end is contained by ls1.

                    ret = new InxData();
                    ret.coord = ls2.end;
                    ret.dim = InxDim.LINE;

                } else {

                    // Neither ls2 end point is contained by ls1 - No intersections.
                    return ret;
                }
            }
        } else {

            // Different gradients - point or no intersections.

            // Find x-ordinate of single point intersection on infinite lines.
            //
            // g_1*x_inx + c_1 = g_2*x_inx + c_2
            // (g_1 - g_2)*x_inx = (c_2 - c_1)
            // x_inx = (c_2 - c_1)/(g_1 - g_2)
            final double x_inx
                = (ls2.intercept - ls1.intercept)/(ls1.gradient - ls2.gradient);

            // Check if bounded lines both contain the intersection.

            if ((x_inx >= ls1.minx) && (x_inx <= ls1.maxx) &&
                (x_inx >= ls2.minx) && (x_inx <= ls2.maxx)
                ) {

                // Point intersection.

                // Calculate y_inx.
                final double y_inx = ls1.y(x_inx);

                ret = new InxData();
                ret.coord = new Coordinate(x_inx, y_inx);
                ret.dim = InxDim.POINT;
            } else {

                // No intersections.
                return ret;
            }
        }

        return ret;
    }
}
