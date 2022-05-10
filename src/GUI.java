import java.util.Scanner;


public class GUI {
  private Scanner inputString = new Scanner(System.in);
  private Scanner inputNumber = new Scanner(System.in);

  public String getString() {
    return inputString.nextLine();
  }

  public double getDouble() {
    try {
      return inputNumber.nextDouble();
    } catch (Exception e) {
      System.err.println("Wrong input, please use numbers and seperate decimals with comma(for example 1,5). Try again");
      inputNumber.nextLine();
      return getDouble();
    }

  }

  public int getInt() {
    try {
      return inputNumber.nextInt();
    } catch (Exception e) {
      System.err.println("Wrong input, please use numbers(integers only). Try again");
      inputNumber.nextLine();
      return getInt();
    }
  }
}
