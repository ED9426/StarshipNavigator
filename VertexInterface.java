// --== VertexInterface ==--
// Name: Austin Cohen 
// Email: aacohen3@wisc.edu
// Team: GE red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.util.List;
/**
 * The interface which will be implemented by the vertex class 
 * @author austincohen
 *
 */
public interface VertexInterface {
	/**
	 * This method returns the type of the vertex
	 * @return: the string type of the vertex
	 */
		public String getType();
		/**
		 * This method sets the type of the vertex
		 * @param: type of a string which represents the new type the vertex is 
		 */
		public void setType(String type);
		/**
		 * This method returns the name of the vertex
		 * @return: the string name of the vertex
		 */
		public String getName();
		/**
		 * This method returns the weights of the vertex's connections
		 * @return: a List of Integers of the weights of the vertex's connections
		 */
		public List<Integer> getWeights();
		/**
		 * This method returns a list of the verticies the vertex is connected to 
		 * @return: List<Vertex> of the vertex's connections
		 */
		public List<Vertex> getConnections();
		/**
		 * This method adds a connection to the vertex and returns boolean if sucessfully added 
		 * or not 
		 * @param: name is the vertex that is being connected
		 * @param: weight is the integer weight of the connection
		 * @return: boolean true if connection added sucessfully false otherwise
		 */
		public boolean addConnection(Vertex name, int weight);
		/**
		 * this method removes a connection from the object and returns a boolean 
		 * if done sucessfully or not 
		 * @param: vertex name is the vertex which the user hopes to remove from the vertex 
		 */
		public boolean removeConnection(Vertex name);
	}

