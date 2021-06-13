package Application;



import model.Etudiant;
import model.Exercice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainEnseignant extends Application {



    public static Stage stage;
    public static Scene scene1;

    public static Scene menuPrincipal;
    public static Scene nomExercice;
    public static Scene fichierMP4;
    public static Scene fichierMP3;
    public static Scene edition1;
    public static Scene edition2;
    public static Scene option;
    public static Scene sauvegarder;
    public static Scene ecranFinal;
    public static Scene selectionnerExercice;

    public static Stage correction = new Stage();

    public static Etudiant etudiant;
    public static Class getClass;


    //public static Scene scene2;
    public static Exercice exercice = new Exercice();

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        getClass = MainEnseignant.class;
        load();
        ecranFinal = new Scene(FXMLLoader.load(getClass().getResource("/ressources/fxml/enseignant/EnseignanteEcranFinal.fxml")));
        selectionnerExercice = new Scene(FXMLLoader.load(getClass().getResource("/ressources/fxml/enseignant/EnseignantSelectionnerExercice.fxml")));

        correction.setScene(new Scene(FXMLLoader.load(getClass().getResource("/ressources/fxml/enseignant/EnseignanteCorrection.fxml"))));


        //Parent root1 = FXMLLoader.load(getClass().getResource("/ressources/fxml/enseignanteDeprecated.fxml"));
        stage.setTitle("Reconstitution");
        //scene1 = new Scene(root1);
        stage.setScene(menuPrincipal);
        stage.setResizable(false);
        correction.setResizable(false);
        stage.show();
    }

    public static void load() throws IOException {
        menuPrincipal = new Scene(FXMLLoader.load(getClass.getResource("/ressources/fxml/enseignant/EnseignanteMenuPrincipale.fxml")),600,400);
        nomExercice = new Scene(FXMLLoader.load(getClass.getResource("/ressources/fxml/enseignant/EnseignanteSaisieNomExercice.fxml")));
        fichierMP4 = new Scene(FXMLLoader.load(getClass.getResource("/ressources/fxml/enseignant/EnseignanteFichierMP4.fxml")));
        fichierMP3 = new Scene(FXMLLoader.load(getClass.getResource("/ressources/fxml/enseignant/EnseignanteFichierMP3.fxml")));
        edition1 = new Scene(FXMLLoader.load(getClass.getResource("/ressources/fxml/enseignant/EnseignanteEdition1.fxml")));
        edition2 = new Scene(FXMLLoader.load(getClass.getResource("/ressources/fxml/enseignant/EnseignanteEdition2.fxml")));
        option = new Scene(FXMLLoader.load(getClass.getResource("/ressources/fxml/enseignant/EnseignanteOption.fxml")));
        sauvegarder = new Scene(FXMLLoader.load(getClass.getResource("/ressources/fxml/enseignant/EnseignanteSauvegarde.fxml")));
        stage.setScene(menuPrincipal);
    }

}
