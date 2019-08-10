package errors;

/**
 * Classe d'exception FieldSyntaxException. Cette exception se produit si le champ lu par le Parser n'est pas reconnu comme un champ (deux caractères maximum)
 */
public class FieldSyntaxException extends CorewarException{

    public FieldSyntaxException(){
        super("Erreur rencontrée lors de la lecture du fichier RedCode."+System.lineSeparator()+"Le champ lu possède une erreur de syntaxe. Il ne doit comporter qu'un nombre, éventuellement un modifieur de champ avant celui ci."+System.lineSeparator()+"Le programme doit s'arrêter");
    }


}
