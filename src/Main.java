import java.io.IOException;

public class Main {




    public static void main(String[] args) throws IOException {

      Controller controller = new Controller();

      MemberListNStuff memberListNStuff = new MemberListNStuff();

      memberListNStuff.loadMenu();

      memberListNStuff.addMember();

      memberListNStuff.showMenu();



   // controller.run();



    }
}
