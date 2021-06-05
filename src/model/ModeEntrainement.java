package model;

public class ModeEntrainement extends Exercice {
    private Integer lettresMini;

    private boolean affichageSolution = false;
    private boolean affichageTempsReel = false;

    public ModeEntrainement() {
        super();
    }

    public Integer getLettresMini() {
        return lettresMini;
    }

    public void setLettresMini(Integer lettresMini) {
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
