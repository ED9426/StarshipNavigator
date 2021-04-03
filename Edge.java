import java.util.List;

public class Edge implements EdgeInterface{
	private String name;
	private String type;
	List<Edge> connections;
	List<Integer> weights;
	
	public Edge(String name, String type, List<Edge> connections, List<Integer> weights) {
		this.name=name;
		this.type=type;
		this.connections=connections;
		this.weights=weights;
	}
	
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public List<Integer> getWeights(){
		return weights;
	}
	public List<Edge> getConnections(){
		return connections;
	}
	public boolean addConnection(Edge name, int weight) {
		if (connections.contains(name))
			return false;
		connections.add(name);
		weights.add(weight);
		return true;
	}
	public boolean removeConnection(Edge name) {
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
