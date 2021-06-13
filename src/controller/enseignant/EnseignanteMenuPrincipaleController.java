package controller.enseignant;

import Application.MainEnseignant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.Exercice;

import java.net.URL;
import java.util.ResourceBundle;

public class EnseignanteMenuPrincipaleController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void createExercice(ActionEvent event) {
        MainEnseignant.exercice = new Exercice();
        MainEnseignant.stage.setScene(MainEnseignant.nomExercice);
    }

    public void corriger(){
        MainEnseignant.stage.setScene(MainEnseignant.selectionnerExercice);
    }

    public void closeApplication(){
        Platform.exit();
        System.exit(0);
    }
}
