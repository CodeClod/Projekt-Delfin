import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class KonkurrenceManager {

  GUI gui = new GUI();
  ArrayList<MemberKonkurrence> memberListKonkurrence = new ArrayList<>();
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");


  public KonkurrenceManager() {
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
    LocalTime emptyTime = LocalTime.of(23, 0, 0, 0);
    LocalDate emptyDate = LocalDate.of(1, 1, 1);

    memberListKonkurrence.add(new MemberKonkurrence(ID.getID(), name, age, passiveActive, paymentDueDate,
        "ikkeBetalt", emptyTime, emptyDate, emptyTime, emptyDate, emptyTime, emptyDate, emptyTime, emptyDate));

    updateInfo(-1, "nothing");
  }


  void updateInfo(int medlemID, String handling) throws FileNotFoundException, ParseException {
boolean memberFound=false;
    PrintStream out = new PrintStream(("MembersInfo\\Konkurrencesvømmere.csv"));
    for (int i = 0; i < memberListKonkurrence.size(); i++) {
      if (memberListKonkurrence.get(i).getNumber() == medlemID)memberFound=true;
      out.print(memberListKonkurrence.get(i).getNumber());
      out.print(";");
      out.print(memberListKonkurrence.get(i).getName());
      out.print(";");
      out.print(memberListKonkurrence.get(i).getAge());
      out.print(";");
      out.print(memberListKonkurrence.get(i).getPassivAktiv());
      out.print(";");
      out.print(memberListKonkurrence.get(i).getJuniorSenior());
      out.print(";");
      out.print(memberListKonkurrence.get(i).getKontingent());
      out.print(";");
      out.print(memberListKonkurrence.get(i).getPaymentDueDate());
      out.print(";");
      if (memberListKonkurrence.get(i).getNumber() == medlemID && handling.equals("betal")) {
        out.print("betalt");
        memberListKonkurrence.get(i).setBetalt("betalt");
      } else out.print(memberListKonkurrence.get(i).getBetalt());
      out.print(";");
      if (memberListKonkurrence.get(i).getNumber() == medlemID && handling.equals("butterFlyRecord")) {
        System.out.println("Enter hours:");
        int hour = gui.getInt();
        System.out.println("Enter minutes:");
        int min = gui.getInt();
        System.out.println("Enter seconds:");
        int sec = gui.getInt();
        System.out.println("Enter nanoseconds: :");
        int nanoSecs = gui.getInt();
        System.out.println("Enter year the record was set:");
        int year = gui.getInt();
        System.out.println("Enter month:");
        int month = gui.getInt();
        System.out.println("Enter day:");
        int day = gui.getInt();
        out.print(hour + ":" + min + ":" + sec + ":" + nanoSecs);
        out.print(";");
        out.print(day + "/" + month + "/" + year);
        memberListKonkurrence.get(i).setButterFlyTime(LocalTime.of(hour, min, sec, nanoSecs));
        memberListKonkurrence.get(i).setButterFlyDate(LocalDate.of(year, month, day));
      } else {
        out.print(memberListKonkurrence.get(i).getButterFlyTime().getHour() + ":"
            + memberListKonkurrence.get(i).getButterFlyTime().getMinute()
            + ":" + memberListKonkurrence.get(i).getButterFlyTime().getSecond() + ":" +
            Integer.toString((memberListKonkurrence.get(i).getButterFlyTime().getNano())).replaceFirst("^0+(?!$)", ""));
        out.print(";");
        out.print(memberListKonkurrence.get(i).getButterFlyDate().getDayOfMonth() + "/" +
            memberListKonkurrence.get(i).getButterFlyDate().getMonthValue() + "/" +
            memberListKonkurrence.get(i).getButterFlyDate().getYear());
      }

      out.print(";");

      if (memberListKonkurrence.get(i).getNumber() == medlemID && handling.equals("rygCrawlRecord")) {
        System.out.println("Enter hours:");
        int hour = gui.getInt();
        System.out.println("Enter minutes:");
        int min = gui.getInt();
        System.out.println("Enter seconds:");
        int sec = gui.getInt();
        System.out.println("Enter nanoseconds: :");
        int nanoSecs = gui.getInt();
        System.out.println("Enter year the record was set:");
        int year = gui.getInt();
        System.out.println("Enter month:");
        int month = gui.getInt();
        System.out.println("Enter day:");
        int day = gui.getInt();
        out.print(hour + ":" + min + ":" + sec + ":" + nanoSecs);
        out.print(";");
        out.print(day + "/" + month + "/" + year);
        memberListKonkurrence.get(i).setRygCrawlTime(LocalTime.of(hour, min, sec, nanoSecs));
        memberListKonkurrence.get(i).setRygCrawlDate(LocalDate.of(year, month, day));
      } else {
        out.print(memberListKonkurrence.get(i).getRygCrawlTime().getHour() + ":" +
            memberListKonkurrence.get(i).getRygCrawlTime().getMinute() + ":" + memberListKonkurrence.get(i).getRygCrawlTime().getSecond()
            + ":" + Integer.toString((memberListKonkurrence.get(i).getRygCrawlTime().getNano())).replaceFirst("^0+(?!$)", ""));
        out.print(";");
        out.print(memberListKonkurrence.get(i).getRygCrawlDate().getDayOfMonth() + "/" +
            memberListKonkurrence.get(i).getRygCrawlDate().getMonthValue() + "/" + memberListKonkurrence.get(i).getRygCrawlDate().getYear());
      }

      out.print(";");
      if (memberListKonkurrence.get(i).getNumber() == medlemID && handling.equals("crawlRecord")) {
        System.out.println("Enter hours:");
        int hour = gui.getInt();
        System.out.println("Enter minutes:");
        int min = gui.getInt();
        System.out.println("Enter seconds:");
        int sec = gui.getInt();
        System.out.println("Enter nanoseconds: :");
        int nanoSecs = gui.getInt();
        System.out.println("Enter year the record was set:");
        int year = gui.getInt();
        System.out.println("Enter month:");
        int month = gui.getInt();
        System.out.println("Enter day:");
        int day = gui.getInt();
        out.print(hour + ":" + min + ":" + sec + ":" + nanoSecs);
        out.print(";");
        out.print(day + "/" + month + "/" + year);
        memberListKonkurrence.get(i).setCrawlTime(LocalTime.of(hour, min, sec, nanoSecs));
        memberListKonkurrence.get(i).setCrawlDate(LocalDate.of(year, month, day));
      } else {
        out.print(memberListKonkurrence.get(i).getCrawlTime().getHour() + ":" + memberListKonkurrence.get(i).getCrawlTime().getMinute()
            + ":" + memberListKonkurrence.get(i).getCrawlTime().getSecond() + ":" +
            Integer.toString((memberListKonkurrence.get(i).getCrawlTime().getNano())).replaceFirst("^0+(?!$)", ""));
        out.print(";");
        out.print(memberListKonkurrence.get(i).getCrawlDate().getDayOfMonth() + "/" +
            memberListKonkurrence.get(i).getCrawlDate().getMonthValue() + "/" + memberListKonkurrence.get(i).getCrawlDate().getYear());
      }

      out.print(";");
      if (memberListKonkurrence.get(i).getNumber() == medlemID && handling.equals("brystRecord")) {
        System.out.println("Enter hours:");
        int hour = gui.getInt();
        System.out.println("Enter minutes:");
        int min = gui.getInt();
        System.out.println("Enter seconds:");
        int sec = gui.getInt();
        System.out.println("Enter nanoseconds: :");
        int nanoSecs = gui.getInt();
        System.out.println("Enter year the record was set:");
        int year = gui.getInt();
        System.out.println("Enter month:");
        int month = gui.getInt();
        System.out.println("Enter day:");
        int day = gui.getInt();
        out.print(hour + ":" + min + ":" + sec + ":" + nanoSecs);
        out.print(";");
        out.print(day + "/" + month + "/" + year);
        memberListKonkurrence.get(i).setBrystTime(LocalTime.of(hour, min, sec, nanoSecs));
        memberListKonkurrence.get(i).setBrystDate(LocalDate.of(year, month, day));
      } else {
        out.print(memberListKonkurrence.get(i).getBrystTime().getHour() + ":" +
            memberListKonkurrence.get(i).getBrystTime().getMinute() + ":" + memberListKonkurrence.get(i).getBrystTime().getSecond()
            + ":" + Integer.toString((memberListKonkurrence.get(i).getBrystTime().getNano())).replaceFirst("^0+(?!$)", ""));
        out.print(";");
        out.print(memberListKonkurrence.get(i).getBrystDate().getDayOfMonth() + "/" +
            memberListKonkurrence.get(i).getBrystDate().getMonthValue() + "/" + memberListKonkurrence.get(i).getBrystDate().getYear());
      }

      out.print("\n");
    }
if (memberFound==false) System.out.println("Medlems ID ikke fundet eller medlem er motionistsvømmer");
  }

  public void loadMemberFile() throws FileNotFoundException, ParseException {
    Scanner fileScanner = new Scanner(new File("MembersInfo\\Konkurrencesvømmere.csv"));
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


      String butterFlyTidStr = input.next();
      String[] butterFlyTidArrayStr = butterFlyTidStr.split(":");
      int[] butterFlyTidArrayInt = new int[4];
      for (int i = 0; i < butterFlyTidArrayStr.length; i++) {
        butterFlyTidArrayInt[i] = Integer.parseInt(butterFlyTidArrayStr[i]);
      }

      LocalTime butterFlyTid = LocalTime.of(butterFlyTidArrayInt[0], butterFlyTidArrayInt[1],
          butterFlyTidArrayInt[2], butterFlyTidArrayInt[3]);

      String butterFlyDatoStr = input.next();
      String[] butterFlyDatoArrayStr = butterFlyDatoStr.split("/");
      int[] butterFlyDatoArrayInt = new int[3];

      for (int i = 0; i < butterFlyDatoArrayStr.length; i++) {
        butterFlyDatoArrayInt[i] = Integer.parseInt(butterFlyDatoArrayStr[i]);
      }


      LocalDate butterFlyDato = LocalDate.of(butterFlyDatoArrayInt[2],
          butterFlyDatoArrayInt[1], butterFlyDatoArrayInt[0]);


      String rygCrawlTidStr = input.next();
      String[] rygCrawlTidArrayStr = rygCrawlTidStr.split(":");
      int[] rygCrawlTidArrayInt = new int[4];
      for (int i = 0; i < rygCrawlTidArrayStr.length; i++) {
        rygCrawlTidArrayInt[i] = Integer.parseInt(rygCrawlTidArrayStr[i]);
      }

      LocalTime rygCrawlTid = LocalTime.of(rygCrawlTidArrayInt[0], rygCrawlTidArrayInt[1],
          rygCrawlTidArrayInt[2], rygCrawlTidArrayInt[3]);

      String rygCrawlDatoStr = input.next();
      String[] rygCrawlDatoArrayStr = rygCrawlDatoStr.split("/");
      int[] rygCrawlDatoArrayInt = new int[3];

//            System.out.println(rygCrawlDatoArrayStr[0]);
      for (int i = 0; i < rygCrawlDatoArrayStr.length; i++) {
        rygCrawlDatoArrayInt[i] = Integer.parseInt(rygCrawlDatoArrayStr[i]);
      }


      LocalDate rygCrawlDato = LocalDate.of(rygCrawlDatoArrayInt[2], rygCrawlDatoArrayInt[1], rygCrawlDatoArrayInt[0]);


      String crawlTidStr = input.next();
      String[] crawlTidArrayStr = crawlTidStr.split(":");
      int[] crawlTidArrayInt = new int[4];
      for (int i = 0; i < crawlTidArrayStr.length; i++) {
        crawlTidArrayInt[i] = Integer.parseInt(crawlTidArrayStr[i]);
      }

      LocalTime crawlTid = LocalTime.of(crawlTidArrayInt[0], crawlTidArrayInt[1],
          crawlTidArrayInt[2], crawlTidArrayInt[3]);

      String crawlDatoStr = input.next();
      String[] crawlDatoArrayStr = crawlDatoStr.split("/");
      int[] crawlDatoArrayInt = new int[3];
      for (int i = 0; i < crawlDatoArrayStr.length; i++) {
        crawlDatoArrayInt[i] = Integer.parseInt(crawlDatoArrayStr[i]);
      }


      LocalDate crawlDato = LocalDate.of(crawlDatoArrayInt[2], crawlDatoArrayInt[1], crawlDatoArrayInt[0]);


      String brystTidStr = input.next();
      String[] brystTidArrayStr = brystTidStr.split(":");
      int[] brystTidArrayInt = new int[4];
      for (int i = 0; i < brystTidArrayStr.length; i++) {
        brystTidArrayInt[i] = Integer.parseInt(brystTidArrayStr[i]);
      }

      LocalTime brystTid = LocalTime.of(brystTidArrayInt[0], brystTidArrayInt[1],
          brystTidArrayInt[2], brystTidArrayInt[3]);

      String brystDatoStr = input.next();
      String[] brystDatoArrayStr = brystDatoStr.split("/");
      int[] brystDatoArrayInt = new int[3];

//            System.out.println(brystDatoArrayStr[0]);
      for (int i = 0; i < brystDatoArrayStr.length; i++) {
        brystDatoArrayInt[i] = Integer.parseInt(brystDatoArrayStr[i]);
      }


      LocalDate brystDato = LocalDate.of(brystDatoArrayInt[2], brystDatoArrayInt[1], brystDatoArrayInt[0]);


      memberListKonkurrence.add(new MemberKonkurrence(number, name, age, aktivPassiv, paymentDueDate, betalt,
          butterFlyTid, butterFlyDato, rygCrawlTid, rygCrawlDato, crawlTid, crawlDato, brystTid, brystDato));
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
      for (Member menuItems : memberListKonkurrence) {
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

    for (int i = 0; i < memberListKonkurrence.size(); i++) {
      if (memberListKonkurrence.get(i).getBetalt().equals("ikkeBetalt") &&
          currentDate.compareTo(memberListKonkurrence.get(i).getDueDate()) > 0) {
        System.out.println(memberListKonkurrence.get(i).getName() + " er i restance. ID nr:" + memberListKonkurrence.get(i).getNumber());
      }


    }
  }

  void visRekorder() {
    System.out.println("Enter member ID:");
    int medlemID = gui.getInt();
    boolean success = false;
    for (int i = 0; i < memberListKonkurrence.size(); i++) {
      if (memberListKonkurrence.get(i).getNumber() == medlemID) {

        System.out.println("Medlem " + medlemID + " har følgende rekorder:\n" + "ButterFlysvømning: Dato: " +
            memberListKonkurrence.get(i).getButterFlyDate() + " Rekordtid: (h/m/s/ns)" + memberListKonkurrence.get(i).getButterFlyTime());

        System.out.println("RygCrawlsvømning: Dato: " + memberListKonkurrence.get(i).getRygCrawlDate() +
            " Rekordtid: (h/m/s/ns)" + memberListKonkurrence.get(i).getRygCrawlTime());

        System.out.println("Crawlsvømning: Dato: " + memberListKonkurrence.get(i).getCrawlDate() +
            " Rekordtid: (h/m/s/ns)" + memberListKonkurrence.get(i).getCrawlTime());

        System.out.println("Brystsvømning: Dato: " + memberListKonkurrence.get(i).getBrystDate() +
            " Rekordtid: (h/m/s/ns)" + memberListKonkurrence.get(i).getBrystTime());


        success = true;
      }

    }
    if (success == false) System.out.println("""
        Medlems ID kan ikke findes
        ID er enten ikke gyldigt eller medlem er motionist
        Prøv venligst igen
        """);


  }
}