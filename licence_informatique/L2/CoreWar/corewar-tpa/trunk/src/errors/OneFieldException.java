package errors;

public class OneFieldException extends SyntaxErrorException {

    public OneFieldException(){
        super("Erreur rencontrée lors de la lecture du fichier de RedCode."+System.lineSeparator()+"Erreur de syntaxe, DAT et JMP n'accepte qu'un seul champ. Syntaxe: OP A\""+System.lineSeparator()+"Le programme doit s'arrêter");
    }
}
