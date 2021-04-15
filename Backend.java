// --== CS400 File Header Information ==--
// Name: HUI GENG
// Email: hgeng7@wisc.edu
// Team: GE red
// Role: Backend developer
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.io.IOException;
import java.io.Reader;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;

/**
 * Backend is used to read the data and then parse the result to the front end.
 * @author Geng Hui
 */
public class Backend implements BackendInterface{
    private Graph<String> map = new Graph();
    private int size=0;

    /**
     * The backend constructor method
     * @param args: the reader to read in the file to be added
     * to the Map graph
     */
    public Backend(Reader args) {
        List<Vertex> data = new ArrayList<>();
        VertexDataReaderInterface cdr = new VertexDataReader();
        try {
            data= cdr.readDataSet(args);
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        }
        for (int i=0; i<data.size();i++) {
            map.insertVertex(data.get(i).getName());
            size++;
        }
        for (int i=0; i<data.size();i++) {
            for (int j= 0; j<data.get(i).connections.size(); j++) {
                if (!data.get(i).connections.get(j).getName().equals(data.get(i).getName())) {  // avoid the self circling edge
                    map.insertEdge(data.get(i).getName(),data.get(i).connections.get(j).getName(),data.get(i).weights.get(j));
                    map.insertEdge(data.get(i).connections.get(j).getName(),data.get(i).getName(),data.get(i).weights.get(j));
                }
            }
        }
    }

    /**
     * @return return the number of the vertex in the backend
     */
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isDorm(String a) {
        if (map.containsVertex(a)) {
            return true;
        }
        return false;
    }

    /**
     * @param a the dorm name for adding
     * @param neighbors the neighbors name of the adding dorm
     * @param weights the weight of the edge of the adding dorm and its neighbers
     * @return
     */
    @Override
    public boolean addDorm(String a, List<String> neighbors, List<Integer> weights) {
        if (map.containsVertex(a)) {
            return false;
        } else {
            map.insertVertex(a);
            for (int i = 0; i < neighbors.size(); i++) {
                map.insertEdge(a,neighbors.get(i),weights.get(i));
                map.insertEdge(neighbors.get(i),a,weights.get(i));
            }
            return true;
        }
    }

    /**
     * @param a remove the dorm named "a" and its connections if it is inside the graph
     * @return true if a is removed
     */
    @Override
    public boolean removeDorm(String a) {
        return map.removeVertex(a);

    }

    /**
     * @param a,b a and b is the name of the two sides of the edge
     * @return the the delivery route path with the minimal sum of the weights.
     */
    @Override
    public List<String> findCheapestDelivery(String a, String b) {
        List<String> path = map.shortestPath(a,b);
        return path;
    }

    /**
     * @param a,b a and b is the name of the two sides of the edge
     * @return the the delivery route path with the minimal number of the nodes along the path.
     */
    @Override
    public List<String> findFastestDelivery(String a, String b) {
        List<String> path = map.BFSPath(a,b);
        return path;
    }

    /**
     * @param path the path that want to calculate the time
     * @return the sum of the weights of the path
     */
    @Override
    public int expectedTime(List<String> path) {
        int time = IntStream.range(0, path.size() - 1)
                .map(i -> map.getWeight(path.get(i), path.get(i + 1)))
                .sum();
        return time;
    }
}
