import java.time.LocalTime;

public class Staevne {
   private String staevneNavn;
   private MemberKonkurrence memberKonkurrence;
   private String placering;
   private LocalTime tid;

   private String disciplin;

    Staevne(String staevneNavn, MemberKonkurrence memberKonkurrence, String placering, LocalTime tid,String disciplin){
        this.staevneNavn = staevneNavn;
        this.placering=placering;
        this.memberKonkurrence=memberKonkurrence;
        this.tid=tid;
        this.disciplin=disciplin;
    }

    public String getDisciplin() {
        return disciplin;
    }

    public LocalTime getTid() {
        return tid;
    }

    public MemberKonkurrence getMemberKonkurrence() {
        return memberKonkurrence;
    }

    public String getPlacering() {
        return placering;
    }

    public String getStaevneNavn() {
        return staevneNavn;
    }

}
