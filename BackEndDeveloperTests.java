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
import java.util.List;
import static org.junit.jupiter.api.Assertions.fail;

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













}
