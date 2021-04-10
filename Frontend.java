import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Frontend implements FrontendInterface {
  private Scanner sc;
  private Backend backend;
  
  public Frontend() {
    this.sc = new Scanner(System.in);
  }
  
  public static void main(String[] args) throws FileNotFoundException {
    Backend backend;
    backend = new Backend(new FileReader("C:\\Users\\sudhi\\eclipse-workspace\\CityExplorer\\src\\Cost-of-living-2018.csv"));
    Frontend ft = new Frontend();
    ft.run(backend);
  }
  
  @Override
  public void run(Backend backend) {
    if (backend!=null) {
      this.backend=backend;
      runBaseMode();
      System.out.println("Thanks for ordering with Starship!");
    } else {
      System.out.println("No Backend found!");
  }
}

  public void runBaseMode() {
    String userInput = "";
    System.out.println("Welcome to the Starship Ordering Service!");
    
    System.out.println("Enter \"S\" to enter search location mode.");
    System.out.println("Enter \"R\" to enter remove unwanted location mode.");
    System.out.println("Enter \"A\" to enter desire location mode.");
    System.out.println("Enter \"E\" to enter expected time mode");
    System.out.println("Enter \"P\" to enter show path taken mode");
    System.out.println("Enter \"x\" to quit.");
    while (!userInput.equals("x")) {
      userInput = sc.next();
      if (userInput.equals("S") || userInput.equals("R") || userInput.equals("A") || userInput.equals("E") || userInput.equals("P")) {
        if (userInput.equals("S")) {
          runSearchMode();
        } else if (userInput.equals("R")) {
          runRemoveMode();
        } else if (userInput.equals("A")) {
          runAddMode();
        } else if (userInput.equals("E")) {
          runTimeMode();
        } else if (userInput.equals("P")) {
          runPathMode();
        } else if (userInput.equals("x")) {
            break;
        }
      } 
    }
  }
  
  public void runSearchMode() {
    
  }
  public void runRemoveMode() {
    
  }
  public void runAddMode() {
    
  }
  public void runTimeMode() {
    
  }
  public void runPathMode() {
    
  }
}
