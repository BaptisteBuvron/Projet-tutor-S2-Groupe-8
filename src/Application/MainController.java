package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;



public class MainController implements Initializable {


    @FXML
    private MediaView mediaView;

    @FXML
    private ImageView imageView;

    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Hello world");
    }

    @FXML
    public void chooseFileAction(ActionEvent e){
        //Si un media est en lecture on le stop.
        if (mediaPlayer != null){
            mediaPlayer.stop();
        }

        //Si une image est affiché on l'a supprime
        if (imageView.getImage() != null){
            imageView.setImage(null);
        }

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
        File selectedFile = fileChooser.showOpenDialog(Main.stage);

        //Si un fichier est sélectionner
        if (selectedFile != null) {

            //nom complet du fichier
            String fileName = selectedFile.getName();

            //extension du fichier
            String extensionFile = fileName.substring(fileName.lastIndexOf(".")+1);

            //Si le fichier est une vidéo
            if (extensionFile.equals("mp3")){
                //On ajoute une image de fond
                Image image = new Image("https://img.lignes-formations.com/wp-content/uploads/sites/45/2019/05/formation-reportage-photo-niveau-1.jpg");
                imageView.setImage(image);
            }

            //Lecture de la vidéo ou d'une musique.
            Media media = new Media(selectedFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(false);

            mediaView.setMediaPlayer(mediaPlayer);

        }


    }








}
