package controller;

import Application.MainEnseignant;
import model.Exercice;
import model.ModeEntrainement;
import model.ModeEvaluation;
import model.Ressource.Audio;
import model.Ressource.Image;
import model.Ressource.Video;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;


public class EnseignantController implements Initializable {

    private File selectedFile;
    private String extensionSelectedFile;

    private File imageSelected;

    private File selectedDirectory;


    @FXML
    Text linkRessource;

    @FXML
    Text linkImage;

    @FXML
    TextField nameExercice;

    @FXML
    HBox ressourceImage;

    @FXML
    TextArea consigne;

    @FXML
    TextArea transcription;

    @FXML
    TextArea aide;

    @FXML
    ToggleGroup modeExercice;

    @FXML
    TextField limiteTemps;

    @FXML
    CheckBox motIncomplet;

    @FXML
    ToggleGroup lettresMinimum;

    @FXML
    CheckBox affichageMotsDecouverts;

    @FXML
    CheckBox affichageSolution;

    @FXML
    TextField occultation;

    @FXML
    Label linkFolder;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	System.out.println("Branche Enseignant Baptiste");
    }

    @FXML
    public void chooseFolderAction(ActionEvent e){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        selectedDirectory = directoryChooser.showDialog(MainEnseignant.stage);
        linkFolder.setText(selectedDirectory.toURI().getPath());


    }

    @FXML
    public void saveAction(ActionEvent e) throws IOException, ClassNotFoundException, URISyntaxException {
        if (nameExercice.getText().trim().isEmpty() || nameExercice.getText() == null) {
            System.out.println("Le titre de l'exercice est vide");
        }else if (selectedFile == null){
            System.out.println("Aucune ressource n'a été sélectionner");
        }
        else if(consigne.getText().trim().isEmpty() || consigne.getText() == null){
            System.out.println("La consigne n'est pas saisie");
        }
        else if(transcription.getText().trim().isEmpty() || transcription.getText() == null){
            System.out.println("La transcripation n'a pas été saisie");
        }
        else if(aide.getText().trim().isEmpty() || aide.getText() == null){
            System.out.println("L'aide n'a pas été saisie");
        }
        else if(occultation.getText().trim().isEmpty() || occultation.getText() == null || occultation.getText().length() > 1){
            System.out.println("Le caractère d'occulation n'a pas été saisie ou est n'est pas correct");
        }
        else if(selectedDirectory == null){
            System.out.println("Le dossier d'enregistrement n'a pas été saisie");
        }

        else{
            switch (((RadioButton) modeExercice.getSelectedToggle()).getText()){
                case "Entrainement":
                    MainEnseignant.exercice = new ModeEntrainement();
                    switch (((RadioButton) lettresMinimum.getSelectedToggle()).getText()){
                        case "2 lettres minimum":
                            ((ModeEntrainement) MainEnseignant.exercice).setLettresMini(2);
                            break;
                        case "3 lettres minimum":
                            ((ModeEntrainement) MainEnseignant.exercice).setLettresMini(3);
                            break;
                        default:
                            break;
                    }

                    if (affichageMotsDecouverts.isSelected()){
                        ((ModeEntrainement) MainEnseignant.exercice).setAffichageTempsReel(true);
                    }
                    if (affichageSolution.isSelected()){
                        ((ModeEntrainement) MainEnseignant.exercice).setAffichageSolution(true);

                    }

                    break;
                case "Evaluation":
                    MainEnseignant.exercice = new ModeEvaluation();
                    break;
            }
            MainEnseignant.exercice.setName(nameExercice.getText());
            MainEnseignant.exercice.setConsigne(consigne.getText());
            MainEnseignant.exercice.setAide(aide.getText());
            MainEnseignant.exercice.setCaractereOcculation(occultation.getText());

            if (extensionSelectedFile.equals("mp4")){
                Video video = new Video();
                video.setFileName(selectedFile.getName());
                video.setFileByte(Files.readAllBytes(selectedFile.toPath()));
                MainEnseignant.exercice.setRessource(video);
            }
            else if(extensionSelectedFile.equals("mp3")) {
                Audio audio = new Audio();
                audio.setFileName(selectedFile.getName());
                audio.setFileByte(Files.readAllBytes(selectedFile.toPath()));
                MainEnseignant.exercice.setRessource(audio);
                if (imageSelected != null){
                    model.Ressource.Image image = new Image();
                    image.setFileByte(Files.readAllBytes(imageSelected.toPath()));
                    image.setFileName(imageSelected.getName());
                    ((Audio ) MainEnseignant.exercice.getRessource()).setImage(image);
                }
            }
            MainEnseignant.exercice.getRessource().setTranscription(transcription.getText());



            save();

        }


    }

    @FXML
    public void chooseImageAction(ActionEvent e){
        //On ajoute une image de fond
        FileChooser fileChooserImage = new FileChooser();
        fileChooserImage.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image jpg", "*.jpg"),
                new FileChooser.ExtensionFilter("Image png", "*.png"),
                new FileChooser.ExtensionFilter("Image jpeg", "*.jpeg")
        );
        fileChooserImage.setTitle("Choisir une image d'illustration");
        imageSelected = null;
        imageSelected = fileChooserImage.showOpenDialog(MainEnseignant.stage);
        linkImage.setText(imageSelected.getName());
    }

    @FXML
    public void chooseFileAction(ActionEvent e) throws IOException {


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
            String fileName = selectedFile.getName();
            linkRessource.setText(fileName);

            //extension du fichier
            String extensionFile = fileName.substring(fileName.lastIndexOf(".") + 1);
            extensionSelectedFile = extensionFile;

            //Main.exercice.setFileName(fileName);

            //Convertir la vidéo en bytes
            //Main.exercice.setFileByte(Files.readAllBytes(selectedFile.toPath()));

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
                ressourceImage.setVisible(true);


                //Récupérer le nom de l'image
                //Main.exercice.setImageName(imageSelected.getName());

                //Convertir l'image en bytes
                //Main.exercice.setImageBytes(Files.readAllBytes(imageSelected.toPath()));

                //Image image = new Image(imageSelected.toURI().toString());
            }
            else {
                imageSelected = null;
                linkImage.setText(null);
                ressourceImage.setVisible(false);
            }

            //Lecture de la vidéo ou d'une musique.
            /*Media media = new Media(selectedFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(false);

            mediaView.setMediaPlayer(mediaPlayer);*/

        }


    }

    public void save() throws IOException, URISyntaxException {


        File newFile = new File(selectedDirectory, MainEnseignant.exercice.getName()+".exercice");
        FileOutputStream fileOut = new FileOutputStream(newFile);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(MainEnseignant.exercice);
        objectOut.close();
        System.out.println("Save success");
    }

    public void read() throws IOException, ClassNotFoundException {
        try{
            FileInputStream fileInt = new FileInputStream("exercice.exercice");
            ObjectInputStream objectInt = new ObjectInputStream(fileInt);
            Object exercice = objectInt.readObject();
            MainEnseignant.exercice = (Exercice) exercice;
            System.out.println("Success");
        }
        catch (FileNotFoundException e){
            System.out.println("Erreur : le fichier n'a pas été trouvé.");
        }



    }




}
