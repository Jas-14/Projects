package errors;

public class InvalidSizePgrm extends CorewarException {

    public InvalidSizePgrm(){
        super("Erreur rencontrée lors de l'initialisation de la machine."+System.lineSeparator()+
                "Les deux programmes que vous souhaitez opposer ne peuvent pas rentrer dans la mémoire."
                +System.lineSeparator()+"Le programme doit s'arrêter.");
    }
}
