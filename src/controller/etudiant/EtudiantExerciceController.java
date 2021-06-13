package controller.etudiant;

import Application.MainEtudiant;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.ModeEntrainement;
import model.ModeEvaluation;
import model.Ressource.Audio;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class EtudiantExerciceController implements Initializable {

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


    @FXML
    private Button boutonAide;

    @FXML
    private Menu menuSolution;

    @FXML
    private MenuItem menuItemSolution;

    @FXML
    private Label motsDecouverts;

    @FXML
    private Label totalMots;

    @FXML
    private HBox affichageTempsReel;

    @FXML
    private Label minutes;

    @FXML
    private Label secondes;

    @FXML
    private Slider volume;

    @FXML
    private Button play;

    @FXML
    private MenuItem saveExercice;

    private boolean isPlaying = false;

    private boolean timeFinish = false;

    private Timer timer;

    private int nbMots;

    private int totalmotsDecouverts = 0;

    private String[] tab;                 // on met la phrase ds un tableau en separant chaque mot
    private String[] tabOcult;

    private long start;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainEtudiant.modeExercice.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    initWindow();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void updateChronometer() {
        long end = System.currentTimeMillis();

        long time = end - start;
        long minutesTime = (time / 1000) / 60;

        // formula for conversion for
        // milliseconds to seconds
        long secondsTime = (time / 1000) % 60;
        if (MainEtudiant.exercice instanceof ModeEvaluation) {
            int timeAutorise = ((ModeEvaluation) MainEtudiant.exercice).getTempsAutorise();
            int totalSeconds = (int) ((int) (minutesTime * 60) + secondsTime);
            if (totalSeconds > timeAutorise) {
                MainEtudiant.etudiant.setTime(totalSeconds);
                timeFinish = true;
                timer.cancel();
                saveExercice();
            } else {
                int timeRemain = timeAutorise - totalSeconds;
                secondes.setText(String.valueOf(timeRemain % 60));
                minutes.setText(String.valueOf(timeRemain / 60));
            }
        } else {
            minutes.setText(String.valueOf(minutesTime));
            secondes.setText(String.valueOf(secondsTime));
        }

    }

    public void saveExercice() {
        MainEtudiant.etudiant.setMotTrouve(totalmotsDecouverts);
        MainEtudiant.etudiant.setTotalMot(Integer.parseInt(totalMots.getText()));
        MainEtudiant.etudiant.setTextTrouve(texteCache.getText());
        MainEtudiant.etudiant.setNomExercice(MainEtudiant.exercice.getName());
        MainEtudiant.etudiant.setTotalTime(((ModeEvaluation)MainEtudiant.exercice).getTempsAutorise());
        mediaPlayer.stop();
        MainEtudiant.modeExercice.close();
        MainEtudiant.stage.setScene(MainEtudiant.enregister);
        MainEtudiant.stage.centerOnScreen();
        MainEtudiant.stage.show();
    }

    @FXML
    public void validerEssaie() {
        String[] essais = saisie.getText().toLowerCase().split("\\s");
        for (String essai : essais) {
            if (essai.length() >= 1) {
                for (int i = 0; i < tab.length; i++) {   // on verifie si le mot a essayer est dans la phrase
                    // System.out.println(tab[i]);

                    String motActuel = String.valueOf(tab[i]).toLowerCase();   // on met le mot de la phrase à verifier dans une nouvelle variable
                    String motOccultee = String.valueOf(tabOcult[i]).toLowerCase();
                    //System.out.println(motActuel);

                    if (motActuel.equals(essai) && !motOccultee.equals(motActuel)) {  // si le le mot essayé est le mm alors il est remplacer dans la phrase occulté pr qu'on le voit
                        tabOcult[i] = essai;
                        totalmotsDecouverts++;
                        motsDecouverts.setText(String.valueOf(totalmotsDecouverts));
                        //System.out.println("nb de mots trouvé : " + foundWords);    // on affiche le nb de mot trouvé
                    } else if (MainEtudiant.exercice instanceof ModeEntrainement) {
                        if ((((ModeEntrainement) MainEtudiant.exercice).getLettresMini()) != null && essai.length() >= ((ModeEntrainement) MainEtudiant.exercice).getLettresMini() && motActuel.startsWith(essai) && motActuel.length() >= 4) {   // mais si ya un certain nb de lettre qui correspond au mot c'est bon aussi , c qd mm affiché
                            String motIncomplet = essai;


                            if (essai.length() > tabOcult[i].replace("#", "").length()) {
                                for (int longmot = essai.length(); longmot < motActuel.length(); longmot++) {
                                    if (Pattern.matches("[a-zA-Z0-9]", Character.toString(motActuel.charAt(longmot)))) {
                                        motIncomplet += MainEtudiant.exercice.getCaractereOcculation();
                                    } else {
                                        motIncomplet += motActuel.charAt(longmot);
                                    }
                                }
                                tabOcult[i] = motIncomplet;
                            }

                            //foundWords++;
                            //System.out.println("nb de mots trouvé : " + foundWords); // on affiche le nb de mot trtextouvé
                        }
                    }


                    //System.out.println(tabOcult[i]);
                }
                String textTotal = "";
                System.out.println(Arrays.toString(tab));
                for (int i = 0; i < tab.length; i++) {
                    textTotal += tabOcult[i] + " ";
                }
                System.out.println("\n");
                texteCache.setText(textTotal);
                saisie.setText("");
            }
        }


    }

    public void afficherAide(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aide");
        alert.setHeaderText("Voici une aide pour l'exercice");
        alert.setContentText(MainEtudiant.exercice.getAide());
        alert.showAndWait();
    }

    public void afficherSolution(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Solution");
        alert.setHeaderText("Voici la solution de l'exercice");
        alert.setContentText(MainEtudiant.exercice.getRessource().getTranscription());
        alert.showAndWait();
    }

    public void initWindow() throws IOException, URISyntaxException {

        if (MainEtudiant.exercice instanceof ModeEvaluation) {
            menuItemSolution.setDisable(true);
            menuSolution.setDisable(true);
            affichageTempsReel.setVisible(false);

        } else if (MainEtudiant.exercice instanceof ModeEntrainement) {
            saveExercice.setDisable(true);
            if (!((ModeEntrainement) MainEtudiant.exercice).isAffichageSolution()) {
                menuItemSolution.setDisable(true);
                menuSolution.setDisable(true);
            }

            if (!((ModeEntrainement) MainEtudiant.exercice).isAffichageTempsReel()) {
                affichageTempsReel.setVisible(false);
            }
        }
        start = System.currentTimeMillis();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    updateChronometer();
                });

            }
        }, 0, 1000);

        String textCacheOcult = MainEtudiant.exercice.getRessource().getTranscription().replaceAll("[a-zA-Z0-9]", MainEtudiant.exercice.getCaractereOcculation());
        tab = MainEtudiant.exercice.getRessource().getTranscription().split("(\\s|\\.|,|;|\"|\\)|\\(|'|:|\\[|`|]|\\{|})");
        nbMots = 0;
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i]);
            if (!tab[i].equals("")) {
                nbMots++;
            }
        }

        totalMots.setText(String.valueOf(nbMots));
        tabOcult = textCacheOcult.split("(\\s|\\.|,|;|\"|\\)|\\(|'|:|\\[|`|]|\\{|})");
        String textTotal = "";
        for (int i = 0; i < tab.length; i++) {
            textTotal += tabOcult[i] + " ";
        }
        texteCache.setText(textTotal);
        System.out.println(Arrays.toString(tab));
        System.out.println(Arrays.toString(tabOcult));

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

        volume.setValue(mediaPlayer.getVolume() *100);
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volume.getValue() / 100);
            }
        });

        if (MainEtudiant.exercice.getRessource() instanceof Audio) {
            System.out.println("audio");
            if (((Audio) MainEtudiant.exercice.getRessource()).getImage() != null) {
                System.out.println("image");
                File imageFile = writeByte(((Audio) MainEtudiant.exercice.getRessource()).getImage().getFileByte(), ((Audio) MainEtudiant.exercice.getRessource()).getImage().getFileName());
                imageView.setVisible(true);
                Image image = new javafx.scene.image.Image(imageFile.toURI().toString());
                imageView.setImage(image);
            } else {
                imageView.setVisible(false);
            }
        } else {
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

        saisie.setOnAction(event -> {
            validerEssaie();
        });


    }

    public void playVideo(ActionEvent actionEvent) {
        if (!isPlaying){
            mediaPlayer.play();
            isPlaying = true;
            play.setText("Pause");
        }else {
            mediaPlayer.pause();
            isPlaying = false;
            play.setText("Play");
        }
    }


    // Method which write the bytes into a file
    static File writeByte(byte[] bytes, String name) throws IOException {

        //Créer le dossier dans les documents s'il n'existe pas.
        File theDir = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\transcription");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        //Ecrire le fichier dans le dossier dans les documents
        String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\transcription\\" + name;
        try (FileOutputStream stream = new FileOutputStream(path)) {
            stream.write(bytes);
            stream.close();
            return new File(path);
        }

    }


    public void closeApplication(){
        Platform.exit();
        System.exit(0);
    }


    public void closeExercice(ActionEvent event) {
        mediaPlayer.stop();
        MainEtudiant.modeExercice.close();
        MainEtudiant.stage.setScene(MainEtudiant.menuPrincipal);
        MainEtudiant.stage.show();
    }
}
