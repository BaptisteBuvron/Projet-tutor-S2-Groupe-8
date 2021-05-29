package model;

public class Etudiant {
    private String nom;

    public Etudiant(String nom){
        this.nom = nom;
    }

    public Etudiant() {
        super();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
