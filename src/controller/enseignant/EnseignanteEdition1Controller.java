package controller.enseignant;

import Application.MainEnseignant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class EnseignanteEdition1Controller implements Initializable {

    @FXML
    private TextArea consigne;

    @FXML
    private TextArea aide;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void validerAide(ActionEvent event) {
        if(consigne.getText().trim().isEmpty() || consigne.getText() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Consigne  manquante.");
            alert.setContentText("Vous n'avez pas saisie de consigne pour l'exercice.");
            alert.showAndWait();
        }
        else if(aide.getText().trim().isEmpty() || aide.getText() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Aide manquante.");
            alert.setContentText("Vous n'avez pas saisie de l'aide pour l'exercice.");
            alert.showAndWait();
        }
        else {
            MainEnseignant.exercice.setAide(aide.getText());
            MainEnseignant.exercice.setConsigne(consigne.getText());
            MainEnseignant.stage.setScene(MainEnseignant.edition2);
        }
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

    public void retour(ActionEvent event) {
        MainEnseignant.stage.setScene(MainEnseignant.fichierMP4);
    }
}
