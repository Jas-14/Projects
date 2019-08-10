package errors;

/**
 * Classe d'exception SyntaxErrorException. C'est une exception qui se produit lorsque la ligne RedCode est incompréhensible pour le Parser, de manière globale.
 */
public class SyntaxErrorException extends CorewarException {

    public SyntaxErrorException(String msg){
        super(msg);
    }

    public SyntaxErrorException(){
        super("Erreur rencontrée lors de la lecture du fichier de RedCode."+System.lineSeparator()+"La ligne de RedCode rencontrée possède une erreur de syntaxe. Impossible de la séparer en trois champs."+System.lineSeparator()+"Le programme doit s'arrêter");

    }



}
