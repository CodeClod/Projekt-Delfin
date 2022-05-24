import java.time.LocalTime;

public class Staevne {
   private String staevneNavn;
   private String placering;
   private LocalTime tid;
   private String disciplin;
   private int id;
   private String navn;

   private int alder;


    Staevne(String staevneNavn, String placering, LocalTime tid,String disciplin, int id, String navn, int alder){
        this.staevneNavn = staevneNavn;
        this.placering=placering;

        this.tid=tid;
        this.disciplin=disciplin;
        this.id=id;
        this.navn=navn;
        this.alder=alder;
    }

    public String getDisciplin() {
        return disciplin;
    }

    public LocalTime getTid() {
        return tid;
    }

    public String getPlacering() {
        return placering;
    }

    public String getStaevneNavn() {
        return staevneNavn;
    }

    public String getNavn() {
        return navn;
    }

    public int getAlder() {
        return alder;
    }

    public int getId() {
        return id;
    }
}
