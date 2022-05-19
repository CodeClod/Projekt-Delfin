import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class MotionistManager {

  GUI gui = new GUI();
  ArrayList<Member> memberList = new ArrayList<>();
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");


  public MotionistManager() {
  }

  void addMember() throws FileNotFoundException, ParseException {

    System.out.println("Enter name.");
    String name = gui.getString();
    System.out.println("Enter age:");
    int age = gui.getInt();
    System.out.println("Enter member status(passive/active). Use P or A");
    String passiveActive = "";
    while (!passiveActive.equals("P") && !passiveActive.equals("A")) {
      passiveActive = gui.getString().toUpperCase(Locale.ROOT);
      if (!passiveActive.equals("P") && !passiveActive.equals("A"))
        System.out.println("Please write 'P' or 'A'");
    }
    String paymentDueDate = ID.createPaymentDate();
    memberList.add(new Member(ID.getID(), name, age, passiveActive, paymentDueDate, "ikkeBetalt"));
    updateInfo(-1);
  }

  void updateInfo(int x) throws FileNotFoundException {

    PrintStream out = new PrintStream(("MembersInfo\\Motionistsvømmere.csv"));
    for (int i = 0; i < memberList.size(); i++) {

      out.print(memberList.get(i).getNumber());
      out.print(";");
      out.print(memberList.get(i).getName());
      out.print(";");
      out.print(memberList.get(i).getAge());
      out.print(";");
      out.print(memberList.get(i).getPassivAktiv());
      out.print(";");
      out.print(memberList.get(i).getJuniorSenior());
      out.print(";");
      out.print(memberList.get(i).getKontingent());
      out.print(";");
      out.print(memberList.get(i).getPaymentDueDate());
      out.print(";");
      if (memberList.get(i).getNumber() == x) {
        out.print("betalt");
        memberList.get(i).setBetalt("betalt");
      }
      if (memberList.get(i).getNumber() != x) out.print(memberList.get(i).getBetalt());
      out.print("\n");
    }

  }

  public void loadMenu() throws FileNotFoundException, ParseException {
    Scanner fileScanner = new Scanner(new File("MembersInfo\\Motionistsvømmere.csv"));
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
      int number = input.nextInt();
      String name = input.next();
      int age = input.nextInt();
      String aktivPassiv = input.next();
      input.next();
      input.next();
      String paymentDueDate = input.next();
      String betalt = input.next();
      memberList.add(new Member(number, name, age, aktivPassiv, paymentDueDate, betalt));
    }

  }

  public void payBill(int ID) throws FileNotFoundException {
    Scanner fileScanner2 = new Scanner(new File("MembersInfo\\Motionistsvømmere.csv"));

    while (fileScanner2.hasNextLine()) {
      String line = fileScanner2.nextLine();
      Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
      int number = input.nextInt();
      String betalt = "";
      if (ID == number) betalt = "betalt";


    }
  }

  public void showMemberList() {
  /*
  switch(show)
  case A: showAll();
  case K: showKon();
  case M: showMot();
  case T: showTræ();
  case J: showJunior();
  case S: showSenior();

   */
    boolean loop = true;
    while (loop) {
      for (Member menuItems : memberList) {
        System.out.println(menuItems.getName());
        System.out.println(menuItems.getAge());
        System.out.println(menuItems.getJuniorSenior());
        System.out.println(menuItems.getKontingent());
      }
      System.out.print("Press Enter to exit the menu.");
      gui.getString();
      loop = false;
    }
  }


  void visRestance() throws ParseException, FileNotFoundException {
    Date currentDate = new Date();
    System.out.println(memberList.get(0).getPaymentDueDate());
    for (int i = 0; i < memberList.size(); i++) {
      if (memberList.get(i).getBetalt().equals("ikkeBetalt") &&
          currentDate.compareTo(memberList.get(i).getDueDate()) > 0) {
        System.out.println(memberList.get(i).getName() +
            " er i restance. ID nr:" + memberList.get(i).getNumber());
      }


    }
  }
}