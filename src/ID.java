import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class ID {
    public static int getID() throws FileNotFoundException {
        int ID = 0;
        Scanner fileScanner = new Scanner(new File("MembersInfo\\ID.csv"));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
            ID = input.nextInt();
            System.out.println(ID);
            PrintStream out = new PrintStream(("MembersInfo\\ID.csv"));
            out.print(ID + 1);
        }
        return ID;
    }

    static String createPaymentDate() {
        String newDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //Getting current date
        Calendar cal = Calendar.getInstance();
        //Number of Days to add
        cal.add(Calendar.DAY_OF_MONTH, 10);
        //Date after adding the days to the current date
        newDate = sdf.format(cal.getTime());
        return newDate;
    }
}