import java.util.SortedSet;
import java.util.HashMap;
import java.util.Scanner;

public class Triangulation {

	private HashMap<Integer,Vertex> mVertices = new HashMap<Integer, Vertex>();
	private HashMap<String,Edge> mEdges = new HashMap<String,Edge>();

	private int mNumVertices;
	private int mNumTriangles;

	private int mTid = -1;

	public Triangulation(Scanner sc) throws Exception {
		
		// 1. Read first line.

		if (!sc.hasNextLine()) {
			throw new Exception("Failed to read first line.");
		}

		Scanner lineScanner = new Scanner(sc.nextLine());

		// 2. Read the number of vertices.
		
		if (!lineScanner.hasNextInt()) {
			lineScanner.close();
			sc.close();
			throw new Exception("Failed to read number of vertices.");
		}
		
		this.mNumVertices = lineScanner.nextInt();
		
		// 3. Read number of triangles.

		if (!lineScanner.hasNextInt()) {
			lineScanner.close();
			sc.close();
			throw new Exception("Failed to read number of Triangles.");
		}

		this.mNumTriangles = lineScanner.nextInt();
	
		lineScanner.close(); // First line read.
	
		// Read Vertex data.

		for (int i = 0; i < this.mNumVertices; ++i) {
			
			// Read next Vertex.

			if (!sc.hasNextLine()) {
				lineScanner.close();
				sc.close();
				throw new Exception("Failed to read " + i + "th Vertex - no line to be read.");
			}
			
			lineScanner = new Scanner(sc.nextLine());
			
			// Read the id of the current Vertex:
			
			if (!lineScanner.hasNextInt()) {
				lineScanner.close();
				sc.close();
				throw new Exception("Failed to read id of " + i + "th Vertex.");
			}

			final int vid = lineScanner.nextInt();
			
			// Read the x-coordinate of the current Vertex:
			
			if (!lineScanner.hasNextDouble()) {
				lineScanner.close();
				sc.close();
				throw new Exception("Failed to read x-coordinate of Vertex " + vid);				
			}
			
			final double x = lineScanner.nextDouble();
			
			// Read the y-coordinate of the current Vertex:
			
			if (!lineScanner.hasNextDouble()) {
				lineScanner.close();
				sc.close();
				throw new Exception("Failed to read y-coordinate of Vertex " + vid);				
			}
			
			final double y = lineScanner.nextDouble();

			lineScanner.close(); // Finished reading line.
			
			// Construct Vertex.
			this.mVertices.put(vid, new Vertex(vid, x, y));
		}
		
		// Read Triangle data.
		
		for (int i = 0; i < this.mNumTriangles; ++i) {
			
			// Read the next Triangle.
			
			if (!sc.hasNextLine()) {
				lineScanner.close();
				sc.close();
				throw new Exception("Failed to read " + i + "th Triangle - no line to be read.");
			}
			
			lineScanner = new Scanner(sc.nextLine());

			// Read the id of the first Vertex of the current Triangle.
			
			if (!lineScanner.hasNextInt()) {
				lineScanner.close();
				sc.close();
				throw new Exception("Failed to read id of the first Vertex of " + i + "th Triangle.");
			}

			final int firstVertexId = lineScanner.nextInt();

			// Read the id of the second Vertex of the current Triangle.
			
			if (!lineScanner.hasNextInt()) {
				lineScanner.close();
				sc.close();
				throw new Exception("Failed to read id of the second Vertex of " + i + "th Triangle.");
			}

			final int secondVertexId = lineScanner.nextInt();

			// Read the id of the third Vertex of the current Triangle.
			
			if (!lineScanner.hasNextInt()) {
				lineScanner.close();
				sc.close();
				throw new Exception("Failed to read id of the third Vertex of " + i + "th Triangle.");
			}

			final int thirdVertexId = lineScanner.nextInt();

			lineScanner.close(); // Finished reading line.
			
			// Construct the ids of the form "low(e)high(e)" of the edges of the current Triangle
			// and construct and cache those edges in a hashmap if they do not exist, otherwise
			// retrieve their references.
			
			final String firstEdgeId = Integer.toString(Math.min(firstVertexId, secondVertexId)) + Integer.toString(Math.max(firstVertexId, secondVertexId));
			Edge firstEdge = null;
			if (!this.mEdges.containsKey(firstEdgeId)) {
				firstEdge = new Edge(firstEdgeId, firstVertexId, secondVertexId, null, null);
				this.mEdges.put(firstEdgeId, firstEdge);
			} else {
				firstEdge = this.mEdges.get(firstEdgeId);
			}

			final String secondEdgeId = Integer.toString(Math.min(secondVertexId, thirdVertexId)) + Integer.toString(Math.max(secondVertexId, thirdVertexId));
			Edge secondEdge = null;
			if (!this.mEdges.containsKey(secondEdgeId)) {
				secondEdge = new Edge(secondEdgeId, secondVertexId, thirdVertexId, null, null);
				this.mEdges.put(secondEdgeId, secondEdge);
			} else {
				secondEdge = this.mEdges.get(secondEdgeId);
			}
			
			final String thirdEdgeId = Integer.toString(Math.min(thirdVertexId, firstVertexId)) + Integer.toString(Math.max(thirdVertexId, firstVertexId));
			Edge thirdEdge = null;
			if (!this.mEdges.containsKey(thirdEdgeId)) {
				thirdEdge = new Edge(thirdEdgeId, thirdVertexId, firstVertexId, null, null);
				this.mEdges.put(thirdEdgeId, thirdEdge);
			} else {
				thirdEdge = this.mEdges.get(thirdEdgeId);
			}
			
			// Construct the current Triangle, assigned with a unique id.
			final Triangle t = new Triangle(this.mTid++, firstEdge, secondEdge, thirdEdge);
			
			// Update Triangle references in each of the constructed edges to include the current Triangle.
			// One of the references should be null, otherwise we have a non-manifold case.
			if (firstEdge.t1 != null) {
				if (firstEdge.t2 != null) {
					throw new IllegalStateException("Triangulation is non-manifold - Edge " + firstEdge.id + " is connected to >2 Triangles.");
				} else {
					firstEdge.t2 = t;
				}
			} else {
				firstEdge.t1 = t;
			}

			if (secondEdge.t1 != null) {
				if (secondEdge.t2 != null) {
					throw new IllegalStateException("Triangulation is non-manifold - Edge " + secondEdge.id + " is connected to >2 Triangles.");
				} else {
					secondEdge.t2 = t;
				}
			} else {
				secondEdge.t1 = t;
			}

			if (thirdEdge.t1 != null) {
				if (thirdEdge.t2 != null) {
					throw new IllegalStateException("Triangulation is non-manifold - Edge " + thirdEdge.id + " is connected to >2 Triangles.");
				} else {
					thirdEdge.t2 = t;
				}
			} else {
				thirdEdge.t1 = t;
			}
		}
	}	
}
