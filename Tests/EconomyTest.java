import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class EconomyTest {

    Economy economy = new Economy();


    KonkurrenceManager loadKonkurrenceManager() throws FileNotFoundException, ParseException {
        KonkurrenceManager konkurrenceManager = new KonkurrenceManager();
        konkurrenceManager.loadMemberFileK();
        return konkurrenceManager;
    }
    MotionistManager loadMotionistManager() throws FileNotFoundException, ParseException {
        MotionistManager motionistManager = new MotionistManager();
        motionistManager.loadMemberFileM();
        return motionistManager;
    }

    // Not sure if done right, maybe too much logic code in the test-method?
    @Test
    void getExpectedIncomeTest() throws FileNotFoundException, ParseException {
        int expectedIncome = economy.getExpectedIncome(loadKonkurrenceManager(), loadMotionistManager());
        int expectedIncomeTestResult = 0;
        for (Member member:loadMotionistManager().memberList
             ) {
            expectedIncomeTestResult += member.getKontingent();
        }
        for (MemberKonkurrence member: loadKonkurrenceManager().memberList
             ) {
            expectedIncomeTestResult += member.getKontingent();
        }
        assertEquals(expectedIncome,expectedIncomeTestResult, "Failed to calculate correct [EXPECTED-INCOME] ...");
    }

    @Test
    void getActualIncomeTest() throws FileNotFoundException, ParseException {
        int actualIncome = economy.getActualIncome(loadKonkurrenceManager(),loadMotionistManager());
        int actualIncomeTestResult = 0;
        for (Member member:loadMotionistManager().memberList
        ) {
            if (member.getBetalt().equals("betalt")) {
                actualIncomeTestResult += member.getKontingent();
            }
        }
        for (MemberKonkurrence member: loadKonkurrenceManager().memberList
        ) {
            if (member.getBetalt().equals("betalt")) {
                actualIncomeTestResult += member.getKontingent();
            }
        }

        assertEquals(actualIncome, actualIncomeTestResult , "Failed to calculate correct [ACTUAL-INCOME] ...");
    }

}