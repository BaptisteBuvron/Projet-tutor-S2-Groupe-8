package controller.etudiant;

import Application.MainEtudiant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EtudiantSaisirNomController implements Initializable {


    @FXML
    private TextField nomEtudiant;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void annulerSaisieNom(ActionEvent event){
        MainEtudiant.stage.setScene(MainEtudiant.selectionnerExercice);

    }

    public void validerSaisieNom(ActionEvent event){
        if (nomEtudiant.getText().trim().isEmpty() || nomEtudiant.getText() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Nom manquant.");
            alert.setContentText("Vous n'avez pas saisie votre nom.");
            alert.showAndWait();
        }else {
            MainEtudiant.etudiant.setNom(nomEtudiant.getText());
            MainEtudiant.stage.close();
            MainEtudiant.modeExercice.show();
            MainEtudiant.modeExercice.setMaximized(true);
            //TODO Launch exercice
        }
    }


}
