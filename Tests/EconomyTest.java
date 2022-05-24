import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class EconomyTest {

    Economy economy = new Economy();


    KonkurrenceManager loadKonkurrenceManager() throws FileNotFoundException, ParseException {
        KonkurrenceManager konkurrenceManager = new KonkurrenceManager();
        konkurrenceManager.loadMemberFile();
        return konkurrenceManager;
    }
    MotionistManager loadMotionistManager() throws FileNotFoundException, ParseException {
        MotionistManager motionistManager = new MotionistManager();
        motionistManager.loadMenu();
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
        for (MemberKonkurrence member: loadKonkurrenceManager().memberListKonkurrence
             ) {
            expectedIncomeTestResult += member.getKontingent();
        }

        assertEquals(expectedIncome,expectedIncomeTestResult);
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
        for (MemberKonkurrence member: loadKonkurrenceManager().memberListKonkurrence
        ) {
            if (member.getBetalt().equals("betalt")) {
                actualIncomeTestResult += member.getKontingent();
            }
        }

        assertEquals(actualIncome, actualIncomeTestResult);
    }

}