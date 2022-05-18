import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, ParseException {

KonkurrenceManager konkurrenceManager = new KonkurrenceManager();

konkurrenceManager.loadMemberFile();

System.out.println(konkurrenceManager.memberList.get(0).getName());

        System.out.println(konkurrenceManager.memberList.get(0).getButterFlyTime());
        System.out.println(konkurrenceManager.memberList.get(0).getButterFlyDate());

        System.out.println(konkurrenceManager.memberList.get(0).getRygCrawlTime());
        System.out.println(konkurrenceManager.memberList.get(0).getRygCrawlDate());

        System.out.println(konkurrenceManager.memberList.get(0).getCrawlTime());
        System.out.println(konkurrenceManager.memberList.get(0).getCrawlDate());


        System.out.println(konkurrenceManager.memberList.get(0).getBrystTime());
        System.out.println(konkurrenceManager.memberList.get(0).getBrystDate());


        //update virker ikke men load virker..
        //update genudprinter datoer som å-m-d men skal udskrive det som å/m/s
       // Controller controller = new Controller();
       // controller.run();

    }
}
