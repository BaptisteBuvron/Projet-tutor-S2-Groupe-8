package Application;



import Model.Exercice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainEnseignant extends Application {

    public static Stage stage;
    public static Scene scene1;
    //public static Scene scene2;
    public static Exercice exercice;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent root1 = FXMLLoader.load(getClass().getResource("/ressources/fxml/main.fxml"));
        //Parent root2 = FXMLLoader.load(getClass().getResource("/ressources/fxml/scene2.fxml"));
        stage.setTitle("Créer un exercice");
        scene1 = new Scene(root1);
        stage.setScene(scene1);
        stage.setMaximized(true);
        stage.show();
    }

}
