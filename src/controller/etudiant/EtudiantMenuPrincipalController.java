package controller.etudiant;

import Application.MainEtudiant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class EtudiantMenuPrincipalController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void openExercice(ActionEvent event){
        MainEtudiant.stage.setScene(MainEtudiant.selectionnerExercice);
    }

    public void closeApplication(){
        Platform.exit();
        System.exit(0);
    }

    public void tutoriel(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention !");
        alert.setHeaderText("Fonctionnalité en cours de développement.");
        alert.setContentText("Le tutoriel n'est pas encore disponible. Veuillez patienter");
        alert.showAndWait();
    }
}
