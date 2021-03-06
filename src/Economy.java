import java.io.FileNotFoundException;
public class Economy {
  private int income;
  public int getExpectedClubIncome(KonkurrenceManager konkurrenceManager, MotionistManager motionistManager) {
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
  public int getActualClubIncome(KonkurrenceManager konkurrenceManager, MotionistManager motionistManager) {
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

  void visRestance(KonkurrenceManager konkurrenceManager, MotionistManager motionistManager, GUI gui){
    System.out.println("Følgende medlemmer er i restance:\n");
    motionistManager.visRestance();
    konkurrenceManager.visRestance();
    System.out.println("Tryk enter for at fortsætte.");
    gui.getString();
  }

  void betalRegning(KonkurrenceManager konkurrenceManager, MotionistManager motionistManager,
                    GUI gui) throws FileNotFoundException {
    System.out.println("Skriv ID på det medlem, som skal betale sin regning: ");
    int memberID = gui.getInt();

    for (boolean memberFound=false; !memberFound;) {
      for (int i = 0; i < konkurrenceManager.memberList.size(); i++) {
        if (konkurrenceManager.memberList.get(i).getNumber() == memberID){memberFound=true;}
      }
      for (int i = 0; i < motionistManager.memberList.size(); i++) {
        if (motionistManager.memberList.get(i).getNumber() == memberID){memberFound=true;}
      }
      if (!memberFound) {System.out.println("ID ikke fundet, proev igen!"); memberID=gui.getInt();}
    }
    motionistManager.updateInfo(memberID);
    konkurrenceManager.updateInfo(memberID, "betal");
  }

}
