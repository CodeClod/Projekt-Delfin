import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Locale;

public class Controller {
  GUI gui = new GUI();
  Music music = new Music();
  MotionistManager motionistManager = new MotionistManager();
  ID id = new ID();
  KonkurrenceManager konkurrenceManager = new KonkurrenceManager();


  private boolean loop = true;
  private final String boldON = "\033[0;1m";
  private final String boldOff = "\033[0;0m";
  private static final String TEXT_RESET = "\u001b[0m";
  private static final String TEXT_GREEN = "\u001b[32m";
  private static final String TEXT_BLUE = "\u001b[34m";

  public Controller() throws FileNotFoundException {
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

  public void mainMenu() throws FileNotFoundException, ParseException {

    menuText();
    switch (gui.getInt()) {

      case 1 -> decideMemberType();
      case 2 -> {
        System.out.println("Skriv ID tal det medlem som skal betale sin regning:");
        motionistManager.updateInfo(gui.getInt());
      }
      case 3 -> motionistManager.visRestance();
      case 4 -> decideSwimUpdate();
      case 5 -> konkurrenceManager.visRekorder();
      case 6 -> {
        System.out.println("Er du sikker på at du vil lukke programmet?");
        if ("ja".equals(gui.getString().toLowerCase(Locale.ROOT))) {
          loop = false;
        }
      }
    }
  }

  public void menuText() {
    System.out.printf(boldON + TEXT_GREEN + "[%s]\n", "-".repeat(47));
    System.out.println("|     " + TEXT_BLUE + "Velkommen til svømmeklubben Delfinen!" + TEXT_GREEN + "     |" + "\n"
        + "|      " + TEXT_RESET + "Vælg venligst en funktion nedenfor:" + TEXT_GREEN + "      |" + "\n" +
        "|                                               |" + "\n" +
        "|" + TEXT_RESET + "1) Tilføj medlem" + TEXT_GREEN + "                               |" + "\n" +
        "|" + TEXT_RESET + "2) Betal regning" + TEXT_GREEN + "                               |" + "\n" +
        "|" + TEXT_RESET + "3) Vis restance" + TEXT_GREEN + "                                |" + "\n" +
        "|" + TEXT_RESET + "4) Opdater tider" + TEXT_GREEN + "                               |" + "\n" +
        "|" + TEXT_RESET + "5) Vis tider" + TEXT_GREEN + "                                   |" + "\n" +
        "|" + TEXT_RESET + "6) Exit" + TEXT_GREEN + "                                        |" + TEXT_RESET);

    System.out.printf(boldON + TEXT_GREEN + "[%s]\n", "-".repeat(47));
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

  public void decideSwimUpdate() throws FileNotFoundException, ParseException {
    System.out.println("""
        Tast 1 for opdatering af Butterfly
        Tast 2 for opdatering af Crawl
        Tast 3 for opdatering af Rygcrawl
        Tast 4 for opdatering af Brystsvømning
        """);
    switch (gui.getInt()) {
      case 1 -> konkurrenceManager.updateInfo(gui.getInt(), "butterFlyRecord");
      case 2 -> konkurrenceManager.updateInfo(gui.getInt(), "crawlRecord");
      case 3 -> konkurrenceManager.updateInfo(gui.getInt(), "rygCrawlRecord");
      case 4 -> konkurrenceManager.updateInfo(gui.getInt(), "brystRecord");


    }
  }

}
