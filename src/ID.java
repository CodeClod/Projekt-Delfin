import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class ID {
    public static int getID() throws FileNotFoundException {
        int ID=0;
        Scanner fileScanner = new Scanner(new File("MembersInfo\\ID.csv"));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
            ID = input.nextInt();
            System.out.println(ID);
            PrintStream out = new PrintStream(("MembersInfo\\ID.csv"));
            out.print(ID+1);
        }
        return ID;
    }
}