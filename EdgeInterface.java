import java.util.List;
public interface EdgeInterface {
	public String getType();
	public String getName();
	public List<Integer> getWeights();
	public List<Edge> getConnections();
	public boolean addConnection(Edge name, int weight);
	public boolean removeConnection(Edge name);
}
