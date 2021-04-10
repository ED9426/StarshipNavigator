import java.util.List;
import java.util.ArrayList;

public class Vertex implements VertexInterface{
	private String name;
	private String type;
	List<Vertex> connections=new ArrayList<Vertex>();
	List<Integer> weights=new ArrayList<Integer>();

	public Vertex(String name, String type, List<Vertex> connections, List<Integer> weights) {
		this.name=name;
		this.type=type;
		if (connections!=null)
		this.connections=connections;
		if (weights!=null)
		this.weights=weights;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type=type;
	}
	public String getName() {
		return name;
	}
	public List<Integer> getWeights(){
		return weights;
	}
	public List<Vertex> getConnections(){
		return connections;
	}
	public boolean addConnection(Vertex name, int weight) {
		if (connections!=null) {
			if (connections.contains(name))
				return false;
		}
		connections.add(name);
		weights.add(weight);
		return true;
	}
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
