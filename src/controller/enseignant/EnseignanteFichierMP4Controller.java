package controller.enseignant;

import Application.MainEnseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Ressource.Audio;
import model.Ressource.Video;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class EnseignanteFichierMP4Controller implements Initializable {


    private File selectedFile;

    private String extensionFile;

    @FXML
    private TextField lienFichier;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void importerFichier(ActionEvent event) throws IOException {
        if (selectedFile != null){
            if (extensionFile.equals("mp4")){
                Video video = new Video();
                video.setFileName(selectedFile.getName());
                video.setFileByte(Files.readAllBytes(selectedFile.toPath()));
                MainEnseignant.exercice.setRessource(video);
                MainEnseignant.stage.setScene(MainEnseignant.edition1);
            }
            if (extensionFile.equals("mp3")){
                Audio audio = new Audio();
                audio.setFileName(selectedFile.getName());
                audio.setFileByte(Files.readAllBytes(selectedFile.toPath()));
                MainEnseignant.exercice.setRessource(audio);
                MainEnseignant.stage.setScene(MainEnseignant.fichierMP3);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Ressource manquante.");
            alert.setContentText("Vous n'avez pas choisi une ressource.");
            alert.showAndWait();
        }

    }

    public void ouvrirFichier(ActionEvent event) {

        //On instancie un selecteur de fichier
        FileChooser fileChooser = new FileChooser();

        //Ajout des filtres d'extensions acceptées.
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichier video", "*.mp4"),
                new FileChooser.ExtensionFilter("Fichier audio", "*.mp3")
        );

        //Definition du titre de la fenêtre de choix de fichier
        fileChooser.setTitle("Choisir votre ressource");

        //Ouvrir la fichier et variable d'affectation du fichier choisi
        selectedFile = fileChooser.showOpenDialog(MainEnseignant.stage);

        //Si un fichier est sélectionné
        if (selectedFile != null) {

            //nom complet du fichier
            String fileName = selectedFile.getPath();
            lienFichier.setText(fileName);

            //extension du fichier
            extensionFile = fileName.substring(fileName.lastIndexOf(".") + 1);



        }
    }

    public void retour(ActionEvent event) {
        MainEnseignant.stage.setScene(MainEnseignant.nomExercice);

    }
}
