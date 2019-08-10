package errors;

/**
 * Classe CorewarException, héritant d'Exception directement.Nous considérons que les erreurs liés au Corewar devront impérativement être attrapées, pour le bon fonctionnement de l'ensemble. Classe mère de toutes les exceptions implémentées spécialement pour le CoreWar.
 */
public class CorewarException extends Exception {

    public CorewarException(){
        super();
    }
    /**
     * Constructeur principal de CoreWarException. Les autres constructeurs sont présents pour des raisons de cohéence.
     * @param message Le message décrivant l'erreur
     */
    public CorewarException(String message){
        super(message);
    }

    public CorewarException(Throwable cause){
        super(cause);

    }

    public CorewarException(String message,Throwable cause){
        super(message,cause);
    }


}
