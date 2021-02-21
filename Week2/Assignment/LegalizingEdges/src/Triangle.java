
public class Triangle {
	private DirectedEdge[] edges;
	
	public Triangle() {
		this.edges = null;
	}
	
	public DirectedEdge[] edges() {
		return this.edges;
	}
	
	public void updateEdges(DirectedEdge[] edges) {
		if (edges.length != 3) {
			throw new IllegalArgumentException("Edge list length must be 3.");
		}
		
		this.edges = edges;
	}
	
}
