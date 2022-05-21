import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MemberKonkurrence extends Member {


    private LocalTime butterFlyTime;
    private LocalDate butterFlyDate;
    private LocalTime rygCrawlTime;
    private LocalDate rygCrawlDate;
    private LocalTime crawlTime;
    private LocalDate crawlDate;
    private LocalTime brystTime;
    private LocalDate brystDate;

    public MemberKonkurrence(int number, String name, int age, String passivAktiv, String paymentDueDate, String betalt, LocalTime butterFlyTime, LocalDate butterFlyDate, LocalTime rygCrawlTime, LocalDate rygCrawlDate, LocalTime crawlTime, LocalDate crawlDate, LocalTime brystTime, LocalDate brystDate) throws ParseException {

        super(number, name, age, passivAktiv, paymentDueDate, betalt);
        this.butterFlyTime = butterFlyTime;
        this.butterFlyDate = butterFlyDate;
        this.rygCrawlTime = rygCrawlTime;
        this.rygCrawlDate = rygCrawlDate;
        this.crawlTime = crawlTime;
        this.crawlDate = crawlDate;
        this.brystTime = brystTime;
        this.brystDate = brystDate;
    }

    public int compareToBryst(MemberKonkurrence o) {

        return this.getBrystTime().toSecondOfDay() - o.getBrystTime().toSecondOfDay();
    }

    public int compareToCrawl(MemberKonkurrence o) {

        return this.getCrawlTime().toSecondOfDay() - o.getCrawlTime().toSecondOfDay();

    }

    public int compareToRygCrawl(MemberKonkurrence o) {

        return this.getRygCrawlTime().toSecondOfDay() - o.getRygCrawlTime().toSecondOfDay();

    }

    public int compareToButterFly(MemberKonkurrence o) {

        return this.getButterFlyTime().toSecondOfDay() - o.getButterFlyTime().toSecondOfDay();

    }

    public LocalTime getButterFlyTime() {
        return butterFlyTime;
    }

    public void setButterFlyTime(LocalTime butterFlyTime) {
        this.butterFlyTime = butterFlyTime;
    }

    public LocalDate getButterFlyDate() {
        return butterFlyDate;
    }

    public void setButterFlyDate(LocalDate butterFlyDate) {
        this.butterFlyDate = butterFlyDate;
    }

    public LocalTime getRygCrawlTime() {
        return rygCrawlTime;
    }

    public void setRygCrawlTime(LocalTime rygCrawlTime) {
        this.rygCrawlTime = rygCrawlTime;
    }

    public LocalDate getRygCrawlDate() {
        return rygCrawlDate;
    }

    public void setRygCrawlDate(LocalDate rygCrawlDate) {
        this.rygCrawlDate = rygCrawlDate;
    }

    public LocalTime getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(LocalTime crawlTime) {
        this.crawlTime = crawlTime;
    }

    public LocalDate getCrawlDate() {
        return crawlDate;
    }

    public void setCrawlDate(LocalDate crawlDate) {
        this.crawlDate = crawlDate;
    }

    public LocalTime getBrystTime() {
        return brystTime;
    }

    public void setBrystTime(LocalTime brystTime) {
        this.brystTime = brystTime;
    }

    public LocalDate getBrystDate() {
        return brystDate;
    }

    public void setBrystDate(LocalDate brystDate) {
        this.brystDate = brystDate;
    }
}
