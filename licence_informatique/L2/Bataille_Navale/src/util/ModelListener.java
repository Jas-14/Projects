package util;

/**
 * Interface ModelListener
 */
public interface ModelListener {

	/**
     * Méthode qui est appelée pour modifier l'affichage d'une fenêtre
     * @param source L'objet qui sera modifié
     */
    void modelUpdated(Object source);
}