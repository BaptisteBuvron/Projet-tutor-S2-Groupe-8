package controller.enseignant;

import Application.MainEnseignant;
import javafx.event.ActionEvent;
import model.Exercice;

public class EnseignanteEcranFinalController {
    public void openExercice(ActionEvent event) {

        MainEnseignant.exercice = new Exercice();
        MainEnseignant.stage.setScene(MainEnseignant.nomExercice);
    }


    public void goMenu(ActionEvent event) {
        MainEnseignant.stage.setScene(MainEnseignant.menuPrincipal);

    }
}
