package Application;

import java.util.Scanner;

public class Algo {
    public static void main(String[] args) {

        String Texte = "   Le seul moyen de le stopper serait d’arrêter tous les ordinateurs, ce qui aurait les mêmes conséquences que de laisser Prélude lancer les bombes. Depuis longtemps, toutes les installations à risque étaient contrôlées par des ordinateurs. Si l’on stoppait les ordinateurs, les centrales nucléaires s’emballeraient, les silos nucléaires cracheraient leur mort sur toute la planète. Bien entendu, l’économie mondiale dirigée par la bourse, s’effondrerait. David ne savait plus quoi faire et, manifestement, tous les militaires présents dans la salle comptaient sur lui pour résoudre cette crise.";
        String TexteOcult;
        String essai;
        int foundWords = 0;
        String caracOcult="pasuneseulvaleur";
        int nbLettreMin=2; // c'est ne nb de lettre min pr reveler un mot en entier, on peut le changer pour le mettre à 3 ou meme 4
        String motIncomplet;

        Texte = Texte.trim();   // on eleve les espace en trop a la fin et au debut de la phrase
        // System.out.println(Texte);

        Texte = Texte.replaceAll(" +", " ");  // on supprime les doubles espace entre les mots
        // System.out.println(Texte);


        while(caracOcult.length()!=1) {
            System.out.println("Quel est le caractère d'occultation ? ");	// on demande le caractère d'occultation
            System.out.println("Veuillez inserez un seul caractère : ");
            Scanner scan = new Scanner(System.in);
            caracOcult= scan.nextLine();
        }

        TexteOcult = Texte.replaceAll("[a-zA-Z0-9]", caracOcult);  // on occulte le texte dans une autre variable

        System.out.println(Texte);
        System.out.println(TexteOcult); // on affich pr verifier si tt est bon

        String[] tab = Texte.split("\\s");                 // on met la phrase ds un tableau en separant chaque mot
        String[] tabOcult = TexteOcult.split("\\s");      // on met la phrase occulté ds un tableau en separant chaque mot

        System.out.println("Nombre de mots à trouver : "+tab.length);  //affiche le nb de mot a trouver

        for (int j = 0; foundWords < tab.length; j++) {  // tant que t'as pas trouvé tt les mots la boucle tourne

            System.out.println("Essayez un mot : ");       // t'entre un mot pr essayer de le reveler ds la parti occulté
            Scanner sc = new Scanner(System.in);
            essai = sc.next().toLowerCase();

            for (int i = 0; i < tab.length; i++) {   // on verifie si le mot a essayer est dans la phrase
                // System.out.println(tab[i]);

                String motActuel = String.valueOf(tab[i]).toLowerCase();   // on met le mot de la phrase à verifier dans une nouvelle variable
                // System.out.println(motActuel);

                if (motActuel.equals(essai)) {  // si le le mot essayé est le mm alors il est remplacer dans la phrase occulté pr qu'on le voit
                    tabOcult[i] = essai;
                    foundWords++;
                    System.out.println("nb de mots trouvé : " + foundWords);    // on affiche le nb de mot trouvé
                }


                else if(essai.length()>=nbLettreMin && motActuel.startsWith(essai) && motActuel.length()>=4) {   // mais si ya un certain nb de lettre qui correspond au mot c'est bon aussi , c qd mm affiché

                    //TODO rajouter le char d'occult en fonction du nb de lettre qui manque
                    motIncomplet=essai;
                    for(int longmot=essai.length();longmot<motActuel.length();longmot++) {
                        motIncomplet=motIncomplet+caracOcult;
                    }
                    tabOcult[i] = motIncomplet;
                    //foundWords++;
                    System.out.println("nb de mots trouvé : " + foundWords); // on affiche le nb de mot trtextouvé
                }

                // System.out.println(tabOcult[i]);
            }

            for (int i = 0; i < tab.length; i++) {
                System.out.print(tabOcult[i] + " ");  // réaffiche le texte occulté avec les nouvelle partie révélé
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Bravo vous avez reussi");   // qd t'as fini tu sor de la boucle
    }
}
