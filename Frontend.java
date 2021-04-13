// --== CS400 File Header Information ==--
// Name: Gahan Sudhir
// Email: gsudhir@wisc.edu
// Team: GE red
// Role: Frontend developer
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 * Provides an interface for the user to interact with the program
 * @author gahan
 */
public class Frontend implements FrontendInterface {
  private Scanner sc;
  private Backend backend;
  String[] neighborslist = {"Adams", "Barnard", "Bradley", "Chadbourne", "Cole", "Davis", "Dejope",
      "Humphrey", "Jorns", "Kronshage", "Leopold", "Merit", "Ogg", "Phillips", "Sellery",
      "Slichter", "Smith", "Sullivan", "Tripp", "Waters", "Witte", "Carson's Market",
      "Four Lakes Market", "Gordon Avenue Market", "Liz's Market", "Rheta's Market", "Starbucks",
      "Park & University", "Park & Johnson", "Park & Dayton", "E Campus Mall & Johnson",
      "Lake & Johnson", "Lake & Dayton", "Park & Observatory", "Observatory & Charter",
      "Charter & University", "Charter & Johnson", "Observatory & Babcock", "Babcock & Tripp",
      "Babcock & Kronshage", "Observatory & Elm", "Elm & Kronshage", "Observatory & Willow",
      "Babcock & University"};
  ArrayList<String> neighbors = new ArrayList<String>();
  ArrayList<String> dormitories = new ArrayList<String>();
  ArrayList<String> restaurants = new ArrayList<String>();
  String[] dorms = {"Adams", "Barnard", "Bradley", "Chadbourne", "Cole", "Davis", "Dejope",
      "Humphrey", "Jorns", "Kronshage", "Leopold", "Merit", "Ogg", "Phillips", "Sellery",
      "Slichter", "Smith", "Sullivan", "Tripp", "Waters", "Witte"};
  String[] rests = {"Carson's Market", "Four Lakes Market", "Gordon Avenue Market", "Liz's Market",
      "Rheta's Market", "Starbucks"};
  boolean[] neighborInd;
  
  /**
   * intializes the frontend to be run
   */
  public Frontend() {
    this.sc = new Scanner(System.in);
    for (int i = 0 ; i < dorms.length; i++) {
      dormitories.add(dorms[i]);
    }
    for (int i = 0 ; i < rests.length; i++) {
      restaurants.add(rests[i]);
    }
    for (int i = 0 ; i < neighborslist.length; i++) {
      neighbors.add(neighborslist[i]);
    }
    neighborInd = new boolean[neighbors.size()];
  }

  public static void main(String[] args) throws FileNotFoundException {
    Backend backend;
    backend = new Backend(new FileReader(
        "./starship.csv"));
    Frontend ft = new Frontend();
    ft.run(backend);
  }

  /**
   * This function is used to start the frontend
   * @param backend the backend used for storing and manipulating the city data.
   */
  @Override
  public void run(Backend backend) {
    if (backend != null) {
      this.backend = backend;
      runBaseMode();
      System.out.println("Thanks for using StarshipOrderingSystem!");
    } else {
      System.out.println("No Backend found!");
    }
  }

  /**
   * Basemode displays all the restaurant / dorms that can be ordered to/from
   * Entering "S" runs the search mode
   * Entering "R" runs the remove location mode
   * Entering "A" runs the add restaurant mode
   * Entering "E" runs the expected time mode
   * Entering "P" runs the fastest path mode
   * Entering "x" quits the program
   */
  public void runBaseMode() {
    String userInput = "";
    System.out.println("Welcome to the Starship Ordering Service!");
    while (!userInput.equals("x")) {
      System.out.println("Here are the restaurants you can order from: ");
      for (int i = 0; i < restaurants.size(); i++) {
        if (i < restaurants.size() - 1)
          System.out.print(restaurants.get(i) + ", ");
        else
          System.out.println(restaurants.get(i));
      }
      System.out.println("Enter \"S\" to enter search location mode.");
      System.out.println("Enter \"R\" to enter remove unwanted location mode.");
      System.out.println("Enter \"A\" to enter add restaurant mode.");
      System.out.println("Enter \"E\" to enter expected time mode (cheapest option)");
      System.out.println("Enter \"P\" to enter fastest path mode (most expensive option)");
      System.out.println("Enter \"x\" to quit.");
      userInput = sc.next();
      if (userInput.equals("S") || userInput.equals("R") || userInput.equals("A")
          || userInput.equals("E") || userInput.equals("P") || userInput.equals("x")) {
        if (userInput.equals("S")) {
          runSearchMode();
        } else if (userInput.equals("R")) {
          runRemoveMode();
        } else if (userInput.equals("A")) {
          runAddMode();
        } else if (userInput.equals("E")) {
          runExpectedTimeMode();
        } else if (userInput.equals("P")) {
          runFastestPathMode();
        } else if (userInput.equals("x")) {
          break;
        }
      }
      else {
        System.out.println("Invalid Input!");
      }
    }
  }

  /**
   * This method allows the user to search for existing dorms and restaurants to order to/from
   */
  public void runSearchMode() {
    String userInput = "";
    System.out.println(
        "Welcome to search location mode! Please enter the location you want to check is present. Enter \"x\" to return to base mode.");
    userInput = sc.next();
    while (!userInput.equals("x")) {
      String location = userInput;
      userInput = sc.nextLine();
      if (userInput != "") {
        location += userInput;
      }
      if (restaurants.contains(location)) {
        System.out.println(
            "Restaurant " + location + " is present in the system and can be ordered from.");
      } else if (dormitories.contains(location)) {
        System.out
            .println("Dormitory " + location + " is present in the system and can be ordered to.");
      } else {
        System.out
            .println(location + " is not present in the system and cannot be ordered to/from.");
      }
      System.out.println(
          "Please enter the location you want to check is present. Enter \"x\" to return to base mode.");
      userInput = sc.next();
    }
  }

  /**
   * This method allows the user to delete any unwanted locations
   */
  public void runRemoveMode() {
    String userInput = "";
    System.out.println("Welcome to remove location mode!");
    System.out.println(
        "Enter the location you would like to remove. Enter \"x\" to return to base mode.");
    userInput = sc.next();
    while (!userInput.equals("x")) {
      String location = userInput;
      userInput = sc.nextLine();
      if (userInput != "") {
        location += userInput;
      }
      if (!dormitories.contains(location) && !restaurants.contains(location)) {
        System.out.println(location + " cannot be found.");
      } else if (this.backend.removeDorm(location)) {
        System.out.println(location + " was successfully removed.");
        dormitories.remove(location);
        restaurants.remove(location);
        neighbors.remove(location);
      } else {
        System.out.println(location + "could not be removed.");
      }
      System.out.println(
          "Enter the location you would like to remove. Enter \"x\" to return to base mode.");
      userInput = sc.next();
    }
  }

  /**
   * This method allows the user to add any restaurants he wants to order from
   */
  public void runAddMode() {
    String userInput = "";
    System.out.println("Welcome to add location mode!");
    System.out
        .println("Enter the location you would like to add. Enter \"x\" to return to base mode.");
    userInput = sc.next();
    while (!userInput.equals("x")) {
      String location = userInput;
      userInput = sc.nextLine();
      if (userInput != "") {
        location += userInput;
      }
      if (dormitories.contains(location) || restaurants.contains(location)) {
        System.out.println(location + " is already present in the system.");
      }
      else {
        ArrayList<String> neightoadd = new ArrayList<>();
        ArrayList<Integer> weights = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++) {
          int temp = (int)(Math.random()*neighbors.size());
          while (neighborInd[temp]) {
            temp = (int)(Math.random()*neighbors.size());
          }
          neighborInd[temp] = true;
          neightoadd.add(neighbors.get(temp));
          weights.add((int)(Math.random()*10 + 1));
        }
        if (this.backend.addDorm(location, neightoadd, weights)) {
          System.out.println("Location successfully added!");
          neighbors.add(location);
          restaurants.add(location);
          neighborInd = new boolean[neighbors.size()];
        } else {
          System.out.println("Location could not be added.");
        }
      }
      System.out.println("Enter the location you would like to add. Enter \"x\" to return to base mode.");
      userInput = sc.next();
    }
  }

  /**
   * This method provides the path with the least total sum of weights (most time = cheapest)
   */
  public void runExpectedTimeMode() {
    String userInput = "";
    String dorm = "";
    String restaurant = "";
    System.out.println("Welcome to expected time mode!");
    System.out.println("Here are the restaurants you can order from: ");
    for (int i = 0; i < restaurants.size(); i++) {
      if (i < restaurants.size() - 1)
        System.out.print(restaurants.get(i) + ", ");
      else
        System.out.println(restaurants.get(i));
    }
    System.out.println("Here are the locations we support delivery to: ");
    for (int i = 0; i < dormitories.size(); i++) {
      if (i < dormitories.size() - 1)
        System.out.print(dormitories.get(i) + ", ");
      else
        System.out.println(dormitories.get(i));
    }
    System.out.println(
        "Enter the location you want your order delivered to and the restaurant you are ordering from separated by spaces. Enter \"x\" to return to base mode.");
    while (!userInput.equals("x")) {
      userInput = sc.next();
      if (userInput.equals("x")) {
        break;
      }
      try {
        dorm = userInput;
        userInput = sc.nextLine();
        restaurant = userInput;
        if (restaurant.length()<=0) {
          throw new NoSuchElementException();
        }
        restaurant = restaurant.substring(1);
        if (!dormitories.contains(dorm) || !restaurants.contains(restaurant)) {
          throw new NoSuchElementException();
        }
        ArrayList<String> path = new ArrayList<String>(this.backend.findCheapestDelivery(dorm, restaurant));
        String time = String.valueOf(backend.expectedTime(path));
        System.out.println("You order from " + restaurant + " will take " + time + " minutes.");
        System.out.print("The path taken will be: ");
        for (int i = path.size()-1; i >=0; i--) {
          if (i != 0) {
            System.out.print(path.get(i) + ", ");
          }
          else {
            System.out.println(path.get(i) + ".");
          }
        }
      } catch (NullPointerException | NoSuchElementException | NumberFormatException e) {
        System.out.println("Invalid Input");
      }
      System.out.println("Enter the location you want your order delivered to and the restaurant you are ordering from separated by spaces. Enter \"x\" to return to base mode.");
    }
  }

  /**
   * This method chooses the path with the least number of edges (fastest = most expensive)
   */
  public void runFastestPathMode() {
    String userInput = "";
    String dorm = "";
    String restaurant = "";
    System.out.println("Welcome to the fastest path mode!");
    System.out.println("Here are the restaurants you can order from: ");
    for (int i = 0; i < restaurants.size(); i++) {
      if (i < restaurants.size() - 1)
        System.out.print(restaurants.get(i) + ", ");
      else
        System.out.println(restaurants.get(i));
    }
    System.out.println("Here are the locations we support delivery to: ");
    for (int i = 0; i < dormitories.size(); i++) {
      if (i < dormitories.size() - 1)
        System.out.print(dormitories.get(i) + ", ");
      else
        System.out.println(dormitories.get(i));
    }
    System.out.println(
        "Enter the location you want your order delivered to and the restaurant you are ordering from separated by spaces. Enter \"x\" to return to base mode.");
    while (!userInput.equals("x")) {
      userInput = sc.next();
      if (userInput.equals("x")) {
        break;
      }
      try {
        dorm = userInput;
        userInput = sc.nextLine();
        restaurant = userInput;
        restaurant = restaurant.substring(1);
        if (!dormitories.contains(dorm) || !restaurants.contains(restaurant)) {
          throw new NoSuchElementException();
        }
        ArrayList<String> path = new ArrayList<String>(this.backend.findFastestDelivery(dorm, restaurant));
        String time = String.valueOf(path.size()*3);
        System.out.println("You order from " + restaurant + " will take " + time + " minutes.");
        System.out.print("The path taken will be: ");
        for (int i = path.size()-1; i >=0; i--) {
          if (i != 0) {
            System.out.print(path.get(i) + ", ");
          }
          else {
            System.out.println(path.get(i) + ".");
          }
        }
      } catch (NullPointerException | NoSuchElementException | NumberFormatException e) {
        System.out.println("Invalid Input");
      }
      System.out.println("Enter the location you want your order delivered to and the restaurant you are ordering from separated by spaces. Enter \"x\" to return to base mode.");
    }
  }
}
