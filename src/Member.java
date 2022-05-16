
public class Member {
  private final int number;
  private final String name;
  private final int age;
  private final String passivAktiv;
  private String juniorSenior;
  private double kontingent;

  public double getKontingent() {
    return kontingent;
  }

  public void setKontingent(double kontingent) {
    this.kontingent = kontingent;
  }





  public String getJuniorSenior() {
    return juniorSenior;
  }

  public void setJuniorSenior(String juniorSenior) {
    this.juniorSenior = juniorSenior;
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


  public Member(int number, String name, int age, String passivAktiv) {
    this.number = number;
    this.name = name;
    this.age = age;
    this.passivAktiv = passivAktiv;
    this.juniorSenior=juniorSenior;
    if (age<18) kontingent=1000;
    if (age>=18&&age<60) kontingent = 1600;
    if (age>=60) kontingent = 1200;
    if (passivAktiv.equals("P")) kontingent=500;
    if (age>=18) this.juniorSenior="S";
    if (age<18) this.juniorSenior="J";
  }

}
