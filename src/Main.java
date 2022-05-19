import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, ParseException {

        KonkurrenceManager konkurrenceManager = new KonkurrenceManager();
        GUI gui = new GUI();
        int tal = 0;
        konkurrenceManager.loadMemberFile();
            while (1 == 1) {
                System.out.println("Tast 1 for at tilføje et medlem. Tast 2 for medlemsbetaling. Tast 3 for at vise medlemmer i restance. Tast 4 for at opdatere rekorder. Tast 5 for at se rekorder.");

            int menu = gui.getInt();

            switch (menu) {
                case 1:
                    konkurrenceManager.addMember();
                    break;
                case 2:
                {
                    System.out.println("Indtast medlems ID på det medlem er skal betale:"); konkurrenceManager.updateInfo(gui.getInt(), "betal");}
                break;
                case 3:
                    konkurrenceManager.visRestance();
                    break;
                case 4: {
                    System.out.println("Indtast medlems ID:");
                    tal = gui.getInt();
                    System.out.println("Du har følgende valgmuligheder: Tast 1: Opdater Butterfly rekord. Tast 2: Opdater Rygcrawl rekord. Tast 3: Opdater Crawl rekord. Tast 4: Opdater Bryst rekord");
                    switch (gui.getInt()) {
                        case 1:
                            konkurrenceManager.updateInfo(tal, "butterFlyRecord");
                            break;
                        case 2:
                            konkurrenceManager.updateInfo(tal, "rygCrawlRecord");
                            break;
                        case 3:
                            konkurrenceManager.updateInfo(tal, "crawlRecord");
                            break;
                        case 4:
                            konkurrenceManager.updateInfo(tal, "brystRecord");
                            break;

                }
                    break;
                }
                case 5: konkurrenceManager.visRekorder();
            }


        }


/*System.out.println(konkurrenceManager.memberList.get(0).getName());

        System.out.println(konkurrenceManager.memberList.get(0).getButterFlyTime());
        System.out.println(konkurrenceManager.memberList.get(0).getButterFlyDate());

        System.out.println(konkurrenceManager.memberList.get(0).getRygCrawlTime());
        System.out.println(konkurrenceManager.memberList.get(0).getRygCrawlDate());

        System.out.println(konkurrenceManager.memberList.get(0).getCrawlTime());
        System.out.println(konkurrenceManager.memberList.get(0).getCrawlDate());


        System.out.println(konkurrenceManager.memberList.get(0).getBrystTime());
        System.out.println(konkurrenceManager.memberList.get(0).getBrystDate());

        konkurrenceManager.updateInfo(25,"");

        */

        //update virker ikke men load virker..
        //update genudprinter datoer som å-m-d men skal udskrive det som å/m/s
        // Controller controller = new Controller();
        // controller.run();

    }
}
