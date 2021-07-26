
public class Edge {
	public String id = null;
	public int vid1 = -1;
	public int vid2 = -1;
	public Triangle t1 = null;
	public Triangle t2 = null;
	
	public boolean containsVertex(int i) {
		return ((this.vid1 == i) || (this.vid2 == i));
	}
	
	public Edge(String id_, int vid1_, int vid2_, Triangle t1_, Triangle t2_) {
		this.id = id_;
		this.vid1 = vid1_;
		this.vid2 = vid2_;
		this.t1 = t1_;
		this.t2 = t2_;
	}
}
