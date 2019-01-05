package main;
import games.AbstractGame;
import players.GamePlayer;


/**
 * Classe permettant de généraliser la boucle de jeu pour tout les jeux de notre application
 */
public class Orchestrator{

  /**
   * Construit une nouvelle instance de la classe Ochestrator
   */
  public Orchestrator(){}

  
  /**
   * Méthode permettant de jouer à un jeu, du début à la fin
   * @param game Le jeu joué
   */
  public void playGame(AbstractGame game){

    while(!game.isOver()){

      System.out.println("Etat du jeu:"+System.lineSeparator());
      System.out.println(game.situationToString());
      System.out.println("C'est au joueur "+game.getCurrentPlayer()+" de jouer"+System.lineSeparator());
      int move=game.getCurrentPlayer().chooseMove(game);
      

      game.oneTurnComplete(move);

      System.out.println("---------------------------------------------------------"+System.lineSeparator());
    }
    
    /*Fin boucle jeu*/
    
    System.out.println("Etat du jeu a la fin:"+System.lineSeparator()); /*On remets la situation du jeu, plutôt utile pour le TicTacToe*/
    System.out.println(game.situationToString());
    GamePlayer gagnant=game.getWinner();

    if(gagnant==null){

      System.out.println("Egalité");
    }
    else{

      System.out.println("Le gagnant est: "+gagnant);
    }


  }


}
