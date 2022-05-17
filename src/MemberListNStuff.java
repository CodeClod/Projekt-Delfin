import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class MemberListNStuff {
  ID id = new ID();
  GUI gui = new GUI();
  ArrayList<Member> memberList = new ArrayList<>();
  private String paymentDueDate = id.createPaymentDate();
  void addMember() throws FileNotFoundException {

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
      out.print("\n");
    }
    System.out.println("Indtast navn:");
    String name = gui.getString();

    System.out.println("Indtast alder:");
    int age = gui.getInt();

    System.out.println("Indtast medlemsstatus (passiv/aktiv). Brug P eller A");

    String passiveActive = "";
    while (!passiveActive.equals("P") && !passiveActive.equals("A")) {
      passiveActive = gui.getString().toUpperCase(Locale.ROOT);
      if (!passiveActive.equals("P") && !passiveActive.equals("A")) System.out.println("Brug 'P' eller 'A'");

    }

    memberList.add(new Member(ID.getID(), name, age, passiveActive, paymentDueDate));
    /*
    switch(add)
    case M: addMotionist();
    case K: addKonkurrence();
    case T: addTræner();
    */

    out.print(memberList.get(memberList.size() - 1).getNumber());
    out.print(";");

    out.print(memberList.get(memberList.size() - 1).getName());
    out.print(";");

    out.print(memberList.get(memberList.size() - 1).getAge());

    out.print(";");

    out.print(memberList.get(memberList.size() - 1).getPassivAktiv());

    out.print(";");

    out.print(memberList.get(memberList.size() - 1).getJuniorSenior());

    out.print(";");

    out.print(memberList.get(memberList.size() - 1).getKontingent());
    out.print(";");
    out.print(memberList.get(memberList.size() - 1).getPaymentDueDate());

    out.print("\n");

  }

  public void loadMenu() throws FileNotFoundException {
    Scanner fileScanner = new Scanner(new File("MembersInfo\\Motionistsvømmere.csv"));
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
      int number = input.nextInt();
      String name = input.next();
      int age = input.nextInt();
      String aktivPassiv = input.next();
      String juniorSenior = input.next();
      String casualCompetitive = input.next();

      memberList.add(new Member(number, name, age, aktivPassiv, paymentDueDate));

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
      System.out.print("Tryk Enter for at gå til menuen");
      gui.getString();
      loop = false;
    }
  }
}
