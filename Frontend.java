import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

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
  ArrayList<String> neighbors;
  ArrayList<String> dormitories;
  ArrayList<String> restaurants;
  String[] dorms = {"Adams", "Barnard", "Bradley", "Chadbourne", "Cole", "Davis", "Dejope",
      "Humphrey", "Jorns", "Kronshage", "Leopold", "Merit", "Ogg", "Phillips", "Sellery",
      "Slichter", "Smith", "Sullivan", "Tripp", "Waters", "Witte"};
  String[] rests = {"Carson's Market", "Four Lakes Market", "Gordon Avenue Market", "Liz's Market",
      "Rheta's Market", "Starbucks"};
  boolean[] neighborInd;
  public Frontend() {
    this.sc = new Scanner(System.in);
    Collections.addAll(dormitories, dorms);
    Collections.addAll(restaurants, rests);
    Collections.addAll(neighbors, neighborslist);
  }

  public static void main(String[] args) throws FileNotFoundException {
    Backend backend;
    backend = new Backend(new FileReader(
        "C:\\Users\\sudhi\\eclipse-workspace\\CityExplorer\\src\\Cost-of-living-2018.csv"));
    Frontend ft = new Frontend();
    ft.run(backend);
  }

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

  public void runBaseMode() {
    String userInput = "";
    System.out.println("Welcome to the Starship Ordering Service!");
    System.out.println("Here are the restaurants you can order from: ");
    for (int i = 0; i < restaurants.size(); i++) {
      if (i < restaurants.size() - 1)
        System.out.print(restaurants.get(i) + ", ");
      else
        System.out.print(restaurants.get(i));
    }
    System.out.println("Enter \"S\" to enter search location mode.");
    System.out.println("Enter \"R\" to enter remove unwanted location mode.");
    System.out.println("Enter \"A\" to enter add location mode.");
    System.out.println("Enter \"E\" to enter expected time mode");
    System.out.println("Enter \"P\" to enter show path taken mode");
    System.out.println("Enter \"x\" to quit.");
    while (!userInput.equals("x")) {
      userInput = sc.next();
      if (userInput.equals("S") || userInput.equals("R") || userInput.equals("A")
          || userInput.equals("E") || userInput.equals("P")) {
        if (userInput.equals("S")) {
          runSearchMode();
        } else if (userInput.equals("R")) {
          runRemoveMode();
        } else if (userInput.equals("A")) {
          runAddMode();
        } else if (userInput.equals("E")) {
          runExpectedTimeMode();
        } else if (userInput.equals("P")) {
          runCheapestPathMode();
        } else if (userInput.equals("x")) {
          break;
        }
      }
    }
  }

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
        System.out.println(location + "was successfully removed.");
      } else {
        System.out.println(location + "could not be removed.");
      }
      System.out.println(
          "Enter the location you would like to remove. Enter \"x\" to return to base mode.");
      userInput = sc.next();
    }
  }


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
          int temp = (int)(Math.random()*45 + 1);
          neightoadd.set(i, neighbors.get(temp));
          weights.set(i, (int)(Math.random()*10 + 1));
        }
        if (this.backend.addDorm(location, neightoadd, weights)) {
          System.out.println("Location successfully added!");
        } else {
          System.out.println("Location could not be added.");
        }
      }
      System.out.println("Enter the location you would like to add. Enter \"x\" to return to base mode.");
    }
  }

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
        System.out.print(restaurants.get(i));
    }
    System.out.println("Here are the locations we support delivery to: ");
    for (int i = 0; i < dormitories.size(); i++) {
      if (i < dormitories.size() - 1)
        System.out.print(dormitories.get(i) + ", ");
      else
        System.out.print(dormitories.get(i));
    }
    System.out.println(
        "Enter the location you want your order delivered to and the restaurant you are ordering from separated by spaces.");
    while (!userInput.equals("x")) {
      userInput = sc.next();
      if (userInput.equals("x")) {
        break;
      }
      try {
        dorm = userInput;
        userInput = sc.nextLine();
        restaurant = userInput;
        if (!dormitories.contains(dorm) || !restaurants.contains(restaurant)) {
          throw new NoSuchElementException();
        }
        ArrayList<String> path = new ArrayList<String>(this.backend.findCheapestDelivery(dorm, restaurant));
        String time = String.valueOf(backend.expectedTime(dorm, restaurant));
        System.out.println("You order from " + restaurant + " will take " + time + " minutes.");
        System.out.print("The path taken will be: ");
        for (int i = 0; i < path.size(); i++) {
          if (i < path.size()-1) {
            System.out.print(path.get(i) + ", ");
          }
          else {
            System.out.print(path.get(i) + ".");
          }
        }
      } catch (NullPointerException | NoSuchElementException | NumberFormatException e) {
        System.out.println("Invalid Input");
      }
      System.out.println("Enter the location you want your order delivered to and the restaurant you are ordering from separated by spaces.");
    }
  }

  public void runCheapestPathMode() {
    String userInput = "";
    String dorm = "";
    String restaurant = "";
    System.out.println("Welcome to the fastest path mode!");
    System.out.println("Here are the restaurants you can order from: ");
    for (int i = 0; i < restaurants.size(); i++) {
      if (i < restaurants.size() - 1)
        System.out.print(restaurants.get(i) + ", ");
      else
        System.out.print(restaurants.get(i));
    }
    System.out.println("Here are the locations we support delivery to: ");
    for (int i = 0; i < dormitories.size(); i++) {
      if (i < dormitories.size() - 1)
        System.out.print(dormitories.get(i) + ", ");
      else
        System.out.print(dormitories.get(i));
    }
    System.out.println(
        "Enter the location you want your order delivered to and the restaurant you are ordering from separated by spaces.");
    while (!userInput.equals("x")) {
      userInput = sc.next();
      if (userInput.equals("x")) {
        break;
      }
      try {
        dorm = userInput;
        userInput = sc.nextLine();
        restaurant = userInput;
        if (!dormitories.contains(dorm) || !restaurants.contains(restaurant)) {
          throw new NoSuchElementException();
        }
        ArrayList<String> path = new ArrayList<String>(this.backend.findFastestDelivery(dorm, restaurant));
        String time = String.valueOf(path.size()*2);
        System.out.println("You order from " + restaurant + " will take " + time + " minutes.");
        System.out.print("The path taken will be: ");
        for (int i = 0; i < path.size(); i++) {
          if (i < path.size()-1) {
            System.out.print(path.get(i) + ", ");
          }
          else {
            System.out.print(path.get(i) + ".");
          }
        }
      } catch (NullPointerException | NoSuchElementException | NumberFormatException e) {
        System.out.println("Invalid Input");
      }
      System.out.println("Enter the location you want your order delivered to and the restaurant you are ordering from separated by spaces.");
    }
  }
}
