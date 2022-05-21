import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class Controller {
    private static final String TEXT_RESET = "\u001b[0m";
    private static final String TEXT_GREEN = "\u001b[32m";
    private static final String TEXT_BLUE = "\u001b[34m";
    GUI gui = new GUI();
    Music music = new Music();
    MotionistManager motionistManager = new MotionistManager();
    KonkurrenceManager konkurrenceManager = new KonkurrenceManager();
    Economy economy = new Economy();
    private boolean loop = true;

    public Controller() {
    }


    public void run() throws InterruptedException, FileNotFoundException, ParseException {

        konkurrenceManager.loadMemberFile();
        motionistManager.loadMenu();
        music.playMusic();
        Thread.sleep(2000);
        while (loop) {
            mainMenu();
        }

    }

    void printMembers() {
        ArrayList<Member> allMembers = new ArrayList<>();
        allMembers.addAll(motionistManager.memberList);
        allMembers.addAll(konkurrenceManager.memberListKonkurrence);
        allMembers.sort(Member::compareTo);


        System.out.printf("%-2s | %-5s | %-3s | %-1s | %8s", "ID:", "Navn:", "Alder:", "MedlemsType:", "Kontingent:");
        System.out.println();
        boolean loop = true;
        while (loop) {
            for (Member menuItems : allMembers) {
                System.out.printf("%-3d | %-10s | %-3d | %-1s | %8.2f", menuItems.getNumber(), menuItems.getName(), menuItems.getAge(), menuItems.getJuniorSenior(), menuItems.getKontingent());
                System.out.println();
            }
            loop = false;
        }
    }

    public void mainMenu() throws FileNotFoundException, ParseException {

        menuText();
        switch (gui.getInt()) {
            case 1 -> decideMemberType();
            case 2 -> {
                System.out.println("Skriv ID tal det medlem som skal betale sin regning:");
                int memberID = gui.getInt();
                motionistManager.updateInfo(memberID);
                konkurrenceManager.updateInfo(memberID, "betal");
            }
            case 3 -> {
                motionistManager.visRestance();
                konkurrenceManager.visRestance();
            }
            case 4 -> decideSwimUpdate();
            case 5 -> konkurrenceManager.visRekorder();
            case 6 -> economy.showExpectedIncome(konkurrenceManager, motionistManager);
            case 7 -> economy.showActualIncome(konkurrenceManager, motionistManager);

            case 8 -> {
                System.out.println("Er du sikker på at du vil lukke programmet?");
                if ("ja".equals(gui.getString().toLowerCase(Locale.ROOT))) {
                    loop = false;
                }
            }
            case 9 -> printMembers();
            case 10 -> konkurrenceManager.printBedsteRekord();
        }
    }

    public void menuText() {
        String boldON = "\033[0;1m";
        System.out.printf(boldON + TEXT_GREEN + "[%s]\n", "-".repeat(47));
        System.out.println("|     " + TEXT_BLUE + "Velkommen til svømmeklubben Delfinen!" + TEXT_GREEN + "     |" + "\n" + "|      " + TEXT_RESET + "Vælg venligst en funktion nedenfor:" + TEXT_GREEN + "      |" + "\n" + "|                                               |" + "\n" + "|" + TEXT_RESET + "1) Tilføj medlem" + TEXT_GREEN + "                               |" + "\n" + "|" + TEXT_RESET + "2) Betal regning" + TEXT_GREEN + "                               |" + "\n" + "|" + TEXT_RESET + "3) Vis restance" + TEXT_GREEN + "                                |" + "\n" + "|" + TEXT_RESET + "4) Opdater tider" + TEXT_GREEN + "                               |" + "\n" + "|" + TEXT_RESET + "5) Vis tider" + TEXT_GREEN + "                                   |" + "\n" + "|" + TEXT_RESET + "6) Vis forventet indtægt" + TEXT_GREEN + "                       |" + "\n" + "|" + TEXT_RESET + "7) Vis faktisk indtægt  " + TEXT_GREEN + "                       |" + "\n" + "|" + TEXT_RESET + "8) Exit" + TEXT_GREEN + "                                        |" + TEXT_RESET);

        System.out.printf(boldON + TEXT_GREEN + "[%s]\n", "-".repeat(47));
        String boldOff = "\033[0;0m";
        System.out.print(boldOff);
    }

    public void decideMemberType() throws FileNotFoundException, ParseException {
        System.out.println("""
                Tast 1 for at tilføje i kategorien konkurrence
                Tast 2 for at tilføje i kategorien motionist
                """);
        switch (gui.getInt()) {
            case 1 -> konkurrenceManager.addMember();
            case 2 -> motionistManager.addMember();
        }
    }

    public void decideSwimUpdate() throws FileNotFoundException {
        System.out.println("Indtast medlems ID:");
        int memberID = gui.getInt();
        System.out.println("""
                Tast 1 for opdatering af Brystsvømning
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

}
