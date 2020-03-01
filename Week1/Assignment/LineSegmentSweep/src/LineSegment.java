
public class LineSegment {
	private Point start;
	private Point end;
	private int id;
	
	public LineSegment(int id, Point start_, Point end_) {
		this.start = start_;
		this.end = end_;
	}

	public LineSegment(Point start_, Point end_) {
		this.start = start_;
		this.end = end_;
		this.id = -1;
	}

	public int id() {
		return this.id;
	}
	
	public Point start() {
		return this.start;
	}
	
	public Point end() {
		return this.end;
	}
	
	public double x(double y) {
		return (y - this.start.y) * (this.end.x - this.start.x) / (this.end.y - this.start.y);
	}
	
	public double y(double x) {
		return ((this.end.y - this.start.y) * x / (this.end.x - this.start.x)) + this.start.y;
	}
}
