import java.util.List;
public interface VertexInterface {
		public String getType();
		public void setType(String type);
		public String getName();
		public List<Integer> getWeights();
		public List<Vertex> getConnections();
		public boolean addConnection(Vertex name, int weight);
		public boolean removeConnection(Vertex name);
	}

