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
    System.out.println("Enter \"R\" to enter remove unwanted location mode.");
    System.out.println("Enter \"A\" to enter desire location mode.");
    System.out.println("Enter \"E\" to enter expected time mode");
    System.out.println("Enter \"P\" to enter show path taken mode");
    System.out.println("Enter \"x\" to quit.");
  }
}
