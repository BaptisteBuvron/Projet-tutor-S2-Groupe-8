package Application;

import Model.Exercice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainEtudiant extends Application {

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/etudiant/InterfaceEtudiantEntrainement.fxml"));
        Parent root1 = loader.load();
        //Parent root2 = FXMLLoader.load(getClass().getResource("/ressources/fxml/scene2.fxml"));
        EtudiantController controller = loader.getController();
        stage.setTitle("Faire un exercice");
        scene1 = new Scene(root1);
        stage.setScene(scene1);
        stage.setMaximized(true);
        stage.setOnHidden(e -> controller.shutdown());
        stage.show();
    }

}
