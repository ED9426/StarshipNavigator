import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.List;
import java.util.zip.DataFormatException;

public class VertexReaderTester {
	public static void main(String args[]) {
		try {
			FileReader fr=new FileReader("/Users/austincohen/Documents/Wisconsin 1/CS 400/P03 Graphs/src/Starship Delivery Times.csv");
			VertexDataReader edr=new VertexDataReader();
				try {
					List<Vertex>edgeList=edr.readDataSet(fr);
					for(int i=0;i<edgeList.size();i++) {
						System.out.print(edgeList.get(i).getName()+"---");
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (DataFormatException e) {
					e.printStackTrace();
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
