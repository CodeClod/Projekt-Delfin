import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class KonkurrenceManager {

    GUI gui = new GUI();
    ArrayList<MemberKonkurrence> memberList = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");



    public KonkurrenceManager() throws FileNotFoundException {
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
        LocalTime emptyTime=LocalTime.of(0,0,0,0);
        LocalDate emptyDate=LocalDate.of(0,0,0);
            memberList.add(new MemberKonkurrence(ID.getID(), name, age, passiveActive, paymentDueDate,"ikkeBetalt",emptyTime,emptyDate,emptyTime,emptyDate,emptyTime,emptyDate,emptyTime,emptyDate));
            updateInfo(-1,"nothing");
        }


    void updateInfo(int x,String handling) throws FileNotFoundException, ParseException {

        PrintStream out = new PrintStream(("MembersInfo\\Konkurrencesvømmere.csv"));
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
            if (memberList.get(i).getNumber()==x&&handling.equals("betal")) {out.print("betalt"); memberList.get(i).setBetalt("betalt");}
            else out.print(memberList.get(i).getBetalt());
            out.print(";");
            if (memberList.get(i).getNumber()==x&&handling.equals("butterflyRecord")) {
                System.out.println("Enter minutes:"); int min= gui.getInt(); System.out.println("Enter seconds:");
                int sec= gui.getInt(); System.out.println("Enter ms:"); int ms= gui.getInt();
                System.out.println("Enter year the record was set:"); int year = gui.getInt();
                System.out.println("Enter month:"); int month = gui.getInt();
                System.out.println("Enter day:"); int day = gui.getInt();
                out.print(min+":"+sec+":"+ms);
                out.print(";");
                out.print(year+"/"+month+"/"+day);
                memberList.get(i).setButterFlyTime(LocalTime.of(min,sec,ms));
                memberList.get(i).setButterFlyDate(LocalDate.of(year,month,day));
            }
            else {out.print(memberList.get(i).getButterFlyTime()); out.print(";"); out.print(memberList.get(i).getButterFlyDate());}
            out.print(";");

            if (memberList.get(i).getNumber()==x&&handling.equals("rygCrawlRecord")) {
                System.out.println("Enter minutes:"); int min= gui.getInt(); System.out.println("Enter seconds:");
                int sec= gui.getInt(); System.out.println("Enter ms:"); int ms= gui.getInt();
                System.out.println("Enter year the record was set:"); int year = gui.getInt();
                System.out.println("Enter month:"); int month = gui.getInt();
                System.out.println("Enter day:"); int day = gui.getInt();
                out.print(min+":"+sec+":"+ms);
                out.print(";");
                out.print(year+"/"+month+"/"+day);
                memberList.get(i).setRygCrawlTime(LocalTime.of(min,sec,ms));
                memberList.get(i).setRygCrawlDate(LocalDate.of(year,month,day));
            }
            else {out.print(memberList.get(i).getRygCrawlTime()); out.print(";"); out.print(memberList.get(i).getRygCrawlDate());}
            out.print(";");
            if (memberList.get(i).getNumber()==x&&handling.equals("crawlRecord")) {
                System.out.println("Enter minutes:"); int min= gui.getInt(); System.out.println("Enter seconds:");
                int sec= gui.getInt(); System.out.println("Enter ms:"); int ms= gui.getInt();
                System.out.println("Enter year the record was set:"); int year = gui.getInt();
                System.out.println("Enter month:"); int month = gui.getInt();
                System.out.println("Enter day:"); int day = gui.getInt();
                out.print(min+":"+sec+":"+ms);
                out.print(";");
                out.print(year+"/"+month+"/"+day);
                memberList.get(i).setCrawlTime(LocalTime.of(min,sec,ms));
                memberList.get(i).setCrawlDate(LocalDate.of(year,month,day));
            }
            else {out.print(memberList.get(i).getCrawlTime()); out.print(";"); out.print(memberList.get(i).getCrawlDate());}
            out.print(";");
            if (memberList.get(i).getNumber()==x&&handling.equals("brystRecord")) {
                System.out.println("Enter minutes:"); int min= gui.getInt(); System.out.println("Enter seconds:");
                int sec= gui.getInt(); System.out.println("Enter ms:"); int ms= gui.getInt();
                System.out.println("Enter year the record was set:"); int year = gui.getInt();
                System.out.println("Enter month:"); int month = gui.getInt();
                System.out.println("Enter day:"); int day = gui.getInt();
                out.print(min+":"+sec+":"+ms);
                out.print(";");
                out.print(year+"/"+month+"/"+day);
                memberList.get(i).setBrystTime(LocalTime.of(0,min,sec,ms));

                memberList.get(i).setBrystDate(LocalDate.of(year,month,day));
            }
            else { out.print(memberList.get(i).getBrystTime()); out.print(";"); out.print(memberList.get(i).getBrystDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));}
            out.print(";");


            out.print("\n");
        }

    }

    public void loadMemberFile() throws FileNotFoundException, ParseException {
        Scanner fileScanner = new Scanner(new File("MembersInfo\\Konkurrencesvømmere.csv"));
        while ( fileScanner.hasNextLine()) {
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

            String butterflyTidStr = input.next();
            String [] butterFlyTidArrayStr = butterflyTidStr.split(":");
            int [] butterFlyTidArrayInt = new int[3];
            for (int i = 0; i < butterFlyTidArrayStr.length; i++) {
                butterFlyTidArrayInt[i] = Integer.parseInt(butterFlyTidArrayStr[i]);
            }
            LocalTime butterFlyTid=LocalTime.of(butterFlyTidArrayInt[0],butterFlyTidArrayInt[1],butterFlyTidArrayInt[2]);

            String butterflyDatoStr = input.next();
            String [] butterFlyDatoArrayStr = butterflyDatoStr.split("/");
            int [] butterFlyDatoArrayInt = new int[3];

//            System.out.println(butterFlyDatoArrayStr[0]);
            for (int i = 0; i < butterFlyDatoArrayStr.length; i++) {
                butterFlyDatoArrayInt[i] = Integer.parseInt(butterFlyDatoArrayStr[i]);
            }
            LocalDate butterFlyDato=LocalDate.of(butterFlyDatoArrayInt[2],butterFlyDatoArrayInt[1],butterFlyDatoArrayInt[0]);

            String rygCrawlTidStr = input.next();
            String [] rygCrawlTidArrayStr = rygCrawlTidStr.split(":");
            int [] rygCrawlTidArrayInt = new int[3];
            for (int i = 0; i < rygCrawlTidArrayStr.length; i++) {
                rygCrawlTidArrayInt[i] = Integer.parseInt(rygCrawlTidArrayStr[i]);
            }
            LocalTime rygCrawlTid=LocalTime.of(rygCrawlTidArrayInt[0],rygCrawlTidArrayInt[1],rygCrawlTidArrayInt[2]);

            String rygCrawlDatoStr = input.next();
            String [] rygCrawlDatoArrayStr = rygCrawlDatoStr.split("/");
            int [] rygCrawlDatoArrayInt = new int[3];

//            System.out.println(rygCrawlDatoArrayStr[0]);
            for (int i = 0; i < rygCrawlDatoArrayStr.length; i++) {
                rygCrawlDatoArrayInt[i] = Integer.parseInt(rygCrawlDatoArrayStr[i]);
            }
            LocalDate rygCrawlDato=LocalDate.of(rygCrawlDatoArrayInt[2],rygCrawlDatoArrayInt[1],rygCrawlDatoArrayInt[0]);

            String CrawlTidStr = input.next();
            String [] CrawlTidArrayStr = CrawlTidStr.split(":");
            int [] CrawlTidArrayInt = new int[3];
            for (int i = 0; i < CrawlTidArrayStr.length; i++) {
                CrawlTidArrayInt[i] = Integer.parseInt(CrawlTidArrayStr[i]);
            }
            LocalTime crawlTid=LocalTime.of(CrawlTidArrayInt[0],CrawlTidArrayInt[1],CrawlTidArrayInt[2]);

            String CrawlDatoStr = input.next();
            String [] CrawlDatoArrayStr = CrawlDatoStr.split("/");
            int [] CrawlDatoArrayInt = new int[3];

//            System.out.println(CrawlDatoArrayStr[0]);
            for (int i = 0; i < CrawlDatoArrayStr.length; i++) {
                CrawlDatoArrayInt[i] = Integer.parseInt(CrawlDatoArrayStr[i]);
            }
            LocalDate crawlDato=LocalDate.of(CrawlDatoArrayInt[2],CrawlDatoArrayInt[1],CrawlDatoArrayInt[0]);

            String brystTidStr = input.next();
            String [] brystTidArrayStr = brystTidStr.split(":");
            int [] brystTidArrayInt = new int[3];
            for (int i = 0; i < brystTidArrayStr.length; i++) {
                brystTidArrayInt[i] = Integer.parseInt(brystTidArrayStr[i]);
            }
            LocalTime brystTid=LocalTime.of(brystTidArrayInt[0],brystTidArrayInt[1],brystTidArrayInt[2]);

            String brystDatoStr = input.next();
            String [] brystDatoArrayStr = brystDatoStr.split("/");
            int [] brystDatoArrayInt = new int[3];

//            System.out.println(brystDatoArrayStr[0]);
            for (int i = 0; i < brystDatoArrayStr.length; i++) {
                brystDatoArrayInt[i] = Integer.parseInt(brystDatoArrayStr[i]);
            }
            LocalDate brystDato=LocalDate.of(brystDatoArrayInt[2],brystDatoArrayInt[1],brystDatoArrayInt[0]);


            memberList.add(new MemberKonkurrence(number, name, age, aktivPassiv, paymentDueDate,betalt,butterFlyTid,butterFlyDato,rygCrawlTid,rygCrawlDato,crawlTid,crawlDato,brystTid,brystDato));
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
            if (memberList.get(i).getBetalt().equals("ikkeBetalt")&&currentDate.compareTo(memberList.get(i).getDueDate()) > 0) {
                System.out.println(memberList.get(i).getName() + " er i restance. ID nr:"+memberList.get(i).getNumber());
            }


        }
    }
}