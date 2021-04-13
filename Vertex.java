// --== Vertex ==--
// Name: Austin Cohen 
// Email: aacohen3@wisc.edu
// Team: GE red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.util.List;
import java.util.ArrayList;
/**
 * This class is used to act as a vertex which has a 
 * type, name, and list of weighted connections
 * @author austincohen
 *
 */
public class Vertex implements VertexInterface{
	private String name;
	private String type;
	List<Vertex> connections=new ArrayList<Vertex>();
	List<Integer> weights=new ArrayList<Integer>();

	/**
	 * This is the constructor method for vertex 
	 * @param name: the name of the vertex
	 * @param type: the type of vertex either "D": dormatory ,"I": intersection, or "R":restrurant
	 * @param connections: the list of vertecies that this vertex is connected to 
	 * @param weights: the weights of the connections 
	 */
	public Vertex(String name, String type, List<Vertex> connections, List<Integer> weights) {
		this.name=name;
		this.type=type;
		if (connections!=null)
		this.connections=connections;
		if (weights!=null)
		this.weights=weights;
	}
/**
 * This method returns the type of the vertex
 * @return: the string type of the vertex
 */
	public String getType() {
		return type;
	}
	/**
	 * This method sets the type of the vertex
	 * @param: type of a string which represents the new type the vertex is 
	 */
	public void setType(String type) {
		this.type=type;
	}
	/**
	 * This method returns the name of the vertex
	 * @return: the string name of the vertex
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method returns the weights of the vertex's connections
	 * @return: a List of Integers of the weights of the vertex's connections
	 */
	public List<Integer> getWeights(){
		return weights;
	}
	/**
	 * This method returns a list of the verticies the vertex is connected to 
	 * @return: List<Vertex> of the vertex's connections
	 */
	public List<Vertex> getConnections(){
		return connections;
	}
	/**
	 * This method adds a connection to the vertex and returns boolean if sucessfully added 
	 * or not 
	 * @param: name is the vertex that is being connected
	 * @param: weight is the integer weight of the connection
	 * @return: boolean true if connection added sucessfully false otherwise
	 */
	public boolean addConnection(Vertex name, int weight) {
		if (connections!=null) {
			if (connections.contains(name))
				return false;
		}
		connections.add(name);
		weights.add(weight);
		return true;
	}
	
	/**
	 * this method removes a connection from the object and returns a boolean 
	 * if done sucessfully or not 
	 * @param: vertex name is the vertex which the user hopes to remove from the vertex 
	 */
	public boolean removeConnection(Vertex name) {
		if (!connections.contains(name))
			return false;
		for (int i=0; i<connections.size();i++) {
			if (connections.get(i).equals(name)) {
				connections.remove(i);
				weights.remove(i);
			}
		}
		return true;
	}
}
