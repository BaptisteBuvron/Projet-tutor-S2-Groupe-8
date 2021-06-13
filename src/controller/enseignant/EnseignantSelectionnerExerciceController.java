package controller.enseignant;

import Application.MainEnseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Etudiant;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EnseignantSelectionnerExerciceController implements Initializable {

    @FXML
    private TextField pathFile;

    private File selectedFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void cancel(ActionEvent event){
        pathFile.setText("");
        selectedFile = null;
        MainEnseignant.etudiant = null;
        MainEnseignant.stage.setScene(MainEnseignant.menuPrincipal);
    }

    @FXML
    public void openAExercice(ActionEvent event){
        //On instancie un selecteur de fichier
        FileChooser fileChooser = new FileChooser();

        //Ajout des filtres d'extensions acceptées.
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Travail", "*.travail")
        );
        pathFile.setText("");

        //Definition du titre de la fenêtre de choix de fichier
        fileChooser.setTitle("Choisir votre exercice à corriger");

        //Ouvrir la fichier et variable d'affectation du fichier choisi
        selectedFile = fileChooser.showOpenDialog(MainEnseignant.stage);
        if (selectedFile != null){
            pathFile.setText(selectedFile.getPath());
        }


    }

    public void importExercice(ActionEvent event) throws IOException {
        if (selectedFile != null){
            try{
                FileInputStream fileInt = new FileInputStream(selectedFile);
                ObjectInputStream objectInt = new ObjectInputStream(fileInt);
                try {
                    Object etudiant = objectInt.readObject();
                    MainEnseignant.etudiant = (Etudiant) etudiant;

                }
                catch (InvalidClassException e){
                    pathFile.setText("");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Le fichier n'est pas le travail d'un étudiant.");
                    alert.setContentText("Veuillez sélectionner un autre exercice.");
                    alert.showAndWait();
                }

            }
            catch (FileNotFoundException | ClassNotFoundException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Le fichier n'a pas été trouvé.");
                alert.setContentText("Vérifier que le fichier " + selectedFile.getPath() + " existe.");
                alert.showAndWait();
            }
            if (MainEnseignant.etudiant != null){
                MainEnseignant.stage.close();
                MainEnseignant.correction.show();

            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Exercice manquant.");
            alert.setContentText("Vous n'avez pas importé d'exercice à corriger.");
            alert.showAndWait();
        }

    }
}
