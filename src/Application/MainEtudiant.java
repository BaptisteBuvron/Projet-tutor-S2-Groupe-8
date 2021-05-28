package Application;

import controller.EtudiantController;
import model.Exercice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainEtudiant extends Application {

    public static Stage stage;

    public static Scene menuPrincipal;

    public static Scene selectionnerExercice;

    public static Scene scene1;
    //public static Scene scene2;
    public static Exercice exercice;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        //Menu d'acceuil
        menuPrincipal = new Scene(FXMLLoader.load(getClass().getResource("/ressources/fxml/etudiant/EtudiantMenuPrincipal.fxml")),600,400);

        selectionnerExercice = new Scene(FXMLLoader.load(getClass().getResource("/ressources/fxml/etudiant/EtudiantSelectionnerExercice.fxml")),600,400);



      /*  Parent root1 = FXMLLoader.load(getClass().getResource("/ressources/fxml/etudiant/InterfaceEtudiantEntrainement.fxml"));
        //Parent root2 = FXMLLoader.load(getClass().getResource("/ressources/fxml/scene2.fxml"));
        stage.setTitle("Faire un exercice");
        scene1 = new Scene(root1);*/

        stage.setScene(menuPrincipal);

        // stage.setScene(scene1);
        stage.show();
    }

}
