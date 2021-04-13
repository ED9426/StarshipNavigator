// --== VertexDataReaderInterface ==--
// Name: Austin Cohen 
// Email: aacohen3@wisc.edu
// Team: GE red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * This method is the interface that the VertexDataReader will implement
 * @author austincohen
 *
 */
public interface VertexDataReaderInterface {
	/**
	 * This method will read in 
	 * @param inputFileReader
	 * @return: A List<Vertex> of the vertecies which will be returned 
	 * @throws FileNotFoundException: when no file is found
	 * @throws IOException: runtime exception
	 * @throws DataFormatException: if the data is not in the correct format
	 */
	public List<Vertex> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException;

}
