package errors;

/**
 * Classe d'exception InvalidSizePgrm. Cette exception est levée
 * si les programmes qui sont passés en argument à la machine
 * virtuelle sont trop grands pour pouvoir tenir dans la mémoire,
 * à eux deux.
 */
public class InvalidSizePgrm extends CorewarException {

    public InvalidSizePgrm(){
        super("Erreur rencontrée lors de l'initialisation de la machine."+System.lineSeparator()+
                "Les deux programmes que vous souhaitez opposer ne peuvent pas rentrer dans la mémoire."
                +System.lineSeparator()+"Le programme doit s'arrêter.");
    }
}
