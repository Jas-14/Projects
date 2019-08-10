package errors;

/**
 * Classe d'exception BadOperatorException. C'est une exception qui se produit si l'opérateur lu par le Parser n'est pas reconnu (c'est à dire non implémenté par notre version du Corewar) ou alors que c'est un opérateur inexistant.
 */
public class BadOperatorException extends CorewarException {

    public BadOperatorException(){
        super("Erreur rencontrée lors de la lecture du fichier RedCode."+System.lineSeparator()+"L'opérateur RedCode utilisé n'est pas reconnu pour cette version du logiciel ou n'existe pas."+System.lineSeparator()+"Le programme doit s'arrêter");

    }
}
