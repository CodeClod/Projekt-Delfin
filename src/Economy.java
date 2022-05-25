import java.io.FileNotFoundException;

public class Economy {

  private int income;
KonkurrenceManager konkurrenceManager = new KonkurrenceManager();
MotionistManager   motionistManager   = new MotionistManager();

  public int getExpectedIncome(KonkurrenceManager konkurrenceManager, MotionistManager motionistManager) {
    //iterer gennem alle medlemmers balance og sout sum
    income = 0;
    for (int i = 0; i < konkurrenceManager.memberList.size(); i++) {
      income += konkurrenceManager.memberList.get(i).getKontingent();
    }
    for (int i = 0; i < motionistManager.memberList.size(); i++) {
      income += motionistManager.memberList.get(i).getKontingent();
    }
    return income;
  }

  public int getActualIncome(KonkurrenceManager konkurrenceManager, MotionistManager motionistManager) {
    income = 0;
    for (int i = 0; i < konkurrenceManager.memberList.size(); i++) {
      if (konkurrenceManager.memberList.get(i).getBetalt().equals("betalt")) {
        income += konkurrenceManager.memberList.get(i).getKontingent();
      }
    }
    for (int i = 0; i < motionistManager.memberList.size(); i++) {
      if (motionistManager.memberList.get(i).getBetalt().equals("betalt")) {
        income += motionistManager.memberList.get(i).getKontingent();
      }
    }
    return income;
  }

  void visRestance(KonkurrenceManager konkurrenceManager, MotionistManager motionistManager){
    System.out.println("Følgende medlemmer er i restance:\n");
    motionistManager.visRestance();
    konkurrenceManager.visRestance();
  }

  void betalRegning(KonkurrenceManager konkurrenceManager, MotionistManager motionistManager,GUI gui) throws FileNotFoundException {
    System.out.println("Skriv ID tal det medlem som skal betale sin regning:");
    int memberID = gui.getInt();

    for (boolean memberFound=false; !memberFound;) {
      for (int i = 0; i < konkurrenceManager.memberList.size(); i++) {
        if (konkurrenceManager.memberList.get(i).getNumber() == memberID){memberFound=true;}
      }
      for (int i = 0; i < motionistManager.memberList.size(); i++) {
        if (motionistManager.memberList.get(i).getNumber() == memberID){memberFound=true;}
      }
      if (memberFound==false) {System.out.println("ID not found. Try again"); memberID=gui.getInt();}
    }




    motionistManager.updateInfo(memberID);
    konkurrenceManager.updateInfo(memberID, "betal");
  }

}
