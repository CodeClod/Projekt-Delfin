import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class MemberListNStuff {

  GUI gui = new GUI();
  ArrayList<Member> memberList = new ArrayList<>();

  void addMember() throws FileNotFoundException {

    PrintStream out = new PrintStream(("MembersInfo\\members.csv"));
    int myNum=0;
    for (int i = 0; i< memberList.size(); i++) {
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
      out.print(memberList.get(i).getMotionistKonkurrence());
      out.print(";");
      out.print(memberList.get(i).getKontingent());
      out.print("\n");
      myNum = memberList.get(i).getNumber();
    }
    System.out.println("Enter name.");
    String name = gui.getString();

    System.out.println("Enter age:");
    int age=gui.getInt();

    System.out.println("Enter member status(passive/active). Use P or A");

    String passiveActive="";
    while (!passiveActive.equals("P")&&!passiveActive.equals("A")){
       passiveActive = gui.getString().toUpperCase(Locale.ROOT);
        if (!passiveActive.equals("P")&&!passiveActive.equals("A")) System.out.println("Please write 'P' or 'A'");

    }



    System.out.println("Enter activity(Motionist/Konkurrence). Use M or K");

    String casualCompetitive="";
    while (!casualCompetitive.equals("K")&&!casualCompetitive.equals("M")){
      casualCompetitive = gui.getString().toUpperCase(Locale.ROOT);
      if (!casualCompetitive.equals("K")&&!casualCompetitive.equals("M")) System.out.println("Please write 'M' or 'K'");

    }





    memberList.add(new Member(myNum+1,name,age,passiveActive,casualCompetitive));
    out.print(memberList.get(memberList.size()-1).getNumber());
    out.print(";");

    out.print(memberList.get(memberList.size()-1).getName());
    out.print(";");

    out.print(memberList.get(memberList.size()-1).getAge());

    out.print(";");

    out.print(memberList.get(memberList.size()-1).getPassivAktiv());

    out.print(";");

    out.print(memberList.get(memberList.size()-1).getJuniorSenior());

    out.print(";");

    out.print(memberList.get(memberList.size()-1).getMotionistKonkurrence());
    out.print(";");

    out.print(memberList.get(memberList.size()-1).getKontingent());


    out.print("\n");

  }
  public void loadMenu() throws FileNotFoundException {
    Scanner fileScanner = new Scanner(new File("MembersInfo\\members.csv"));
    while(fileScanner.hasNextLine()){
      String line = fileScanner.nextLine();
      Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
      int number = input.nextInt();
      String name = input.next();
      int age = input.nextInt();
      String aktivPassiv = input.next();
      String juniorSenior=input.next();
      String casualCompetitive=input.next();

     memberList.add(new Member(number,name,age,aktivPassiv,casualCompetitive));
    }

  }



  public void showMemberList() {
    boolean loop = true;
    while (loop) {
      for (Member menuItems : memberList) {
        System.out.println(menuItems.getName());
        System.out.println(menuItems.getAge());
        System.out.println(menuItems.getJuniorSenior());
        System.out.println(menuItems.getMotionistKonkurrence());
        System.out.println(menuItems.getKontingent());
      }
      System.out.print("Press Enter to exit the menu.");
      gui.getString();
      loop = false;
    }
  }
}
