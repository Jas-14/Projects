package players;
import games.AbstractGame;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe humain, un joueur humain*/
public class Human extends AbstractPlayer implements GamePlayer{

	/**
	 * Le nom de la personne
	 */
	protected String name;
	
	/**
	 * Le scanner du clavier ouvert pour que le joueur puisse entre ses coups
	 */
	public Scanner scan;
	
	
	/**
	 * Construit une nouvelle instance de la classe Human
	 * @param name Le nom du joueur
	 * @param scan Le scanner ouvert qui scrute le clavier
	 */
	public Human(String name,Scanner scan) {
		
		this.name=name;
		this.scan=scan;
		
		
	}
	
	
	
	
  /*Méthode de GamePlayer*/
  @Override
  public int chooseMove(AbstractGame game){

	  this.checkSituation(game); //Voir AbstractPlayer
	  
	  List<Integer> Moves= new ArrayList<>();
	  
	  int i=-1; /*La variable i va recevoir le coup donné par le joueur. Elle est 
	  initialisée à -1 car aucun jeu ne l'utilise*/
	  
	  Moves=game.validMoves();
	  System.out.println("Voici les coups disponibles");
	  System.out.println(game.moveToString(Moves));
	  
	  
	  System.out.println("Saisissez votre coup:");
	  i=this.scan.nextInt();
	  
	  while(!(Moves.contains(i))) { 
	  /*C'est peut être mieux de faire un do while
	  mais au moins, on peut afficher "coup incorrect" avec un while simple!*/
		  
		  System.out.println("Coup incorrect. Veuillez réessayer:");
		  i=this.scan.nextInt();
		  
		  
	  }	  
	 
	  
	  return i;
	  
	
	  
	  
  }
  
  @Override
  public String toString() {
	  
	  
	  return this.name;
	  
	  
  }
  
  


}
