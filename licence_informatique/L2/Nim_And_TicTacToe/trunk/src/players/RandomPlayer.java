package players;
import games.AbstractGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe permettant de faire jouer un joueur automatique tirant ses coups au hasard
 */
public class RandomPlayer extends AbstractPlayer implements GamePlayer {
	
	/**
	 * Attribut permettant d'utiliser l'aléatoire dans les méthodes de RandomPlayer
	 */
	protected Random randomGenerator;
	
	/**
	 * Construit une nouvelle instance de la classe RandomPlayer
	 */
	public RandomPlayer() {
		
		this.randomGenerator=new Random();
	}
	
	@Override
	public int chooseMove(AbstractGame game) {
		
		this.checkSituation(game);
		
		List<Integer> Moves= new ArrayList<>();
		
		Moves=game.validMoves();
		
		return Moves.get(this.randomGenerator.nextInt(Moves.size()));
		
		
		
	}


}
