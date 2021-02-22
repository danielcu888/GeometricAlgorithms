import java.util.LinkedList;
import java.util.Scanner;

public class Digraph {

	private int V;
	private final Vertex[] vertices;
	private final LinkedList<DirectedEdge>[] edges;
	private int flippedEdgeCount;
	
	@SuppressWarnings("unchecked")
	Digraph(Scanner sc) {
		
		// Initialize flipped edge count.
		this.flippedEdgeCount = 0;
		
		// Read vertex count.
		this.V = sc.nextInt();

		// Create vertex list.
		this.vertices = new Vertex[this.V];
		
		// Create DirectedEdge lists.
		this.edges = (LinkedList<DirectedEdge>[]) new LinkedList[this.V];
		
		// Read triangle count.
		final int numTriangles = sc.nextInt();

		for (int vid = 0; vid < this.V; ++vid) {
			
			// Create Vertex with id, vid.
			final int x = sc.nextInt();
			final int y = sc.nextInt();
			vertices[vid] = new Vertex(x, y, vid);
			
			// Create DirectedEdge lists for Vertex, vid.
			this.edges[vid] = new LinkedList<DirectedEdge>();
		}

		for (int t = 0; t < numTriangles; ++t) {

			// Create Triangle.
			final Triangle tri = new Triangle();
			
			// Read indices of Vertexes of current Triangle.
			final int va = sc.nextInt();
			final int vb = sc.nextInt();
			final int vc = sc.nextInt();
			
			// Create the edges of the current Triangle.
			DirectedEdge[] tedges = new DirectedEdge[3];
			tedges[0] = this.addEdge(va, vb, tri);
			tedges[1] = this.addEdge(vb, vc, tri);
			tedges[2] = this.addEdge(vc, va, tri);
			
			// Update the edges on the current Triangle.
			tri.updateEdges(tedges);
		}
		
		// Legalize edges.
		int vid = 0;
		while (vid < this.V) {
			if (vid == 0) {
				// We're starting again so clear all marks.
				this.clearAllMarks();
			}
			
			
			DirectedEdge flipCandidate = null;
			
			// Iterate over all the edges incident to Vertex vid.
			for (DirectedEdge evw : this.edges[vid]) {
				
				// Check if already marked - i.e., is legal.
				if (evw.getMark()) {
					continue;
				}
				
				final int v = evw.start().id();
				final int w = evw.end().id();
				
				// Check if flippable.
				if (!this.isFlippable(v,w)) {
					// Edge v->w is not flippable - skip.
					evw.setMark(true);
					continue;
				}
				
				if (this.isLegal(v, w)) {
					// evw is legal, and therefore so is ewv. Mark them both.

					evw.setMark(true);
					
					// Find ewv
					for (DirectedEdge e : this.edges[w]) {
						if (e.end().id() == v) {
							e.setMark(true);
							break;
						}
					}
					
					continue;
				} else if (flipCandidate != null){
					// v-w should be flipped, but we already have a flip candidate
					// with the same low(e) == v.
					// Update flip candidate to be the one with the lowest w.
					if (w < flipCandidate.end().id()) {
						// Update flip candidate.
						flipCandidate = evw;
					}
				} else {
					flipCandidate = evw;
				}
			}
			
			// Identified all illegal edges out from v.
			if (flipCandidate != null) {
				// We have a flip candidate - flip it.
				this.flip(flipCandidate.start().id(), flipCandidate.end().id());
				
				// reset counter
				vid = 0;
				
				// clear all marks
				this.clearAllMarks();
			}
		}
		
		// We have processed all edges in the graph without requiring a flip - we're done.
	}
	
	public boolean isLegal(int v, int w) {
		// 1a. Find DirectedEdge v->w
		DirectedEdge evw = null;
		for (DirectedEdge e : this.edges(v)) {
			if (e.end().id() == w) {
				evw = e;
				break;
			}
		}
		
		// 1b. Retrieve Triangle of evw
		Triangle tevw = evw.triangle();
		
		// 1c. Retrieve unshared vertex on tevw
		int x = -1;
		for (DirectedEdge e : tevw.edges()) {
			
			final int sid = e.start().id();
			if ((sid != v) && (sid != w)) {
				x = e.start().id();
				break;
			}
			
			final int eid = e.end().id();
			if ((eid != v) && (eid != w)) {
				x = e.end().id();
				break;
			}
		}
		
		// 2a. Find DirectedEdge w->v
		DirectedEdge ewv = null;
		for (DirectedEdge e : this.edges(w)) {
			if (e.end().id() == v) {
				ewv = e;
				break;
			}
		}
		
		// 2b. Retrieve Triangle of ewv
		Triangle tewv = ewv.triangle();
		
		// 2c. Retrieve unshared vertex on tewv
		int y = -1;
		for (DirectedEdge e : tewv.edges()) {
			
			final int sid = e.start().id();
			if ((sid != v) && (sid != w)) {
				y = e.start().id();
				break;
			}
			
			final int eid = e.end().id();
			if ((eid != v) && (eid != w)) {
				y = e.end().id();
				break;
			}
		}
		
		// 3. Retrieve circumcircle of tevw:
		final Circle ctevw = this.getCircumcircle(x, v, w);
		
		// 4. Check if y is contained by ctevw
		final boolean ycontained = ctevw.contains(new Coordinate(this.vertices[y].x(), this.vertices[y].y()));
		if (ycontained) {
			return false;
		}
		
		// 5. Retrieve circumcircle of tewv
		final Circle ctewv = this.getCircumcircle(y, w, v);
		
		// 6. Check if x is contained by ctewv
		final boolean xcontained = ctewv.contains(new Coordinate(this.vertices[x].x(), this.vertices[x].y()));
		if (xcontained) {
			return false;
		}
		
		// Edges v->w and w->v are both legal.
		return true;
	}
	
	
	public void clearAllMarks() {
		for (int vid = 0; vid < this.V; ++vid) {
			for (DirectedEdge e : this.edges[vid]) {
				e.setMark(false);
			}
		}
	}

	DirectedEdge addEdge(int v, int w, Triangle t) {
		// Add edge v->w:
		final DirectedEdge e = new DirectedEdge(this.vertices[v],this.vertices[w], t);
		this.edges[v].add(e);
		return e;
	}
	
	void removeEdge(int v, int w) {

		// Remove DirectedEdge v->w.
		for (DirectedEdge e : this.edges[v]) {
			if (e.end().id() == w) {
				this.edges[v].remove(e);
				return;
			}
		}
	}
	
	Iterable<DirectedEdge> edges(int v) {
		return this.edges[v];
	}

	int V() {
		return this.V;
	}
		
	boolean isFlippable(int v, int w) {
		// 1. Find DirectedEdge v->w
		DirectedEdge evw = null;
		for (DirectedEdge e : this.edges(v)) {
			if (e.end().id() == w) {
				evw = e;
				break;
			}
		}
		
		if (evw == null) {
			return false;
		}
		
		// 2. Find DirectedEdge w->v
		DirectedEdge ewv = null;
		for (DirectedEdge e : this.edges(w)) {
			if (e.end().id() == v) {
				ewv = e;
				break;
			}
		}
		
		if (ewv == null) {
			return false;
		}

		return true;
	}
	
	public Circle getCircumcircle(int x, int v, int w) {
		// Form the circle with chords x-v and x-w, used to
		// check illegality of edge v->w and w->v.

		final Coordinate px = new Coordinate(this.vertices[x].x(), this.vertices[x].y());
		
		// 1. Form perpendicular bisector of x-v, called bxv.
		final Coordinate pv = new Coordinate(this.vertices[v].x(), this.vertices[v].y());
		final Line lxv = new Line(px, pv);
		final Line bxv = lxv.perpendicularBisector();
		
		// 2. Form perpendicular bisector of x-w, called bxw.
		final Coordinate pw = new Coordinate(this.vertices[w].x(), this.vertices[w].y());
		final Line lxw = new Line(px, pw);
		final Line bxw = lxw.perpendicularBisector();

		// 3. Find intersection point of bxv and bxw, c.
		final InxData inxData = Line.intersection(bxv,bxw);
		if (inxData.dim != InxDim.POINT) {
			throw new IllegalStateException("Expected point intersection.");
		}
		final Coordinate c = inxData.coord;
		
		// 4. Find distance from intersection point to x, r.
		final double r = Math.sqrt(Math.pow((c.x-px.x), 2.0) + Math.pow((c.y-px.y), 2.0));
		
		// construct desired Circle
		return new Circle(c,r);
	}

	/**
	 * To flip E(v->w) and E(w->v) of Triangles T(E(v->w)) and
	 * T(E(w->v)) respectively we:
	 * a. Theoretically form the quadrilateral Q(T(E(v->w)), T(E(w->v))).
	 * b. Delete the edges E(v->w) and E(w->v).
	 * c. Cache the remaining edges of edges T(E(v->w)) and T(E(w->v)).
	 * d. Create edges E(x->y) and E(y->x) where x and
	 *    y are the uncommon vertices of Triangles T(E(v->w)) and T(E(w->v))
	 * e. construct new Triangles T(x,v,y) and T(x,w,y).
	 * f. The Triangles referenced by the old edges of T(E(v->w)) and T(E(w->v)) will need
	 *    to be updated to refer to T(x,v,y) and T(x,w,y).
	 *    
	 *       		w
	 * 
	 *    x            			y
	 * 
	 * 		        v
	 */
	void flip(int v, int w) {

		// 1a. Find DirectedEdge v->w
		DirectedEdge evw = null;
		for (DirectedEdge e : this.edges(v)) {
			if (e.end().id() == w) {
				evw = e;
				break;
			}
		}
		
		// 1b. Retrieve Triangle of evw
		Triangle tevw = evw.triangle();
		
		// 1c. Retrieve unshared vertex on tevw
		int x = -1;
		for (DirectedEdge e : tevw.edges()) {
			
			final int sid = e.start().id();
			if ((sid != v) && (sid != w)) {
				x = e.start().id();
				break;
			}
			
			final int eid = e.end().id();
			if ((eid != v) && (eid != w)) {
				x = e.end().id();
				break;
			}
		}
		
		// 2a. Find DirectedEdge w->v
		DirectedEdge ewv = null;
		for (DirectedEdge e : this.edges(w)) {
			if (e.end().id() == v) {
				ewv = e;
				break;
			}
		}
		
		// 2b. Retrieve Triangle of ewv
		Triangle tewv = ewv.triangle();
		
		// 2c. Retrieve unshared vertex on tewv
		int y = -1;
		for (DirectedEdge e : tewv.edges()) {
			
			final int sid = e.start().id();
			if ((sid != v) && (sid != w)) {
				y = e.start().id();
				break;
			}
			
			final int eid = e.end().id();
			if ((eid != v) && (eid != w)) {
				y = e.end().id();
				break;
			}
		}
		
		// 3a. Remove evw
		this.removeEdge(v, w);

		// 3b. Remove ewv
		this.removeEdge(w, v);
		
		// 4. Retrieve undeleted edges.
		// Triangle tevw had edges v->w, w->x, x->v.
		// Triangle tewv has edges w->v, v->y, y->w.
		
		// 4a. Retrieve the undeleted edges of tevw which are:
		//     1. w->x (called ewx) and
		//     2. x->v (called exv)
		DirectedEdge ewx = null;
		DirectedEdge exv = null;
		for (DirectedEdge e : tevw.edges()) {
			if ((e.start().id() == w) && (e.end().id() == x)) {
				ewx = e;
			}
			else if ((e.start().id() == x) && (e.end().id() == v)) {
				exv = e;
			}
			else {
				throw new IllegalStateException("Should not have this edge.");
			}
		}

		// 4b. Retrieve the undeleted edges of tewv which are:
		//     1. v->y (called evy) and
		//     2. y->w (called eyw)
		DirectedEdge evy = null;
		DirectedEdge eyw = null;
		for (DirectedEdge e : tewv.edges()) {
			if ((e.start().id() == v) && (e.end().id() == y)) {
				evy = e;
			}
			else if ((e.start().id() == y) && (e.end().id() == w)) {
				eyw = e;
			}
			else {
				throw new IllegalStateException("Should not have this edge.");
			}
		}
		
		// 5a. Create a new Triangle for edges w->x, *x->y* and y->w (called texy)
		Triangle texy = new Triangle();

		// 5b. Add edge exy
		final DirectedEdge exy = this.addEdge(x, y, texy);
		
		// 5c. Update Triangle references on ewx and eyw:
		ewx.setTriangle(texy);
		eyw.setTriangle(texy);
		
		// 5d. Update texy's edges.
		final DirectedEdge[] texyEdges = {ewx,exy,eyw};
		texy.updateEdges(texyEdges);
		
		// 6a. Create a new Triangle for edges v->y, *y->x* and x->v (called teyx).
		Triangle teyx = new Triangle();
		
		// 6b. Create edge eyx
		final DirectedEdge eyx = this.addEdge(y, x, teyx);
		
		// 6c. Update Triangle references on evy and exv:
		evy.setTriangle(teyx);
		exv.setTriangle(teyx);

		// 6d. Update teyx's edges.
		final DirectedEdge[] teyxEdges = {evy,eyx,exv};
		teyx.updateEdges(teyxEdges);
		
		// 7. Update flippedEdgeCount.
		++this.flippedEdgeCount;
	}

	int flippedEdgeCount() {
		return this.flippedEdgeCount;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (int v = 0; v < this.V; ++v) {
			sb.append(v+":");
			for (DirectedEdge e : this.edges[v]) {
				sb.append(v + "->" + e.end().id() +",");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
