package Model;

public class ModeEntrainement extends Exercice {
    private int lettresMini;

    private boolean affichageSolution = false;
    private boolean affichageTempsReel = false;

    public ModeEntrainement() {
        super();
    }

    public int getLettresMini() {
        return lettresMini;
    }

    public void setLettresMini(int lettresMini) {
        this.lettresMini = lettresMini;
    }


    public boolean isAffichageSolution() {
        return affichageSolution;
    }

    public void setAffichageSolution(boolean affichageSolution) {
        this.affichageSolution = affichageSolution;
    }

    public boolean isAffichageTempsReel() {
        return affichageTempsReel;
    }

    public void setAffichageTempsReel(boolean affichageTempsReel) {
        this.affichageTempsReel = affichageTempsReel;
    }
}
