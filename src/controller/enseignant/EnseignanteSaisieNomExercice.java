package controller.enseignant;

import Application.MainEnseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Exercice;

import java.net.URL;
import java.util.ResourceBundle;

public class EnseignanteSaisieNomExercice implements Initializable {

    @FXML
    private TextField nomExercice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomExercice.setText(MainEnseignant.exercice.getName());
    }

    public void validName(ActionEvent event){
        MainEnseignant.exercice = new Exercice();
        if (nomExercice.getText().trim().isEmpty() || nomExercice.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Nom manquant.");
            alert.setContentText("Vous n'avez pas saisie de nom à l'exercice.");
            alert.showAndWait();
        }else {
            MainEnseignant.exercice.setName(nomExercice.getText());
            MainEnseignant.stage.setScene(MainEnseignant.fichierMP4);
        }
    }


}
