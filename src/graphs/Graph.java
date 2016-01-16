package graphs;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;

public class Graph {

	private int vertices;
	private int edges;
	private ArrayList<LinkedList<Integer>> adjacencyList;
	private boolean twoColorable;
	private int invalidVertex;
	private int invalidSource;
	
	/*
	 * Constructor to initialize a graph.
	 * Creates an array of size numVertices.
	 */
	public Graph(int numVertices){
		edges = 0;
		adjacencyList = new ArrayList<LinkedList<Integer>>();
		vertices = numVertices;
		twoColorable = true;
		/*
		 * Invalid vertex is a vertex that is connected to another
		 * vertex of the same color. This will be used to print
		 * out the subgraph that makes the graph non-twoColorable.
		 */
		invalidVertex = -1;
		invalidSource = -1;
		
		/*
		 * The adjacency list is an ArrayList of LinkedLists.
		 * This initializes each element of the ArrayList with
		 * an empty LinkedList before we start reading in the
		 * edges of the graph input.
		 */
		for(int i = 0; i < numVertices; i++){
			adjacencyList.add(new LinkedList<Integer>());
		}
		
	}
	
	/*
	 * Creates an edge between two vertices v1 and v2.
	 * Updates the edge count for the graph class.
	 */
	public void addEdge(int v1, int v2){
		adjacencyList.get(v1-1).add(v2);
		adjacencyList.get(v2-1).add(v1);
		edges++;
	}
	
	/*
	 * Accessor methods
	 */
	public int getVertices(){
		return vertices;
	}
	
	public int getEdges(){
		return edges;
	}
	
	public boolean getColorable(){
		return twoColorable;
	}
	
	public boolean colorable(){
		Vertex[] visited = new Vertex[vertices];
		
		/*
		 * Initializes the array of "visited" vertices.
		 * 
		 * A Vertex object is created in the form (color, parent)
		 * 
		 * 0 indicates an undiscovered vertex.
		 * 1 indicates a BLUE vertex.
		 * 2 indicates a RED vertex.
		 * 
		 * For the parent, -1 indicates that there is no parent.
		 */
		for(int i = 0; i < visited.length; i++){
			visited[i] = new Vertex(0, -1);
		}
		
		for(int j = 0; j < visited.length; j++){
			if(visited[j].color() == 0){
				visited = DFS_visit(visited, -1 ,j);
			}
		}
		
		if(twoColorable == false){
			
			StringBuilder invalidCycle = new StringBuilder();
			/*
			 * Backtrack through the cycle by starting with
			 * the invalid vertex. Continue looking at each
			 * parent of the vertex until we find the beginning,
			 * then output all the traversed nodes.
			 */
			int backTracker = invalidSource;
			
			invalidCycle.append(invalidVertex+1 + " ");
			invalidCycle.append(backTracker+1 + " ");
			
			while(visited[backTracker].parent() != invalidVertex){
				invalidCycle.append((visited[backTracker].parent()+1) + " ");
				backTracker = visited[backTracker].parent();
			}
			
			invalidCycle.append(visited[backTracker].parent()+1);
			
			System.out.println(invalidCycle);
			
			return false;
		}
		else{
			
			/*
			 * Simply prints out each vertex and its color
			 * if the graph is indeed twoColorable.
			 */
			for(int m = 0; m < visited.length; m++){
				String nodeColor = "";
				if(visited[m].color() == 1){
					nodeColor = "BLUE";
				}
				else
					nodeColor = "RED";
				System.out.println((m+1) + ": " + nodeColor);
			}
			
			return true;
		}
	}
	
	/*
	 * Recursive method to perform a depth first search of
	 * the graph. 
	 * 
	 * Parameters:
	 * 		arr -> The array of visited vertices.
	 * 		from -> Parent of the vertex to be visited. -1 indicates
	 *     	 	    that there is no parent, and if that is the case
	 *     			then V is the source vertex.
	 *  	v -> Vertex to be visited
	 */
	public Vertex[] DFS_visit(Vertex[] arr, int from, int v){
		Vertex[] visited = arr;
		
		//adj is the list of adjacent vertices to vertex v
		LinkedList<Integer> adj = adjacencyList.get(v);
		
		//If this is the source vertex, we will color it BLUE
		if(from == -1){
			visited[v].setColor(1);;
		}
		else{
			visited[v].setParent(from);
			if(visited[from].color() == 1)
				visited[v].setColor(2);
			else
				visited[v].setColor(1);
		}
		
		for(Integer i : adj){
			if(visited[i-1].color() == 0)
				DFS_visit(visited, v, i-1);
			else{
				/*
				 * If vertex V has an adjacent such that
				 * the adjacent vertex has already been explored,
				 * has the same color as V, AND it's parent is not 
				 * V, then there must be an odd cycle in the graph.
				 */
				if((visited[i-1].color() == visited[v].color()) && visited[i-1].parent() != v){
					twoColorable = false;
					/*
					 * Label this vertex as invalid. This will only
					 * be done once, but that does not necessarily
					 * mean there is only one invalid cycle in the graph.
					 */
					if(invalidVertex == -1){
						invalidVertex = i-1;
						invalidSource = v;
					}
				}
			}
		}
		
		return visited;
	}
	
	/*
	 * Testing methods
	 */
	public String toString(){
		String str = "";
		int i = 1;
		for(LinkedList<Integer> adjLst : adjacencyList){
			str = str + i + ". " + Arrays.toString(adjLst.toArray()) + "\n";
			i++;
		}
		str = str + "\nEdges: " + edges + "\nVertices: " + vertices;
		
		return str;
	}
	
	public void printGraph(){
		int i = 1;
		for(LinkedList<Integer> adjLst : adjacencyList){
			System.out.println(i + ". " + Arrays.toString(adjLst.toArray()));
			i++;
		}
		System.out.println("Edges: " + edges);
		System.out.println("Vertices: " + vertices);
	}

}
