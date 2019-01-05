package players;
import games.AbstractGame;

/**
 * Interface GamePlayer permettant d'implémenter tout les types
de joueurs, automatiques ou non*/

public interface GamePlayer{

  /**
   * Méthode permettant de demander le coup joué selon le jeu
  et retourner l'indice de ce coup dans la liste des
  validMoves de chaque jeu
  * @param game Le jeu joué actuellement
  * @return Le coup entré par le joueur
  */
  public int chooseMove(AbstractGame game);

}
