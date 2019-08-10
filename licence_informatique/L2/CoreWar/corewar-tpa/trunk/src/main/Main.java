package main;

import GUI.Welcome_Window;
import errors.CorewarException;
import errors.InvalidSizePgrm;

/**
 * Classe main de l'application.
 */
public class Main {

    /**
     * Méthode main de la partie CoreWar
     * @param args Le type de lancement et les fichiers si besoin
     */
    public static void main(String[] args) {

        String textError="Fonctionnement du main:"+System.lineSeparator()+"-Pour un" +
                "lancement en mode console: c cheminfichier1 cheminfichier2"+System.lineSeparator()+
                "-Pour un lancement en mode interface graphique: g";

        if(args.length==0){

            System.err.println(textError);
            System.exit(-1);
        }

        if(args[0].equals("c")){

            if(args.length<3){

                System.err.println(textError);
                System.exit(-1);
            }

            CoreWarGame game=null;
            try{
                game = new CoreWarGame(args[1], args[2]);
            }catch (CorewarException e){

                e.printStackTrace();
                System.exit(-1);
            }

            game.gameLoop();

            }else if(args[0].equals("g")){

            new Welcome_Window();

            }else {

            System.err.println(textError);
        }

    }


}
