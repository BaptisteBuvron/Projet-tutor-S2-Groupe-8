package controller.enseignant;

import Application.MainEnseignant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Exercice;

public class EnseignanteEcranFinalController {
    public void openExercice(ActionEvent event) {

        MainEnseignant.exercice = new Exercice();
        MainEnseignant.stage.setScene(MainEnseignant.nomExercice);
    }


    public void goMenu(ActionEvent event) {
        MainEnseignant.stage.setScene(MainEnseignant.menuPrincipal);

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
