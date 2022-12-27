package graph;
import java.util.ArrayList;

public interface Graph {
	public ArrayList<Vertex> getSuccessors(Vertex s);
	//public int getDistance(Vertex departure,Vertex arrival) ;
	public ArrayList<Vertex> getAllVertexes();
	
	

}
