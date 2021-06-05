package controller.etudiant;

import Application.MainEtudiant;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
}
