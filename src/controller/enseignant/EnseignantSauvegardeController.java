package controller.enseignant;

import Application.MainEnseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnseignantSauvegardeController implements Initializable {

    @FXML
    private TextField linkFolder;
    private File selectedDirectory;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void sauvegarder(ActionEvent event) throws IOException {
        if (selectedDirectory != null){
            File newFile = new File(selectedDirectory, MainEnseignant.exercice.getName()+".exercice");
            FileOutputStream fileOut = new FileOutputStream(newFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(MainEnseignant.exercice);
            objectOut.close();
            MainEnseignant.reset();
            MainEnseignant.stage.setScene(MainEnseignant.ecranFinal);
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

    public void retour(ActionEvent event) {
        MainEnseignant.stage.setScene(MainEnseignant.option);
    }
}
