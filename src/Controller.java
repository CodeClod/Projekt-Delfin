import java.io.IOException;
import java.util.Locale;

public class Controller {
  GUI gui = new GUI();
  FileReader fileReader = new FileReader();
  private boolean loop = true;

  public void run() throws IOException {
    while (loop) {
      mainMenu();
    }

  }

  public void mainMenu() throws IOException {
    System.out.println(fileReader.mainMenu());
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
