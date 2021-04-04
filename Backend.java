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
        List<EdgeInterface> data = new ArrayList<>();
        EdgeDataReaderInterface cdr = new EdgeDataReader();
        try {
            data= cdr.readDataSet(args);
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        }
        for (int i=0; i<data.size();i++) {
            // TODO: add the date information into the graph;
            size++;
        }
    }


    @Override
    public boolean addEdge() {
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }
}
