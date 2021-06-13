package controller.etudiant;

import Application.MainEnseignant;
import Application.MainEtudiant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EtudiantSauvegarderExerciceController implements Initializable {

    @FXML
    private TextField linkFolder;
    private File selectedDirectory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void save(ActionEvent event) throws IOException {
        if (selectedDirectory != null){

            File newFile = new File(selectedDirectory, MainEtudiant.etudiant.getNom()+"-"+MainEtudiant.exercice.getName()+".travail");
            FileOutputStream fileOut = new FileOutputStream(newFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(MainEtudiant.etudiant);
            objectOut.close();

           /* File file = new File(selectedDirectory.getPath(), MainEtudiant.etudiant.getNom()+"-"+MainEtudiant.exercice.getName()+".json");
            Writer writer = new FileWriter(file);
            Gson gson = new GsonBuilder().create();
            gson.toJson(MainEtudiant.etudiant, writer);
            writer.flush(); //flush data to file   <---
            writer.close(); //close write          <---
            System.out.println(gson.toJson(MainEtudiant.etudiant));*/
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enregistrement !");
            alert.setHeaderText("Enregistrement de l'exercice.");
            alert.setContentText("L'exercice a été enregistré.");
            alert.showAndWait();
            MainEtudiant.modeExercice.close();
            MainEtudiant.stage.setScene(MainEtudiant.menuPrincipal);
            MainEtudiant.stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Dossier manquant.");
            alert.setContentText("Vous n'avez pas choisi un dossier pour enregistrer l'exercice.");
            alert.showAndWait();
        }
    }

    public void parcourir(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        linkFolder.setText("");
        selectedDirectory = null;
        selectedDirectory = directoryChooser.showDialog(MainEnseignant.stage);
        linkFolder.setText(selectedDirectory.toURI().getPath());
    }
}
