import java.util.ArrayList;

public class Hold {
    ArrayList<MemberKonkurrence> holdMedlemmer = new ArrayList<>();
    ArrayList<Trainer> trainers = new ArrayList<>();


    public ArrayList<MemberKonkurrence> getHoldMedlemmer() {
        return holdMedlemmer;
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

}