package errors;

/**
 * Classe BadModifierException. Cette exception se produit si le modifieur de champ rencontré n'est pas reconnu. Soit il n'est pas implémenté par notre logiciel, soit il n'existe pas.
 */
public class BadModifierException extends CorewarException {

    public BadModifierException(){
        super("Erreur rencontrée lors de la lecture du fichier RedCode"+System.lineSeparator()+"Le modifieur de champ rencontré n'est pas reconnu pour cette version du logiciel ou n'existe pas."+System.lineSeparator()+"Le programme doit s'arrêter");
    }
}
