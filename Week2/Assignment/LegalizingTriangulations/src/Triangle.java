
public class Triangle {

	public int id;
	public Edge e1 = null;
	public Edge e2 = null;
	public Edge e3 = null;

	public int unsharedVertexIndex(Edge e) {
		if (e.id == e1.id) {
			if (!e.containsVertex(e2.vid1)) return e2.vid1;
			if (!e.containsVertex(e2.vid2)) return e2.vid2;
			if (!e.containsVertex(e3.vid1)) return e3.vid1;
			if (!e.containsVertex(e3.vid2)) return e3.vid2;

			throw new IllegalStateException("Could not find unshared vertex for Edge " + e.id + " in Triangle " + this.id);
		} else if (e.id == e2.id) {
			if (!e.containsVertex(e1.vid1)) return e1.vid1;
			if (!e.containsVertex(e1.vid2)) return e1.vid2;
			if (!e.containsVertex(e3.vid1)) return e3.vid1;
			if (!e.containsVertex(e3.vid2)) return e3.vid2;

			throw new IllegalStateException("Could not find unshared vertex for Edge " + e.id + " in Triangle " + this.id);			
		} else if (e.id == e3.id) {
			if (!e.containsVertex(e1.vid1)) return e1.vid1;
			if (!e.containsVertex(e1.vid2)) return e1.vid2;
			if (!e.containsVertex(e2.vid1)) return e2.vid1;
			if (!e.containsVertex(e2.vid2)) return e2.vid2;

			throw new IllegalStateException("Could not find unshared vertex for Edge " + e.id + " in Triangle " + this.id);						
		} else {
			throw new IllegalArgumentException("Edge " + e.id + " is not in Triangle " + this.id);
		}
	}

	public Triangle(int id_, Edge e1_, Edge e2_, Edge e3_) {
		this.id = id_;
		this.e1 = e1_;
		this.e2 = e2_;
		this.e3 = e3_;
	}
}
