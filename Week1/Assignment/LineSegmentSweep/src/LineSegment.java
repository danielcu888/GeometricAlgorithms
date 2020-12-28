
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
            this.intercept = this.start.y;
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
     *   If this LineSegment is vertical, and x is that
     *   of this LineSegment, then the minimum
     *   y-coordinate of this LineSegment will be returned.
     * @return null If x is out of range of the x-extent
     *    of this LineSegment.
     * @return start.y for a vertical LineSegment coi with x.
     */
    public Double y(double x) {

        if ((x < this.minx) || (x > this.maxx)) {
            return null;
        }

        if (this.isVertical && (this.start.x == x)) {
            return this.miny;
        }

        return (this.gradient * x) + this.intercept;
    }

    /**
     * Return the x-ordinate of the Coordinate of the
     * specified y-ordinate that is coi with this LineSegment.
     * @param y The specified x-ordinate.
     * @return The x-ordinate of the Coordinate of the
     *   specified y-ordinate that is coi with this LineSegment.
     *   If this LineSegment is horizontal, and y is equal
     *   to that of the LineSegment, then the minimum
     *   x-coordinate of this LineSegment will be returned.
     * @return null If y is out of range of the y-extent
     *    of this LineSegment.
     */
    public Double x(double y) {

        if ((y < this.miny) || (y > this.maxy)) {
            return null;
        }

        if (this.isHorizontal && (this.start.y == y)) {
            return this.minx;
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
     * Return the y-intercept of the infinite line associated
     * with this LineSegment.
     * @return The y-intercept of this LineSegment.
     * @return start.y if this LineSegment is horizontal.
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

        if (this.isVertical) {
            if (this.start.x == c.x) {
                return (c.y >= this.miny) && (c.y <= this.maxy);
            } else {
                return false;
            }
        }

        final Double y = this.y(c.x);
        if (y == null) {
            return false;
        }

        return y == c.y;
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

        // Handle scenarios where one or both lines are vertical.

        if (ls1.isVertical && ls2.isVertical) {
            // Both lines are vertical.

            if (ls1.start.x == ls2.start.x) {
                // Both vertical lines share same x value.

                int count1 = 0;
                int count2 = 0;
                final boolean ls1Cls2S = ls1.contains(ls2.start);
                if (ls1Cls2S) {
                    ++count1;
                }
                final boolean ls1Cls2E = ls1.contains(ls2.end);
                if (ls1Cls2E) {
                    ++count1;
                }
                final boolean ls2Cls1S = ls2.contains(ls1.start);
                if (ls2Cls1S) {
                    ++count2;
                }
                final boolean ls2Cls1E = ls2.contains(ls1.end);
                if (ls2Cls1E) {
                    ++count2;
                }

                if ((count1 != 0) || (count2 != 0)) {
                    ret = new InxData();
                    if (ls1Cls2S) {
                        ret.coord = ls2.start;
                    } else if (ls1Cls2E) {
                        ret.coord = ls2.end;
                    } else if (ls2Cls1S) {
                        ret.coord = ls1.start;
                    } else if (ls2Cls1E) {
                        ret.coord = ls1.end;
                    }

                    if ((count1 == 1) && (count2 == 1)) {
                        ret.dim = InxDim.POINT;
                    } else {
                        ret.dim = InxDim.LINE;
                    }
                }
            }

            return ret;

        } else if (ls1.isVertical) {
            if ((ls2.minx <= ls1.start.x) && (ls2.maxx >= ls1.start.x)) {
                // Point intersection.
                ret = new InxData();
                ret.coord = new Coordinate(ls1.start.x, ls2.y(ls1.start.x));
                ret.dim = InxDim.POINT;
            }

            return ret;
        } else if (ls2.isVertical) {
            if ((ls1.minx <= ls2.start.x) && (ls1.maxx >= ls2.start.x)) {
                // Point intersection.
                ret = new InxData();
                ret.coord = new Coordinate(ls2.start.x, ls1.y(ls2.start.x));
                ret.dim = InxDim.POINT;
            }
            return ret;
        }

        // Handle scenarios where neither line is vertical.

        if (ls1.gradient() == ls2.gradient()) {

            // Identical gradients - either line inx or no intersections.

            if (ls1.intercept() != ls2.intercept()) {

                // Identical, non-infinite gradients, different intercepts,
                // - No intersection.
                return ret;
            }

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

            } else if (ls2.contains(ls1.start)) {

                // ls1.start is contained by ls2.

                ret = new InxData();
                ret.coord = ls1.start;
                ret.dim = InxDim.LINE;

            } else if (ls2.contains(ls1.end)) {

                // ls1.end is contained by ls2.

                ret = new InxData();
                ret.coord = ls1.end;
                ret.dim = InxDim.LINE;

            } else {

                // No end point contained - No intersections.
                return ret;
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
