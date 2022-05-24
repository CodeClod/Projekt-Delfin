import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class Controller {
//  private static final String TEXT_RESET = "\u001B[0m";
//  public static final String RESET = "\033[0m";
//  private static final String TEXT_GREEN = "\u001b[32m";
//  private static final String TEXT_BLUE = "\u001b[34m";
    GUI gui                               = new GUI();

    Music music                           = new Music();
    MotionistManager motionistManager     = new MotionistManager();
    KonkurrenceManager konkurrenceManager = new KonkurrenceManager();
    Economy economy                       = new Economy();
    private boolean loop                  =  true;

    String menuString                     =  null;

    int menuInt                           =  0;
    public      Controller() {
    }
    void        loadFiles() throws FileNotFoundException, ParseException {
        konkurrenceManager.loadMemberFileK();
        motionistManager.loadMemberFileM();
        konkurrenceManager.loadTeamMembers(konkurrenceManager.memberList);
        konkurrenceManager.loadTrainers();
        konkurrenceManager.loadStaevner();
        }
    public void run() throws InterruptedException, FileNotFoundException, ParseException {
        loadFiles();
        while (loop)
        {
            mainMenu();
        }
    }
    void        printMembers() {
        ArrayList<Member> allMembers = new ArrayList<>();
        allMembers.addAll(motionistManager.memberList);
        allMembers.addAll(konkurrenceManager.memberList);
        allMembers.sort(Member::compareTo);


        System.out.printf("%-2s | %-5s | %-3s | %-1s | %8s", "ID:", "Navn:", "Alder:", "MedlemsType:", "Kontingent:");
        System.out.println();
        boolean loop = true;
        while (loop) {
            for (Member member : allMembers) {
                System.out.printf("%-3d | %-19s | %-3d | %-1s | %8.2f", member.getNumber(), member.getName(), member.getAge(), member.getJuniorSenior(), member.getKontingent());
                System.out.println();
            }
            loop = false;
        }
    }
    public void mainMenu()         throws FileNotFoundException, ParseException {

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
                System.out.println("Følgende medlemmer er i restance:\n");
                motionistManager.visRestance();
                konkurrenceManager.visRestance();
                System.out.println("Tryk Enter for at fortsætte:");
                gui.getString();
            }
            case 4 -> decideSwimUpdate();
            case 5 -> {konkurrenceManager.visRekorder();
                System.out.println("Tryk Enter for at gå tilbage til menuen.");
            gui.getString();}
            case 6 -> {
                System.out.println(economy.getExpectedIncome(konkurrenceManager, motionistManager) + " kr.");
                System.out.println("Tryk Enter for at gå tilbage til menuen.");
                gui.getString();}

            case 7 -> {
                System.out.println(economy.getActualIncome(konkurrenceManager, motionistManager) + " kr. ");
                System.out.println("Tryk Enter for at gå tilbage til menuen.");
                gui.getString();}
            case 8 -> {
                printMembers();System.out.println("Tryk Enter for at gå tilbage til menuen.");
                gui.getString();}
            case 9 -> {

                System.out.println("Vis (J)unior rekorder eller vis (S)enior rekorder?(Tast 'J' eller 'S')");

                konkurrenceManager.printBedsteRekord();
                System.out.print("Tryk Enter for at gå tilbage til menuen.");
                gui.getString();}
             case 10 -> {
                 System.out.println("Indtast et af de følgende tal for at ændre aktivitetsstatus på discipliner: \n1: Butterfly. 2: Rygcrawl. 3: Crawl. 4: Bryst.");
                 int disciplin = gui.getInt();
                 String disciplinString="";
                 if (disciplin==1) disciplinString="butterFly";
                 if (disciplin==2) disciplinString="rygCrawl";
                 if (disciplin==3) disciplinString="crawl";
                 if (disciplin==4) disciplinString="bryst";
                 konkurrenceManager.setAktivForDiscplin(disciplinString);
             }
            case 11 -> {konkurrenceManager.visAktiveDiscipliner();
                System.out.println("Tryk enter for at fortsætte:"); gui.getString();}

            case 12 -> konkurrenceManager.addTrainer();

            case 13 -> {
                System.out.println("Vis Junior hold eller Senior hold?(Skriv J eller S)");
                String JS = gui.getString().toUpperCase();
                boolean menu = true;
                while (menu){
                switch (JS) {
                    case "J": konkurrenceManager.printJuniorHold();
                    menu=false;
                    break;
                    case "S": konkurrenceManager.printSeniorHold();
                    menu=false;
                    break;
                    default:
                    {System.out.println("Skriv S eller J!");JS = gui.getString().toUpperCase();}
                }
                }
                System.out.println("Tryk enter for at fortsætte:"); gui.getString();
            }

            case 14 -> konkurrenceManager.addStaevne();

            case 15 -> {konkurrenceManager.printStaevner();
                System.out.println("Tryk enter for at fortsætte:"); gui.getString();}

            case 16 -> removeMember();

            case 17 -> {
                System.out.println("Er du sikker på at du vil lukke programmet?");
                if ("ja".equals(gui.getString().toLowerCase(Locale.ROOT))) {
                    loop = false;
                }
            }
        }
    }
    public void menuText(){
    System.out.println("""
            Velkommen til svømmeklubben Delfinen!
            Vælg venligst en funktion nedenfor:
            
            1)  Tilføj medlem.
            2)  Betal regning
            3)  Vis medlemmer i restance
            4)  Opdater rekord-tider
            5)  Vis rekord tider for specifikt medlem
            6)  Vis forventet indtægt
            7)  Vis faktisk indtægt
            8)  Vis liste over alle medlemmer  
            9)  Vis top-5 rekord-tider for hele klubben
            10) Ændre aktivitetstatus for discipliner for medlem
            11) Vis aktive disciplin for medlem
            12) Tilføj træner
            13) Vis Junior eller Senior Hold Lineup og træner
            14) Tilføj stævne
            15) Vis stævner
            16) Fjern medlem
            17) Exit
            """);

}
    /*public void menuText() {
        String boldON = "\033[0;1m";
        System.out.printf(boldON + TEXT_GREEN + "[%s]\n", "-".repeat(47));
        System.out.println("|     " + TEXT_BLUE + "Velkommen til svømmeklubben Delfinen!" + TEXT_GREEN + "     " +
                "|" + "\n" + "|      " + TEXT_RESET + "Vælg venligst en funktion nedenfor:" + TEXT_GREEN + "      " +
                "|" + "\n" + "|                                               " +
                "|" + "\n" + "|" + TEXT_RESET + "1) Tilføj medlem" + TEXT_GREEN + "                               " +
                "|" + "\n" + "|" + TEXT_RESET + "2) Betal regning" + TEXT_GREEN + "                               " +
                "|" + "\n" + "|" + TEXT_RESET + "3) Vis restance" + TEXT_GREEN + "                                " +
                "|" + "\n" + "|" + TEXT_RESET + "4) Opdater tider" + TEXT_GREEN + "                               " +
                "|" + "\n" + "|" + TEXT_RESET + "5) Vis tider" + TEXT_GREEN + "                                   " +
                "|" + "\n" + "|" + TEXT_RESET + "6) Vis forventet indtægt" + TEXT_GREEN + "                       " +
                "|" + "\n" + "|" + TEXT_RESET + "7) Vis faktisk indtægt  " + TEXT_GREEN + "                       " +
                "|" + "\n" + "|" + TEXT_RESET + "8) Vis liste over alle medlemmer  " + TEXT_GREEN + "             " +
                "|" + "\n" + "|" + TEXT_RESET + "9) Vis klubbens bedste rekorder  " + TEXT_GREEN + "              " +
                "|" + "\n" + "|" + TEXT_RESET + "10) Exit" + TEXT_GREEN + "                                       " +
                "|" + TEXT_RESET);

        System.out.printf(boldON + TEXT_GREEN + "[%s]\n", "-".repeat(47));
        String boldOff = "\033[0;0m";
        System.out.print(boldOff);
    }*/
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
    void        removeMember()     throws FileNotFoundException {
        System.out.println("Indtast medlems ID på det medlem der skal fjernes.");
        int number=gui.getInt();
        int arrayIntIndex=-1;
        int mK=-1;
        
        for (int i = 0; i<konkurrenceManager.memberList.size(); i++){
            {
            if (konkurrenceManager.memberList.get(i).getNumber()==number){ arrayIntIndex=i;mK=0;}
        }
        }
        for (int i = 0; i<motionistManager.memberList.size(); i++){
            {
                if (motionistManager.memberList.get(i).getNumber()==number){ arrayIntIndex=i;mK=1;}
            }
        }

        if (mK==0){konkurrenceManager.memberList.remove(arrayIntIndex);}
        if (mK==1){motionistManager.memberList.remove(arrayIntIndex);}

        motionistManager.updateInfo(-1);
        konkurrenceManager.updateInfo(-1,"nothing");

    }
}



