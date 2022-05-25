import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Member implements Comparable<Member> {


    private final int number;
    private final String name;
    private final int age;
    private final String passivAktiv;
    private final String paymentDueDate;
    private final Date dueDate;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private String juniorSenior;
    private double kontingent;
    private String betalt;

    public Member(int number, String name, int age, String passivAktiv, String paymentDueDate,
                  String betalt) throws ParseException {

        this.number = number;
        this.name = name;
        this.age = age;
        this.passivAktiv = passivAktiv;
        this.paymentDueDate = paymentDueDate;
        this.dueDate = sdf.parse(paymentDueDate);
        this.betalt = betalt;
        if (age < 18) kontingent = 1000;
        if (age >= 18 && age < 60) kontingent = 1600;
        if (age >= 60) kontingent = 1200;
        if (passivAktiv.equals("P")) kontingent = 500;
        if (age >= 18) this.juniorSenior = "S";
        if (age < 18) this.juniorSenior = "J";
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getBetalt() {
        return betalt;
    }

    public void setBetalt(String betalt) {
        this.betalt = betalt;
    }

    public double getKontingent() {
        return kontingent;
    }

    public String getJuniorSenior() {
        return juniorSenior;
    }

    public String getName() {
        return name;
    }

    public String getPassivAktiv() {
        return passivAktiv;
    }

    public int getAge() {
        return age;
    }

    public int getNumber() {
        return number;
    }

    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    @Override
    public int compareTo(Member o) {
        return this.getNumber() - o.getNumber();
    }
}
