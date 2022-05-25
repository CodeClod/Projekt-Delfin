import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class KonkurrenceManager {

    GUI gui = new GUI();
    ArrayList<MemberKonkurrence> memberList = new ArrayList<>();
    ArrayList<Trainer> trainers = new ArrayList<>();
    ArrayList<Staevne> staevner = new ArrayList<>();
    private boolean butterFlyAktivPassiv;
    private boolean rygCrawlAktivPassiv;
    private boolean crawlAktivPassiv;
    private boolean brystAktivPassiv;
    private boolean success;
    private boolean aktivOrPassiv;
    private boolean junior;
    private boolean senior;

    private Hold juniorHold = new Hold();
    private Hold seniorHold = new Hold();

    private int age;
    private int alder;
    private int arrayListIndex;
    private int min = 0;
    private int sec = 0;
    private int ms = 0;
    private int month = 0;
    private int day = 0;
    private int year = 0;
    private int x;
    private int i;
    private int medlemID;
    private int number;
    private int minutter;
    private int sekunder;
    private int mSekunder;
    private int id;

    private String paymentDueDate;
    private String betalt;
    private String j;
    private String juniorSenior;
    private String aktivPassiv;
    private String stringTid;
    private String aktivEllerpassiv;
    private String navn;
    private String s;
    private String staevnenavn;
    private String placering;
    private String line;
    private String staevneNavn;
    private String TidStr;

    LocalDate dateSetter = LocalDate.of(0, 1, 1);


    void printBedsteRekord() {
        juniorSenior = gui.getString().toUpperCase();
        while (!juniorSenior.equals("J") && !juniorSenior.equals("S")) {
            System.out.println("Skriv 'J' eller 'S'!");
            juniorSenior = gui.getString().toUpperCase();
        }
        if (juniorSenior.equals("J")) {
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
        if (juniorSenior.equals("S")) {
            System.out.println("\nBedste Senior-hold rekorder:");
            try {
                memberList.sort(MemberKonkurrence::compareToButterFly);
                System.out.println("ButterFly:");
                printHjaelpMetode("butterFly");

            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberList.sort(MemberKonkurrence::compareToRygCrawl);
                System.out.println("RygCrawl:");
                printHjaelpMetode("rygCrawl");

            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberList.sort(MemberKonkurrence::compareToCrawl);
                System.out.println("Crawl:");
                printHjaelpMetode("crawl");

            } catch (Exception E) {
                System.out.println();
            }

            try {
                memberList.sort(MemberKonkurrence::compareToBryst);
                System.out.println("Bryst:");
                printHjaelpMetode("bryst");

            } catch (Exception E) {
                System.out.println();
            }

        }
    }

    private void printHjaelpMetode(String disciplin) {
        x = 0;
        i = 0;
        int tid[] = new int[0];

        while (x < 5) {
            if (disciplin.equals("butterFly")) tid = memberList.get(i).getButterFlyTime();
            if (disciplin.equals("rygCrawl")) tid = memberList.get(i).getRygCrawlTime();
            if (disciplin.equals("crawl")) tid = memberList.get(i).getCrawlTime();
            if (disciplin.equals("bryst")) tid = memberList.get(i).getBrystTime();

            stringTid = String.valueOf((tid[0])) + ":" + String.valueOf((tid[1])) + ":" +
                    String.valueOf((tid[2]));


            if (memberList.get(i).getAge() >= 18) {
                System.out.printf("ID: %-6d Navn: %-12s Tid: %10s", memberList.get(i).getNumber(),
                        memberList.get(i).getName(), stringTid);
                System.out.println();
                x++;
            }
            i++;
        }
    }

    private void printHjaelpMetodeJ(String disciplin) {
        x = 0;
        i = 0;
        int[] tid = new int[0];

        while (x < 5) {
            if (disciplin.equals("butterFly")) tid = memberList.get(i).getButterFlyTime();
            if (disciplin.equals("rygCrawl")) tid = memberList.get(i).getRygCrawlTime();
            if (disciplin.equals("crawl")) tid = memberList.get(i).getCrawlTime();
            if (disciplin.equals("bryst")) tid = memberList.get(i).getBrystTime();

            stringTid = String.valueOf((tid[0])) + ":" +
                    String.valueOf((tid[1])) + ":" + String.valueOf((tid[2]));


            if (memberList.get(i).getAge() < 18) {
                System.out.printf("ID: %-6d Navn: %-12s Tid: %10s", memberList.get(i).getNumber(),
                        memberList.get(i).getName(), stringTid);
                System.out.println();
                x++;
            }
            i++;
        }
    }

    void addMember() throws FileNotFoundException, ParseException {


        System.out.println("Indtast navn: ");
        String name = gui.getString();
        System.out.println("Indtast alder:");
        age = gui.getInt();
        System.out.println("Indtast status(passiv/aktiv). Brug P eller A");
        String passiveActive = "";
        while (!passiveActive.equals("P") && !passiveActive.equals("A")) {
            passiveActive = gui.getString().toUpperCase(Locale.ROOT);
            if (!passiveActive.equals("P") && !passiveActive.equals("A")) System.out.println("Please write 'P' or 'A'");
        }
        paymentDueDate = ID.createPaymentDate();
        int[] emptyTime = {99, 0, 0};
        LocalDate emptyDate = LocalDate.of(1, 1, 1);

        memberList.add(new MemberKonkurrence(ID.getID(), name, age, passiveActive, paymentDueDate,
                "ikkeBetalt", emptyTime, emptyDate, emptyTime, emptyDate, emptyTime, emptyDate,
                emptyTime, emptyDate, false, false, false, false));

        updateInfo(-1, "nothing");
    }

    void updateHjaelpeMetode() {

        System.out.println("Enter minutes:");
        this.min = gui.getInt();
        System.out.println("Enter seconds:");
        this.sec = gui.getInt();
        System.out.println("Enter ms:");
        this.mSekunder = gui.getInt();

        for (boolean isSuccess = false; !isSuccess; ) {
            try {
                System.out.println("Enter year the record was set:");
                year = gui.getInt();
                System.out.println("Enter month:");
                month = gui.getInt();
                System.out.println("Enter day:");
                day = gui.getInt();
                dateSetter = LocalDate.of(year, month, day);
                isSuccess = true;
            } catch (Exception e) {
                System.out.println("Ugyldig dato. Proev igen. Tryk enter");
            }
        }

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
                out.print(min + ":" + sec + ":" + mSekunder);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setButterFlyTime(new int[]{min, sec, mSekunder});
                memberKonkurrence.setButterFlyDate(dateSetter);

            } else {
                out.print(memberKonkurrence.getButterFlyTime()[0] + ":" +
                        memberKonkurrence.getButterFlyTime()[1] + ":" + memberKonkurrence.getButterFlyTime()[2]);
                out.print(";");
                out.print(memberKonkurrence.getButterFlyDate().getDayOfMonth() + "/" +
                        memberKonkurrence.getButterFlyDate().getMonthValue() + "/" +
                        memberKonkurrence.getButterFlyDate().getYear());
            }

            out.print(";");

            if (memberKonkurrence.getNumber() == medlemID && handling.equals("rygCrawlRecord")) {
                updateHjaelpeMetode();
                out.print(min + ":" + sec + ":" + mSekunder);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setRygCrawlTime(new int[]{min, sec, mSekunder});

                memberKonkurrence.setRygCrawlDate(LocalDate.of(year, month, day));

            } else {
                out.print(memberKonkurrence.getRygCrawlTime()[0] + ":" +
                        memberKonkurrence.getRygCrawlTime()[1] + ":" + memberKonkurrence.getRygCrawlTime()[2]);
                out.print(";");
                out.print(memberKonkurrence.getRygCrawlDate().getDayOfMonth() + "/" +
                        memberKonkurrence.getRygCrawlDate().getMonthValue() + "/" +
                        memberKonkurrence.getRygCrawlDate().getYear());
            }

            out.print(";");

            if (memberKonkurrence.getNumber() == medlemID && handling.equals("crawlRecord")) {
                updateHjaelpeMetode();
                out.print(min + ":" + sec + ":" + mSekunder);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setCrawlTime(new int[]{min, sec, mSekunder});
                memberKonkurrence.setCrawlDate(dateSetter);

            } else {
                out.print(memberKonkurrence.getCrawlTime()[0] + ":" +
                        memberKonkurrence.getCrawlTime()[1] + ":" + memberKonkurrence.getCrawlTime()[2]);
                out.print(";");
                out.print(memberKonkurrence.getCrawlDate().getDayOfMonth() + "/" +
                        memberKonkurrence.getCrawlDate().getMonthValue() + "/" +
                        memberKonkurrence.getCrawlDate().getYear());
            }

            out.print(";");

            if (memberKonkurrence.getNumber() == medlemID && handling.equals("brystRecord")) {
                updateHjaelpeMetode();
                out.print(min + ":" + sec + ":" + mSekunder);
                out.print(";");
                out.print(day + "/" + month + "/" + year);
                memberKonkurrence.setBrystTime(new int[]{min, sec, mSekunder});
                memberKonkurrence.setBrystDate(dateSetter);

            } else {
                out.print(memberKonkurrence.getBrystTime()[0] + ":" + memberKonkurrence.getBrystTime()[1]
                        + ":" + memberKonkurrence.getBrystTime()[2]);
                out.print(";");
                out.print(memberKonkurrence.getBrystDate().getDayOfMonth() + "/" +
                        memberKonkurrence.getBrystDate().getMonthValue() + "/" +
                        memberKonkurrence.getBrystDate().getYear());
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
            number = input.nextInt();
            String name = input.next();
            age = input.nextInt();
            aktivPassiv = input.next();
            input.next();
            input.next();
            paymentDueDate = input.next();
            betalt = input.next();


            String butterFlyTidStr = input.next();
            String[] butterFlyTidArrayStr = butterFlyTidStr.split(":");
            int[] butterFlyTid = new int[4];
            for (int i = 0; i < butterFlyTidArrayStr.length; i++) {
                butterFlyTid[i] = Integer.parseInt(butterFlyTidArrayStr[i]);
            }

            String butterFlyDatoStr = input.next();
            String[] butterFlyDatoArrayStr = butterFlyDatoStr.split("/");
            int[] butterFlyDatoArrayInt = new int[3];

            for (int i = 0; i < butterFlyDatoArrayStr.length; i++) {
                butterFlyDatoArrayInt[i] = Integer.parseInt(butterFlyDatoArrayStr[i]);
            }

            LocalDate butterFlyDato = LocalDate.of(butterFlyDatoArrayInt[2], butterFlyDatoArrayInt[1], butterFlyDatoArrayInt[0]);


            String rygCrawlTidStr = input.next();
            String[] rygCrawlTidArrayStr = rygCrawlTidStr.split(":");
            int[] rygCrawlTid = new int[4];
            for (int i = 0; i < rygCrawlTidArrayStr.length; i++) {
                rygCrawlTid[i] = Integer.parseInt(rygCrawlTidArrayStr[i]);
            }

            String rygCrawlDatoStr = input.next();
            String[] rygCrawlDatoArrayStr = rygCrawlDatoStr.split("/");
            int[] rygCrawlDatoArrayInt = new int[3];

            for (int i = 0; i < rygCrawlDatoArrayStr.length; i++) {
                rygCrawlDatoArrayInt[i] = Integer.parseInt(rygCrawlDatoArrayStr[i]);
            }


            LocalDate rygCrawlDato = LocalDate.of(rygCrawlDatoArrayInt[2], rygCrawlDatoArrayInt[1], rygCrawlDatoArrayInt[0]);


            String crawlTidStr = input.next();
            String[] crawlTidArrayStr = crawlTidStr.split(":");
            int[] crawlTid = new int[4];
            for (int i = 0; i < crawlTidArrayStr.length; i++) {
                crawlTid[i] = Integer.parseInt(crawlTidArrayStr[i]);
            }

            String crawlDatoStr = input.next();
            String[] crawlDatoArrayStr = crawlDatoStr.split("/");
            int[] crawlDatoArrayInt = new int[3];
            for (int i = 0; i < crawlDatoArrayStr.length; i++) {
                crawlDatoArrayInt[i] = Integer.parseInt(crawlDatoArrayStr[i]);
            }


            LocalDate crawlDato = LocalDate.of(crawlDatoArrayInt[2], crawlDatoArrayInt[1], crawlDatoArrayInt[0]);


            String brystTidStr = input.next();
            String[] brystTidArrayStr = brystTidStr.split(":");
            int[] brystTid = new int[4];
            for (int i = 0; i < brystTidArrayStr.length; i++) {
                brystTid[i] = Integer.parseInt(brystTidArrayStr[i]);
            }

            String brystDatoStr = input.next();
            String[] brystDatoArrayStr = brystDatoStr.split("/");
            int[] brystDatoArrayInt = new int[3];

            for (int i = 0; i < brystDatoArrayStr.length; i++) {
                brystDatoArrayInt[i] = Integer.parseInt(brystDatoArrayStr[i]);
            }


            LocalDate brystDato = LocalDate.of(brystDatoArrayInt[2], brystDatoArrayInt[1], brystDatoArrayInt[0]);

            butterFlyAktivPassiv = Boolean.parseBoolean(input.next());
            rygCrawlAktivPassiv = Boolean.parseBoolean(input.next());
            crawlAktivPassiv = Boolean.parseBoolean(input.next());
            brystAktivPassiv = Boolean.parseBoolean(input.next());


            memberList.add(new MemberKonkurrence(number, name, age, aktivPassiv, paymentDueDate, betalt,
                    butterFlyTid, butterFlyDato, rygCrawlTid, rygCrawlDato, crawlTid, crawlDato, brystTid,
                    brystDato, butterFlyAktivPassiv, rygCrawlAktivPassiv, crawlAktivPassiv, brystAktivPassiv));
        }

    }

    void visRestance() {
        Date currentDate = new Date();

        for (MemberKonkurrence memberKonkurrence : memberList) {
            if (memberKonkurrence.getBetalt().equals("ikkeBetalt") &&
                    currentDate.compareTo(memberKonkurrence.getDueDate()) > 0) {

                System.out.println(memberKonkurrence.getName() +
                        " er i restance. ID nr:" + memberKonkurrence.getNumber());
            }


        }
    }

    void visRekorder() {
        System.out.println("Indtast ID:");
        medlemID = gui.getInt();
        success = false;
        for (MemberKonkurrence memberKonkurrence : memberList) {
            if (memberKonkurrence.getNumber() == medlemID) {

                System.out.println("Medlem " + memberKonkurrence.getName() + " har følgende rekorder:\n");

                System.out.println("ButterFlysvømning: Dato: " + memberKonkurrence.getButterFlyDate() +
                        " Rekordtid:" + memberKonkurrence.getButterFlyTime()[0] + ":" +
                        memberKonkurrence.getButterFlyTime()[1] + ":" + memberKonkurrence.getButterFlyTime()[2]);

                System.out.println("RygCrawlsvømning: Dato: " + memberKonkurrence.getRygCrawlDate() +
                        " Rekordtid:" + memberKonkurrence.getRygCrawlTime()[0] + ":" +
                        memberKonkurrence.getRygCrawlTime()[1] + ":" + memberKonkurrence.getRygCrawlTime()[2]);

                System.out.println("Crawlsvømning: Dato: " + memberKonkurrence.getCrawlDate() +
                        " Rekordtid:" + memberKonkurrence.getCrawlTime()[0] + ":" +
                        memberKonkurrence.getCrawlTime()[1] + ":" + memberKonkurrence.getCrawlTime()[2]);

                System.out.println("Brystsvømning: Dato: " + memberKonkurrence.getBrystDate() +
                        " Rekordtid:" + memberKonkurrence.getBrystTime()[0] + ":" +
                        memberKonkurrence.getBrystTime()[1] + ":" + memberKonkurrence.getBrystTime()[2]);

                success = true;
            }

        }
        if (!success) {
            System.out.println("""
                    Medlems ID kan ikke findes
                    ID er enten ikke gyldigt eller medlem er motionist
                    Proev venligst igen
                    """);
            visRekorder();
        }


    }

    void setAktivForDiscplin(String disciplin) throws FileNotFoundException {


        System.out.println("Indtast medlems ID:");
        number = gui.getInt();
        arrayListIndex = 0;


        for (boolean memberFound = false; !memberFound; ) {
            for (int i = 0; i < memberList.size(); i++) {
                if (memberList.get(i).getNumber() == number) {
                    arrayListIndex = i;
                    memberFound = true;
                }
            }
            if (memberFound == false) {
                System.out.println("ID ikke fundet, proev igen!");
                number = gui.getInt();
            }
        }


        System.out.println("Skal discplin saettes til aktiv eller passiv? (Skriv 'A' eller 'P')");
        aktivEllerpassiv = gui.getString().toUpperCase();
        aktivOrPassiv = aktivEllerpassiv.equals("A");


        if (disciplin.equals("butterFly")) {
            memberList.get(arrayListIndex).setButterFlyAktiv(aktivOrPassiv);
        }
        if (disciplin.equals("rygCrawl")) {
            memberList.get(arrayListIndex).setRygCrawlAktiv(aktivOrPassiv);
        }
        if (disciplin.equals("crawl")) {
            memberList.get(arrayListIndex).setCrawlAktiv(aktivOrPassiv);
        }
        if (disciplin.equals("bryst")) {
            memberList.get(arrayListIndex).setBrystAktiv(aktivOrPassiv);
        }
        updateInfo(number, "nothing");
    }

    void visAktiveDiscipliner() {
        System.out.println("Indtast medlems ID:");
        number = gui.getInt();
        arrayListIndex = 0;


        for (boolean memberFound = false; !memberFound; ) {
            for (int i = 0; i < memberList.size(); i++) {
                if (memberList.get(i).getNumber() == number) {
                    memberFound = true;
                    arrayListIndex = i;
                }
            }
            if (memberFound == false) {
                System.out.println("ID ikke fundet, proev igen!");
                number = gui.getInt();
            }
        }

        System.out.println(memberList.get(arrayListIndex).getName() +
                " aktivitetsstatuser paa foelgende discipliner er(true=aktiv):\nButterfly: " +
                memberList.get(arrayListIndex).isButterFlyAktiv() + "\nRygcrawl:" +
                memberList.get(arrayListIndex).isRygCrawlAktiv() + "\nCrawl:" +
                memberList.get(arrayListIndex).isCrawlAktiv() + "\nBryst:" +
                memberList.get(arrayListIndex).isBrystAktiv());

        System.out.println("Tryk Enter for at fortsaette.");
        gui.getString();

    }

    void addTrainer() throws FileNotFoundException {

        System.out.println("Indtast traeners navn:");
        navn = gui.getString();
        System.out.println("Tilfoej traener til junior hold?(J/N)");
        j = gui.getString().toUpperCase();
        junior = j.equals("J");
        System.out.println("Tilfoej traener til senior hold?(J/N)");
        s = gui.getString().toUpperCase();
        senior = s.equals("J");

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
            number = input.nextInt();
            junior = input.nextBoolean();
            senior = input.nextBoolean();
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
        System.out.println("Træenere:");
        for (int i = 0; i < juniorHold.getTrainers().size(); i++) {
            System.out.println("Navn: " + juniorHold.getTrainers().get(i).getNavn() + ". ID: " + juniorHold.trainers.get(i).getNumber());
        }
        System.out.println("Svoemmere:");
        for (int i = 0; i < juniorHold.getHoldMedlemmer().size(); i++) {
            System.out.println("Navn: " + juniorHold.holdMedlemmer.get(i).getName() + ". ID:" + juniorHold.getHoldMedlemmer().get(i).getNumber());
        }
    }

    public void printSeniorHold() {
        System.out.println("Traenere:");
        for (int i = 0; i < seniorHold.getTrainers().size(); i++) {
            System.out.println("Navn: " + seniorHold.getTrainers().get(i).getNavn() +
                    ". ID: " + seniorHold.trainers.get(i).getNumber());
        }
        System.out.println("Svoemmere:");
        for (int i = 0; i < seniorHold.getHoldMedlemmer().size(); i++) {
            System.out.println("Navn: " + seniorHold.holdMedlemmer.get(i).getName() +
                    ". ID:" + seniorHold.getHoldMedlemmer().get(i).getNumber());
        }
    }

    void addStaevne() throws FileNotFoundException {
        System.out.println("Indtast staevnenavn:");
        staevnenavn = gui.getString();
        System.out.println("Indtast placering:");
        placering = gui.getString();
        System.out.println("Indtast tid:");
        System.out.println("Indtast minutter:");
        minutter = gui.getInt();
        System.out.println("Indtast sekunder:");
        sekunder = gui.getInt();
        System.out.println("Indtast ms:");
        mSekunder = gui.getInt();
        int[] tid = {minutter, sekunder, mSekunder};
        System.out.println("Indtast ID på svoemmer, der deltog:");
        id = gui.getInt();
        MemberKonkurrence swimmer = null;
        for (int i = 0; i < memberList.size(); i++) {
            if (id == memberList.get(i).getNumber()) swimmer = memberList.get(i);
        }
        System.out.println("Indtast disciplin:");
        String disciplin = gui.getString();
        staevner.add(new Staevne(staevnenavn, placering, tid, disciplin,
                swimmer.getNumber(), swimmer.getName(), swimmer.getAge()));

        PrintStream out = new PrintStream(("MembersInfo\\competitions.csv"));
        for (int i = 0; i < staevner.size(); i++) {
            out.print(staevner.get(i).getStaevneNavn());
            out.print(";");
            out.print(staevner.get(i).getPlacering());
            out.print(";");
            out.print(staevner.get(i).getTid()[0] + ":" + staevner.get(i).getTid()[1] + ":" + staevner.get(i).getTid()[2]);
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
            line = fileScanner.nextLine();
            Scanner input = new Scanner(line).useDelimiter(";");
            staevneNavn = input.next();

            placering = input.next();

            TidStr = input.next();
            String[] TidArrayStr = TidStr.split(":");
            int[] tid = new int[3];
            for (int i = 0; i < TidArrayStr.length; i++) {
                tid[i] = Integer.parseInt(TidArrayStr[i]);
            }


            String disciplin = input.next();

            number = input.nextInt();

            navn = input.next();

            alder = input.nextInt();

            staevner.add(new Staevne(staevneNavn, placering, tid, disciplin, number, navn, alder));
        }
    }

    void printStaevner() {
        System.out.println("Liste over alle staevner:\n\n");
        System.out.println("Staevne:                       Navn:                 Tid:(m:s:ms) Disciplin:   " +
                "Placering:      ");
        for (int i = 0; i < staevner.size(); i++) {
            // System.out.println(staevner.get(i).getStaevneNavn().toUpperCase()+" "+"\nSvømmer: "+staevner.get(i).getNavn()+".  "+"Tid:"+ staevner.get(i).getTid()[0]+":"+staevner.get(i).getTid()[1]+":"+staevner.get(i).getTid()[2]+" "+staevner.get(i).getDisciplin()+" "+staevner.get(i).getPlacering()+"\n");
            System.out.printf("%-30S | %-20s | %02d:%02d:%-2d | %-10s | %s", staevner.get(i).getStaevneNavn(),
                    staevner.get(i).getNavn(), staevner.get(i).getTid()[0], staevner.get(i).getTid()[1],
                    staevner.get(i).getTid()[2], staevner.get(i).getDisciplin(), staevner.get(i).getPlacering());
            System.out.println();
        }
    }


}