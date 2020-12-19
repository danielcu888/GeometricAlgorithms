enum InxDim {
    UNKNOWN,
    POINT,
    LINE
}

class InxData {
    public Coordinate coord;
    public InxDim dim;

    InxData() {
        this.coord = null;
        this.dim = InxDim.UNKNOWN;
    }
}

public class LineSegment {
    public Integer id;
    public Coordinate start;
    public Coordinate end;
    private boolean isHorizontal;
    private boolean isVertical;
    private double gradient;
    private double intercept;
    private double minx;
    private double maxx;
    private double miny;
    private double maxy;

    public LineSegment(int id_, Coordinate start_, Coordinate end_) {
        this.id = id_;
        this.start = start_;
        this.end = end_;
        this.isHorizontal = (this.end.y == this.start.y);
        this.isVertical = (this.end.x == this.start.x);

        if (this.isVertical){
            this.gradient = (this.end.y > this.start.y)
                          ? Double.POSITIVE_INFINITY
                          : Double.NEGATIVE_INFINITY;
            this.intercept = Double.NaN;
        } else if (this.isHorizontal) {
            this.gradient = 0;
            this.intercept = this.start.y;
        } else {
            this.gradient = (this.end.y - this.start.y)/(this.end.x - this.start.x);
            this.intercept = this.start.y - (this.gradient * this.start.x);
        }

        this.minx = Double.min(this.start.x, this.end.x);
        this.maxx = Double.max(this.start.x, this.end.x);
        this.miny = Double.min(this.start.y, this.end.y);
        this.maxy = Double.max(this.start.y, this.end.y);
    }

    public Double y(double x) {
        if ((x < this.minx) || (x > this.maxx)) {
            return null;
        }

        return (this.gradient * x) + this.intercept;
    }

    public Double x(double y) {
        if ((y < this.miny) || (y > this.maxy)) {
            return null;
        }

        return (y - this.intercept) / this.gradient;
    }

    public double gradient() {
        return this.gradient;
    }

    public double intercept() {
        return this.intercept;
    }

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

    public static InxData intersection(LineSegment ls1, LineSegment ls2) {

        InxData ret = null;

        // Check lines are non-null.

        if (ls1 == null) {
            throw new NullPointerException("intersection - ls1 is null.");
        }

        if (ls2 == null) {
            throw new NullPointerException("intersection - ls2 is null.");
        }

        // Check that the infinite lines intersect.

        if (ls1.gradient() == ls2.gradient()) {
            // Identical gradients - either line or no intersections.
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
                    // Neither ls2 endpoint is contained by ls1 - No intersections.
                    return ret;
                }
            }
        } else {
            // Different infinite line gradients - point or no intersections.

            // Find single point on 1-D intersection.
            // g_1*x_inx + c_1 = g_2*x_inx + c_2
            // (g_1 - g_2)*x_inx = (c_2 - c_1)
            // x_inx = (c_2 - c_1)/(g_1 - g_2)
            final double x_inx
                = (ls2.intercept - ls1.intercept)/(ls1.gradient - ls2.gradient);

            // Check infinite line intersection is contained by bounded lines.
            if ((x_inx >= ls1.minx) && (x_inx <= ls1.maxx) && (x_inx >= ls2.minx) && (x_inx <= ls2.maxx))
            {
                // Infinite line intersection point is contained by both ls1 and ls2.

                // Calculate y_inx.
                final double y_inx = ls1.y(x_inx);

                // Construct return object.
                ret = new InxData();
                ret.coord = new Coordinate(x_inx, y_inx);
                ret.dim = InxDim.POINT;
            } else {
                // Intersection point not contained by both bounded lines - no intersections.
                return ret;
            }
        }

        return ret;
    }
}
