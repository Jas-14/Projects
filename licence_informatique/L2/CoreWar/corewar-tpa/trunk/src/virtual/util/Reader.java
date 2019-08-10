package virtual.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Classe Reader pour lire un fichier texte RedCode et le transformer
 * en un programme RedCode.
 */
public class Reader {

    /**
     * Méthode permettant de lire un fichier et de le convertir en un tableau
     * de String
     * @param namefile Le nom du fichier à lire
     * @return La liste des lignes du fichier, sans les sauts de ligne
     */
    public static List<String> readPgrm(String namefile){

        Path path=null;
        try {
        	path= Paths.get(namefile);
        }catch(InvalidPathException e){

            System.err.println("Erreur, le fichier RedCode n'a pas été trouvé"+System.lineSeparator()+"Le programme doit s'arrêter.");
            System.exit(-1);

        }

        List<String> pgrm=null;


        try{

            pgrm= Files.readAllLines(path);

        }catch (IOException e){

            e.printStackTrace();
            System.err.println("Une erreur a été rencontrée lors de la lecture"+System.lineSeparator()+"Le programme doit s'arrêter");
            System.exit(-1);
        }

        if(pgrm.isEmpty()){

            throw new IllegalStateException("Le fichier est vide"+System.lineSeparator()+"Le programme doit s'arrêter");
        }

        return pgrm;

    }

}
