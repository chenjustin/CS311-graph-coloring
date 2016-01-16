package graphs;

/*
 * Vertex class
 * A vertex object contains an int value v
 * and its parent p.
 */
public class Vertex {

	private int c;
	private int p;
	
	public Vertex(int color, int parent) {
		c = color;
		p = parent;
	}
	
	/*
	 * Accessor methods
	 */
	public int parent(){
		return p;
	}
	public int color(){
		return c;
	}
	
	/*
	 * Mutator methods
	 */
	public void setParent(int x){
		p = x;
	}
	public void setColor(int y){
		c = y;
	}
}
