// --== CS400 File Header Information ==--
// Name: Austin Cohen 
// Email: aacohen3@wisc.edu
// Team: GE red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;
import static org.junit.jupiter.api.Assertions.*;

public class DataWranglerTests {
	@Test 
	public void testRegular() {
		StringReader fr=new StringReader(",a,b,c,d,DIR\n"
				+ "a,,2,,,I \n"+"b,2,,,,I\n"+"c,,,,1,I\n"+"d,,,1,,I\n");
		VertexDataReader edr=new VertexDataReader();
		try {
			List<Vertex>edgeList=edr.readDataSet(fr);
			String correctAnswer="a\na---b\nb\nb---a\nc\nc---d\nd\nd---c\n";
			String answer="";
			for(int i=0;i<edgeList.size();i++) {
				answer+=(edgeList.get(i).getName()+"\n");
				for(int j=0; j<edgeList.get(i).connections.size();j++) {
					answer+=(edgeList.get(i).getName()+"---"+edgeList.get(i).connections.get(j).getName()+"\n");
				}
			}
			if (!correctAnswer.equals(answer))
				fail("return wrong value");
		} catch (IOException e) {
			fail("IOException");
			e.printStackTrace();
		} catch (DataFormatException e) {
			fail("DataFormatException");
			e.printStackTrace();
		}

	}
	@Test
	public void testEmpty() {
		StringReader fr=new StringReader(",a,b,c,d,DIR\n"
				+ "a,,,,,I \n"+"b,,,,,I\n"+"c,,,,,I\n"+"d,,,,,I\n");
		VertexDataReader edr=new VertexDataReader();
		try {
			List<Vertex>edgeList=edr.readDataSet(fr);
			String correctAnswer="";
			String answer="";
			for(int i=0;i<edgeList.size();i++) {
				for(int j=0; j<edgeList.get(i).connections.size();j++) {
					answer+=(edgeList.get(i).getName()+"---"+edgeList.get(i).connections.get(j).getName()+"\n");
				}
			}
			if (!correctAnswer.equals(answer))
				fail("return wrong value");
		} catch (IOException e) {
			fail("IOException");
			e.printStackTrace();
		} catch (DataFormatException e) {
			fail("DataFormatException");
			e.printStackTrace();
		}

	}
	@Test
	public void checkWeights() {
		StringReader fr=new StringReader(",a,b,c,d,DIR\n"
				+ "a,,2,,,I \n"+"b,2,,,,I\n"+"c,,,,1,I\n"+"d,,,1,,I\n");
		VertexDataReader edr=new VertexDataReader();
		try {
			List<Vertex>edgeList=edr.readDataSet(fr);
			if (edgeList.get(0).getWeights().get(0)!=2)
				fail("incorrect weight for a-b connection");
			if (edgeList.get(1).getWeights().get(0)!=2)
				fail("incorrect weight for b-a connection");
			if (edgeList.get(2).getWeights().get(0)!=1)
				fail("incorrect weight for c-d connection");
			if (edgeList.get(3).getWeights().get(0)!=1)
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
