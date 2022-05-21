import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Member implements Comparable<Member> {


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private final int number;
    private final String name;
    private final int age;
    private final String passivAktiv;
    private String juniorSenior;
    private double kontingent;

    private String betalt;
    private String paymentDueDate;

    public Date getDueDate() {
        return dueDate;
    }

    public String getBetalt() {
        return betalt;
    }

    public void setBetalt(String betalt) {
        this.betalt = betalt;
    }

    private Date dueDate;

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


    public Member(int number, String name, int age, String passivAktiv,
                  String paymentDueDate,String betalt) throws ParseException {

        this.number = number;
        this.name = name;
        this.age = age;
        this.passivAktiv = passivAktiv;
        this.paymentDueDate = paymentDueDate;
        this.dueDate = sdf.parse(paymentDueDate);
        this.betalt=betalt;
        if (age < 18) kontingent = 1000;
        if (age >= 18 && age < 60) kontingent = 1600;
        if (age >= 60) kontingent = 1200;
        if (passivAktiv.equals("P")) kontingent = 500;
        if (age >= 18) this.juniorSenior = "S";
        if (age < 18) this.juniorSenior = "J";
    }

    @Override
    public int compareTo(Member o) {
        return this.getNumber()-o.getNumber();
    }
}
