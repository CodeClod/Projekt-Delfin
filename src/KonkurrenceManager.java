import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class KonkurrenceManager {

    GUI gui = new GUI();
    ArrayList<MemberKonkurrence> memberListKonkurrence = new ArrayList<>();
    private int hour = 0;
    private int min = 0;
    private int sec = 0;
    private int nanoSecs = 0;
    private int month = 0;
    private int day = 0;
    private int year = 0;

    public KonkurrenceManager() {
    }

    void printBedsteRekord() {
        String JS = gui.getString().toUpperCase();
        while(!JS.equals("J")&&!JS.equals("S")){ System.out.println("Skriv 'J' eller 'S'!");JS = gui.getString().toUpperCase();}
        if (JS.equals("J")) {
            System.out.print("Bedste rekorder for Junior-holdet:\n");
            try {
                memberListKonkurrence.sort(MemberKonkurrence::compareToButterFly);
                System.out.println("ButterFly:");
                printHjaelpMetodeJ("butterFly");
            } catch (Exception E) {
                System.out.println("");
            }

            try {
                memberListKonkurrence.sort(MemberKonkurrence::compareToRygCrawl);
                System.out.println("RygCrawl:");
                printHjaelpMetodeJ("rygCrawl");

            } catch (Exception E) {
                System.out.println("");
            }

            try {
                memberListKonkurrence.sort(MemberKonkurrence::compareToCrawl);
                System.out.println("Crawl:");
                printHjaelpMetodeJ("crawl");

            } catch (Exception E) {
                System.out.println("");
            }

            try {
                memberListKonkurrence.sort(MemberKonkurrence::compareToBryst);
                System.out.println("Bryst:");
                printHjaelpMetodeJ("bryst");

            } catch (Exception E) {
                System.out.println("");
            }
        }
        if (JS.equals("S")) {
            System.out.println("\nBedste Senior-hold rekorder:");
            try {
                memberListKonkurrence.sort(MemberKonkurrence::compareToButterFly);
                System.out.println("ButterFly:");
                printHjaelpMetodeS("butterFly");

            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberListKonkurrence.sort(MemberKonkurrence::compareToRygCrawl);
                System.out.println("RygCrawl:");
                printHjaelpMetodeS("rygCrawl");

            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberListKonkurrence.sort(MemberKonkurrence::compareToCrawl);
                System.out.println("Crawl:");
                printHjaelpMetodeS("crawl");

            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberListKonkurrence.sort(MemberKonkurrence::compareToBryst);
                System.out.println("Bryst:");
                printHjaelpMetodeS("bryst");

            } catch (Exception E) {
                System.out.println();
            }

        }
    }

    private void printHjaelpMetodeS(String disciplin) {
        int x = 0;
        int i = 0;
        LocalTime tid=LocalTime.of(0,0);

        while (x < 5) {
            if (disciplin=="butterFly") tid=memberListKonkurrence.get(i).getButterFlyTime();
            if (disciplin=="rygCrawl") tid=memberListKonkurrence.get(i).getRygCrawlTime();
            if (disciplin=="crawl") tid=memberListKonkurrence.get(i).getCrawlTime();
            if (disciplin=="bryst") tid=memberListKonkurrence.get(i).getBrystTime();

            if (memberListKonkurrence.get(i).getAge() >= 18) {
                System.out.println("ID:"+memberListKonkurrence.get(i).getNumber()+" Navn:"+memberListKonkurrence.get(i).getName()+" Tid:"+tid);
                x++;
            }
            i++;
        }
    }

    private void printHjaelpMetodeJ(String disciplin) {
        int x = 0;
        int i = 0;
        LocalTime tid=LocalTime.of(0,0);

        while (x < 5) {
            if (disciplin=="butterFly") tid=memberListKonkurrence.get(i).getButterFlyTime();
            if (disciplin=="rygCrawl") tid=memberListKonkurrence.get(i).getRygCrawlTime();
            if (disciplin=="crawl") tid=memberListKonkurrence.get(i).getCrawlTime();
            if (disciplin=="bryst") tid=memberListKonkurrence.get(i).getBrystTime();

            if (memberListKonkurrence.get(i).getAge() < 18) {
                System.out.println("ID:"+memberListKonkurrence.get(i).getNumber()+" Navn:"+memberListKonkurrence.get(i).getName()+" Tid:"+tid);
                x++;
            }
            i++;
        }
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
            if (!passiveActive.equals("P") && !passiveActive.equals("A")) System.out.println("Please write 'P' or 'A'");
        }
        String paymentDueDate = ID.createPaymentDate();
        LocalTime emptyTime = LocalTime.of(23, 0, 0, 0);
        LocalDate emptyDate = LocalDate.of(1, 1, 1);

        memberListKonkurrence.add(new MemberKonkurrence(ID.getID(), name, age, passiveActive, paymentDueDate, "ikkeBetalt", emptyTime, emptyDate, emptyTime, emptyDate, emptyTime, emptyDate, emptyTime, emptyDate));

        updateInfo(-1, "nothing");
    }

    void updateHjaelpeMetode() {
        System.out.println("Enter hours:");
        this.hour = gui.getInt();
        System.out.println("Enter minutes:");
        this.min = gui.getInt();
        System.out.println("Enter seconds:");
        this.sec = gui.getInt();
        System.out.println("Enter nanoseconds: :");
        this.nanoSecs = gui.getInt();
        System.out.println("Enter year the record was set:");
        this.year = gui.getInt();
        System.out.println("Enter month:");
        this.month = gui.getInt();
        System.out.println("Enter day:");
        this.day = gui.getInt();
    }

    void updateInfo(int medlemID, String handling) throws FileNotFoundException {
        PrintStream out = new PrintStream(("MembersInfo\\Konkurrencesvømmere.csv"));
        for (MemberKonkurrence memberKonkurrence : memberListKonkurrence) {
            MotionistManager.updateInfoHjaelp(out, memberKonkurrence);
            if (memberKonkurrence.getNumber() == medlemID && handling.equals("betal")) {
                out.print("betalt");
                memberKonkurrence.setBetalt("betalt");
            } else out.print(memberKonkurrence.getBetalt());
            out.print(";");

            if (memberKonkurrence.getNumber() == medlemID && handling.equals("butterFlyRecord")) {
                updateHjaelpeMetode();
                out.print(hour + ":" + min + ":" + sec + ":" + nanoSecs);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setButterFlyTime(LocalTime.of(hour, min, sec, nanoSecs));
                memberKonkurrence.setButterFlyDate(LocalDate.of(year, month, day));
            } else {
                out.print(memberKonkurrence.getButterFlyTime().getHour() + ":" + memberKonkurrence.getButterFlyTime().getMinute() + ":" + memberKonkurrence.getButterFlyTime().getSecond() + ":" + Integer.toString((memberKonkurrence.getButterFlyTime().getNano())).replaceFirst("^0+(?!$)", ""));
                out.print(";");


                out.print(memberKonkurrence.getButterFlyDate().getDayOfMonth() + "/" + memberKonkurrence.getButterFlyDate().getMonthValue() + "/" + memberKonkurrence.getButterFlyDate().getYear());
            }

            out.print(";");

            if (memberKonkurrence.getNumber() == medlemID && handling.equals("rygCrawlRecord")) {
                updateHjaelpeMetode();
                out.print(hour + ":" + min + ":" + sec + ":" + nanoSecs);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setRygCrawlTime(LocalTime.of(hour, min, sec, nanoSecs));
                memberKonkurrence.setRygCrawlDate(LocalDate.of(year, month, day));
            } else {
                out.print(memberKonkurrence.getRygCrawlTime().getHour() + ":" + memberKonkurrence.getRygCrawlTime().getMinute() + ":" + memberKonkurrence.getRygCrawlTime().getSecond() + ":" + Integer.toString((memberKonkurrence.getRygCrawlTime().getNano())).replaceFirst("^0+(?!$)", ""));
                out.print(";");
                out.print(memberKonkurrence.getRygCrawlDate().getDayOfMonth() + "/" + memberKonkurrence.getRygCrawlDate().getMonthValue() + "/" + memberKonkurrence.getRygCrawlDate().getYear());
            }

            out.print(";");
            if (memberKonkurrence.getNumber() == medlemID && handling.equals("crawlRecord")) {
                updateHjaelpeMetode();
                out.print(hour + ":" + min + ":" + sec + ":" + nanoSecs);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setCrawlTime(LocalTime.of(hour, min, sec, nanoSecs));
                memberKonkurrence.setCrawlDate(LocalDate.of(year, month, day));
            } else {
                out.print(memberKonkurrence.getCrawlTime().getHour() + ":" + memberKonkurrence.getCrawlTime().getMinute() + ":" + memberKonkurrence.getCrawlTime().getSecond() + ":" + Integer.toString((memberKonkurrence.getCrawlTime().getNano())).replaceFirst("^0+(?!$)", ""));
                out.print(";");
                out.print(memberKonkurrence.getCrawlDate().getDayOfMonth() + "/" + memberKonkurrence.getCrawlDate().getMonthValue() + "/" + memberKonkurrence.getCrawlDate().getYear());
            }
            out.print(";");
            if (memberKonkurrence.getNumber() == medlemID && handling.equals("brystRecord")) {
                updateHjaelpeMetode();
                out.print(hour + ":" + min + ":" + sec + ":" + nanoSecs);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setBrystTime(LocalTime.of(hour, min, sec, nanoSecs));
                memberKonkurrence.setBrystDate(LocalDate.of(year, month, day));
            } else {
                out.print(memberKonkurrence.getBrystTime().getHour() + ":" + memberKonkurrence.getBrystTime().getMinute() + ":" + memberKonkurrence.getBrystTime().getSecond() + ":" + Integer.toString((memberKonkurrence.getBrystTime().getNano())).replaceFirst("^0+(?!$)", ""));
                out.print(";");
                out.print(memberKonkurrence.getBrystDate().getDayOfMonth() + "/" + memberKonkurrence.getBrystDate().getMonthValue() + "/" + memberKonkurrence.getBrystDate().getYear());
            }

            out.print("\n");
        }
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

            LocalTime butterFlyTid = LocalTime.of(butterFlyTidArrayInt[0], butterFlyTidArrayInt[1], butterFlyTidArrayInt[2], butterFlyTidArrayInt[3]);

            String butterFlyDatoStr = input.next();
            String[] butterFlyDatoArrayStr = butterFlyDatoStr.split("/");
            int[] butterFlyDatoArrayInt = new int[3];

            for (int i = 0; i < butterFlyDatoArrayStr.length; i++) {
                butterFlyDatoArrayInt[i] = Integer.parseInt(butterFlyDatoArrayStr[i]);
            }

            LocalDate butterFlyDato = LocalDate.of(butterFlyDatoArrayInt[2], butterFlyDatoArrayInt[1], butterFlyDatoArrayInt[0]);


            String rygCrawlTidStr = input.next();
            String[] rygCrawlTidArrayStr = rygCrawlTidStr.split(":");
            int[] rygCrawlTidArrayInt = new int[4];
            for (int i = 0; i < rygCrawlTidArrayStr.length; i++) {
                rygCrawlTidArrayInt[i] = Integer.parseInt(rygCrawlTidArrayStr[i]);
            }

            LocalTime rygCrawlTid = LocalTime.of(rygCrawlTidArrayInt[0], rygCrawlTidArrayInt[1], rygCrawlTidArrayInt[2], rygCrawlTidArrayInt[3]);

            String rygCrawlDatoStr = input.next();
            String[] rygCrawlDatoArrayStr = rygCrawlDatoStr.split("/");
            int[] rygCrawlDatoArrayInt = new int[3];

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

            LocalTime crawlTid = LocalTime.of(crawlTidArrayInt[0], crawlTidArrayInt[1], crawlTidArrayInt[2], crawlTidArrayInt[3]);

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

            LocalTime brystTid = LocalTime.of(brystTidArrayInt[0], brystTidArrayInt[1], brystTidArrayInt[2], brystTidArrayInt[3]);

            String brystDatoStr = input.next();
            String[] brystDatoArrayStr = brystDatoStr.split("/");
            int[] brystDatoArrayInt = new int[3];

            for (int i = 0; i < brystDatoArrayStr.length; i++) {
                brystDatoArrayInt[i] = Integer.parseInt(brystDatoArrayStr[i]);
            }


            LocalDate brystDato = LocalDate.of(brystDatoArrayInt[2], brystDatoArrayInt[1], brystDatoArrayInt[0]);


            memberListKonkurrence.add(new MemberKonkurrence(number, name, age, aktivPassiv, paymentDueDate, betalt, butterFlyTid, butterFlyDato, rygCrawlTid, rygCrawlDato, crawlTid, crawlDato, brystTid, brystDato));
        }

    }


    void visRestance() {
        Date currentDate = new Date();

        for (MemberKonkurrence memberKonkurrence : memberListKonkurrence) {
            if (memberKonkurrence.getBetalt().equals("ikkeBetalt") && currentDate.compareTo(memberKonkurrence.getDueDate()) > 0) {
                System.out.println(memberKonkurrence.getName() + " er i restance. ID nr:" + memberKonkurrence.getNumber());
            }


        }
    }

    void visRekorder() {
        System.out.println("Enter member ID:");
        int medlemID = gui.getInt();
        boolean success = false;
        for (MemberKonkurrence memberKonkurrence : memberListKonkurrence) {
            if (memberKonkurrence.getNumber() == medlemID) {

                System.out.println("Medlem " + medlemID + " har følgende rekorder:\n" + "ButterFlysvømning: Dato: " + memberKonkurrence.getButterFlyDate() + " Rekordtid: (h/m/s/ns)" + memberKonkurrence.getButterFlyTime());

                System.out.println("RygCrawlsvømning: Dato: " + memberKonkurrence.getRygCrawlDate() + " Rekordtid: (h/m/s/ns)" + memberKonkurrence.getRygCrawlTime());

                System.out.println("Crawlsvømning: Dato: " + memberKonkurrence.getCrawlDate() + " Rekordtid: (h/m/s/ns)" + memberKonkurrence.getCrawlTime());

                System.out.println("Brystsvømning: Dato: " + memberKonkurrence.getBrystDate() + " Rekordtid: (h/m/s/ns)" + memberKonkurrence.getBrystTime());


                success = true;
            }

        }
        if (!success) System.out.println("""
                Medlems ID kan ikke findes
                ID er enten ikke gyldigt eller medlem er motionist
                Prøv venligst igen
                """);


    }
}