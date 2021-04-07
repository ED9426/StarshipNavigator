import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class EdgeDataReader implements EdgeDataReaderInterface {
	public List<Edge> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException{
		List<Edge> edges = new ArrayList<Edge>();
		String [] edgeNames=new String [45];
		// read in data
		try (BufferedReader br = new BufferedReader(inputFileReader)) {

			String line; // each line in the data set
			edgeNames=br.readLine().split(","); // skip the first line (titles) of the data set
			for (int j=1; j<edgeNames.length-1; j++) {
				edges.add(new Edge(edgeNames[j], null, null,null));
			}
			int lines=-1;
			while ((line = br.readLine()) != null) { // traverse each line
				String[] values = line.split(","); // split data by comma
				System.out.print(lines+"--");
				System.out.println(values.length-1);
				lines=lines+1;
				for (int i=1; i<values.length-1; i++) {
					if (!values[i].equals("")&&(lines!=26&&i!=44))
						edges.get(lines).addConnection(edges.get(i), Integer.parseInt(values[i]));
				}
				edges.get(lines).setType(values[values.length-1]);
			}

		}
		return edges;
	}
}
