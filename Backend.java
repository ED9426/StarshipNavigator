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
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Backend is used to read the data and then parse the result to the front end.
 * @author Geng Hui
 */
public class Backend implements BackendInterface{
    private Graph<String> map;
    private int size=0;
    /**
     * The backend constructor method
     * @param args: the reader to read in the file to be added
     * to the Map graph
     */
    public Backend(Reader args) {
        List<Edge> data = new ArrayList<>();
        EdgeDataReaderInterface cdr = new EdgeDataReader();
        try {
            data= cdr.readDataSet(args);
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        }
        for (int i=0; i<data.size();i++) {
            // TODO: add the date information into the graph;
            map.insertVertex(data.get(i).getName());
            size++;
        }
        for (int i=0; i<data.size();i++) {
            for (int j= 0; j<data.get(i).connections.size(); j++) {
                map.insertEdge(data.get(i).getName(),data.get(i).connections.get(j).getName(),data.get(i).weights.get(j));
                map.insertEdge(data.get(i).connections.get(j).getName(),data.get(i).getName(),data.get(i).weights.get(j));
            }
        }
    }



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

    @Override
    public Edge addDorm(String a, List<String> neighbors, List<Integer> weights) {
        return null;
    }

    @Override
    public Edge removeDorm(String a) {
        if (map.containsVertex(a)) {
            map.removeVertex(a);
        }
        return null;
    }

    @Override
    public List<> findCheapestDelivery(String a, String b) {
        Graph.Path path = map.dijkstrasShortestPath(a,b);
        return null;
    }

    @Override
    public List<Edge> findFastestDelivery(String a, String b) {
        return null;
    }
}
