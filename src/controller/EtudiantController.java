package controller;

import Application.MainEtudiant;
import model.Exercice;
import model.ModeEntrainement;
import model.Ressource.Audio;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class EtudiantController implements Initializable {

    @FXML
    private Label consigne;

    @FXML
    private Button validerButton;

    @FXML
    private TextArea texteCache;

    @FXML
    private MediaView mediaView = new MediaView();

    @FXML
    private Slider timeSlider;

    @FXML
    private TextField saisie;


    private File video;

    private MediaPlayer mediaPlayer;

    @FXML
    private ImageView imageView;

    private String[] tab;                 // on met la phrase ds un tableau en separant chaque mot
    private String[] tabOcult;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void validerEssaie(){
        String[] essais = saisie.getText().toLowerCase().split("\\s");
        for (String essai: essais){
            if (essai.length() >= 1){
                for (int i = 0; i < tab.length; i++) {   // on verifie si le mot a essayer est dans la phrase
                    // System.out.println(tab[i]);

                    String motActuel = String.valueOf(tab[i]).toLowerCase();   // on met le mot de la phrase à verifier dans une nouvelle variable
                    //System.out.println(motActuel);

                    if (motActuel.equals(essai)) {  // si le le mot essayé est le mm alors il est remplacer dans la phrase occulté pr qu'on le voit
                        tabOcult[i] = essai;
                        //foundWords++;
                        //System.out.println("nb de mots trouvé : " + foundWords);    // on affiche le nb de mot trouvé
                    }
                    else if (MainEtudiant.exercice instanceof ModeEntrainement){
                        if((((ModeEntrainement) MainEtudiant.exercice).getLettresMini()) != null && essai.length()>=((ModeEntrainement) MainEtudiant.exercice).getLettresMini() && motActuel.startsWith(essai) && motActuel.length()>=4) {   // mais si ya un certain nb de lettre qui correspond au mot c'est bon aussi , c qd mm affiché
                            String motIncomplet=essai;
                            if (essai.length() > tabOcult[i].replace("#","").length()){
                                for(int longmot=essai.length();longmot<motActuel.length();longmot++) {
                                    motIncomplet+=MainEtudiant.exercice.getCaractereOcculation();
                                }
                                tabOcult[i] = motIncomplet;
                            }

                            //foundWords++;
                            //System.out.println("nb de mots trouvé : " + foundWords); // on affiche le nb de mot trtextouvé
                        }
                    }





                    //System.out.println(tabOcult[i]);
                }
                String textTotal= "";
                for (int i = 0; i < tab.length; i++) {
                    textTotal+= tabOcult[i] + " ";
                }
                texteCache.setText(textTotal);
                saisie.setText("");


            }
        }



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
        File selectedFile = fileChooser.showOpenDialog(MainEtudiant.stage);

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

        texteCache.setText(MainEtudiant.exercice.getRessource().getTranscription().replaceAll("[a-zA-Z0-9]", MainEtudiant.exercice.getCaractereOcculation()));
        tab = MainEtudiant.exercice.getRessource().getTranscription().split("\\s");
        tabOcult = texteCache.getText().split("\\s");

        consigne.setText(MainEtudiant.exercice.getConsigne());
        video = writeByte(MainEtudiant.exercice.getRessource().getFileByte(), MainEtudiant.exercice.getRessource().getFileName());
        Media media = new Media(video.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(() -> {
            timeSlider.setMin(0);
            timeSlider.setMax(mediaPlayer.getMedia().getDuration().toMinutes());
            timeSlider.setValue(0);
        });
        if (MainEtudiant.exercice.getRessource() instanceof Audio){
            System.out.println("audio");
            if (((Audio) MainEtudiant.exercice.getRessource()).getImage() != null){
                System.out.println("image");
                File imageFile = writeByte(((Audio) MainEtudiant.exercice.getRessource()).getImage().getFileByte(), ((Audio) MainEtudiant.exercice.getRessource()).getImage().getFileName());
                imageView.setVisible(true);
                Image image = new javafx.scene.image.Image(imageFile.toURI().toString());
                imageView.setImage(image);
            }else {
                imageView.setVisible(false);
            }
        }
        else {
            imageView.setVisible(false);
        }

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                //coding...
                Duration d = mediaPlayer.getCurrentTime();

                timeSlider.setValue(d.toMinutes());
            }
        });

//            time slider

        timeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (timeSlider.isPressed()) {
                double val = timeSlider.getValue();
                mediaPlayer.seek(new Duration(val * 60 * 1000));
            }
        });


    }

    public void playVideo(ActionEvent actionEvent){
        mediaPlayer.play();
    }

    public void pauseVideo(ActionEvent actionEvent){
        mediaPlayer.pause();
    }

    // Method which write the bytes into a file
    static File writeByte(byte[] bytes, String name) throws IOException {
        //Ecrire le fichier dans le dossier dans les documents
        String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"\\ProjetTutoré\\"+ name;
            try(FileOutputStream stream = new FileOutputStream(path)){
                stream.write(bytes);
                stream.close();
                return new File(path);

            }

    }


    public void shutdown() {
        //Delete the video
        System.out.println("au revoir");
    }
}
