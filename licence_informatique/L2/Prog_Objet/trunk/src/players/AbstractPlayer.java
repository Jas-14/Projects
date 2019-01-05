package players;
import games.AbstractGame;


/**
Classe abstraite AbstractPlayer pour pouvoir généraliser la gestion de l'exception de chooseMove*/
public abstract class AbstractPlayer{
	
	/**
	 * Méthode qui permet d'empêcher la fonction chooseMove d'être utilisée 
		quand elle ne doit plus l'être (quand la partie est terminée)
		@param situation La situation de jeu sur laquelle on appelle chooseMove
		@throws IllegalArgumentException
	 */
	public void checkSituation(AbstractGame situation) throws IllegalArgumentException{
		
		if(situation.isOver()){
		/* permet d'empêcher la fonction chooseMove d'être utilisée 
		quand elle ne doit plus l'être (quand la partie est terminée)*/
		throw new IllegalArgumentException("C'est fini ! Le jeu est terminé !");

		}
		
	}
	
	@Override
	public String toString() {
		
		return "Ordinateur #"+this.hashCode();
	}
	
		
}
	
