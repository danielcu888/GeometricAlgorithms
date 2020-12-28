public class Coordinate implements Comparable<Coordinate> {
	public double x;
	public double y;

	public Coordinate(double x_, double y_) {
		this.x = x_;
		this.y = y_;
	}

	@Override
	public int compareTo(Coordinate o) {
		if (this.y < o.y) {
			return -1;
		} else if (this.y > o.y) {
			return +1;
		} else if (this.x < o.x) {
			return -1;
		} else if (this.x > o.x) {
			return +1;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return "C(" + this.x + "," + this.y + ")";
	}
}