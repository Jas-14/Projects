package errors;

/**
 * Classe d'exception BadInstructionException. Cette exception est rencontrée si la ligne de RedCode était syntaxiquement correcte, mais elle n'a pas de sens pour l'instruction.
 * Exemple: Les données sont uniquement des entiers. Donc une instruction DAT comportera obligatoirement un modifieur de champ '#', sinon ce n'est pas une instruction valide.
 */
public class BadInstructionException extends CorewarException {

    public BadInstructionException(){
        super("Erreur rencontrée lors de la lecture du fichier RedCode"+System.lineSeparator()+"L'instruction n'a pas de sens, elle n'est pas possible à exécuter."+System.lineSeparator()+"Le programme doit s'arrêter");

    }
}
