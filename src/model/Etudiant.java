package model;


import java.io.Serializable;
import java.util.Objects;

public class Etudiant implements Serializable {
    private String nomExercice;
    private String nom;
    private String textTrouve;
    private int motTrouve;
    private int totalMot;




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

    public String getTextTrouve() {
        return textTrouve;
    }

    public void setTextTrouve(String textTrouve) {
        this.textTrouve = textTrouve;
    }

    public int getMotTrouve() {
        return motTrouve;
    }

    public void setMotTrouve(int motTrouve) {
        this.motTrouve = motTrouve;
    }

    public int getTotalMot() {
        return totalMot;
    }

    public void setTotalMot(int totalMot) {
        this.totalMot = totalMot;
    }

    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }

    public String getNomExercice() {
        return nomExercice;
    }
}
