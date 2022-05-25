import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

public class Controller {
    private static final String TEXT_RESET = "\u001B[0m";
    private static final String RESET = "\033[0m";
    private static final String TEXT_GREEN = "\u001b[32m";
    private static final String TEXT_BLUE = "\u001b[34m";
    private boolean loop = true;
    GUI gui = new GUI();
    Music music = new Music();
    MotionistManager motionistManager = new MotionistManager();
    KonkurrenceManager konkurrenceManager = new KonkurrenceManager();
    Economy economy = new Economy();

    public Controller() throws FileNotFoundException {
    }


    public void run() throws FileNotFoundException, ParseException {
        loadFiles();
        music.playMusic();
        while (loop) {
            mainMenu();
        }
    }

    void loadFiles() throws FileNotFoundException, ParseException {
        konkurrenceManager.loadMemberFileK();
        motionistManager.loadMemberFileM();
        konkurrenceManager.loadTeamMembers(konkurrenceManager.memberList);
        konkurrenceManager.loadTrainers();
        konkurrenceManager.loadStaevner();
    }


    public void mainMenu() throws FileNotFoundException, ParseException {

        menuText();
        switch (gui.getInt()) {
            case 1 -> generalMemberSubMenu();
            case 2 -> economySubMenu();
            case 3 -> konkurrenceSubMenu();
            case 4 -> {
                System.out.println("""
                   Er du sikker paa at du vil lukke programmet?
                   1) Ja
                   2) Nej
                   """);
                if (gui.getInt() == 1) {
                    loop = false;
                }
            }
            default -> {
                System.out.println("Venligst skriv et tal mellem 1-5! Tryk enter for at fortsaette.");
                gui.getString();
            }
        }
    }

    void economySubMenu() throws FileNotFoundException, ParseException {
        System.out.println("""
                Oekonomi submenu:
                               
                1) Vis forventet indtaegt
                2) Vis faktisk indtaegt
                3) Vis medlemmer i restance
                4) Betal regning
                5) Gaa til hovedmenu
                 """);
        for (boolean isSuccess = false; !isSuccess; ) {
            switch (gui.getInt()) {
                case 1 -> {
                    System.out.println("Forventet indtaegt: " +
                            economy.getExpectedClubIncome(konkurrenceManager, motionistManager));
                    System.out.println("Tryk enter for at fortsaette: ");
                    gui.getString();
                    isSuccess = true;
                }
                case 2 -> {
                    System.out.println("Faktisk indtaegt: " +
                            economy.getActualClubIncome(konkurrenceManager, motionistManager));
                    System.out.println("Tryk enter for at fortsaette: ");
                    gui.getString();
                    isSuccess = true;
                }
                case 3 -> {
                    economy.visRestance(konkurrenceManager, motionistManager, gui);
                    isSuccess = true;
                }
                case 4 -> {
                    economy.betalRegning(konkurrenceManager, motionistManager, gui);
                    isSuccess = true;
                }
                case 5 -> mainMenu();
            }
            if (!isSuccess) System.out.println("Fokert input. Skriv 1-4.");
        }

    }

    void generalMemberSubMenu() throws FileNotFoundException, ParseException {
        System.out.println("""
                Overordnet medlemshaandterings submenu:
                                
                1) Tilfoej medlem
                2) Fjern medlem
                3) Vis alle medlemmer
                4) Gaa til hovedmenu
                """);
        for (boolean isSuccess = false; !isSuccess; ) {
            switch (gui.getInt()) {
                case 1 -> {
                    decideMemberType();
                    isSuccess = true;
                }

                case 2 -> {
                    removeMember();
                    isSuccess = true;
                }

                case 3 -> {
                    printMembers();
                    System.out.println("Tryk Enter for at gaa tilbage til menuen.");
                    gui.getString();
                    isSuccess = true;
                }
                case 4 -> mainMenu();
            }
            if (!isSuccess) System.out.println("Venligst skriv 1,2 eller 3");
        }
    }

    public void decideMemberType() throws FileNotFoundException, ParseException {

        System.out.println("""
                1) Tilfoej i kategorien konkurrence
                2) Tilfoej i kategorien motionist
                3) Gaa til hovedmenu
                """);
        for (boolean isSuccess = false; !isSuccess; ) {
            switch (gui.getInt()) {
                case 1 -> {
                    konkurrenceManager.addMember();
                    isSuccess = true;
                }
                case 2 -> {
                    motionistManager.addMember();
                    isSuccess = true;
                }
                case 3 -> mainMenu();
            }
            System.out.println("skriv venligst et gyldigt tal!");
        }

    }

    public void removeMember() throws FileNotFoundException {
        System.out.println("Indtast medlems ID på det medlem, der skal fjernes.");
        int number = gui.getInt();
        int arrayIntIndex = -1;
        int motionistOrKonkurrence = -1;

        for (int i = 0; i < konkurrenceManager.memberList.size(); i++) {
            {
                if (konkurrenceManager.memberList.get(i).getNumber() == number) {
                    arrayIntIndex = i;
                    motionistOrKonkurrence = 0;
                }
            }
        }
        for (int i = 0; i < motionistManager.memberList.size(); i++) {
            {
                if (motionistManager.memberList.get(i).getNumber() == number) {
                    arrayIntIndex = i;
                    motionistOrKonkurrence = 1;
                }
            }
        }

        if (motionistOrKonkurrence == 0) {
            konkurrenceManager.memberList.remove(arrayIntIndex);
        }
        if (motionistOrKonkurrence == 1) {
            motionistManager.memberList.remove(arrayIntIndex);
        }

        motionistManager.updateInfo(-1);
        konkurrenceManager.updateInfo(-1, "nothing");

    }

    public void printMembers() {
        ArrayList<Member> allMembers = new ArrayList<>();
        allMembers.addAll(motionistManager.memberList);
        allMembers.addAll(konkurrenceManager.memberList);
        allMembers.sort(Member::compareTo);


        System.out.printf("%-2s | %-5s | %-3s | %-1s | %8s", "ID:", "Navn:", "Alder:", "MedlemsType:", "Kontingent:");
        System.out.println();
        boolean loop = true;
        while (loop) {
            for (Member member : allMembers) {
                System.out.printf("%-3d | %-19s | %-3d | %-1s | %8.2f", member.getNumber(), member.getName(),
                        member.getAge(), member.getJuniorSenior(), member.getKontingent());
                System.out.println();
            }
            loop = false;
        }
    }

    void konkurrenceSubMenu() throws FileNotFoundException, ParseException {
        System.out.println("""
                Konkurrence Under-menu:
                                
                1)  Opdater rekordtider for specifikt medlem
                2)  Opdater aktivitetsstatus for specifikt medlem
                3)  Tilfoej traener
                4)  Tilfoej staevne
                5)  Vis top 5 rekorder for junior eller senior, inden for hver svoemmediciplin
                6)  Vis hold
                7)  Vis Staevner
                8)  Vis aktive discipliner for et medlem
                9)  Vis rekorder for et specifikt medlem
                10) Gaa til hovedmenu
                """);
        for (boolean isSuccess = false; !isSuccess; ) {
            switch (gui.getInt()) {
                case 1 -> {
                    updateRecord();
                    isSuccess = true;
                }

                case 2 -> {
                    System.out.println("Indtast et af de foelgende tal for at aendre aktivitetsstatus på discipliner:" +
                            " \n1: Butterfly. 2: Rygcrawl. 3: Crawl. 4: Bryst.");
                    int diciplin = gui.getInt();
                    while (diciplin != 1 && diciplin != 2 && diciplin != 3 && diciplin != 4) {
                        System.out.println("Fokert input. Indtast et af de følgende tal for at aendre aktivitetsstatus"
                                + " på discipliner: \n1: Butterfly. 2: Rygcrawl. 3: Crawl. 4: Bryst.");
                        diciplin = gui.getInt();
                    }

                    String disciplinString = "";
                    if (diciplin == 1) disciplinString = "butterFly";
                    if (diciplin == 2) disciplinString = "rygCrawl";
                    if (diciplin == 3) disciplinString = "crawl";
                    if (diciplin == 4) disciplinString = "bryst";
                    konkurrenceManager.setAktivForDiscplin(disciplinString);
                    isSuccess = true;
                }

                case 3 -> {
                    konkurrenceManager.addTrainer();
                    isSuccess = true;
                }

                case 4 -> {
                    konkurrenceManager.addStaevne();
                    isSuccess = true;
                }

                case 5 -> {

                    System.out.println("Vis (J)unior rekorder eller vis (S)enior rekorder?(Tast 'J' eller 'S')");

                    konkurrenceManager.printBedsteRekord();
                    System.out.print("Tryk Enter for at gaa tilbage til menuen.");
                    gui.getString();
                    isSuccess = true;
                }

                case 6 -> {
                    System.out.println("Vis Junior hold eller Senior hold?(Skriv J eller S)");
                    String juniorOrSenior = gui.getString().toUpperCase();
                    boolean menu = true;
                    while (menu) {
                        switch (juniorOrSenior) {
                            case "J" -> {
                                konkurrenceManager.printJuniorHold();
                                menu = false;
                            }
                            case "S" -> {
                                konkurrenceManager.printSeniorHold();
                                menu = false;
                            }
                            default -> {
                                System.out.println("Skriv S eller J!");
                                juniorOrSenior = gui.getString().toUpperCase();
                            }
                        }
                    }
                    System.out.println("Tryk enter for at fortsætte:");
                    gui.getString();
                    isSuccess = true;
                }

                case 7 -> {
                    konkurrenceManager.printStaevner();
                    System.out.println("Tryk enter for at fortsaette:");
                    gui.getString();
                    isSuccess = true;
                }

                case 8 -> {
                    konkurrenceManager.visAktiveDiscipliner();
                    isSuccess = true;
                }

                case 9 -> {
                    konkurrenceManager.visRekorder();
                    System.out.println("Tryk Enter for at gaa tilbage til menuen.");
                    gui.getString();
                    isSuccess = true;
                }

                case 10 -> mainMenu();
            }
            if (!isSuccess) System.out.println("Venligst skriv et tal mellem 1-10.");
        }

    }

    public void updateRecord() throws FileNotFoundException {
        System.out.println("Indtast medlems ID:");
        int memberID = gui.getInt();
        for (boolean memberFound = false; !memberFound; ) {
            for (int i = 0; i < konkurrenceManager.memberList.size(); i++) {
                if (konkurrenceManager.memberList.get(i).getNumber() == memberID) {
                    memberFound = true;
                }
            }
            if (!memberFound) {
                System.out.println("ID ikke fundet, proev igen!");
                memberID = gui.getInt();
            }
        }
        System.out.println("""
                Tast 1 for opdatering af Brystsvoemning
                Tast 2 for opdatering af Butterfly
                Tast 3 for opdatering af Crawl
                Tast 4 for opdatering af Rygcrawl
                """);
        int updateRekord = gui.getInt();
        switch (updateRekord) {
            case 1 -> konkurrenceManager.updateInfo(memberID, "brystRecord");
            case 2 -> konkurrenceManager.updateInfo(memberID, "butterFlyRecord");
            case 3 -> konkurrenceManager.updateInfo(memberID, "crawlRecord");
            case 4 -> konkurrenceManager.updateInfo(memberID, "rygCrawlRecord");
        }
    }

    public void menuText() {
        String boldON = "\033[0;1m";
        System.out.printf(boldON + TEXT_GREEN + "[%s]\n", "-".repeat(47));
        System.out.println("|     " + TEXT_BLUE + "Velkommen til svoemmeklubben Delfinen!" + TEXT_GREEN + "    " +
                "|" + "\n" + "|      " + TEXT_RESET + "Vaelg venligst en funktion nedenfor:" + TEXT_GREEN + "     " +
                "|" + "\n" + "|                                               " +
                "|" + "\n" + "|" + TEXT_RESET + "1) Medlemshaandering" + TEXT_GREEN + "                           " +
                "|" + "\n" + "|" + TEXT_RESET + "2) Oekonomi" + TEXT_GREEN + "                                    " +
                "|" + "\n" + "|" + TEXT_RESET + "3) Konkurrence-relateret undermenu" + TEXT_GREEN + "             " +
                "|" + "\n" + "|" + TEXT_RESET + "4) Exit" + TEXT_GREEN + "                                        " +
                "|" + TEXT_RESET);
        System.out.printf(boldON + TEXT_GREEN + "[%s]\n", "-".repeat(47));
        String boldOff = "\033[0;0m";
        System.out.print(boldOff);
    }

}