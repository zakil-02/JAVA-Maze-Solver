package graph;

public interface Distance {
	/**
	 * Returns the weight of the arc between two vertices, but in our application the distance will be always 1 if the two vertices are really neighbors.
	 *
	 * @param departure the vertex from which to depart
	 * @param arrival the vertex at which to arrive
	 * @return the distance between the specified departure and arrival vertices
	 */
	public int getDistance(Vertex departure,Vertex arrival);
}
