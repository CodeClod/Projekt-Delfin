import java.io.IOException;

public class Controller {
  GUI gui = new GUI();
  FileReader fileReader = new FileReader();

  public void run() throws IOException {
    mainMenu();
  }

  public void mainMenu() throws IOException {
    System.out.println(fileReader.mainMenu());
    switch (gui.getInt()) {

    }
  }

}
