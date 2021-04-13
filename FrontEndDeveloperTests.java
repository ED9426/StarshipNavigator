//////////////// TestFrontend //////////////////////////
//
// Title:   This programs tests the Frontend class by applying different scenarios and seeing if the
//          frontend returns the expected output
//
// Course:   CS 400 Spring 2020
//
// Name: Gahan Sudhir
// Email: gsudhir@wisc.edu
// Team: GE Red
// Role: Frontend Developer
// TA: Surabhi
// Lecturer: Florian Heimerl
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE 
//
///////////////////////////////////////////////////////////////////////////////

import java.util.List;
import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;

public class FrontEndDeveloperTests {
  public void initEach() throws FileNotFoundException {
    Frontend test = new Frontend();
    test.run(new Backend(new FileReader("./starship.csv")));
}

/**
 * Test enter 'X' ToExit
 */
@Test
public void test1() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;

    try {
        ByteArrayInputStream exit = new ByteArrayInputStream("x".getBytes());
        System.setIn(exit);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Frontend test = new Frontend();
        test.run(new Backend(new FileReader("./starship.csv")));
        System.setOut(standardOut);
        System.setIn(standardIn);
        assertTrue(test != null);
    } catch (Exception e) {
        System.setOut(standardOut);
        System.setIn(standardIn);
        e.printStackTrace();
    }
}

/**
 * Test Frontend addition of restaurant
 */
@Test
public void test2() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
        String input = "A" +  System.lineSeparator() + "Panera Bread" + System.lineSeparator() + "x" + System.lineSeparator() + "S" + System.lineSeparator() + "Panera Bread";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        initEach();
        System.setOut(standardOut);
        System.setIn(standardIn);
        String Output = outputStream.toString();
        assertTrue(Output.contains("is present in the system"));
    } catch (Exception e) {
        System.setOut(standardOut);
        System.setIn(standardIn);
        e.printStackTrace();
    }
}

/**
 * Test Frontend Expected Time mode
 */
@Test
public void test3() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
        String input = "E" +  System.lineSeparator() + "Adams Starbucks" + System.lineSeparator();
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        initEach();
        System.setOut(standardOut);
        System.setIn(standardIn);
        String Output = outputStream.toString();
        assertTrue(Output.contains("will take 36 minutes") && Output.contains("Starbucks, ") && Output.contains("Adams."));
    } catch (Exception e) {
        System.setOut(standardOut);
        System.setIn(standardIn);
        e.printStackTrace();
    }
}

/**
 * Test Frontend addCity Mode
 */
@Test
public void test4() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      String input = "P" +  System.lineSeparator() + "Adams Starbucks" + System.lineSeparator();
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outputStream));
      initEach();
      System.setOut(standardOut);
      System.setIn(standardIn);
      String Output = outputStream.toString();
      assertTrue(Output.contains("will take 18 minutes") && Output.contains("Starbucks, Park & Observatory") && Output.contains("Adams."));
    } catch (Exception e) {
        System.setOut(standardOut);
        System.setIn(standardIn);
        e.printStackTrace();
    }
}

/**
 * Test Frontend initialized return 3 cities.
 */
@Test
public void test5() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      String input = "A" +  System.lineSeparator() + "Panera Bread" + System.lineSeparator() + "x" + System.lineSeparator() + "R" + System.lineSeparator() + "Panera Bread" + System.lineSeparator() + "x" + System.lineSeparator();
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outputStream));
      initEach();
      System.setOut(standardOut);
      System.setIn(standardIn);
      String Output = outputStream.toString();
      assertTrue(!Output.contains("Panera Bread"));
    } catch (Exception e) {
        System.setOut(standardOut);
        System.setIn(standardIn);
        e.printStackTrace();
    }
}
}
