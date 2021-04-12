import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.List;
import java.util.zip.DataFormatException;

public class VertexReaderTester {
	public static void main(String args[]) {
		StringReader fr=new StringReader(",a,b,c,d,DIR\n"
				+ "a,,2,,,I \n"+"b,2,,,,I\n"+"c,,,,1,I\n"+"d,,,1,,I\n");
		VertexDataReader edr=new VertexDataReader();
		try {
			List<Vertex>edgeList=edr.readDataSet(fr);
			if (edgeList.get(0).getWeights().get(0)==2)
				fail("incorrect weight for a-b connection");
			if (edgeList.get(1).getWeights().get(0)==2)
				fail("incorrect weight for b-a connection");
			if (edgeList.get(2).getWeights().get(0)==1)
				fail("incorrect weight for c-d connection");
			if (edgeList.get(3).getWeights().get(0)==1)
				fail("incorrect weight for d-c connection");
		} catch (IOException e) {
			fail("IOException");
			e.printStackTrace();
		} catch (DataFormatException e) {
			fail("DataFormatException");
			e.printStackTrace();
		}
	}
}
