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

    @Test
    void getExpectedIncomeTest() throws FileNotFoundException, ParseException {
        int expectedIncome = economy.getExpectedIncome(loadKonkurrenceManager(), loadMotionistManager());
        assertEquals(expectedIncome,30600);
    }

    @Test
    void getActualIncomeTest() throws FileNotFoundException, ParseException {
        int actualIncome = economy.getActualIncome(loadKonkurrenceManager(),loadMotionistManager());
        assertEquals(actualIncome, 19800);
    }

}