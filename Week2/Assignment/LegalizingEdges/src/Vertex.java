
public class Vertex {
	private int x;
	private int y;
	private int id;
	
	public Vertex(int x_, int y_, int id_) {
		this.x = x_;
		this.y = y_;
		this.id = id_;
	}
	
	public int x() {
		return this.x;
	}
	
	public int y() {
		return this.y;
	}
	
	public int id() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
