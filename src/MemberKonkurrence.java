import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MemberKonkurrence extends Member {
    
    private int[] butterFlyTime;
    private LocalDate butterFlyDate;
    private int[] rygCrawlTime;
    private LocalDate rygCrawlDate;
    private int[] crawlTime;
    private LocalDate crawlDate;
    private int[] brystTime;
    private LocalDate brystDate;

    private boolean butterFlyAktiv=false;
    private boolean rygCrawlAktiv=false;
    private boolean crawlAktiv=false;
    private boolean brystAktiv=false;


    public MemberKonkurrence(int number, String name, int age, String passivAktiv, String paymentDueDate, String betalt, int[] butterFlyTime, LocalDate butterFlyDate, int[] rygCrawlTime, LocalDate rygCrawlDate, int[] crawlTime, LocalDate crawlDate, int[] brystTime, LocalDate brystDate,boolean butterFlyAktiv, boolean rygCrawlAktiv, boolean crawlAktiv, boolean brystAktiv) throws ParseException {

        super(number, name, age, passivAktiv, paymentDueDate, betalt);
        this.butterFlyTime = butterFlyTime;
        this.butterFlyDate = butterFlyDate;
        this.rygCrawlTime = rygCrawlTime;
        this.rygCrawlDate = rygCrawlDate;
        this.crawlTime = crawlTime;
        this.crawlDate = crawlDate;
        this.brystTime = brystTime;
        this.brystDate = brystDate;
        this.butterFlyAktiv=butterFlyAktiv;
        this.rygCrawlAktiv=rygCrawlAktiv;
        this.crawlAktiv=crawlAktiv;
        this.brystAktiv=brystAktiv;
    }

    public boolean isBrystAktiv() {
        return brystAktiv;
    }

    public void setBrystAktiv(boolean brystAktiv) {
        this.brystAktiv = brystAktiv;
    }

    public boolean isButterFlyAktiv() {
        return butterFlyAktiv;
    }

    public void setButterFlyAktiv(boolean butterFlyAktiv) {
        this.butterFlyAktiv = butterFlyAktiv;
    }

    public boolean isCrawlAktiv() {
        return crawlAktiv;
    }

    public void setCrawlAktiv(boolean crawlAktiv) {
        this.crawlAktiv = crawlAktiv;
    }

    public boolean isRygCrawlAktiv() {
        return rygCrawlAktiv;
    }

    public void setRygCrawlAktiv(boolean rygCrawlAktiv) {
        this.rygCrawlAktiv = rygCrawlAktiv;
    }

    public int compareToBryst(MemberKonkurrence o) {

        return (this.getBrystTime()[0]*60000+this.getBrystTime()[1]*1000+this.getBrystTime()[2]) - (o.getBrystTime()[0]*60000+o.getBrystTime()[1]*1000+o.getBrystTime()[2]);
    }

    public int compareToCrawl(MemberKonkurrence o) {

        return (this.getCrawlTime()[0]*60000+this.getCrawlTime()[1]*1000+this.getCrawlTime()[2]) - (o.getCrawlTime()[0]*60000+o.getCrawlTime()[1]*1000+o.getCrawlTime()[2]);
    }

    public int compareToRygCrawl(MemberKonkurrence o) {

        return (this.getRygCrawlTime()[0]*60000+this.getRygCrawlTime()[1]*1000+this.getRygCrawlTime()[2]) - (o.getRygCrawlTime()[0]*60000+o.getRygCrawlTime()[1]*1000+o.getRygCrawlTime()[2]);
    }

    public int compareToButterFly(MemberKonkurrence o) {

        return (this.getButterFlyTime()[0]*60000+this.getButterFlyTime()[1]*1000+this.getButterFlyTime()[2]) - (o.getButterFlyTime()[0]*60000+o.getButterFlyTime()[1]*1000+o.getButterFlyTime()[2]);
    }

    

    public LocalDate getButterFlyDate() {
        return butterFlyDate;
    }

    public void setButterFlyDate(LocalDate butterFlyDate) {
        this.butterFlyDate = butterFlyDate;
    }
    
    public LocalDate getRygCrawlDate() {
        return rygCrawlDate;
    }

    public void setRygCrawlDate(LocalDate rygCrawlDate) {
        this.rygCrawlDate = rygCrawlDate;
    }

    
    public LocalDate getCrawlDate() {
        return crawlDate;
    }

    public void setCrawlDate(LocalDate crawlDate) {
        this.crawlDate = crawlDate;
    }

    public LocalDate getBrystDate() {
        return brystDate;
    }

    public void setBrystDate(LocalDate brystDate) {
        this.brystDate = brystDate;
    }

    public int[] getButterFlyTime() {
        return butterFlyTime;
    }

    public int[] getBrystTime() {
        return brystTime;
    }

    public int[] getCrawlTime() {
        return crawlTime;
    }

    public int[] getRygCrawlTime() {
        return rygCrawlTime;
    }

    public void setCrawlTime(int[] crawlTime) {
        this.crawlTime = crawlTime;
    }

    public void setRygCrawlTime(int[] rygCrawlTime) {
        this.rygCrawlTime = rygCrawlTime;
    }

    public void setBrystTime(int[] brystTime) {
        this.brystTime = brystTime;
    }

    public void setButterFlyTime(int[] butterFlyTime) {
        this.butterFlyTime = butterFlyTime;
    }
}
