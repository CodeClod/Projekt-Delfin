import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
  private final Path mainMenu = Path.of("Files\\MainMenu.txt");

  public String mainMenu() throws IOException {
    return Files.readString(mainMenu);
  }
}
