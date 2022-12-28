package graph;
import java.util.*;
public interface MinDistance {
	//Get mindistance of a vertex
	public int getFor(Vertex v);
	//Get the whole HashMap minDistance
   	public HashMap<Vertex,Integer> getAll();
   	//Update a mindistance of a vertex
	public void updateDistance(Vertex v, int d);

}
