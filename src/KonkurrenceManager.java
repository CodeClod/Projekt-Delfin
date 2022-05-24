import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class KonkurrenceManager {

    GUI gui = new GUI();
    ArrayList<MemberKonkurrence> memberList = new ArrayList<>();
    ArrayList<Trainer> trainers = new ArrayList<>();
    ArrayList<Staevne> staevner = new ArrayList<>();
    Hold juniorHold = new Hold();
    Hold seniorHold = new Hold();
    private final int hour = 0;
    private int min = 0;
    private int sec = 0;

    private final int ms = 0;
    private int month = 0;
    private int day = 0;
    private int year = 0;

    public KonkurrenceManager() {
    }

    void printBedsteRekord() {
        String JS = gui.getString().toUpperCase();
        while (!JS.equals("J") && !JS.equals("S")) {
            System.out.println("Skriv 'J' eller 'S'!");
            JS = gui.getString().toUpperCase();
        }
        if (JS.equals("J")) {
            System.out.print("Bedste rekorder for Junior-holdet:\n");
            try {
                memberList.sort(MemberKonkurrence::compareToButterFly);
                System.out.println("ButterFly:");
                printHjaelpMetodeJ("butterFly");
            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberList.sort(MemberKonkurrence::compareToRygCrawl);
                System.out.println("RygCrawl:");
                printHjaelpMetodeJ("rygCrawl");

            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberList.sort(MemberKonkurrence::compareToCrawl);
                System.out.println("Crawl:");
                printHjaelpMetodeJ("crawl");

            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberList.sort(MemberKonkurrence::compareToBryst);
                System.out.println("Bryst:");
                printHjaelpMetodeJ("bryst");

            } catch (Exception E) {
                System.out.println();
            }
        }
        if (JS.equals("S")) {
            System.out.println("\nBedste Senior-hold rekorder:");
            try {
                memberList.sort(MemberKonkurrence::compareToButterFly);
                System.out.println("ButterFly:");
                printHjaelpMetodeS("butterFly");

            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberList.sort(MemberKonkurrence::compareToRygCrawl);
                System.out.println("RygCrawl:");
                printHjaelpMetodeS("rygCrawl");

            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberList.sort(MemberKonkurrence::compareToCrawl);
                System.out.println("Crawl:");
                printHjaelpMetodeS("crawl");

            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberList.sort(MemberKonkurrence::compareToBryst);
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
        LocalTime tid = LocalTime.of(0, 0);

        while (x < 5) {
            if (disciplin.equals("butterFly")) tid = memberList.get(i).getButterFlyTime();
            if (disciplin.equals("rygCrawl")) tid = memberList.get(i).getRygCrawlTime();
            if (disciplin.equals("crawl")) tid = memberList.get(i).getCrawlTime();
            if (disciplin.equals("bryst")) tid = memberList.get(i).getBrystTime();

            String stringTid = tid.format(DateTimeFormatter.ofPattern("mm:ss"));


            if (memberList.get(i).getAge() >= 18) {
                System.out.printf("ID: %-6d Navn: %-12s Tid: %10s", memberList.get(i).getNumber(), memberList.get(i).getName(), stringTid);
                System.out.println();
                x++;
            }
            i++;
        }
    }

    private void printHjaelpMetodeJ(String disciplin) {
        int x = 0;
        int i = 0;
        LocalTime tid = LocalTime.of(0, 0);

        while (x < 5) {
            if (disciplin.equals("butterFly")) tid = memberList.get(i).getButterFlyTime();
            if (disciplin.equals("rygCrawl")) tid = memberList.get(i).getRygCrawlTime();
            if (disciplin.equals("crawl")) tid = memberList.get(i).getCrawlTime();
            if (disciplin.equals("bryst")) tid = memberList.get(i).getBrystTime();

            String stringTid = tid.format(DateTimeFormatter.ofPattern("mm:ss"));


            if (memberList.get(i).getAge() < 18) {
                System.out.printf("ID: %-6d Navn: %-12s Tid: %10s", memberList.get(i).getNumber(), memberList.get(i).getName(), stringTid);
                System.out.println();
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

        memberList.add(new MemberKonkurrence(ID.getID(), name, age, passiveActive, paymentDueDate, "ikkeBetalt", emptyTime, emptyDate, emptyTime, emptyDate, emptyTime, emptyDate, emptyTime, emptyDate, false, false, false, false));

        updateInfo(-1, "nothing");
    }

    void updateHjaelpeMetode() {

        System.out.println("Enter minutes:");
        this.min = gui.getInt();
        System.out.println("Enter seconds:");
        this.sec = gui.getInt();

        System.out.println("Enter year the record was set:");
        this.year = gui.getInt();
        System.out.println("Enter month:");
        this.month = gui.getInt();
        System.out.println("Enter day:");
        this.day = gui.getInt();
    }

    void updateInfo(int medlemID, String handling) throws FileNotFoundException {
        PrintStream out = new PrintStream(("MembersInfo\\competitiveMembers.csv"));
        for (MemberKonkurrence memberKonkurrence : memberList) {
            MotionistManager.updateInfoHjaelp(out, memberKonkurrence);
            if (memberKonkurrence.getNumber() == medlemID && handling.equals("betal")) {
                out.print("betalt");
                memberKonkurrence.setBetalt("betalt");
            } else out.print(memberKonkurrence.getBetalt());
            out.print(";");

            if (memberKonkurrence.getNumber() == medlemID && handling.equals("butterFlyRecord")) {
                updateHjaelpeMetode();
                out.print(hour + ":" + min + ":" + sec);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setButterFlyTime(LocalTime.of(hour, min, sec));
                memberKonkurrence.setButterFlyDate(LocalDate.of(year, month, day));

            } else {
                out.print(memberKonkurrence.getButterFlyTime().getHour() + ":" + memberKonkurrence.getButterFlyTime().getMinute() + ":" + memberKonkurrence.getButterFlyTime().getSecond());
                out.print(";");
                out.print(memberKonkurrence.getButterFlyDate().getDayOfMonth() + "/" + memberKonkurrence.getButterFlyDate().getMonthValue() + "/" + memberKonkurrence.getButterFlyDate().getYear());
            }

            out.print(";");

            if (memberKonkurrence.getNumber() == medlemID && handling.equals("rygCrawlRecord")) {
                updateHjaelpeMetode();
                out.print(hour + ":" + min + ":" + sec);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setRygCrawlTime(LocalTime.of(hour, min, sec));
                memberKonkurrence.setRygCrawlDate(LocalDate.of(year, month, day));
            } else {
                out.print(memberKonkurrence.getRygCrawlTime().getHour() + ":" + memberKonkurrence.getRygCrawlTime().getMinute() + ":" + memberKonkurrence.getRygCrawlTime().getSecond());
                out.print(";");
                out.print(memberKonkurrence.getRygCrawlDate().getDayOfMonth() + "/" + memberKonkurrence.getRygCrawlDate().getMonthValue() + "/" + memberKonkurrence.getRygCrawlDate().getYear());
            }

            out.print(";");
            if (memberKonkurrence.getNumber() == medlemID && handling.equals("crawlRecord")) {
                updateHjaelpeMetode();
                out.print(hour + ":" + min + ":" + sec);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setCrawlTime(LocalTime.of(hour, min, sec));
                memberKonkurrence.setCrawlDate(LocalDate.of(year, month, day));
            } else {
                out.print(memberKonkurrence.getCrawlTime().getHour() + ":" + memberKonkurrence.getCrawlTime().getMinute() + ":" + memberKonkurrence.getCrawlTime().getSecond());
                out.print(";");
                out.print(memberKonkurrence.getCrawlDate().getDayOfMonth() + "/" + memberKonkurrence.getCrawlDate().getMonthValue() + "/" + memberKonkurrence.getCrawlDate().getYear());
            }
            out.print(";");
            if (memberKonkurrence.getNumber() == medlemID && handling.equals("brystRecord")) {
                updateHjaelpeMetode();
                out.print(hour + ":" + min + ":" + sec);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setBrystTime(LocalTime.of(hour, min, sec));
                memberKonkurrence.setBrystDate(LocalDate.of(year, month, day));
            } else {
                out.print(memberKonkurrence.getBrystTime().getHour() + ":" + memberKonkurrence.getBrystTime().getMinute() + ":" + memberKonkurrence.getBrystTime().getSecond());
                out.print(";");
                out.print(memberKonkurrence.getBrystDate().getDayOfMonth() + "/" + memberKonkurrence.getBrystDate().getMonthValue() + "/" + memberKonkurrence.getBrystDate().getYear());
            }
            out.print(";");
            out.print(memberKonkurrence.isButterFlyAktiv());
            out.print(";");
            out.print(memberKonkurrence.isRygCrawlAktiv());
            out.print(";");
            out.print(memberKonkurrence.isCrawlAktiv());
            out.print(";");
            out.print(memberKonkurrence.isBrystAktiv());
            out.print("\n");
        }
    }

    public void loadMemberFileK() throws FileNotFoundException, ParseException {
        Scanner fileScanner = new Scanner(new File("MembersInfo\\competitiveMembers.csv"));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Scanner input = new Scanner(line).useDelimiter(";");
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

            boolean butterFlyAktivPassiv = Boolean.parseBoolean(input.next());
            boolean rygCrawlAktivPassiv = Boolean.parseBoolean(input.next());
            boolean crawlAktivPassiv = Boolean.parseBoolean(input.next());
            boolean brystAktivPassiv = Boolean.parseBoolean(input.next());


            memberList.add(new MemberKonkurrence(number, name, age, aktivPassiv, paymentDueDate, betalt, butterFlyTid, butterFlyDato, rygCrawlTid, rygCrawlDato, crawlTid, crawlDato, brystTid, brystDato, butterFlyAktivPassiv, rygCrawlAktivPassiv, crawlAktivPassiv, brystAktivPassiv));
        }

    }

    void visRestance() {
        Date currentDate = new Date();

        for (MemberKonkurrence memberKonkurrence : memberList) {
            if (memberKonkurrence.getBetalt().equals("ikkeBetalt") && currentDate.compareTo(memberKonkurrence.getDueDate()) > 0) {
                System.out.println(memberKonkurrence.getName() + " er i restance. ID nr:" + memberKonkurrence.getNumber());
            }


        }
    }

    void visRekorder() {
        System.out.println("Enter member ID:");
        int medlemID = gui.getInt();
        boolean success = false;
        for (MemberKonkurrence memberKonkurrence : memberList) {
            if (memberKonkurrence.getNumber() == medlemID) {

                System.out.println("Medlem " + medlemID + " har følgende rekorder:\n" + "ButterFlysvømning: Dato: " + memberKonkurrence.getButterFlyDate() + " Rekordtid:" + memberKonkurrence.getButterFlyTime().format(DateTimeFormatter.ofPattern("mm:ss")));

                System.out.println("RygCrawlsvømning: Dato: " + memberKonkurrence.getRygCrawlDate() + " Rekordtid:" + memberKonkurrence.getRygCrawlTime().format(DateTimeFormatter.ofPattern("mm:ss")));

                System.out.println("Crawlsvømning: Dato: " + memberKonkurrence.getCrawlDate() + " Rekordtid:" + memberKonkurrence.getCrawlTime().format(DateTimeFormatter.ofPattern("mm:ss")));

                System.out.println("Brystsvømning: Dato: " + memberKonkurrence.getBrystDate() + " Rekordtid:" + memberKonkurrence.getBrystTime().format(DateTimeFormatter.ofPattern("mm:ss")));


                success = true;
            }

        }
        if (!success) System.out.println("""
                Medlems ID kan ikke findes
                ID er enten ikke gyldigt eller medlem er motionist
                Prøv venligst igen
                """);


    }

    void setAktivForDiscplin(String disciplin) throws FileNotFoundException {
        System.out.println("Indtast medlems ID:");
        int number = gui.getInt();
        int arrayListIndex = 0;
        boolean memberFound=false;

        while(memberFound==false) {
            for (int i = 0; i < memberList.size(); i++) {
                if (memberList.get(i).getNumber() == number){ arrayListIndex = i; memberFound=true;}
            }
            if (memberFound==false) {System.out.println("ID not found. Try again"); number=gui.getInt();}
        }


        System.out.println("Skal discplin sættes til aktiv eller passiv?(skriv 'A' eller 'P'");
        String aktivEllerpassiv = gui.getString().toUpperCase();
        boolean aktivPassiv = aktivEllerpassiv.equals("A");


        if (disciplin.equals("butterFly")) {
            memberList.get(arrayListIndex).setButterFlyAktiv(aktivPassiv);
        }
        if (disciplin.equals("rygCrawl")) {
            memberList.get(arrayListIndex).setRygCrawlAktiv(aktivPassiv);
        }
        if (disciplin.equals("crawl")) {
            memberList.get(arrayListIndex).setCrawlAktiv(aktivPassiv);
        }
        if (disciplin.equals("bryst")) {
            memberList.get(arrayListIndex).setBrystAktiv(aktivPassiv);
        }
        updateInfo(number, "nothing");
    }

    void visAktiveDiscipliner() {
        System.out.println("Indtast medlems ID:");
        int number = gui.getInt();
        int arrayListIndex = 0;
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getNumber() == number) arrayListIndex = i;
        }

        System.out.println(memberList.get(arrayListIndex).getName() + " aktivitetsstatuser på følgende discipliner er(true=aktiv):\nButterfly: " + memberList.get(arrayListIndex).isButterFlyAktiv() + "\nRygcrawl:" + memberList.get(arrayListIndex).isRygCrawlAktiv() + "\nCrawl:" + memberList.get(arrayListIndex).isCrawlAktiv() + "\nBryst:" + memberList.get(arrayListIndex).isBrystAktiv());


    }

    void addTrainer() throws FileNotFoundException {

        System.out.println("Indtast træner navn:");
        String navn = gui.getString();
        System.out.println("Tilføj træner til junior hold?(J/N)");
        String j = gui.getString();
        boolean junior = j.equals("J");
        System.out.println("Tilføj træner til senior hold?(J/N)");
        String s = gui.getString();
        boolean senior = s.equals("J");

        Trainer trainer = new Trainer(navn, ID.getID(), junior, senior);

        if (junior) juniorHold.addTrainer(trainer);
        if (senior) seniorHold.addTrainer(trainer);
        trainers.add(trainer);
        PrintStream out = new PrintStream(("MembersInfo\\trainers.csv"));
        for (int i = 0; i < trainers.size(); i++) {
            out.print(trainers.get(i).getNavn());
            out.print(";");
            out.print(trainers.get(i).getNumber());
            out.print(";");
            out.print(trainers.get(i).isJuniorTrainer());
            out.print(";");
            out.print(trainers.get(i).isSeniorTrainer());
            out.print("\n");
        }
    }

    void loadTrainers() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("MembersInfo\\trainers.csv"));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
            String name = input.next();
            int number = input.nextInt();
            boolean junior = input.nextBoolean();
            boolean senior = input.nextBoolean();
            trainers.add(new Trainer(name, number, junior, senior));
            if (junior == true) juniorHold.addTrainer(new Trainer(name, number, junior, senior));
            if (senior == true) seniorHold.addTrainer(new Trainer(name, number, junior, senior));
        }
    }

    void loadTeamMembers(ArrayList<MemberKonkurrence> holdMedlemmer) {

        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getAge() >= 18) seniorHold.holdMedlemmer.add(memberList.get(i));
            if (memberList.get(i).getAge() < 18) juniorHold.holdMedlemmer.add(memberList.get(i));
        }
    }

    public void printJuniorHold() {
        System.out.println("Trænere:");
        for (int i = 0; i < juniorHold.getTrainers().size(); i++) {
            System.out.println("Name: " + juniorHold.getTrainers().get(i).getNavn() + ". ID: " + juniorHold.trainers.get(i).getNumber());
        }
        System.out.println("Svømmere:");
        for (int i = 0; i < juniorHold.getHoldMedlemmer().size(); i++) {
            System.out.println("Navn: " + juniorHold.holdMedlemmer.get(i).getName() + ". ID:" + juniorHold.getHoldMedlemmer().get(i).getNumber());
        }
    }
    public void printSeniorHold() {
        System.out.println("Trænere:");
        for (int i = 0; i < seniorHold.getTrainers().size(); i++) {
            System.out.println("Name: " + seniorHold.getTrainers().get(i).getNavn() + ". ID: " + seniorHold.trainers.get(i).getNumber());
        }
        System.out.println("Svømmere:");
        for (int i = 0; i < seniorHold.getHoldMedlemmer().size(); i++) {
            System.out.println("Navn: " + seniorHold.holdMedlemmer.get(i).getName() + ". ID:" + seniorHold.getHoldMedlemmer().get(i).getNumber());
        }
    }

    void addStaevne() throws FileNotFoundException {
        System.out.println("Indtast staevnenavn:");
        String staevnenavn = gui.getString();
        System.out.println("Indtast placering:");
        String placering = gui.getString();
        System.out.println("Indtast tid:");
        System.out.println("Indtast minutter:");
        int minutter = gui.getInt();
        System.out.println("Indtast sekunder:");
        int sekunder = gui.getInt();
        LocalTime tid = LocalTime.of(0,minutter,sekunder);
        System.out.println("Indtast ID på svømmer der deltog:");
        int id = gui.getInt();
        MemberKonkurrence swimmer = null;
        for (int i = 0; i< memberList.size(); i++){
            if (id== memberList.get(i).getNumber()) swimmer= memberList.get(i);
        }
        System.out.println("Indtast disciplin:");
        String disciplin = gui.getString();
        staevner.add(new Staevne(staevnenavn,placering,tid,disciplin,swimmer.getNumber(),swimmer.getName(),swimmer.getAge()));

        PrintStream out = new PrintStream(("MembersInfo\\competitions.csv"));
        for (int i = 0; i < staevner.size(); i++) {
            out.print(staevner.get(i).getStaevneNavn());
            out.print(";");
            out.print(staevner.get(i).getPlacering());
            out.print(";");
            out.print(staevner.get(i).getTid());
            out.print(";");
            out.print(staevner.get(i).getDisciplin());
            out.print(";");
            out.print(staevner.get(i).getId());
            out.print(";");
            out.print(staevner.get(i).getNavn());
            out.print(";");
            out.print(staevner.get(i).getAlder());
            out.print("\n");
        }
    }
    
    void loadStaevner() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("MembersInfo\\competitions.csv"));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Scanner input = new Scanner(line).useDelimiter(";");
            String staevneNavn = input.next();

            String placering = input.next();

            String TidStr = input.next();
            String[] TidArrayStr = TidStr.split(":");
            int[] TidArrayInt = new int[3];
            for (int i = 0; i < TidArrayStr.length; i++) {
                TidArrayInt[i] = Integer.parseInt(TidArrayStr[i]);
            }

            LocalTime tid = LocalTime.of(TidArrayInt[0], TidArrayInt[1], TidArrayInt[2]);

            String disciplin = input.next();

            int number = input.nextInt();

            String navn = input.next();

            int alder = input.nextInt();

            staevner.add(new Staevne(staevneNavn,placering,tid,disciplin,number,navn,alder));
        }
    }

    void printStaevner(){
        System.out.println("Liste over alle stævner:\n\n");
        for (int i = 0; i< staevner.size(); i++){
            System.out.println(staevner.get(i).getStaevneNavn().toUpperCase()+" "+"\nSvømmer: "+staevner.get(i).getNavn()+".  "+"Tid:"+ staevner.get(i).getTid().format(DateTimeFormatter.ofPattern("mm:ss"))+" "+staevner.get(i).getDisciplin()+staevner.get(i).getPlacering()+"\n");

        }
    }



}