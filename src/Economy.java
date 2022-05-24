public class Economy {

  private int income;

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
}
