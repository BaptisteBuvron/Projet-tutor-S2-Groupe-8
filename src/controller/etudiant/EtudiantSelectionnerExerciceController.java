package controller.etudiant;

import Application.MainEtudiant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Exercice;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EtudiantSelectionnerExerciceController implements Initializable
{

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
        MainEtudiant.exercice = null;
        MainEtudiant.stage.setScene(MainEtudiant.menuPrincipal);
    }

    @FXML
    public void openAExercice(ActionEvent event){
        //On instancie un selecteur de fichier
        FileChooser fileChooser = new FileChooser();

        //Ajout des filtres d'extensions acceptées.
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Exercice", "*.exercice")
        );

        //Definition du titre de la fenêtre de choix de fichier
        fileChooser.setTitle("Choisir votre exercice");

        //Ouvrir la fichier et variable d'affectation du fichier choisi
        selectedFile = fileChooser.showOpenDialog(MainEtudiant.stage);
        if (selectedFile != null){
            pathFile.setText(selectedFile.getPath());
        }


    }

    public void importExercice(ActionEvent event) throws IOException {
        if (selectedFile != null){
            try{
                FileInputStream fileInt = new FileInputStream(selectedFile);
                ObjectInputStream objectInt = new ObjectInputStream(fileInt);
                Object exercice = objectInt.readObject();
                MainEtudiant.exercice = (Exercice) exercice;

            }
            catch (FileNotFoundException | ClassNotFoundException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Le fichier n'a pas été trouvé.");
                alert.setContentText("Vérifier que le fichier " + selectedFile.getPath() + " existe.");
                alert.showAndWait();
            }
            if (MainEtudiant.exercice != null){
                //TODO CHANGE SCENE, GO TO SELECT NAME OF THE STUDENT

            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Exercice manquant.");
            alert.setContentText("Vous n'avez pas importé d'exercice.");
            alert.showAndWait();
        }

    }



}
