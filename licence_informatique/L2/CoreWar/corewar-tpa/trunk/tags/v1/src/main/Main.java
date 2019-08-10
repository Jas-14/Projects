package main;

/**
 * Classe main de l'application.
 */
public class Main {

    public static void main(String[] args) {

        if(args.length==0){

            System.err.println("Erreur. Il faut donner dans la ligne de commande le nom du fichier contenant le" + System.lineSeparator()+
                    "code RedCode que vous voulez exécuter dans la machine");
            System.exit(-1);
        }

        Master game=new Master(String.join(" ",args));

        game.gameLoop();

    }


}
