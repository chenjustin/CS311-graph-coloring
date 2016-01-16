package graphs;
import java.io.*;

public class Main {

	private String fullPath;
	
	public Main(String s){
		fullPath = s;
		Graph g = readGraphInput();
	}
	
	private Graph readGraphInput(){
		Graph g = null;
		
		try {
			File d = new File(".");
			BufferedReader reader = new BufferedReader(new FileReader(d.getCanonicalPath() + fullPath));
			
			/*
			 * Line 1 of the input graphs contains the number of
			 * vertices. We initialize the graph (ArrayList) with 
			 * this value.
			 */
			g = new Graph(Integer.parseInt(reader.readLine()));
			
			String line = null;
			while((line = reader.readLine()) != null){
				int commaLocation = line.indexOf(" ");
				String s1 = line.substring(0, commaLocation);
				String s2 = line.substring(commaLocation+1);
				g.addEdge(Integer.parseInt(s1), Integer.parseInt(s2));
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g.colorable();
		
		System.out.println("\nTwo-colorable: " + g.getColorable());
		
		return g;
		
	}
	
	public static void main(String args[]){
		
		String path;
		String file;
		
		path = "/res/";
		
		/*
		 * Input the following in the command-line arguments
		 * to generate the desired graph:
		 * 1 --> smallgraph.txt
		 * 2 --> largegraph1.txt
		 * 3 --> largegraph2.txt
		 */
		if(args[0].equals("2")){
			file = "largegraph1";
		}
		else if(args[0].equals("3")){
			file = "largegraph2";
		}
		else{
			file = "smallgraph";
		}
		
		new Main(path + file);
	}
}
