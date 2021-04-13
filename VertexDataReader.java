// --== VertexDataReader ==--
// Name: Austin Cohen 
// Email: aacohen3@wisc.edu
// Team: GE red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * This class uses a reader to read information from a csv file 
 * into vertex nodes which will be processed into the graph class. 
 * @author austincohen
 *
 */
public class VertexDataReader implements VertexDataReaderInterface {
	/**
	 * this method takes a reader as input and turns 
	 * the file into verticies that the backend can use
	 * @param: inputFilerReader of type reader is the reader that is used 
	 * to read the file
	 * @return: List<Vertex> is the list of verticies that is returned by 
	 * the data reader
	 */
	public List<Vertex> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException{
		List<Vertex> edges = new ArrayList<Vertex>();
		String [] edgeNames=new String [45];
		// read in data
		try (BufferedReader br = new BufferedReader(inputFileReader)) {

			String line; // each line in the data set
			edgeNames=br.readLine().split(","); // skip the first line (titles) of the data set
			for (int j=1; j<edgeNames.length-1; j++) {
				edges.add(new Vertex(edgeNames[j], null, null,null));
			}
			int lines=-1;
			while ((line = br.readLine()) != null) { // traverse each line
				String[] values = line.split(","); // split data by comma
				lines=lines+1;
				for (int i=1; i<values.length-1; i++) {
					if (!values[i].equals("")) {//&&(lines!=26&&i!=44))
						edges.get(lines).addConnection(edges.get(i-1), Integer.parseInt(values[i]));
					}
				}
				edges.get(lines).setType(values[values.length-1]);
			}

		}
		return edges;
	}
}

