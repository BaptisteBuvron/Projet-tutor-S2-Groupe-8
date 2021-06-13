package controller.enseignant;

import Application.MainEnseignant;
import Application.MainEtudiant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnseignanteCorrection implements Initializable {


    @FXML
    private Label nomEtudiant;

    @FXML
    private Label nomExercice;

    @FXML
    private Label motDecouverts;

    @FXML
    private Label totalMots;

    @FXML
    private Label time;


    @FXML
    private Label totalTime;

    @FXML
    private TextArea text;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainEnseignant.correction.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                initWindow();
            }
        });

    }

    private void initWindow() {
        nomEtudiant.setText(MainEnseignant.etudiant.getNom());
        nomExercice.setText(MainEnseignant.etudiant.getNomExercice());
        motDecouverts.setText(String.valueOf(MainEnseignant.etudiant.getMotTrouve()));
        totalMots.setText(String.valueOf(MainEnseignant.etudiant.getTotalMot()));
        time.setText(String.valueOf(MainEnseignant.etudiant.getTime()));
        totalTime.setText(String.valueOf(MainEnseignant.etudiant.getTotalTime()));
        text.setText(String.valueOf(MainEnseignant.etudiant.getTextTrouve()));
    }

    public void retour(ActionEvent event) {
        MainEnseignant.correction.close();
        MainEnseignant.stage.setScene(MainEnseignant.selectionnerExercice);
        MainEnseignant.stage.show();
    }

    public void closeCorrect(ActionEvent event) {
        MainEnseignant.correction.close();
        MainEnseignant.stage.setScene(MainEnseignant.menuPrincipal);
        MainEnseignant.stage.show();
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
