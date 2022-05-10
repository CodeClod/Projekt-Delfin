import java.io.IOException;
import java.util.Locale;

public class Controller {
  GUI gui = new GUI();
  FileReader fileReader = new FileReader();
  Music music = new Music();
  private boolean loop = true;
  private final String boldON = "\033[0;1m";
  private final String boldOff = "\033[0;0m";
  private static final String TEXT_RESET = "\u001b[0m";
  private static final String TEXT_GREEN = "\u001b[32m";

  public void run() throws InterruptedException {
    music.playMusic();
    Thread.sleep(2000);
    while (loop) {
      mainMenu();
    }

  }

  public void mainMenu() {
    System.out.printf(boldON + TEXT_GREEN + "[%s]\n", "-".repeat(47));
    System.out.printf("|     " + TEXT_RESET + "Velkommen til svømmeklubben Delfinen!" + TEXT_GREEN + "     |" + "\n"
        + "|      " + TEXT_RESET + "Vælg venligst en funktion nedenfor:" + TEXT_GREEN + "      |" + "\n" +
        "|                                               |" + "\n" +
        "|" + TEXT_RESET + "1)" + TEXT_GREEN + "                                             |" + "\n" +
        "|" + TEXT_RESET + "2)" + TEXT_GREEN + "                                             |" + "\n" +
        "|" + TEXT_RESET + "3)" + TEXT_GREEN + "                                             |" + "\n" +
        "|" + TEXT_RESET + "4) Exit" + TEXT_GREEN + "                                        |" + TEXT_RESET);
    System.out.printf("\n" +TEXT_GREEN + "[%s]\n", "-".repeat(47));
    switch (gui.getInt()) {
      case 1 -> System.out.println("something");
      case 2 -> System.out.println("something else");
      case 3 -> System.out.println("more something");
      case 4 -> {
        System.out.println("Er du sikker på at du vil lukke programmet?");
        if ("ja".equals(gui.getString().toLowerCase(Locale.ROOT))) {
          loop = false;
        }
      }
    }
  }

}
