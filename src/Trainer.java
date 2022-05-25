public class Trainer {

    private String navn;
    private int number;
    private boolean juniorTrainer;
    private boolean seniorTrainer;


    Trainer(String navn, int number, boolean juniorTrainer, boolean seniorTrainer){
        this.navn=navn;
        this.number=number;
        this.juniorTrainer = juniorTrainer;
        this.seniorTrainer = seniorTrainer;
    }

    public boolean isJuniorTrainer() {
        return juniorTrainer;
    }

    public boolean isSeniorTrainer() {
        return seniorTrainer;
    }

    public void setJuniorTrainer(boolean juniorTrainer) {
        this.juniorTrainer = juniorTrainer;
    }

    public void setSeniorTrainer(boolean seniorTrainer) {
        this.seniorTrainer = seniorTrainer;
    }

    public int getNumber() {
        return number;
    }

    public String getNavn() {
        return navn;
    }
}
