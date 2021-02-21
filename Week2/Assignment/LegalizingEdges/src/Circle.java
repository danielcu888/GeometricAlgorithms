
public class Circle {
	private double radius;
	private Coordinate centre;
	
	public Circle(Coordinate c_, double r_) {
		this.radius = r_;
		this.centre = c_;
	}

	public boolean contains(Coordinate p) {
		return Math.pow((p.x-this.centre.x),2.0)+Math.pow((p.y-this.centre.y), 2.0) < Math.pow(this.radius, 2.0);
	}

}
