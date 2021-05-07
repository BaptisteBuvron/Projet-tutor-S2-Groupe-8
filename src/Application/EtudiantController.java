package Application;

import Model.Exercice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class EtudiantController implements Initializable {

    @FXML
    Label consigne;

    @FXML
    Button validerButton;

    @FXML
    TextArea texteCache;

    @FXML
    MediaView mediaView = new MediaView();

    private File video;

    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void openExercice(ActionEvent actionEvent) throws IOException, ClassNotFoundException, URISyntaxException {
        //On instancie un selecteur de fichier
        FileChooser fileChooser = new FileChooser();

        //Ajout des filtres d'extensions acceptées.
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Exercice", "*.exercice")
        );

        //Definition du titre de la fenêtre de choix de fichier
        fileChooser.setTitle("Choisir votre exercice");

        //Ouvrir la fichier et variable d'affectation du fichier choisi
        File selectedFile = fileChooser.showOpenDialog(Main.stage);

        try{
            FileInputStream fileInt = new FileInputStream(selectedFile);
            ObjectInputStream objectInt = new ObjectInputStream(fileInt);
            Object exercice = objectInt.readObject();
            MainEtudiant.exercice = (Exercice) exercice;
            System.out.println("Success");
        }
        catch (FileNotFoundException e){
            System.out.println("Erreur : le fichier n'a pas été trouvé.");
        }
        if (MainEtudiant.exercice != null){
            initWindow();
        }


    }

    public void initWindow() throws IOException, URISyntaxException {
        texteCache.setText(MainEtudiant.exercice.getRessource().getTranscription());
        video = writeByte(MainEtudiant.exercice.getRessource().getFileByte());
        Media media = new Media(video.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

    }

    // Method which write the bytes into a file
    static File writeByte(byte[] bytes) throws IOException {
        //Ecrire le fichier dans le dossier dans les documents
        String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"\\ProjetTutoré\\"+ MainEtudiant.exercice.getRessource().getFileName();
            try(FileOutputStream stream = new FileOutputStream(path)){
                stream.write(MainEtudiant.exercice.getRessource().getFileByte());
                stream.close();
                return new File(path);

            }

    }

    public void shutdown() {
        //Delete the video
        System.out.println("au revoir");
    }
}
