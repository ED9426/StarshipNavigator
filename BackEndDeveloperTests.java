// --== CS400 File Header Information ==--
// Name: HUI GENG
// Email: hgeng7@wisc.edu
// Team: GE red
// Role: Backend developer
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BackEndDeveloperTests {

    /**
     * @throws FileNotFoundException throw an exception if the file doesn't exist.
     * Test if the isDorm function works.
     */
    @Test
    public void testisDorm() throws FileNotFoundException {
        Backend b=new Backend(new FileReader("./Starship.csv"));
        if (b.isDorm("something")) {
            fail("Doesn't exist");
        }
        if (!b.isDorm("Adams")) {
            fail("Adams should be a dorm name");
        }
    }

    /**
     * @throws FileNotFoundException throw an exception if the file doesn't exist.
     * Test if the addDorm function works.
     */
    @Test
    public void testaddDorm() throws FileNotFoundException {
        Backend b = new Backend(new FileReader("./Starship.csv"));
        List<String> neighbors = new LinkedList<String>();
        List<Integer> weights = new LinkedList<>();
        neighbors.add("Adams");
        weights.add(20);
        b.addDorm("Sheboygan", neighbors, weights);
        if (!b.isDorm("Sheboygan")) {
            fail("Doesn't exist");
        }
    }

    /**
     * @throws FileNotFoundException throw an exception if the file doesn't exist.
     * Test if the RemoveDorm function works.
     */
    @Test
    public void testRemoveDorm() throws FileNotFoundException {
        Backend b = new Backend(new FileReader("./Starship.csv"));
        b.removeDorm("Adams");
        if (b.isDorm("Adams")) {
            fail("Doesn't exist");
        }
    }

    /**
     * @throws FileNotFoundException throw an exception if the file doesn't exist.
     * Test if the Cheapest function works.
     */
    @Test
    public void testCheapest() throws FileNotFoundException {
        Backend b = new Backend(new FileReader("./Starship.csv"));
        List<String> neighbors = new LinkedList<String>();
        List<Integer> weights = new LinkedList<>();
        neighbors.add("Adams");
        weights.add(20);
        b.addDorm("Sheboygan", neighbors, weights);
        assertTrue(b.findCheapestDelivery("Adams", "Sheboygan").size() == 2);

        assertTrue(b.findCheapestDelivery("Cole", "Bradley").size() == 3);
    }

    /**
     * @throws FileNotFoundException throw an exception if the file doesn't exist.
     * Test if the Fastest function works.
     */
    @Test
    public void testFastest() throws FileNotFoundException {
        Backend b = new Backend(new FileReader("./Starship.csv"));
        List<String> newpath = new LinkedList<>();
        newpath.add("Cole");
        newpath.add("Elm & Kronshage");
        newpath.add("Bradley");
        assertEquals(b.findFastestDelivery("Cole", "Bradley"), newpath);
    }

    /**
     * @throws FileNotFoundException throw an exception if the file doesn't exist.
     * Test if the expectedTime function works.
     */
    @Test
    public void testexpectedTime() throws FileNotFoundException {
        Backend b = new Backend(new FileReader("./Starship.csv"));
        List<String> newpath = new LinkedList<>();
        newpath.add("Cole");
        newpath.add("Elm & Kronshage");
        newpath.add("Bradley");
        assertEquals(b.expectedTime(b.findFastestDelivery("Cole", "Bradley")), 4);
    }
}
