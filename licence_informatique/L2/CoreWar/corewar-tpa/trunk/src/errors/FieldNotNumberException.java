package errors;
/**
 * Classe d'exception FieldNotNumberException. Cette exception se produit si l'adresse d'un champ ne correspond pas à un nombre.
 */
public class FieldNotNumberException extends CorewarException {

    public FieldNotNumberException(){
        super("Erreur rencontrée lors de la lecture du fichier RedCode."+System.lineSeparator()+"L'adresse spécifiée dans le champ n'est pas un nombre"+System.lineSeparator()+"Le programme doit s'arrêter");
    }
}
