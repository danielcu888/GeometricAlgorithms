
public class DirectedEdge {
	private Vertex start;
	private Vertex end;
	private Triangle tri;
	private boolean mark;
	
	public DirectedEdge(Vertex start_, Vertex end_, Triangle t_) {
		this.start = start_;
		this.end = end_;
		this.tri = t_;
		this.mark = false;
	}

	public DirectedEdge(Vertex start_, Vertex end_) {
		this.start = start_;
		this.end = end_;
		this.tri = null;
		this.mark = false;
	}

	
	public Vertex start() {
		return this.start;
	}
	
	public Vertex end() {
		return this.end;
	}
	
	public Triangle triangle() {
		return this.tri;
	}
	
	public boolean getMark() {
		return this.mark;
	}
	
	public void setMark(boolean mark_) {
		this.mark = mark_;
	}
	
	public void setTriangle(Triangle t) {
		this.tri = t;
	}
}
