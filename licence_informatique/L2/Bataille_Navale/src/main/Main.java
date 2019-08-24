package main;
import game.*;
import game.player.Humain;
import game.player.IA;
import game.player.Player;
import gui.Window;

import java.util.Scanner;

/**
 * Classe Main de l'application
 */
public class Main {

    /**
     * Méthode main
     * @param args Signifie si l'exécution doit être en console
     * ou en interface graphique
     */
    public static void main(String[] args) {

        String textError="Pour jouer à la bataille navale," +
                "veuillez préciser sous quel mode." +
                System.lineSeparator()+"Indiquez "+
                "'c' pour console ou 'g' pour interface graphique";

        if(args.length==0){

            System.err.println(textError);
            System.exit(-1);

        }

        Scanner s=new Scanner(System.in);

        Player humain=new Humain("Humain",s);

        Player IA=new IA();

        BatailleNavale game= new BatailleNavale(humain,IA);

        if(args[0].equals("c")) {

            System.out.println(game.jeu());

        }else if(args[0].equals("g")){

            game.initGame();

            new Window(game.getGrilleJoueurAlea(),game.getGrilleJoueurH());
        }else{

            System.err.println(textError);
            System.exit(-1);
        }

        s.close();

    }
}
