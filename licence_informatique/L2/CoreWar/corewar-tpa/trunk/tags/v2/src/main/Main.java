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
        }else if(args.length==1){

            System.err.println("Erreur, il faut donner le nom de deux fichiers contenant le code.");
            System.exit(-1);
        }

        CoreWarGame game=new CoreWarGame(args[0],args[1]);

        game.gameLoop();

    }


}
