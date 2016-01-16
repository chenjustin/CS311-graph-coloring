package graphs;

public class Test {

	public static void main(String args[]){
		Graph g1 = new Graph(4);

		g1.addEdge(1, 2);
		g1.addEdge(2, 3);
		g1.addEdge(3, 4);
		g1.addEdge(4, 2);
		
		g1.colorable();
		System.out.println("\nTwo-colorable: " + g1.getColorable());
		
	}
}
