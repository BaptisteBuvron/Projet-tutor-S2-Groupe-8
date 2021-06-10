package controller.enseignant;

import Application.MainEnseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class EnseignanteEdition2Controller implements Initializable {


    @FXML
    private TextArea transcription;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void valider(ActionEvent event) {

        if (transcription.getText().trim().isEmpty() || transcription.getText() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Transcription  manquante.");
            alert.setContentText("Vous n'avez pas saisie de transcription  pour l'exercice.");
            alert.showAndWait();
        }
        else {
            MainEnseignant.exercice.getRessource().setTranscription(transcription.getText());
            MainEnseignant.stage.setScene(MainEnseignant.option);

        }
    }

    public void retour(ActionEvent event) {
        MainEnseignant.stage.setScene(MainEnseignant.edition1);

    }
}
