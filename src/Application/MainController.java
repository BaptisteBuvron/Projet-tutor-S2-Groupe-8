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

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;


public class MainController implements Initializable {


    @FXML
    private MediaView mediaView;

    @FXML
    private ImageView imageView;

    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Hello world 1");
    }

    @FXML
    public void chooseFileAction(ActionEvent e) throws IOException {
        //Si un media est en lecture on le stop.
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        //Si une image est affiché on l'a supprime
        if (imageView.getImage() != null) {
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

        //Si un fichier est sélectionné
        if (selectedFile != null) {

            //nom complet du fichier
            String fileName = selectedFile.getName();

            //extension du fichier
            String extensionFile = fileName.substring(fileName.lastIndexOf(".") + 1);

            Main.exercice.setFileName(fileName);

            //Convertir la vidéo en bytes
            Main.exercice.setFileByte(Files.readAllBytes(selectedFile.toPath()));

            //Créer le dossier dans les documents s'il n'existe pas.
            /*File theDir = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"\\ProjetTutoré");
            if (!theDir.exists()){
                theDir.mkdirs();
            }*/

            //Ecrire le fichier dans le dossier dans les documents
            /*try(FileOutputStream stream = new FileOutputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"\\ProjetTutoré\\"+ Main.exercice.getFileName())){
                stream.write(Main.exercice.getFileByte());
            }*/

            //Si le fichier est une musique
            if (extensionFile.equals("mp3")) {
                //On ajoute une image de fond
                FileChooser fileChooserImage = new FileChooser();
                fileChooserImage.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image jpg", "*.jpg"),
                        new FileChooser.ExtensionFilter("Image png", "*.png"),
                        new FileChooser.ExtensionFilter("Image jpeg", "*.jpeg")
                );
                fileChooserImage.setTitle("Choisir une image d'illustration");
                File imageSelected = null;
                while (imageSelected == null) {
                    imageSelected = fileChooserImage.showOpenDialog(Main.stage);
                }

                //Récupérer le nom de l'image
                Main.exercice.setImageName(imageSelected.getName());

                //Convertir l'image en bytes
                Main.exercice.setImageBytes(Files.readAllBytes(imageSelected.toPath()));

                Image image = new Image(imageSelected.toURI().toString());
                imageView.setImage(image);
            }

            //Lecture de la vidéo ou d'une musique.
            Media media = new Media(selectedFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(false);

            mediaView.setMediaPlayer(mediaPlayer);

        }


    }

    @FXML
    public void nextSceneButton(ActionEvent e) {
        Main.stage.setTitle("deuxième page");
        Main.stage.setScene(Main.scene2);

    }


}
