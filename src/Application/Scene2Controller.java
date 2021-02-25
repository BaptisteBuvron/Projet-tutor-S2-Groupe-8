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


public class Scene2Controller implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Hello world 2");
    }

    public void previousSceneAction(ActionEvent e){
        Main.stage.setTitle("Première page");
        Main.stage.setScene(Main.scene1);
    }









}
