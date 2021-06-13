package controller.enseignant;

import Application.MainEnseignant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Exercice;
import model.ModeEntrainement;
import model.ModeEvaluation;
import model.Ressource.Audio;
import model.Ressource.Image;
import model.Ressource.Video;

import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class EnseignanteOptionController implements Initializable {

    @FXML
    private ToggleGroup modeExercice;

    @FXML
    private TextField limiteTemps;

    @FXML
    private CheckBox motIncomplet;

    @FXML
    private ToggleGroup lettresMinimum;

    @FXML
    private CheckBox affichageMotsDecouverts;

    @FXML
    private CheckBox affichageSolution;

    @FXML
    private TextField occultation;

    @FXML
    private CheckBox sensibiliteCasse;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void valid(ActionEvent event) {
        boolean error = false;
        if (occultation.getText().trim().isEmpty() || occultation.getText() == null || occultation.getText().length() > 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Caractère d'occultation  manquant.");
            alert.setContentText("Vous n'avez pas saisie de caractère d'occultation pour l'exercice.");
            alert.showAndWait();
            error = true;
        } else {
            Exercice copieExercice = MainEnseignant.exercice;
            switch (((RadioButton) modeExercice.getSelectedToggle()).getText()) {
                case "Mode Entrainement":
                    MainEnseignant.exercice = new ModeEntrainement();
                    switch (((RadioButton) lettresMinimum.getSelectedToggle()).getText()) {
                        case "2 lettres minimum":
                            ((ModeEntrainement) MainEnseignant.exercice).setLettresMini(2);
                            break;
                        case "3 lettres minimum":
                            ((ModeEntrainement) MainEnseignant.exercice).setLettresMini(3);
                            break;
                        default:
                            break;
                    }

                    if (affichageMotsDecouverts.isSelected()) {
                        ((ModeEntrainement) MainEnseignant.exercice).setAffichageTempsReel(true);
                    } else {
                        ((ModeEntrainement) MainEnseignant.exercice).setAffichageTempsReel(false);

                    }
                    if (affichageSolution.isSelected()) {
                        ((ModeEntrainement) MainEnseignant.exercice).setAffichageSolution(true);

                    } else {
                        ((ModeEntrainement) MainEnseignant.exercice).setAffichageSolution(false);

                    }

                    break;
                case "Mode Evaluation":
                    MainEnseignant.exercice = new ModeEvaluation();
                    if (limiteTemps.getText().matches("^[1-9]\\d*$")){
                        ((ModeEvaluation) MainEnseignant.exercice).setTempsAutorise(Integer.parseInt(limiteTemps.getText()));

                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Attention !");
                        alert.setHeaderText("Temps autorisé incorect.");
                        alert.setContentText("Vous n'avez pas saisie un temps autorisé correct.");
                        alert.showAndWait();
                        error = true;
                    }
                    break;
            }
            if (!error){
                MainEnseignant.exercice.setAide(copieExercice.getAide());
                MainEnseignant.exercice.setConsigne(copieExercice.getConsigne());
                MainEnseignant.exercice.setName(copieExercice.getName());
                MainEnseignant.exercice.setCaractereOcculation(copieExercice.getCaractereOcculation());
                MainEnseignant.exercice.setRessource(copieExercice.getRessource());
                MainEnseignant.exercice.setCaractereOcculation(occultation.getText());
                if (sensibiliteCasse.isSelected()){
                    MainEnseignant.exercice.setSensibiliteCase(true);
                }
                else {
                    MainEnseignant.exercice.setSensibiliteCase(false);
                }
                MainEnseignant.stage.setScene(MainEnseignant.sauvegarder);
            }


        }
    }

    public void retour(ActionEvent event) {
        MainEnseignant.stage.setScene(MainEnseignant.edition2);

    }

    public void tutoriel(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention !");
        alert.setHeaderText("Fonctionnalité en cours de développement.");
        alert.setContentText("Le tutoriel n'est pas encore disponible. Veuillez patienter");
        alert.showAndWait();
    }

    public void closeApplication(){
        Platform.exit();
        System.exit(0);
    }
}
