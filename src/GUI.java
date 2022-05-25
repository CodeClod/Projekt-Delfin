import java.util.Scanner;


public class GUI {
  private final Scanner inputString = new Scanner(System.in);
  private final Scanner inputNumber = new Scanner(System.in);

  public String getString() {
    return inputString.nextLine();
  }

  public double getDouble() {
    try {
      return inputNumber.nextDouble();
    } catch (Exception e) {
      System.err.println("Forkert input, brug venligst tal og separer med komma. Prøv igen!");
      inputNumber.nextLine();
      return getDouble();
    }

  }
  public int getInt() {
    try {
      return inputNumber.nextInt();
    } catch (Exception e) {
      System.err.println("Forkert input, kun heltal. Prøv igen!");
      inputNumber.nextLine();
      return getInt();
    }
  }
}
