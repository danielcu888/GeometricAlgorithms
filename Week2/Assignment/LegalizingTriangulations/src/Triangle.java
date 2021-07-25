
public class Triangle {

	public int id;
	public Edge e1 = null;
	public Edge e2 = null;
	public Edge e3 = null;
	
	public Triangle(int id_, Edge e1_, Edge e2_, Edge e3_) {
		this.id = id_;
		this.e1 = e1_;
		this.e2 = e2_;
		this.e3 = e3_;
	}
}
