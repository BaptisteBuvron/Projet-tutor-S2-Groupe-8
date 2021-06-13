package controller.enseignant;

import Application.MainEnseignant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Ressource.Audio;
import model.Ressource.Image;
import model.Ressource.Video;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class EnseignanteFichierMP3Controller implements Initializable {
    private File imageSelected;

    private String extensionFile;

    @FXML
    private TextField lienFichier;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void importerFichier(ActionEvent event) throws IOException {
        if (imageSelected != null){
            model.Ressource.Image image = new Image();
            image.setFileByte(Files.readAllBytes(imageSelected.toPath()));
            image.setFileName(imageSelected.getName());
            ((Audio) MainEnseignant.exercice.getRessource()).setImage(image);
            MainEnseignant.stage.setScene(MainEnseignant.edition1);
        }
        else {
            MainEnseignant.stage.setScene(MainEnseignant.edition1);

        }
        //TODO CONTINUER


    }

    public void ouvrirFichier(ActionEvent event) {

        FileChooser fileChooserImage = new FileChooser();
        fileChooserImage.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image jpg", "*.jpg"),
                new FileChooser.ExtensionFilter("Image png", "*.png"),
                new FileChooser.ExtensionFilter("Image jpeg", "*.jpeg")
        );
        fileChooserImage.setTitle("Choisir une image d'illustration");
        imageSelected = null;
        imageSelected = fileChooserImage.showOpenDialog(MainEnseignant.stage);

        //Si un fichier est sélectionné
        if (imageSelected != null) {

            //nom complet du fichier
            String fileName = imageSelected.getPath();
            lienFichier.setText(fileName);

            //extension du fichier



        }
    }


    public void retour(ActionEvent event) {
        MainEnseignant.stage.setScene(MainEnseignant.fichierMP4);
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
